package hu.unideb.inf.globus_komissio.databases.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.globus_komissio.databases.sqldatabase.User;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.UserCard;


public class UserAndUserCard {

    // A User és UserCard között lévő adatbáziskapcsolat

    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userId"
    )
    public UserCard userCard;

    @Override
    public String toString() {
        return "UserAndUserCard{" +
                "user=" + user.toString() +
                ", userCard=" + userCard +
                '}';
    }
}
