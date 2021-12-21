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
import com.IMDB.dto.Location;
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
public class adminControllerLocations {
    @Autowired
    companyDao compdao;
    @Autowired
    locationDao locdao;
    @Autowired
    movieDao movdao;
    @Autowired
    userDao usedao;
    
    
        
    @GetMapping("Locations")
    public String DisplayHeros(Model model){
        List<Location> Locationlist = locdao.get_all_Locations();
        model.addAttribute("Locations", Locationlist);
        return "AdminLocations";
    }
    
    @GetMapping("addLocation")
     public String addLocation(HttpServletRequest request)
     {
         return "AddLocation";
     }
      @PostMapping("addLocation")
      public String AddLocation(HttpServletRequest request) {
          int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
          String street = request.getParameter("street");
          String city = request.getParameter("city");
          String country = request.getParameter("country");
          Location temp = new Location();
          temp.setStreetNumber(streetNumber);
          temp.setStreet(street);
          temp.setCity(city);
          temp.setCountry(country);
          locdao.add_Location(temp);
          return "redirect:/AdminLocations";
      }


      @GetMapping("editLocation")
      public String editLocation(HttpServletRequest request, Model model) {
          int id = Integer.parseInt(request.getParameter("id"));
          Location temp = locdao.get_Location_by_id(id);
          model.addAttribute("location", temp);
          return "EditLocation";
      }
      @PostMapping("EditLocation")
      public String EditLocation(HttpServletRequest request){
          int id = Integer.parseInt(request.getParameter("id"));
          int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
          String street = request.getParameter("street");
          String city = request.getParameter("city");
          String country = request.getParameter("country");
          Location temp = new Location();
          temp.setId(id);
          temp.setStreetNumber(streetNumber);
          temp.setStreet(street);
          temp.setCity(city);
          temp.setCountry(country);
          locdao.edit_Location(id, temp);
          return "redirect:/AdminLocations";
      }
         @GetMapping("deleteLocation")
          public String deleteLocation(HttpServletRequest request) {
          int id = Integer.parseInt(request.getParameter("id"));
          int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
          String street = request.getParameter("street");
          String city = request.getParameter("city");
          String country = request.getParameter("country");
          Location temp = new Location();
          temp.setId(id);
          temp.setStreetNumber(streetNumber);
          temp.setStreet(street);
          temp.setCity(city);
          temp.setCountry(country);
          locdao.remove_Location(temp);
          return "redirect:/AdminLocations";
      }
}
