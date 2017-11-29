package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupAddPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_add_payment);

        final Button nextButton = findViewById(R.id.FarmerRequestPickupAddPaymentNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupAddPayment.this, FarmerRequestPickupEnterAnotherPayment.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.FarmerRequestPickupAddPaymentBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupAddPayment.this, FarmerBeginRequestPickup.class);
                startActivity(myIntent);
            }
        });
    }
}
