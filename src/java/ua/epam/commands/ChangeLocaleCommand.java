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
import javax.servlet.http.Cookie;

/**
 *
 * @author margarita
 */
public class ChangeLocaleCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        HttpSession httpSession = request.getSession();
        
        String value = request.getParameter("send");
        if (value.equals("ua")) {
            httpSession.setAttribute("loc", "uk_UA");
        } else {
            httpSession.setAttribute("loc", "en_US");
        }
        
        String addr = request.getParameter("pageName");
        RequestDispatcher rd = null;
        
        if(addr ==null || addr.equals("") || addr.equals("index"))  {
            rd = request.getRequestDispatcher("index.jsp");
        } else {
            rd = request.getRequestDispatcher("/pages/" + addr +".jsp"); 
        }
        rd.forward(request, response);            
    }
    
}
