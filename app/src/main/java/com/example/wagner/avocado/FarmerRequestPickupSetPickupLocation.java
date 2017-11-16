package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupSetPickupLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_set_pickup_location);

        final Button button = findViewById(R.id.farmerRequestPickupSetPickupLocationNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerRequestPickupSetPickupLocationIntent = new Intent(FarmerRequestPickupSetPickupLocation.this,
                        FarmerRequestPickupSetPickupLocationType.class);
                startActivity(farmerRequestPickupSetPickupLocationIntent);
            }
        });
    }
}
