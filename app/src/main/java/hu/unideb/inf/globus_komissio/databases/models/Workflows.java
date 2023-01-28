package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Workflows {

    @NonNull
    @PrimaryKey
    private String id;

    private String name;
    private long infoSysId;
    @NonNull private boolean active;
    @NonNull private String dateCreate;
    @NonNull private String dateMod;
    private String lastTransferDate;
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

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateMod() {
        return dateMod;
    }

    public void setDateMod(String dateMod) {
        this.dateMod = dateMod;
    }

    public String getLastTransferDate() {
        return lastTransferDate;
    }

    public void setLastTransferDate(String lastTransferDate) {
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