package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpFarmerAddProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_farmer_add_profile);

        final Button nextButton = findViewById(R.id.signUpFarmerAddPhotosFinishButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        SignUpFarmerAddPhotos.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpFarmerAddPhotosBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        SignUpSetLocation.class);
                startActivity(myIntent);
            }
        });
    }


}
