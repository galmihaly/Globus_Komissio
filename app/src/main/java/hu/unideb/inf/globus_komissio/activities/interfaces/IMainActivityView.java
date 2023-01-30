package hu.unideb.inf.globus_komissio.activities.interfaces;

import hu.unideb.inf.globus_komissio.Enums.PageEnums;
import hu.unideb.inf.globus_komissio.Enums.UiElementsEnums;

public interface IMainActivityView {
    void refreshUiWithMessage(String message);
    void settingUiElementsVisibility(UiElementsEnums uiElementsEnums);
    void loadOtherActivityPages(PageEnums pageEnums);
}
