package com.example.projetosi_henriqueneves.model;

import java.util.Date;

public class Game {

    private int id;
    private double price;
    private String name, coverbase64, description, dev_name, pub_name;
    private Date releasedate;

    public Game(int id, double price, String coverbase64, String name, String description, String dev_name, String pub_name, Date releasedate) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.coverbase64 = coverbase64;
        this.description = description;
        this.dev_name = dev_name;
        this.pub_name = pub_name;
        this.releasedate = releasedate;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverbase64() {
        return coverbase64;
    }

    public void setCoverbase64(String coverbase64) {
        this.coverbase64 = coverbase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //    private String name;
//    private String coverUrl;
//    private String description;
//    private double price;
//    private String developer;
//    private String publisher;
//    private String releaseDate;
    // Getters and setters
}
