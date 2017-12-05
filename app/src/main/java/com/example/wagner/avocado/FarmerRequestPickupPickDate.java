package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class FarmerRequestPickupPickDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_pick_date);

        DatePicker datepicker = (DatePicker)findViewById(R.id.datePicker);
        int prevDate = getIntent().getIntExtra("myDate",datepicker.getDayOfMonth());
        int prevMonth = getIntent().getIntExtra("myMonth",datepicker.getMonth());

        boolean AM = getIntent().getBooleanExtra("myAM",false);
        boolean PM = getIntent().getBooleanExtra("myPM",false);

        CheckBox cb1 = (CheckBox)findViewById(R.id.amCheckBox);
        cb1.setChecked(AM);
        CheckBox cb2 = (CheckBox)findViewById(R.id.pmCheckBox);
        cb2.setChecked(PM);

        datepicker.updateDate(datepicker.getYear(),prevMonth,prevDate);


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

                if (!cb1.isChecked() && !cb2.isChecked()) {
                    showToast("Please enter a time when you will be available.");
                }
                else {
                    String date = month+"/"+day+"/"+year;
                    String time;
                    if (am && pm) time = "ALLDAY";
                    else if (am && !pm) time = "AM";
                    else if (pm & !am) time = "PM";
                    else time = "NEVER";

                    Intent myIntent = new Intent(FarmerRequestPickupPickDate.this,
                            FarmerRequestPickupSetPickupLocation.class);
                    String phonenumber = getIntent().getStringExtra("phonenumber");
                    myIntent.putExtra("phonenumber", phonenumber);
                    myIntent.putExtra("date", date);
                    myIntent.putExtra("time", time);
                    myIntent.putExtra("crop", getIntent().getStringExtra("crop"));
                    myIntent.putExtra("metric", getIntent().getStringExtra("metric"));
                    myIntent.putExtra("amount", getIntent().getStringExtra("amount"));
                    //Add whatever needs to be read from this screen to the bundle
                    myIntent.putExtra("myDate", datepicker.getDayOfMonth());
                    myIntent.putExtra("myMonth", datepicker.getMonth());
                    myIntent.putExtra("myAM", am);
                    myIntent.putExtra("myPM", pm);

                    startActivity(myIntent);
                }
            }
        });

        final Button backButton = findViewById(R.id.FarmerRequestPickupDateBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupPickDate.this, FarmerBeginRequestPickup.class);
                myIntent.putExtra("crop", getIntent().getStringExtra("crop"));
                myIntent.putExtra("metric", getIntent().getStringExtra("metric"));
                myIntent.putExtra("amount", getIntent().getStringExtra("amount"));
                startActivity(myIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
