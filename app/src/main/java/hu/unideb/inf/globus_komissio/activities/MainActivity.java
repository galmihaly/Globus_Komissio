package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import hu.unideb.inf.globus_komissio.Enums.UiElementsEnums;
import hu.unideb.inf.globus_komissio.activities.presenters.MainActivityPresenter;
import hu.unideb.inf.globus_komissio.databases.models.Articles;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.globus_komissio.databases.sqldatabase.Repository;
import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView {


    // text objektumok begyűjtése még kell
    // meg kell nézni a ApplicationLogger (indexoutofarray)

    public Room room;
    public Button button2;
    private MainActivityPresenter mainActivityPresenter;
    private ImageView readyState1;
    private ProgressBar progressBar1;
    private ImageView leftArrow1;

    private ImageView readyState2;
    private ProgressBar progressBar2;
    private ImageView leftArrow2;

    private ImageView readyState3;
    private ProgressBar progressBar3;
    private ImageView leftArrow3;

    private ImageView readyState4;
    private ProgressBar progressBar4;
    private ImageView leftArrow4;

    private List<Articles> list;
    private LiveData<List<Articles>> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programstart);
        initUiElements();

        mainActivityPresenter = new MainActivityPresenter(this);

        Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
        Room room = Room.getDatabase(getApplicationContext());

        //Kell majd készíteni String TimeStamp konvertert
        // A DATE konverzókat át kell nézni, lecsapódik a date második fele
        // automatikusan növekvő id-k definiálása a modellekhez


        button2.setOnClickListener(v -> {
            mainActivityPresenter.startProgramProcesses();
        });

    }

    @Override
    public void refreshUiWithMessage(String message) {
        Log.e("", message);
    }

    @Override
    public void refreshUiWithObject(Object result) {

        String s = (String) result;
        Log.e("obj", s);
    }

    @Override
    public void settingUiElementsVisibility(UiElementsEnums uiElementsEnums){

        switch (uiElementsEnums){
            case PROGRESS_BAR_1:{

                leftArrow1.setVisibility(View.INVISIBLE);
                progressBar1.setVisibility(View.VISIBLE);
                readyState1.setVisibility(View.INVISIBLE);
                break;
            }
            case READY_STATE_1:{

                progressBar1.setVisibility(View.INVISIBLE);
                readyState1.setVisibility(View.VISIBLE);
                break;
            }
            case PROGRESS_BAR_2:{

                leftArrow2.setVisibility(View.INVISIBLE);
                progressBar2.setVisibility(View.VISIBLE);
                readyState2.setVisibility(View.INVISIBLE);
                break;
            }
            case READY_STATE_2:{

                progressBar2.setVisibility(View.INVISIBLE);
                readyState2.setVisibility(View.VISIBLE);
                break;
            }
            case PROGRESS_BAR_3:{

                leftArrow3.setVisibility(View.INVISIBLE);
                progressBar3.setVisibility(View.VISIBLE);
                readyState3.setVisibility(View.INVISIBLE);
                break;
            }
            case READY_STATE_3:{

                progressBar3.setVisibility(View.INVISIBLE);
                readyState3.setVisibility(View.VISIBLE);
                break;
            }
            case PROGRESS_BAR_4:{

                leftArrow4.setVisibility(View.INVISIBLE);
                progressBar4.setVisibility(View.VISIBLE);
                readyState4.setVisibility(View.INVISIBLE);
                break;
            }
            case READY_STATE_4:{

                progressBar4.setVisibility(View.INVISIBLE);
                readyState4.setVisibility(View.VISIBLE);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void initUiElements() {
        button2 = findViewById(R.id.button2);

        readyState1 = findViewById(R.id.readyState1);
        readyState2 = findViewById(R.id.readyState2);
        readyState3 = findViewById(R.id.readyState3);
        readyState4 = findViewById(R.id.readyState4);

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);

        leftArrow1 = findViewById(R.id.leftArrow1);
        leftArrow2 = findViewById(R.id.leftArrow2);
        leftArrow3 = findViewById(R.id.leftArrow3);
        leftArrow4 = findViewById(R.id.leftArrow4);
    }
}