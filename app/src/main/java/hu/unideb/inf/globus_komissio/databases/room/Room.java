package hu.unideb.inf.globus_komissio.databases.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hu.unideb.inf.globus_komissio.databases.models.ArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.models.Config;
import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;
import hu.unideb.inf.globus_komissio.databases.models.Devices;
import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.LogTypes;
import hu.unideb.inf.globus_komissio.databases.models.Logs;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodeStorages;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.PickingItems;
import hu.unideb.inf.globus_komissio.databases.models.PickingItemsLast;
import hu.unideb.inf.globus_komissio.databases.models.PickingStatuses;
import hu.unideb.inf.globus_komissio.databases.models.Pickings;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplateTypes;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;
import hu.unideb.inf.globus_komissio.databases.models.Rights;
import hu.unideb.inf.globus_komissio.databases.models.StorageTypes;
import hu.unideb.inf.globus_komissio.databases.models.Storages;
import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.models.Version;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.ArticleTypesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.ArticlesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.ConfigDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.DeviceTypesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.DevicesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.LogClassesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.LogTypesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.LogsDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.MovementCodesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.MovementCodesStoragesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.PickingItemLastDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.PickingItemsDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.PickingStatusesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.PickingsDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.PrintTemplateTypesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.PrintTemplatesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.RightsDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.StorageTypesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.StoragesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.UserArticleTypesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.UserMovementCodesDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.UserRightsDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.UsersDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.VersionDAO;
import hu.unideb.inf.globus_komissio.databases.room.DAOs.WorkflowsDAO;


@Database(entities = {Articles.class, ArticleTypes.class, Config.class, Devices.class, DeviceTypes.class,
        LogClasses.class, Logs.class, LogTypes.class, MovementCodes.class, MovementCodeStorages.class, PickingItems.class,
        PickingItemsLast.class, Pickings.class, PickingStatuses.class, PrintTemplates.class, PrintTemplateTypes.class, Rights.class,
        Storages.class, StorageTypes.class, UserArticleTypes.class, UserMovementCodes.class, UserRights.class, Users.class,
        Version.class, Workflows.class},
        version = 1, exportSchema = false)
public abstract class Room extends RoomDatabase {

    private static Room INSTANCE;

    public static Room getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(), Room.class, "GalMihalyTest")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static Room getInstance() {
        if(INSTANCE == null) return null;

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract ArticlesDAO articlesDAO();
    public abstract ArticleTypesDAO articleTypesDAO();
    public abstract ConfigDAO configDAO();
    public abstract DevicesDAO devicesDAO();
    public abstract DeviceTypesDAO deviceTypesDAO();
    public abstract LogClassesDAO logClassesDAO();
    public abstract LogsDAO logsDAO();
    public abstract LogTypesDAO logTypesDAO();
    public abstract MovementCodesDAO movementCodesDAO();
    public abstract MovementCodesStoragesDAO movementCodesStoragesDAO();
    public abstract PickingItemLastDAO pickingItemLastDAO();
    public abstract PickingItemsDAO pickingItemsDAO();
    public abstract PickingsDAO pickingsDAO();
    public abstract PickingStatusesDAO pickingStatusesDAO();
    public abstract PrintTemplatesDAO printTemplatesDAO();
    public abstract PrintTemplateTypesDAO printTemplateTypesDAO();
    public abstract RightsDAO rightsDAO();
    public abstract StoragesDAO storagesDAO();
    public abstract StorageTypesDAO storageTypesDAO();
    public abstract UserArticleTypesDAO userArticleTypesDAO();
    public abstract UserMovementCodesDAO userMovementCodesDAO();
    public abstract UserRightsDAO userRightsDAO();
    public abstract UsersDAO usersDAO();
    public abstract VersionDAO versionDAO();
    public abstract WorkflowsDAO workflowsDAO();
}
