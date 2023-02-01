package hu.unideb.inf.globus_komissio.activities.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

import hu.unideb.inf.globus_komissio.enums.PageEnums;
import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.enums.LogLevel;
import hu.unideb.inf.globus_komissio.activities.BarcodeLoginActivity;
import hu.unideb.inf.globus_komissio.activities.PincodeLoginActivity;
import hu.unideb.inf.globus_komissio.activities.UserPasswordLoginActivity;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginActivityPresenter;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginActivityView;
import hu.unideb.inf.globus_komissio.tasks.CustomCallable;
import hu.unideb.inf.globus_komissio.tasks.ProcessBarcode;
import hu.unideb.inf.globus_komissio.tasks.ProcessUsernamePassword;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.PresenterThreadCallback;
import hu.unideb.inf.globus_komissio.activities.utils.Util;

public class LoginActivityPresenter implements ILoginActivityPresenter, PresenterThreadCallback {

    private ILoginActivityView iLoginActivityView;
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private LoginActivityHandler mLoginActivityHandler;
    private Context context;

    public LoginActivityPresenter(ILoginActivityView iLoginActivityView, Context context) {
        this.iLoginActivityView = iLoginActivityView;
        this.context = context;
    }

    @Override
    public void loginWithPinCode(String pincode) {
        initTaskManager();
    }

    @Override
    public void loginWithBarcode(String barcode) {
        initTaskManager();
        initBarcodeProcess(barcode);
    }

    @Override
    public void getClearRequest() {
        sendClearToPresenter();
    }

    @Override
    public void loginWithUsernamePassword(String username, String password) {
        initTaskManager();
    }

    @Override
    public void initTaskManager() {
        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "A feladatkezelő létrehozása megkezdődött.");

            mLoginActivityHandler = new LoginActivityHandler(Looper.myLooper(), this);
            mCustomThreadPoolManager = CustomThreadPoolManager.getsInstance();
            mCustomThreadPoolManager.setPresenterCallback(this);

            ApplicationLogger.logging(LogLevel.INFORMATION, "A feladatkezelő létrehozása befejeződött.");
        }
        catch (Exception e){
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void initPasswordProcess(String password) {
        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az jelszóhitelesítési folyamat inicializálása elkezdődött.");

            CustomCallable callable = new CustomCallable();
            callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
            mCustomThreadPoolManager.addCallableMethod(callable);

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az jelszóhitelesítési folyamat inicializálása befejeződött.");
        }
        catch (Exception e){
            e.printStackTrace();
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void initBarcodeProcess(String barcode) {
        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az vonalkódhitelesítési folyamat inicializálása elkezdődött.");

            ProcessBarcode callable = new ProcessBarcode(context, barcode);
            callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
            mCustomThreadPoolManager.addCallableMethod(callable);

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az vonalkódhitelesítési folyamat inicializálása befejeződött.");
        }
        catch (Exception e){
            e.printStackTrace();
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void initUserPasswordProcess(String username, String password) {
        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az felhasználónév és jelszó hitelesítési folyamat inicializálása elkezdődött.");

            ProcessUsernamePassword callable = new ProcessUsernamePassword(context, username, password);
            callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
            mCustomThreadPoolManager.addCallableMethod(callable);

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az felhasználónév és jelszó hitelesítési folyamat inicializálása befejeződött.");
        }
        catch (Exception e){
            e.printStackTrace();
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void sendPageEnumToPresenter(PageEnums pageEnums) {
        switch (pageEnums){
            case BARCODE_LOGINPAGE_ACTIVITY:{
                Intent intent = new Intent(context, BarcodeLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                iLoginActivityView.loadOtherActivityPages(intent);
                break;
            }
            case USERPASSWORD_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(context, UserPasswordLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                iLoginActivityView.loadOtherActivityPages(intent);
                break;
            }
            case PINCODE_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(context, PincodeLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                iLoginActivityView.loadOtherActivityPages(intent);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void sendMessageToPresenter(String message) {
        iLoginActivityView.sendStringToUiToast(message);
    }

    @Override
    public void sendClearToPresenter() {
        iLoginActivityView.getClearFromPresenter();
    }

    //PresenterThreadCallback
    @Override
    public void sendResultToPresenter(Message message) {
        if(mLoginActivityHandler == null) return;
        mLoginActivityHandler.sendMessage(message);
    }

    private static class LoginActivityHandler extends Handler {

        private WeakReference<ILoginActivityPresenter> iLoginPageActivityPresenterWeakReference;

        public LoginActivityHandler(Looper looper, ILoginActivityPresenter iLoginPageActivityPresenter) {
            super(looper);
            this.iLoginPageActivityPresenterWeakReference = new WeakReference<>(iLoginPageActivityPresenter);
        }

        // Ui-ra szánt üzenetet kezelejük itt
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case Util.LOGINBARCODE_FINISH:{
                    iLoginPageActivityPresenterWeakReference.get().sendMessageToPresenter("Sikeres bejelentkezés!");
                    break;
                }
                case Util.LOGINBARCODE_NORIGHT:{
                    iLoginPageActivityPresenterWeakReference.get().sendMessageToPresenter("Nincs jogosultásga belépni!");
                    iLoginPageActivityPresenterWeakReference.get().sendClearToPresenter();
                    break;
                }
                case Util.LOGINBARCODE_NORIGHTS:{
                    iLoginPageActivityPresenterWeakReference.get().sendMessageToPresenter("Nincs semmilyen jogosultsága!");
                    iLoginPageActivityPresenterWeakReference.get().sendClearToPresenter();
                    break;
                }
                case Util.LOGINBARCODE_FAILED:{
                    iLoginPageActivityPresenterWeakReference.get().sendMessageToPresenter("Nincs ilyen vonalkóddal regisztrált felhasználó!");
                    iLoginPageActivityPresenterWeakReference.get().sendClearToPresenter();
                    break;
                }
                case Util.WIFI_FAILED:{
                    iLoginPageActivityPresenterWeakReference.get().sendMessageToPresenter("Nincs internetelérés!");
                    break;
                }
                default:
                    break;
            }
        }
    }
}
