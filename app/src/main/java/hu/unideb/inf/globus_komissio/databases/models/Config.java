package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Config {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private String configName;
    @NonNull private String configValue;
    private long userId;
    private String comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", configName='" + configName + '\'' +
                ", configValue=" + configValue +
                ", userId=" + userId +
                ", comments='" + comments + '\'' +
                '}';
    }
}
