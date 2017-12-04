package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FarmerRequestPickupEnterPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_enter_payment);

        Button nextButton = findViewById(R.id.farmerRequestPickupEnterPaymentNextButton);

        RadioButton cashRadioButton = findViewById(R.id.cashRadioButton);
        RadioButton bankAccountRadioButton = findViewById(R.id.bankAccountRadioButton);
        RadioButton creditCardRadioButton = findViewById(R.id.creditCardRadioButton);

        if (creditCardRadioButton.isChecked()){
            nextButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupEnterPayment.this,
                            FarmerRequestPickupEnterAnotherPayment.class);
                    startActivity(farmerBeginRequestPickupIntent);
                }
            });
        } else if (bankAccountRadioButton.isChecked()){
            nextButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent addBankAccountIntent = new Intent(FarmerRequestPickupEnterPayment.this,
                            FarmerRequestPickupAddBankAccount.class);
                    startActivity(addBankAccountIntent);
                }
            });
        } else if (cashRadioButton.isChecked()) {
            nextButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent skipAhead = new Intent(FarmerRequestPickupEnterPayment.this,
                            FarmerRequestPickupReviewOrder.class);
                    startActivity(skipAhead);
                }
            });
        }

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
