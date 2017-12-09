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

        farmerBeginRequestPickupIntent.putExtra
                ("popup", getIntent().getIntExtra("popup",-1));
        farmerBeginRequestPickupIntent.putExtra("availability", response);

        String phonenumber = getIntent().getStringExtra("phonenumber");
        farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
        farmerBeginRequestPickupIntent.putExtra
                ("locationtype", getIntent().getStringExtra("locationtype"));
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
                ("startaddress", getIntent().getStringExtra("startaddress"));
        farmerBeginRequestPickupIntent.putExtra
                ("startcountry", getIntent().getStringExtra("startcountry"));
        farmerBeginRequestPickupIntent.putExtra
                ("startpostalcode", getIntent().getStringExtra("startpostalcode"));
        farmerBeginRequestPickupIntent.putExtra
                ("startcity", getIntent().getStringExtra("startcity"));
        farmerBeginRequestPickupIntent.putExtra
                ("endaddress" , getIntent().getStringExtra("endaddress"));
        farmerBeginRequestPickupIntent.putExtra
                ("endcity", getIntent().getStringExtra("endcity"));
        farmerBeginRequestPickupIntent.putExtra
                ("endcountry", getIntent().getStringExtra("endcountry"));
        farmerBeginRequestPickupIntent.putExtra
                ("endpostalcode", getIntent().getStringExtra("endpostalcode"));

        Bundle bundle = getIntent().getParcelableExtra("startbundle");
        LatLng coords = bundle.getParcelable("startcoordinates");
        Bundle args = new Bundle();
        args.putParcelable("startcoordinates", coords);
        farmerBeginRequestPickupIntent.putExtra("startbundle", args);
        farmerBeginRequestPickupIntent.putExtra
                ("endaddress", getIntent().getStringExtra("endaddress"));
        farmerBeginRequestPickupIntent.putExtra
                ("endcountry", getIntent().getStringExtra("endcountry"));
        farmerBeginRequestPickupIntent.putExtra
                ("endpostalcode", getIntent().getStringExtra("endpostalcode"));
        farmerBeginRequestPickupIntent.putExtra
                ("endcity", getIntent().getStringExtra("endcity"));
        Bundle bundle2 = getIntent().getParcelableExtra("endbundle");
        LatLng coords2 = bundle2.getParcelable("endcoordinates");
        Bundle args2 = new Bundle();
        args2.putParcelable("endcoordinates", coords2);
        farmerBeginRequestPickupIntent.putExtra("endbundle", args2);

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
