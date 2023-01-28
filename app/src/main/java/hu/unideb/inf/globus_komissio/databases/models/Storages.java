package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Storages {

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull private String code;
    private String barcode;
    @NonNull private String name;
    private String comments;
    @NonNull private boolean active;
    @NonNull private String dateCreate;
    @NonNull private String dateMod;
    private String lastTransferDate;
    private String lastTransferAction;
    private int transferFlag;
    @NonNull private boolean pickingSource;
    @NonNull private boolean pickingTarget;
    private long receiverUserId;
    private boolean permanent;
    private long storageTypeId;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public boolean isPickingSource() {
        return pickingSource;
    }

    public void setPickingSource(boolean pickingSource) {
        this.pickingSource = pickingSource;
    }

    public boolean isPickingTarget() {
        return pickingTarget;
    }

    public void setPickingTarget(boolean pickingTarget) {
        this.pickingTarget = pickingTarget;
    }

    public long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public long getStorageTypeId() {
        return storageTypeId;
    }

    public void setStorageTypeId(long storageTypeId) {
        this.storageTypeId = storageTypeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Storages{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                ", active=" + active +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", lastTransferData=" + lastTransferDate +
                ", lastTransferAction=" + lastTransferAction +
                ", transferFlag=" + transferFlag +
                ", pickingSource=" + pickingSource +
                ", pickingTarget=" + pickingTarget +
                ", receiverUserId=" + receiverUserId +
                ", pernament=" + permanent +
                ", storageTypeId=" + storageTypeId +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}