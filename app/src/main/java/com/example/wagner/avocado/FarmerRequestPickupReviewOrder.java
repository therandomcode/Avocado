package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

public class FarmerRequestPickupReviewOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_review_order);

        //proceeds to the add payment screen and passes all relevant info the user had entered
        final Button nextButton = findViewById(R.id.farmerRequestPickupReviewOrderNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupReviewOrder.this,
                        FarmerRequestPickupAddPayment.class);

                myIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra
                        ("locationtype", getIntent().getStringExtra("locationtype"));
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

                //saves the pickup address and LatLng coordinates
                myIntent.putExtra
                        ("startaddress", getIntent().getStringExtra("startaddress"));
                myIntent.putExtra
                        ("startcountry", getIntent().getStringExtra("startcountry"));
                myIntent.putExtra
                        ("startpostalcode", getIntent().getStringExtra("startpostalcode"));
                myIntent.putExtra
                        ("startcity", getIntent().getStringExtra("startcity"));
                Bundle startBundle = getIntent().getParcelableExtra("startbundle");
                LatLng startCoords = startBundle.getParcelable("startcoordinates");
                Bundle startArgs = new Bundle();
                startArgs.putParcelable("startcoordinates", startCoords);
                myIntent.putExtra("startbundle", startArgs);

                //saves the dropoff address and LatLng coordinates
                myIntent.putExtra
                        ("endaddress", getIntent().getStringExtra("endaddress"));
                myIntent.putExtra
                        ("endcountry", getIntent().getStringExtra("endcountry"));
                myIntent.putExtra
                        ("endpostalcode", getIntent().getStringExtra("endpostalcode"));
                myIntent.putExtra
                        ("endcity", getIntent().getStringExtra("endcity"));
                Bundle endBundle = getIntent().getParcelableExtra("endbundle");
                LatLng endCoords = endBundle.getParcelable("endcoordinates");
                Bundle endArgs = new Bundle();
                endArgs.putParcelable("endcoordinates", endCoords);
                myIntent.putExtra("endbundle", endArgs);

                //saves the selected pickup date
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

        //returns to the Loading screen which eventually loads the choose transporter screen
        //passes all relevant info the user previously entered
        final Button backButton = findViewById(R.id.farmerRequestPickupReviewOrderBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupReviewOrder.this,
                        Loading.class);
                myIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra
                        ("locationtype", getIntent().getStringExtra("locationtype"));
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

                //saves the pickup address and LatLng coordinates
                myIntent.putExtra
                        ("startaddress", getIntent().getStringExtra("startaddress"));
                myIntent.putExtra
                        ("startcountry", getIntent().getStringExtra("startcountry"));
                myIntent.putExtra
                        ("startpostalcode", getIntent().getStringExtra("startpostalcode"));
                myIntent.putExtra
                        ("startcity", getIntent().getStringExtra("startcity"));
                Bundle startBundle = getIntent().getParcelableExtra("startbundle");
                LatLng startCoords = startBundle.getParcelable("startcoordinates");
                Bundle startArgs = new Bundle();
                startArgs.putParcelable("startcoordinates", startCoords);
                myIntent.putExtra("startbundle", startArgs);

                //saves the dropoff address and LatLng coordinates
                myIntent.putExtra
                        ("endaddress", getIntent().getStringExtra("endaddress"));
                myIntent.putExtra
                        ("endcountry", getIntent().getStringExtra("endcountry"));
                myIntent.putExtra
                        ("endpostalcode", getIntent().getStringExtra("endpostalcode"));
                myIntent.putExtra
                        ("endcity", getIntent().getStringExtra("endcity"));
                Bundle endBundle = getIntent().getParcelableExtra("endbundle");
                LatLng endCoords = endBundle.getParcelable("endcoordinates");
                Bundle endArgs = new Bundle();
                endArgs.putParcelable("endcoordinates", endCoords);
                myIntent.putExtra("endbundle", endArgs);

                //saves the selected pickup date
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
