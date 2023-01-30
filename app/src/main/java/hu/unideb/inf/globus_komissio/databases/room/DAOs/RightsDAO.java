package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Rights;

@Dao
public interface RightsDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, description FROM Rights")
    LiveData<List<Rights>> getAllRights() throws Exception;

    @Query("UPDATE Rights SET name= :name, description= :description WHERE id = :id")
    void updateRights(long id, String name, String description) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setRight(List<Rights> right) throws Exception;
}
