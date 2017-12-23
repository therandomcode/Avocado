package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.RatingBar;

public class ViewProfile extends AppActivity implements DataReceived {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        setDefaultView();

        DatabaseHandler db = new DatabaseHandler(this);
        db.getTransporter(getIntent().getStringExtra("transporternumber"));
    }

    private void setDefaultView(){
        //TextView short_bio = findViewById(R.id.user_profile_short_bio);
        TextView phoneNumber = findViewById(R.id.user_profile_phoneNumber);
        TextView address = findViewById(R.id.user_profile_address);
        //short_bio.setVisibility(View.VISIBLE);
        phoneNumber.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
    }

    public void Success(String response){

        String name = "";
        String rating = "";
        String address = "";
        String phonenumber = "";
        String country = "";
        String city = "";
        String postalcode = "";

        try {
            JSONArray avail = new JSONArray(response);
            JSONObject transporter = avail.getJSONObject(0);
            name = (String)transporter.get("firstname")+" "+(String)transporter.get("lastname");
            rating = (String)transporter.get("ratings");
            address = (String)transporter.get("address");
            phonenumber = (String)transporter.get("phonenumber");
            country = (String)transporter.get("country");
            city = (String)transporter.get("city");
            postalcode = (String)transporter.get("postalcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView tname = (TextView)findViewById(R.id.user_profile_name);
        TextView tphonenunmber = (TextView)findViewById(R.id.user_profile_phoneNumber);
        TextView taddress = (TextView)findViewById(R.id.user_profile_address);
        TextView tcity = (TextView)findViewById(R.id.user_profile_city);
        TextView tcountry = (TextView)findViewById(R.id.user_profile_country);
        TextView tpostalcode = (TextView)findViewById(R.id.user_profile_postalcode);

        findViewById(R.id.user_profile_photo).bringToFront();
        tname.setText(name);
        tphonenunmber.setText(phonenumber);
        taddress.setText(address);
        tcity.setText(city);
        tcountry.setText(country);
        tpostalcode.setText(postalcode);

        RatingBar rb = (RatingBar)findViewById(R.id.ratingBar);
        rb.setClickable(false);
        rb.setRating(Float.parseFloat(rating));

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
