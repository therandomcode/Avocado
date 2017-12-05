package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arkaroy on 12/3/17.
 */

public class Loading  extends AppActivity implements TransporterReceived{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String crop = getIntent().getStringExtra("crop");
        String metric = getIntent().getStringExtra("metric");
        String amount = getIntent().getStringExtra("amount");

        DatabaseHandler db = new DatabaseHandler(this);

        db.getAvailableTransporters(time, date, crop, amount, metric);
    }

    @Override
    public void Success(String response) {
        Intent farmerBeginRequestPickupIntent = new Intent(Loading.this,
                FarmerRequestPickupChooseTransporter.class);

        farmerBeginRequestPickupIntent.putExtra("availability", response);

        String phonenumber = getIntent().getStringExtra("phonenumber");
        farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
        farmerBeginRequestPickupIntent.putExtra
                ("locationtype", getIntent().getStringExtra("locationtype"));
        farmerBeginRequestPickupIntent.putExtra
                ("address", getIntent().getStringExtra("address"));
        farmerBeginRequestPickupIntent.putExtra
                ("date", getIntent().getStringExtra("date"));
        farmerBeginRequestPickupIntent.putExtra
                ("time", getIntent().getStringExtra("time"));
        farmerBeginRequestPickupIntent.putExtra
                ("crop", getIntent().getStringExtra("crop"));
        farmerBeginRequestPickupIntent.putExtra
                ("metric", getIntent().getStringExtra("metric"));
        farmerBeginRequestPickupIntent.putExtra
                ("amount", getIntent().getStringExtra("amount"));
        farmerBeginRequestPickupIntent.putExtra
                ("address", getIntent().getStringExtra("address"));
        farmerBeginRequestPickupIntent.putExtra
                ("country", getIntent().getStringExtra("country"));
        farmerBeginRequestPickupIntent.putExtra
                ("postalcode", getIntent().getStringExtra("postalcode"));
        farmerBeginRequestPickupIntent.putExtra
                ("city", getIntent().getStringExtra("city"));
        Bundle bundle = getIntent().getParcelableExtra("bundle");
        LatLng coords = bundle.getParcelable("coordinates");
        Bundle args = new Bundle();
        args.putParcelable("coordinates", coords);
        farmerBeginRequestPickupIntent.putExtra("bundle", args);
        farmerBeginRequestPickupIntent.putExtra
                ("address2", getIntent().getStringExtra("address2"));
        farmerBeginRequestPickupIntent.putExtra
                ("country2", getIntent().getStringExtra("country2"));
        farmerBeginRequestPickupIntent.putExtra
                ("postalcode2", getIntent().getStringExtra("postalcode2"));
        farmerBeginRequestPickupIntent.putExtra
                ("city2", getIntent().getStringExtra("city2"));
        Bundle bundle2 = getIntent().getParcelableExtra("bundle2");
        LatLng coords2 = bundle.getParcelable("coordinates2");
        Bundle args2 = new Bundle();
        args.putParcelable("coordinates2", coords2);
        farmerBeginRequestPickupIntent.putExtra("bundle2", args2);

        farmerBeginRequestPickupIntent.putExtra
                ("myDate", getIntent().getIntExtra("myDate", 0));
        farmerBeginRequestPickupIntent.putExtra
                ("myMonth", getIntent().getIntExtra("myMonth", 0));
        farmerBeginRequestPickupIntent.putExtra
                ("myAM", getIntent().getBooleanExtra("myAM",false));
        farmerBeginRequestPickupIntent.putExtra
                ("myPM", getIntent().getBooleanExtra("myPM",false));

        startActivity(farmerBeginRequestPickupIntent);
    }
}
