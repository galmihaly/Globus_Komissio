package hu.unideb.inf.globus_komissio.databases.sqldatabase;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.ArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.models.Config;
import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;
import hu.unideb.inf.globus_komissio.databases.models.Devices;
import hu.unideb.inf.globus_komissio.databases.models.LanguageCodes;
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
import hu.unideb.inf.globus_komissio.databases.models.Storages;
import hu.unideb.inf.globus_komissio.databases.models.StorageTypes;
import hu.unideb.inf.globus_komissio.databases.models.Translations;
import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.models.Version;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;

public interface Communicator {

    void getConnection();

    List<Articles> getAllArticles();
    List<ArticleTypes> getAllArticleTypes();
    List<Config> getAllConfig();
    List<Devices> getAllDevices();
    List<DeviceTypes> getAllDeviceTypes();
    List<LanguageCodes> getAllLanguageCodes();
    List<LogClasses> getAllLogClasses();
    List<Logs> getAllLogs();
    List<LogTypes> getAllLogTypes();
    List<MovementCodes> getAllMovementCodes();
    List<MovementCodeStorages> getAllMovementCodeStorages();
    List<PickingItems> getAllPickingItems();
    List<PickingItemsLast> getAllPickingItemsLast();
    List<Pickings> getAllPickings();
    List<PickingStatuses> getAllPickingStatuses();
    List<PrintTemplates> getAllPrintTemplates();
    List<PrintTemplateTypes> getAllPrintTemplateTypes();
    List<Rights> getAllRights();
    List<Storages> getAllStorages();
    List<StorageTypes> getAllStoragesTypes();
    List<Translations> getAllTranslations();
    List<UserArticleTypes> getAllUserArticleTypes();
    List<UserMovementCodes> getAllUserMovementCodes();
    List<UserRights> getAllUserRights();
    List<Users> getAllUsers();
    List<Version> getAllVersion();
    List<Workflows> getAllWorkflows();


}
