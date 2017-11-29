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

public class TransporterAddTripPickDate extends AppCompatActivity {

    DatePicker picker;
    CheckBox AMCheckBox;
    CheckBox PMCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_add_trip_pick_date);

        final Button finishButton = findViewById(R.id.transporterAddTripFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast();
                Intent myIntent = new Intent(TransporterAddTripPickDate.this, TransporterViewSchedule.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterAddTripBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterAddTripPickDate.this, TransporterAddTripSetDestination.class);
                startActivity(myIntent);
            }
        });
    }

    private void showToast() {
        Toast.makeText(this,
                "Thank you! Farmers in the area will be notified.",
                Toast.LENGTH_LONG).show();
    }
}
