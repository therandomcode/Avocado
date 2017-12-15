package com.example.wagner.avocado;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TransporterMessages extends AppActivity implements DataReceived {

    ListView messages;
    ListView lst;
    //String[] delivered = {"delivered", "delivered", "not delivered"};
    private int index;

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