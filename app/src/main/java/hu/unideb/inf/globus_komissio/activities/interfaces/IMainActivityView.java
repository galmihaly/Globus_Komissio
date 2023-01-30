package hu.unideb.inf.globus_komissio.activities.interfaces;

import hu.unideb.inf.globus_komissio.Enums.UiElementsEnums;

public interface IMainActivityView {
    void refreshUiWithMessage(String message);
    void refreshUiWithObject(Object result);
    public void settingUiElementsVisibility(UiElementsEnums uiElementsEnums);
    public void initUiElements();
}
