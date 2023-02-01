package hu.unideb.inf.globus_komissio.tasks;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.logger.ApplicationLogger;
import hu.unideb.inf.globus_komissio.enums.LogLevel;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.activities.utils.Util;

public class ProcessUsernamePassword implements Callable {

    private WeakReference<CustomThreadPoolManager> ctpmw;
    private String username;
    private String password;
    private Context context;

    public ProcessUsernamePassword(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
    }

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.ctpmw = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {

            boolean isLogin = false;

            if (Thread.interrupted()) throw new InterruptedException();

            if(Util.isInternetConnection(context)){
                isLogin = getUserFromSQL(username, password);
            }
            else {
                isLogin = getUserFROMRoom(username, password);
            }


        } catch (InterruptedException e) {
            ApplicationLogger.logging(LogLevel.FATAL, "A processzor szál megszakadt.");
        }

        return null;
    }

    private boolean getUserFROMRoom(String username, String password) {
        return true;
    }

    private boolean getUserFromSQL(String username, String password) {
        return true;
    }
}
