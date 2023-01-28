package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MovementCodeStorages {

    @NonNull
    @PrimaryKey
    private long movementCodeId;

    @NonNull private String storageId;
    @NonNull private boolean rightIn;
    @NonNull private boolean rightOut;

    public long getMovementCodeId() {
        return movementCodeId;
    }

    public void setMovementCodeId(long movementCodeId) {
        this.movementCodeId = movementCodeId;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public boolean isRightIn() {
        return rightIn;
    }

    public void setRightIn(boolean rightIn) {
        this.rightIn = rightIn;
    }

    public boolean isRightOut() {
        return rightOut;
    }

    public void setRightOut(boolean rightOut) {
        this.rightOut = rightOut;
    }

    @Override
    public String toString() {
        return "MovementCodeStorages{" +
                "movementCodeId=" + movementCodeId +
                ", storageId='" + storageId + '\'' +
                ", rightIn=" + rightIn +
                ", rightOut=" + rightOut +
                '}';
    }
}
