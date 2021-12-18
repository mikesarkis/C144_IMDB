/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dto.Company;
import com.IMDB.dto.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mike
 */
@Repository
public class companyDaoDB implements companyDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class CompanyMapper implements RowMapper<Company>{

        @Override
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            Company company = new Company();
            company.setId(rs.getInt("id"));
            company.setName(rs.getString("name"));
            company.setAddress(rs.getString("address"));
            return company;
        }
        
    }

    @Override
    public Company add_company(Company temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company remove_company(Company temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company edit_company(int id, Company temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company get_company_by_id(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> get_all_compaines() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> get_all_movies(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
