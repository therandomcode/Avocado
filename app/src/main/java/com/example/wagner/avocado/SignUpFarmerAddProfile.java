package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpFarmerAddProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_farmer_add_profile);

<<<<<<< HEAD

        final Button finishButton = findViewById(R.id.signUpFarmerAddPhotosFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast();
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        FarmerHome.class);
=======
        final Button nextButton = findViewById(R.id.signUpFarmerAddPhotosFinishButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        SignUpFarmerAddPhotos.class);
>>>>>>> e5736c17ae041a9c097c39afdf359096e28435d1

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String city = getIntent().getStringExtra("city");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");

                myIntent.putExtra("firstname", firstname);
                myIntent.putExtra("lastname", lastname);
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putExtra("password", password);
                myIntent.putExtra("address", address);
                myIntent.putExtra("city", city);
                myIntent.putExtra("country", country);
                myIntent.putExtra("postalcode", postalcode);

                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpFarmerAddPhotosBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddProfile.this,
                        SignUpSetLocation.class);
                startActivity(myIntent);
            }
        });

        final Button skipButton = findViewById(R.id.signUpSetLocationSkipButton);
<<<<<<< HEAD
        skipButton.setOnClickListener(new View.OnClickListener() {
=======
        backButton.setOnClickListener(new View.OnClickListener() {
>>>>>>> e5736c17ae041a9c097c39afdf359096e28435d1
            public void onClick(View v) {
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

                DatabaseHandler db = new DatabaseHandler();
                db.insertFarmer(firstname, lastname, phonenumber, password, address, country,
                        postalcode, city);
<<<<<<< HEAD
=======

>>>>>>> e5736c17ae041a9c097c39afdf359096e28435d1
                startActivity(myIntent);
            }
        });
    }

<<<<<<< HEAD
    private void showToast() {
        Toast.makeText(this, "Thank you for signing up!", Toast.LENGTH_LONG).show();
    }


}
=======

}
>>>>>>> e5736c17ae041a9c097c39afdf359096e28435d1
