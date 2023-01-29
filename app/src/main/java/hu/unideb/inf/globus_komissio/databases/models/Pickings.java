package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = PickingStatuses.class,
                        parentColumns = "id",
                        childColumns = "pickingStatusId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = ArticleTypes.class,
                        parentColumns = "id",
                        childColumns = "articleTypeId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = MovementCodes.class,
                        parentColumns = "id",
                        childColumns = "movementCodeId",
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
                @Index(value = {"pickingStatusId"}),
                @Index(value = {"articleTypeId"}),
                @Index(value = {"movementCodeId"}),
                @Index(value = {"userId"}),
                @Index(value = {"receiverUserId"})
        }
)
public class Pickings {

    @NonNull
    @PrimaryKey
    private long id;

    private String name;
    @NonNull private String description;
    @NonNull private long pickingStatusId;
    @NonNull private String dateCreate;
    @NonNull private String dateMod;
    private String dateUpload;
    private String dateProcess;
    @NonNull private long userId;
    @NonNull private long receiverUserId;
    @NonNull private long articleTypeId;
    @NonNull private long movementCodeId;
    private String comments;
    private String barcode;
    private String dateBook;
    private String resultText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPickingStatusId() {
        return pickingStatusId;
    }

    public void setPickingStatusId(long pickingStatusId) {
        this.pickingStatusId = pickingStatusId;
    }

    @NonNull
    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    @NonNull
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

    public long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(long articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public long getMovementCodeId() {
        return movementCodeId;
    }

    public void setMovementCodeId(long movementCodeId) {
        this.movementCodeId = movementCodeId;
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

    public String getDateBook() {
        return dateBook;
    }

    public void setDateBook(String dateBook) {
        this.dateBook = dateBook;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    @Override
    public String toString() {
        return "Pickings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pickingStatusId=" + pickingStatusId +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", dateUpload=" + dateUpload +
                ", datePorcess=" + dateProcess +
                ", userId=" + userId +
                ", receiverUserId=" + receiverUserId +
                ", articleTypeId=" + articleTypeId +
                ", movementCodeId=" + movementCodeId +
                ", comments='" + comments + '\'' +
                ", barcode='" + barcode + '\'' +
                ", dateBook=" + dateBook +
                ", resultText='" + resultText + '\'' +
                '}';
    }
}