package com.example.wagner.avocado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class TransporterViewSchedule extends AppActivity implements DataReceived{

    ListView lst;
    /*String[] transportername={"Juan","Ricardo","Davíd"};
    String[] time={"1996 Toyota Tacoma","2002 Nissan Navara","2000 Agrale Marrua"};
    Integer[] imgid ={R.drawable.bgavocado,R.drawable.bgavocado,R.drawable.bgavocado};
    */
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> fulltimes = new ArrayList<String>();
    ArrayList<Integer> imgids = new ArrayList<Integer>();
    ArrayList<String> startlocations = new ArrayList<String>();
    ArrayList<String> endlocations = new ArrayList<String>();
    ArrayList<String> statuses = new ArrayList<String>();
    private int index;
    private String s;


    /*
     * hardcoded farmers to populate schedule
     * TODO have the database populate the schedule based on how many requests the transporter
     * TODO must complete
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_view_schedule);
        findViewById(R.id.messagesPopUp).setVisibility(View.GONE);

        DatabaseHandler db =  new DatabaseHandler(this);
        db.getSchedule(getIntent().getStringExtra("phonenumber"));
    }

    public void Success(String response){

        String name, sloc, eloc, date, mytime;

        try {
            JSONArray scheduledFarmers = new JSONArray(response);

            for (int i = 0; i < scheduledFarmers.length(); i++){
                JSONObject farmer = scheduledFarmers.getJSONObject(i);
                name = (String)farmer.get("firstname")+" "+(String)farmer.get("lastname");
                sloc = (String) farmer.get("startcity");
                eloc = (String) farmer.get("endcity");
                date = (String) farmer.get("date");
                mytime = (String) farmer.get("time");

                names.add(name);
                fulltimes.add(date+" "+mytime);
                startlocations.add(sloc);
                endlocations.add(eloc);
                imgids.add(R.drawable.bgavocado);

                // TODO put statuses in the database and add them to the arraylist

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        lst= findViewById(R.id.listview);
        TransporterViewScheduleCustomListView customListview =
                new TransporterViewScheduleCustomListView(this
                        ,names.toArray(new String[names.size()])
                        ,fulltimes.toArray(new String[fulltimes.size()])
                        ,statuses.toArray(new String[statuses.size()])
                        ,imgids.toArray(new Integer[imgids.size()]));
        lst.setAdapter(customListview);

        final Button changeStatusButton = findViewById(R.id.changeStatusButton);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                index = position;
                String status = (String) changeStatusButton.getText();
                if (!status.equals("Completed")) {
                    findViewById(R.id.messagesPopUp).setVisibility(View.VISIBLE);
                    findViewById(R.id.messagesPopUp).bringToFront();
                }
            }
        });

        final Button onMyWayButton = findViewById(R.id.onMyWayButton);
        onMyWayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.messagesPopUp).setVisibility(View.GONE);
                showToast("¡Su mensaje ha sido enviado!");
            }
        });

        final Button earlyButton = findViewById(R.id.lateButton);
        earlyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.messagesPopUp).setVisibility(View.GONE);
                showToast("¡Su mensaje ha sido enviado!");
            }
        });

        final Button lateButton = findViewById(R.id.hereButton);
        lateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.messagesPopUp).setVisibility(View.GONE);
                showToast("¡Su mensaje ha sido enviado!");
            }
        });

        final Button cancelTripButton = findViewById(R.id.cancelTripButton);
        cancelTripButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.messagesPopUp).setVisibility(View.GONE);
                showToast("¡Su mensaje ha sido enviado!");
            }
        });

        //closes the messages popup
        final ImageButton closeMessagesButton = findViewById(R.id.closeMessagesPopUpButton);
        closeMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.messagesPopUp).setVisibility(View.GONE);
            }
        });

        final Button setAvailabilityButton = findViewById(R.id.transporterViewScheduleSetAvailabilityButton);
        setAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterViewSchedule.this,
                        TransporterSetAvailabilityLocation.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterViewScheduleBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterViewSchedule.this,
                        TransporterHome.class);
                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                startActivity(myIntent);
            }
        });

        /*
         * TODO have the toggles filter the database accordingly to display all the farmer requests
         * TODO in the selected timeframe
         */

        final ToggleButton todayButton = findViewById(R.id.transporterViewScheduleTodayButton);
        final ToggleButton tomorrowButton = findViewById(R.id.transporterViewScheduleTomorrowButton);
        final ToggleButton nextWeekButton = findViewById(R.id.transporterViewScheduleNextWeekButton);

        todayButton.setChecked(true);
        tomorrowButton.setChecked(false);
        nextWeekButton.setChecked(false);

        todayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tomorrowButton.setChecked(false);
                    nextWeekButton.setChecked(false);
                }
                else {
                    if (!tomorrowButton.isChecked() && !nextWeekButton.isChecked())
                        todayButton.setChecked(true);
                }
            }
        });

        tomorrowButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    todayButton.setChecked(false);
                    nextWeekButton.setChecked(false);
                }
                else {
                    if (!todayButton.isChecked() && !nextWeekButton.isChecked())
                        tomorrowButton.setChecked(true);
                }
            }
        });

        nextWeekButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tomorrowButton.setChecked(false);
                    todayButton.setChecked(false);
                }
                else {
                    if (!tomorrowButton.isChecked() && !todayButton.isChecked())
                        nextWeekButton.setChecked(true);
                }
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
