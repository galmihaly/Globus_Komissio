package hu.unideb.inf.globus_komissio.activities.presenters;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

import hu.unideb.inf.globus_komissio.Enums.PageEnums;
import hu.unideb.inf.globus_komissio.LoggerElements.ApplicationLogger;
import hu.unideb.inf.globus_komissio.LoggerElements.LogLevel;
import hu.unideb.inf.globus_komissio.Enums.UiElementsEnums;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityPresenter;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;
import hu.unideb.inf.globus_komissio.tasks.ProcessBaseDatas;
import hu.unideb.inf.globus_komissio.tasks.ProcessFinishTask;
import hu.unideb.inf.globus_komissio.tasks.ProcessMasterDatas;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.PresenterThreadCallback;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class MainActivityPresenter implements IMainActivityPresenter, PresenterThreadCallback {

    private IMainActivityView iMainActivityView;
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private MainActivityHandler mMainActivityHandler;

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

            ProcessFinishTask callable = new ProcessFinishTask();
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
        iMainActivityView.loadOtherActivityPages(pageEnums);
    }

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
                case Util.PROCESS_FINISH_1:{
                    iMainActivityPresenterWeakReference.get().sendUiEnumToPresenter(UiElementsEnums.READY_STATE_2);
                    iMainActivityPresenterWeakReference.get().initMasterDataProcess();
                    break;
                }
                case Util.PROCESS_FINISH_2:{
                    iMainActivityPresenterWeakReference.get().sendUiEnumToPresenter(UiElementsEnums.READY_STATE_3);
                    iMainActivityPresenterWeakReference.get().initFinishProcess();
                    break;
                }
                case Util.PROCESS_FINISH_3:{
                    iMainActivityPresenterWeakReference.get().sendUiEnumToPresenter(UiElementsEnums.READY_STATE_4);
                    iMainActivityPresenterWeakReference.get().sendLoadPageEnum(PageEnums.BARCODE_LOGINPAGE_ACTIVITY);
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
