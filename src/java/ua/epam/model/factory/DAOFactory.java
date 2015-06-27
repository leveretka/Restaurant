/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.factory;

import ua.epam.model.dao.MealDAO;
import ua.epam.model.dao.OrderDAO;
import ua.epam.model.dao.CustomerDAO;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author margarita
 */
public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int POSTGRE = 1;

    public abstract MealDAO getMealDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract CustomerDAO getCustomerDAO();

    public static DAOFactory getDAOFactory(int whichFactory/*, Logger logger*/) {
  
        switch (whichFactory) {
            case POSTGRE: 
                PostgreDAOFactory p = PostgreDAOFactory.getInstance(/*logger*/);
                return p;
            default: 
                return null;
        }
    }
 
}
