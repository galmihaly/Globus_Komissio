package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import hu.unideb.inf.globus_komissio.enums.PageEnums;
import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginActivityView;
import hu.unideb.inf.globus_komissio.activities.presenters.LoginActivityPresenter;

public class PincodeLoginActivity extends AppCompatActivity implements ILoginActivityView {

    private Button readyButton2;
    private Button deleteButton2;

    // menü gombok
    private ImageButton userPasswordLoginButton2;
    private ImageButton barcodeButton2;

    private EditText pincodeTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeric_login_page);
        initUiElements();

        LoginActivityPresenter lap = new LoginActivityPresenter(this, getApplicationContext());

        pincodeTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pincodeInput = pincodeTextBox.getText().toString();

                if(!pincodeInput.equals("")){
                    readyButton2.setEnabled(true);
                    deleteButton2.setEnabled(true);

                    readyButton2.setBackgroundResource(R.drawable.button_blue_background);
                    deleteButton2.setBackgroundResource(R.drawable.button_blue_background);
                }
                else {
                    readyButton2.setEnabled(false);
                    deleteButton2.setEnabled(false);
                    readyButton2.setBackgroundResource(R.drawable.button_gray_background);
                    deleteButton2.setBackgroundResource(R.drawable.button_gray_background);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userPasswordLoginButton2.setOnClickListener(v -> {
            lap.sendPageEnumToPresenter(PageEnums.USERPASSWORD_LOGINPAGE_ACTIVITY);
        });

        barcodeButton2.setOnClickListener(v -> {
            lap.sendPageEnumToPresenter(PageEnums.BARCODE_LOGINPAGE_ACTIVITY);
        });
    }

    @Override
    public void loadOtherActivityPages(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void sendStringToUiToast(String message) { Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG); }

    private void initUiElements() {
        readyButton2 = findViewById(R.id.readyButton2);
        deleteButton2 = findViewById(R.id.deleteButton2);

        pincodeTextBox = findViewById(R.id.pincodeBox);
        pincodeTextBox.requestFocus();

        readyButton2.setEnabled(false);
        deleteButton2.setEnabled(false);
        readyButton2.setBackgroundResource(R.drawable.button_gray_background);
        deleteButton2.setBackgroundResource(R.drawable.button_gray_background);

        userPasswordLoginButton2 = findViewById(R.id.userPasswordLoginButton2);
        barcodeButton2 = findViewById(R.id.barcodeButton2);
    }
}