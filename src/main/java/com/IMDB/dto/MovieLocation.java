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
public class MovieLocation {
    private int id;
    private int movieId;
    private int locationID;
    private String date;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
