package com.example.wagner.avocado;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class SignUpSetLocation extends AppCompatActivity implements
        OnMarkerClickListener,
        OnMapClickListener,
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener {

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap = null;

    /**
     * Keeps track of the selected marker.
     */
    private Marker mLastMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_set_location);

        findViewById(R.id.map).setVisibility(View.GONE);

        final Button nextButton = findViewById(R.id.signUpSetLocationNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String name = getIntent().getStringExtra("name");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");

                EditText text2 = (EditText)findViewById(R.id.editText2);
                EditText text6 = (EditText)findViewById(R.id.editText6);
                String address = text2.getText().toString() + " " + text6.getText().toString();

                EditText text3 = (EditText)findViewById(R.id.editText3);
                String country = text3.getText().toString();

                EditText text4 = (EditText)findViewById(R.id.editText4);
                String postalcode = text4.getText().toString();

                EditText text5 = (EditText)findViewById(R.id.editText5);
                String city = text5.getText().toString();

                insertFarmerMySQL(name, phonenumber, password, address, country, postalcode, city);

                Intent myIntent = new Intent(SignUpSetLocation.this, SignUpFarmerAddPhotos.class);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpSetLocationBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetLocation.this, CreateAccount.class);
                startActivity(myIntent);
            }
        });

        final Button skipButton = findViewById(R.id.signUpSetLocationSkipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpSetLocation.this, SignUpLater.class);
                startActivity(myIntent);
            }
        });


        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        new OnMapAndViewReadyListener(mapFragment, this);


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

    public void insertFarmerMySQL(String name, String phonenumber, String password, String
            address, String country, String postalcode, String
                                          city)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String names[] = name.split( " ");
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("firstname", names[0]);
        map.put("lastname", names[1]);
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
        findViewById(R.id.map).setVisibility(View.GONE);
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
        findViewById(R.id.map).setVisibility(View.VISIBLE);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Set listener for marker click event.  See the bottom of this class for its behavior.
        mMap.setOnMarkerClickListener(this);

        // Set listener for map click event.  See the bottom of this class for its behavior.
        mMap.setOnMapClickListener(this);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localized.
        map.setContentDescription("Agromovil Map Test.");

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.5709,-74.2973), (float) 5.0));
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Going to your location", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onMapClick(final LatLng point) {
        // Any showing info window closes when the map is clicked.
        // Clear the currently selected marker.
        if (mLastMarker != null) mLastMarker.remove();
        mLastMarker = mMap.addMarker(new MarkerOptions().position(point));
        Toast.makeText(this, "Setting your pickup location", Toast.LENGTH_SHORT).show();
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // The user has re-tapped on the marker which was already showing an info window.
        if (marker.equals(mLastMarker)) {
            mLastMarker.remove();
            return true;
        }

        mLastMarker = marker;

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur.
        return false;
    }
}
