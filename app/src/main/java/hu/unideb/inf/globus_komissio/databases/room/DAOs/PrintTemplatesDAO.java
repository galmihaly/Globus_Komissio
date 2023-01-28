package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;

@Dao
public interface PrintTemplatesDAO {

    //alap lekérdezések:

    @Query("SELECT id, templateTypeId, name, templateData, comments FROM PrintTemplates")
    LiveData<List<PrintTemplates>> getAllLogClasses() throws Exception;

    @Query("UPDATE PrintTemplates SET templateTypeId= :templateTypeId, name= :name, templateData= :templateData, comments= :comments WHERE id = :id")
    void updatePrintTemplates(long id, long templateTypeId, String name, String templateData, String comments) throws Exception;

    @Insert
    void setPrintTemplate(PrintTemplates printTemplate) throws Exception;
}
