package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import android.content.Context;
/**
 * Created by arkaroy on 12/3/17.
 */

public class Loading  extends AppCompatActivity implements TransporterReceived{

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

        startActivity(farmerBeginRequestPickupIntent);
    }
}
