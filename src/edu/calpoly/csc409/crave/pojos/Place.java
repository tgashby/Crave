package edu.calpoly.csc409.crave.pojos;

public class Place {
    protected String mName;
    protected double mDistance;

    public Place(String name, double distance) {
        this.mName = name;
        this.mDistance = distance;

        // Truncate to two decimal points
        this.mDistance = Math.floor(this.mDistance * 100) / 100;
    }

    public String getName() {
        return mName;
    }

    public double getDistance() {
        return mDistance;
    }
}
