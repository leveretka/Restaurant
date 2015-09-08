/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.company.model.entity.Meal;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import ua.company.model.dao.MealDAO;
import ua.company.model.factory.DAOFactory;

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
