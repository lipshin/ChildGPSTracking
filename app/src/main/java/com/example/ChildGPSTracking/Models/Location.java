package com.example.ChildGPSTracking.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

    private String Latitude;
    private String Longitude;

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public Location(String x, String y){
        Latitude = x;
        Longitude = y;
    }

    public Location() {

    }

    protected Location(Parcel in){
        Latitude = in.readString();
        Longitude = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Latitude);
        dest.writeString(Longitude);
    }
}
