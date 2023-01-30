package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodes;

@Dao
public interface MovementCodesDAO {

    //alap lekérdezések:

    @Query("SELECT id, direction, name, comments, active, dateCreate, dateMod, lastTransferDate, lastTransferAction, transferFlag FROM MovementCodes")
    LiveData<List<MovementCodes>> getAllMovementCodes() throws Exception;

    @Query("UPDATE MovementCodes SET name= :name, direction= :direction, comments= :comments, active= :active, dateCreate= :dateCreate, dateMod= :dateMod, lastTransferDate= :lastTransferDate, lastTransferAction= :lastTransferAction, transferFlag= :transferFlag WHERE id = :id")
    void updateMovementCodes(long id, int direction, String name, String comments,  boolean active, String dateCreate, String dateMod, String lastTransferDate, String lastTransferAction, int transferFlag) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setMovementCode(List<MovementCodes> movementCode) throws Exception;

}
