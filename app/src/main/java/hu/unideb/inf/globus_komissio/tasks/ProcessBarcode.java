package hu.unideb.inf.globus_komissio.tasks;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.databases.localcontainers.LoggedUserContainer;
import hu.unideb.inf.globus_komissio.databases.localcontainers.RightsContainer;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.enums.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.logger.LogLevel;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.activities.utils.Util;

public class ProcessBarcode implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;
    private String barcode;
    private Context context;
    private Room room;

    public ProcessBarcode(Context context, String barcode) {
        this.context = context;
        this.barcode = barcode;
    }

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.ctpmw = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {

            boolean isLogin = false;

            if (Thread.interrupted()) throw new InterruptedException();

            Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
            LoggedUserContainer loggedUserContainer = LoggedUserContainer.getsInstance();
            Room room = Room.getInstance();

            if(Util.isInternetConnection(context)){

                isLogin = repository.communicator.getLoggedUser(barcode);

                if(isLogin){

                    isLogin = repository.communicator.getLoggedUserRights(loggedUserContainer.getId());

                    if(isLogin){

                        List<RightsContainer> rightsContainerList = loggedUserContainer.getRights();
                        for (int i = 0; i < rightsContainerList.size(); i++) {
                            if(rightsContainerList.get(i).getName().equals("LoginPDA")) {
                                Log.e("", rightsContainerList.get(i).getName());
                                isLogin = true;
                                break;
                            }
                        }

                        if(isLogin){
                            ApplicationLogger.logging(LogLevel.INFORMATION, "Sikeres bejelentkezés Barcode-dal és LoginPDA jogosultsággal!");

                            Message message = Util.createMessage(Util.LOGINBARCODE_FINISH, "Sikeres bejelentkezés Barcode-dal!");
                            if(ctpmw != null && ctpmw.get() != null) {
                                ctpmw.get().sendResultToPresenter(message);
                            }
                        }
                        else {
                            ApplicationLogger.logging(LogLevel.ERROR, "Nincs jogosultsága a belépéshez!");

                            Message message = Util.createMessage(Util.LOGINBARCODE_NORIGHT, "Nincs jogosultsága a belépéshez!");
                            if(ctpmw != null && ctpmw.get() != null) {
                                ctpmw.get().sendResultToPresenter(message);
                            }
                        }
                    }
                    else {

                        ApplicationLogger.logging(LogLevel.ERROR, "A bejelentkező személy megtalálható az adatbázisban, de nem található hozzá jogosultság(ok)!");

                        Message message = Util.createMessage(Util.LOGINBARCODE_NORIGHTS, "A bejelentkező személy megtalálható az adatbázisban, de nem található hozzá jogosultság(ok)!");
                        if(ctpmw != null && ctpmw.get() != null) {
                            ctpmw.get().sendResultToPresenter(message);
                        }
                    }
                }
                else {
                    ApplicationLogger.logging(LogLevel.ERROR, "A bejelentkező személy nem található meg az adatbázisban!");

                    Message message = Util.createMessage(Util.LOGINBARCODE_FAILED, "A bejelentkező személy nem található meg az adatbázisban!");
                    if(ctpmw != null && ctpmw.get() != null) {
                        ctpmw.get().sendResultToPresenter(message);
                    }
                }
            }
            else {

                Message message = Util.createMessage(Util.WIFI_FAILED, "Nincs elérhető WIFI hálózat az applikáció számára!");
                if(ctpmw != null && ctpmw.get() != null) {
                    ctpmw.get().sendResultToPresenter(message);
                }
            }


        } catch (InterruptedException e) {
            ApplicationLogger.logging(LogLevel.FATAL, "A processzor szál megszakadt.");
        }

        return null;
    }
}
