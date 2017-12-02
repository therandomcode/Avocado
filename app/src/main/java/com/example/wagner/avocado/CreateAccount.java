package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    private CheckBox farmerBox;
    private CheckBox transporterBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        farmerBox = (CheckBox) findViewById(R.id.Farmer);
        transporterBox = (CheckBox) findViewById(R.id.Transporter);

        final Button button = findViewById(R.id.createAccountSubmitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText text2 = (EditText)findViewById(R.id.editText2);
                String name = text2.getText().toString();

                EditText text3 = (EditText)findViewById(R.id.editText3);
                String phonenumber = text3.getText().toString();

                EditText text4 = (EditText)findViewById(R.id.editText4);
                String password = text4.getText().toString();

                farmerBox = (CheckBox) findViewById(R.id.Farmer);
                transporterBox = (CheckBox) findViewById(R.id.Transporter);


                if (farmerBox.isChecked()) {
                    Intent farmerBeginRequestPickupIntent = new Intent(CreateAccount.this,
                            SignUpSetLocation.class);

                    farmerBeginRequestPickupIntent.putExtra("name", name);
                    farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                    farmerBeginRequestPickupIntent.putExtra("password", password);

                    startActivity(farmerBeginRequestPickupIntent);
                }
                else if (transporterBox.isChecked()){
                    Intent farmerBeginRequestPickupIntent = new Intent(CreateAccount.this,
                            SignUpSetLocationTransporter.class);

                    farmerBeginRequestPickupIntent.putExtra("name", name);
                    farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                    farmerBeginRequestPickupIntent.putExtra("password", password);

                    startActivity(farmerBeginRequestPickupIntent);
                }
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
}
