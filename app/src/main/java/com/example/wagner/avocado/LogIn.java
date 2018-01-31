package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppActivity implements DataReceived {

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

        final Button logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                farmerBox = (CheckBox) findViewById(R.id.Farmer);
                transporterBox = (CheckBox) findViewById(R.id.Transporter);
                
                //checks which box is clicked to determine whether user is trying to log in as a
                //farmer or transporter
                if (farmerBox.isChecked()) {
                    isFarmer = true;
                }
                else if (transporterBox.isChecked()) {
                    isFarmer = false;
                }

                EditText user = (EditText)findViewById(R.id.phonenumber);
                EditText pass = (EditText)findViewById(R.id.password);
                password = pass.getText().toString();
                phone = user.getText().toString();

                if (!phone.equals("") && !pass.equals("")) {
                    if (isFarmer)
                        db.getFarmer(phone);
                    else
                        db.getTransporter(phone);
                }
                else {
                    showToast("Por favor ingrese nombre de usuario y contraseña.");
                }

            }
        });

        //returns to the home screen
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void Success(String response){
        try {
            JSONArray avail = new JSONArray(response);
            if (avail.length() == 0){
                showToast("El nombre de usuario no existe, ¡Por favor, regístrase!");
                Intent myIntent = new Intent(LogIn.this, LogIn.class);
                startActivity(myIntent);
            }
            else
            {
                JSONObject x = avail.getJSONObject(0);
                String realpass = (String) x.get("pass");
                if (realpass.equals(password)){
                    showToast("Bienvenido "+(String)x.get("firstname"));
                    Intent myIntent;
                    if (isFarmer)
                        myIntent = new Intent(LogIn.this, FarmerHome.class);
                    else
                        myIntent = new Intent(LogIn.this, TransporterHome.class);
                    myIntent.putExtra("phonenumber", phone);
                    startActivity(myIntent);
                }
                else {
                    showToast("La contraseña es invalida.");
                    Intent myIntent = new Intent(LogIn.this, LogIn.class);
                    startActivity(myIntent);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
