/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Company;
import com.IMDB.dto.Movie;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface companyDao {
    public Company add_company(Company temp);
    public Company remove_company(Company temp);
    public Company edit_company (int id, Company temp);
    public Company get_company_by_id(int id);
    public List<Company> get_all_compaines();
    public List<Movie> get_all_movies(int id);
    
}
