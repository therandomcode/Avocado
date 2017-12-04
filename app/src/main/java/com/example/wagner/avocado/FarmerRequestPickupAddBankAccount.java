package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupAddBankAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_add_bank_account);

        final Button button = findViewById(R.id.farmerRequestPickupAddBankAccountNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupAddBankAccount.this,
                        FarmerRequestPickupOrderConfirmation.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupAddBankAccountBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupAddBankAccount.this,
                        FarmerRequestPickupEnterPayment.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }
}
