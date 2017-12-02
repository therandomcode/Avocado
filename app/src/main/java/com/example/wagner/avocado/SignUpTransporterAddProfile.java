package com.example.wagner.avocado;

/**
 * Created by arkaroy on 12/2/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpTransporterAddProfile extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_transporter_add_profile);

        final Button nextbutton = findViewById(R.id.finishButton);
        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterAddProfile.this, TransporterHome.class);
                startActivity(myIntent);
            }
        });

        final Button backbutton = findViewById(R.id.backButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterAddProfile.this, SignUpTransporterAddPhotos.class);
                startActivity(myIntent);
            }
        });

    }

}
