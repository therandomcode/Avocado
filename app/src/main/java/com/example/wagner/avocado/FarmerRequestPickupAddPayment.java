package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class FarmerRequestPickupAddPayment extends AppCompatActivity {

    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_enter_payment);

        //assigns all buttons on the screen to variables
        final Button nextButton = findViewById(R.id.farmerRequestPickupEnterPaymentNextButton);
        final Button backButton = findViewById(R.id.farmerRequestPickupEnterPaymentBackButton);
        final RadioButton cashRadioButton = (RadioButton) findViewById(R.id.cashRadioButton);
        final RadioButton bankAccountRadioButton = (RadioButton) findViewById(R.id.bankAccountRadioButton);
        final RadioButton creditCardRadioButton = (RadioButton) findViewById(R.id.creditCardRadioButton);

        //checks if a radiobutton was selected, if not then pops a toast
        //otherwise, initiates the correct sequence based on the selected payment method
        //also passes through the necessary information to record the transaction in the database
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!creditCardRadioButton.isChecked() && !bankAccountRadioButton.isChecked()
                        && !cashRadioButton.isChecked()) {
                    showToast("Por favor seleccione un método de pago.");
                }
                else {
                    if (creditCardRadioButton.isChecked()) {
                        myIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                        FarmerRequestPickupEnterAnotherPayment.class);
                    } else if (bankAccountRadioButton.isChecked()) {
                        myIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                FarmerRequestPickupAddBankAccount.class);
                    } else {
                        myIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                FarmerRequestPickupOrderConfirmation.class);
                    }
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
            }
        });

        //returns to the review order screen
        //also passes through the necessary information in case user wants to go all the way back
        //and change small details of the pickup
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupAddPayment.this,
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
                startActivity(myIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
