package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class TransporterHistory extends AppCompatActivity {

    ListView lst;
    String[] transportername={"Juan","Ricardo","Davíd"};
    String[] time={"Tuesday, 2 October","Thursday, 4 October","Tuesday, 2 October"};
    Integer[] imgid ={R.drawable.arka,R.drawable.cecilia,R.drawable.raza};
    String[] price={"150 COP", "130 COP", "200 COP"};
    String[] delivered={"delivered","delivered","not delivered"};
    String[] detail={"2 tonnes of bananas", "3 tonnes of avocados", "2 tonnes of avocados"};
    String[] locationfrom ={"from Tony in Santa Cruz", "from Vaterme in La Paz", "from Adiffere in La Paz"};
    String[] locationto ={"to Pedro in La Paz", "to Miguel in La Paz", "to Abigail in Santa Cruz"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_history);

        lst= findViewById(R.id.listview);
        TransporterHistoryListView customListview = new TransporterHistoryListView(this,transportername,time,imgid,price,delivered,locationfrom,locationto,detail);
        lst.setAdapter(customListview);
        
        final Button backButton = findViewById(R.id.transporterHistoryBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterHistory.this,
                        TransporterHome.class);
                startActivity(myIntent);
            }
        });

    }
}
