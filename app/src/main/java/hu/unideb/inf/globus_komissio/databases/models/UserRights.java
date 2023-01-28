package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class UserRights {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull private long userId;
    @NonNull private long rightId;
    @NonNull private long grantor;
    @NonNull private String verify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRightId() { return rightId; }

    public void setRightId(long rightId) {
        this.rightId = rightId;
    }

    public long getGrantor() { return grantor; }

    public void setGrantor(long grantor) {
        this.grantor = grantor;
    }

    public String getVerify() { return verify; }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    @Override
    public String toString() {
        return "UserRights{" +
                "userId=" + userId +
                ", rightId=" + rightId +
                ", grantor=" + grantor +
                ", verify='" + verify + '\'' +
                '}';
    }
}
