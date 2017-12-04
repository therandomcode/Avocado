package com.example.wagner.avocado;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import cz.msebera.android.httpclient.Header;

public class FarmerRequestPickupChooseTransporter extends AppCompatActivity {

    ListView bestMatch;
    ListView lst;

    ArrayList<Transporter> trans = new ArrayList<Transporter>();
    final ArrayList<String> transportername = new ArrayList<String>();/*={"Juan Felipe","Ricardo Sanchez-Delorio","Davíd de Leon"};*/
    final ArrayList<String> cars = new ArrayList<String>();/*={"1996 Toyota Tacoma","2002 Nissan Navara","2000 Agrale Marrua"};*/
    final ArrayList<String> cities = new ArrayList<String>();/* ={"Cartagena", "Cúcuta", "Santa Marta"};*/
    Integer[] imgid ={R.drawable.arka,R.drawable.cecilia,R.drawable.raza};
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_choose_transporter);



        String locationtype = getIntent().getStringExtra("locationtype");
        String droplocation = getIntent().getStringExtra("droplocation");
        String address = getIntent().getStringExtra("address");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String crop = getIntent().getStringExtra("crop");
        String metric = getIntent().getStringExtra("metric");
        String amount = getIntent().getStringExtra("amount");

        String availability = getIntent().getStringExtra("availability");

        String firstname, lastname, available, addresstrans, city, postalcode, country, phonenumber,
                carmake, capacity, licenseplatenumber;
        try {
            JSONArray avail = new JSONArray(availability);

            for (int i = 0; i < avail.length(); i++){
                JSONObject x = avail.getJSONObject(i);

                firstname = (String)x.get("firstname");

                lastname = (String)x.get("lastname");
                carmake = (String)x.get("carmake");
                city = (String)x.get("city");
                transportername.add(firstname + " " + lastname);
                cars.add(carmake);
                cities.add(city);
            }
        } catch (JSONException e) {
            System.out.println("Failure");
            e.printStackTrace();
        }


        bestMatch = findViewById(R.id.bestMatchListView);
        String [] transarray = transportername.toArray(new String[transportername.size()]);
        String [] cararray = cars.toArray(new String[cars.size()]);

        FarmerRequestPickupChooseTransporterCustomListView customListview
                = new FarmerRequestPickupChooseTransporterCustomListView(this
                , transarray, cararray, imgid);
        bestMatch.setAdapter(customListview);

        lst= findViewById(R.id.listview);
        //FarmerRequestPickupChooseTransporterCustomListView customListview = new FarmerRequestPickupChooseTransporterCustomListView(this,transportername,times,imgid);
        lst.setAdapter(customListview);



        final Button button = findViewById(R.id.farmerRequestPickupChooseTransporterNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent
                        = new Intent(FarmerRequestPickupChooseTransporter.this,
                        FarmerRequestPickupReviewOrder.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupChooseTransporterBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupChooseTransporter.this,
                        FarmerRequestPickupSetDropoffLocation.class);

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

                startActivity(myIntent);
            }
        });
    }


}
