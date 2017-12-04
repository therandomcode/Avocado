package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        setDefaultView();
        final Button historyButton = findViewById(R.id.profileHistoryButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(ViewProfile.this, FarmerHistory.class);
                startActivity(myIntent);
            }
        });

    }

    private void setDefaultView(){
        TextView short_bio = findViewById(R.id.user_profile_short_bio);
        TextView phoneNumber = findViewById(R.id.user_profile_phoneNumber);
        TextView address = findViewById(R.id.user_profile_address);
        short_bio.setVisibility(View.VISIBLE);
        phoneNumber.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
    }

}
