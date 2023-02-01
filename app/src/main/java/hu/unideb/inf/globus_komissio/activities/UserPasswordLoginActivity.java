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

public class UserPasswordLoginActivity extends AppCompatActivity implements ILoginActivityView {

    private Button readyButton1;
    private Button deleteButton1;

    // menü gombok
    private ImageButton numericPanelButton1;
    private ImageButton barcodeButton1;

    private EditText usernameTextBox;
    private EditText passwordTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_password_login_page);
        initUiElements();

        LoginActivityPresenter lap = new LoginActivityPresenter(this, getApplicationContext());

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String barcodeInput = usernameTextBox.getText().toString();
                String pincodeInput = passwordTextBox.getText().toString();

                if(!barcodeInput.equals("") && !pincodeInput.equals("")){
                    readyButton1.setEnabled(true);
                    deleteButton1.setEnabled(true);

                    readyButton1.setBackgroundResource(R.drawable.button_blue_background);
                    deleteButton1.setBackgroundResource(R.drawable.button_blue_background);
                }
                else {
                    readyButton1.setEnabled(false);
                    deleteButton1.setEnabled(false);
                    readyButton1.setBackgroundResource(R.drawable.button_gray_background);
                    deleteButton1.setBackgroundResource(R.drawable.button_gray_background);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        usernameTextBox.addTextChangedListener(textWatcher);
        passwordTextBox.addTextChangedListener(textWatcher);

        barcodeButton1.setOnClickListener(v -> {
            lap.sendPageEnumToPresenter(PageEnums.BARCODE_LOGINPAGE_ACTIVITY);
        });

        numericPanelButton1.setOnClickListener(v -> {
            lap.sendPageEnumToPresenter(PageEnums.PINCODE_LOGINPAGE_ACTIVITY);
        });
    }

    @Override
    public void loadOtherActivityPages(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void getClearFromPresenter() { usernameTextBox.setText(""); }

    @Override
    public void sendStringToUiToast(String message) { Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG); }

    private void initUiElements() {
        readyButton1 = findViewById(R.id.readyButton1);
        deleteButton1 = findViewById(R.id.deleteButton1);

        usernameTextBox = findViewById(R.id.usernameTextBox);
        passwordTextBox = findViewById(R.id.passwordTextBox);
        usernameTextBox.requestFocus();

        readyButton1.setEnabled(false);
        deleteButton1.setEnabled(false);
        readyButton1.setBackgroundResource(R.drawable.button_gray_background);
        deleteButton1.setBackgroundResource(R.drawable.button_gray_background);

        numericPanelButton1 = findViewById(R.id.numericPanelButton1);
        barcodeButton1 = findViewById(R.id.barcodeButton1);
    }
}