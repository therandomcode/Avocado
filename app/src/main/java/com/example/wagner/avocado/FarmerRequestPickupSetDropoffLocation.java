package com.example.wagner.avocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.widget.EditText;


public class FarmerRequestPickupSetDropoffLocation extends AppCompatActivity implements
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
        setContentView(R.layout.activity_farmer_request_pickup_set_dropoff_location);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        new OnMapAndViewReadyListener(mapFragment, this);

        final Button nextButton = findViewById(R.id.farmerRequestPickupSetDropoffLocationNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText edittext7 = (EditText)findViewById(R.id.editText7);
                String droplocation = edittext7.getText().toString();

                Intent farmerRequestPickupSetPickupLocationIntent = new Intent
                        (FarmerRequestPickupSetDropoffLocation.this,
                        FarmerRequestPickupChooseTransporter.class);


                String phonenumber = getIntent().getStringExtra("phonenumber");
                farmerRequestPickupSetPickupLocationIntent.putExtra("phonenumber", phonenumber);
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("droplocation", droplocation);
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("locationtype", getIntent().getStringExtra("locationtype"));
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("address", getIntent().getStringExtra("address"));
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                farmerRequestPickupSetPickupLocationIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));

                startActivity(farmerRequestPickupSetPickupLocationIntent);
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupSetDropoffLocationBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerRequestPickupSetPickupLocationIntent = new Intent(FarmerRequestPickupSetDropoffLocation.this,
                        FarmerRequestPickupSetPickupLocationType.class);
                startActivity(farmerRequestPickupSetPickupLocationIntent);
            }
        });

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

        Toast.makeText(this,
                "Set your dropoff location by dropping a pin or entering your address.",
                Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, "Setting your dropoff location", Toast.LENGTH_SHORT).show();
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
