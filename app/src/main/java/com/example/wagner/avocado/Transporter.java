package com.example.wagner.avocado;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Wagner on 20-Nov-17.
 */

public class Transporter {
    private Integer Day;

    //for the request
    //Arraylist will have available days as a pair:
    //[day, time]
    private Hashtable<Integer, String> available_days;

    //for the profile page
    private String firstname;
    private String lastname;
    private String bio;
    private String rating;
    private String vehicle;
    private String address;
    private String clients;
    private String car_make;
    private Integer imgid;

    public Transporter()
    {
    }

    public Transporter(String firstname, String lastname, String address, String car_make,
                       Integer imgid, Hashtable<Integer, String> available_days)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.address=address;
        this.car_make=car_make;
        this.imgid = imgid;
        this.available_days=available_days;
    }


    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCarMake(String car_make) { this.car_make = car_make; }

    public void setImgid(Integer imgid) { this.imgid = imgid; }

    public void setHash(Hashtable<Integer,String> hash) {
        this.available_days = hash;
    }

    public boolean isAvailable(Integer day, String t1){
        String t2 = available_days.get(day);
        if (t2 == null) return false;
        switch(t2){
            case "NEVER":
                return false;
            case "AM":
                return t1.equals("AM");
            case "PM":
                return t1.equals("PM");
            case "ALL DAY":
                return true;
        }
        return false;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getTimes() {
        Enumeration days = available_days.keys();
        Enumeration times = available_days.elements();
        String available_days_string = "";
        while(days.hasMoreElements() && times.hasMoreElements()) {
            int day = (int)(days.nextElement());
            available_days_string += "(" + day + ", " + times.nextElement() + ")";
        }
        return available_days_string;
    }

    public String getCarMake() { return car_make; }

    public String getImgid() { return Integer.toString(imgid); }

    public Hashtable<Integer, String> getHash() {
        return available_days;
    }
}
