/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dto;

/**
 *
 * @author Mike
 */
public class Movie {
    private int id;
    private double ratring;
    private String title;
    private String director;
    private String story;
    private String Mpprating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRatring() {
        return ratring;
    }

    public void setRatring(double ratring) {
        this.ratring = ratring;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMpprating() {
        return Mpprating;
    }

    public void setMpprating(String Mpprating) {
        this.Mpprating = Mpprating;
    }

    public String getProduction_year() {
        return production_year;
    }

    public void setProduction_year(String production_year) {
        this.production_year = production_year;
    }
    private String production_year;    
}
