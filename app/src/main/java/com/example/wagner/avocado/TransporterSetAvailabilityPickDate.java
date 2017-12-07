package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class TransporterSetAvailabilityPickDate extends AppActivity implements TransporterReceived {

    String day;
    String month;
    String year;

    CheckBox cb1;
    CheckBox cb2;

    Bundle bundle, args;

    String date,time, phonenumber;

    Boolean am, pm, again;

    String crop = "";
    String amount = "";
    String metric = "";

    private ArrayList<String> dates;
    private ArrayList<String> times;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_set_availability_pick_date);

        final Button addMoreButton = findViewById(R.id.transporterSetAvailabilityAddMoreButton);
        final TransporterSetAvailabilityPickDate activity = this;
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                cb1 = (CheckBox)findViewById(R.id.amCheckBox);
                cb2 = (CheckBox)findViewById(R.id.pmCheckBox);

                if (!cb1.isChecked() && !cb2.isChecked()) {
                    showToast("Please enter a time when you will be available.");
                }
                else {
                    bundle = getIntent().getParcelableExtra("bundle");
                    LatLng coords = bundle.getParcelable("coordinates");
                    args = new Bundle();
                    args.putParcelable("coordinates", coords);

                    DatePicker datepicker = (DatePicker)findViewById(R.id.datePicker);
                    day = Integer.toString(datepicker.getDayOfMonth());
                    month = Integer.toString(datepicker.getMonth()+1);
                    year = Integer.toString(datepicker.getYear());

                    am = cb1.isChecked();
                    pm = cb2.isChecked();

                    date = month+"/"+day+"/"+year;

                    if (am && pm)
                        time = "ALLDAY";
                    else if (am && !pm)
                        time = "AM";
                    else if (pm & !am)
                        time = "PM";
                    else
                        time = "NEVER";

                    dates = getIntent().getStringArrayListExtra("dates");
                    times = getIntent().getStringArrayListExtra("times");

                    if (dates == null) dates = new ArrayList<>();
                    if (times == null) times = new ArrayList<>();
                    dates.add(date);
                    times.add(time);
                    showToast2(date, time);
                    DatabaseHandler db = new DatabaseHandler(activity);
                    String phonenumber = getIntent().getStringExtra("phonenumber");
                    db.getTransporter(phonenumber);
                }
            }
        });

        final Button finishButton = findViewById(R.id.transporterSetAvailabilityFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast("Thank you! Farmers in the area will be notified.");

                Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this
                        , TransporterAvailableTimes.class);
                phonenumber = getIntent().getStringExtra("phonenumber");
                
                myIntent.putExtra("phonenumber", phonenumber);
                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putStringArrayListExtra("dates",
                        getIntent().getStringArrayListExtra("dates"));
                myIntent.putStringArrayListExtra("times",
                        getIntent().getStringArrayListExtra("times"));
                myIntent.putExtra("bundle", args);
                startActivity(myIntent);

            }
        });

        final Button backButton = findViewById(R.id.transporterSetAvailabilityBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this, TransporterSetAvailabilityLocation.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);
                startActivity(myIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }

    private void showToast2(String date, String time){
        if (time.equals("ALLDAY")) {
            Toast.makeText(this,
                    "You are available on " + date + " AM/PM.",
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,
                    "You are available on " + date + " " + time + ".",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void Success(String response) {

        try {
            System.out.println("THE RESPONSE: "+response);
            JSONArray avail = new JSONArray(response);
            for (int i = 0; i < avail.length(); i++) {
                //x is the transporter object
                JSONObject x = avail.getJSONObject(i);
                System.out.println("Transporter x: "+x.toString());
                //transavail is the availabilities of transporter
                JSONArray transavail = new JSONArray((String) x.get("availability"));

                //new avail is a new availability
                JSONObject newavail = new JSONObject();
                newavail.put("time", time);
                newavail.put("date", date);
                newavail.put("crop", crop);
                newavail.put("amount", amount);
                newavail.put("metric", metric);
                transavail.put(newavail);

                x.put("availability", transavail.toString());
                System.out.println("Transporter: "+x.toString());

                String firstname = (String)x.get("firstname");
                String lastname = (String)x.get("lastname");
                String availability = (String)x.get("availability");
                String address = (String)x.get("address");
                String city = (String)x.get("city");
                String postalcode = (String)x.get("postalcode");
                String country = (String)x.get("country");
                String password = (String)x.get("pass");
                String phonenumber = (String)x.get("phonenumber");
                String carmake = (String)x.get("carmake");
                String capacity = (String)x.get("capacity");
                String licenseplatenumber = (String)x.get("licenseplatenumber");
                String requests = (String)x.get("requests");
                String ratings = (String)x.get("ratings");
                String deliveries = (String)x.get("deliveries");
                DatabaseHandler db = new DatabaseHandler();
                db.insertTransporter(firstname, lastname, availability, address, city,
                        postalcode, country, password, phonenumber, carmake, capacity,
                        licenseplatenumber, requests, ratings, deliveries);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this
                , TransporterSetAvailabilityPickDate.class);
        phonenumber = getIntent().getStringExtra("phonenumber");
        myIntent.putExtra("phonenumber", phonenumber);
        myIntent.putExtra("bundle", args);
        myIntent.putStringArrayListExtra("dates", dates);
        myIntent.putStringArrayListExtra("times", times);
        startActivity(myIntent);
    }
}
