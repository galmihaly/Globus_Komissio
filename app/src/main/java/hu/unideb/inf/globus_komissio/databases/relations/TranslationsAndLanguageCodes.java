package hu.unideb.inf.globus_komissio.databases.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.globus_komissio.databases.models.LanguageCodes;
import hu.unideb.inf.globus_komissio.databases.models.Translations;

public class TranslationsAndLanguageCodes {

    @Embedded private LanguageCodes languageCodes;
    @Relation(
            parentColumn = "id",
            entityColumn = "languageCode"
    )
    private Translations translations;
}
