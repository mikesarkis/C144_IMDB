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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Mike
 */
@Controller
public class adminController {
    @Autowired
    companyDao compdao;
    @Autowired
    locationDao locdao;
    @Autowired
    movieDao movdao;
    @Autowired
    userDao usedao;
    
    
    

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
          return "redirect:/Admin";
      }

      
    
}
