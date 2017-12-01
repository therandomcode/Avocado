package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupEnterAnotherPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_enter_another_payment);

        final Button button = findViewById(R.id.farmerRequestPickupEnterAnotherPaymentNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterAnotherPayment.this,
                        FarmerRequestPickupOrderConfirmation.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

    }
}
