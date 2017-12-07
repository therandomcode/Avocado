package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.*;

public class FarmerRequestPickupChooseTransporter extends AppCompatActivity {

    ListView bestMatch;
    ListView lst;

    PopupWindow popUpWindow;
    LinearLayout mainLayout;
    LinearLayout containerLayout;
    TextView tvMsg;

    String crop, amount, metric, date, startaddress, startcity, startcountry,startpostalcode
            , endaddress, endcity, endcountry, endpostalcode, locationtype, timing;



    ArrayList<Transporter> trans = new ArrayList<Transporter>();
    final ArrayList<String> transportername = new ArrayList<String>();/*={"Juan Felipe","Ricardo Sanchez-Delorio","Davíd de Leon"};*/
    final ArrayList<String> cars = new ArrayList<String>();/*={"1996 Toyota Tacoma","2002 Nissan Navara","2000 Agrale Marrua"};*/
    final ArrayList<String> cities = new ArrayList<String>();/* ={"Cartagena", "Cúcuta", "Santa Marta"};*/
    final ArrayList<Integer> imgid = new ArrayList<>();
    final ArrayList<String> number = new ArrayList<String>();
    //Integer[] imgid ={R.drawable.arka,R.drawable.cecilia,R.drawable.raza};

    private int transporterIndex;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_choose_transporter);

        locationtype = getIntent().getStringExtra("locationtype");
        crop = getIntent().getStringExtra("crop");
        amount = getIntent().getStringExtra("amount");
        metric = getIntent().getStringExtra("metric");
        date = getIntent().getStringExtra("date");
        startaddress = getIntent().getStringExtra("startaddress");
        startcity = getIntent().getStringExtra("startcity");
        startcountry = getIntent().getStringExtra("startcountry");
        startpostalcode = getIntent().getStringExtra("startpostalcode");
        endaddress = getIntent().getStringExtra("endaddress");
        endcity = getIntent().getStringExtra("endcity");
        endcountry = getIntent().getStringExtra("endcountry");
        endpostalcode = getIntent().getStringExtra("endpostalcode");
        timing = getIntent().getStringExtra("time");

        int temp = getIntent().getIntExtra("popup",-1);
        if (temp != -1) {
            findViewById(R.id.popUp).setVisibility(View.VISIBLE);
            transporterIndex = temp;
        }
        else {
            findViewById(R.id.popUp).setVisibility(View.GONE);
        }

        containerLayout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);
        tvMsg = new TextView(this);

        //String locationtype = getIntent().getStringExtra("locationtype");
        String droplocation = getIntent().getStringExtra("droplocation");
        String address = getIntent().getStringExtra("address");
        //String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        //String crop = getIntent().getStringExtra("crop");
        //String metric = getIntent().getStringExtra("metric");
        //String amount = getIntent().getStringExtra("amount");

        final String availability = getIntent().getStringExtra("availability");

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
                phonenumber = (String)x.get("phonenumber");
                transportername.add(firstname + " " + lastname);
                cars.add(carmake);
                cities.add(city);
                imgid.add(R.drawable.arka);
                number.add(phonenumber);
            }
        } catch (JSONException e) {
            System.out.println("Failure");
            e.printStackTrace();
        }


        bestMatch = findViewById(R.id.bestMatchListView);
        String [] transarray = transportername.toArray(new String[transportername.size()]);
        String [] cararray = cars.toArray(new String[cars.size()]);
        Integer [] images = imgid.toArray(new Integer[imgid.size()]);

        FarmerRequestPickupChooseTransporterCustomListView customListview
                = new FarmerRequestPickupChooseTransporterCustomListView(this
                , transarray, cararray, images);
        bestMatch.setAdapter(customListview);

        if (transportername.size() > 0) {
            transportername.remove(0);
            cars.remove(0);
            imgid.remove(0);
        }

        transarray = transportername.toArray(new String[transportername.size()]);
        cararray = cars.toArray(new String[cars.size()]);
        images = imgid.toArray(new Integer[imgid.size()]);

        FarmerRequestPickupChooseTransporterCustomListView secondListview
                = new FarmerRequestPickupChooseTransporterCustomListView(this
                , transarray, cararray, images);

        lst= findViewById(R.id.listview);
        lst.setClickable(true);
        lst.setAdapter(secondListview);

        bestMatch.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                findViewById(R.id.popUp).setVisibility(View.VISIBLE);
                transporterIndex = 0;
            }
        });
        if (transportername.size() > 0) {
            lst.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapter, View v, int position,
                                        long arg3) {
                    transporterIndex = position + 1;
                    findViewById(R.id.popUp).setVisibility(View.VISIBLE);
                }
            });
        }
        final ImageButton closeButton = findViewById(R.id.closePopUpButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.popUp).setVisibility(View.GONE);
            }
        });

        final Button viewProfileButton = findViewById(R.id.acceptButton);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupChooseTransporter.this, ViewProfile.class);

                myIntent.putExtra("transporternumber", number.get(transporterIndex));
                myIntent.putExtra("popup",transporterIndex);
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

        final Button selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupChooseTransporter.this,
                        FarmerRequestPickupReviewOrder.class);
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

                String transporternumber = "";
                try {
                    JSONArray avail = new JSONArray(availability);
                    JSONObject transporter = avail.getJSONObject(transporterIndex);
                    transporternumber = (String)transporter.get("phonenumber");;
                } catch (JSONException e) {
                    System.out.println("Failure");
                    e.printStackTrace();
                }
                String farmernumber = getIntent().getStringExtra("phonenumber");

                String request;


                JSONObject newreq = new JSONObject();
                try {
                    newreq.put("crop", crop);
                    newreq.put("amount", amount);
                    newreq.put("metric", metric);
                    newreq.put("locationtype", locationtype);
                    newreq.put("phonenumberfarmer", farmernumber);
                    newreq.put("phonenumbertransporter", transporternumber);
                    newreq.put("date", date);
                    newreq.put("startaddress", startaddress);
                    newreq.put("startcity", startcity);
                    newreq.put("startcountry", startcountry);
                    newreq.put("startpostalcode", startpostalcode);
                    newreq.put("endaddress", endaddress);
                    newreq.put("endcity", endcity);
                    newreq.put("endcountry", endcountry);
                    newreq.put("endpostalcode", endpostalcode);
                    newreq.put("time", timing);
                    newreq.put("status", "pending");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                request = newreq.toString();


                DatabaseHandler db = new DatabaseHandler();
                db.sendRequest(transporternumber, farmernumber, request);

                startActivity(myIntent);
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
