package hu.unideb.inf.globus_komissio.tasks;

import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.LoggerElements.ApplicationLogger;
import hu.unideb.inf.globus_komissio.LoggerElements.LogLevel;
import hu.unideb.inf.globus_komissio.databases.models.ArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.Config;
import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;
import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.LogTypes;
import hu.unideb.inf.globus_komissio.databases.models.Logs;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.PickingStatuses;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplateTypes;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;
import hu.unideb.inf.globus_komissio.databases.models.StorageTypes;
import hu.unideb.inf.globus_komissio.databases.models.Version;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class ProcessBaseDatas implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;
    private Room room;

    private List<Version> versionsList;
    private List<Config> configList;
    private List<PrintTemplateTypes> printTemplateTypesList;
    private List<PrintTemplates> printTemplatesList;
    private List<Logs> logsList;
    private List<LogTypes> logTypesList;
    private List<LogClasses> logClassesList;
    private List<ArticleTypes> articleTypesList;
    private List<PickingStatuses> pickingStatusesList;
    private List<DeviceTypes> deviceTypesList;
    private List<MovementCodes> movementCodesList;
    private List<StorageTypes> storageTypesList;

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

            configList = repository.Communicator.getAllConfig();
            printTemplateTypesList = repository.Communicator.getAllPrintTemplateTypes();
            printTemplatesList = repository.Communicator.getAllPrintTemplates();
            logsList = repository.Communicator.getAllLogs();
            logTypesList = repository.Communicator.getAllLogTypes();
            logClassesList = repository.Communicator.getAllLogClasses();
            articleTypesList = repository.Communicator.getAllArticleTypes();
            pickingStatusesList = repository.Communicator.getAllPickingStatuses();
            deviceTypesList = repository.Communicator.getAllDeviceTypes();
            movementCodesList = repository.Communicator.getAllMovementCodes();
            versionsList = repository.Communicator.getAllVersion();
            storageTypesList = repository.Communicator.getAllStoragesTypes();

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

            room.printTemplateTypesDAO().setPrintTemplateType(printTemplateTypesList);
            room.printTemplatesDAO().setPrintTemplate(printTemplatesList);
            room.logTypesDAO().setLogType(logTypesList);
            room.logClassesDAO().setLogClass(logClassesList);
            room.logsDAO().setLog(logsList);
            room.articleTypesDAO().setArticleTypes(articleTypesList);
            room.pickingStatusesDAO().setPickingStatuse(pickingStatusesList);
            room.deviceTypesDAO().setDeviceType(deviceTypesList);
            room.movementCodesDAO().setMovementCode(movementCodesList);
            room.configDAO().setConfig(configList);
            room.versionDAO().setVersion(versionsList);
            room.storageTypesDAO().setStorageType(storageTypesList);

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");

            if(ctpmw != null && ctpmw.get() != null) {
                Message message = Util.createMessage(Util.PROCESS_FINISH_1, "Az alapadatok feltöltése ROOM adatbázisba befejeződött.");
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
