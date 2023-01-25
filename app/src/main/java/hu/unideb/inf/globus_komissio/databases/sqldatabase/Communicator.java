package hu.unideb.inf.globus_komissio.databases.sqldatabase;

import java.util.List;

public interface Communicator {

    public List<User> getAllUserData();
    public List<Card> getAllCardData();
    public List<UserCard> getAllUserCardsData();
}
