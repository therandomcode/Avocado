package com.example.wagner.avocado;

import android.os.Bundle;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arkaroy on 12/2/17.
 */

public class SignUpTransporterSetCarInfo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_set_car_info_transporter);

        String carMakeTemp = getIntent().getStringExtra("carmake");
        String licensePlateNumberTemp = getIntent().getStringExtra("licenseplatenumber");
        String capacityTemp = getIntent().getStringExtra("capacity");

        EditText car = (EditText)findViewById(R.id.carmake);
        EditText lpn = (EditText)findViewById(R.id.licenseplatenumber);
        EditText cap = (EditText)findViewById(R.id.capacity);

        if ((carMakeTemp != null) && (!carMakeTemp.equals(""))) {
            car.setText(carMakeTemp, TextView.BufferType.EDITABLE);
        }

        if ((licensePlateNumberTemp != null) && (!licensePlateNumberTemp.equals(""))) {
            lpn.setText(licensePlateNumberTemp, TextView.BufferType.EDITABLE);
        }

        if ((capacityTemp != null) && (!capacityTemp.equals(""))) {
            cap.setText(capacityTemp, TextView.BufferType.EDITABLE);
        }

        final Button nextbutton = findViewById(R.id.signUpSetCarNextButton);
        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterSetCarInfo.this, SignUpTransporterAddPhotos.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");
                String city = getIntent().getStringExtra("city");

                EditText edittext1 = (EditText)findViewById(R.id.carmake);
                String carmake = edittext1.getText().toString();

                EditText edittext2 = (EditText)findViewById(R.id.licenseplatenumber);
                String licenseplatenumber = edittext2.getText().toString();

                EditText edittext3 = (EditText)findViewById(R.id.capacity);
                String capacity = edittext3.getText().toString();

                if (!carmake.equals("") && !licenseplatenumber.equals("") && !capacity.equals("")) {
                    myIntent.putExtra("firstname", firstname);
                    myIntent.putExtra("lastname", lastname);
                    myIntent.putExtra("phonenumber", phonenumber);
                    myIntent.putExtra("password", password);
                    myIntent.putExtra("address", address);
                    myIntent.putExtra("country", country);
                    myIntent.putExtra("postalcode", postalcode);
                    myIntent.putExtra("city", city);
                    myIntent.putExtra("carmake", carmake);
                    myIntent.putExtra("licenseplatenumber", licenseplatenumber);
                    myIntent.putExtra("capacity", capacity);

                    Bundle bundle = getIntent().getParcelableExtra("bundle");
                    LatLng coords = bundle.getParcelable("coordinates");
                    Bundle args = new Bundle();
                    args.putParcelable("coordinates", coords);
                    myIntent.putExtra("bundle", args);

                    startActivity(myIntent);
                }
                else {
                    showToast("Please enter information for all of the fields to continue.");
                }
            }
        });

        final Button backbutton = findViewById(R.id.signUpSetCarBackButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterSetCarInfo.this, SignUpTransporterSetLocation.class);
                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("address", getIntent().getStringExtra("address"));
                myIntent.putExtra("city", getIntent().getStringExtra("city"));
                myIntent.putExtra("postalcode", getIntent().getStringExtra("postalcode"));
                myIntent.putExtra("country", getIntent().getStringExtra("country"));
                myIntent.putExtra("user", "transporter");

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);
                startActivity(myIntent);
            }
        });

        final Button skipbutton = findViewById(R.id.signUpSetLocationSkipButton);
        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterSetCarInfo.this, SignUpLater.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");
                String city = getIntent().getStringExtra("city");

                DatabaseHandler db = new DatabaseHandler();
                db.insertTransporter(firstname, lastname, "[]", address, city, postalcode
                        , country, password, phonenumber, "", "", "",
                        "[]", "0", "[]");

                myIntent.putExtra("type", "transporter");
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));

                myIntent.putExtra("firstname", firstname);
                myIntent.putExtra("lastname", lastname);
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putExtra("address", address);
                myIntent.putExtra("city", city);
                myIntent.putExtra("postalcode", postalcode);
                myIntent.putExtra("country", country);
                myIntent.putExtra("user", "transporter");
                myIntent.putExtra("screen", "TransporterSetCarInfo");

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
}