package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Logs {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private long logTypeId;
    @NonNull private long logClassId;
    @NonNull private Date timestamp;
    private String message;
    private String exception;
    private String relatedId;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(long logTypeId) {
        this.logTypeId = logTypeId;
    }

    public long getLogClassId() {
        return logClassId;
    }

    public void setLogClassId(long logClassId) {
        this.logClassId = logClassId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", logTypeId=" + logTypeId +
                ", logClassId=" + logClassId +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                ", relatedId='" + relatedId + '\'' +
                ", userId=" + userId +
                '}';
    }
}
