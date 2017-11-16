package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_home);

        //Back and next navigation buttons
        final Button button = findViewById(R.id.farmerHomeRequestPickupButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerBeginRequestPickup.class);
                startActivity(myIntent);
            }
        });

        final Button signOutButton = findViewById(R.id.farmerHomeSignOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, BeginSignUp.class);
                startActivity(myIntent);
            }
        });
    }
}
