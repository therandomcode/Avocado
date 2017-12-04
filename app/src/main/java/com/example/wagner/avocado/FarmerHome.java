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

        final Button pickupButton = findViewById(R.id.farmerHomeRequestPickupButton);
        pickupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerBeginRequestPickup.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        final Button profileButton = findViewById(R.id.farmerHomeProfileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerEditProfile.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

/*        final Button historyButton = findViewById(R.id.farmerHomeHistoryButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerHistory.class);
                startActivity(myIntent);
            }
        });
*/
        final Button messagesButton = findViewById(R.id.farmerHomeMessagesButton);
        messagesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, FarmerMessages.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        final Button signOutButton = findViewById(R.id.farmerHomeSignOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, CreateAccount.class);
                startActivity(myIntent);
            }
        });

        final Button transporterButton = findViewById(R.id.farmerHomeTransporterButton);
        transporterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHome.this, TransporterHome.class);
                startActivity(myIntent);
            }
        });
    }
}
