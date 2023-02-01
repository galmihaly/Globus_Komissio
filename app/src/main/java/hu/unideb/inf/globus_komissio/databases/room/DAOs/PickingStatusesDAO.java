package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.PickingStatuses;

@Dao
public interface PickingStatusesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, comments, backgroundColor, foregroundColor FROM PickingStatuses")
    LiveData<List<PickingStatuses>> getAllPickingStatuses() throws Exception;

    @Query("UPDATE PickingStatuses SET name= :name, comments= :comments, backgroundColor= :backgroundColor, foregroundColor= :foregroundColor WHERE id = :id")
    void updatePickingStatuses(long id, String name, String comments, int backgroundColor, int foregroundColor) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setPickingStatuse(List<PickingStatuses> pickingStatuse) throws Exception;
}
