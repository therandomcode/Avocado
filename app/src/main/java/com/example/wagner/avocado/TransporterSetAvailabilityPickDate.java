package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.CheckBox;

public class TransporterSetAvailabilityPickDate extends AppCompatActivity {

    DatePicker picker;
    CheckBox AMCheckBox;
    CheckBox PMCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_set_availability_pick_date);

        final Button addMoreButton = findViewById(R.id.transporterSetAvailabilityAddMoreButton);
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this, TransporterSetAvailabilityPickDate.class);
                startActivity(myIntent);
            }
        });

        final Button doneButton = findViewById(R.id.transporterSetAvailabilityDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this, TransporterSetAvailabilityLocationType.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterSetAvailabilityBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this, TransporterViewSchedule.class);
                startActivity(myIntent);
            }
        });
    }
}
