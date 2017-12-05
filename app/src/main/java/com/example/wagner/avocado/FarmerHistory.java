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
    Integer[] imgid ={R.drawable.bgavocado,R.drawable.bgavocado,R.drawable.bgavocado};
    String[] price={"150 COP", "130 COP", "200 COP"};
    String[] delivered={"delivered","delivered","not delivered"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_history);

        lst= findViewById(R.id.listview);
        FarmerHistoryListView customListview = new FarmerHistoryListView(this,transportername,time,imgid,price,delivered);
        lst.setAdapter(customListview);



        final Button backButton = findViewById(R.id.farmerHistoryBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerHistory.this,
                        FarmerEditProfile.class);
                startActivity(myIntent);
            }
        });
    }
}
