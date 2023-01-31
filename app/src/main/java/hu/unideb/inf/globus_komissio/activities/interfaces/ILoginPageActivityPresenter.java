package hu.unideb.inf.globus_komissio.activities.interfaces;

import hu.unideb.inf.globus_komissio.Enums.PageEnums;

public interface ILoginPageActivityPresenter {

    void initTaskManager();
    void loadPage(PageEnums pageEnums);
}
