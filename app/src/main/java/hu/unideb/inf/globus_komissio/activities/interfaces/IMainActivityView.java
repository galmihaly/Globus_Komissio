package hu.unideb.inf.globus_komissio.activities.interfaces;

import android.content.Intent;

import hu.unideb.inf.globus_komissio.enums.UiElementsEnums;

public interface IMainActivityView {
    void refreshUiWithMessage(String message);
    void settingUiElementsVisibility(UiElementsEnums uiElementsEnums);
    void loadOtherActivityPages(Intent intent);
}
