/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Movie;
import com.IMDB.dto.User;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface userDao {
    public User add_User(User temp);
    public void remove_User(User temp);
    public void edit_User(int id, User temp);
    public User get_User_by_id(int id);
    public void rate_movie(int movieid, int userid, double rating);
    public List<User> get_all_Users();
    public List<Movie> get_all_movies(int id);
    
}
