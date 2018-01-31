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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TransporterSetAvailabilityLocation extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener,
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
    private Marker mLastMarker = null;
    private LatLng markerLatLng = null;

    private ToggleButton homeButton;
    private ToggleButton anotherLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_set_availability_location_type);

        findViewById(R.id.transporterSetAvailabilityLocationTypeMap).setVisibility(View.GONE);
        final EditText address = findViewById(R.id.transporterSetAvailabilityLocationTypeAddress);
        address.setVisibility(View.GONE);

        homeButton = findViewById(R.id.transporterSetAvailabilityLocationTypeHomeButton);
        anotherLocationButton = findViewById(R.id.transporterSetAvailabilityLocationTypeAnotherLocationButton);
        final ImageView house = findViewById(R.id.transporterSetAvailabilityLocationTypeHouseIcon);

        homeButton.setChecked(true);
        anotherLocationButton.setChecked(false);

        Bundle coords = getIntent().getParcelableExtra("bundle");
        if (coords != null) {
            LatLng coordinates = coords.getParcelable("coordinates");
            if (coordinates != null) { markerLatLng = coordinates; }
        }

        homeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    anotherLocationButton.setChecked(false);
                    findViewById(R.id.transporterSetAvailabilityLocationTypeMap).setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    house.setVisibility(View.GONE);
                } else {
                    anotherLocationButton.setChecked(true);
                    findViewById(R.id.transporterSetAvailabilityLocationTypeMap).setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    house.setVisibility(View.VISIBLE);
                }
            }
        });

        anotherLocationButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    homeButton.setChecked(false);
                    findViewById(R.id.transporterSetAvailabilityLocationTypeMap).setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    house.setVisibility(View.VISIBLE);
                } else {
                    homeButton.setChecked(true);
                    findViewById(R.id.transporterSetAvailabilityLocationTypeMap).setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    house.setVisibility(View.GONE);
                }
            }
        });

        final Button nextButton = findViewById(R.id.transporterSetAvailabilityLocationTypeNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityLocation.this,
                        TransporterSetAvailabilityPickDate.class);

                /*
                 * TODO use the geocoder to turn addresses into LatLng and vice versa
                 * TODO if the user enters an address, a marker is dropped at that location
                 * TODO if the user drops a pin, auto-fill the address for that pin
                 */

                if (homeButton.isChecked())
                    myIntent.putExtra("address", "home");
                else {
                    EditText loc = (EditText)findViewById(R.id.transporterSetAvailabilityLocationTypeAddress);
                    String address = loc.getText().toString();
                    myIntent.putExtra("address", address);
                }

                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);

                Bundle args = new Bundle();
                args.putParcelable("coordinates", markerLatLng);
                myIntent.putExtra("bundle", args);
                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.transporterSetAvailabilityLocationTypeBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransporterSetAvailabilityLocation.this,
                        TransporterViewSchedule.class);
                myIntent.putExtra("phonenumber",
                        getIntent().getStringExtra("phonenumber"));
                startActivity(myIntent);
            }
        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.transporterSetAvailabilityLocationTypeMap);
        new OnMapAndViewReadyListener(mapFragment, this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        if (markerLatLng != null) {
            mLastMarker = mMap.addMarker(new MarkerOptions().position(markerLatLng));
        }

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
        Toast.makeText(this, "En camino a su ubicación", Toast.LENGTH_SHORT).show();
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
        markerLatLng = point;
        if (mLastMarker != null) mLastMarker.remove();
        mLastMarker = mMap.addMarker(new MarkerOptions().position(point));
        Toast.makeText(this, "Configurando su ubicación", Toast.LENGTH_SHORT).show();
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // The user has re-tapped on the marker which was already showing an info window.
        if (marker.equals(mLastMarker)) {
            mLastMarker.remove();
            markerLatLng = null;
            return true;
        }

        mLastMarker = marker;

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur.
        return false;
    }
}
