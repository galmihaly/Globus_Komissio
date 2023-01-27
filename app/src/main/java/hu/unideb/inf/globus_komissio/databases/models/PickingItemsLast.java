package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class PickingItemsLast {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private long pickingId;
    @NonNull private String dateCreate;
    @NonNull private String artnr;
    private float quantity;
    @NonNull private String quantityUnit;
    @NonNull private String workflowId;
    private long workflowInfoSysId;
    @NonNull private long userId;
    @NonNull private long receiverUserId;
    @NonNull private String storageIdFrom;
    @NonNull private String storageIdTo;
    private String comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPickingId() {
        return pickingId;
    }

    public void setPickingId(long pickingId) {
        this.pickingId = pickingId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getArtnr() {
        return artnr;
    }

    public void setArtnr(String artnr) {
        this.artnr = artnr;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public long getWorkflowInfoSysId() {
        return workflowInfoSysId;
    }

    public void setWorkflowInfoSysId(long workflowInfoSysId) { this.workflowInfoSysId = workflowInfoSysId; }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getStorageIdFrom() {
        return storageIdFrom;
    }

    public void setStorageIdFrom(String storageIdFrom) {
        this.storageIdFrom = storageIdFrom;
    }

    public String getStorageIdTo() {
        return storageIdTo;
    }

    public void setStorageIdTo(String storageIdTo) {
        this.storageIdTo = storageIdTo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PickingItemsLast{" +
                "id=" + id +
                ", pickingId=" + pickingId +
                ", dateCreate=" + dateCreate +
                ", artnr='" + artnr + '\'' +
                ", quantity=" + quantity +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", workflowId='" + workflowId + '\'' +
                ", workflowInfoSysId=" + workflowInfoSysId +
                ", userId=" + userId +
                ", receiverUserId=" + receiverUserId +
                ", storageIdFrom='" + storageIdFrom + '\'' +
                ", getStorageIdTo='" + storageIdTo + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
