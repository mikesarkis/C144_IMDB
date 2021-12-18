/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Location;
import com.IMDB.dto.Movie;
import com.IMDB.dto.MovieLocation;
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
public class locationDaoDB implements locationDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class LocationMapper implements RowMapper<Location>{

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setStreetNumber(rs.getInt("streetnumber"));
            location.setStreet(rs.getString("street"));
            location.setCity(rs.getString("city"));
            location.setCountry(rs.getString("country"));
            return location;
        }
        
    }
    public static final class MovieLocationMapper implements RowMapper<MovieLocation>{

        @Override
        public MovieLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
            MovieLocation movielocation = new MovieLocation();
            movielocation.setId(rs.getInt("id"));
            movielocation.setMovieId(rs.getInt("movieID"));
            movielocation.setLocationID(rs.getInt("FilmingLocationID"));
            movielocation.setDate(rs.getString("year"));
            return movielocation;
        }
        
    }

    @Override
    public Location add_Location(Location temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location remove_Location(Location temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location edit_Location(int id, Location temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location get_Location_by_id(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> get_all_Locations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> get_all_movies(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
