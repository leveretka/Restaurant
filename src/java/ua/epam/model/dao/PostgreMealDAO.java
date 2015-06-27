/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.dao;

import ua.epam.model.entity.Meal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static ua.epam.model.dao.CustomerDAO.ALL;
import ua.epam.model.entity.Customer;

/**
 *
 * @author margarita
 */
public class PostgreMealDAO implements MealDAO{

    private Connection conn;

    public PostgreMealDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public boolean insert(Meal meal) throws SQLException {
                
        if (!find(meal)) {

            PreparedStatement statement = conn.prepareStatement(INSERT);
            
            Integer typeId = getTypeID(meal.getMealType().name().toLowerCase());
            if (typeId == null) {
                throw new SQLException();
            }
            statement.setString(1, meal.getName());
            statement.setInt(2, typeId);
            statement.setDouble(3, meal.getPrice());
            statement.setDouble(4, meal.getOut());
            statement.setString(5, meal.getMeasure());
            statement.setString(6, meal.getName_ua());

            statement.execute();
            return true;            
        }
        
        return false;

    }
    
    @Override
    public List<Meal> getAllMeals() throws SQLException {

        PreparedStatement st = conn.prepareStatement(ALL);
        return getMeals(st);
    }

    @Override
    public List<Meal> getMeals(Meal.MealType mt) throws SQLException {
        
        PreparedStatement st = conn.prepareStatement(GROUP);
        st.setString(1, mt.name().toLowerCase());
        ResultSet rs = st.executeQuery();
        
        List<Meal> meals = new ArrayList<>();
        
        while (rs.next()) {

            int id = rs.getInt(1);
            Meal meal = new Meal();
            meal.setId(id);
            meal.setName(rs.getString(2));
            meal.setMealType(mt);
            meal.setPrice(rs.getDouble(3));
            meal.setOut(rs.getDouble(4));
            meal.setMeasure(rs.getString(5));
            meal.setName_ua(rs.getString(6));
            meals.add(meal);
        }
        
        return meals;
    }

    @Override
    public boolean remove(Meal meal) throws SQLException {
        if (!find(meal)) {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            int typeId = getTypeID(meal.getMealType().name().toLowerCase());
            
            statement.setString(1, meal.getName());
            statement.setInt(2, typeId);
            statement.setDouble(3, meal.getPrice());
            statement.setDouble(4, meal.getOut());
            statement.setString(5, meal.getMeasure());
            statement.execute();
            return true;            
        }
        
        return false;
    }

    @Override
    public boolean find(Meal meal) throws SQLException {
        
        PreparedStatement statement = conn.prepareStatement(FIND);
        statement.setString(1, meal.getName());
        statement.setString(2, meal.getMealType().toString().toLowerCase());
        statement.setDouble(3, meal.getPrice());
        statement.setDouble(4, meal.getOut());
        statement.setString(5, meal.getMeasure());

        ResultSet rs = statement.executeQuery();
        rs.next();
            
        if (rs.getInt(1) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private Integer getTypeID(String typename) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(TYPE_ID);
        statement.setString(1, typename);

        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt(1);
    } 

    private String getTypeName(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(TYPE_NAME);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getString(1);
    } 

    private List<Meal> getMeals(PreparedStatement st) throws SQLException {
        
        ResultSet rs = st.executeQuery();
        
        List<Meal> meals = new ArrayList<>();
        
        while (rs.next()) {

            int id = rs.getInt(1);
            int meal_id = rs.getInt(3);
            Meal meal = new Meal();
            meal.setId(id);
            meal.setName(rs.getString(2));

            meal.setMealType(Meal.MealType.valueOf(getTypeName(meal_id).toUpperCase()));
            
            meal.setPrice(rs.getDouble(4));
            meal.setOut(rs.getDouble(5));
            meal.setMeasure(rs.getString(6));
            meal.setName_ua(rs.getString(7));
            meals.add(meal);
        }
        
        return meals;
    }

    @Override
    public Meal getMealByID(Integer id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(CONCRETE_MEAL);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        
        rs.next();
        
        int meal_id = rs.getInt(3);
        Meal meal = new Meal();
        meal.setId(id);
        meal.setName(rs.getString(2));

        meal.setMealType(Meal.MealType.valueOf(getTypeName(meal_id).toUpperCase()));
            
        meal.setPrice(rs.getDouble(4));
        meal.setOut(rs.getDouble(5));
        meal.setMeasure(rs.getString(6));
        meal.setName_ua(rs.getString(7));
        
        return meal;
    }
    
    public void closeConn() throws SQLException {
        conn.close();
    }


}
