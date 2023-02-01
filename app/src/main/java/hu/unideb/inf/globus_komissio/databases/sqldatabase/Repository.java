package hu.unideb.inf.globus_komissio.databases.sqldatabase;

import hu.unideb.inf.globus_komissio.enums.CommunicatorTypeEnums;

public class Repository {

    public CommunicatorTypeEnums CommunicatorTypes;
    public Communicator communicator;

    public Repository(CommunicatorTypeEnums enumType){
        this.CommunicatorTypes = enumType;

        if(enumType.equals(CommunicatorTypeEnums.MsSQLServer)){
            this.communicator = new SQLDatabaseQueries();
        }
        else {
            this.communicator = null;
        }
    }
}

