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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ToggleButton;


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
     * Keeps track of the inputted LatLng.
     */
    private Marker mLastMarker = null;
    private LatLng markerLatLng = null;
    private EditText addressLine1;
    private EditText addressLine2;
    private EditText cityText;
    private EditText countryText;
    private EditText postalCodeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_request_pickup_set_dropoff_location);

        addressLine1 = (EditText)findViewById(R.id.addressline1);
        addressLine2 = (EditText)findViewById(R.id.addressline2);
        cityText = (EditText)findViewById(R.id.city);
        countryText = (EditText)findViewById(R.id.country);
        postalCodeText = (EditText)findViewById(R.id.postalcode);

        //checks to see if user had previously entered an address
        //if so, auto-fills the fields with that information
        String address = getIntent().getStringExtra("endaddress");
        if (address != null) {
            String[] addresses = address.split("/");
            if (addresses.length > 0) {
                String address1 = addresses[0];
                addressLine1.setText(address1, TextView.BufferType.EDITABLE);
            }
            if (addresses.length == 2) {
                addressLine2.setText(addresses[1], TextView.BufferType.EDITABLE);
            }
        }
        String city = getIntent().getStringExtra("endcity");
        if ((city != null) && (!city.equals(""))) cityText.setText(city, TextView.BufferType.EDITABLE);
        String country = getIntent().getStringExtra("endcountry");
        if ((country != null) && (!country.equals(""))) countryText.setText(country, TextView.BufferType.EDITABLE);
        String postalcode = getIntent().getStringExtra("endpostalcode");
        if ((postalcode != null) && (!postalcode.equals(""))) {
            postalCodeText.setText(postalcode, TextView.BufferType.EDITABLE);
        }
        else {
            Bundle coords = getIntent().getParcelableExtra("endbundle");
            if (coords != null) {
                LatLng coordinates = coords.getParcelable("endcoordinates");
                if (coordinates != null) { markerLatLng = coordinates; }
            }
        }

        findViewById(R.id.map).setVisibility(View.GONE);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        new OnMapAndViewReadyListener(mapFragment, this);

        final Button nextButton = findViewById(R.id.farmerRequestPickupSetDropoffLocationNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                addressLine1 = (EditText)findViewById(R.id.addressline1);
                addressLine2 = (EditText)findViewById(R.id.addressline2);
                cityText = (EditText)findViewById(R.id.city);
                countryText = (EditText)findViewById(R.id.country);
                postalCodeText = (EditText)findViewById(R.id.postalcode);

                final String endaddress = addressLine1.getText().toString() + "/" + addressLine2.getText().toString();
                final String endcity = cityText.getText().toString();
                final String endcountry = countryText.getText().toString();
                final String endpostalcode = postalCodeText.getText().toString();

                Intent farmerRequestPickupSetDropoffLocationIntent = new Intent
                        (FarmerRequestPickupSetDropoffLocation.this,
                        Loading.class);

                if ((!endaddress.equals("") && !endcity.equals("") &&
                        !endcountry.equals("") && !endpostalcode.equals("")) ||
                        markerLatLng != null) {
                    String phonenumber = getIntent().getStringExtra("phonenumber");
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("phonenumber", phonenumber);
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("locationtype", getIntent().getStringExtra("locationtype"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("address", getIntent().getStringExtra("address"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("date", getIntent().getStringExtra("date"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("time", getIntent().getStringExtra("time"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("crop", getIntent().getStringExtra("crop"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("metric", getIntent().getStringExtra("metric"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("amount", getIntent().getStringExtra("amount"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("startaddress", getIntent().getStringExtra("startaddress"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("startcity", getIntent().getStringExtra("startcity"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("startcountry", getIntent().getStringExtra("startcountry"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("startpostalcode", getIntent().getStringExtra("startpostalcode"));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("endaddress", endaddress);
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("endcity", endcity);
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("endcountry", endcountry);
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("endpostalcode", endpostalcode);

                    Bundle bundle = getIntent().getParcelableExtra("startbundle");
                    LatLng coords = bundle.getParcelable("startcoordinates");
                    Bundle args = new Bundle();
                    args.putParcelable("startcoordinates", coords);
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("startbundle", args);

                    Bundle args2 = new Bundle();
                    args2.putParcelable("endcoordinates", markerLatLng);
                    farmerRequestPickupSetDropoffLocationIntent.putExtra("endbundle", args2);

                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("myDate", getIntent().getIntExtra("myDate", 0));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("myMonth", getIntent().getIntExtra("myMonth", 0));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("myAM", getIntent().getBooleanExtra("myAM",false));
                    farmerRequestPickupSetDropoffLocationIntent.putExtra
                            ("myPM", getIntent().getBooleanExtra("myPM",false));
                    
                    startActivity(farmerRequestPickupSetDropoffLocationIntent);
                }
                else {
                    showToast("Ingrese información para todos los campos de dirección" +
                            " o suelte su ubicación para continuar.");
                }
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupSetDropoffLocationBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent farmerRequestPickupSetDropoffLocationIntent = new Intent(FarmerRequestPickupSetDropoffLocation.this,
                        FarmerRequestPickupSetPickupLocation.class);

                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("locationtype", getIntent().getStringExtra("locationtype"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("phonenumber", getIntent().getStringExtra("phonenumber"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("startaddress", getIntent().getStringExtra("startaddress"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("startcountry", getIntent().getStringExtra("startcountry"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("startpostalcode", getIntent().getStringExtra("startpostalcode"));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("startcity", getIntent().getStringExtra("startcity"));
                Bundle bundle = getIntent().getParcelableExtra("startbundle");
                LatLng coords = bundle.getParcelable("startcoordinates");
                Bundle args = new Bundle();
                args.putParcelable("startcoordinates", coords);
                farmerRequestPickupSetDropoffLocationIntent.putExtra("startbundle", args);

                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("myDate", getIntent().getIntExtra("myDate", 0));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("myMonth", getIntent().getIntExtra("myMonth", 0));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("myAM", getIntent().getBooleanExtra("myAM",false));
                farmerRequestPickupSetDropoffLocationIntent.putExtra
                        ("myPM", getIntent().getBooleanExtra("myPM",false));

                startActivity(farmerRequestPickupSetDropoffLocationIntent);
            }
        });

        /*
         * TODO use the geocoder and reverse geocoder to turn addresses into LatLng and vice versa
         * TODO for the toggle buttons,
         * TODO   if the user enters an address and switches to drop pin, a marker at that location
         * TODO   if the user drops a pin and switches to address, auto-fill the address for that pin
         */

        final ToggleButton enterAddressButton = findViewById(R.id.farmerRequestPickupSetDropoffLocationEnterAddressButton);
        final ToggleButton dropPinButton = findViewById(R.id.farmerRequestPickupSetDropoffLocationDropPinButton);

        enterAddressButton.setChecked(true);
        dropPinButton.setChecked(false);

        //checks which toggle is selected and loads the text fields or map fragment
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

    private void setAddressTextView(){
        EditText addressLine1 = findViewById(R.id.addressline1);
        EditText country = findViewById(R.id.country);
        EditText addressLine2 = findViewById(R.id.addressline2);
        EditText postalCode = findViewById(R.id.postalcode);
        EditText city = findViewById(R.id.city);
        addressLine1.setVisibility(View.VISIBLE);
        addressLine2.setVisibility(View.VISIBLE);
        city.setVisibility(View.VISIBLE);
        country.setVisibility(View.VISIBLE);
        postalCode.setVisibility(View.VISIBLE);
        findViewById(R.id.map).setVisibility(View.GONE);
    }

    private void setDropPinTextView() {
        EditText addressLine1 = findViewById(R.id.addressline1);
        EditText country = findViewById(R.id.country);
        EditText addressLine2 = findViewById(R.id.addressline2);
        EditText postalCode = findViewById(R.id.postalcode);
        EditText city = findViewById(R.id.city);
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

        Toast.makeText(this,
                "Suelte su ubicación o ingrese su información para continuar.",
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
