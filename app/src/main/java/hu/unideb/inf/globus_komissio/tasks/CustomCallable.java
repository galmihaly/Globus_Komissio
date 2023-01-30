package hu.unideb.inf.globus_komissio.tasks;

import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.User;
import hu.unideb.inf.globus_komissio.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.globus_komissio.tasksmanager.Util;

public class CustomCallable implements Callable {

    private WeakReference<CustomThreadPoolManager> customThreadPoolManagerWeakReference;

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.customThreadPoolManagerWeakReference = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {
            if (Thread.interrupted()) throw new InterruptedException();

            Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
            List<Articles> articlesList = repository.Communicator.getAllArticles();

            Log.e("listaméret:", String.valueOf(articlesList.size()));
            for (int i = 0; i < articlesList.size(); i++) {
                Log.e(String.valueOf(i), String.valueOf(articlesList.get(i)));
            }

            Message message = Util.createMessage(Util.TASKS_CANCELLED, "Sikerült");

            if(customThreadPoolManagerWeakReference != null && customThreadPoolManagerWeakReference.get() != null) {
                customThreadPoolManagerWeakReference.get().sendResultToPresenter(message);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
        List<Articles> articlesList = repository.Communicator.getAllArticles();

        return null;
    }
}
