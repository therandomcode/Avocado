package com.example.wagner.avocado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

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
    }
}
