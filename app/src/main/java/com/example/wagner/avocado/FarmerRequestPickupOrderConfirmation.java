package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FarmerRequestPickupOrderConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_order_confirmation);

        //returns to the farmer home screen after the order is completed
        final Button finishButton = findViewById(R.id.farmerRequestPickupOrderConfirmationFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast("Thank you! Your request for pickup has been sent.");
                Intent farmerRequestPickupSetPickupLocationIntent =
                        new Intent(FarmerRequestPickupOrderConfirmation.this,
                            FarmerHome.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerRequestPickupSetPickupLocationIntent.putExtra("phonenumber", phonenumber);
                startActivity(farmerRequestPickupSetPickupLocationIntent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
