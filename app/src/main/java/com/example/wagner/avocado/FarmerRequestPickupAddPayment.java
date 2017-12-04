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
    private boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_enter_payment);

        final Button nextButton = findViewById(R.id.farmerRequestPickupEnterPaymentNextButton);
        Button backButton = findViewById(R.id.farmerRequestPickupEnterPaymentBackButton);
        final RadioButton cashRadioButton = (RadioButton) findViewById(R.id.cashRadioButton);
        final RadioButton bankAccountRadioButton = (RadioButton) findViewById(R.id.bankAccountRadioButton);
        final RadioButton creditCardRadioButton = (RadioButton) findViewById(R.id.creditCardRadioButton);


        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                done = true;
                if (creditCardRadioButton.isChecked()){
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            myIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                    FarmerRequestPickupEnterAnotherPayment.class);
                        }
                    });
                } else if (bankAccountRadioButton.isChecked()){
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            myIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                    FarmerRequestPickupAddBankAccount.class);
                        }
                    });
                } else if (cashRadioButton.isChecked()) {
                    nextButton.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v) {
                            myIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                    FarmerRequestPickupReviewOrder.class);
                        }
                    });
                }
                else {
                    showToast("Please select a payment method.");
                    done = false;
                }
                if (done) {
                    myIntent.putExtra
                            ("phonenumber", getIntent().getStringExtra("phonenumber"));
                    myIntent.putExtra
                            ("locationtype", getIntent().getStringExtra("locationtype"));
                    myIntent.putExtra
                            ("address", getIntent().getStringExtra("address"));
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
                    myIntent.putExtra
                            ("address", getIntent().getStringExtra("address"));
                    myIntent.putExtra
                            ("country", getIntent().getStringExtra("country"));
                    myIntent.putExtra
                            ("postalcode", getIntent().getStringExtra("postalcode"));
                    myIntent.putExtra
                            ("city", getIntent().getStringExtra("city"));
                    Bundle bundle = getIntent().getParcelableExtra("bundle");
                    LatLng coords = bundle.getParcelable("coordinates");
                    Bundle args = new Bundle();
                    args.putParcelable("coordinates", coords);
                    myIntent.putExtra("bundle", args);
                    myIntent.putExtra
                            ("address2", getIntent().getStringExtra("address2"));
                    myIntent.putExtra
                            ("country2", getIntent().getStringExtra("country2"));
                    myIntent.putExtra
                            ("postalcode2", getIntent().getStringExtra("postalcode2"));
                    myIntent.putExtra
                            ("city2", getIntent().getStringExtra("city2"));
                    Bundle bundle2 = getIntent().getParcelableExtra("bundle2");
                    LatLng coords2 = bundle.getParcelable("coordinates2");
                    Bundle args2 = new Bundle();
                    args.putParcelable("coordinates2", coords2);
                    myIntent.putExtra("bundle2", args2);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String phonenumber = getIntent().getStringExtra("phonenumber");
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupAddPayment.this,
                        FarmerRequestPickupOrderConfirmation.class);
                farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
