package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by jeremy on 11/29/17.
 */

public class FarmerMyMessages extends AppCompatActivity {


    ListView lst;
    String[] transportername = {"Felipe los Espadrillas", "Ricardo de Leon", "Davíd Latafundia"};
    String[] time = {"Tuesday, 2 October", "Thursday, 4 October", "Tuesday, 2 October"};
    Integer[] imgid = {R.drawable.bgavocado, R.drawable.bgavocado, R.drawable.bgavocado};
    String[] msg = {"Hi Juan, it's Felipe. I'm going to be late because there is a tree blocking the road. I'm sorry! ", "Hello! Are you still free Friday?", "OK, sounds good. See you then. "};
    String[] delivered = {"delivered", "delivered", "not delivered"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_my_messages);

        lst = findViewById(R.id.messagesListView);
        FarmerHistoryListView customListview = new FarmerHistoryListView(this, transportername, time, imgid, msg, delivered);
        lst.setAdapter(customListview);

        final Button backButton = findViewById(R.id.farmerMyMessagesBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerMyMessages.this,
                        FarmerHome.class);
                startActivity(myIntent);
            }
        });
    }
}
