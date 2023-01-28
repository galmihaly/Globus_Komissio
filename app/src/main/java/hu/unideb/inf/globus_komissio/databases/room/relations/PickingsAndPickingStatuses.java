package hu.unideb.inf.globus_komissio.databases.room.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.globus_komissio.databases.models.PickingStatuses;
import hu.unideb.inf.globus_komissio.databases.models.Pickings;

public class PickingsAndPickingStatuses {

    // Picking és a PickingStatuses kulcsainak kapcsolatai

    @Embedded private PickingStatuses pickingStatuses;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    private Pickings pickings;
}
