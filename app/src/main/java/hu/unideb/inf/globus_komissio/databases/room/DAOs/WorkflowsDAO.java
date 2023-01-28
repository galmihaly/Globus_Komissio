package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;

@Dao
public interface WorkflowsDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, infoSysId, active, dateCreate, dateMod, lastTransferDate, lastTransferAction, transferFlag FROM Workflows")
    LiveData<List<Workflows>> getAllWorkFlows() throws Exception;

    @Query("UPDATE Workflows SET name= :name, infoSysId= :infoSysId, active= :active, " +
                                "dateCreate= :dateCreate, dateMod= :dateMod, lastTransferDate= :lastTransferDate, " +
                                "lastTransferAction= :lastTransferAction, transferFlag= :transferFlag " +
                                "WHERE id = :id")
    void updateWorkflows(String id, String name, long infoSysId,
                         boolean active, String dateCreate, String dateMod,
                         String lastTransferDate, String lastTransferAction, int transferFlag) throws Exception;

    @Insert
    void setWorkflow(Workflows logClass) throws Exception;

}
