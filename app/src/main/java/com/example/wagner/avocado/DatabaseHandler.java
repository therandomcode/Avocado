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

        client.post("http://172.104.28.91/insertfarmer.php",params ,new AsyncHttpResponseHandler() {

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
        client.post("http://172.104.28.91/inserttransporter.php",params ,new AsyncHttpResponseHandler() {

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

        client.post("http://172.104.28.91/getTransporters.php", params,
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

        client.post("http://172.104.28.91/getTransporter.php", params,
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

        client.post("http://172.104.28.91/getFarmer.php", params,
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
            url = "http://172.104.28.91/getFarmerHistory.php";
            System.out.println("hi");
        }
        else if (type.equals("transporter"))
        {
            post = "getTransporterHistory";
            url = "http://172.104.28.91/getTransporterHistory.php";
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

    public void sendRequest(String crop, String amount, String metric, String locationtype
                            , String farmernumber, String transporternumber, String date, String time
                            , String startaddress, String startcity, String startcountry
                            , String startpostalcode, String endaddress, String endcity
                            , String endcountry, String endpostalcode, String status
                            , String farmerrating, String transporterrating){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("crop", crop);
        map.put("amount", amount);
        map.put("metric", metric);
        map.put("locationtype", locationtype);
        map.put("phonenumberfarmer", farmernumber);
        map.put("phonenumbertransporter", transporternumber);
        map.put("date", date);
        map.put("time", time);
        map.put("startaddress", startaddress);
        map.put("startcity", startcity);
        map.put("startcountry", startcountry);
        map.put("startpostalcode", startpostalcode);
        map.put("endaddress", endaddress);
        map.put("endcity", endcity);
        map.put("endcountry", endcountry);
        map.put("endpostalcode", endpostalcode);
        map.put("status", status);
        map.put("farmerrating", farmerrating);
        map.put("transporterrating", transporterrating);

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();

        params.put("sendRequest", gson.toJson(wordList));

        client.post("http://172.104.28.91/sendRequest.php", params,
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

    public void getRequests(String phonenumber){
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
        params.put("getRequests", gson.toJson(wordList));

        client.post("http://172.104.28.91/getRequests.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("requests: ");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                }
        );
    }

    public void getSchedule(String phonenumber){
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
        params.put("getSchedule", gson.toJson(wordList));

        client.post("http://172.104.28.91/getSchedule.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("schedule: ");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                }
        );
    }

    public void getFarmerMessages(String phonenumber){
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
        params.put("getFarmerMessages", gson.toJson(wordList));

        client.post("http://172.104.28.91/getFarmerMessages.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("FarmerMessages: ");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                }
        );
    }


    public void updateStatus(String phonenumberfarmer, String phonenumbertransporter, String status
                            , String date, String time){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println("phonenumberfarmer: "+phonenumberfarmer);
        System.out.println("phonenumbertransporter: "+phonenumbertransporter);
        System.out.println("status: "+status);
        System.out.println("date: "+date);
        System.out.println("time: "+time);

        map.put("phonenumberfarmer", phonenumberfarmer);
        map.put("phonenumbertransporter", phonenumbertransporter);
        map.put("status", status);
        map.put("date", date);
        map.put("time", time);

        //intent = nextscreen;
        final ArrayList<Transporter> trans = new ArrayList<Transporter>();

        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("updateStatus", gson.toJson(wordList));

        client.post("http://172.104.28.91/updateStatus.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] b) {

                        String response = new String(b);
                        System.out.println("updateStatus: ");
                        System.out.println(response);
                        activity.Success(response);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        System.out.println(new String(bytes));
                    }
                }
        );
    }

}
