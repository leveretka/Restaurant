/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;
import ua.company.model.factory.DAOFactory;
import ua.company.model.entity.Customer;
import ua.company.model.dao.CustomerDAO;


/**
 *
 * @author margarita
 */
public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String e_mail = request.getParameter("e-mail");
        String tel = request.getParameter("tel");
  
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPass(password);
        customer.setE_mail(e_mail);
        customer.setTel(tel);
        
        CustomerDAO customerDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRE).getCustomerDAO();

        if (customerDAO.insert(customer)) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("login", login);
            customerDAO.closeConn();
            RequestDispatcher rd = request.getRequestDispatcher("/pages/success.jsp");
            rd.forward(request, response);
        } else {
            customerDAO.closeConn();
            RequestDispatcher rd = request.getRequestDispatcher("/pages/registration.jsp");
            rd.forward(request, response);            
        }

    }
    
}
