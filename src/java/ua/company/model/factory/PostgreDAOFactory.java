/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.model.factory;

import java.sql.SQLException;
import ua.company.model.dao.MealDAO;
import ua.company.model.dao.OrderDAO;
import ua.company.model.dao.CustomerDAO;
import ua.company.model.dao.PostgreCustomerDAO;
import ua.company.model.dao.PostgreMealDAO;
import ua.company.model.dao.PostgreOrderDAO;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;

/**
 *
 * @author margarita
 */
public class PostgreDAOFactory extends DAOFactory{

    static PoolingDataSource dataSource = null;
    
    static PostgreDAOFactory instance = new PostgreDAOFactory();
    
    private static Logger logger;

    public static PostgreDAOFactory getInstance() {
        return instance;
    }
    
    private PostgreDAOFactory() {
        initSource();
    }
    
    @Override
    public MealDAO getMealDAO() {
        try {
            return new PostgreMealDAO(dataSource.getConnection());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public OrderDAO getOrderDAO() {
        try {
            return new PostgreOrderDAO(dataSource.getConnection());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        try {
            return new PostgreCustomerDAO(dataSource.getConnection());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    
    private void initSource(/*Logger logger*/) {

        // load the underlying driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
            System.exit(1);
        }
            
        ConnectionFactory connectionFactory = null;
        ObjectPool connectionPool = null;
        PoolableConnectionFactory poolableConnectionFactory = null;

        // Build the DSN: jdbc:postgresql://host:port/database
        String buf = "jdbc:postgresql://127.0.0.1:5432/Restaurant";
 
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("initialSize", "50");
        
        props.setProperty("maxActive", "25");

        connectionFactory = new DriverManagerConnectionFactory(buf, props);
        connectionPool = new GenericObjectPool(null);
        poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);
 
        dataSource = new PoolingDataSource(connectionPool);
    }
    
}
