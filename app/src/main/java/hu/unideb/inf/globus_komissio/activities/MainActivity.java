package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    public Room room;
    public Button button1;
    public Button button2;

    private List<Articles> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programstart);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
        Room room = Room.getDatabase(getApplicationContext());

        //Kell majd készíteni String TimeStamp konvertert
        // A DATE konverzókat át kell nézni, lecsapódik a date második fele
        // automatikusan növekvő id-k definiálása a modellekhez


        button1.setOnClickListener((view) ->{
            new Thread(()->{
                list = repository.Communicator.getAllArticles();

                runOnUiThread(()->{
                    if(list != null){
                        for (int i = 0; i < list.size(); i++) {
                            Log.e("", list.get(i).toString());
                        }
                    }
                });
            }).start();
        });

        button2.setOnClickListener(v -> {
            new Thread(()->{
                list = repository.Communicator.getAllArticles();

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