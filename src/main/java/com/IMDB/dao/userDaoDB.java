/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Movie;
import com.IMDB.dto.User;
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

/**
 *
 * @author Mike
 */
@Repository
public class userDaoDB implements userDao{
    
    @Autowired
    JdbcTemplate jdbc;


    
    public static final class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setIsAdmin(rs.getBoolean("isAdmin"));
            return user;
        }
        
    }

    @Override
    public User add_User(User temp) {
        final String insert_user = "INSERT INTO user(username,password,email,isAdmin) "+ " VALUES(?,?,?,?)";
        jdbc.update(insert_user, temp.getUsername(), temp.getPassword(), temp.getEmail(), temp.isIsAdmin());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        temp.setId(newID);
        return temp;
    }

    @Override
    public void remove_User(User temp) {
               
        List<Movie> list_movies =   get_all_movies(temp.getId());     
        final String delete_relation = "DELETE * FROM movie_user WHERE userID = ?";
        jdbc.update(delete_relation, temp.getId());
        final String delete_user = "DELETE * FROM user WHERE id = ?";
        jdbc.update(delete_user, temp.getId());
        for (Movie movie: list_movies)
        {
            update_movie_rating(movie.getId());
        }
    }

    @Override
    public void edit_User(int id, User temp) {
        final String update_user = "UPDATE user SET username = ? , password = ? , email = ? , isAdmin = ?  "
            + " WHERE id = ?";
        jdbc.update(update_user, temp.getUsername(), temp.getPassword(), temp.getEmail(), temp.isIsAdmin() , id );
    }

    @Override
    public User get_User_by_id(int id) {
        final String get_User = "SELECT * FROM user  WHERE id = ? ";
        return jdbc.queryForObject(get_User , new UserMapper(), id);
    }

    @Override
    public List<User> get_all_Users() {
        final String get_all_Users = "SELECT * FROM user ";
        return jdbc.query(get_all_Users , new UserMapper());
    }
    @Override
    public void rate_movie(int movieid, int userid, double rating) {
        final String rate_movie = "INSERT INTO movie_user(movieID,userID,rating) "+" VALUES(?,?,?)";
        jdbc.update(rate_movie, movieid, userid, rating);
        update_movie_rating(movieid);
        
    }

    @Override
    public List<Movie> get_all_movies(int id) {
        final String get_all_movies_for_user = "SELECT * FROM movie m "
            +" JOIN  movie_user t ON t.movieID = m.id "
            +" JOIN user u on u.id = t.userID  WHERE u.id = ?";
        List<Movie>movie_list = jdbc.query(get_all_movies_for_user , new movieDaoDB.MovieMapper() ,id);
        return movie_list;
    }
    private void update_movie_rating(int movieid)
    {
       String movieidString = String.valueOf(movieid);
       final String updaterating = "INSERT INTO movie(rating) "+ " VALUES(?)";
       double rating= -1;
       final String get_rating = "SELECT AVG(rating) AS Rating FROM movie_user WHERE movieID = "; 
       java.sql.Connection conn = null;
        try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/IMDB","root","");
        Statement s = conn.createStatement();
        ResultSet r = s.executeQuery(get_rating+movieidString);
        r.next();
        rating = r.getDouble("Rating");
        r.close();
        jdbc.update(updaterating,rating );
        
        }catch (SQLException ex) {
            Logger.getLogger(movieDaoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
