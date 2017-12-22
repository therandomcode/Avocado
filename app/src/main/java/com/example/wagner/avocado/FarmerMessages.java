package com.example.wagner.avocado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ListView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class FarmerMessages extends AppCompatActivity {

    ListView lst;
    String[] transportername = {"Felipe los Espadrillas", "Ricardo de Leon", "Davíd Latafundia", "Roberto Santos", "Antonio Cortéz"};
    String[] time = {"Tuesday, 2 October 15:10", "Thursday, 4 October 13:24", "Tuesday, 6 October 15:36", "Monday 10 October 9:30", "Friday 10 October 11:45"};
    Integer[] imgid = {R.drawable.profile, R.drawable.cecilia, R.drawable.maria, R.drawable.arka, R.drawable.andrew};
    String[] status = {"Accepted", "Declined", "In Progress", "Declined", "Declined"};

    private int index;

    /*
     * currently hardcoding the list of farmers that are requesting pickup
     * TODO use the database to only load farmers that have sent a request to the transporter
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_messages);

        ArrayList<String> checktransporters = getIntent().getStringArrayListExtra("transporters");
        ArrayList<String> checktimes = getIntent().getStringArrayListExtra("times");
        ArrayList<String> checkmsg = getIntent().getStringArrayListExtra("messages");
        ArrayList<Integer> checkimages = getIntent().getIntegerArrayListExtra("images");
        if ((checktransporters != null)) {
            transportername = checktransporters.toArray(new String[0]);
            time = checktimes.toArray(new String[0]);
            status = checkmsg.toArray(new String[0]);
            imgid = checkimages.toArray(new Integer[0]);
        }


        lst = findViewById(R.id.farmerMessagesListView);
        FarmerMessagesListView customListview = new FarmerMessagesListView(this,
                transportername, time, status, imgid);
        lst.setAdapter(customListview);

        final Button backButton = findViewById(R.id.farmerMyMessagesBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerMessages.this,
                        FarmerHome.class);
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                startActivity(myIntent);
            }
        });
    }
}