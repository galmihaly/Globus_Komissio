package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class MovementCodes {

    @NonNull
    @PrimaryKey
    private long id;

    @NonNull private int direction;
    @NonNull private String name;
    private String comments;
    @NonNull private boolean active;
    @NonNull private String dateCreate;
    @NonNull private String dateMod;
    private String lastTransferDate;
    private String lastTransferAction;
    private int transferFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        return "MovementCodes{" +
                "id=" + id +
                ", direction=" + direction +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                ", active=" + active +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", lastTransferDate=" + lastTransferDate +
                ", lastTransferAction='" + lastTransferAction + '\'' +
                ", transferFlag=" + transferFlag +
                '}';
    }
}