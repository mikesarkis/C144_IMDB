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
import com.IMDB.dto.Company;
import com.IMDB.dto.Location;
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
public class adminControllerCompanies {
    @Autowired
    companyDao compdao;
    @Autowired
    locationDao locdao;
    @Autowired
    movieDao movdao;
    @Autowired
    userDao usedao;
    
    
   @GetMapping("Companies")
    public String DisplayHeros(Model model){
        List<Company> Companylist = compdao.get_all_compaines();
        model.addAttribute("Companies", Companylist);
        return "AdminCompanies";
    }
   @GetMapping("addCompany")
     public String addCompany(HttpServletRequest request)
     {
         return "AddCompany";
     }
     
      @PostMapping("addCompany")
      public String AddCompany(HttpServletRequest request) {
          String Name = request.getParameter("Name");
          String address = request.getParameter("address");
          Company temp = new Company();
          temp.setName(Name);
          temp.setAddress(address);
          compdao.add_company(temp);
          return "redirect:/AdminCompanies";
      }
          @GetMapping("editcompany")
      public String editLocation(HttpServletRequest request, Model model) {
          int id = Integer.parseInt(request.getParameter("id"));
          Company temp = compdao.get_company_by_id(id);
          model.addAttribute("company", temp);
          return "EditLocation";
      }
      @PostMapping("EditCompany")
      public String EditCompany(HttpServletRequest request){
          int id = Integer.parseInt(request.getParameter("id"));
          String Name = request.getParameter("Name");
          String address = request.getParameter("address");
          Company temp = new Company();
          temp.setName(Name);
          temp.setAddress(address);
          compdao.edit_company(id, temp);
          return "redirect:/AdminCompanies";
      }
      @GetMapping("deleteCompany")
          public String deleteCompany(HttpServletRequest request) {
          int id = Integer.parseInt(request.getParameter("id"));
          String Name = request.getParameter("Name");
          String address = request.getParameter("address");
          Company temp = new Company();
          temp.setName(Name);
          temp.setAddress(address);
          compdao.remove_company(temp);
          return "redirect:/AdminCompanies";
      }
      @GetMapping("getMovies")
      public String getMovies (HttpServletRequest request, Model model){
         int id = Integer.parseInt(request.getParameter("id"));
         List<Movie> list1 = compdao.get_all_movies(id);
         model.addAttribute("getMovies", list1);
         return "DisplayMoviesCompanies";
     }
}
