package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FarmerBeginRequestPickup extends AppCompatActivity {

    private String[] produceSpinner;
    private String [] metricSpinner;
    private EditText produceSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_begin_request_pickup);

        //checks if the produce size was entered before and if so, sets it to the previously
        //entered amount
        produceSize = (EditText)findViewById(R.id.numberOfCrop);
        produceSize.setText(getIntent().getStringExtra("amount"),TextView.BufferType.EDITABLE);

        //sets up the produce spinner with the crops as the fields
        this.produceSpinner = new String[] {
                "Aguacates", "Plátanos", "Cacao", "Café", "Maíz", "Semillas Oleaginosas",
                "Fresas o Frutillas", "Tobaco"
        };
        Spinner s = (Spinner) findViewById(R.id.produceSpinner);
        ArrayAdapter<String> produceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, produceSpinner);
        s.setAdapter(produceAdapter);

        //checks if the crop was entered before and if so, sets it to the previously
        //entered crop
        // TODO doesn't work yet
        String savedProduce = getIntent().getStringExtra("produce");
        if ( (savedProduce != null) && !savedProduce.equals("Avocados")) {
            int position = produceAdapter.getPosition(savedProduce);
            s.setSelection(position);
        }

        //sets up the produce spinner with the crops as the fields
        this.metricSpinner = new String[] {
                "kg", "toneladas", "Cajas 50x50x50 cm", "Cajas 60x60x60 cm", "Cajas 120x120x120 cm"
        };
        Spinner t = (Spinner) findViewById(R.id.metricSpinner);
        ArrayAdapter<String> metricAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, metricSpinner);
        t.setAdapter(metricAdapter);

        //checks if the metric was entered before and if so, sets it to the previously
        //entered metric
        // TODO doesn't work yet
        String savedMetric = getIntent().getStringExtra("metric");
        if ( (savedMetric != null) && !savedMetric.equals("kg")) {
            int position = metricAdapter.getPosition(savedMetric);
            t.setSelection(position);
        }

        //Back and next navigation buttons
        final Button nextButton = findViewById(R.id.farmerBeginRequestPickupNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Spinner spinner1 = (Spinner)findViewById(R.id.produceSpinner);
                String crop = spinner1.getSelectedItem().toString();

                Spinner spinner2 = (Spinner)findViewById(R.id.metricSpinner);
                String metric = spinner2.getSelectedItem().toString();

                produceSize = (EditText)findViewById(R.id.numberOfCrop);
                String amount = produceSize.getText().toString();

                Intent farmerBeginRequestPickupIntent = new Intent
                        (FarmerBeginRequestPickup.this, FarmerRequestPickupPickDate.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                farmerBeginRequestPickupIntent.putExtra("crop", crop);
                farmerBeginRequestPickupIntent.putExtra("metric", metric);
                farmerBeginRequestPickupIntent.putExtra("amount", amount);

                //checks if the fields are all filled in before continuing to next activity
                if (amount.length() == 0) showToast("Por favor ingrese la cantidad de productos que tiene.");
                else startActivity(farmerBeginRequestPickupIntent);
            }
        });

        //returns to the farmer home screen but passes the phonenumber in the intent so the account
        //remains logged in
        final Button backButton = findViewById(R.id.farmerBeginRequestPickupBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent =
                        new Intent(FarmerBeginRequestPickup.this, FarmerHome.class);
                farmerBeginRequestPickupIntent.putExtra("phonenumber",
                        getIntent().getStringExtra("phonenumber"));
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
