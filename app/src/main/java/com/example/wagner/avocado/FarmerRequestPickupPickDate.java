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

    final Button button = findViewById(R.id.FarmerRequestPickupDateNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupPickDate.this, FarmerRequestPickupSetPickupLocation.class);
                // Bundle myBundle = new Bundle();

                //Add whatever needs to be read from this screen to the bundle
                //myBundle.putInt("myDate", picker.getDayOfMonth());
                //myBundle.putBoolean("myAM", AMCheckBox.isChecked());
                //myBundle.putBoolean("myPM", PMCheckBox.isChecked());

                //myIntent.putExtras(myBundle);
                startActivity(myIntent);
            }
        });
    }
}
