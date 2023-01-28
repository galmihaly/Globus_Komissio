package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Config;

@Dao
public interface ConfigDAO {

    //alap lekérdezések:

    @Query("SELECT id, configName, configValue, userId, comments FROM Config")
    LiveData<List<Config>> getAllConfig() throws Exception;

    @Query("UPDATE Config SET configName= :configName, configValue= :configValue, userId= :userId, comments= :comments WHERE id = :id")
    void updateConfig(long id, String configName, String configValue, long userId, String comments) throws Exception;

    @Insert
    void setConfig(Config config) throws Exception;
}
