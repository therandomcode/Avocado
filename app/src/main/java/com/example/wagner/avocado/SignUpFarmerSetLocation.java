package com.example.wagner.avocado;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.os.ResultReceiver;
import android.os.Handler;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;


public class SignUpFarmerSetLocation extends AppCompatActivity implements
        OnMarkerClickListener,
        OnMapClickListener,
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener {

    private Bundle bundle;

    private static final String TAG = SignUpFarmerSetLocation.class.getSimpleName();

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private static final String ADDRESS_REQUESTED_KEY = "address-request-pending";
    private static final String LOCATION_ADDRESS_KEY = "location-address";

    /**
     * Represents a geographical location.
     */
    private Location mLastLocation;

    /**
     * Tracks whether the user has requested an address. Becomes true when the user requests an
     * address and false when the address (or an error message) is delivered.
     */
    private boolean mAddressRequested;

    /**
     * The formatted location address.
     */
    private String mAddressOutput;

    /**
     * Receiver registered with this activity to get the response from FetchAddressIntentService.
     */
    private AddressResultReceiver mResultReceiver;

    /**
     * Displays the location address.
     */
    private TextView mLocationAddressTextView;

    /**
     * Visible while the address is being fetched.
     */
    private ProgressBar mProgressBar;

    /**
     * Kicks off the request to fetch an address when pressed.
     */
    private Button mFetchAddressButton;

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
    private Geocoder geocoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_set_location);

        bundle = savedInstanceState;

        addressLine1 = (EditText)findViewById(R.id.addressline1);
        addressLine2 = (EditText)findViewById(R.id.addressline2);
        cityText = (EditText)findViewById(R.id.city);
        countryText = (EditText)findViewById(R.id.country);
        postalCodeText = (EditText)findViewById(R.id.postalcode);

        geocoder = new Geocoder(SignUpFarmerSetLocation.this);
        mResultReceiver = new AddressResultReceiver(new Handler());
        mAddressRequested = false;
        mAddressOutput = "";
        updateValuesFromBundle(bundle);

        String address = getIntent().getStringExtra("address");
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

        findViewById(R.id.map).setVisibility(View.GONE);

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

                    Intent myIntent = new Intent(SignUpFarmerSetLocation.this, SignUpFarmerAddPhotos.class);

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
                    showToast("Please enter information for all of the address" +
                            " fields or drop a pin to continue.");
                }
            }
        });

        final Button backButton = findViewById(R.id.signUpSetLocationBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerSetLocation.this, CreateAccount.class);
                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("user", "farmer");
                startActivity(myIntent);
            }
        });

        final Button skipButton = findViewById(R.id.signUpSetLocationSkipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");

                DatabaseHandler db = new DatabaseHandler();

                db.insertFarmer(firstname, lastname, phonenumber, password, "", ""
                        , "", "", "[]", "[]", "0");


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

                Intent myIntent = new Intent(SignUpFarmerSetLocation.this, SignUpLater.class);
                myIntent.putExtra("firstname", firstname);
                myIntent.putExtra("lastname", lastname);
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putExtra("password", password);
                myIntent.putExtra("address", address);
                myIntent.putExtra("country", country);
                myIntent.putExtra("postalcode", postalcode);
                myIntent.putExtra("city", city);
                myIntent.putExtra("screen", "FarmerSetLocation");

                myIntent.putExtra("type", "farmer");

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
        Toast.makeText(this, "Going to your location", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
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
        updateAddress();
        if (mLastMarker != null) mLastMarker.remove();
        mLastMarker = mMap.addMarker(new MarkerOptions().position(point));
        Toast.makeText(this, "Setting your farm's location", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getAddress();
        }
    }

    /**
     * Updates fields based on data stored in the bundle.
     */
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Check savedInstanceState to see if the address was previously requested.
            if (savedInstanceState.keySet().contains(ADDRESS_REQUESTED_KEY)) {
                mAddressRequested = savedInstanceState.getBoolean(ADDRESS_REQUESTED_KEY);
            }
            // Check savedInstanceState to see if the location address string was previously found
            // and stored in the Bundle. If it was found, display the address string in the UI.
            if (savedInstanceState.keySet().contains(LOCATION_ADDRESS_KEY)) {
                mAddressOutput = savedInstanceState.getString(LOCATION_ADDRESS_KEY);
                displayAddressOutput();
            }
        }
    }

    /**
     * Runs when user clicks the Fetch Address button.
     */
    @SuppressWarnings("unused")
    public void fetchAddressButtonHandler(View view) {
        if (mLastLocation != null) {
            startIntentService();
            return;
        }

        // If we have not yet retrieved the user location, we process the user's request by setting
        // mAddressRequested to true. As far as the user is concerned, pressing the Fetch Address button
        // immediately kicks off the process of getting the address.
        mAddressRequested = true;
    }

    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    private void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    @SuppressWarnings("MissingPermission")
    private void getAddress() {
        Location location = new Location("Farm Location");
        location.setLatitude(markerLatLng.latitude);
        location.setLongitude(markerLatLng.longitude);
        mLastLocation = location;

        // If the user pressed the fetch address button before we had the location,
        // this will be set to true indicating that we should kick off the intent
        // service after fetching the location.
        if (mAddressRequested) {
            startIntentService();
        }
    }

    /**
     * Updates the address in the UI.
     */
    private void displayAddressOutput() {
        mLocationAddressTextView.setText(mAddressOutput);
    }

    /**
     * Shows a toast with the given text.
     */
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save whether the address has been requested.
        savedInstanceState.putBoolean(ADDRESS_REQUESTED_KEY, mAddressRequested);

        // Save the address string.
        savedInstanceState.putString(LOCATION_ADDRESS_KEY, mAddressOutput);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Receiver for data sent from FetchAddressIntentService.
     */
    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            displayAddressOutput();

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                showToast(getString(R.string.address_found));
            }

            // Reset. Enable the Fetch Address button and stop showing the progress bar.
            mAddressRequested = false;
        }
    }
    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(SignUpFarmerSetLocation.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getAddress();
            }
        }
    }

    private void updateAddress() {
        addressLine1.setText(mAddressOutput, TextView.BufferType.EDITABLE);
    }
}
