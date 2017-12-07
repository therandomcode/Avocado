package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        setDefaultView();

        final Button historyButton = findViewById(R.id.farmerHistoryButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(ViewProfile.this, ProfileHistory.class);
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

        final Button backButton = findViewById(R.id.farmerProfileBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(ViewProfile.this, Loading.class);
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

    private void setDefaultView(){
        //TextView short_bio = findViewById(R.id.user_profile_short_bio);
        TextView phoneNumber = findViewById(R.id.user_profile_phoneNumber);
        TextView address = findViewById(R.id.user_profile_address);
        //short_bio.setVisibility(View.VISIBLE);
        phoneNumber.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
    }

}
