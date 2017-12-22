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

        //initiates the request pickup sequence, passing in the phonenumber so the account info
        //is known
        final Button pickupButton = findViewById(R.id.farmerHomeRequestPickupButton);
        pickupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerBeginRequestPickup.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        //initiates the profile screen, passing in the phonenumber so the account info
        //is known
        final Button profileButton = findViewById(R.id.farmerHomeProfileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerEditProfile.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        //initiates the messages screen, passing in the phonenumber so the account info
        //is known
        final Button messagesButton = findViewById(R.id.farmerHomeMessagesButton);
        messagesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerMessages.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        //returns to the login/sign up screen, passing in the phonenumber so the account info
        //is known
        final Button signOutButton = findViewById(R.id.farmerHomeSignOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, BeginSignUp.class);
                startActivity(myIntent);
            }
        });
    }
}
