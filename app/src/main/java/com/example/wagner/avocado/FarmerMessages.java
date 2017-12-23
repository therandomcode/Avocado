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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class FarmerMessages extends AppActivity implements DataReceived{

    ListView lst;


    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> fulltimes = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    ArrayList<Integer> imgids = new ArrayList<Integer>();

    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_messages);


        //sets the listview to the hardcoded transporters
        DatabaseHandler db =  new DatabaseHandler(this);
        db.getSchedule(getIntent().getStringExtra("phonenumber"));
    }

    public void Success(String response){


        String name, stat, date, mytime;

        try {
            JSONArray transporters = new JSONArray(response);

            for (int i = 0; i < transporters.length(); i++){
                JSONObject transporter = transporters.getJSONObject(i);
                name = (String)transporter.get("firstname")+" "+(String)transporter.get("lastname");
                stat = (String) transporter.get("status");
                date = (String) transporter.get("date");
                mytime = (String) transporter.get("time");

                names.add(name);
                fulltimes.add(date+" "+mytime);
                status.add(stat);
                imgids.add(R.drawable.bgavocado);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        lst = findViewById(R.id.messagesListView);
        FarmerMessagesListView customListview = new FarmerMessagesListView(this,
                names.toArray(new String[names.size()])
                , fulltimes.toArray(new String[fulltimes.size()])
                , status.toArray(new String[status.size()]));

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