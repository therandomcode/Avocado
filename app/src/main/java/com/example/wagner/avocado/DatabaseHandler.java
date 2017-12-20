package com.example.wagner.avocado;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;



import cz.msebera.android.httpclient.Header;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by arkaroy on 12/2/17.
 * IF AT ANY POINT, THE SERVER HOSTING THE PHP CODE GETS CHANGED:
 * GO THROUGH THIS FILE AND WHEREVER YOU SEE THE FOLLOWING:
 * http://epiwork.hcii.cs.cmu.edu/agromovil/sites/:
 * CHANGE IT TO THE NEW PATH TO THE PHP FILE
 */

public class DatabaseHandler extends AppCompatActivity{

    AppActivity activity;

    public DatabaseHandler(AppCompatActivity activity){
        this.activity = (AppActivity) activity;
    }

    public DatabaseHandler(){

    }

    public void insertFarmer(String firstname, String lastname, String phonenumber, String password, String
            address, String country, String postalcode, String
                                      city, String transactions, String deliveries, String ratings){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        map.put("phonenumber", phonenumber);
        map.put("password", password);
        map.put("address", address);
        map.put("country", country);
        map.put("postalcode", postalcode);
        map.put("city", city);
        map.put("transactions", transactions);
        map.put("deliveries", deliveries);
        map.put("ratings", ratings);

        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("insertFarmer", gson.toJson(wordList));

        client.post("http://epiwork.hcii.cs.cmu.edu/agromovil/sites/insertfarmer.php",params ,new AsyncHttpResponseHandler() {

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
                        System.out.println(obj.get("id"));
                        System.out.println(obj.get("status"));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    System.out.println("failed like this?");
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println("hello????"+new String(bytes));
            }
        });
    }

    public void insertTransporter(String firstname, String lastname, String availability, String
            address, String city, String postalcode, String
                                     country, String password, String phonenumber, String carmake
                                , String capacity, String licenseplatenumber, String requests,
                                    String ratings, String deliveries){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        map.put("phonenumber", phonenumber);
        map.put("password", password);
        map.put("address", address);
        map.put("country", country);
        map.put("postalcode", postalcode);
        map.put("city", city);
        map.put("availability", availability);
        map.put("carmake", carmake);
        map.put("capacity", capacity);
        map.put("licenseplatenumber", licenseplatenumber);
        map.put("requests", requests);
        map.put("ratings", ratings);
        map.put("deliveries", deliveries);

        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("insertTransporter", gson.toJson(wordList));
        client.post("http://epiwork.hcii.cs.cmu.edu/agromovil/sites/inserttransporter.php",params ,new AsyncHttpResponseHandler() {

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
                        System.out.println(obj.get("id"));
                        System.out.println(obj.get("status"));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block

                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void getAvailableTransporters(String time, String date, String crop, String amount,
                                String metric){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("time", time);
        map.put("date", date);
        map.put("crop", crop);
        map.put("amount", amount);
        map.put("metric", metric);

        //intent = nextscreen;
        final ArrayList<Transporter> trans = new ArrayList<Transporter>();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("getTransporters", gson.toJson(wordList));

        client.post("http://epiwork.hcii.cs.cmu.edu/agromovil/sites/getTransporters.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("hello!");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }


                }
        );


    }

    public void getTransporter(String phonenumber){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println("phonenumber: "+phonenumber);

        map.put("phonenumber", phonenumber);

        //intent = nextscreen;
        final ArrayList<Transporter> trans = new ArrayList<Transporter>();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("getTransporter", gson.toJson(wordList));

        client.post("http://epiwork.hcii.cs.cmu.edu/agromovil/sites/getTransporter.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("hello!");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                }
        );
    }

    public void getFarmer(String phonenumber){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println("phonenumber: "+phonenumber);

        map.put("phonenumber", phonenumber);

        //intent = nextscreen;
        final ArrayList<Transporter> trans = new ArrayList<Transporter>();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("getFarmer", gson.toJson(wordList));

        client.post("http://epiwork.hcii.cs.cmu.edu/agromovil/sites/getFarmer.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("hello!");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        System.out.println("failed");
                        System.out.println(new String(bytes));
                    }


                }
        );
    }

    public void getTransaction(String phonenumber, String type){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println("phonenumber: "+phonenumber);

        map.put("phonenumber", phonenumber);

        //intent = nextscreen;
        final ArrayList<Transporter> trans = new ArrayList<Transporter>();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();

        String post = null;
        String url = null;

        if (type.equals("farmer")){
            post = "getFarmerHistory";
            url = "http://epiwork.hcii.cs.cmu.edu/agromovil/sites/getFarmerHistory.php";
            System.out.println("hi");
        }
        else if (type.equals("transporter"))
        {
            post = "getTransporterHistory";
            url = "http://epiwork.hcii.cs.cmu.edu/agromovil/sites/getTransporterHistory.php";
        }


        params.put(post, gson.toJson(wordList));

        client.post(url, params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("hello!");
                        System.out.println(response);
                        activity.Success(response);
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        System.out.println("failed");
                        System.out.println(new String(bytes));
                    }


                }
        );
    }

    public void sendRequest(String transporternumber, String farmernumber, String request){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("phonenumberfarmer", farmernumber);
        map.put("phonenumbertransporter", transporternumber);
        map.put("request", request);



        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();

        params.put("sendRequest", gson.toJson(wordList));

        client.post("http://epiwork.hcii.cs.cmu.edu/agromovil/sites/sendRequest.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("hello!");
                        System.out.println(response);
//                        activity.Success(response);
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        System.out.println("failed");
                        System.out.println(new String(bytes));
                    }


                }
        );
    }

}
