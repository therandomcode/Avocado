package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DatabaseMetaData;

public class TransporterSetAvailabilityPickDate extends AppActivity implements TransporterReceived {

    DatePicker datepicker;
    String day;
    String month;
    String year;

    CheckBox cb1;
    CheckBox cb2;

    String date,time;

    Boolean am, pm, again;

    String crop = "";
    String amount = "";
    String metric = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_set_availability_pick_date);



        final Button addMoreButton = findViewById(R.id.transporterSetAvailabilityAddMoreButton);
        final TransporterSetAvailabilityPickDate activity = this;
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DatePicker datepicker = (DatePicker)findViewById(R.id.datePicker);
                day = Integer.toString(datepicker.getDayOfMonth());
                month = Integer.toString(datepicker.getMonth()+1);
                year = Integer.toString(datepicker.getYear());

                cb1 = (CheckBox)findViewById(R.id.amCheckBox);
                am = cb1.isChecked();

                cb2 = (CheckBox)findViewById(R.id.pmCheckBox);
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

                showToast2(date, time);
                DatabaseHandler db = new DatabaseHandler(activity);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                db.getTransporter(phonenumber);

                again = true;
            }
        });

        final Button finishButton = findViewById(R.id.transporterSetAvailabilityFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                datepicker = (DatePicker)findViewById(R.id.datePicker);
                day = Integer.toString(datepicker.getDayOfMonth());
                month = Integer.toString(datepicker.getMonth()+1);
                year = Integer.toString(datepicker.getYear());

                cb1 = (CheckBox)findViewById(R.id.amCheckBox);
                am = cb1.isChecked();

                cb2 = (CheckBox)findViewById(R.id.pmCheckBox);
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

                showToast2(date, time);
                showToast();
                DatabaseHandler db = new DatabaseHandler(activity);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                db.getTransporter(phonenumber);

                again = false;
            }
        });

        final Button backButton = findViewById(R.id.transporterSetAvailabilityBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this, TransporterSetAvailabilityLocationType.class);
                startActivity(myIntent);
            }
        });
    }

    private void showToast() {
        Toast.makeText(this,
                "Thank you! Farmers in the area will be notified.",
                Toast.LENGTH_LONG).show();
    }

    private void showToast2(String date, String time){
        Toast.makeText(this,
                "You are available on " + date + " " + time +".",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void Success(String response) {

        try {
            JSONArray avail = new JSONArray(response);
            for (int i = 0; i < avail.length(); i++) {
                //x is the transporter object
                JSONObject x = avail.getJSONObject(i);

                //transavail is the availabilities of transporter
                JSONArray transavail = new JSONArray((String) x.get("availability"));

                //new avail is a new availability
                JSONObject newavail = new JSONObject();
                newavail.put("time", time);
                newavail.put("date", date);
                newavail.put("time", crop);
                newavail.put("time", amount);
                newavail.put("time", metric);
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
                String password = (String)x.get("password");
                String phonenumber = (String)x.get("phonenumber");
                String carmake = (String)x.get("carmake");
                String capacity = (String)x.get("capacity");
                String licenseplatenumber = (String)x.get("licenseplatenumber");
                DatabaseHandler db = new DatabaseHandler();
                db.insertTransporter(firstname, lastname, availability, address, city,
                        postalcode, country, password, phonenumber, carmake, capacity,
                        licenseplatenumber);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (again)
        {
            Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this
                    , TransporterSetAvailabilityPickDate.class);

            startActivity(myIntent);
        }
        else
        {
            Intent myIntent = new Intent(TransporterSetAvailabilityPickDate.this
                    , TransporterViewSchedule.class);

            startActivity(myIntent);
        }

    }
}
