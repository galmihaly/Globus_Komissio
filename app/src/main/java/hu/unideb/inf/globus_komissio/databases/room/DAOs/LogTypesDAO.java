package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.LogTypes;

@Dao
public interface LogTypesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, description FROM LogTypes")
    LiveData<List<LogClasses>> getAllLogTypes() throws Exception;

    @Query("UPDATE LogClasses SET name= :name, description= :description WHERE id = :id")
    void updateLogTypes(long id, String name, String description) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLogType(List<LogTypes> logTypes) throws Exception;
}
