package com.anythingintellect.mydeliveries.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ishan.dhingra on 02/09/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery extends RealmObject {

    // For internal DB use to main the order
    @PrimaryKey
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
