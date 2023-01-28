package hu.unideb.inf.globus_komissio.databases.room.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;

@Dao
public interface UserRightsDAO {

    //alap lekérdezések:

    @Query("SELECT id, userId, rightId, grantor, verify FROM UserRights")
    LiveData<List<UserRights>> getAllUserRights() throws Exception;

    @Query("UPDATE UserRights SET userId= :userId, rightId= :rightId, grantor= :grantor, verify= :verify WHERE id = :id")
    void updateUserRights(long id, long userId, long rightId, long grantor, String verify) throws Exception;

    @Insert
    void setUserRight(UserRights userArticleType) throws Exception;
}
