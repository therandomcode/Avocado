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
    String[] time={"Tuesday, 2 October","Thursday, 4 October","Tuesday, 2 October"};
    Integer[] imgid ={R.drawable.arka,R.drawable.cecilia,R.drawable.raza};
    String[] price={"150 COP", "130 COP", "200 COP"};
    String[] detail={"2 tonnes of bananas", "3 tonnes of avocados", "2 tonnes of avocados"};
    String[] locationfrom ={"from Tony in Santa Cruz", "from Vaterme in La Paz", "from Adiffere in La Paz"};
    String[] locationto ={"to Pedro in La Paz", "to Miguel in La Paz", "to Abigail in Santa Cruz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_view_schedule);

        lst= findViewById(R.id.listview);
        TransporterViewScheduleCustomListView customListview = new TransporterViewScheduleCustomListView(this,transportername,time,imgid,price,locationfrom,locationto,detail);
        lst.setAdapter(customListview);

        final Button setAvailabilityButton = findViewById(R.id.transporterViewScheduleSetAvailabilityButton);
        setAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterViewSchedule.this,
                        TransporterSetAvailabilityLocationType.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterViewScheduleBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterViewSchedule.this,
                        TransporterHome.class);
                startActivity(myIntent);
            }
        });

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
