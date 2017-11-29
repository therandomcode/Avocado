package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FarmerRequestPickupEnterPayment extends AppCompatActivity {

    final RadioButton cashRadioButton = (RadioButton) findViewById(R.id.cashRadioButton);
    final RadioButton bankAccountRadioButton = (RadioButton) findViewById(R.id.bankAccountRadioButton);
    final RadioButton creditCardRadioButton = (RadioButton) findViewById(R.id.creditCardRadioButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_enter_payment);

        final Button backButton = findViewById(R.id.farmerRequestPickupEnterPaymentBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterPayment.this,
                        FarmerRequestPickupOrderConfirmation.class);
                        startActivity(farmerBeginRequestPickupIntent);
            }
        });

    }
}
