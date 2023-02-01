package hu.unideb.inf.globus_komissio.activities.interfaces;

import android.content.Intent;

public interface ILoginActivityView {
    void loadOtherActivityPages(Intent intent);
    void sendStringToUiToast(String message);
}
