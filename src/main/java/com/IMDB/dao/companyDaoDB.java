/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IMDB.dao;

import com.IMDB.dao.movieDaoDB.MovieMapper;
import com.IMDB.dto.Company;
import com.IMDB.dto.Movie;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        final String insert_company = "INSERT INTO company(name,address) "+ " VALUES(?,?)";
        jdbc.update(insert_company, temp.getName(), temp.getAddress());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        temp.setId(newID);
        return temp;
    }
    


    @Override
    @Transactional
    public void remove_company(Company temp) {
        List<Movie> list_movies = get_all_movies(temp.getId());
        for(Movie movie : list_movies)
        {
            try {
                if(check_if_movie_to_delete(movie.getId(), temp.getId()))
                {
                    final String delete_movie_for_company = "DELETE * FROM movie WHERE id = ?";
                    jdbc.update(delete_movie_for_company, movie.getId());
                }
            } catch (SQLException ex) {
                Logger.getLogger(companyDaoDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        final String delete_relation = "DELETE * FROM movie_company WHERE productionCompanyID = ?";
        jdbc.update(delete_relation, temp.getId());
        final String delete_company = "DELETE * FROM company WHERE id = ?";
        jdbc.update(delete_company, temp.getId());
        
    }

    @Override
    public void edit_company(int id, Company temp) {
        final String update_company = "UPDATE company SET name=? , address = ? "
                + " WHERE id = ?";
        jdbc.update(update_company, temp.getName(), temp.getAddress(), id );
    }

    @Override
    public Company get_company_by_id(int id) {
        final String get_company = "SELECT * FROM company WHERE id=? ";
        return jdbc.queryForObject(get_company, new CompanyMapper(), id);
    }

    @Override
    public List<Company> get_all_compaines() {
        final String get_all_companies = "SELECT * FROM company ";
        return jdbc.query(get_all_companies , new CompanyMapper());
    }

    @Override
    public List<Movie> get_all_movies(int id) {
        final String get_all_movies_for_company= "SELECT * FROM movie m "
                +" JOIN  movie_company t ON t.movieID = m.id "
                +" JOIN company c on c.id = t.productionCompanyID  WHERE c.id = ?";
        List<Movie> movie_list = jdbc.query(get_all_movies_for_company, new MovieMapper() ,id);
        return movie_list;
    }
    private boolean check_if_movie_to_delete(int movieid, int companyid) throws SQLException
    {
        String movieidString = String.valueOf(movieid);
        String companyidString = String.valueOf(companyid);
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/IMDB","root","");
        Statement s = conn.createStatement();
        ResultSet r = s.executeQuery("SELECT COUNT(movieID) AS companycount FROM movie_company WHERE movieID = "+ movieidString + " AND productionCompanyID = "+ companyidString);
        r.next();
        int count = r.getInt("companycount") ;
        r.close() ;
        if(count > 1)
        {
            return false;
        }
        else
            return true;
        
    }

    
}
