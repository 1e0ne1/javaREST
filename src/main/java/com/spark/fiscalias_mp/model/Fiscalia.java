/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.model;

/**
 *
 * @author Leonel
 */
public class Fiscalia {
    private int id;
    private String name;
    private String dept;
    private String phone;
    private String lat;
    private String lon;

    public Fiscalia(int id, String name, String dept, String phone, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;
    }

    public Fiscalia(){
        this.id=0;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
    
    
}
