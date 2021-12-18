/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dao.movieDaoDB.MovieMapper;
import com.IMDB.dto.Location;
import com.IMDB.dto.Movie;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    public Location add_Location(Location temp) {
        final String insert_location = "INSERT INTO location(streetnumber,street,city,country) "+ " VALUES(?,?,?,?)";
        jdbc.update(insert_location, temp.getStreetNumber(), temp.getStreet(), temp.getCity(), temp.getCountry());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        temp.setId(newID);
        return temp;
    }

    @Override
    @Transactional
    public void remove_Location(Location temp) {
        List<Movie> list_movies = get_all_movies(temp.getId());
        for(Movie movie : list_movies)
        {
            try {
                if(check_if_movie_to_delete(movie.getId(), temp.getId()))
                {
                    final String delete_movie_for_company = "DELETE * FROM movie WHERE id = ?";
                    jdbc.update(delete_movie_for_company, movie.getId());
                }
            } catch (SQLException ex) {
                Logger.getLogger(companyDaoDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        final String delete_relation = "DELETE * FROM movie_location WHERE FilmingLocationID = ?";
        jdbc.update(delete_relation, temp.getId());
        final String delete_location = "DELETE * FROM location WHERE id = ?";
        jdbc.update(delete_location, temp.getId());
    }

    @Override
    public void edit_Location(int id, Location temp) {
        final String update_Location = "UPDATE location SET streetnumber = ? , street = ? , city = ? , country = ?  "
                + " WHERE id = ?";
        jdbc.update(update_Location, temp.getStreetNumber() , temp.getStreet(), temp.getCity(), temp.getCountry() , id );
    }

    @Override
    public Location get_Location_by_id(int id) {
        final String get_Location = "SELECT * FROM location  WHERE id = ? ";
        return jdbc.queryForObject(get_Location , new LocationMapper(), id);
        
    }

    @Override
    public List<Location> get_all_Locations() {
        final String get_all_Locations = "SELECT * FROM location ";
        return jdbc.query(get_all_Locations , new LocationMapper());
    }

    @Override
    public List<Movie> get_all_movies(int id) {
        final String get_all_movies_for_location = "SELECT * FROM movie m "
            +" JOIN  movie_location t ON t.movieID = m.id "
            +" JOIN location l on l.id = t.FilmingLocationID  WHERE l.id = ?";
        return jdbc.query(get_all_movies_for_location , new MovieMapper() ,id);
        
    }
    @Override
    public List<Movie> get_all_movies_for_date(String date) {
        final String get_all_movies_for_date = "SELECT * FROM movie m "
                +" JOIN  movie_location t ON t.movieID = m.id WHERE t.year = ?";
        return jdbc.query(get_all_movies_for_date , new MovieMapper(),date);
    }
        private boolean check_if_movie_to_delete(int movieid, int Locationid) throws SQLException
    {
        String movieidString = String.valueOf(movieid);
        String LocationidString = String.valueOf(Locationid);
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/IMDB","root","");
        Statement s = conn.createStatement();
        ResultSet r = s.executeQuery("SELECT COUNT(movieID) AS Locationcount FROM movie_location WHERE movieID = "+ movieidString + " AND FilmingLocationID = "+ LocationidString);
        r.next();
        int count = r.getInt("Locationcount") ;
        r.close() ;
        if(count > 1)
        {
            return false;
        }
        else
            return true;
        
    }
    
}
