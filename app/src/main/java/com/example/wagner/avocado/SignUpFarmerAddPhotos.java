package com.example.wagner.avocado;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class SignUpFarmerAddPhotos extends AppCompatActivity {

    private int RESULT_LOAD_IMAGE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_farmer_add_photos);

        final Button addPhotosButton = findViewById(R.id.signUpFarmerAddPhotosButton);
        addPhotosButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                showToast("We need to ask the user permission.");
                showPhoneStatePermission();

                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        final Button finishButton = findViewById(R.id.signUpFarmerAddPhotosFinishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast("Thanks for adding photos!");

                Intent myIntent = new Intent(SignUpFarmerAddPhotos.this,
                        SignUpFarmerAddProfile.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String city = getIntent().getStringExtra("city");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");

                myIntent.putExtra("firstname", firstname);
                myIntent.putExtra("lastname", lastname);
                myIntent.putExtra("phonenumber", phonenumber);
                myIntent.putExtra("password", password);
                myIntent.putExtra("address", address);
                myIntent.putExtra("city", city);
                myIntent.putExtra("country", country);
                myIntent.putExtra("postalcode", postalcode);

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);

                startActivity(myIntent);
            }
        });

        final Button backButton = findViewById(R.id.signUpFarmerAddPhotosBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddPhotos.this,
                        SignUpFarmerSetLocation.class);
                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("address", getIntent().getStringExtra("address"));
                myIntent.putExtra("city", getIntent().getStringExtra("city"));
                myIntent.putExtra("postalcode", getIntent().getStringExtra("postalcode"));
                myIntent.putExtra("country", getIntent().getStringExtra("country"));
                myIntent.putExtra("user", "farmer");

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);
                startActivity(myIntent);
            }
        });

        final Button skipButton = findViewById(R.id.signUpSetLocationSkipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpFarmerAddPhotos.this,
                        SignUpLater.class);

                String firstname = getIntent().getStringExtra("firstname");
                String lastname = getIntent().getStringExtra("lastname");
                String phonenumber = getIntent().getStringExtra("phonenumber");
                String password = getIntent().getStringExtra("password");
                String address = getIntent().getStringExtra("address");
                String city = getIntent().getStringExtra("city");
                String country = getIntent().getStringExtra("country");
                String postalcode = getIntent().getStringExtra("postalcode");

                DatabaseHandler db = new DatabaseHandler();
                db.insertFarmer(firstname, lastname, phonenumber, password, address, country,
                        postalcode, city);

                myIntent.putExtra("firstname", getIntent().getStringExtra("firstname"));
                myIntent.putExtra("lastname", getIntent().getStringExtra("lastname"));
                myIntent.putExtra("phonenumber", getIntent().getStringExtra("phonenumber"));
                myIntent.putExtra("address", getIntent().getStringExtra("address"));
                myIntent.putExtra("city", getIntent().getStringExtra("city"));
                myIntent.putExtra("postalcode", getIntent().getStringExtra("postalcode"));
                myIntent.putExtra("country", getIntent().getStringExtra("country"));
                myIntent.putExtra("user", "farmer");
                myIntent.putExtra("screen", "FarmerAddPhotos");

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng coords = bundle.getParcelable("coordinates");
                Bundle args = new Bundle();
                args.putParcelable("coordinates", coords);
                myIntent.putExtra("bundle", args);

                startActivity(myIntent);
            }
        });
    }

 private void showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SignUpFarmerAddPhotos.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showExplanation("Permission Needed",
                        "Rationale",
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            } else {
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            }
        } else {
            Toast.makeText(SignUpFarmerAddPhotos.this,
                    "Permission (already) Granted!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE =1;
    private final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE =2;
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Read Permission Granted!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,
                            "Read Permission Denied!",
                            Toast.LENGTH_SHORT).show();
                }
            case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SignUpFarmerAddPhotos.this,
                            "Write Permission Granted!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpFarmerAddPhotos.this,
                            "Write Permission Denied!",
                            Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        showToast("RequestPermission method started");
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }

    //This is a complete misuse of the toast method sorry
    private void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            // String picturePath contains the path of selected Image
            ImageView imageView = findViewById(R.id.farmerAddFarmPhotosViewPhoto);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
