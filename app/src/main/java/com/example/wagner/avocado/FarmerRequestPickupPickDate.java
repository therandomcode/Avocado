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


                DatePicker datepicker = (DatePicker)findViewById(R.id.datePicker);
                String day = Integer.toString(datepicker.getDayOfMonth());
                String month = Integer.toString(datepicker.getMonth()+1);
                String year = Integer.toString(datepicker.getYear());

                CheckBox cb1 = (CheckBox)findViewById(R.id.amCheckBox);
                Boolean am = cb1.isChecked();

                CheckBox cb2 = (CheckBox)findViewById(R.id.pmCheckBox);
                Boolean pm = cb2.isChecked();

                String date = month+"/"+day+"/"+year;
                String time;
                if (am && pm)
                    time = "ALLDAY";
                else if (am && !pm)
                    time = "AM";
                else if (pm & !am)
                    time = "PM";
                else
                    time = "NEVER";

                Intent myIntent = new Intent(FarmerRequestPickupPickDate.this, FarmerRequestPickupSetPickupLocation.class);

                // Bundle myBundle = new Bundle();
                myIntent.putExtra("date", date);
                myIntent.putExtra("time", time);
                myIntent.putExtra("crop", getIntent().getStringExtra("crop"));
                myIntent.putExtra("metric", getIntent().getStringExtra("metric"));
                myIntent.putExtra("amount", getIntent().getStringExtra("amount"));
                //Add whatever needs to be read from this screen to the bundle
                //myBundle.putInt("myDate", picker.getDayOfMonth());
                //myBundle.putBoolean("myAM", AMCheckBox.isChecked());
                //myBundle.putBoolean("myPM", PMCheckBox.isChecked());

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
