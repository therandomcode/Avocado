package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

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

                Intent farmerRequestPickupSetPickupLocationTypeIntent = new Intent(FarmerRequestPickupSetPickupLocationType.this,
                        FarmerRequestPickupSetDropoffLocation.class);

                farmerRequestPickupSetPickupLocationTypeIntent.putExtra("locationtype", locationtype);
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("address", getIntent().getStringExtra("address"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("country", getIntent().getStringExtra("country"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("postalcode", getIntent().getStringExtra("postalcode"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("city", getIntent().getStringExtra("city"));
                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra("bundle", args);

                startActivity(farmerRequestPickupSetPickupLocationTypeIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupSetPickupLocationTypeBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerRequestPickupSetPickupLocationTypeIntent = new Intent(FarmerRequestPickupSetPickupLocationType.this,
                        FarmerRequestPickupSetPickupLocation.class);

                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("address", getIntent().getStringExtra("address"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("country", getIntent().getStringExtra("country"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("postalcode", getIntent().getStringExtra("postalcode"));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("city", getIntent().getStringExtra("city"));
                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra("bundle", args);
                startActivity(farmerRequestPickupSetPickupLocationTypeIntent);
            }
        });
    }
}
