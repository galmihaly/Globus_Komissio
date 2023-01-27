package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.ArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.models.Config;
import hu.unideb.inf.globus_komissio.databases.models.DeviceTypes;
import hu.unideb.inf.globus_komissio.databases.models.Devices;
import hu.unideb.inf.globus_komissio.databases.models.LanguageCodes;
import hu.unideb.inf.globus_komissio.databases.models.LogClasses;
import hu.unideb.inf.globus_komissio.databases.models.LogTypes;
import hu.unideb.inf.globus_komissio.databases.models.Logs;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodeStorages;
import hu.unideb.inf.globus_komissio.databases.models.MovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.PickingItems;
import hu.unideb.inf.globus_komissio.databases.models.PickingItemsLast;
import hu.unideb.inf.globus_komissio.databases.models.PickingStatuses;
import hu.unideb.inf.globus_komissio.databases.models.Pickings;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplateTypes;
import hu.unideb.inf.globus_komissio.databases.models.PrintTemplates;
import hu.unideb.inf.globus_komissio.databases.models.Rights;
import hu.unideb.inf.globus_komissio.databases.models.StorageTypes;
import hu.unideb.inf.globus_komissio.databases.models.Storages;
import hu.unideb.inf.globus_komissio.databases.models.Translations;
import hu.unideb.inf.globus_komissio.databases.models.UserArticleTypes;
import hu.unideb.inf.globus_komissio.databases.models.UserMovementCodes;
import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.models.Users;
import hu.unideb.inf.globus_komissio.databases.models.Version;
import hu.unideb.inf.globus_komissio.databases.models.Workflows;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.fragments.ProgramStartFragment;
import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    public Room room;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programstart);
        button = findViewById(R.id.button);

        Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);

        //Kell majd készíteni String TimeStamp konvertert
        // A DATE konverzókat át kell nézni, lecsapódik a date második fele
        // automatikusan növekvő id-k definiálása a modellekhez

        button.setOnClickListener((view) ->{
            new Thread(()->{
                List<Workflows> list = repository.Communicator.getAllWorkflows();

                runOnUiThread(()->{
                    if(list != null){
                        for (int i = 0; i < list.size(); i++) {
                            Log.e("", list.get(i).toString());
                        }
                    }
                });
            }).start();
        });
    }

    @Override
    public void refreshUiWithMessage(String message) {

    }

    @Override
    public void refreshUiWithObject(Object result) {

    }
}