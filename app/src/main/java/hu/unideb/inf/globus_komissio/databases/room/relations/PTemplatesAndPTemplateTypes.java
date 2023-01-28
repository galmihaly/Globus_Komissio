package hu.unideb.inf.globus_komissio.databases.room.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.globus_komissio.databases.models.PrintTemplateTypes;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;

// PrintTemplates és a PrintTemplateTypes kulcsainak kapcsolata
public class PTemplatesAndPTemplateTypes {
    @Embedded public PrintTemplateTypes printTemplateTypes;
    @Relation(
            parentColumn = "id",
            entityColumn = "templateTypeId"
    )
    public PrintTemplates printTemplates;
}
