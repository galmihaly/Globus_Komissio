package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = ArticleTypes.class,
                        parentColumns = "id",
                        childColumns = "articleTypeId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {@Index(value = {"articleTypeId"})}
)
public class Articles {

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull private String name;
    private String quantityUnit;
    private String barcode;
    private boolean active;
    @NonNull private float price;
    @NonNull private long articleTypeId;
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

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(long articleTypeId) {
        this.articleTypeId = articleTypeId;
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

    public void setLastTransferAction(String lastTransferAction) { this.lastTransferAction = lastTransferAction; }

    public int getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(int transferFlag) {
        this.transferFlag = transferFlag;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", barcode='" + barcode + '\'' +
                ", active=" + active +
                ", price=" + price +
                ", articleTypeId=" + articleTypeId +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", lastTransferDate=" + lastTransferDate +
                ", lastTransferAction='" + lastTransferAction + '\'' +
                ", transferFlag=" + transferFlag +
                '}';
    }
}