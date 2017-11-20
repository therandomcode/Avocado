package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerRequestPickupReviewOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_review_order);

        final Button button = findViewById(R.id.farmerRequestPickupReviewOrderNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupReviewOrder.this,
                        FarmerRequestPickupEnterPayment.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });
    }
}
