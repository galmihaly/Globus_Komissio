package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = LogTypes.class,
                        parentColumns = "id",
                        childColumns = "logTypeId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = LogClasses.class,
                        parentColumns = "id",
                        childColumns = "logClassId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = {"logTypeId"}),
                @Index(value = {"logClassId"})
        }
)
public class Logs {

    @NonNull
    @PrimaryKey
    private long id;

    @NonNull private long logTypeId;
    @NonNull private long logClassId;
    @NonNull private String timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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
