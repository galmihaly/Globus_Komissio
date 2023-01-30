package hu.unideb.inf.globus_komissio.tasks;

import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.LoggerElements.ApplicationLogger;
import hu.unideb.inf.globus_komissio.LoggerElements.LogLevel;
import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.models.Devices;
import hu.unideb.inf.globus_komissio.databases.models.Logs;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodeStorages;
import hu.unideb.inf.globus_komissio.databases.models.PickingItems;
import hu.unideb.inf.globus_komissio.databases.models.PickingItemsLast;
import hu.unideb.inf.globus_komissio.databases.models.Pickings;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;
import hu.unideb.inf.globus_komissio.databases.models.Rights;
import hu.unideb.inf.globus_komissio.databases.models.Storages;
import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class ProcessMasterDatas implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;
    private Room room;

    private List<UserRights> userRightsList;
    private List<Devices> devicesList;
    private List<Pickings> pickingsList;
    private List<UserArticleTypes> userArticleTypesList;
    private List<PickingItems> pickingItemsList;
    private List<MovementCodeStorages> movementCodeStoragesList;
    private List<UserMovementCodes> userMovementCodesList;
    private List<Articles> articlesList;
    private List<PrintTemplates> printTemplatesList;
    private List<Logs> logsList;

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.ctpmw = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {
            if (Thread.interrupted()) throw new InterruptedException();

            getSQLTablesToLists();

            room = Room.getInstance();
            if(room != null){
                sendSQLTablesToROOM();
            }
            else {
                ApplicationLogger.logging(LogLevel.FATAL, "A ROOM adatbázis nem jött létre a program indulásakor.");

                //üzenet a MainActivityPresenter - nek
                Message message = Util.createMessage(Util.ROOM_CREATE_FAIL, "A ROOM adatbázis nem jött létre a program indulásakor.");
                if(ctpmw != null && ctpmw.get() != null) {
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

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok letöltése SQL adatbázisból megkezdődött.");

            userRightsList = repository.Communicator.getAllUserRights();
            pickingsList = repository.Communicator.getAllPickings();
            userArticleTypesList = repository.Communicator.getAllUserArticleTypes();
            movementCodeStoragesList = repository.Communicator.getAllMovementCodeStorages();
            userMovementCodesList = repository.Communicator.getAllUserMovementCodes();
            articlesList = repository.Communicator.getAllArticles();
            devicesList = repository.Communicator.getAllDevices();
            pickingItemsList = repository.Communicator.getAllPickingItems();

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok letöltése SQL adatbázisból befejeződött.");
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
            //-----------------------------------------------------------------------

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.PROCESS_FINISH_2, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");
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
