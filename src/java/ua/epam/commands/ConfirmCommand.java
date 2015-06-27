/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.model.entity.Order;
import java.util.List;
import javax.servlet.RequestDispatcher;
import ua.epam.model.dao.OrderDAO;
import ua.epam.model.factory.DAOFactory;

/**
 *
 * @author margarita
 */
public class ConfirmCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        HttpSession httpSession = request.getSession();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);
        OrderDAO orderDAO = factory.getOrderDAO();
        
        orderDAO.confirm(Integer.parseInt(request.getParameter("order_id")));
        
        httpSession.setAttribute("notconfirmed", orderDAO.getNotConfirmed());
        orderDAO.closeConn();
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/confirm.jsp");
                
        rd.forward(request, response);

    }
    
}
