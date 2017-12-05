package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FarmerEditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_edit_profile);
        setDefaultView();
        final Button editProfileButton = findViewById(R.id.farmerEditProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setEditorView();
            }
        });

        final Button historyButton = findViewById(R.id.farmerHistoryButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerEditProfile.this, FarmerHistory.class);
                startActivity(myIntent);
            }
        });

        final Button saveChangesButton = findViewById(R.id.farmerSaveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writeChanges();
                setDefaultView();
            }
        });

        final Button backButton = findViewById(R.id.farmerProfileBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnHomeIntent = new Intent(FarmerEditProfile.this, FarmerHome.class);
                startActivity(returnHomeIntent);
            }
        });

    }

    private void setDefaultView(){
        Button editProfile = findViewById(R.id.farmerEditProfileButton);
        Button saveChanges = findViewById(R.id.farmerSaveChangesButton);
        TextView short_bio = findViewById(R.id.user_profile_short_bio);
        TextView phoneNumber = findViewById(R.id.user_profile_phoneNumber);
        TextView address = findViewById(R.id.user_profile_address);
        EditText edit_short_bio = findViewById(R.id.profile_edit_short_bio);
        EditText edit_phoneNumber = findViewById(R.id.profile_edit_phoneNumber);
        EditText edit_address = findViewById(R.id.profile_edit_address);
        editProfile.setVisibility(View.VISIBLE);
        saveChanges.setVisibility(View.GONE);
        short_bio.setVisibility(View.VISIBLE);
        phoneNumber.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
        edit_short_bio.setVisibility(View.GONE);
        edit_phoneNumber.setVisibility(View.GONE);
        edit_address.setVisibility(View.GONE);
    }

    private void setEditorView() {
        Button editProfile = findViewById(R.id.farmerEditProfileButton);
        Button saveChanges = findViewById(R.id.farmerSaveChangesButton);
        TextView short_bio = findViewById(R.id.user_profile_short_bio);
        TextView phoneNumber = findViewById(R.id.user_profile_phoneNumber);
        TextView address = findViewById(R.id.user_profile_address);
        EditText edit_short_bio = findViewById(R.id.profile_edit_short_bio);
        EditText edit_phoneNumber = findViewById(R.id.profile_edit_phoneNumber);
        EditText edit_address = findViewById(R.id.profile_edit_address);
        editProfile.setVisibility(View.GONE);
        saveChanges.setVisibility(View.VISIBLE);
        short_bio.setVisibility(View.GONE);
        phoneNumber.setVisibility(View.GONE);
        address.setVisibility(View.GONE);
        edit_short_bio.setVisibility(View.VISIBLE);
        edit_phoneNumber.setVisibility(View.VISIBLE);
        edit_address.setVisibility(View.VISIBLE);
    }

    private void writeChanges() {
        EditText edit_short_bio = findViewById(R.id.profile_edit_short_bio);
        EditText edit_phoneNumber = findViewById(R.id.profile_edit_phoneNumber);
        EditText edit_address = findViewById(R.id.profile_edit_address);
        String user_bio = edit_short_bio.getText().toString();
        String user_phoneNumber = edit_phoneNumber.getText().toString();
        String user_address = edit_address.getText().toString();

    }
}
