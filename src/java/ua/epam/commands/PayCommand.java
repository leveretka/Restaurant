/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.model.dao.CustomerDAO;
import ua.epam.model.dao.OrderDAO;
import ua.epam.model.entity.Customer;
import ua.epam.model.factory.DAOFactory;

/**
 *
 * @author margarita
 */
public class PayCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession httpSession = request.getSession();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);
        OrderDAO orderDAO = factory.getOrderDAO();
        
        orderDAO.pay(Integer.parseInt(request.getParameter("order_id")));
        
        CustomerDAO customerDAO = factory.getCustomerDAO();
        
        Customer customer = new Customer();
        customer.setLogin((String) httpSession.getAttribute("login"));
        
        Integer id = customerDAO.getCustomerId(customer);
        customer.setId(id);
        
        customerDAO.closeConn();

        httpSession.setAttribute("notpayed", orderDAO.getNotPayed(customer));
        orderDAO.closeConn();
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/payment.jsp");
                
        rd.forward(request, response);
    }
    
}
