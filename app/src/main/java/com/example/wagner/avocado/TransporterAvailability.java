package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class TransporterAvailability extends AppCompatActivity {

    ListView lst;
    String[] time={"Tuesday, 2 October, 10AM","Tuesday, 2 October, 12PM","Tuesday, 2 October, 2PM","Thursday, 4 October, 10AM","Thursday, 4 October, 2PM","Friday, 5 October, 1PM", "Friday, 5 October, 3PM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_availability);

        lst= findViewById(R.id.listview);
        TransporterAvailabilityListView customListview = new TransporterAvailabilityListView(this,time);
        lst.setAdapter(customListview);
        
        final Button backButton = findViewById(R.id.transporterAvailabilityBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterAvailability.this,
                        TransporterHome.class);
                startActivity(myIntent);
            }
        });

    }
}
