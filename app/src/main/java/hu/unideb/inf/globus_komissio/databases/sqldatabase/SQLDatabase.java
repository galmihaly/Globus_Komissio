package hu.unideb.inf.globus_komissio.databases.sqldatabase;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.models.Version;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;

public class SQLDatabase implements Communicator{

    private Connection connection;
    private String query;
    private ResultSet rs;
    private int size;
    private Statement stmt;

    private final String _serverName = "server.logcontrol.hu";
    private final String _portNumber = "4241";
    private final String _databaseName = "GalMihalyTest";
    private final String _userId = "Galmihaly";
    private final String _password = "Gm2022!!!";

    @Override
    public void getConnection() {
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            StringBuilder sb = new StringBuilder();

            String connectionURL = sb.append("jdbc:jtds:sqlserver://")
                    .append(_serverName)
                    .append(":").append(_portNumber)
                    .append("/").append(_databaseName)
                    .toString();

            connection = DriverManager.getConnection(connectionURL, _userId, _password);

        }
        catch (SQLException e) {
            Log.e("SQLException", "Az MSSQL adatbázishoz való csatlakozáskor hiba lépett fel!");
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            Log.e("ClassNotFoundException", "Nem található meg a JDBC Driver!");
            ex.printStackTrace();
        }
    }

    @Override
    public List<Articles> getAllArticles() {
        getConnection();

        List<Articles> articlesList = null;
        if(connection != null) {
            try {

                articlesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [QuantityUnit], [Barcode], [Active], [Price], [ArticleTypeId], [DateCreate], [DateMod], [LastTransferDate], [LastTransferAction], [TransferFlag] FROM Articles";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Articles articles = new Articles();

                    articles.setId(rs.getString(1));
                    articles.setName(rs.getString(2));
                    articles.setQuantityUnit(rs.getString(3));
                    articles.setBarcode(rs.getString(4));
                    articles.setActive(rs.getBoolean(5));
                    articles.setPrice(rs.getFloat(6));
                    articles.setArticleTypeId(rs.getLong(7));
                    articles.setDateCreate(rs.getString(8));
                    articles.setDateMod(rs.getString(9));
                    articles.setLastTransferDate(rs.getString(10));
                    articles.setLastTransferAction(rs.getString(11));
                    articles.setTransferFlag(rs.getInt(12));

                    articlesList.add(articles);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Articles táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return articlesList;
    }

    @Override
    public List<ArticleTypes> getAllArticleTypes() {
        getConnection();

        List<ArticleTypes> articleTypesList = null;
        if(connection != null) {
            try {

                articleTypesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Comments] FROM ArticleTypes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    ArticleTypes articleTypes = new ArticleTypes();

                    articleTypes.setId(rs.getLong(1));
                    articleTypes.setName(rs.getString(2));
                    articleTypes.setComments(rs.getString(3));

                    articleTypesList.add(articleTypes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az ArticleTypes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return articleTypesList;
    }

    @Override
    public List<Config> getAllConfig() {
        getConnection();

        List<Config> configList = null;
        if(connection != null) {
            try {

                configList = new ArrayList<>();

                query = "SELECT [Id], [ConfigName], [ConfigValue], [UserId], [Comments] FROM Config";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Config config = new Config();

                    config.setId(rs.getLong(1));
                    config.setConfigName(rs.getString(2));
                    config.setConfigValue(rs.getString(3));
                    config.setUserId(rs.getLong(4));
                    config.setComments(rs.getString(5));

                    configList.add(config);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Config táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return configList;
    }

    @Override
    public List<Devices> getAllDevices() {
        getConnection();

        List<Devices> devicesList = null;
        if(connection != null) {
            try {

                devicesList = new ArrayList<>();

                query = "SELECT [Id], [DeviceId], [DeviceTypeId], [DeviceName], [Comments], [Active], [UserId], [StorageId], [LoginMode], [LastUserId], [LastUserLogin], [IPAddress], [Port], [ProgramType], [Flag] FROM Devices";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Devices devices = new Devices();

                    devices.setId(rs.getLong(1));
                    devices.setDeviceId(rs.getString(2));
                    devices.setDeviceTypeId(rs.getInt(3));
                    devices.setDeviceName(rs.getString(4));
                    devices.setComments(rs.getString(5));
                    devices.setActive(rs.getBoolean(6));
                    devices.setUserId(rs.getLong(7));
                    devices.setStorageId(rs.getString(8));
                    devices.setLoginMode(rs.getInt(9));
                    devices.setLastUserId(rs.getInt(10));
                    devices.setLastUserLogin(rs.getString(11));
                    devices.setIpAddress(rs.getString(12));
                    devices.setPort(rs.getInt(13));
                    devices.setProgramType(rs.getInt(14));
                    devices.setFlag(rs.getInt(15));

                    devicesList.add(devices);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Devices táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return devicesList;
    }

    @Override
    public List<DeviceTypes> getAllDeviceTypes() {
        getConnection();

        List<DeviceTypes> deviceTypesList = null;
        if(connection != null) {
            try {

                deviceTypesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Comments] FROM DeviceTypes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    DeviceTypes deviceTypes = new DeviceTypes();

                    deviceTypes.setId(rs.getLong(1));
                    deviceTypes.setName(rs.getString(2));
                    deviceTypes.setComments(rs.getString(3));

                    deviceTypesList.add(deviceTypes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az DeviceTypes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return deviceTypesList;
    }

    @Override
    public List<LanguageCodes> getAllLanguageCodes() {
        getConnection();

        List<LanguageCodes> languageCodesList = null;
        if(connection != null) {
            try {

                languageCodesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [ISO] FROM LanguageCodes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    LanguageCodes languageCodes = new LanguageCodes();

                    languageCodes.setId(rs.getString(1));
                    languageCodes.setName(rs.getString(2));
                    languageCodes.setISO(rs.getString(3));

                    languageCodesList.add(languageCodes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az LanguageCodes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return languageCodesList;
    }

    @Override
    public List<LogClasses> getAllLogClasses() {
        getConnection();

        List<LogClasses> logClassesList = null;
        if(connection != null) {
            try {

                logClassesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Description] FROM LogClasses";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    LogClasses logClasses = new LogClasses();

                    logClasses.setId(rs.getLong(1));
                    logClasses.setName(rs.getString(2));
                    logClasses.setDescription(rs.getString(3));

                    logClassesList.add(logClasses);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az LogClasses táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return logClassesList;
    }

    @Override
    public List<Logs> getAllLogs() {
        getConnection();

        List<Logs> logsList = null;
        if(connection != null) {
            try {

                logsList = new ArrayList<>();

                query = "SELECT [Id], [LogTypeId], [LogClassId], convert(varchar, [TimeStamp], 121), [Message], [Message], [Exception], [RelatedId], [UserId] FROM Logs";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Logs logs = new Logs();

                    logs.setId(rs.getLong(1));
                    logs.setLogTypeId(rs.getLong(2));
                    logs.setLogClassId(rs.getLong(3));
                    logs.setTimestamp(rs.getString(5));
                    logs.setMessage(rs.getString(6));
                    logs.setException(rs.getString(7));
                    logs.setRelatedId(rs.getString(8));
                    logs.setUserId(rs.getLong(9));

                    logsList.add(logs);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Logs táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return logsList;
    }

    @Override
    public List<LogTypes> getAllLogTypes() {
        getConnection();

        List<LogTypes> logTypesList = null;
        if(connection != null) {
            try {

                logTypesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Description] FROM LogTypes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    LogTypes logTypes = new LogTypes();

                    logTypes.setId(rs.getLong(1));
                    logTypes.setName(rs.getString(2));
                    logTypes.setDescription(rs.getString(3));

                    logTypesList.add(logTypes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az LogTypes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return logTypesList;
    }

    @Override
    public List<MovementCodes> getAllMovementCodes() {
        getConnection();

        List<MovementCodes> movementCodesList = null;
        if(connection != null) {
            try {

                movementCodesList = new ArrayList<>();

                query = "SELECT [Id], [Direction], [Name], [Comments], [Active], [DateCreate], [DateMod], [LastTransferDate], [LastTransferAction], [TransferFlag] FROM MovementCodes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    MovementCodes movementCodes = new MovementCodes();

                    movementCodes.setId(rs.getLong(1));
                    movementCodes.setDirection(rs.getInt(2));
                    movementCodes.setName(rs.getString(3));
                    movementCodes.setComments(rs.getString(4));
                    movementCodes.setActive(rs.getBoolean(5));
                    movementCodes.setDateCreate(rs.getString(6));
                    movementCodes.setDateMod(rs.getString(7));
                    movementCodes.setLastTransferDate(rs.getString(8));
                    movementCodes.setLastTransferAction(rs.getString(9));
                    movementCodes.setTransferFlag(rs.getInt(10));

                    movementCodesList.add(movementCodes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az MovementCodes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return movementCodesList;
    }

    @Override
    public List<MovementCodeStorages> getAllMovementCodeStorages() {
        getConnection();

        List<MovementCodeStorages> movementCodeStoragesList = null;
        if(connection != null) {
            try {

                movementCodeStoragesList = new ArrayList<>();

                query = "SELECT [MovementCodeId], [StorageId], [RightIn], [RightOut] FROM MovementCodeStorages";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    MovementCodeStorages movementCodeStorages = new MovementCodeStorages();

                    movementCodeStorages.setMovementCodeId(rs.getLong(1));
                    movementCodeStorages.setStorageId(rs.getString(2));
                    movementCodeStorages.setRightIn(rs.getBoolean(3));
                    movementCodeStorages.setRightOut(rs.getBoolean(4));

                    movementCodeStoragesList.add(movementCodeStorages);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az MovementCodeStorages táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return movementCodeStoragesList;
    }

    @Override
    public List<PickingItems> getAllPickingItems() {
        getConnection();

        List<PickingItems> pickingItemsList = null;
        if(connection != null) {
            try {

                pickingItemsList = new ArrayList<>();

                query = "SELECT [Id], [PickingId], [Position], [Name], [ArticleId], [UserId], [ReceiverUserId], [SourceStorageId], [PickingStorageId], [WorkflowId], [Quantity], [QuantityUnit], [PickingStatusId], [DeviceId], [DateCreate], [DateMod], [DateUpload], [DateProcess], [Comments], [Barcode] FROM PickingItems";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    PickingItems pickingItems = new PickingItems();

                    pickingItems.setId(rs.getLong(1));
                    pickingItems.setPickingId(rs.getInt(2));
                    pickingItems.setPosition(rs.getInt(3));
                    pickingItems.setName(rs.getString(4));
                    pickingItems.setArticleId(rs.getString(5));
                    pickingItems.setUserId(rs.getLong(6));
                    pickingItems.setReceiverUserId(rs.getLong(7));
                    pickingItems.setSourceStorageId(rs.getString(8));
                    pickingItems.setPickingStorageId(rs.getString(9));
                    pickingItems.setWorkFlowId(rs.getString(10));
                    pickingItems.setQuantity(rs.getFloat(11));
                    pickingItems.setQuantityUnit(rs.getString(12));
                    pickingItems.setPickingStatusId(rs.getLong(13));
                    pickingItems.setDeviceId(rs.getLong(14));
                    pickingItems.setDateCreate(rs.getString(15));
                    pickingItems.setDateMod(rs.getString(16));
                    pickingItems.setDateUpload(rs.getString(17));
                    pickingItems.setDateProcess(rs.getString(18));
                    pickingItems.setComments(rs.getString(19));
                    pickingItems.setBarcode(rs.getString(20));

                    pickingItemsList.add(pickingItems);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az PickingItems táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return pickingItemsList;
    }

    @Override
    public List<PickingItemsLast> getAllPickingItemsLast() {
        getConnection();

        List<PickingItemsLast> pickingItemsLastList = null;
        if(connection != null) {
            try {

                pickingItemsLastList = new ArrayList<>();

                query = "SELECT [Id], [PICKING_ID], [DATE_CREATE], [ARTNR], [QUANTITY], [QUANTITY_UNIT], [WORKFLOW_ID], [WORKFLOW_INFOSYS_ID], [USER_ID], [RECEIVER_USER_ID], [STORAGE_ID_FROM], [STORAGE_ID_TO], [COMMENTS] FROM PickingItemsLast";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    PickingItemsLast pickingItemsLast = new PickingItemsLast();

                    pickingItemsLast.setId(rs.getLong(1));
                    pickingItemsLast.setPickingId(rs.getLong(2));
                    pickingItemsLast.setDateCreate(rs.getString(3));
                    pickingItemsLast.setArtnr(rs.getString(4));
                    pickingItemsLast.setQuantity(rs.getFloat(5));
                    pickingItemsLast.setQuantityUnit(rs.getString(6));
                    pickingItemsLast.setWorkflowId(rs.getString(7));
                    pickingItemsLast.setWorkflowInfoSysId(rs.getLong(8));
                    pickingItemsLast.setUserId(rs.getLong(9));
                    pickingItemsLast.setReceiverUserId(rs.getInt(10));
                    pickingItemsLast.setStorageIdFrom(rs.getString(11));
                    pickingItemsLast.setStorageIdTo(rs.getString(12));
                    pickingItemsLast.setComments(rs.getString(13));

                    pickingItemsLastList.add(pickingItemsLast);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az PickingItemsLast táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return pickingItemsLastList;
    }

    @Override
    public List<Pickings> getAllPickings() {
        getConnection();

        List<Pickings> pickingsList = null;
        if(connection != null) {
            try {

                pickingsList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Description], [PickingStatusId], [DateCreate], [DateMod], [DateUpload], [DateProcess], [UserId], [ReceiverUserId], [ArticleTypeId], [MovementCodeId], [Comments], [Barcode], [DateBook], [ResultText] FROM Pickings";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Pickings pickings = new Pickings();

                    pickings.setId(rs.getLong(1));
                    pickings.setName(rs.getString(2));
                    pickings.setDescription(rs.getString(3));
                    pickings.setPickingStatusId(rs.getLong(4));
                    pickings.setDateCreate(rs.getString(5));
                    pickings.setDateMod(rs.getString(6));
                    pickings.setDateUpload(rs.getString(7));
                    pickings.setDateProcess(rs.getString(8));
                    pickings.setUserId(rs.getLong(9));
                    pickings.setReceiverUserId(rs.getLong(10));
                    pickings.setArticleTypeId(rs.getLong(11));
                    pickings.setMovementCodeId(rs.getLong(12));
                    pickings.setComments(rs.getString(13));
                    pickings.setBarcode(rs.getString(14));
                    pickings.setDateBook(rs.getString(15));
                    pickings.setResultText(rs.getString(16));

                    pickingsList.add(pickings);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Pickings táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return pickingsList;
    }

    @Override
    public List<PickingStatuses> getAllPickingStatuses() {
        getConnection();

        List<PickingStatuses> pickingStatusesList = null;
        if(connection != null) {
            try {

                pickingStatusesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Comments], [BackgroundColor], [ForegroundColor] FROM PickingStatuses";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    PickingStatuses pickingStatuses = new PickingStatuses();

                    pickingStatuses.setId(rs.getLong(1));
                    pickingStatuses.setName(rs.getString(2));
                    pickingStatuses.setComments(rs.getString(3));
                    pickingStatuses.setBackgroundColor(rs.getInt(4));
                    pickingStatuses.setForegroundColor(rs.getInt(5));

                    pickingStatusesList.add(pickingStatuses);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az PickingStatuses táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return pickingStatusesList;
    }

    @Override
    public List<PrintTemplates> getAllPrintTemplates() {
        getConnection();

        List<PrintTemplates> printTemplatesList = null;
        if(connection != null) {
            try {

                printTemplatesList = new ArrayList<>();

                query = "SELECT [Id], [TemplateTypeId], [Name], [TemplateData], [Comments] FROM PrintTemplates";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    PrintTemplates printTemplates = new PrintTemplates();

                    printTemplates.setId(rs.getLong(1));
                    printTemplates.setTemplateTypeId(rs.getLong(2));
                    printTemplates.setName(rs.getString(3));
                    printTemplates.setTemplateData(rs.getString(4));
                    printTemplates.setComments(rs.getString(5));

                    printTemplatesList.add(printTemplates);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az PrintTemplates táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return printTemplatesList;
    }

    @Override
    public List<PrintTemplateTypes> getAllPrintTemplateTypes() {
        getConnection();

        List<PrintTemplateTypes> printTemplateTypesList = null;
        if(connection != null) {
            try {

                printTemplateTypesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Comments] FROM PrintTemplateTypes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    PrintTemplateTypes printTemplateTypes = new PrintTemplateTypes();

                    printTemplateTypes.setId(rs.getLong(1));
                    printTemplateTypes.setName(rs.getString(2));
                    printTemplateTypes.setComments(rs.getString(3));

                    printTemplateTypesList.add(printTemplateTypes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az PrintTemplateTypes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return printTemplateTypesList;
    }

    @Override
    public List<Rights> getAllRights() {
        getConnection();

        List<Rights> rightsList = null;
        if(connection != null) {
            try {

                rightsList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Description] FROM Rights";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Rights rights = new Rights();

                    rights.setId(rs.getLong(1));
                    rights.setName(rs.getString(2));
                    rights.setDescription(rs.getString(3));

                    rightsList.add(rights);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Rights táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return rightsList;
    }

    @Override
    public List<Storages> getAllStorages() {
        getConnection();

        List<Storages> storagesList = null;
        if(connection != null) {
            try {

                storagesList = new ArrayList<>();

                query = "SELECT [Id], [Code], [Barcode], [Name], [Comments], [Active], [DateCreate], [DateMod], [LastTransferDate], [LastTransferAction], [TransferFlag], [PickingSource], [PickingTarget], [ReceiverUserId], [Permanent], [StorageTypeId], [ParentId] FROM Storages";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Storages storages = new Storages();

                    storages.setId(rs.getString(1));
                    storages.setCode(rs.getString(2));
                    storages.setBarcode(rs.getString(3));
                    storages.setName(rs.getString(4));
                    storages.setComments(rs.getString(5));
                    storages.setActive(rs.getBoolean(6));
                    storages.setDateCreate(rs.getString(7));
                    storages.setDateMod(rs.getString(8));
                    storages.setLastTransferDate(rs.getString(9));
                    storages.setLastTransferAction(rs.getString(10));
                    storages.setTransferFlag(rs.getInt(11));
                    storages.setPickingSource(rs.getBoolean(12));
                    storages.setPickingTarget(rs.getBoolean(13));
                    storages.setReceiverUserId(rs.getLong(14));
                    storages.setPermanent(rs.getBoolean(15));
                    storages.setStorageTypeId(rs.getLong(16));
                    storages.setParentId(rs.getString(17));

                    storagesList.add(storages);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Storages táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return storagesList;
    }

    @Override
    public List<StorageTypes> getAllStoragesTypes() {
        getConnection();

        List<StorageTypes> storageTypesList = null;
        if(connection != null) {
            try {

                storageTypesList = new ArrayList<>();

                query = "SELECT [Id], [Name], [Comments] FROM StorageTypes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    StorageTypes storageTypes = new StorageTypes();

                    storageTypes.setId(rs.getLong(1));
                    storageTypes.setName(rs.getString(2));
                    storageTypes.setComments(rs.getString(3));

                    storageTypesList.add(storageTypes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az StorageTypes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return storageTypesList;
    }

    @Override
    public List<UserArticleTypes> getAllUserArticleTypes() {
        getConnection();

        List<UserArticleTypes> userArticleTypesList = null;
        if(connection != null) {
            try {

                userArticleTypesList = new ArrayList<>();

                query = "SELECT [UserId], [ArticleTypeId], [Grantor], [Verify], [RightIn], [RightOut], [UserDefault] FROM UserArticleTypes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    UserArticleTypes userArticleTypes = new UserArticleTypes();

                    userArticleTypes.setUserId(rs.getLong(1));
                    userArticleTypes.setArticleTypeId(rs.getLong(2));
                    userArticleTypes.setGrantor(rs.getInt(3));
                    userArticleTypes.setVerify(rs.getString(4));
                    userArticleTypes.setRightIn(rs.getBoolean(5));
                    userArticleTypes.setRightOut(rs.getBoolean(6));
                    userArticleTypes.setUserDefault(rs.getBoolean(7));

                    userArticleTypesList.add(userArticleTypes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az UserArticleTypes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return userArticleTypesList;
    }

    @Override
    public List<UserMovementCodes> getAllUserMovementCodes() {
        getConnection();

        List<UserMovementCodes> userMovementCodesList = null;
        if(connection != null) {
            try {

                userMovementCodesList = new ArrayList<>();

                query = "SELECT [UserId], [MovementCodeId], [Grantor], [Verify], [RightIn], [RightOut], [UserDefault] FROM UserMovementCodes";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    UserMovementCodes userMovementCodes = new UserMovementCodes();

                    userMovementCodes.setUserId(rs.getLong(1));
                    userMovementCodes.setMovementCodeId(rs.getLong(2));
                    userMovementCodes.setGrantor(rs.getInt(3));
                    userMovementCodes.setRightIn(rs.getBoolean(4));
                    userMovementCodes.setRightOut(rs.getBoolean(5));
                    userMovementCodes.setUserDefault(rs.getBoolean(6));

                    userMovementCodesList.add(userMovementCodes);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az UserMovementCodes táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return userMovementCodesList;
    }

    @Override
    public List<UserRights> getAllUserRights() {
        getConnection();

        List<UserRights> userRightsList = null;
        if(connection != null) {
            try {

                userRightsList = new ArrayList<>();

                query = "SELECT [UserId], [RightId], [Grantor], [Verify] FROM UserRights";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    UserRights userRights = new UserRights();

                    userRights.setUserId(rs.getLong(1));
                    userRights.setRightId(rs.getLong(2));
                    userRights.setGrantor(rs.getLong(3));
                    userRights.setVerify(rs.getString(4));

                    userRightsList.add(userRights);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az UserRights táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return userRightsList;
    }

    @Override
    public List<Users> getAllUsers() {
        getConnection();

        List<Users> usersList = null;
        if(connection != null) {
            try {

                usersList = new ArrayList<>();

                query = "SELECT [Id], [Account], [Name], [Password], [Active], [LastLogin], [Email], [Telephone], [RFID], [Pin], [Barcode], [InternalId], [UserId], [DateCreate], [DateMod], [LastTransferDate], [LastTransferAction], [TransferFlag], [MobileFlexPassword], [MobileFlexPin], [LocalUser] FROM Users";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Users users = new Users();

                    users.setId(rs.getLong(1));
                    users.setAccount(rs.getString(2));
                    users.setName(rs.getString(3));
                    users.setPassword(rs.getString(4));
                    users.setActive(rs.getBoolean(5));
                    users.setLastLogin(rs.getString(6));
                    users.setEmail(rs.getString(7));
                    users.setTelephone(rs.getString(8));
                    users.setRfid(rs.getString(9));
                    users.setPin(rs.getString(10));
                    users.setBarcode(rs.getString(11));
                    users.setInternalId(rs.getString(12));
                    users.setUserId(rs.getLong(13));
                    users.setDateCreate(rs.getString(14));
                    users.setDateMod(rs.getString(15));
                    users.setLastTransferDate(rs.getString(16));
                    users.setLastTransferAction(rs.getString(17));
                    users.setTransferFlag(rs.getInt(18));
                    users.setMobileFlexPassword(rs.getString(19));
                    users.setMobileFlexPin(rs.getString(20));
                    users.setLocalUser(rs.getBoolean(21));

                    usersList.add(users);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Users táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return usersList;
    }

    @Override
    public List<Version> getAllVersion() {
        getConnection();

        List<Version> versionList = null;
        if(connection != null) {
            try {

                versionList = new ArrayList<>();

                query = "SELECT [Id], convert(varchar, [DateCreate], 121), [Comments] FROM Version";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Version version = new Version();

                    version.setId(rs.getLong(1));
                    version.setDateCreate(rs.getString(2));
                    version.setComments(rs.getString(3));

                    versionList.add(version);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Version táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return versionList;
    }

    @Override
    public List<Workflows> getAllWorkflows() {
        getConnection();

        List<Workflows> workflowsList = null;
        if(connection != null) {
            try {

                workflowsList = new ArrayList<>();

                query = "SELECT [Id], [Name], [InfoSysId], [Active], [DateCreate], [DateMod], [LastTransferDate], [LastTransferAction], [TransferFlag] FROM Workflows";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    Workflows workflows = new Workflows();

                    Log.d("", String.valueOf(rs.getDate(5)));

                    workflows.setId(rs.getString(1));
                    workflows.setName(rs.getString(2));
                    workflows.setInfoSysId(rs.getLong(3));
                    workflows.setActive(rs.getBoolean(4));
                    workflows.setDateCreate(rs.getString(5));
                    workflows.setDateMod(rs.getString(6));
                    workflows.setLastTransferDate(rs.getString(7));
                    workflows.setLastTransferAction(rs.getString(8));
                    workflows.setTransferFlag(rs.getInt(9));

                    workflowsList.add(workflows);
                    size++;
                }

                if(size == 0) {
                    Log.i("s", "Az Workflows táblában a lekérdezhető adatok száma 0!");
                    return null;
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("SQLException", "Az MSSql adatbázis kacsolatának felbontása sikertelen!");
                }
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!");
            return null;
        }

        return workflowsList;
    }
}
