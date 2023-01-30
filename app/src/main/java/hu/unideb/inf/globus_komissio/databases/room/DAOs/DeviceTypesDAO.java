package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;

@Dao
public interface DeviceTypesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, comments FROM DeviceTypes")
    LiveData<List<DeviceTypes>> getAllDeviceTypes() throws Exception;

    @Query("UPDATE DeviceTypes SET name= :name, comments= :comments WHERE id = :id")
    void updateDeviceTypes(long id, String name, String comments) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setDeviceType(List<DeviceTypes> deviceType) throws Exception;
}
