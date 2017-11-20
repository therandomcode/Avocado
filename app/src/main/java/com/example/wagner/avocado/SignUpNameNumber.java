package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpNameNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_name_number);
/*
        final Button signOutButton = findViewById(R.id.signUpNameNumberNextButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpNameNumber.this, SignUpFarmerAddPhotos.class);
                startActivity(myIntent);
            }
        }); */
    }
}
