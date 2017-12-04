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
            }
        });

    }

    private void showToast() {
        Toast.makeText(this, "Great! We will send you a reminder.", Toast.LENGTH_LONG).show();
    }
}
