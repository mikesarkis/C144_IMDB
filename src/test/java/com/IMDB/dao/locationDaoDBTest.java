/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Company;
import com.IMDB.dto.Location;
import com.IMDB.dto.Movie;
import com.IMDB.dto.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Mike
 */
public class locationDaoDBTest {
    @Autowired
    companyDao compdao;
    @Autowired
    locationDao locdao;
    @Autowired
    movieDao movdao;
    @Autowired
    userDao usedao;
    
    public locationDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    @BeforeEach
    public void setUp() {
        List<Movie> movies = movdao.get_all_Movies();
        for(Movie movie: movies )
        {
            movdao.remove_Movie(movie);
        }
        List<Company> companies = compdao.get_all_compaines();
        for(Company company: companies )
        {
            compdao.remove_company(company);
        }
        List<Location> locations = locdao.get_all_Locations();
        for(Location location: locations )
        {
            locdao.remove_Location(location);
        }
        List<User> users = usedao.get_all_Users();
        for(User user: users )
        {
            usedao.remove_User(user);
        }
 
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        Location loc = new Location();
        loc.setStreetNumber(78);
        loc.setStreet("75 Ave");
        loc.setCity("Chicago");
        loc.setCountry("USA");
        Location locfromdao= locdao.add_Location(loc);
        loc.setId(locfromdao.getId());
        assertEquals(loc,locfromdao);
    }
    
}
