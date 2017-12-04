package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private CheckBox farmerBox;
    private CheckBox transporterBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        farmerBox = (CheckBox) findViewById(R.id.Farmer);
        transporterBox = (CheckBox) findViewById(R.id.Transporter);

        final Button logInButton = findViewById(R.id.signUpButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                farmerBox = (CheckBox) findViewById(R.id.Farmer);
                transporterBox = (CheckBox) findViewById(R.id.Transporter);
                Intent myIntent = new Intent(LogIn.this, FarmerHome.class);
                EditText phonenumber = findViewById(R.id.phonenumber);
                EditText password = findViewById(R.id.password);
                String phonenumberString = phonenumber.getText().toString();
                String passwordString = password.getText().toString();
                if (farmerBox.isChecked()) {
                    myIntent.putExtra("type", "farmer");
                }
                else if (transporterBox.isChecked()) {
                    myIntent.putExtra("type", "transporter");
                }
                if ((phonenumberString != null) && !phonenumberString.equals("") &&
                        (passwordString != null) && !phonenumberString.equals("") &&
                        (farmerBox.isChecked() || transporterBox.isChecked())) {
                    startActivity(myIntent);
                }
                else {
                    showToast("Please enter your log in information.");
                }
            }
        });

        final Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(LogIn.this, BeginSignUp.class);
                startActivity(myIntent);
            }
        });
    }

    public void onTransporterClicked(View view) {
        if (farmerBox.isChecked()) {
            farmerBox.setChecked(false);
        }
    }

    public void onFarmerClicked(View view) {
        if (transporterBox.isChecked()) {
            transporterBox.setChecked(false);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
