package com.anythingintellect.mydeliveries.model;

/**
 * Created by ishan.dhingra on 02/09/17.
 */

public class Delivery {

    private String description;
    private String imgUrl;
    private Location location;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
