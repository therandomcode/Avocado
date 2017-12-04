package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.EditText;

public class LogIn extends AppActivity implements TransporterReceived{


    private CheckBox farmerBox;
    private CheckBox transporterBox;


    private String password, phone;
    Boolean isFarmer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        final DatabaseHandler db = new DatabaseHandler(this);


        farmerBox = (CheckBox) findViewById(R.id.Farmer);
        transporterBox = (CheckBox) findViewById(R.id.Transporter);

        final Button logInButton = findViewById(R.id.signUpButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                farmerBox = (CheckBox) findViewById(R.id.Farmer);
                transporterBox = (CheckBox) findViewById(R.id.Transporter);
                
                
                if (farmerBox.isChecked()) {
                    isFarmer = true;
                }
                else if (transporterBox.isChecked()) {
                    isFarmer = false;
                }

                //Intent myIntent = new Intent(LogIn.this, FarmerHome.class);

                EditText user = (EditText)findViewById(R.id.phonenumber);
                EditText pass = (EditText)findViewById(R.id.password);
                password = pass.getText().toString();
                phone = user.getText().toString();

                if (!phone.equals(null) && !pass.equals(null)) {
                    if (isFarmer)
                        db.getFarmer(phone);
                    else
                        db.getTransporter(phone);
                }
                else {
                    showToast("Please enter username and password");
                }
                //startActivity(myIntent);

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

    public void Success(String response){
        try {
            JSONArray avail = new JSONArray(response);
            if (avail.length() == 0){
                showToast("Username does not exist, please sign up!");
                Intent myIntent = new Intent(LogIn.this, LogIn.class);
                startActivity(myIntent);
            }
            else
            {
                JSONObject x = avail.getJSONObject(0);
                String realpass = (String) x.get("pass");
                if (realpass.equals(password)){
                    showToast("Welcome "+(String)x.get("firstname"));
                    Intent myIntent = new Intent(LogIn.this, FarmerHome.class);
                    myIntent.putExtra("phonenumber", phone);
                    startActivity(myIntent);
                }
                else {
                    showToast("Invalid password");
                    Intent myIntent = new Intent(LogIn.this, LogIn.class);
                    startActivity(myIntent);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
