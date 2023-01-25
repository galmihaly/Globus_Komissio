package hu.unideb.inf.globus_komissio.tasksmanager;

import android.os.Message;

public interface PresenterThreadCallback {
    void sendResultToPresenter(Message message);
}
