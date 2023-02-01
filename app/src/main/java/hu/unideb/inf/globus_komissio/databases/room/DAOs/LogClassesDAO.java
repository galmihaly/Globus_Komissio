package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import hu.unideb.inf.globus_komissio.databases.models.LogClasses;

@Dao
public interface LogClassesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, description FROM LogClasses")
    LiveData<List<LogClasses>> getAllLogClasses() throws Exception;

    @Query("UPDATE LogClasses SET name= :name, description= :description WHERE id = :id")
    void updateLogClasses(long id, String name, String description) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLogClass(List<LogClasses> logClass) throws Exception;
}
