package hu.unideb.inf.globus_komissio.activities.interfaces;

import hu.unideb.inf.globus_komissio.enums.PageEnums;

public interface ILoginActivityPresenter {

    void initTaskManager();
    void sendPageEnumToPresenter(PageEnums pageEnums);
    void sendMessageToPresenter(String message);
    void sendClearToPresenter();
    void getClearRequest();
    void loginWithPinCode(String pincode);
    void loginWithBarcode(String barcode);
    void loginWithUsernamePassword(String username, String password);
    void initPasswordProcess(String password);
    void initBarcodeProcess(String barcode);
    void initUserPasswordProcess(String username, String password);
}
