package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpTransporterAddPhotos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_transporter_add_photos);

<<<<<<< HEAD
        final Button nextbutton = findViewById(R.id.signUpTransporterAddPhotosNextButton);
=======
        final Button nextbutton = findViewById(R.id.nextButton);
>>>>>>> e5736c17ae041a9c097c39afdf359096e28435d1
        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterAddPhotos.this, SignUpTransporterAddProfile.class);

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

        final Button backbutton = findViewById(R.id.backButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterAddPhotos.this, SignUpSetCarInfoTransporter.class);
                startActivity(myIntent);
            }
        });

        final Button skipbutton = findViewById(R.id.signUpSetLocationSkipButton);
        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD

                Intent myIntent = new Intent(SignUpTransporterAddPhotos.this, SignUpLater.class);

=======
                Intent myIntent = new Intent(SignUpTransporterAddPhotos.this, TransporterHome.class);
>>>>>>> e5736c17ae041a9c097c39afdf359096e28435d1

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

                DatabaseHandler db = new DatabaseHandler();
                db.insertTransporter(firstname, lastname, "", address, city, postalcode
                        , country, password, phonenumber, carmake, capacity, licenseplatenumber);

                startActivity(myIntent);
            }
        });

    }
}
