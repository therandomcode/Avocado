package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    private CheckBox farmerBox;
    private CheckBox transporterBox;
    private EditText fn;
    private EditText pn;
    private EditText pass;
    private EditText ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //asigns variables to the editText fields
        fn = (EditText)findViewById(R.id.firstname);
        pn = (EditText)findViewById(R.id.phonenumber);
        pass = (EditText)findViewById(R.id.password);
        ln = (EditText)findViewById(R.id.lastname);

        //checks the strings based in the intents to see if the user had entered info in the screen
        //gone to the next screen and then gone back
        //if so, it sets the fields to the strings the user originally entered
        String firstNameTemp = getIntent().getStringExtra("firstname");
        String lastNameTemp = getIntent().getStringExtra("lastname");
        String phoneNumTemp = getIntent().getStringExtra("phonenumber");

        if ((firstNameTemp != null) && (!firstNameTemp.equals(""))) {
            fn.setText(firstNameTemp, TextView.BufferType.EDITABLE);
        }

        if ((lastNameTemp != null) && (!lastNameTemp.equals(""))) {
            ln.setText(lastNameTemp, TextView.BufferType.EDITABLE);
        }

        if ((phoneNumTemp != null) && (!phoneNumTemp.equals(""))) {
            pn.setText(phoneNumTemp, TextView.BufferType.EDITABLE);
        }

        farmerBox = (CheckBox) findViewById(R.id.Farmer);
        transporterBox = (CheckBox) findViewById(R.id.Transporter);

        String user = getIntent().getStringExtra("user");

        if ((user != null) && (user.equals("farmer"))) {
            farmerBox.setChecked(true);
        }
        else if ((user != null) && (user.equals("transporter"))) {
            transporterBox.setChecked(true);
        }

        //performs an activity and passes info entered when the submit button is clicked
        //if not all the fields are filled out, it does not go to the next activity and
        // throws a toast
        final Button submitButton = findViewById(R.id.createAccountSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String firstname = fn.getText().toString();
                String phonenumber = pn.getText().toString();
                String password = pass.getText().toString();
                String lastname = ln.getText().toString();

                farmerBox = (CheckBox) findViewById(R.id.Farmer);
                transporterBox = (CheckBox) findViewById(R.id.Transporter);
                if ((farmerBox.isChecked() || transporterBox.isChecked()) && (!firstname.equals("")) &&
                        (!lastname.equals("")) && (!phonenumber.equals("")) && (!password.equals(""))) {

                    //proceeds to the correct sign up sequence depending on which box is clicked
                    if (farmerBox.isChecked()) {
                        Intent farmerBeginRequestPickupIntent = new Intent(CreateAccount.this,
                                SignUpFarmerSetLocation.class);

                        farmerBeginRequestPickupIntent.putExtra("firstname", firstname);
                        farmerBeginRequestPickupIntent.putExtra("lastname", lastname);
                        farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                        farmerBeginRequestPickupIntent.putExtra("password", password);

                        startActivity(farmerBeginRequestPickupIntent);
                    } else if (transporterBox.isChecked()) {
                        Intent farmerBeginRequestPickupIntent = new Intent(CreateAccount.this,
                                SignUpTransporterSetLocation.class);

                        farmerBeginRequestPickupIntent.putExtra("firstname", firstname);
                        farmerBeginRequestPickupIntent.putExtra("lastname", lastname);
                        farmerBeginRequestPickupIntent.putExtra("phonenumber", phonenumber);
                        farmerBeginRequestPickupIntent.putExtra("password", password);

                        startActivity(farmerBeginRequestPickupIntent);
                    }
                }
                else {
                    showToast("Por favor ingrese la información de todos los campos para continuar.");
                }
            }
        });

        //returns to the sign up/login home screen if cancel is clicked
        final Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CreateAccount.this, BeginSignUp.class);
                startActivity(myIntent);
            }
        });
    }

    public void onTransporterClicked(View view) {
        if (farmerBox.isChecked()) {
            farmerBox.setChecked(false);
        }
    }


    public void onFarmerClicked(View view) {
        if (transporterBox.isChecked()) {
            transporterBox.setChecked(false);
        }
    }

    //shows a toast
    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
