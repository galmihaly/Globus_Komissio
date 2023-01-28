package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;

@Dao
public interface UserArticleTypesDAO {

    //alap lekérdezések:

    @Query("SELECT userId, articleTypeId, grantor, verify, rightIn, rightOut, userDefault FROM UserArticleTypes")
    LiveData<List<UserArticleTypes>> getAllUserArticleTypes() throws Exception;

    @Query("UPDATE UserArticleTypes SET articleTypeId= :articleTypeId, grantor= :grantor, verify= :verify, rightIn= :rightIn, rightOut= :rightOut, userDefault= :userDefault WHERE userId = :userId")
    void updateUserArticleTypes(long userId, long articleTypeId, int grantor, String verify, boolean rightIn, boolean rightOut, boolean userDefault) throws Exception;

    @Insert
    void setUserArticleType(UserArticleTypes userArticleType) throws Exception;
}
