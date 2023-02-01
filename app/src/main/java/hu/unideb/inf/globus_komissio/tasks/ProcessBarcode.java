package hu.unideb.inf.globus_komissio.tasks;

import android.content.Context;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.databases.localcontainers.LoggedUserContainer;
import hu.unideb.inf.globus_komissio.databases.localcontainers.RightsContainer;
import hu.unideb.inf.globus_komissio.databases.models.Rights;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.enums.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.enums.LogLevel;
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

        if(barcode == null || context == null) return null;

        try {

            if (Thread.interrupted()) throw new InterruptedException();

            if(Util.isInternetConnection(context)){

                getLoggedUserFromSQL();
            }
            else {

                getLoggedUserFromRoom();
            }


        } catch (InterruptedException e) {
            ApplicationLogger.logging(LogLevel.FATAL, "A processzor szál megszakadt.");
        }

        return null;
    }

    private void getLoggedUserFromRoom() {
        Room room = Room.getInstance();
        LoggedUserContainer loggedUserContainer = LoggedUserContainer.getsInstance();
        Message message;

        if(room != null){
            try {
                boolean isLogin = false;

                Users user = room.usersDAO().getLoggedUser(barcode);

                if(user != null){

                    setUserToLocalContainer(user);
                    long id = loggedUserContainer.getId();
                    List<UserRights> userRightsList = room.userRightsDAO().getUserRightsById(id);

                    if(userRightsList.size() != 0){

                        List<Rights> rightsList = room.rightsDAO().getAllRights();
                        List<Rights> resultRightsList = new ArrayList<>();

                        for (int i = 0; i < userRightsList.size(); i++) {
                            for (int j = 0; j < rightsList.size(); j++) {
                                if(userRightsList.get(i).getRightId() == rightsList.get(j).getId()){
                                    Rights rights = new Rights();
                                    rights.setId(rightsList.get(j).getId());
                                    rights.setName(rightsList.get(j).getName());
                                    rights.setDescription(rightsList.get(j).getDescription());
                                    resultRightsList.add(rights);
                                }
                            }
                        }

                        for (int i = 0; i < resultRightsList.size(); i++) {
                            if(resultRightsList.get(i).getName().equals("LoginPDA")) {
                                isLogin = true;
                                break;
                            }
                        }

                        setRightsToLocalContainer(resultRightsList);

                        if(isLogin){
                            ApplicationLogger.logging(LogLevel.INFORMATION, "Sikeres bejelentkezés Barcode-dal és LoginPDA jogosultsággal!");
                            message = Util.createMessage(Util.LOGINBARCODE_FINISH, "Sikeres bejelentkezés Barcode-dal!");
                        }
                        else {
                            ApplicationLogger.logging(LogLevel.WARNING, "Nincs jogosultsága a belépéshez!");
                            message = Util.createMessage(Util.LOGINBARCODE_NORIGHT, "Nincs jogosultsága a belépéshez!");
                        }
                    }
                    else {

                        ApplicationLogger.logging(LogLevel.WARNING, "A bejelentkező személy megtalálható az adatbázisban, de nem található hozzá jogosultság(ok)!");
                        message = Util.createMessage(Util.LOGINBARCODE_NORIGHTS, "A bejelentkező személy megtalálható az adatbázisban, de nem található hozzá jogosultság(ok)!");
                    }
                }
                else {
                    ApplicationLogger.logging(LogLevel.WARNING, "A bejelentkező személy nem található meg az adatbázisban!");
                    message = Util.createMessage(Util.LOGINBARCODE_FAILED, "A bejelentkező személy nem található meg az adatbázisban!");
                }

                if(ctpmw != null && ctpmw.get() != null) {
                    ctpmw.get().sendResultToPresenter(message);
                }
            }
            catch (Exception e){
                ApplicationLogger.logging(LogLevel.ERROR, "Hiba történt adatbázisból való olvasás során!");
            }
        }
        else {
            ApplicationLogger.logging(LogLevel.ERROR, "A ROOM adatbázis nem jött létre a program indulásakor!");
        }
    }

    private void getLoggedUserFromSQL(){

        Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
        LoggedUserContainer loggedUserContainer = LoggedUserContainer.getsInstance();
        Message message;

        boolean isLogin = repository.communicator.getLoggedUser(barcode);
        if(isLogin){

            isLogin = repository.communicator.getLoggedUserRights(loggedUserContainer.getId());

            if(isLogin){

                List<RightsContainer> rightsContainerList = loggedUserContainer.getRights();
                for (int i = 0; i < rightsContainerList.size(); i++) {
                    if(rightsContainerList.get(i).getName().equals("LoginPDA")) {
                        isLogin = true;
                        break;
                    }
                }

                if(isLogin){
                    ApplicationLogger.logging(LogLevel.INFORMATION, "Sikeres bejelentkezés Barcode-dal és LoginPDA jogosultsággal!");
                    message = Util.createMessage(Util.LOGINBARCODE_FINISH, "Sikeres bejelentkezés Barcode-dal!");
                }
                else {
                    ApplicationLogger.logging(LogLevel.WARNING, "Nincs jogosultsága a belépéshez!");
                    message = Util.createMessage(Util.LOGINBARCODE_NORIGHT, "Nincs jogosultsága a belépéshez!");
                }
            }
            else {

                ApplicationLogger.logging(LogLevel.WARNING, "A bejelentkező személy megtalálható az adatbázisban, de nem található hozzá jogosultság(ok)!");
                message = Util.createMessage(Util.LOGINBARCODE_NORIGHTS, "A bejelentkező személy megtalálható az adatbázisban, de nem található hozzá jogosultság(ok)!");
            }
        }
        else {
            ApplicationLogger.logging(LogLevel.WARNING, "A bejelentkező személy nem található meg az adatbázisban!");
            message = Util.createMessage(Util.LOGINBARCODE_FAILED, "A bejelentkező személy nem található meg az adatbázisban!");
        }

        if(ctpmw != null && ctpmw.get() != null) {
            ctpmw.get().sendResultToPresenter(message);
        }
    }

    public void setUserToLocalContainer(Users user){
        if(user == null) return;

        LoggedUserContainer loggedUserContainer = LoggedUserContainer.getsInstance();

        loggedUserContainer.setId(user.getId());
        loggedUserContainer.setAccount(user.getAccount());
        loggedUserContainer.setName(user.getName());
        loggedUserContainer.setPassword(user.getPassword());
        loggedUserContainer.setPin(user.getPin());
        loggedUserContainer.setRfid(user.getRfid());
        loggedUserContainer.setBarcode(user.getBarcode());
        loggedUserContainer.setActive(user.getActive());

    }

    public void setRightsToLocalContainer(List<Rights> rightsList){
        if(rightsList == null) return;

        LoggedUserContainer loggedUserContainer = LoggedUserContainer.getsInstance();
        List<RightsContainer> rightsContainerList = new ArrayList<>();

        for (int i = 0; i < rightsList.size(); i++) {
            RightsContainer rightsContainer = new RightsContainer();
            rightsContainer.setId(rightsList.get(i).getId());
            rightsContainer.setName(rightsList.get(i).getName());
            rightsContainerList.add(rightsContainer);
        }
        loggedUserContainer.setRights(rightsContainerList);
    }
}
