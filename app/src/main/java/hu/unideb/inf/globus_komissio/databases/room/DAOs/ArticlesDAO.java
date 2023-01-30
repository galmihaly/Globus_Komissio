package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Articles;

@Dao
public interface ArticlesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, quantityUnit, barcode, active, price, articleTypeId, dateCreate, dateMod, lastTransferDate, lastTransferAction, transferFlag  FROM Articles")
    LiveData<List<Articles>> getAllArticles() throws Exception;

    @Query("UPDATE Articles SET name= :name, quantityUnit= :quantityUnit, barcode= :barcode, active= :active, price= :price, dateCreate= :dateCreate, dateMod= :dateMod, lastTransferDate= :lastTransferDate, lastTransferAction= :lastTransferAction, transferFlag= :transferFlag WHERE id = :id")
    void updateArticle(String id, String name, String quantityUnit, String barcode, boolean active, float price, String dateCreate, String dateMod, String lastTransferDate, String lastTransferAction, int transferFlag) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setArticle(List<Articles> articles) throws Exception;
}
