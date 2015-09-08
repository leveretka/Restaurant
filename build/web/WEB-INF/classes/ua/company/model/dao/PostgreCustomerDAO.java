/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ua.company.model.entity.Customer;
import java.util.List;

/**
 *
 * @author margarita
 */
public class PostgreCustomerDAO implements CustomerDAO{
    
    private Connection conn;

    public PostgreCustomerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(ALL);
        
        List<Customer> customers = new ArrayList<>();
        
        while (rs.next()) {
            
            Customer customer = new Customer();
            customer.setId(rs.getInt(1));
            customer.setLogin(rs.getString(2));
            customer.setPass(rs.getString(3));
            customer.setE_mail(rs.getString(4));
            customer.setTel(rs.getString(5));
            customers.add(customer);
        }
        
        return customers;
    }

    @Override
    public boolean insert(Customer customer) throws SQLException {
        
        if (!find(customer)) {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            prepareStatement(statement, customer);
            statement.execute();
            return true;            
        }
        
        return false;
    }

    @Override
    public boolean find(Customer customer) throws SQLException {
     
        PreparedStatement statement = conn.prepareStatement(FIND);

        statement.setString(1, customer.getLogin());
            
        ResultSet rs = statement.executeQuery();
        rs.next();
            
        if (rs.getInt(1) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    private void prepareStatement(PreparedStatement statement, Customer customer) throws SQLException {
       
        statement.setString(1, customer.getLogin());
        statement.setString(2, customer.getPass());
        statement.setString(3, customer.getE_mail());
        statement.setString(4, customer.getTel());
        
    }

    @Override
    public boolean delete(Customer customer) throws SQLException {
        
        if (!find(customer)) {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            prepareStatement(statement, customer);
            statement.execute();
            return true;            
        }
        
        return false;
   }

    @Override
    public Integer getCustomerId(Customer customer) throws SQLException {
        if (find(customer)) {
            PreparedStatement statement = conn.prepareStatement(CUST_ID);
            statement.setString(1, customer.getLogin());
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        
        return null;
    }
    
    public void closeConn() throws SQLException {
        conn.close();
    }
}
