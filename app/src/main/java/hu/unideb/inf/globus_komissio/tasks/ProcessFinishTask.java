package hu.unideb.inf.globus_komissio.tasks;

import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.LoggerElements.ApplicationLogger;
import hu.unideb.inf.globus_komissio.LoggerElements.LogLevel;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class ProcessFinishTask implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.ctpmw = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {
            if (Thread.interrupted()) throw new InterruptedException();

            //üzenet a MainActivityPresenter - nek
            Message message = Util.createMessage(Util.PROCESS_FINISH_3, "Jelenleg nincs befejezési folyamat implementálva.");
            if(ctpmw != null && ctpmw.get() != null) {
                ctpmw.get().sendResultToPresenter(message);
            }

        } catch (InterruptedException e) {
            ApplicationLogger.logging(LogLevel.FATAL, "A processzor szál megszakadt.");
        }

        return null;
    }

}
