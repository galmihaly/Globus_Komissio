package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import hu.unideb.inf.globus_komissio.databases.models.Storages;

@Dao
public interface StoragesDAO {

    //alap lekérdezések:

    @Query("SELECT id, code, barcode, name, comments, active, dateCreate, dateMod, " +
            "lastTransferDate, lastTransferAction, transferFlag, pickingSource, pickingTarget, " +
            "receiverUserId, permanent, storageTypeId, parentId FROM Storages")
    LiveData<List<Storages>> getAllStorages() throws Exception;

    @Query("UPDATE Storages SET code= :code, barcode= :barcode, name= :name, comments= :comments," +
                                " active= :active, dateCreate= :dateCreate, dateMod= :dateMod," +
                                " lastTransferDate= :lastTransferDate, lastTransferAction= :lastTransferAction," +
                                " transferFlag= :transferFlag, pickingSource= :pickingSource," +
                                " pickingTarget= :pickingTarget, receiverUserId= :receiverUserId," +
                                " permanent= :permanent, storageTypeId= :storageTypeId, parentId= :parentId WHERE id = :id")
    void updateStorages(String id, String code, String barcode, String name, String comments,
                        boolean active, String dateCreate, String dateMod, String lastTransferDate,
                        String lastTransferAction, int transferFlag, boolean pickingSource, boolean pickingTarget,
                        long receiverUserId, float permanent, long storageTypeId, String parentId) throws Exception;

    @Insert
    void setStorage(Storages logClass) throws Exception;
}
