package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.PickingItems;

@Dao
public interface PickingItemsDAO {

    //alap lekérdezések:

    @Query("SELECT id, pickingId, position, name, articleId, userId, receiverUserId, " +
            "sourceStorageId, pickingStorageId, workFlowId, quantity, quantityUnit, pickingStatusId, " +
            "deviceId, dateCreate, dateMod, dateUpload, dateProcess, comments, barcode  FROM PickingItems")
    LiveData<List<PickingItems>> getAllPickingItems() throws Exception;

    @Query("UPDATE PickingItems SET pickingId= :pickingId, position= :position, name= :name, " +
                                    "articleId= :articleId, userId= :userId, receiverUserId= :receiverUserId, " +
                                    "sourceStorageId= :sourceStorageId, pickingStorageId= :pickingStorageId, workflowId= :workflowId, " +
                                    "quantity= :quantity, quantityUnit= :quantityUnit, pickingStatusId= :pickingStatusId, " +
                                    "deviceId= :deviceId, dateCreate= :dateCreate, dateMod= :dateMod, dateUpload= :dateUpload, " +
                                    "dateProcess= :dateProcess, comments= :comments, barcode= :barcode " +
                                    "WHERE id = :id")
    void updateLogTypes(long id, long pickingId, int position,
                        String name, String articleId, long userId, long receiverUserId,
                        String sourceStorageId, String pickingStorageId, String workflowId,
                        float quantity, String quantityUnit, long pickingStatusId,
                        long deviceId, String dateCreate, String dateMod,
                        String dateUpload, String dateProcess,
                        String comments, String barcode) throws Exception;


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setPickingItem(List<PickingItems> pickingItem) throws Exception;
}
