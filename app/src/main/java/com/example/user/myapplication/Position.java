package com.example.user.myapplication;

import java.io.Serializable;

/**
 * Created by Paula on 2017-05-08.
 */

public class Position implements Serializable {

    private int id;
    private String name;
    private String longitude;
    private String latitude;

    //Empty constructor
    public Position(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position() {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //getting name
    public String getName() {
        return name;
    }

    //setting name
    public void setName(String name) {
        this.name = name;
    }

    //getting name
    public String getLongitude() {
        return longitude;
    }

    //setting name
    public void setLongitude(String longtitude) {
        this.longitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String toString() {
        return id - 1 + " " + name; //nie powinno byc -1 ale nie wiem gdzie jest blad
    }
}

