package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Version;

@Dao
public interface VersionDAO {

    //alap lekérdezések:

    @Query("SELECT id, dateCreate, comments FROM Version")
    LiveData<List<Version>> getAllVersion() throws Exception;

    @Query("UPDATE Version SET dateCreate= :dateCreate, comments= :comments WHERE id = :id")
    void updateVersion(long id, String dateCreate, String comments) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setVersion(List<Version> version) throws Exception;

}
