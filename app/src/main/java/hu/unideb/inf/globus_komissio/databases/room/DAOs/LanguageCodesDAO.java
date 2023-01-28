package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;
import hu.unideb.inf.globus_komissio.databases.models.LanguageCodes;

@Dao
public interface LanguageCodesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, ISO FROM LanguageCodes")
    LiveData<List<LanguageCodes>> getAllLanguageCodes() throws Exception;

    @Query("UPDATE LanguageCodes SET name= :name, ISO= :ISO WHERE id = :id")
    void updateLanguageCode(String id, String name, String ISO) throws Exception;

    @Insert
    void setDeviceType(LanguageCodes languageCode) throws Exception;
}
