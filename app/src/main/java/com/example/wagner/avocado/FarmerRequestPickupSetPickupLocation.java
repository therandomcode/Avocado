package com.example.wagner.avocado;

import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import android.location.Geocoder;
import android.location.Address;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.List;


public class FarmerRequestPickupSetPickupLocation extends AppCompatActivity implements
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
        setContentView(R.layout.activity_farmer_request_pickup_set_pickup_location);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        new OnMapAndViewReadyListener(mapFragment, this);

        addressLine1 = (EditText)findViewById(R.id.addressline1);
        addressLine2 = (EditText)findViewById(R.id.addressline2);
        cityText = (EditText)findViewById(R.id.city);
        countryText = (EditText)findViewById(R.id.country);
        postalCodeText = (EditText)findViewById(R.id.postalcode);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        String templocation = getIntent().getStringExtra("locationtype");
        if ( (templocation != null) && !templocation.equals("")) {
            if (templocation.equals("My Farm")) {
                rg.check(R.id.myFarm);
            } else if (templocation.equals("Farm")) {
                rg.check(R.id.myFarm);
            } else if (templocation.equals("Town Centre")) {
                rg.check(R.id.townCentre);
            } else if (templocation.equals("Church")) {
                rg.check(R.id.church);
            } else {
                EditText other = findViewById(R.id.otherText);
                other.setText(templocation);
            }
        }





        String address = getIntent().getStringExtra("startaddress");
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
        final String city = getIntent().getStringExtra("startcity");
        if ((city != null) && (!city.equals(""))) cityText.setText(city, TextView.BufferType.EDITABLE);
        String country = getIntent().getStringExtra("startcountry");
        if ((country != null) && (!country.equals(""))) countryText.setText(country, TextView.BufferType.EDITABLE);
        String postalcode = getIntent().getStringExtra("startpostalcode");
        if ((postalcode != null) && (!postalcode.equals(""))) {
            postalCodeText.setText(postalcode, TextView.BufferType.EDITABLE);
        }
        else {
            Bundle coords = getIntent().getParcelableExtra("startbundle");
            if (coords != null) {
                LatLng coordinates = coords.getParcelable("startcoordinates");
                if (coordinates != null) { markerLatLng = coordinates; }
            }
        }

        // initially hides the map view
        findViewById(R.id.map).setVisibility(View.GONE);

        final Button nextButton = findViewById(R.id.farmerRequestPickupSetPickupLocationNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent farmerRequestPickupSetPickupLocationIntent = new Intent(FarmerRequestPickupSetPickupLocation.this,
                        FarmerRequestPickupSetDropoffLocation.class);

                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                int id = rg.getCheckedRadioButtonId();
                String locationtype;
                if (id == -1){
                    EditText edittext = (EditText)findViewById(R.id.otherText);
                    locationtype = edittext.getText().toString();
                }
                else {
                    RadioButton rb = (RadioButton)findViewById(id);
                    locationtype = rb.getText().toString();
                }

                addressLine1 = (EditText)findViewById(R.id.addressline1);
                addressLine2 = (EditText)findViewById(R.id.addressline2);
                cityText = (EditText)findViewById(R.id.city);
                countryText = (EditText)findViewById(R.id.country);
                postalCodeText = (EditText)findViewById(R.id.postalcode);

                final String startaddress = addressLine1.getText().toString() + "/" + addressLine2.getText().toString();
                final String startcity = cityText.getText().toString();
                final String startcountry = countryText.getText().toString();
                final String startpostalcode = postalCodeText.getText().toString();

                //checks to see if a location was selected and if a location type was selected
                //if not it pops a toast
                if (((startaddress.equals("") || startcity.equals("") ||
                        startcountry.equals("") || startpostalcode.equals("")) &&
                        markerLatLng == null)) {
                    showToast("Ingrese información para todos los campos de dirección" +
                            " o suelte su ubicación para continuar.");
                }
                else if ((locationtype == null) ||
                        locationtype.equals("")) {
                    showToast("Por favor seleccione un tipo de ubicación.");
                }
                else {
                    farmerRequestPickupSetPickupLocationIntent.putExtra("locationtype", locationtype);
                    String phonenumber = getIntent().getStringExtra("phonenumber");
                    farmerRequestPickupSetPickupLocationIntent.putExtra("phonenumber", phonenumber);
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

                    farmerRequestPickupSetPickupLocationIntent.putExtra("startaddress", startaddress);
                    farmerRequestPickupSetPickupLocationIntent.putExtra("startcity", startcity);
                    farmerRequestPickupSetPickupLocationIntent.putExtra("startcountry", startcountry);
                    farmerRequestPickupSetPickupLocationIntent.putExtra("startpostalcode", startpostalcode);

                    Bundle args = new Bundle();
                    args.putParcelable("startcoordinates", markerLatLng);
                    farmerRequestPickupSetPickupLocationIntent.putExtra("startbundle", args);

                    farmerRequestPickupSetPickupLocationIntent.putExtra
                            ("myDate", getIntent().getIntExtra("myDate",0));
                    farmerRequestPickupSetPickupLocationIntent.putExtra
                            ("myMonth", getIntent().getIntExtra("myMonth",0));
                    farmerRequestPickupSetPickupLocationIntent.putExtra
                            ("myAM", getIntent().getBooleanExtra("myAM",false));
                    farmerRequestPickupSetPickupLocationIntent.putExtra
                            ("myPM", getIntent().getBooleanExtra("myPM",false));

                    startActivity(farmerRequestPickupSetPickupLocationIntent);
                }
            }
        });

        final Button backButton = findViewById(R.id.farmerRequestPickupSetPickupLocationBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FarmerRequestPickupSetPickupLocation.this, FarmerRequestPickupPickDate.class);

                String phonenumber = getIntent().getStringExtra("phonenumber");
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putExtra
                        ("date", getIntent().getStringExtra("date"));
                myIntent.putExtra
                        ("time", getIntent().getStringExtra("time"));
                myIntent.putExtra
                        ("crop", getIntent().getStringExtra("crop"));
                myIntent.putExtra
                        ("metric", getIntent().getStringExtra("metric"));
                myIntent.putExtra
                        ("amount", getIntent().getStringExtra("amount"));
                myIntent.putExtra("myDate", getIntent().getIntExtra("myDate",0));
                myIntent.putExtra("myMonth", getIntent().getIntExtra("myMonth",0));
                myIntent.putExtra("myAM", getIntent().getBooleanExtra("myAM",false));
                myIntent.putExtra("myPM", getIntent().getBooleanExtra("myPM", false));
                startActivity(myIntent);
            }
        });

        /*
         * TODO use the geocoder and reverse geocoder to turn addresses into LatLng and vice versa
         * TODO for the toggle buttons,
         * TODO   if the user enters an address and switches to drop pin, a marker at that location
         * TODO   if the user drops a pin and switches to address, auto-fill the address for that pin
         */

        final ToggleButton enterAddressButton = findViewById(R.id.farmerRequestPickupSetLocationEnterAddressButton);
        final ToggleButton dropPinButton = findViewById(R.id.farmerRequestPickupSetLocationDropPinButton);

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
                "Configure su ubicación o ingrese su dirección.",
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
        markerLatLng = point;
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
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }

}
