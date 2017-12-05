package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class TransporterBrowseFarmers extends AppCompatActivity {

    ListView lst;
    String[] transportername={"Juan","Ricardo","Davíd"};
    Integer[] imgid ={R.drawable.arka,R.drawable.cecilia,R.drawable.raza};
    String[] detail={"bananas", "avocados", "avocados"};
    String[] locationfrom ={"in Santa Cruz", "in La Paz", "in La Paz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_browse_farmers);

        lst= findViewById(R.id.listview);
        TransporterBrowseFarmersListView customListview = new TransporterBrowseFarmersListView(this,transportername,imgid,locationfrom,detail);
        lst.setAdapter(customListview);
        
        final Button backButton = findViewById(R.id.transporterBrowseFarmersBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterBrowseFarmers.this,
                        TransporterHome.class);
                startActivity(myIntent);
            }
        });

    }
}
