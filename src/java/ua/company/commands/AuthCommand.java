/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import ua.company.model.factory.DAOFactory;
import ua.company.model.entity.Meal;
import ua.company.model.dao.MealDAO;
import ua.company.model.dao.CustomerDAO;
import ua.company.model.dao.OrderDAO;
import ua.company.model.entity.Customer;
import ua.company.model.entity.Order;


/**
 *
 * @author margarita
 */
public class AuthCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPass(password);
        
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);
        
        CustomerDAO customerDAO = factory.getCustomerDAO();
        
        if (customerDAO.find(customer)) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("login", login);

            MealDAO mealDAO = factory.getMealDAO();
            List<Meal> meals = mealDAO.getAllMeals();
            
            mealDAO.closeConn();
            
            httpSession.setAttribute("meals", meals);
            
            Map<Meal, Integer> mealsInOrder = new HashMap<>();
            httpSession.setAttribute("meals in order", mealsInOrder);
            
            customer.setId(customerDAO.getCustomerId(customer));
            customerDAO.closeConn();

            OrderDAO orderDAO = factory.getOrderDAO();

            if (customer.getLogin().equals("admin")) {
                List<Order> notConfirmed = orderDAO.getNotConfirmed();
                httpSession.setAttribute("notconfirmed", notConfirmed);
                
                orderDAO.closeConn();
                RequestDispatcher rd = request.getRequestDispatcher("/pages/confirm.jsp");
                
                rd.forward(request, response);
            } else {
                List<Order> notPayed = orderDAO.getNotPayed(customer);
                httpSession.setAttribute("notpayed", notPayed);
                orderDAO.closeConn();
                
                RequestDispatcher rd = request.getRequestDispatcher("/pages/payment.jsp");
                    
                rd.forward(request, response);
            }
                
        } else {
                
            RequestDispatcher rd = request.getRequestDispatcher("/pages/incorrect.jsp");
                
            rd.forward(request, response);
        }
    }
    
}
