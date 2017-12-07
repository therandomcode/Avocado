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

        final Button button = findViewById(R.id.farmerRequestPickupOrderConfirmationFinishButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast();
                Intent farmerRequestPickupSetPickupLocationIntent = new Intent(FarmerRequestPickupOrderConfirmation.this,
                        FarmerMyMessages.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerRequestPickupSetPickupLocationIntent.putExtra("phonenumber", phonenumber);
                startActivity(farmerRequestPickupSetPickupLocationIntent);
            }
        });
    }

    private void showToast() {
        Toast.makeText(this,
                "Thank you! Your request for pickup has been sent.",
                Toast.LENGTH_LONG).show();
    }
}
