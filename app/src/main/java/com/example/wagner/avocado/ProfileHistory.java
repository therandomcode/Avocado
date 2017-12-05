package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.google.android.gms.maps.model.LatLng;

public class ProfileHistory extends AppCompatActivity {

    ListView lst;
    String[] transportername={"Juan","Ricardo","Davíd"};
    String[] time={"Tuesday, 2 October","Thursday, 4 October","Tuesday, 2 October"};
    Integer[] imgid ={R.drawable.bgavocado,R.drawable.bgavocado,R.drawable.bgavocado};
    String[] price={"150 COP", "130 COP", "200 COP"};
    String[] delivered={"delivered","delivered","not delivered"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_history);

        lst= findViewById(R.id.listview);
        FarmerHistoryListView customListview = new FarmerHistoryListView(this,transportername,time,imgid,price,delivered);
        lst.setAdapter(customListview);

        final Button backButton = findViewById(R.id.profileHistoryBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileHistory.this,
                        ViewProfile.class);
                myIntent.putExtra("popup", getIntent().getIntExtra("popup",0));
                myIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra
                        ("locationtype", getIntent().getStringExtra("locationtype"));
                myIntent.putExtra
                        ("address", getIntent().getStringExtra("address"));
                myIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                myIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                myIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                myIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                myIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));
                myIntent.putExtra
                        ("address", getIntent().getStringExtra("address"));
                myIntent.putExtra
                        ("country", getIntent().getStringExtra("country"));
                myIntent.putExtra
                        ("postalcode", getIntent().getStringExtra("postalcode"));
                myIntent.putExtra
                        ("city", getIntent().getStringExtra("city"));
                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);
                myIntent.putExtra
                        ("address2", getIntent().getStringExtra("address2"));
                myIntent.putExtra
                        ("country2", getIntent().getStringExtra("country2"));
                myIntent.putExtra
                        ("postalcode2", getIntent().getStringExtra("postalcode2"));
                myIntent.putExtra
                        ("city2", getIntent().getStringExtra("city2"));
                Bundle bundle2 = getIntent().getParcelableExtra("bundle2");
                LatLng coords2 = bundle.getParcelable("coordinates2");
                Bundle args2 = new Bundle();
                args.putParcelable("coordinates2", coords2);
                myIntent.putExtra("bundle2", args2);

                myIntent.putExtra
                        ("myDate", getIntent().getIntExtra("myDate", 0));
                myIntent.putExtra
                        ("myMonth", getIntent().getIntExtra("myMonth", 0));
                myIntent.putExtra
                        ("myAM", getIntent().getBooleanExtra("myAM",false));
                myIntent.putExtra
                        ("myPM", getIntent().getBooleanExtra("myPM",false));
                startActivity(myIntent);
            }
        });
    }
}
