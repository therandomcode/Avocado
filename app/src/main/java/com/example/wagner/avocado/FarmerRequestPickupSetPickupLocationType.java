package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupSetPickupLocationType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_set_pickup_location_type);

        final Button button = findViewById(R.id.farmerRequestPickupSetPickupLocationTypeNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupSetPickupLocationType.this,
                        FarmerRequestPickupSetDropoffLocation.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }
}
