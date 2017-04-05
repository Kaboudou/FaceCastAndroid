package org.btssio.slam.facecams.objects;

import java.util.Date;

/**
 * Created by kadim on 27/09/2016.
 */

public class Events {
    private String nom;
    private String type;
    private String date;
    private String place;

    public Events(String nom, String type, String date, String place) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.place = place;
    }
    public Events(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
