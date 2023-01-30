package hu.unideb.inf.globus_komissio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hu.unideb.inf.globus_komissio.R;
import hu.unideb.inf.globus_komissio.activities.interfaces.ILoginPageActivityView;

public class BarcodeLoginPageActivity extends AppCompatActivity implements ILoginPageActivityView {

    private Button readyButton;
    private Button deleteButton;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_login_page);
        initUiElements();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText.getRootView(), InputMethodManager.SHOW_FORCED);
        editText.requestFocus();
        editText.setShowSoftInputOnFocus(true);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String barcodeInput = editText.getText().toString();

                if(!barcodeInput.equals("")){
                    readyButton.setEnabled(true);
                    deleteButton.setEnabled(true);

                    readyButton.setBackgroundResource(R.drawable.button_blue_background);
                    deleteButton.setBackgroundResource(R.drawable.button_blue_background);
                }
                else {
                    readyButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    readyButton.setBackgroundResource(R.drawable.button_gray_background);
                    deleteButton.setBackgroundResource(R.drawable.button_gray_background);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initUiElements() {
        readyButton = findViewById(R.id.readyButton);
        deleteButton = findViewById(R.id.deleteButton);

        editText = findViewById(R.id.barcodeTextBox);

        readyButton.setEnabled(false);
        deleteButton.setEnabled(false);
        readyButton.setBackgroundResource(R.drawable.button_gray_background);
        deleteButton.setBackgroundResource(R.drawable.button_gray_background);
    }
}