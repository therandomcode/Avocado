package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class FarmerRequestPickupSetPickupLocationType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_set_pickup_location_type);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        String templocation = getIntent().getStringExtra("locationtype");
        if ( (templocation != null) && !templocation.equals("")) {
            if (templocation.equals("My Farm")) {
                rg.check(R.id.myFarm);
            }
            else if (templocation.equals("Farm")) {
                rg.check(R.id.farm);
            }
            else if (templocation.equals("Town Centre")) {
                rg.check(R.id.townCentre);
            }
            else if (templocation.equals("Church")) {
                rg.check(R.id.church);
            }
            else {
                EditText other = findViewById(R.id.other);
                other.setText(templocation);
            }
        }

        final Button nextButton = findViewById(R.id.farmerRequestPickupSetPickupLocationTypeNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                int id = rg.getCheckedRadioButtonId();
                String locationtype;

                if (id == -1){
                    EditText edittext = (EditText)findViewById(R.id.other);
                    locationtype = edittext.getText().toString();
                }
                else{
                    RadioButton rb = (RadioButton)findViewById(id);
                    locationtype = rb.getText().toString();
                }

                if ( (locationtype != null) && !locationtype.equals("")) {
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

                    farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                            ("myDate", getIntent().getIntExtra("myDate", 0));
                    farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                            ("myMonth", getIntent().getIntExtra("myMonth", 0));
                    farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                            ("myAM", getIntent().getBooleanExtra("myAM",false));
                    farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                            ("myPM", getIntent().getBooleanExtra("myPM",false));

                    startActivity(farmerRequestPickupSetPickupLocationTypeIntent);
                }
                else {
                    showToast("Please select the type of pickup location.");
                }
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

                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("myDate", getIntent().getIntExtra("myDate",0));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("myMonth", getIntent().getIntExtra("myMonth",0));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("myAM", getIntent().getBooleanExtra("myAM",false));
                farmerRequestPickupSetPickupLocationTypeIntent.putExtra
                        ("myPM", getIntent().getBooleanExtra("myPM",false));

                startActivity(farmerRequestPickupSetPickupLocationTypeIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
