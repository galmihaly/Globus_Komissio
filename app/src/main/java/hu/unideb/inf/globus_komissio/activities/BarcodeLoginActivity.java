package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.globus_komissio.databases.models.UserRights;
import hu.unideb.inf.globus_komissio.databases.room.Room;
import hu.unideb.inf.globus_komissio.enums.PageEnums;
import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginActivityView;
import hu.unideb.inf.globus_komissio.activities.presenters.LoginActivityPresenter;

public class BarcodeLoginActivity extends AppCompatActivity implements ILoginActivityView {

    private Button readyButton3;
    private Button deleteButton3;

    // menü gombok
    private ImageButton userPasswordLoginButton3;
    private ImageButton numericPanelButton3;

    private EditText barcodeTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_login_page);

        initUiElements();

        LoginActivityPresenter laPresenter = new LoginActivityPresenter(this, getApplicationContext());

        barcodeTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String barcodeInput = barcodeTextBox.getText().toString();

                if(!barcodeInput.equals("")){
                    readyButton3.setEnabled(true);
                    deleteButton3.setEnabled(true);

                    readyButton3.setBackgroundResource(R.drawable.button_blue_background);
                    deleteButton3.setBackgroundResource(R.drawable.button_blue_background);
                }
                else {
                    readyButton3.setEnabled(false);
                    deleteButton3.setEnabled(false);
                    readyButton3.setBackgroundResource(R.drawable.button_gray_background);
                    deleteButton3.setBackgroundResource(R.drawable.button_gray_background);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userPasswordLoginButton3.setOnClickListener(v -> {
            laPresenter.sendPageEnumToPresenter(PageEnums.USERPASSWORD_LOGINPAGE_ACTIVITY);
        });

        numericPanelButton3.setOnClickListener(v -> {
            laPresenter.sendPageEnumToPresenter(PageEnums.PINCODE_LOGINPAGE_ACTIVITY);
        });

        readyButton3.setOnClickListener(v -> {
            laPresenter.loginWithBarcode(barcodeTextBox.getText().toString());
        });

        deleteButton3.setOnClickListener(v -> {

        });

    }

    @Override
    public void loadOtherActivityPages(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void getClearFromPresenter() { barcodeTextBox.setText(""); }

    @Override
    public void sendStringToUiToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void initUiElements() {
        readyButton3 = findViewById(R.id.readyButton3);
        deleteButton3 = findViewById(R.id.deleteButton3);

        barcodeTextBox = findViewById(R.id.barcodeTextBox);
        barcodeTextBox.requestFocus();

        readyButton3.setEnabled(false);
        deleteButton3.setEnabled(false);
        readyButton3.setBackgroundResource(R.drawable.button_gray_background);
        deleteButton3.setBackgroundResource(R.drawable.button_gray_background);

        userPasswordLoginButton3 = findViewById(R.id.userPasswordLoginButton3);
        numericPanelButton3 = findViewById(R.id.numericPanelButton3);
    }
}