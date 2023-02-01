package hu.unideb.inf.globus_komissio.databases.localcontainers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;

public class LoggedUserContainer {

    private static final LoggedUserContainer sInstance;

    private int id;
    private String account;
    private String name;
    private String password;
    private String pin;
    private String rfid;
    private String barcode;
    private boolean active;
    private List<RightsContainer> rights;

    static {
        sInstance = new LoggedUserContainer();
    }

    public static LoggedUserContainer getsInstance() {
        return sInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String newAccount) {
        account = newAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String newPin) {
        pin = newPin;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String newRfid) {
        rfid = newRfid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String newBarcode) {
        barcode = newBarcode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean newActive) {
        active = newActive;
    }

    public List<RightsContainer> getRights() {
        return rights;
    }

    public void setRights(List<RightsContainer> newRights) {
        rights = newRights;
    }
}
