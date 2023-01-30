package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Pickings;

@Dao
public interface PickingsDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, description, pickingStatusId, dateCreate, dateMod, dateUpload, dateProcess, userId, receiverUserId, articleTypeId, movementCodeId, comments, barcode, dateBook, resultText FROM Pickings")
    LiveData<List<Pickings>> getAllPickings() throws Exception;

    @Query("UPDATE Pickings SET name= :name, description= :description, pickingStatusId= :pickingStatusId, " +
                                "dateCreate= :dateCreate, dateMod= :dateMod, dateUpload= :dateUpload, " +
                                "dateProcess= :dateProcess, userId= :userId, receiverUserId= :receiverUserId, " +
                                "articleTypeId= :articleTypeId, movementCodeId= :movementCodeId, comments= :comments, " +
                                "barcode= :barcode, dateBook= :dateBook, resultText= :resultText " +
            "                   WHERE id = :id")
    void updatePicking(long id, String name, String description,
                       long pickingStatusId, String dateCreate, String dateMod,
                       String dateUpload, String dateProcess, long userId,
                       long receiverUserId, long articleTypeId, long movementCodeId,
                       String comments, String barcode, String dateBook, String resultText) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setPicking(List<Pickings> picking) throws Exception;
}
