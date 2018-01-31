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
import android.widget.TextView;
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

public class SignUpTransporterSetLocation extends AppCompatActivity implements
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
        setContentView(R.layout.activity_sign_up_set_location_transporter);

        findViewById(R.id.map).setVisibility(View.GONE);

        addressLine1 = (EditText)findViewById(R.id.addressline1);
        addressLine2 = (EditText)findViewById(R.id.addressline2);
        cityText = (EditText)findViewById(R.id.city);
        countryText = (EditText)findViewById(R.id.country);
        postalCodeText = (EditText)findViewById(R.id.postalcode);

        final String address = getIntent().getStringExtra("address");
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
        String city = getIntent().getStringExtra("city");
        if ((city != null) && (!city.equals(""))) cityText.setText(city, TextView.BufferType.EDITABLE);
        String country = getIntent().getStringExtra("country");
        if ((country != null) && (!country.equals(""))) countryText.setText(country, TextView.BufferType.EDITABLE);
        String postalcode = getIntent().getStringExtra("postalcode");
        if ((postalcode != null) && (!postalcode.equals(""))) {
            postalCodeText.setText(postalcode, TextView.BufferType.EDITABLE);
        }
        else {
            Bundle coords = getIntent().getParcelableExtra("bundle");
            if (coords != null) {
                LatLng coordinates = coords.getParcelable("coordinates");
                if (coordinates != null) { markerLatLng = coordinates; }
            }
        }

        final Button nextButton = findViewById(R.id.signUpSetLocationNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");

                addressLine1 = (EditText)findViewById(R.id.addressline1);
                addressLine2 = (EditText)findViewById(R.id.addressline2);
                cityText = (EditText)findViewById(R.id.city);
                countryText = (EditText)findViewById(R.id.country);
                postalCodeText = (EditText)findViewById(R.id.postalcode);
                String addressLine1String = addressLine1.getText().toString();
                String addressLine2String = addressLine2.getText().toString();
                String cityString = cityText.getText().toString();
                String countryString = countryText.getText().toString();
                String postalCodeString = postalCodeText.getText().toString();

                if ((!addressLine1String.equals("") && !cityString.equals("") &&
                        !countryString.equals("") && !postalCodeString.equals("")) ||
                        markerLatLng != null) {
                    String address = addressLine1.getText().toString() + "/"
                            + addressLine2.getText().toString();

                    Intent myIntent = new Intent(SignUpTransporterSetLocation.this,
                            SignUpTransporterSetCarInfo.class);

                    myIntent.putExtra("firstname", firstname);
                    myIntent.putExtra("lastname", lastname);
                    myIntent.putExtra("phonenumber", phonenumber);
                    myIntent.putExtra("password", password);
                    myIntent.putExtra("address", address);
                    myIntent.putExtra("country", countryString);
                    myIntent.putExtra("postalcode", postalCodeString);
                    myIntent.putExtra("city", cityString);

                    Bundle args = new Bundle();
                    args.putParcelable("coordinates", markerLatLng);
                    myIntent.putExtra("bundle", args);

                    startActivity(myIntent);
                }
                else {
                    showToast("ngrese información para todos los campos de dirección" +
                            " o suelte su ubicación para continuar.");
                }
            }
        });

        final Button backButton = findViewById(R.id.signUpSetLocationBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterSetLocation.this, CreateAccount.class);
                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("password", getIntent().getStringExtra("password"));
                myIntent.putExtra("user", "transporter");
                startActivity(myIntent);
            }
        });

        final Button skipButton = findViewById(R.id.signUpSetLocationSkipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpTransporterSetLocation.this, SignUpLater.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");

                DatabaseHandler db = new DatabaseHandler();

                db.insertTransporter(firstname, lastname, "[]", "", "",
                        "", "", password, phonenumber, ""
                        , "", "", "[]", "0", "[]");
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("type", "transporter");

                addressLine1 = (EditText)findViewById(R.id.addressline1);
                addressLine2 = (EditText)findViewById(R.id.addressline2);
                cityText = (EditText)findViewById(R.id.city);
                countryText = (EditText)findViewById(R.id.country);
                postalCodeText = (EditText)findViewById(R.id.postalcode);

                String address = addressLine1.getText().toString() + "/"
                        + addressLine2.getText().toString();
                String country = countryText.getText().toString();
                String postalcode = postalCodeText.getText().toString();
                String city = cityText.getText().toString();

                myIntent.putExtra("firstname", firstname);
                myIntent.putExtra("lastname", lastname);
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putExtra("password", password);
                myIntent.putExtra("address", address);
                myIntent.putExtra("country", country);
                myIntent.putExtra("postalcode", postalcode);
                myIntent.putExtra("city", city);
                myIntent.putExtra("user", "transporter");

                myIntent.putExtra("screen", "TransporterSetLocation");
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
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}

