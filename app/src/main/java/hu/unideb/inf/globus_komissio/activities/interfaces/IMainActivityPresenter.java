package hu.unideb.inf.globus_komissio.activities.interfaces;

import hu.unideb.inf.globus_komissio.enums.PageEnums;
import hu.unideb.inf.globus_komissio.enums.UiElementsEnums;

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
