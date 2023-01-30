package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.ArticleTypes;

@Dao
public interface ArticleTypesDAO {

    //alap lekérdezések:

    @Query("SELECT id, name, comments FROM ArticleTypes")
    LiveData<List<ArticleTypes>> getAllArticleTypes() throws Exception;

    @Query("UPDATE ArticleTypes SET name= :name, comments= :commnents WHERE id = :id")
    void updateArticleTypes(String id, String name, String commnents) throws Exception;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setArticleTypes(List<ArticleTypes> articleTypes) throws Exception;
}
