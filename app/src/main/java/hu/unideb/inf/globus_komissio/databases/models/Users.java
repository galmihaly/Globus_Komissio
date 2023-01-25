package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Users {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private String account;
    @NonNull private String name;
    @NonNull private String password;
    @NonNull private boolean active;
    private Date lastLogin;
    private String email;
    private String telephone;
    private String rfid;
    private String pin;
    private String barcode;
    private String internalId;
    @NonNull private long userId;
    @NonNull private Date dateCreate;
    @NonNull private Date dateMod;
    private Date lastTransferDate;
    private String lastTransferAction;
    private int transferFlag;
    private String mobileFlexPassword;
    private String mobileFlexPin;
    private boolean localUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getMobileFlexPassword() {
        return mobileFlexPassword;
    }

    public void setMobileFlexPassword(String mobileFlexPassword) {
        this.mobileFlexPassword = mobileFlexPassword;
    }

    public String getMobileFlexPin() {
        return mobileFlexPin;
    }

    public void setMobileFlexPin(String mobileFlexPin) {
        this.mobileFlexPin = mobileFlexPin;
    }

    public boolean isLocalUser() {
        return localUser;
    }

    public void setLocalUser(boolean localUser) {
        this.localUser = localUser;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", lastLogin=" + lastLogin +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rfid='" + rfid + '\'' +
                ", pin='" + pin + '\'' +
                ", barcode='" + barcode + '\'' +
                ", internalId='" + internalId + '\'' +
                ", userId=" + userId +
                ", dateCreate=" + dateCreate +
                ", dateMod=" + dateMod +
                ", lastTransferDate=" + lastTransferDate +
                ", lastTransferAction='" + lastTransferAction + '\'' +
                ", transferFlag=" + transferFlag +
                ", mobileFlexPassword='" + mobileFlexPassword + '\'' +
                ", mobileFlexPin='" + mobileFlexPin + '\'' +
                ", localUser=" + localUser +
                '}';
    }
}
