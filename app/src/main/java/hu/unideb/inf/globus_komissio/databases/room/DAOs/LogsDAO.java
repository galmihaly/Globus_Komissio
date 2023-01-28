package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Logs;

@Dao
public interface LogsDAO {

    //alap lekérdezések:

    @Query("SELECT id, logTypeId, logClassId, timestamp, message, exception, relatedId, userId FROM Logs")
    LiveData<List<Logs>> getAllLogs() throws Exception;

    @Query("UPDATE Logs SET logTypeId= :logTypeId, logClassId= :logClassId, timeStamp= :tinmeStamp, message= :message, exception= :exception, relatedId= :relatedId, userId= :userid WHERE id = :id")
    void updateLogs(long id, long logTypeId, long logClassId, String tinmeStamp, String message, String exception, String relatedId, long userid) throws Exception;

    @Insert
    void setLog(Logs log) throws Exception;
}
