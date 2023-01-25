package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LanguageCodes {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private String id;

    @NonNull private String name;
    @NonNull private String ISO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    @Override
    public String toString() {
        return "LanguageCodes{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ISO='" + ISO + '\'' +
                '}';
    }
}
