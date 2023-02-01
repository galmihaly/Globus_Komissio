package hu.unideb.inf.globus_komissio.activities.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

import hu.unideb.inf.globus_komissio.enums.PageEnums;
import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.logger.LogLevel;
import hu.unideb.inf.globus_komissio.enums.UiElementsEnums;
import hu.unideb.inf.globus_komissio.activities.BarcodeLoginActivity;
import hu.unideb.inf.globus_komissio.activities.PincodeLoginActivity;
import hu.unideb.inf.globus_komissio.activities.UserPasswordLoginActivity;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityPresenter;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;
import hu.unideb.inf.globus_komissio.tasks.ProcessBaseDatas;
import hu.unideb.inf.globus_komissio.tasks.ProcessFinish;
import hu.unideb.inf.globus_komissio.tasks.ProcessMasterDatas;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.PresenterThreadCallback;
import hu.unideb.inf.globus_komissio.activities.utils.Util;

public class MainActivityPresenter implements IMainActivityPresenter, PresenterThreadCallback {

    private IMainActivityView iMainActivityView;
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private MainActivityHandler mMainActivityHandler;
    private Context context;

    public MainActivityPresenter(IMainActivityView iMainActivityView, Context context) {
        this.iMainActivityView = iMainActivityView;
        this.context = context;
    }

    @Override
    public void startProgramProcesses() {
        initTaskManager();
        initBaseProcess();
    }

    @Override
    public void initTaskManager(){
        try {
            iMainActivityView.settingUiElementsVisibility(UiElementsEnums.PROGRESS_BAR_1);
            ApplicationLogger.logging(LogLevel.INFORMATION, "A feladatkezelő létrehozása megkezdődött.");

            mMainActivityHandler = new MainActivityHandler(Looper.myLooper(), this);
            mCustomThreadPoolManager = CustomThreadPoolManager.getsInstance();
            mCustomThreadPoolManager.setPresenterCallback(this);

            iMainActivityView.settingUiElementsVisibility(UiElementsEnums.READY_STATE_1);
            ApplicationLogger.logging(LogLevel.INFORMATION, "A feladatkezelő létrehozása befejeződött.");
        }
        catch (Exception e){
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void initBaseProcess(){
        try {
            iMainActivityView.settingUiElementsVisibility(UiElementsEnums.PROGRESS_BAR_2);
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alap folyamat inicializálása elkezdődött.");

            ProcessBaseDatas callable = new ProcessBaseDatas();
            callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
            mCustomThreadPoolManager.addCallableMethod(callable);

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az alap folyamat inicializálása befejeződött.");
        }
        catch (Exception e){
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void initMasterDataProcess() {
        try {
            iMainActivityView.settingUiElementsVisibility(UiElementsEnums.PROGRESS_BAR_3);
            ApplicationLogger.logging(LogLevel.INFORMATION, "Az törzsadat folyamat inicializálása elkezdődött.");

            ProcessMasterDatas callable = new ProcessMasterDatas();
            callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
            mCustomThreadPoolManager.addCallableMethod(callable);

            ApplicationLogger.logging(LogLevel.INFORMATION, "Az törzsadat folyamat inicializálása befejeződött.");
        }
        catch (Exception e){
            e.printStackTrace();
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void initFinishProcess() {
        try {
            iMainActivityView.settingUiElementsVisibility(UiElementsEnums.PROGRESS_BAR_4);
            ApplicationLogger.logging(LogLevel.INFORMATION, "A befejezés folyamat inicializálása elkezdődött.");

            ProcessFinish callable = new ProcessFinish();
            callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
            mCustomThreadPoolManager.addCallableMethod(callable);

            ApplicationLogger.logging(LogLevel.INFORMATION, "A befejezés inicializálása befejeződött.");
        }
        catch (Exception e){
            e.printStackTrace();
            ApplicationLogger.logging(LogLevel.FATAL, e.getMessage());
        }
    }

    @Override
    public void sendUiEnumToPresenter(UiElementsEnums uiElementsEnums) {
        iMainActivityView.settingUiElementsVisibility(uiElementsEnums);
    }

    @Override
    public void sendUiMessageToPresenter(String message) {
        iMainActivityView.refreshUiWithMessage(message);
    }

    @Override
    public void sendLoadPageEnum(PageEnums pageEnums) {
        switch (pageEnums){
            case BARCODE_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(context, BarcodeLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                iMainActivityView.loadOtherActivityPages(intent);
                break;
            }
            case USERPASSWORD_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(context, UserPasswordLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                iMainActivityView.loadOtherActivityPages(intent);
                break;
            }
            case PINCODE_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(context, PincodeLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                iMainActivityView.loadOtherActivityPages(intent);
                break;
            }
            default:
                break;
        }
    }

    //PresenterThreadCallback
    @Override
    public void sendResultToPresenter(Message message) {
        if(mMainActivityHandler == null) return;
        mMainActivityHandler.sendMessage(message);
    }


    private static class MainActivityHandler extends Handler {


        private WeakReference<IMainActivityPresenter> iMainActivityPresenterWeakReference;

        public MainActivityHandler(Looper looper, IMainActivityPresenter iMainActivityPresenter) {
            super(looper);
            this.iMainActivityPresenterWeakReference = new WeakReference<>(iMainActivityPresenter);
        }

        // Ui-ra szánt üzenetet kezelejük itt
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case Util.PROGRAMSTART_FINISH_1:{
                    iMainActivityPresenterWeakReference.get().sendUiEnumToPresenter(UiElementsEnums.READY_STATE_2);
                    iMainActivityPresenterWeakReference.get().initMasterDataProcess();
                    break;
                }
                case Util.PROGRAMSTART_FINISH_2:{
                    iMainActivityPresenterWeakReference.get().sendUiEnumToPresenter(UiElementsEnums.READY_STATE_3);
                    iMainActivityPresenterWeakReference.get().initFinishProcess();
                    break;
                }
                case Util.PROGRAMSATRT_FINISH_3:{
                    iMainActivityPresenterWeakReference.get().sendUiEnumToPresenter(UiElementsEnums.READY_STATE_4);
                    iMainActivityPresenterWeakReference.get().sendLoadPageEnum(PageEnums.PINCODE_LOGINPAGE_ACTIVITY);
                    break;
                }
                case Util.ROOM_SEND_FAIL:{
                    iMainActivityPresenterWeakReference.get().sendUiMessageToPresenter("Hiba történt a lokális adatbázis feltöltése során!");
                    break;
                }
                case Util.ROOM_CREATE_FAIL:{
                    iMainActivityPresenterWeakReference.get().sendUiMessageToPresenter("Hiba történt a lokális adatbázis létrehozása során!");
                    break;
                }
                default:
                    break;
            }
        }
    }
}
