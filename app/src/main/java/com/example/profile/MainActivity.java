package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";

    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassRepeat;

    private Button btnPickImage, btnRegister;

    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnPassRepeat;

    private RadioGroup rgGender;

    private ConstraintLayout parent;

    private Spinner countriesSpinner;

    private Switch agreementCheck;

    private  Snackbar currentlyShownSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnPickImage.setOnClickListener(v -> openGallery());
        btnRegister.setOnClickListener(v1 -> initRegister());
    }

    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "content://media/internal/images/media"));
        startActivity(intent);
    }


    private void initViews(){
        Log.d(TAG, "initViews: Started");
        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtPassRepeat = findViewById(R.id.edtTxtPassRepeat);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);
        countriesSpinner = findViewById(R.id.spinCountries);
        agreementCheck = findViewById(R.id.agreementSwitch);
    }



    private void initRegister(){
        Log.d(TAG,"initRegister: Started");

        if(validateData()){
            if(agreementCheck.isChecked()){
                showSnackBar();
            }
            else{
                Toast.makeText(this, "You need to agree to the license agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateData(){
        boolean valid = true;
        Log.d(TAG,"validateData: started");
        if(edtTxtName.getText().toString().equals("") || edtTxtName.getText().toString().equals("") || edtTxtEmail.getText().toString().equals("")||edtTxtPassword.getText().toString().equals("")||edtTxtPassRepeat.getText().toString().equals("")){
            valid = false;
        }

        return valid;
    }

    private void showSnackBar(){
        Snackbar snBar = Snackbar.make(parent,"User registered", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", v5 -> {

                });
        snBar.show();
        currentlyShownSnackbar = snBar;
    }
}