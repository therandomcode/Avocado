package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

public class TransporterViewSchedule extends AppCompatActivity {

    ListView lst;
    String[] transportername={"Juan","Ricardo","Davíd"};
    String[] time={"1996 Toyota Tacoma","2002 Nissan Navara","2000 Agrale Marrua"};
    Integer[] imgid ={R.drawable.bgavocado,R.drawable.bgavocado,R.drawable.bgavocado};

    /*
     * hardcoded farmers to populate schedule
     * TODO have the database populate the schedule based on how many requests the transporter
     * TODO must complete
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_view_schedule);

        lst= findViewById(R.id.listview);
        TransporterViewScheduleCustomListView customListview = new TransporterViewScheduleCustomListView(this,transportername,time,imgid);
        lst.setAdapter(customListview);

        final Button setAvailabilityButton = findViewById(R.id.transporterViewScheduleSetAvailabilityButton);
        setAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterViewSchedule.this,
                        TransporterSetAvailabilityLocation.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterViewScheduleBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterViewSchedule.this,
                        TransporterHome.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        /*
         * TODO have the toggles filter the database accordingly to display all the farmer requests
         * TODO in the selected timeframe
         */

        final ToggleButton todayButton = findViewById(R.id.transporterViewScheduleTodayButton);
        final ToggleButton tomorrowButton = findViewById(R.id.transporterViewScheduleTomorrowButton);
        final ToggleButton nextWeekButton = findViewById(R.id.transporterViewScheduleNextWeekButton);

        todayButton.setChecked(true);
        tomorrowButton.setChecked(false);
        nextWeekButton.setChecked(false);

        todayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tomorrowButton.setChecked(false);
                    nextWeekButton.setChecked(false);
                }
                else {
                    if (!tomorrowButton.isChecked() && !nextWeekButton.isChecked())
                        todayButton.setChecked(true);
                }
            }
        });

        tomorrowButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    todayButton.setChecked(false);
                    nextWeekButton.setChecked(false);
                }
                else {
                    if (!todayButton.isChecked() && !nextWeekButton.isChecked())
                        tomorrowButton.setChecked(true);
                }
            }
        });

        nextWeekButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tomorrowButton.setChecked(false);
                    todayButton.setChecked(false);
                }
                else {
                    if (!tomorrowButton.isChecked() && !todayButton.isChecked())
                        nextWeekButton.setChecked(true);
                }
            }
        });

    }
}
