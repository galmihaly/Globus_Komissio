package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.PickingItemsLast;

@Dao
public interface PickingItemLastDAO {

    //alap lekérdezések:

    @Query("SELECT id, pickingId, dateCreate, artnr, quantity, quantityUnit, workflowId, workflowInfoSysId, userId, receiverUserId, storageIdFrom, storageIdTo, comments FROM PickingItemsLast")
    LiveData<List<PickingItemsLast>> getAllPickingItemsLast() throws Exception;

    @Query("UPDATE PickingItemsLast SET pickingId= :pickingId, dateCreate= :dateCreate, artnr= :artnr, quantity= :quantity, quantityUnit= :quantityUnit, workflowId= :workflowId, workflowInfoSysId= :workflowInfoSysId, userId= :userId, receiverUserId= :receiverUserId, storageIdFrom= :storageIdFrom, storageIdTo= :storageIdTo, comments= :comments WHERE id = :id")
    void updatePickingItemsLast(long id, long pickingId, String dateCreate, String artnr, float quantity, String quantityUnit, String workflowId, long workflowInfoSysId, long userId, long receiverUserId, String storageIdFrom, String storageIdTo, String comments) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setPickingItemsLast(List<PickingItemsLast> pickingItemsLast) throws Exception;

}
