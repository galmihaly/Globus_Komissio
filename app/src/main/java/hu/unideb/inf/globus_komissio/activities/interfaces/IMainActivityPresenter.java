package hu.unideb.inf.globus_komissio.activities.interfaces;

import hu.unideb.inf.globus_komissio.Enums.PageEnums;
import hu.unideb.inf.globus_komissio.Enums.UiElementsEnums;

public interface IMainActivityPresenter {

    void startProgramProcesses();
    void initTaskManager();
    void initBaseProcess();
    void initMasterDataProcess();
    void initFinishProcess();
    void sendUiEnumToPresenter(UiElementsEnums uiElementsEnums);
    void sendUiMessageToPresenter(String message);
    void sendLoadPageEnum(PageEnums pageEnums);
}
