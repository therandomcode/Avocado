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


        //sets the best match display to the first transporter in the available list
        // TODO make an algorithm based on location, truck capacity, rating, etc. that
        // TODO actually finds a "best match" driver for the farmer
        bestMatch = findViewById(R.id.bestMatchListView);
        String [] transArray = transportername.toArray(new String[transportername.size()]);
        String [] carArray = cars.toArray(new String[cars.size()]);
        Integer [] images = imgid.toArray(new Integer[imgid.size()]);

        String[] newTransArray = new String[1];
        newTransArray[0] = transArray[0];
        String[] newCarArray = new String[1];
        newCarArray[0] = carArray[0];
        Integer[] newImages = new Integer[1];
        newImages[0] = images[0];

        FarmerRequestPickupChooseTransporterCustomListView customListview
                = new FarmerRequestPickupChooseTransporterCustomListView(this
                , newTransArray, newCarArray, newImages);
        bestMatch.setAdapter(customListview);

        //checks to see if there are other drivers to choose from
        //if so, removes the first driver or the current best match and makes a list view of the
        //remaining drivers
        if (transportername.size() > 0) {
            transportername.remove(0);
            cars.remove(0);
            imgid.remove(0);
        }

        transArray = transportername.toArray(new String[transportername.size()]);
        carArray = cars.toArray(new String[cars.size()]);
        images = imgid.toArray(new Integer[imgid.size()]);

        FarmerRequestPickupChooseTransporterCustomListView secondListView
                = new FarmerRequestPickupChooseTransporterCustomListView(this
                , transArray, carArray, images);

        lst = findViewById(R.id.listview);
        lst.setClickable(true);
        lst.setAdapter(secondListView);

        //checks to see if the best match item was selected and returns an index of 0 if it was
        bestMatch.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                findViewById(R.id.popUp).setVisibility(View.VISIBLE);
                transporterIndex = 0;
            }
        });

        //if there are other drivers to browse, it sets the index to the position of the
        //index selected plus 1 since the best match would could as index = 0
        //also displays the popup for the user to view their profile or select them
        if (transportername.size() > 0) {
            lst.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapter, View v, int position,
                                        long arg3) {
                    transporterIndex = position + 1;
                    findViewById(R.id.popUp).setVisibility(View.VISIBLE);
                }
            });
        }

        //closes the popup
        final ImageButton closeButton = findViewById(R.id.closePopUpButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.popUp).setVisibility(View.GONE);
            }
        });

        //takes the user to the profile of the transporter they select
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

        //proceeds to the review order screen and payment sequence and sends a request to the
        //selected transporter
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
                db.sendRequest(crop, amount, metric, locationtype, farmernumber, transporternumber
                                , date, timing, startaddress, startcity, startcountry, startpostalcode
                                , endaddress, endcity, endcountry, endpostalcode, "pending"
                                , "0", "0");

                startActivity(myIntent);
            }
        });

        //returns to the dropoff location screen and passes all previously inputted information
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
