package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

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
    private Spinner spinner;

    private List<Articles> list;
    private LiveData<List<Articles>> list2;

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
                            Log.e(String.valueOf(i), list.get(i).toString());
                        }
                    }
                });
            }).start();
        });

        button2.setOnClickListener(v -> {
            new Thread(()->{
                for (int i = 0; i < list.size(); i++) {
                    try {
                        room.articlesDAO().setArticle(list.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        try {
            room.articlesDAO().getAllArticles().observe(this, articles -> {
                for (int i = 0; i < articles.size(); i++) {
                    Log.e(String.valueOf(i), articles.get(i).toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshUiWithMessage(String message) {

    }

    @Override
    public void refreshUiWithObject(Object result) {

    }
}