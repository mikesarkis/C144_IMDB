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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Mike
 */
@Repository
public class movieDaoDB implements movieDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class MovieMapper implements RowMapper<Movie>{

        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setDirector(rs.getString("director"));
            movie.setStory(rs.getString("story"));
            movie.setRatring(rs.getDouble("ratring"));
            movie.setMpprating(rs.getString("mpprating"));
            movie.setProduction_year(rs.getString("production_year"));
            return movie;
        }
        
    }

    @Override
    public Movie add_Movie(Movie temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie remove_Movie(Movie temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie edit_Movie(int id, Movie temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie get_Movie_by_id(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> get_all_Movies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> get_all_Locations(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> get_all_compaines(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> get_all_users(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
