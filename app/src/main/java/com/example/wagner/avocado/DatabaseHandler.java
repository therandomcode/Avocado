package com.example.wagner.avocado;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by arkaroy on 12/2/17.
 */

public class DatabaseHandler {

    public DatabaseHandler(){

    }

    public void insertFarmer(String firstname, String lastname, String phonenumber, String password, String
            address, String country, String postalcode, String
                                      city){
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

        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("insertFarmer", gson.toJson(wordList));
        client.post("http://10.0.2.2/~arkaroy/sqlitetomysql/insertfarmer.php",params ,new AsyncHttpResponseHandler() {

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

    public void insertTransporter(String firstname, String lastname, String availability, String
            address, String city, String postalcode, String
                                     country, String password, String phonenumber, String carmake
                                , String capacity, String licenseplatenumber){

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

        wordList.add(map);

        Gson gson = new GsonBuilder().create();
        params.put("insertTransporter", gson.toJson(wordList));
        client.post("http://10.0.2.2/~arkaroy/sqlitetomysql/inserttransporter.php",params ,new AsyncHttpResponseHandler() {

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

}
