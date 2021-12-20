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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    
    @GetMapping("search")
    public String searchResults(HttpServletRequest request, Model model) throws Exception {
        String query = request.getParameter("search");
        URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + KeyHolder.movieKey + "&language=en-US&query=" + query + "&include_adult=false");
        List<Movie> movies = callAPI(url);
        
        model.addAttribute("movies", movies);
        return "movies";
    }
    
    public List<Movie> callAPI(URL queryURL) {
    
        HttpURLConnection connection = null;
    
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        
        System.out.println("START");
        
        try {
//            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + KeyHolder.movieKey + "&language=en-US&query=" + query + "&include_adult=false");
//            URL url = new URL("https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + KeyHolder.movieKey + "&language=en-US");
            connection = (HttpURLConnection) queryURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
        System.out.println("MIDDLE");
            
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
            
//            parseObj(responseContent.toString());

            
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
            JSONObject movie = movies.getJSONObject(i);
            String title = movie.getString("title");
            String story = movie.getString("overview");
            String date = movie.getString("release_date");
            double rating = movie.getDouble("vote_average");
            
            Movie newMovie = new Movie();
            newMovie.setTitle(title);
            newMovie.setStory(story);
            newMovie.setProduction_year(date);
            newMovie.setRating(rating);
            newMovie.setProduction_year(date);
            
//            System.out.println(String.valueOf(newMovie.getRating()));
            
            movieList.add(newMovie);
            
        }
        return movieList;
    }
    
    public static String parseObj(String responseBody) {
        JSONObject obj = new JSONObject(responseBody);
        System.out.println(obj.getString("imdb_id"));
        return null;
        
    }
}
