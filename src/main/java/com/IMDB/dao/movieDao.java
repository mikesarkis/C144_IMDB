/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Company;
import com.IMDB.dto.Location;
import com.IMDB.dto.Movie;
import com.IMDB.dto.User;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface movieDao {
    public Movie add_Movie(Movie temp);
    public Movie remove_Movie(Movie temp);
    public Movie edit_Movie(int id, Movie temp);
    public Movie get_Movie_by_id(int id);
    public List<Movie> get_all_Movies();
    public List<Location> get_all_Locations(int id);
    public List<Company> get_all_compaines(int id);
    public List<User> get_all_users(int id);
    
}
