/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Location;
import com.IMDB.dto.Movie;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface locationDao {
    public Location add_Location(Location temp);
    public Location remove_Location(Location temp);
    public Location edit_Location(int id, Location temp);
    public Location get_Location_by_id(int id);
    public List<Location> get_all_Locations();
    public List<Movie> get_all_movies(int id);
    
}
