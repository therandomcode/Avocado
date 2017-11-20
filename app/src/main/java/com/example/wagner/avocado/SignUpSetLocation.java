package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

public class SignUpSetLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_set_location);

        //findViewById(R.id.map).setVisibility(View.GONE);

        final Button signOutButton = findViewById(R.id.signUpSetLocationNextButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetLocation.this, SignUpFarmerAddPhotos.class);
                startActivity(myIntent);
            }
        });

        final ToggleButton enterAddressButton = findViewById(R.id.signUpSetLocationEnterAddressButton);
        final ToggleButton dropPinButton = findViewById(R.id.signUpSetLocationDropPinButton);

        enterAddressButton.setChecked(true);
        dropPinButton.setChecked(false);

        enterAddressButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dropPinButton.setChecked(false);
                    setAddressTextView();
                } else {
                    dropPinButton.setChecked(true);
                    setDropPinTextView();
                }
            }
        });

        dropPinButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enterAddressButton.setChecked(false);
                    setDropPinTextView();
                } else {
                    enterAddressButton.setChecked(true);
                    setAddressTextView();
                }
            }
        });
    }

    private void setAddressTextView(){
        EditText addressLine1 = findViewById(R.id.editText2);
        EditText country = findViewById(R.id.editText3);
        EditText addressLine2 = findViewById(R.id.editText6);
        EditText postalCode = findViewById(R.id.editText4);
        EditText city = findViewById(R.id.editText5);
        addressLine1.setVisibility(View.VISIBLE);
        addressLine2.setVisibility(View.VISIBLE);
        city.setVisibility(View.VISIBLE);
        country.setVisibility(View.VISIBLE);
        postalCode.setVisibility(View.VISIBLE);
        //findViewById(R.id.map).setVisibility(View.INVISIBLE);
    }

    private void setDropPinTextView() {
        EditText addressLine1 = findViewById(R.id.editText2);
        EditText country = findViewById(R.id.editText3);
        EditText addressLine2 = findViewById(R.id.editText6);
        EditText postalCode = findViewById(R.id.editText4);
        EditText city = findViewById(R.id.editText5);
        addressLine1.setVisibility(View.GONE);
        addressLine2.setVisibility(View.GONE);
        city.setVisibility(View.GONE);
        country.setVisibility(View.GONE);
        postalCode.setVisibility(View.GONE);
        //findViewById(R.id.map).setVisibility(View.VISIBLE);
    }
}
