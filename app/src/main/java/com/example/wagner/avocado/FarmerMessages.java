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

    ListView messages;
    ListView lst;
    String[] transportername = {"Felipe los Espadrillas", "Ricardo de Leon", "Davíd Latafundia"};
    String[] time = {"Tuesday, 2 October 15:10", "Thursday, 4 October 13:24", "Tuesday, 6 October 15:36"};
    Integer[] imgid = {R.drawable.bgavocado, R.drawable.bgavocado, R.drawable.bgavocado};
    String[] msg = {"Requests delivery 20 Dec 2017", "Request delivery 14 Jan 2018", "Requests delivery 15 Jan 2018."};
    //String[] delivered = {"delivered", "delivered", "not delivered"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_messages);

        lst = findViewById(R.id.messagesListView);
        FarmerMessagesListView customListview = new FarmerMessagesListView(this,
                transportername, time, msg);
        lst.setAdapter(customListview);
    }
}