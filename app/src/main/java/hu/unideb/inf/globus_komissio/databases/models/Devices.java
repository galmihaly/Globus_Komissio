package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Devices {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull private String deviceId;
    @NonNull private long deviceTypeId;
    @NonNull private String deviceName;
    private String comments;
    private boolean active;
    private long userId;
    private String storageId;
    @NonNull private int loginMode;
    private long lastUserId;
    private Date lastUserLogin;
    private String ipAddress;
    private int port;
    private int programType;
    private int flag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public int getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(int loginMode) {
        this.loginMode = loginMode;
    }

    public long getLastUserId() {
        return lastUserId;
    }

    public void setLastUserId(long lastUserId) {
        this.lastUserId = lastUserId;
    }

    public Date getLastUserLogin() {
        return lastUserLogin;
    }

    public void setLastUserLogin(Date lastUserLogin) {
        this.lastUserLogin = lastUserLogin;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getProgramType() {
        return programType;
    }

    public void setProgramType(int programType) {
        this.programType = programType;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", deviceTypeId=" + deviceTypeId +
                ", deviceName='" + deviceName + '\'' +
                ", comments='" + comments + '\'' +
                ", active=" + active +
                ", userId=" + userId +
                ", storageId='" + storageId + '\'' +
                ", loginMode=" + loginMode +
                ", lastUserId=" + lastUserId +
                ", lastUserLogin=" + lastUserLogin +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", programType=" + programType +
                ", flag=" + flag +
                '}';
    }
}
