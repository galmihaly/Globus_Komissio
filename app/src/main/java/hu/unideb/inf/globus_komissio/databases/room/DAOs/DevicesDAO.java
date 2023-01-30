package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Devices;

@Dao
public interface DevicesDAO {

    //alap lekérdezések:

    @Query("SELECT id, deviceId, deviceTypeId, deviceName, comments, active, userId, storageId, loginMode, lastUserId, lastUserLogin, ipAddress, port, programType, flag FROM Devices")
    LiveData<List<Devices>> getAllDevices() throws Exception;

    @Query("UPDATE Devices SET deviceId= :deviceId, deviceTypeId= :deviceTypeId ,deviceName= :deviceName, " +
                                        "comments= :comments, active= :active, userId= :userId, storageId= " +
                                        ":storageId, loginMode= :loginMode, lastUserId= :lastUserId, " +
                                        "lastUserLogin= :lastUserLogin, ipAddress= :ipAddress, port= :port, " +
                                        "programType= :programType, flag= :flag WHERE id = :id")
    void updateDevices(long id, String deviceId, long deviceTypeId, String deviceName, String comments, boolean active, long userId, String storageId, int loginMode, long lastUserId, String lastUserLogin, String ipAddress, int port, int programType, int flag) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setDevice(List<Devices> device) throws Exception;
}
