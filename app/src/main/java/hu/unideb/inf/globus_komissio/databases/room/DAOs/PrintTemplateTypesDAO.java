package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.PrintTemplateTypes;

@Dao
public interface PrintTemplateTypesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, comments FROM PrintTemplateTypes")
    LiveData<List<PrintTemplateTypes>> getAllPrintTemplateTypes() throws Exception;

    @Query("UPDATE PrintTemplateTypes SET name= :name, comments= :comments WHERE id = :id")
    void updatePrintTemplateTypes(long id, String name, String comments) throws Exception;

    @Insert
    void setPrintTemplateType(PrintTemplateTypes printTemplateType) throws Exception;
}
