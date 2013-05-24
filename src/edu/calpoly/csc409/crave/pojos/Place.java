package edu.calpoly.csc409.crave.pojos;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    protected String mName;
    protected double mDistance;
    protected LatLng mLatLng;

    public Place(String name, double distance, LatLng latLng) {
        this.mName = name;
        this.mDistance = distance;
        this.mLatLng = latLng;

        // Truncate to two decimal points
        this.mDistance = Math.floor(this.mDistance * 100) / 100;
    }

    public String getName() {
        return mName;
    }

    public double getDistance() {
        return mDistance;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }
}
