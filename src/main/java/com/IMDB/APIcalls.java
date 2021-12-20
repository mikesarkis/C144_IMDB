/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.IMDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class APIcalls {
    
    private static HttpURLConnection connection;
    
    public static void main(String[] args) {
        
        // -=-=-=-=-=-=-=-=-=-=-=-=
        // NON-ASYNC - doesn't work 
        // -=-=-=-=-=-=-=-=
//        String url = "https://jsonplaceholder.typicode.com/albums";
//
//        
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
//.build();
//        client.sendAsync(request, BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenApply(Main::parse)
//                .join();
        
        
        
        // -=-=-=-=-=-=-=-=-=-=-=-=
        // NON-ASYNC
        // -=-=-=-=-=-=-=-=-=-=-=-=
        
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("What movie are you searching for?");
        String query = scanner.nextLine();
        String movieID = "438631";
        
        try {
//            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + KeyHolder.movieKey + "&language=en-US&query=" + query + "&include_adult=false");
            URL url = new URL("https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + KeyHolder.movieKey + "&language=en-US");
            connection = (HttpURLConnection) url.openConnection();
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
            
//            parseObjArr(responseContent.toString());
            parseObj(responseContent.toString());

            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
    
    public static String parseObjArr(String reponseBody) {
        JSONObject obj = new JSONObject(reponseBody);
        JSONArray movies = obj.getJSONArray("results");

        for (int i = 0; i < movies.length(); i++) {
            JSONObject movie = movies.getJSONObject(i);
            String title = movie.getString("title");
            Float rating = movie.getFloat("vote_average");
            String date = movie.getString("release_date");
            System.out.println(title + " - " + rating + " - " + date);
        }
        return null;
    }
    
    public static String parseObj(String responseBody) {
        JSONObject obj = new JSONObject(responseBody);
        System.out.println(obj.getString("imdb_id"));
        return null;
        
    }
 
}

// movieSearch = "https://api.themoviedb.org/3/search/movie?api_key=" + KeyHolder.movieKey + "&language=en-US&query=" + query + "&include_adult=false"
// movieInfo = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + KeyHolder.movieKey + "&language=en-US"