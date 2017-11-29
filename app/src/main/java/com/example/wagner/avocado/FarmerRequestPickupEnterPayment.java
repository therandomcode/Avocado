package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupEnterPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_enter_payment);
/*
        final Button addCardButton = findViewById(R.id.farmerRequestPickupEnterAnotherPaymentAddCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterPayment.this,
                        FarmerRequestPickupEnterAnotherPayment.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button button = findViewById(R.id.farmerRequestPickupEnterPaymentNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterPayment.this,
                        FarmerRequestPickupOrderConfirmation.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button addButton = findViewById(R.id.farmerRequestPickupEnterAnotherPaymentAddCardButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupAddCardIntent = new Intent(FarmerRequestPickupEnterPayment.this,
                       FarmerRequestPickupEnterAnotherPayment.class);
                startActivity(farmerBeginRequestPickupAddCardIntent);
            }
        }); */

        final Button nextButton = findViewById(R.id.farmerRequestPickupEnterPaymentNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterPayment.this, FarmerRequestPickupOrderConfirmation.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button addButton = findViewById(R.id.farmerRequestPickupEnterAnotherPaymentAddCardButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterPayment.this, FarmerRequestPickupEnterAnotherPayment.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }
}
