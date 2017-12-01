package com.example.wagner.avocado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by kyleesantos on 10/27/17.
 */


public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "transportersInfo";
    // Contacts table name
    private static final String TABLE_TRANSPORTERS = "transporters";
    // Transporters Table Columns names
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_TIMES = "availability";
    private static final String KEY_CROPS = "crops";

    private enum Time{
        NEVER, AM, PM, ALL_DAY
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TRANSPORTERS + "("
                + KEY_FIRSTNAME + " TEXT," + KEY_LASTNAME + " TEXT," + KEY_ADDRESS + " TEXT,"
                + KEY_TIMES + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTERS);
// Creating tables again
        onCreate(db);
    }

    // Adding new transporter
    public void addTransporter(Transporter transporter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, transporter.getFirstName()); // Transporter First Name
        values.put(KEY_LASTNAME, transporter.getLastName()); // Transporter Last Name
        values.put(KEY_ADDRESS, transporter.getAddress()); //Transporter Address
        values.put(KEY_TIMES, transporter.getTimes()); // Transporter Times
// Inserting Row
        db.insert(TABLE_TRANSPORTERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one transporter
    public Transporter getTransporter(String lastname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRANSPORTERS, new String[]{KEY_FIRSTNAME,
                        KEY_LASTNAME, KEY_ADDRESS, KEY_TIMES}, KEY_LASTNAME + "=?",
                new String[]{String.valueOf(lastname)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Transporter contact = new Transporter(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),
                string_to_hash(cursor.getString(3)));
        db.close();
        return contact;
    }

    // Converts a string of comma separated times to a hashtable
    private Hashtable<Integer, String> string_to_hash(String times) {
        Hashtable<Integer, String> times_hash = new Hashtable<>();
        String[] times_array = times.split("\\)" + "\\(");
        int length = times_array.length;
        String first_time = times_array[0];
        times_array[0] = first_time.substring(1);
        String last_time = times_array[length-1];
        times_array[length-1] = last_time.substring(0,last_time.length()-1);

        for (int index = 0; index < length; index++){
            String date_and_times = times_array[index];
            String[] day_time_indiv = date_and_times.split(", ");
            Integer key = Integer.valueOf(day_time_indiv[0]);
            String t = day_time_indiv[1];
            times_hash.put(key, t);
        }
        return times_hash;
    }

    // Converts a string of comma separated crops to an arraylist of strings
    private ArrayList<String> string_to_arraylist(String crops) {
        ArrayList<String> crops_arraylist = new ArrayList<String>(Arrays.asList(crops.split(", ")));
        return crops_arraylist;
    }

    // Getting All Transporters
    public List<Transporter> getAllTransporters() {
        List<Transporter> transporterList = new ArrayList<Transporter>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TRANSPORTERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transporter transporter = new Transporter();
                transporter.setFirstName(cursor.getString(0));
                transporter.setLastName(cursor.getString(1));
                transporter.setAddress(cursor.getString(2));
                transporter.setHash(string_to_hash(cursor.getString(3)));
// Adding contact to list
                transporterList.add(transporter);
            } while (cursor.moveToNext());
        }
        db.close();
// return contact list
        return transporterList;
    }

//    // Getting transporters Count
//    public int getTransportersCount() {
//        String countQuery = "SELECT * FROM " + TABLE_TRANSPORTERS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//        db.close();
//// return count
//        return cursor.getCount();
//    }
//
//    // Updating a transporter
//    public int updateTransporter(Transporter transporter) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_FIRSTNAME, transporter.getFirstName());
//        db.close();
//// updating row
//        return db.update(TABLE_TRANSPORTERS, values, KEY_LASTNAME + " = ?",
//                new String[]{String.valueOf(transporter.getLastName())});
//    }

    // Deleting a transporter
    public void deleteTransporter(Transporter transporter) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSPORTERS, KEY_LASTNAME + " = ?",
                new String[] { String.valueOf(transporter.getLastName()) });
        db.close();
    }
}