/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dao.userDaoDB.UserMapper;
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
//            movie.setOriginal_title(rs.getString("original_title "));
            movie.setOverview(rs.getString("overview"));
//            movie.setVote_average(rs.getDouble("vote_average"));
//            movie.setVote_count(rs.getInt("vote_count"));
//            movie.setOriginal_language(rs.getString("original_language"));
//            movie.setBackdrop_path(rs.getString("backdrop_path"));
            movie.setRelease_date(rs.getString("release_date"));
            movie.setAdult(rs.getBoolean("adult"));
//            movie.setVideo(rs.getBoolean("video"));
            movie.setPoster_path(rs.getString("poster_path"));
            return movie;
        }
        
    }

    @Override
    public Movie add_Movie(Movie temp) {
        final String insert_movie = "INSERT INTO movie (id, title, overview, release_date, popularity, poster_path, adult, rating, favorite) VALUES(?,?,?,?,?,?,?,?,?);";
        jdbc.update(insert_movie,
                temp.getId(),
                temp.getTitle(),
                temp.getOverview(),
                temp.getRelease_date(),
                temp.getPopularity(),
                temp.getPoster_path(),
                temp.isAdult(),
                temp.getRating(),
                temp.isFavorite());
        
        return temp;
    }
    
    @Override
    public List<Movie> getAllSavedFavorites() {
        final String getAllFavorites = "SELECT * FROM movie;";
        
        return jdbc.query(getAllFavorites, new MovieMapper());
    }
    
//    @Override
//    public Movie add_Movie(Movie temp) {
//        final String insert_movie = "INSERT INTO movie(title,original_title,overview,vote_average,vote_count ,original_language,backdrop_path,release_date,release_date,adult,video, poster_path   ) "+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//        jdbc.update(insert_movie, temp.getTitle(), temp.getOriginal_title(),temp.getOverview(), temp.getVote_average(), temp.getVote_count(), temp.getOriginal_language(), temp.getBackdrop_path(), temp.getRelease_date(), temp.isAdult(), temp.isVideo(), temp.getPoster_path());
//        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
//        temp.setId(newID);
//        return temp;
//    }
    
    
    @Override
    public void add_company_movie(int companyid, int movieid) {
       final String insert_relation = "INSERT INTO movie_company(movieID, productionCompanyID) "+ " VALUES(?,?)";
       jdbc.update(insert_relation, companyid, movieid);
    }
    @Override
    public void add_Location_movie(int locationid, int movieid, String year) {
       final String insert_relation = "INSERT INTO movie_location(movieID, FilmingLocationID, year) "+ " VALUES(?,?,?)";
       jdbc.update(insert_relation, movieid, locationid, year);
    }   

    @Override
    public void remove_Movie(Movie temp) {
//        final String delete_relation = "DELETE * FROM movie_location WHERE movieID = ?";
//        jdbc.update(delete_relation, temp.getId());
        final String delete_movie = "DELETE FROM movie WHERE id = ?";
        jdbc.update(delete_movie, temp.getId());
    }

    @Override
    public void edit_Movie(int id, Movie temp) {
//        final String update_movie = "UPDATE movie SET title = ? , original_title = ? , overview = ? ,   vote_average = ? ,  vote_count = ?,  original_language = ? , backdrop_path = ? , release_date = ? , adult = ?, video = ?,poster_path = ?  "
//                + " WHERE id = ?";
//        jdbc.update(update_movie, temp.getTitle(), temp.getOriginal_title(),temp.getOverview(), temp.getVote_average(), temp.getVote_count(), temp.getOriginal_language(), temp.getBackdrop_path(), temp.getRelease_date(), temp.isAdult(), temp.isVideo(), temp.getPoster_path() , id );
    }

    @Override
    public Movie get_Movie_by_id(int id) {
        final String get_Movie = "SELECT * FROM movie  WHERE id=? ";
        Movie temp = jdbc.queryForObject(get_Movie , new MovieMapper(), id);
        return temp;
    }
    @Override
    public List<Movie> get_Movie_by_title(String title) {
        final String get_Movie_by_title = "SELECT * FROM movie  WHERE title=? ";
        List<Movie> movie_by_title_list = jdbc.query(get_Movie_by_title , new MovieMapper(), title);
        return movie_by_title_list; 
    }

    @Override
    public List<Movie> get_all_Movies() {
        final String get_all_Movies = "SELECT * FROM movie ";
        List<Movie> list_movies = jdbc.query(get_all_Movies , new MovieMapper());
        return list_movies;
    }

    @Override
    public List<Location> get_all_Locations(int id) {
         final String get_all_locations_for_movie = "SELECT * FROM location l "
            +" JOIN  movie_location t ON t.FilmingLocationID = l.id "
            +" JOIN movie m on m.id = t.movieID  WHERE m.id = ?";
        List<Location> Location_list = jdbc.query(get_all_locations_for_movie , new locationDaoDB.LocationMapper() ,id);
        return Location_list;
    }

    @Override
    public List<Company> get_all_compaines(int id) {
        final String get_all_compaines_for_movie = "SELECT * FROM company c "
            +" JOIN  movie_company t ON t.productionCompanyID = c.id "
            +" JOIN movie m on m.id = t.movieID  WHERE m.id = ?";
        List<Company> compaines_list = jdbc.query(get_all_compaines_for_movie , new companyDaoDB.CompanyMapper() ,id);
        return compaines_list;
    }

    @Override
    public List<User> get_all_users(int id) {
       final String get_all_users_for_movie = "SELECT u.username FROM user u "
            +" JOIN  movie_user t ON t.userID = u.id "
            +" JOIN movie m on m.id = t.movieID  WHERE m.id = ?";
        List<User> users_list = jdbc.query(get_all_users_for_movie , new  UserMapper(),id);
        return users_list;
    }
    
}
