/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.factory;

import java.io.IOException;
import java.sql.SQLException;
import ua.epam.model.dao.MealDAO;
import ua.epam.model.dao.OrderDAO;
import ua.epam.model.dao.CustomerDAO;
import ua.epam.model.dao.PostgreCustomerDAO;
import ua.epam.model.dao.PostgreMealDAO;
import ua.epam.model.dao.PostgreOrderDAO;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

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
