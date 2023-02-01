package hu.unideb.inf.globus_komissio.tasks;

import android.content.Context;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.enums.LogLevel;
import hu.unideb.inf.globus_komissio.databases.models.ArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.Config;
import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;
import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.LogTypes;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.PickingItemsLast;
import hu.unideb.inf.globus_komissio.databases.models.PickingStatuses;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplateTypes;
import hu.unideb.inf.globus_komissio.databases.models.Rights;
import hu.unideb.inf.globus_komissio.databases.models.StorageTypes;
import hu.unideb.inf.globus_komissio.databases.models.Storages;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.models.Version;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.enums.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.activities.utils.Util;

public class ProcessBaseDatas implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;
    private Room room;
    private Context context;


    private List<Version> versionsList;
    private List<Config> configList;
    private List<StorageTypes> storageTypesList;
    private List<PickingItemsLast> pickingItemsLastList;
    private List<Workflows> workflowsList;
    private List<Users> usersList;
    private List<Rights> rightsList;
    private List<Storages> storagesList;
    private List<PickingStatuses> pickingStatusesList;
    private List<DeviceTypes> deviceTypesList;
    private List<PrintTemplateTypes> printTemplateTypesList;
    private List<LogTypes> logTypesList;
    private List<LogClasses> logClassesList;
    private List<MovementCodes> movementCodesList;
    private List<ArticleTypes> articleTypesList;

    public ProcessBaseDatas(Context context) {
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

                    //üzenet a MainActivityPresenter - nek
                    Message message = Util.createMessage(Util.ROOM_CREATE_FAIL, "A ROOM adatbázis nem jött létre a program indulásakor.");
                    if(ctpmw != null && ctpmw.get() != null) {
                        ctpmw.get().sendResultToPresenter(message);
                    }
                }
            }
            else{
                ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");

                if(ctpmw != null && ctpmw.get() != null) {
                    Message message = Util.createMessage(Util.PROGRAMSTART_FINISH_1, "Az alapadatok feltöltése folyamat befejeződött.");
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

            // független táblák --------------------------------------------------------
            configList = repository.communicator.getAllConfig();
            versionsList = repository.communicator.getAllVersion();
            storageTypesList = repository.communicator.getAllStoragesTypes();
            pickingItemsLastList = repository.communicator.getAllPickingItemsLast();
            workflowsList = repository.communicator.getAllWorkflows();
            usersList = repository.communicator.getAllUsers();
            rightsList = repository.communicator.getAllRights();
            storagesList = repository.communicator.getAllStorages();
            pickingStatusesList = repository.communicator.getAllPickingStatuses();
            deviceTypesList = repository.communicator.getAllDeviceTypes();
            printTemplateTypesList = repository.communicator.getAllPrintTemplateTypes();
            logTypesList = repository.communicator.getAllLogTypes();
            logClassesList = repository.communicator.getAllLogClasses();
            movementCodesList = repository.communicator.getAllMovementCodes();
            articleTypesList = repository.communicator.getAllArticleTypes();
            //-----------------------------------------------------------------------------

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok letöltése SQL adatbázisból befejeződött.");
        }
        catch (Exception e){
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.SQL_READ_FAIL, e.getMessage());
                ctpmw.get().sendResultToPresenter(message);
            }
        }
    }

    private void sendSQLTablesToROOM(){

        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok feltöltése ROOM adatbázisba elkezdődött.");

            // független táblák --------------------------------------------------
            room.configDAO().setConfig(configList);
            room.versionDAO().setVersion(versionsList);
            room.storageTypesDAO().setStorageType(storageTypesList);
            room.pickingItemLastDAO().setPickingItemsLast(pickingItemsLastList);
            room.workflowsDAO().setWorkflow(workflowsList);
            room.usersDAO().setUser(usersList);
            room.rightsDAO().setRight(rightsList);
            room.storagesDAO().setStorage(storagesList);
            room.pickingStatusesDAO().setPickingStatuse(pickingStatusesList);
            room.deviceTypesDAO().setDeviceType(deviceTypesList);
            room.printTemplateTypesDAO().setPrintTemplateType(printTemplateTypesList);
            room.logTypesDAO().setLogType(logTypesList);
            room.logClassesDAO().setLogClass(logClassesList);
            room.movementCodesDAO().setMovementCode(movementCodesList);
            room.articleTypesDAO().setArticleTypes(articleTypesList);
            //----------------------------------------------------------------------

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.PROGRAMSTART_FINISH_1, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");
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
