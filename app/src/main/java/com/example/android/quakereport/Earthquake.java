package com.example.android.quakereport;

public class Earthquake {
    //Member variable to store magnitude of the EarthQuake
    private Double mEarthquakeMagnitude;

    //Member variable to store the city of the Earthquake
    private String mEarthquakeLocation;

    //Variable to store Date of the EarthQuake
    private Long mTimeInMilliSeconds;

    //Variable to store URL
    private String mURL;

    public Earthquake(Double magnitude, String location, Long timeInMilliSeconds, String url){
        mEarthquakeMagnitude = magnitude;
        mEarthquakeLocation = location;
        mTimeInMilliSeconds = timeInMilliSeconds;
        mURL = url;
    }

    public String getEarthquakeLocation() {
        return mEarthquakeLocation;
    }

    public Double getEarthquakeMagnitude() {
        return mEarthquakeMagnitude;
    }

    public Long getEarthquakeTime(){
        return mTimeInMilliSeconds;
    }

    public  String getEarthquakeURL(){
        return mURL;
    }
}
