package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

public class FarmerHistory extends AppCompatActivity {

    ListView lst;
    String[] transportername={"Juan","Ricardo","Davíd"};
    String[] time={"Tuesday, 2 October","Thursday, 4 October","Tuesday, 2 October"};
    Integer[] imgid ={R.drawable.arka,R.drawable.cecilia,R.drawable.raza};
    String[] price={"150 COP", "130 COP", "200 COP"};
    String[] delivered={"delivered","delivered","not delivered"};
    String[] detail={"2 tonnes of bananas", "3 tonnes of avocados", "2 tonnes of avocados"};
    String[] location ={"to Pedro in La Paz", "to Miguel in La Paz", "to Abigail in La Paz"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_history);

        lst= findViewById(R.id.listview);
        FarmerHistoryListView customListview = new FarmerHistoryListView(this,transportername,time,imgid,price,delivered,detail,location);
        lst.setAdapter(customListview);



        final Button backButton = findViewById(R.id.FarmerHistoryBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHistory.this,
                        FarmerHome.class);
                startActivity(myIntent);
            }
        });

        final Button orderButton = findViewById(R.id.FarmerHistoryPlanOrderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHistory.this,
                        FarmerBeginRequestPickup.class);
                startActivity(myIntent);
            }
        });

        final Button homeButton = findViewById(R.id.FarmerHistoryHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHistory.this,
                        FarmerHome.class);
                startActivity(myIntent);
            }
        });

        final ToggleButton historyButton = findViewById(R.id.FarmerHistoryHistoryButton);
        historyButton.setChecked(true);
    }
}
