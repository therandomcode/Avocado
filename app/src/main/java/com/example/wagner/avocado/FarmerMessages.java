package com.example.wagner.avocado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class FarmerMessages extends AppCompatActivity {

    //temporary hardcoded messages from transporters
    ListView lst;
    String[] transportername = {"Felipe los Espadrillas", "Ricardo de Leon", "Davíd Latafundia"};
    String[] time = {"Tuesday, 2 October 15:10", "Thursday, 4 October 13:24", "Tuesday, 6 October 15:36"};
    Integer[] imgid = {R.drawable.maria, R.drawable.arka, R.drawable.profile};
    String[] msg = {"Requests delivery 20 Dec 2017", "Request delivery 14 Jan 2018", "Requests delivery 15 Jan 2018."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_messages);

        //sets the listview to the hardcoded transporters
        lst = findViewById(R.id.messagesListView);
        FarmerMessagesListView customListview = new FarmerMessagesListView(this,
                transportername, time, msg);
        lst.setAdapter(customListview);

        //returns to the sign up/login home screen if cancel is clicked
        final Button backButton = findViewById(R.id.farmerMyMessagesBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerMessages.this, FarmerHome.class);
                myIntent.putExtra("phonenumber",
                        getIntent().getStringExtra("phonenumber"));
                startActivity(myIntent);
            }
        });
    }
}