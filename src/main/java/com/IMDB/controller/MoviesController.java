/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.controller;

import com.IMDB.KeyHolder;
import com.IMDB.dao.companyDao;
import com.IMDB.dao.locationDao;
import com.IMDB.dao.movieDao;
import com.IMDB.dao.userDao;
import com.IMDB.dto.Movie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Mike
 */
@Controller
public class MoviesController {
    @Autowired
    companyDao compdao;
    @Autowired
    locationDao locdao;
    @Autowired
    movieDao movdao;
    @Autowired
    userDao usedao;
//    @GetMapping("Movies")
//    public String DisplayHeros(Model model){
//        List<Movie> movielist = movdao.get_all_Movies();
//        model.addAttribute("Movies", movielist);
//        return "Movies";
//    }
    
    private List<Movie> movies;
    
    
    @GetMapping("search")
    public String searchResults(HttpServletRequest request, Model model) throws Exception {
        String query = request.getParameter("search");
        URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + KeyHolder.movieKey + "&language=en-US&query=" + query + "&include_adult=false");
        movies = callAPI(url);
        
        model.addAttribute("movies", movies);
        return "movies";
    }
    
    
    @GetMapping("displayMovie")
    public String displayMovieById(int id, Model model) {
        System.out.println(id);
        Movie movie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
        
        model.addAttribute(movie);
        return "movie";
    }
    
    public List<Movie> callAPI(URL queryURL) {
    
        HttpURLConnection connection = null;
    
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        
        try {
            connection = (HttpURLConnection) queryURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
                        
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            
            return parseObjArr(responseContent.toString());
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        
        return null;
    }
    
    
    public static List<Movie> parseObjArr(String reponseBody) {
        JSONObject obj = new JSONObject(reponseBody);
        JSONArray movies = obj.getJSONArray("results");
        
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < movies.length(); i++) {
            
            // this variable needs to be initiated here
            // to prevent crashing is API value is "null"
            String imageURL = "";
            
            JSONObject movie = movies.getJSONObject(i);
            int id = movie.getInt("id");
            String title = movie.getString("title");
            String overview = movie.getString("overview");
            String release_date = movie.getString("release_date");
            double popularity = movie.getDouble("popularity");
            
            // needs "optString" instead of "getString" for nulls
            // along with initiating variable above so it can be
            // an empty string is API call returns "null"
            imageURL = movie.optString("poster_path");
            
                        
            Movie newMovie = new Movie();
            newMovie.setId(id);
            newMovie.setTitle(title);
            newMovie.setOverview(overview);
            newMovie.setRelease_date(release_date);
            newMovie.setPopularity(popularity);
            newMovie.setPoster_path("https://image.tmdb.org/t/p/original/" + imageURL);
            
            movieList.add(newMovie);
            
        }
        
        // sort movie list by popularity
            Collections.sort(movieList, new Comparator<Movie>() {
                @Override public int compare(Movie m1, Movie m2) {
                    return Double.compare(m2.getPopularity(), m1.getPopularity());
                }
            });
        
        return movieList;
    }
    
    public static String parseObj(String responseBody) {
        JSONObject obj = new JSONObject(responseBody);
        System.out.println(obj.getString("imdb_id"));
        return null;
        
    }
}
