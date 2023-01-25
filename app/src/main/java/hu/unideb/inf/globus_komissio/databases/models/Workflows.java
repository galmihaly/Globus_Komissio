package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Workflows {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private long infoSysId;
    @NonNull private boolean active;
    @NonNull private Date dateCreate;
    @NonNull private Date dateMod;
    private Date lastTransferDate;
    private String lastTransferAction;
    private int transferFlag;

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

    public long getInfoSysId() {
        return infoSysId;
    }

    public void setInfoSysId(long infoSysId) {
        this.infoSysId = infoSysId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    public Date getLastTransferDate() {
        return lastTransferDate;
    }

    public void setLastTransferDate(Date lastTransferDate) {
        this.lastTransferDate = lastTransferDate;
    }

    public String getLastTransferAction() {
        return lastTransferAction;
    }

    public void setLastTransferAction(String lastTransferAction) {
        this.lastTransferAction = lastTransferAction;
    }

    public int getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(int transferFlag) {
        this.transferFlag = transferFlag;
    }

    @Override
    public String toString() {
        return "Workflows{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", infoSysId=" + infoSysId +
                ", active=" + active +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", lastTransferDate=" + lastTransferDate +
                ", lastTransferAction='" + lastTransferAction + '\'' +
                ", transferFlag=" + transferFlag +
                '}';
    }
}
