package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class SignUpFarmerAddProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_farmer_add_profile);

        final Button finishButton = findViewById(R.id.signUpFarmerAddPhotosFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast();
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        FarmerHome.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String city = getIntent().getStringExtra("city");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");

                int index = address.indexOf("/");
                String newaddress = address.substring(0,index) + " " + address.substring(index+1);

                DatabaseHandler db = new DatabaseHandler();
                db.insertFarmer(firstname, lastname, phonenumber, password, address, country,
                        postalcode, city, "[]","[]", "N.A.");
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("type", "farmer");

                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpFarmerAddPhotosBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        SignUpFarmerAddPhotos.class);
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
    }
    private void showToast() {
        Toast.makeText(this,
                "Thank you for signing up!",
                Toast.LENGTH_LONG).show();
    }



}
