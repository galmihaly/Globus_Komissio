package hu.unideb.inf.globus_komissio.activities.presenters;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

import hu.unideb.inf.globus_komissio.LoggerElements.ApplicationLogger;
import hu.unideb.inf.globus_komissio.LoggerElements.LogLevel;
import hu.unideb.inf.globus_komissio.Enums.UiElementsEnums;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityPresenter;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;
import hu.unideb.inf.globus_komissio.tasks.ProcessBaseDatas;
import hu.unideb.inf.globus_komissio.tasks.ProcessMasterDatas;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.PresenterThreadCallback;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class MainActivityPresenter implements IMainActivityPresenter, PresenterThreadCallback {

    private IMainActivityView iMainActivityView;
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private UiHandler mUiHandler;

    public MainActivityPresenter(IMainActivityView iMainActivityView) {
        this.iMainActivityView = iMainActivityView;
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

            mUiHandler = new UiHandler(Looper.myLooper(), iMainActivityView, this);
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
    public void sendResultToPresenter(Message message) {
        if(mUiHandler == null) return;
        mUiHandler.sendMessage(message);
    }


    private static class UiHandler extends Handler {

        private WeakReference<IMainActivityView> iMainActivityViewWeakReference;
        private WeakReference<IMainActivityPresenter> iMainActivityPresenterWeakReference;

        public UiHandler(Looper looper, IMainActivityView iMainActivityView, IMainActivityPresenter iMainActivityPresenter) {
            super(looper);
            this.iMainActivityViewWeakReference = new WeakReference<>(iMainActivityView);
            this.iMainActivityPresenterWeakReference = new WeakReference<>(iMainActivityPresenter);
        }

        // Ui-ra szánt üzenetet kezelejük itt
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case Util.PROCESS_FINISH_1:{
                    iMainActivityViewWeakReference.get().settingUiElementsVisibility(UiElementsEnums.READY_STATE_2);
                    iMainActivityPresenterWeakReference.get().initMasterDataProcess();
                    break;
                }
                case Util.PROCESS_FINISH_2:{
                    iMainActivityViewWeakReference.get().settingUiElementsVisibility(UiElementsEnums.READY_STATE_3);
                    break;
                }
                case Util.ROOM_SEND_FAIL:{
                    iMainActivityViewWeakReference.get().refreshUiWithMessage("Hiba történt a lokális adatbázis feltöltése során!");
                    break;
                }
                case Util.ROOM_CREATE_FAIL:{
                    iMainActivityViewWeakReference.get().refreshUiWithMessage("Hiba történt a lokális adatbázis létrehozása során!");
                    break;
                }
                default:
                    break;
            }
        }
    }
}
