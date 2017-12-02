package com.example.wagner.avocado;

import android.os.Bundle;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.EditText;

/**
 * Created by arkaroy on 12/2/17.
 */

public class SignUpSetCarInfoTransporter extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_set_car_info_transporter);

        final Button nextbutton = findViewById(R.id.signUpSetCarNextButton);
        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetCarInfoTransporter.this, SignUpTransporterAddPhotos.class);
                startActivity(myIntent);
            }
        });

        final Button backbutton = findViewById(R.id.signUpSetCarBackButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetCarInfoTransporter.this, SignUpSetLocationTransporter.class);
                startActivity(myIntent);
            }
        });

    }
}