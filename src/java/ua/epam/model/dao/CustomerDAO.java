/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import ua.epam.model.entity.Customer;
import ua.epam.model.entity.Meal;
import java.util.List;

/**
 *
 * @author margarita
 */
public interface CustomerDAO {

    final static String FIND = "SELECT COUNT(*) FROM customers WHERE login = ?"; 
    final static String ALL = "SELECT * FROM customers"; 
    final static String INSERT = "INSERT INTO customers(login, pass, e_mail, tel) VALUES(?, ?, ?, ?)";
    final static String DELETE = "DELETE FROM customers WHERE login=?, pass=?, e_mail=?, tel=?";
    final static String CUST_ID = "SELECT id FROM customers WHERE login = ?";
 
    boolean insert(Customer customer)throws SQLException;
    boolean find(Customer customer)throws SQLException;
    boolean delete(Customer customer) throws SQLException;
    List<Customer> getAllCustomers()throws SQLException;
    Integer getCustomerId(Customer customer) throws SQLException;
    
    void closeConn() throws SQLException;
}
