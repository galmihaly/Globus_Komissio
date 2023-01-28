package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserArticleTypes {

    @NonNull
    @PrimaryKey
    private long userId;

    @NonNull private long articleTypeId;
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

    public long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(long articleTypeId) {
        this.articleTypeId = articleTypeId;
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

    public boolean isUserDefault() {
        return userDefault;
    }

    public void setUserDefault(boolean userDefault) {
        this.userDefault = userDefault;
    }

    @Override
    public String toString() {
        return "UserArticleTypes{" +
                "userId=" + userId +
                ", articleTypeId=" + articleTypeId +
                ", grantor=" + grantor +
                ", verify='" + verify + '\'' +
                ", rightIn=" + rightIn +
                ", rightOut=" + rightOut +
                ", userDefault=" + userDefault +
                '}';
    }
}
