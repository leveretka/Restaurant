/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.dao;

import java.sql.SQLException;
import ua.epam.model.entity.Entity;
import ua.epam.model.entity.Meal;
import ua.epam.model.entity.Meal.MealType;
import java.util.List;

/**
 *
 * @author margarita
 */
public interface MealDAO {

    final static String FIND = "SELECT COUNT(*) FROM meals, types WHERE meals.name = ? AND types.name = ? AND price = ? AND out = ? AND measure = ? AND meals.type=types.id"; 
    final static String ALL = "SELECT * FROM meals"; 
    final static String CONCRETE_MEAL = "SELECT * FROM meals WHERE id=?"; 
    final static String GROUP = "SELECT meals.id, meals.name, price, out, measure, meals.name_ua FROM meals, types WHERE meals.type=types.id AND types.name = ?"; 
    final static String INSERT = "INSERT INTO meals(name, type, price, out, measure, name_ua) VALUES(?, ?, ?, ?, ?, ?)"; 
    final static String TYPE_ID = "SELECT id FROM types WHERE name = ?";
    final static String DELETE = "DELETE FROM meals WHERE name=?, type=?, price=?, out=?, measure=?";
    final static String TYPE_NAME = "SELECT name FROM types WHERE id=?";

    boolean insert(Meal meal) throws SQLException;
    boolean remove(Meal meal) throws SQLException;
    boolean find(Meal meal) throws SQLException;
    List<Meal> getAllMeals() throws SQLException;
    List<Meal> getMeals(MealType mt) throws SQLException;
    Meal getMealByID(Integer id) throws SQLException;
    
    void closeConn() throws SQLException;

}
