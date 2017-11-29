package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.EditText;

public class FarmerRequestPickupSetPickupLocationType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_set_pickup_location_type);

        final Button nextButton = findViewById(R.id.farmerRequestPickupSetPickupLocationTypeNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                int id = rg.getCheckedRadioButtonId();
                String locationtype;

                if (id == -1){
                    EditText edittext = (EditText)findViewById(R.id.editText);
                    locationtype = edittext.getText().toString();
                }
                else{
                    RadioButton rb = (RadioButton)findViewById(id);
                    locationtype = rb.getText().toString();
                }

                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupSetPickupLocationType.this,
                        FarmerRequestPickupSetDropoffLocation.class);

                farmerBeginRequestPickupIntent.putExtra("locationtype", locationtype);
                farmerBeginRequestPickupIntent.putExtra
                        ("address", getIntent().getStringExtra("address"));
                farmerBeginRequestPickupIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                farmerBeginRequestPickupIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                farmerBeginRequestPickupIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                farmerBeginRequestPickupIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                farmerBeginRequestPickupIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));

                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupSetPickupLocationTypeBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupSetPickupLocationType.this,
                        FarmerRequestPickupSetPickupLocation.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }
}
