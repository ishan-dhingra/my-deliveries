package com.anythingintellect.mydeliveries.model;

import io.realm.RealmObject;

/**
 * Created by ishan.dhingra on 02/09/17.
 */

public class Location extends RealmObject {

    private double lat;
    private double lng;
    private String address;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
