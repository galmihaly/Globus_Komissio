package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import hu.unideb.inf.globus_komissio.Enums.PageEnums;
import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginPageActivityView;
import hu.unideb.inf.globus_komissio.activities.presenters.LoginPageActivityPresenter;

public class BarcodeLoginPageActivity extends AppCompatActivity implements ILoginPageActivityView {

    private Button readyButton3;
    private Button deleteButton3;

    // menü gombok
    private ImageButton userPasswordLoginButton3;
    private ImageButton numericPanelButton3;
    private ImageButton barcodeButton3;

    private EditText barcodeTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_login_page);
        initUiElements();

        //billentyűzet megjelenítés kell még ide (opcionális)

        LoginPageActivityPresenter lpap = new LoginPageActivityPresenter(this);

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
            lpap.loadPage(PageEnums.USERPASSWORD_LOGINPAGE_ACTIVITY);
        });

        numericPanelButton3.setOnClickListener(v -> {
            Intent intent = new Intent(this, NumericLoginPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }

    @Override
    public void loadOtherActivityPages(PageEnums pageEnums) {

        switch (pageEnums){
            case BARCODE_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(this, BarcodeLoginPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            }
            case USERPASSWORD_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(this, UserPasswordLoginPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            }
            case NUMERIC_LOGINPAGE_ACTIVITY:{

                Intent intent = new Intent(this, NumericLoginPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
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
        barcodeButton3 = findViewById(R.id.barcodeButton3);
    }
}