package com.example.wagner.avocado;

import android.os.Bundle;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.EditText;

/**
 * Created by arkaroy on 12/2/17.
 */

public class SignUpSetCarInfoTransporter extends AppCompatActivity {

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
                Intent myIntent = new Intent(SignUpSetCarInfoTransporter.this, SignUpTransporterAddPhotos.class);

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


                startActivity(myIntent);
            }
        });

        final Button backbutton = findViewById(R.id.signUpSetCarBackButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetCarInfoTransporter.this, SignUpSetLocationTransporter.class);
                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("address", getIntent().getStringExtra("address"));
                myIntent.putExtra("city", getIntent().getStringExtra("city"));
                myIntent.putExtra("postalcode", getIntent().getStringExtra("postalcode"));
                myIntent.putExtra("country", getIntent().getStringExtra("country"));
                myIntent.putExtra("user", "transporter");
                startActivity(myIntent);
            }
        });

        final Button skipbutton = findViewById(R.id.signUpSetLocationSkipButton);
        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetCarInfoTransporter.this, SignUpLater.class);

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
                        "[]");

                myIntent.putExtra("type", "transporter");
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));

                startActivity(myIntent);
            }
        });


    }
}