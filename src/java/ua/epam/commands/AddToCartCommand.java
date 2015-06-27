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
import ua.epam.model.entity.Meal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import ua.epam.model.dao.MealDAO;
import ua.epam.model.dao.PostgreMealDAO;
import ua.epam.model.factory.DAOFactory;

/**
 *
 * @author margarita
 */
public class AddToCartCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        
        HttpSession httpSession = request.getSession();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Map<Meal, Integer> mealsInOrder = (Map<Meal, Integer>) httpSession.getAttribute("meals in order");
        
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);
        
        MealDAO mealDAO = factory.getMealDAO();
        
        Meal meal = mealDAO.getMealByID(id);
        if (!mealsInOrder.containsKey(meal)) {
            mealsInOrder.put(meal, 1);
        } else {
            mealsInOrder.put(meal, mealsInOrder.get(meal) + 1);
        }
        
        mealDAO.closeConn();
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/menu.jsp");
                
        rd.forward(request, response);

    }
    
}
