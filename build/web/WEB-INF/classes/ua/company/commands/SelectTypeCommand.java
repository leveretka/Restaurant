/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.company.model.dao.MealDAO;
import ua.company.model.entity.Meal;
import ua.company.model.factory.DAOFactory;

/**
 *
 * @author margarita
 */
public class SelectTypeCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("meals");
        String value = (String) request.getAttribute("type");
        
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);
        MealDAO mealDAO = factory.getMealDAO();
        
        
        if (value.equals("none")) {
            httpSession.setAttribute("meals", mealDAO.getAllMeals());
            mealDAO.closeConn();
        } else {
            Meal.MealType mt = Meal.MealType.valueOf(value.toUpperCase());
            httpSession.setAttribute("meals", mealDAO.getMeals(mt));
            mealDAO.closeConn();
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/menu.jsp");
                
        rd.forward(request, response);

        
    }
    
}
