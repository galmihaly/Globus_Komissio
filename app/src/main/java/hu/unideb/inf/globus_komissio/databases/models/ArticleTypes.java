package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArticleTypes {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private String name;
    private String commnents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommnents() {
        return commnents;
    }

    public void setCommnents(String commnents) {
        this.commnents = commnents;
    }

    @Override
    public String toString() {
        return "ArticleTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Commnents='" + commnents + '\'' +
                '}';
    }
}
