package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Workflows.class,
                        parentColumns = "id",
                        childColumns = "workFlowId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Articles.class,
                        parentColumns = "id",
                        childColumns = "articleId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Devices.class,
                        parentColumns = "id",
                        childColumns = "deviceId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Pickings.class,
                        parentColumns = "id",
                        childColumns = "pickingId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = PickingStatuses.class,
                        parentColumns = "id",
                        childColumns = "pickingStatusId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Storages.class,
                        parentColumns = "id",
                        childColumns = "pickingStorageId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Storages.class,
                        parentColumns = "id",
                        childColumns = "sourceStorageId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Users.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Users.class,
                        parentColumns = "id",
                        childColumns = "receiverUserId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = {"workFlowId"}),
                @Index(value = {"articleId"}),
                @Index(value = {"deviceId"}),
                @Index(value = {"pickingId"}),
                @Index(value = {"pickingStatusId"}),
                @Index(value = {"pickingStorageId"}),
                @Index(value = {"sourceStorageId"}),
                @Index(value = {"userId"}),
                @Index(value = {"receiverUserId"})
        }
)
public class PickingItems {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private long pickingId;
    @NonNull private int position;
    private String name;
    @NonNull private String articleId;
    @NonNull private long userId;
    @NonNull private long receiverUserId;
    @NonNull private String sourceStorageId;
    @NonNull private String pickingStorageId;
    @NonNull private String workFlowId;
    @NonNull private float quantity;
    @NonNull private String quantityUnit;
    @NonNull private long pickingStatusId;
    @NonNull private long deviceId;
    @NonNull private String dateCreate;
    @NonNull private String dateMod;
    private String dateUpload;
    private String dateProcess;
    private String comments;
    private String barcode;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

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

    public String getSourceStorageId() {
        return sourceStorageId;
    }

    public void setSourceStorageId(String sourceStorageId) {
        this.sourceStorageId = sourceStorageId;
    }

    public String getPickingStorageId() {
        return pickingStorageId;
    }

    public void setPickingStorageId(String pickingStorageId) {
        this.pickingStorageId = pickingStorageId;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
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

    public long getPickingStatusId() {
        return pickingStatusId;
    }

    public void setPickingStatusId(long pickingStatusId) {
        this.pickingStatusId = pickingStatusId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
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

    public String getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(String dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getDateProcess() {
        return dateProcess;
    }

    public void setDateProcess(String dateProcess) {
        this.dateProcess = dateProcess;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "PickingItems{" +
                "id=" + id +
                ", pickingId=" + pickingId +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", articleId='" + articleId + '\'' +
                ", userId=" + userId +
                ", receiverUserId=" + receiverUserId +
                ", sourceStorageId='" + sourceStorageId + '\'' +
                ", pickingStorageId='" + pickingStorageId + '\'' +
                ", workFlowId='" + workFlowId + '\'' +
                ", quantity=" + quantity +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", pickingStatusId=" + pickingStatusId +
                ", deviceId=" + deviceId +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", dateUpload=" + dateUpload +
                ", dateProcess=" + dateProcess +
                ", comments='" + comments + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
