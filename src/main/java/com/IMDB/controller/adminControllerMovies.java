/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.controller;

import com.IMDB.dao.companyDao;
import com.IMDB.dao.locationDao;
import com.IMDB.dao.movieDao;
import com.IMDB.dao.userDao;
import com.IMDB.dto.Movie;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Mike
 */
public class adminControllerMovies {
    @Autowired
    companyDao compdao;
    @Autowired
    locationDao locdao;
    @Autowired
    movieDao movdao;
    @Autowired
    userDao usedao;
    
    
    @GetMapping("Movies")
    public String DisplayHeros(Model model){
        List<Movie> Movielist = movdao.get_all_Movies();
        model.addAttribute("Movies", Movielist);
        return "AdminMovies";
    }
    
    @GetMapping("addMovie")
     public String addMovie(HttpServletRequest request)
     {
         return "AddMovie";
     }
    
    @PostMapping("addMovie")
      public String AddMovie(HttpServletRequest request) {
          String Title = request.getParameter("Title");
          String original_title = request.getParameter("original_title");
          String overview = request.getParameter("overview");
          double vote_average = Double.parseDouble(request.getParameter("vote_average"));
          int vote_count = Integer.parseInt(request.getParameter("vote_count"));
          String poster_path = request.getParameter("poster_path");
          String original_language = request.getParameter("original_language");
          String backdrop_path = request.getParameter("backdrop_path");
          String release_date = request.getParameter("release_date");
          boolean adult = Boolean.parseBoolean(request.getParameter("adult").toLowerCase());
          boolean video = Boolean.parseBoolean(request.getParameter("video").toLowerCase());
          Movie temp = new Movie();
          temp.setTitle(Title);
          temp.setOriginal_title(original_title);
          temp.setOverview(overview);
          temp.setVote_average(vote_average);
          temp.setVote_count(vote_count);
          temp.setPoster_path(poster_path);
          temp.setOriginal_language(original_language);
          temp.setBackdrop_path(backdrop_path);
          temp.setRelease_date(release_date);
          temp.setAdult(adult);
          temp.setVideo(video);
          movdao.add_Movie(temp);
          return "redirect:/AdminMovies";
      }
       @GetMapping("editMovie")
      public String editMovie(HttpServletRequest request, Model model) {
          int id = Integer.parseInt(request.getParameter("id"));
          Movie temp = movdao.get_Movie_by_id(id);
          model.addAttribute("movie", temp);
          return "EditMovie";
      }
      @PostMapping("editMovie")
      public String EditMovie(HttpServletRequest request){
          int id = Integer.parseInt(request.getParameter("id"));
          String Title = request.getParameter("Title");
          String original_title = request.getParameter("original_title");
          String overview = request.getParameter("overview");
          double vote_average = Double.parseDouble(request.getParameter("vote_average"));
          int vote_count = Integer.parseInt(request.getParameter("vote_count"));
          String poster_path = request.getParameter("poster_path");
          String original_language = request.getParameter("original_language");
          String backdrop_path = request.getParameter("backdrop_path");
          String release_date = request.getParameter("release_date");
          boolean adult = Boolean.parseBoolean(request.getParameter("adult").toLowerCase());
          boolean video = Boolean.parseBoolean(request.getParameter("video").toLowerCase());
          Movie temp = new Movie();
          temp.setTitle(Title);
          temp.setOriginal_title(original_title);
          temp.setOverview(overview);
          temp.setVote_average(vote_average);
          temp.setVote_count(vote_count);
          temp.setPoster_path(poster_path);
          temp.setOriginal_language(original_language);
          temp.setBackdrop_path(backdrop_path);
          temp.setRelease_date(release_date);
          temp.setAdult(adult);
          temp.setVideo(video);
          movdao.edit_Movie(id, temp);
          return "redirect:/AdminMovies";
      }
        @GetMapping("deleteMovie")
          public String deleteMovie(HttpServletRequest request) {
          int id = Integer.parseInt(request.getParameter("id"));
          String Title = request.getParameter("Title");
          String original_title = request.getParameter("original_title");
          String overview = request.getParameter("overview");
          double vote_average = Double.parseDouble(request.getParameter("vote_average"));
          int vote_count = Integer.parseInt(request.getParameter("vote_count"));
          String poster_path = request.getParameter("poster_path");
          String original_language = request.getParameter("original_language");
          String backdrop_path = request.getParameter("backdrop_path");
          String release_date = request.getParameter("release_date");
          boolean adult = Boolean.parseBoolean(request.getParameter("adult").toLowerCase());
          boolean video = Boolean.parseBoolean(request.getParameter("video").toLowerCase());
          Movie temp = new Movie();
          temp.setId(id);
          temp.setTitle(Title);
          temp.setOriginal_title(original_title);
          temp.setOverview(overview);
          temp.setVote_average(vote_average);
          temp.setVote_count(vote_count);
          temp.setPoster_path(poster_path);
          temp.setOriginal_language(original_language);
          temp.setBackdrop_path(backdrop_path);
          temp.setRelease_date(release_date);
          temp.setAdult(adult);
          temp.setVideo(video);
          movdao.remove_Movie(temp);
          return "redirect:/AdminMovies";
      }
}
