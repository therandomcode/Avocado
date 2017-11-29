package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;

public class FarmerBeginRequestPickup extends AppCompatActivity {

    private String[] produceSpinner;
    private String [] metricSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_begin_request_pickup);

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

        final Button nextButton = findViewById(R.id.farmerBeginRequestPickupNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Spinner spinner1 = (Spinner)findViewById(R.id.produceSpinner);
                String crop = spinner1.getSelectedItem().toString();

                Spinner spinner2 = (Spinner)findViewById(R.id.metricSpinner);
                String metric = spinner1.getSelectedItem().toString();

                EditText text1 = (EditText)findViewById(R.id.editText2);
                String amount = text1.getText().toString();

                Intent farmerBeginRequestPickupIntent = new Intent(FarmerBeginRequestPickup.this, FarmerRequestPickupPickDate.class);

                farmerBeginRequestPickupIntent.putExtra("crop", crop);
                farmerBeginRequestPickupIntent.putExtra("metric", metric);
                farmerBeginRequestPickupIntent.putExtra("amount", amount);

                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerBeginRequestPickupBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerBeginRequestPickup.this, FarmerHome.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }
}
