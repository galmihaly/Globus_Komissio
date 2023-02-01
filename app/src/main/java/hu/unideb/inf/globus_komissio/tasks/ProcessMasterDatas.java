package hu.unideb.inf.globus_komissio.tasks;

import android.content.Context;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.databases.models.Logs;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;
import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.enums.LogLevel;
import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.models.Devices;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodeStorages;
import hu.unideb.inf.globus_komissio.databases.models.PickingItems;
import hu.unideb.inf.globus_komissio.databases.models.Pickings;
import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.enums.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.activities.utils.Util;

public class ProcessMasterDatas implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;
    private Room room;
    private Context context;

    private List<UserRights> userRightsList;
    private List<Devices> devicesList;
    private List<Pickings> pickingsList;
    private List<UserArticleTypes> userArticleTypesList;
    private List<PickingItems> pickingItemsList;
    private List<MovementCodeStorages> movementCodeStoragesList;
    private List<UserMovementCodes> userMovementCodesList;
    private List<Articles> articlesList;
    private List<Logs> logsList;
    private List<PrintTemplates> printTemplatesList;

    public ProcessMasterDatas(Context context) {
        this.context = context;
    }

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.ctpmw = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {
            if (Thread.interrupted()) throw new InterruptedException();

            if(Util.isInternetConnection(context)){
                getSQLTablesToLists();

                room = Room.getInstance();
                if(room != null){
                    sendSQLTablesToROOM();
                }
                else {
                    ApplicationLogger.logging(LogLevel.FATAL, "A ROOM adatbázis nem jött létre a program indulásakor.");

                    Message message = Util.createMessage(Util.ROOM_CREATE_FAIL, "A ROOM adatbázis nem jött létre a program indulásakor.");
                    if(ctpmw != null && ctpmw.get() != null) {
                        ctpmw.get().sendResultToPresenter(message);
                    }
                }
            }
            else {
                ApplicationLogger.logging(LogLevel.INFORMATION, "A függő adatok feltöltése ROOM adatbázisba befejeződött.");

                if(ctpmw != null && ctpmw.get() != null) {
                    Message message = Util.createMessage(Util.PROGRAMSTART_FINISH_2, "Az függő adatok feltöltése ROOM adatbázisba befejeződött.");
                    ctpmw.get().sendResultToPresenter(message);
                }
            }


        } catch (InterruptedException e) {
            ApplicationLogger.logging(LogLevel.FATAL, "A processzor szál megszakadt.");
        }

        return null;
    }

    private void getSQLTablesToLists(){
        try {
            Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);

            ApplicationLogger.logging(LogLevel.INFORMATION, "A függő adatok letöltése SQL adatbázisból megkezdődött.");

            userRightsList = repository.communicator.getAllUserRights();
            pickingsList = repository.communicator.getAllPickings();
            userArticleTypesList = repository.communicator.getAllUserArticleTypes();
            movementCodeStoragesList = repository.communicator.getAllMovementCodeStorages();
            userMovementCodesList = repository.communicator.getAllUserMovementCodes();
            articlesList = repository.communicator.getAllArticles();
            devicesList = repository.communicator.getAllDevices();
            pickingItemsList = repository.communicator.getAllPickingItems();
            logsList = repository.communicator.getAllLogs();
            printTemplatesList = repository.communicator.getAllPrintTemplates();

            ApplicationLogger.logging(LogLevel.INFORMATION, "A függő adatok letöltése SQL adatbázisból befejeződött.");
        }
        catch (Exception e){
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());

            e.printStackTrace();

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.SQL_READ_FAIL, e.getMessage());
                ctpmw.get().sendResultToPresenter(message);
            }
        }
    }

    private void sendSQLTablesToROOM(){

        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok feltöltése ROOM adatbázisba elkezdődött.");

            // független táblák -----------------------------------------------------
            room.userRightsDAO().setUserRight(userRightsList);
            room.pickingsDAO().setPicking(pickingsList);
            room.userArticleTypesDAO().setUserArticleType(userArticleTypesList);
            room.movementCodesStoragesDAO().setMovementCodeStorage(movementCodeStoragesList);
            room.userMovementCodesDAO().setUserMovementCode(userMovementCodesList);
            room.articlesDAO().setArticle(articlesList);
            room.devicesDAO().setDevice(devicesList);
            room.pickingItemsDAO().setPickingItem(pickingItemsList);
            room.logsDAO().setLog(logsList);
            room.printTemplatesDAO().setPrintTemplate(printTemplatesList);
            //-----------------------------------------------------------------------

            ApplicationLogger.logging(LogLevel.INFORMATION, "A függő adatok feltöltése ROOM adatbázisba befejeződött.");

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.PROGRAMSTART_FINISH_2, "Az függő adatok feltöltése ROOM adatbázisba befejeződött.");
                ctpmw.get().sendResultToPresenter(message);
            }
        }
        catch (Exception e){
            //ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
            e.printStackTrace();

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.ROOM_SEND_FAIL, e.getMessage());
                ctpmw.get().sendResultToPresenter(message);
            }
        }
    }
}
