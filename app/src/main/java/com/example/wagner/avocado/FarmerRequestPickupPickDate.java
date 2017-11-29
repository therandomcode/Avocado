package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.view.View;
import android.widget.CheckBox;

public class FarmerRequestPickupPickDate extends AppCompatActivity {

    DatePicker picker;
    CheckBox AMCheckBox;
    CheckBox PMCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_pick_date);

    final Button nextButton = findViewById(R.id.FarmerRequestPickupDateNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupPickDate.this, FarmerRequestPickupSetPickupLocation.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.FarmerRequestPickupDateBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupPickDate.this, FarmerBeginRequestPickup.class);
                startActivity(myIntent);
            }
        });
    }
}
