package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

public class SignUpLater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_later);

        final ToggleButton todayButton = findViewById(R.id.signUpLaterToday);
        final ToggleButton tomorrowButton = findViewById(R.id.signUpLaterTomorrow);
        final ToggleButton weekendButton = findViewById(R.id.signUpLaterWeekend);

        todayButton.setChecked(true);
        tomorrowButton.setChecked(false);
        weekendButton.setChecked(false);

        todayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tomorrowButton.setChecked(false);
                    weekendButton.setChecked(false);
                }
            }
        });

        tomorrowButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    todayButton.setChecked(false);
                    weekendButton.setChecked(false);
                }
            }
        });

        weekendButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    todayButton.setChecked(false);
                    tomorrowButton.setChecked(false);
                }
            }
        });

        final Button button = findViewById(R.id.button_cal_next3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast();
<<<<<<< HEAD
                Intent myIntent = new Intent(SignUpLater.this,
                        FarmerHome.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpLaterBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String prevScreen = getIntent().getStringExtra("screen");
                if (prevScreen != null) {
                    Intent myIntent;
                    if (prevScreen.equals("FarmerSetLocation")) {
                        myIntent = new Intent(SignUpLater.this,
                                SignUpFarmerSetLocation.class);
                    }
                    else if (prevScreen.equals("FarmerAddPhotos")) {
                        myIntent = new Intent(SignUpLater.this,
                                SignUpFarmerAddPhotos.class);
                    }
                    else if (prevScreen.equals("TransporterSetLocation")) {
                        myIntent = new Intent(SignUpLater.this,
                                SignUpTransporterSetLocation.class);
                    }
                    else if (prevScreen.equals("TransporterSetCarInfo")) {
                        myIntent = new Intent(SignUpLater.this,
                                SignUpTransporterSetCarInfo.class);
                    }
                    else {
                        myIntent = new Intent(SignUpLater.this,
                                SignUpTransporterAddPhotos.class);
                    }

                    myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                    myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                    myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                    myIntent.putExtra("address", getIntent().getStringExtra("address"));
                    myIntent.putExtra("city", getIntent().getStringExtra("city"));
                    myIntent.putExtra("postalcode", getIntent().getStringExtra("postalcode"));
                    myIntent.putExtra("country", getIntent().getStringExtra("country"));
                    myIntent.putExtra("user", getIntent().getStringExtra("user"));

                    Bundle bundle = getIntent().getParcelableExtra("bundle");
                    if (bundle != null) {
                        LatLng coords = bundle.getParcelable("coordinates");
                        Bundle args = new Bundle();
                        args.putParcelable("coordinates", coords);
                        myIntent.putExtra("bundle", args);
                    }
                    startActivity(myIntent);
                }
=======
                Intent farmerRequestPickupSetPickupLocationIntent;

                String type = getIntent().getStringExtra("type");
                if (type.equals("farmer")){
                    farmerRequestPickupSetPickupLocationIntent = new Intent(SignUpLater.this,
                            FarmerHome.class);
                }
                else {
                    farmerRequestPickupSetPickupLocationIntent = new Intent(SignUpLater.this,
                            TransporterHome.class);
                }
                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerRequestPickupSetPickupLocationIntent.putExtra("phonenumber", phonenumber);

                startActivity(farmerRequestPickupSetPickupLocationIntent);
>>>>>>> Arka
            }
        });

    }

    private void showToast() {
        Toast.makeText(this, "Great! We will send you a reminder.", Toast.LENGTH_LONG).show();
    }
}
