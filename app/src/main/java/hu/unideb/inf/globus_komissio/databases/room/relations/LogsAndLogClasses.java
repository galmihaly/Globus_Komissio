package hu.unideb.inf.globus_komissio.databases.room.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.Logs;

public class LogsAndLogClasses {

    // Logs és a LogClasses táblák kulcsainak kapcsolata

    @Embedded private LogClasses logClasses;
    @Relation(
            parentColumn = "id",
            entityColumn = "logClassId"
    )
    private Logs logs;
}
