/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.company.model.dao.CustomerDAO;
import ua.company.model.entity.Customer;
import ua.company.model.entity.Meal;
import ua.company.model.entity.Order;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import ua.company.model.dao.OrderDAO;
import ua.company.model.factory.DAOFactory;


/**
 *
 * @author margarita
 */
public class MakeOrderCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        HttpSession httpSession = request.getSession();
        
        Order order = new Order();
        order.setMeals((Map<Meal, Integer>) httpSession.getAttribute("meals in order"));
        String login = (String) httpSession.getAttribute("login");
        
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);
        CustomerDAO customerDAO = factory.getCustomerDAO();
        
        Customer customer = new Customer();
        customer.setLogin(login);
        
        Integer id = customerDAO.getCustomerId(customer);
        customer.setId(id);
        
        customerDAO.closeConn();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setCustomer(customer);
        
        OrderDAO orderDAO = factory.getOrderDAO();
        
        if (orderDAO.insert(order)) {
            httpSession.removeAttribute("meals in order");
            httpSession.setAttribute("meals in order", new HashMap<Meal, Integer>());
            orderDAO.closeConn();
            RequestDispatcher rd = request.getRequestDispatcher("/pages/ordermade.jsp");
            rd.forward(request, response);
        } else {
            orderDAO.closeConn();
            RequestDispatcher rd = request.getRequestDispatcher("/pages/ordererror.jsp");
            rd.forward(request, response);            
        }
        
    }
    
}
