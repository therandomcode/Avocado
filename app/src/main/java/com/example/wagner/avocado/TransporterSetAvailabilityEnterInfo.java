package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TransporterSetAvailabilityEnterInfo extends AppCompatActivity {

    private String[] produceSpinner;
    private String [] metricSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_set_availability_enter_info);

        this.produceSpinner = new String[] {
                "Avocados", "Bananas", "Cocoa Beans", "Coffee", "Corn", "Fique", "Oilseed", "Strawberries", "Tobacco"
        };
        Spinner s = (Spinner) findViewById(R.id.produceSpinner);
        ArrayAdapter<String> produceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, produceSpinner);
        s.setAdapter(produceAdapter);

        this.metricSpinner = new String[] {
                "kg", "tons", "2x2x2 foot boxes", "4x4x4 foot boxes", "50x50x50 cm boxes"
        };
        Spinner t = (Spinner) findViewById(R.id.metricSpinner);
        ArrayAdapter<String> metricAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, metricSpinner);
        t.setAdapter(metricAdapter);
        //Back and next navigation buttons

        final Button nextButton = findViewById(R.id.transporterSetAvailabilityEnterInfoNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityEnterInfo.this, TransporterSetAvailabilityPickDate.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterSetAvailabilityEnterInfoBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityEnterInfo.this, TransporterHome.class);
                startActivity(myIntent);
            }
        });
    }
}
