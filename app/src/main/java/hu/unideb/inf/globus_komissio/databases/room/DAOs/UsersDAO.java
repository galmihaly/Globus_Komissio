package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import hu.unideb.inf.globus_komissio.databases.models.Users;

@Dao
public interface UsersDAO {

    //alap lekérdezések:

    @Query("SELECT id, account, name, password, active, lastLogin, email, " +
            "telephone, rfid, pin, barcode, internalId, userId, dateCreate, dateMod, " +
            "lastTransferDate, lastTransferAction, transferFlag, mobileFlexPassword, mobileFlexPin, localUser FROM Users")
    LiveData<List<Users>> getAllUsers() throws Exception;

    @Query("UPDATE Users SET account= :account, name= :name, password= :password, active= :active, lastLogin= :lastLogin," +
                            " email= :email, telephone= :telephone, rfid= :rfid, pin= :pin," +
                            " barcode= :barcode, internalId= :internalId, userId= :userId," +
                            " dateCreate= :dateCreate, dateMod= :dateMod, lastTransferDate= :lastTransferDate," +
                            " lastTransferAction= :lastTransferAction, transferFlag= :transferFlag," +
                            " mobileFlexPassword= :mobileFlexPassword, mobileFlexPin= :mobileFlexPin, localUser= :localUser WHERE id = :id")
    void updateUsers(long id, String account, String name, String password,
                     boolean active, String lastLogin, String email, String telephone,
                     String rfid, String pin, String barcode, String internalId,
                     long userId, String dateCreate, String dateMod, String lastTransferDate,
                     String lastTransferAction, int transferFlag, String mobileFlexPassword,
                     String mobileFlexPin, boolean localUser) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setUser(List<Users> user) throws Exception;

    // speciális lekérdezések

    @Query("SELECT id, account, name, password, active, lastLogin, email, " +
            "telephone, rfid, pin, barcode, internalId, userId, dateCreate, dateMod, " +
            "lastTransferDate, lastTransferAction, transferFlag, mobileFlexPassword, mobileFlexPin, localUser FROM Users " +
            "WHERE barcode= :barcode")
    Users getLoggedUser(String barcode) throws Exception;
}
