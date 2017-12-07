package com.example.wagner.avocado;

/**
 * Created by kyleesantos on 12/6/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class TransporterAvailableTimes extends AppCompatActivity {

    ListView lst;
    String[] dates;
    String[] times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_available_times);

        ArrayList<String> datesArrayList = getIntent().getStringArrayListExtra("dates");
        ArrayList<String> timesArrayList = getIntent().getStringArrayListExtra("times");
        dates = datesArrayList.toArray(new String[0]);
        times = timesArrayList.toArray(new String[0]);

        for(int i = 0; i < dates.length; i++)
        {
            String temp = dates[i];
            dates[i] = dates[dates.length - i - 1];
            dates[dates.length - i - 1] = temp;

            String temp2;
            if (times[i].equals("ALLDAY")) { temp2 = "AM/PM"; }
            else { temp2 = times[i]; }
            if (times[times.length - i - 1].equals("ALLDAY")) { times[i] = "AM/PM"; }
            else { times[i] = times[times.length - i - 1]; }
            times[times.length - i - 1] = temp2;
        }

        lst= findViewById(R.id.listview);
        TransporterAvailableTimesListView customListview =
                new TransporterAvailableTimesListView(this,dates,times);
        lst.setAdapter(customListview);

        final Button backButton = findViewById(R.id.transporterAvailableTimesBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterAvailableTimes.this,
                        TransporterSetAvailabilityPickDate.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putStringArrayListExtra("dates",
                        getIntent().getStringArrayListExtra("dates"));
                myIntent.putStringArrayListExtra("times",
                        getIntent().getStringArrayListExtra("times"));
                myIntent.putExtra("bundle",getIntent().getParcelableExtra("bundle"));

                startActivity(myIntent);
            }
        });

        final Button confirmButton = findViewById(R.id.transporterAvailableTimesConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterAvailableTimes.this,
                        TransporterViewSchedule.class);

                showToast("Great! We have set your availability!");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
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

