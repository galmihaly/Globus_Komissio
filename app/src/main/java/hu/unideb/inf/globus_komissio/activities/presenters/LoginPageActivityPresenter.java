package hu.unideb.inf.globus_komissio.activities.presenters;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import hu.unideb.inf.globus_komissio.Enums.PageEnums;
import hu.unideb.inf.globus_komissio.LoggerElements.ApplicationLogger;
import hu.unideb.inf.globus_komissio.LoggerElements.LogLevel;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginPageActivityPresenter;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginPageActivityView;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityPresenter;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.PresenterThreadCallback;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class LoginPageActivityPresenter implements ILoginPageActivityPresenter, PresenterThreadCallback {

    private ILoginPageActivityView iLoginPageActivityView;
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private LoginPageActivityPresenter.UiHandler mUiHandler;

    public LoginPageActivityPresenter(ILoginPageActivityView iLoginPageActivityView) {
        this.iLoginPageActivityView = iLoginPageActivityView;
    }

    @Override
    public void initTaskManager() {
        try {
            ApplicationLogger.logging(LogLevel.INFORMATION, "A feladatkezelő létrehozása megkezdődött.");

            mUiHandler = new LoginPageActivityPresenter.UiHandler(Looper.myLooper(), iLoginPageActivityView, this);
            mCustomThreadPoolManager = CustomThreadPoolManager.getsInstance();
            mCustomThreadPoolManager.setPresenterCallback(this);

            ApplicationLogger.logging(LogLevel.INFORMATION, "A feladatkezelő létrehozása befejeződött.");
        }
        catch (Exception e){
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void loadPage(PageEnums pageEnums) {
        iLoginPageActivityView.loadOtherActivityPages(pageEnums);
    }

    @Override
    public void sendResultToPresenter(Message message) {
        if(mUiHandler == null) return;
        mUiHandler.sendMessage(message);
    }

    private static class UiHandler extends Handler {

        private WeakReference<ILoginPageActivityView> iLoginPageActivityViewWeakReference;
        private WeakReference<ILoginPageActivityPresenter> iLoginPageActivityPresenterWeakReference;

        public UiHandler(Looper looper, ILoginPageActivityView iLoginPageActivityView, ILoginPageActivityPresenter iLoginPageActivityPresenter) {
            super(looper);
            this.iLoginPageActivityViewWeakReference = new WeakReference<>(iLoginPageActivityView);
            this.iLoginPageActivityPresenterWeakReference = new WeakReference<>(iLoginPageActivityPresenter);
        }

        // Ui-ra szánt üzenetet kezelejük itt
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case Util.PROCESS_FINISH_1:{
                    //iLoginPageActivityViewWeakReference.get().settingUiElementsVisibility(UiElementsEnums.READY_STATE_2);
                    //iLoginPageActivityPresenterWeakReference.get().initMasterDataProcess();
                    break;
                }
                case Util.PROCESS_FINISH_2:{
                    //iLoginPageActivityViewWeakReference.get().settingUiElementsVisibility(UiElementsEnums.READY_STATE_3);
                    //iLoginPageActivityPresenterWeakReference.get().initFinishProcess();
                    break;
                }
                case Util.PROCESS_FINISH_3:{
                    //iLoginPageActivityViewWeakReference.get().settingUiElementsVisibility(UiElementsEnums.READY_STATE_4);
                    //iLoginPageActivityPresenterWeakReference.get().loadOtherActivityPages(PageEnums.BARCODE_LOGINPAGE_ACTIVITY);
                    break;
                }
                case Util.ROOM_SEND_FAIL:{
                    //iLoginPageActivityViewWeakReference.get().refreshUiWithMessage("Hiba történt a lokális adatbázis feltöltése során!");
                    break;
                }
                case Util.ROOM_CREATE_FAIL:{
                    //iLoginPageActivityViewWeakReference.get().refreshUiWithMessage("Hiba történt a lokális adatbázis létrehozása során!");
                    break;
                }
                default:
                    break;
            }
        }
    }
}
