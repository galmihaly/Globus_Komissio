package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserMovementCodes {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int us;

    @NonNull private long movementCodeId;
    @NonNull private int grantor;
    private String verify;
    @NonNull private int rightIn;
    @NonNull private int rightOut;
    @NonNull private int userDefault;

    public int getUs() {
        return us;
    }

    public void setUs(int us) {
        this.us = us;
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

    public int getRightIn() {
        return rightIn;
    }

    public void setRightIn(int rightIn) {
        this.rightIn = rightIn;
    }

    public int getRightOut() {
        return rightOut;
    }

    public void setRightOut(int rightOut) {
        this.rightOut = rightOut;
    }

    public int getUserDefault() {
        return userDefault;
    }

    public void setUserDefault(int userDefault) {
        this.userDefault = userDefault;
    }

    @Override
    public String toString() {
        return "UserMovementCodes{" +
                "us=" + us +
                ", movementCodeId=" + movementCodeId +
                ", grantor=" + grantor +
                ", verify='" + verify + '\'' +
                ", rightIn=" + rightIn +
                ", rightOut=" + rightOut +
                ", userDefault=" + userDefault +
                '}';
    }
}
