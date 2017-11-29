package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import cz.msebera.android.httpclient.Header;

public class FarmerRequestPickupChooseTransporter extends AppCompatActivity {

    ListView lst;
    String[] transportername={"Juan","Ricardo","Davíd"};
    String[] times={"1996 Toyota Tacoma","2002 Nissan Navara","2000 Agrale Marrua"};
    Integer[] imgid ={R.drawable.bgavocado,R.drawable.bgavocado,R.drawable.bgavocado};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_choose_transporter);


        String firstname = "joe";
        String lastname = "blow";
        String locationtype = getIntent().getStringExtra("locationtype");
        String droplocation = getIntent().getStringExtra("droplocation");
        String address = getIntent().getStringExtra("address");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String crop = getIntent().getStringExtra("crop");
        String metric = getIntent().getStringExtra("metric");
        String amount = getIntent().getStringExtra("amount");

        ArrayList<Transporter> availableTransporters = getTransporters(time, date, crop, amount, metric);

        lst= findViewById(R.id.listview);
        FarmerRequestPickupChooseTransporterCustomListView customListview = new FarmerRequestPickupChooseTransporterCustomListView(this,transportername,times,imgid);
        lst.setAdapter(customListview);


        final Button button = findViewById(R.id.farmerRequestPickupChooseTransporterNextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerBeginRequestPickupIntent = new Intent(FarmerRequestPickupChooseTransporter.this,
                        FarmerRequestPickupReviewOrder.class);
                startActivity(farmerBeginRequestPickupIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupChooseTransporterBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupChooseTransporter.this,
                        FarmerRequestPickupSetDropoffLocation.class);
                startActivity(myIntent);
            }
        });
    }

    public ArrayList<Transporter> getTransporters(String time, String date, String crop, String amount,
                                                  String metric){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("time", time);
        map.put("date", date);
        map.put("crop", crop);
        map.put("amount", amount);
        map.put("metric", metric);



        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("getTransporters", gson.toJson(wordList));

        client.post("http://10.0.2.2/~arkaroy/sqlitetomysql/getTransporters.php",params ,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    System.out.println("Hello I am here!");
                    String response = new String(bytes);
                    System.out.println(response);
                    JSONArray arr = new JSONArray(response);
                    System.out.println(arr.length());
                    for(int j=0; j<arr.length();j++){
                        JSONObject obj = (JSONObject)arr.get(j);
                        System.out.println(obj.get("firstname"));
                        System.out.println(obj.get("lastname"));
                        System.out.println(obj.get("availability"));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block

                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
        ArrayList<Transporter> trans = new ArrayList<Transporter>();
        return trans;
    }


}
