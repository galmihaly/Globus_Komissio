package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserMovementCodes {
    @NonNull
    @PrimaryKey
    private long userId;

    @NonNull private long movementCodeId;
    @NonNull private int grantor;
    private String verify;
    @NonNull private boolean rightIn;
    @NonNull private boolean rightOut;
    @NonNull private boolean userDefault;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMovementCodeId() {
        return movementCodeId;
    }

    public void setMovementCodeId(long movementCodeId) {
        this.movementCodeId = movementCodeId;
    }

    public int getGrantor() {
        return grantor;
    }

    public void setGrantor(int grantor) {
        this.grantor = grantor;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public boolean getRightIn() {
        return rightIn;
    }

    public void setRightIn(boolean rightIn) {
        this.rightIn = rightIn;
    }

    public boolean getRightOut() {
        return rightOut;
    }

    public void setRightOut(boolean rightOut) {
        this.rightOut = rightOut;
    }

    public boolean getUserDefault() {
        return userDefault;
    }

    public void setUserDefault(boolean userDefault) {
        this.userDefault = userDefault;
    }

    @Override
    public String toString() {
        return "UserMovementCodes{" +
                "us=" + userId +
                ", movementCodeId=" + movementCodeId +
                ", grantor=" + grantor +
                ", verify='" + verify + '\'' +
                ", rightIn=" + rightIn +
                ", rightOut=" + rightOut +
                ", userDefault=" + userDefault +
                '}';
    }
}
