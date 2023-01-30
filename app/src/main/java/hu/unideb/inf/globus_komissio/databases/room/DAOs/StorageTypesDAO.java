package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.StorageTypes;

@Dao
public interface StorageTypesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, comments FROM StorageTypes")
    LiveData<List<StorageTypes>> getAllStoragesTypes() throws Exception;

    @Query("UPDATE StorageTypes SET name= :name, comments= :comments WHERE id = :id")
    void updateStorageTypes(long id, String name, String comments) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setStorageType(List<StorageTypes> storageType) throws Exception;
}
