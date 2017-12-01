package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpFarmerAddPhotos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_farmer_add_photos);

        final Button finishButton = findViewById(R.id.signUpFarmerAddPhotosFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast();
                Intent myIntent = new Intent(SignUpFarmerAddPhotos.this,
                        FarmerHome.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpFarmerAddPhotosBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddPhotos.this,
                        SignUpSetLocation.class);
                startActivity(myIntent);
            }
        });
    }

    private void showToast() {
        Toast.makeText(this,
                "Thank you for signing up!",
                Toast.LENGTH_LONG).show();
    }
}
