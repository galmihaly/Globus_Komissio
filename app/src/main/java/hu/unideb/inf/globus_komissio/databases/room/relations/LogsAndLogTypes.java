package hu.unideb.inf.globus_komissio.databases.room.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.globus_komissio.databases.models.LogTypes;
import hu.unideb.inf.globus_komissio.databases.models.Logs;

public class LogsAndLogTypes {

    // Logs és a LogTypes táblák kulcsainak kapcsolata

    @Embedded
    private LogTypes logTypes;
    @Relation(
            parentColumn = "id",
            entityColumn = "logTypeId"
    )
    private Logs logs;
}
