package com.example.wagner.avocado;

/**
 * Created by arkaroy on 12/2/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

public class SignUpTransporterAddProfile extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_transporter_add_profile);

        final Button nextbutton = findViewById(R.id.finishButton);
        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterAddProfile.this, TransporterHome.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");
                String city = getIntent().getStringExtra("city");
                String carmake = getIntent().getStringExtra("carmake");
                String licenseplatenumber = getIntent().getStringExtra("licenseplatenumber");
                String capacity = getIntent().getStringExtra("capacity");

                int index = address.indexOf("/");
                String newaddress = address.substring(0,index) + " " + address.substring(index+1);

                DatabaseHandler db = new DatabaseHandler();
                db.insertTransporter(firstname, lastname, "", newaddress, city, postalcode
                        , country, password, phonenumber, carmake, capacity, licenseplatenumber);

                startActivity(myIntent);
            }
        });

        final Button backbutton = findViewById(R.id.backButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterAddProfile.this, SignUpTransporterAddPhotos.class);
                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("address", getIntent().getStringExtra("address"));
                myIntent.putExtra("city", getIntent().getStringExtra("city"));
                myIntent.putExtra("postalcode", getIntent().getStringExtra("postalcode"));
                myIntent.putExtra("country", getIntent().getStringExtra("country"));
                myIntent.putExtra("user", "transporter");
                myIntent.putExtra("carmake", getIntent().getStringExtra("carmake"));
                myIntent.putExtra("licenseplatenumber", getIntent().getStringExtra("licenseplatenumber"));
                myIntent.putExtra("capacity", getIntent().getStringExtra("capacity"));

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);
                startActivity(myIntent);
            }
        });

    }

}
