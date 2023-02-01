package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;

@Dao
public interface UserMovementCodesDAO {

    //alap lekérdezések:

    @Query("SELECT userId, movementCodeId, grantor, verify, rightIn, rightOut, userDefault FROM UserMovementCodes")
    LiveData<List<UserMovementCodes>> getAllUserMovementCodes() throws Exception;

    @Query("UPDATE UserMovementCodes SET movementCodeId= :movementCodeId, grantor= :grantor, verify= :verify, rightIn= :rightIn, rightOut= :rightOut, userDefault= :userDefault WHERE userId = :userId")
    void updateUserMovementCodes(long userId, long movementCodeId, int grantor, String verify, boolean rightIn, boolean rightOut, boolean userDefault) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setUserMovementCode(List<UserMovementCodes> userMovementCode) throws Exception;
}
