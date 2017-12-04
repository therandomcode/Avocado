package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FarmerRequestPickupAddPayment extends AppCompatActivity {

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
            String phonenumber = getIntent().getStringExtra("phonenumber");
            public void onClick(View v) {
                if (creditCardRadioButton.isChecked()){
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                    FarmerRequestPickupEnterAnotherPayment.class);
                            farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                            startActivity(farmerBeginRequestPickupIntent);
                        }
                    });
                } else if (bankAccountRadioButton.isChecked()){
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent addBankAccountIntent = new Intent(FarmerRequestPickupAddPayment.this,
                                    FarmerRequestPickupAddBankAccount.class);
                            addBankAccountIntent.putExtra("phonenumber", phonenumber);
                            startActivity(addBankAccountIntent);
                        }
                    });
                } else if (cashRadioButton.isChecked()) {
                    nextButton.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v) {
                            Intent skipAhead = new Intent(FarmerRequestPickupAddPayment.this,
                                    FarmerRequestPickupReviewOrder.class);
                            skipAhead.putExtra("phonenumber", phonenumber);
                            startActivity(skipAhead);
                        }
                    });
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
}
