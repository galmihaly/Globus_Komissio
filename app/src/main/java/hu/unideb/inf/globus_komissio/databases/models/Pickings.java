package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Pickings {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    @NonNull private String description;
    @NonNull private long pickingStatusId;
    @NonNull private Date dateCreate;
    @NonNull private Date dateMod;
    private Date dateUpload;
    private Date datePorcess;
    @NonNull private long userId;
    @NonNull private long receiverUserId;
    @NonNull private long articleTypeId;
    @NonNull private long movementCodeId;
    private String comments;
    private String barcode;
    private Date dateBook;
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

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    public Date getDatePorcess() {
        return datePorcess;
    }

    public void setDatePorcess(Date datePorcess) {
        this.datePorcess = datePorcess;
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

    public Date getDateBook() {
        return dateBook;
    }

    public void setDateBook(Date dateBook) {
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
                ", datePorcess=" + datePorcess +
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
