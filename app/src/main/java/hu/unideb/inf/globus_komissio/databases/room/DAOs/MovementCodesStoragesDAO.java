package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodeStorages;

@Dao
public interface MovementCodesStoragesDAO {

    //alap lekérdezések:

    @Query("SELECT movementCodeId, storageId, rightIn, rightOut FROM MovementCodeStorages")
    LiveData<List<MovementCodeStorages>> getAllMovementCodeStorages() throws Exception;

    @Query("UPDATE MovementCodeStorages SET storageId= :storageId, rightIn= :rightIn, rightOut= :rightOut WHERE movementCodeId = :movementCodeId")
    void updateMovementCodeStorages(long movementCodeId, String storageId, boolean rightIn, boolean rightOut) throws Exception;

    @Insert
    void setMovementCodeStorage(MovementCodeStorages movementCodeStorage) throws Exception;
}
