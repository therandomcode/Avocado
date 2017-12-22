package com.example.wagner.avocado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ListView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class TransporterMessages extends AppCompatActivity {

    ListView messages;
    ListView lst;
    String[] transportername = {"Felipe los Espadrillas", "Ricardo de Leon", "Davíd Latafundia"};
    String[] time = {"Tuesday, 2 October 15:10", "Thursday, 4 October 13:24", "Tuesday, 6 October 15:36"};
    Integer[] imgid = {R.drawable.profile, R.drawable.cecilia, R.drawable.maria};
    String[] msg = {"Requests delivery 20 Dec 2017", "Request delivery 14 Jan 2018", "Requests delivery 15 Jan 2018."};

    private int index;

    /*
     * currently hardcoding the list of farmers that are requesting pickup
     * TODO use the database to only load farmers that have sent a request to the trnasporter
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_messages);

        findViewById(R.id.popUp).setVisibility(View.GONE);

        ArrayList<String> checktransporters = getIntent().getStringArrayListExtra("transporters");
        ArrayList<String> checktimes = getIntent().getStringArrayListExtra("times");
        ArrayList<String> checkmsg = getIntent().getStringArrayListExtra("messages");
        ArrayList<Integer> checkimages = getIntent().getIntegerArrayListExtra("images");
        if ((checktransporters != null)) {
            transportername = checktransporters.toArray(new String[0]);
            time = checktimes.toArray(new String[0]);
            msg = checkmsg.toArray(new String[0]);
            imgid = checkimages.toArray(new Integer[0]);
        }


        lst = findViewById(R.id.transporterMessagesListView);
        TransporterMessagesListView customListview = new TransporterMessagesListView(this,
                transportername, time, msg, imgid);
        lst.setAdapter(customListview);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                index = position;
                findViewById(R.id.popUp).setVisibility(View.VISIBLE);
                findViewById(R.id.popUp).bringToFront();
            }
        });

        final Button acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterMessages.this, TransporterMessages.class);
                ArrayList<String> newtransportername = new ArrayList<>(Arrays.asList(transportername));
                newtransportername.remove(index);
                myIntent.putStringArrayListExtra("transporters", newtransportername);
                ArrayList<String> newtime = new ArrayList<>(Arrays.asList(time));
                newtime.remove(index);
                myIntent.putStringArrayListExtra("times", newtime);
                ArrayList<String> newmsg = new ArrayList<>(Arrays.asList(msg));
                newmsg.remove(index);
                myIntent.putStringArrayListExtra("messages", newmsg);
                ArrayList<Integer> newimgid = new ArrayList<>(Arrays.asList(imgid));
                newimgid.remove(index);
                myIntent.putIntegerArrayListExtra("images", newimgid);
                myIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));

                startActivity(myIntent);

                /*
                 * TODO insert this farmer into the database and load it into the transporter's
                 * TODO schedule
                 */

                Toast toast = Toast.makeText(TransporterMessages.this,
                        "Thank you! They have been added to your schedule, " +
                                "which you can access from the home page.",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });

        final Button declineButton = findViewById(R.id.declineButton);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterMessages.this, TransporterMessages.class);
                ArrayList<String> newtransportername = new ArrayList<>(Arrays.asList(transportername));
                newtransportername.remove(index);
                myIntent.putStringArrayListExtra("transporters", newtransportername);
                ArrayList<String> newtime = new ArrayList<>(Arrays.asList(time));
                newtime.remove(index);
                myIntent.putStringArrayListExtra("times", newtime);
                ArrayList<String> newmsg = new ArrayList<>(Arrays.asList(msg));
                newmsg.remove(index);
                myIntent.putStringArrayListExtra("messages", newmsg);
                ArrayList<Integer> newimgid = new ArrayList<>(Arrays.asList(imgid));
                newimgid.remove(index);
                myIntent.putIntegerArrayListExtra("images", newimgid);
                myIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));

                startActivity(myIntent);
            }
        });

        //hides the popup
        final ImageButton closeButton = findViewById(R.id.closePopUpButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.popUp).setVisibility(View.GONE);
            }
        });

        final Button backButton = findViewById(R.id.transporterMyMessagesBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterMessages.this,
                        TransporterHome.class);
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                startActivity(myIntent);
            }
        });
    }
}