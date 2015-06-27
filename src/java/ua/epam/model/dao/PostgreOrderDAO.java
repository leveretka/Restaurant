/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ua.epam.model.entity.Order;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static ua.epam.model.dao.MealDAO.FIND;
import static ua.epam.model.dao.MealDAO.INSERT;
import ua.epam.model.entity.Customer;
import ua.epam.model.entity.Meal;

/**
 *
 * @author margarita
 */
public class PostgreOrderDAO implements OrderDAO{

    private Connection conn;

    public PostgreOrderDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public boolean insert(Order order) throws SQLException {
        
            PreparedStatement statement = conn.prepareStatement(INSERT);

            statement.setDate(1, order.getDate());
            statement.setInt(3, order.getCustomer().getId());
            statement.setBoolean(4, false);
            statement.setBoolean(5, false);

            double total = 0.0;

            for (Meal m: order.getMeals().keySet()) {
                
                int qnt = order.getMeals().get(m);
                double price = getMealPrice(m);
                price *= qnt;
                
                total += price;
            }

            order.setPrice(total);

            statement.setDouble(2, total);
            
            statement.execute();
            
            int orderId = getOrderId(order);
            
            
            for (Meal m: order.getMeals().keySet()) {
                
                int qnt = order.getMeals().get(m);
                double price = getMealPrice(m);
                price *= qnt;

                PreparedStatement s = conn.prepareStatement(INSERT_MENU_ORDER);
                s.setInt(1, orderId);
                s.setInt(2, m.getId());
                s.setInt(3, qnt);
                s.setDouble(4, price);
                s.execute();
            }
            
            return true;            
        
        

    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        
        PreparedStatement statement = conn.prepareStatement(ALL_ORDERS);
        
        return getOrders(statement);
    }

    @Override
    public List<Order> getAllCustomerOrders(Customer customer) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(ORDERS_CUSTOMER);
        statement.setInt(1, customer.getId());
        
        return getOrders(statement);
    }

    @Override
    public boolean find(Order order) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(FIND);
        statement.setDate(1, order.getDate());
        statement.setDouble(2, order.getPrice());
        statement.setInt(3, order.getCustomer().getId());

        ResultSet rs = statement.executeQuery();
        rs.next();
            
        if (rs.getInt(1) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private int getOrderId(Order order) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(ORDER_ID);
        
        statement.setDate(1, order.getDate());
        statement.setDouble(2, order.getPrice());
        statement.setInt(3, order.getCustomer().getId());

        ResultSet rs = statement.executeQuery();
        rs.next();
        
        return rs.getInt(1);
        
    }
    
    private double getMealPrice(Meal meal) throws SQLException {
        
        PreparedStatement statement = conn.prepareStatement(MEAL_PRICE);
        
        statement.setDouble(1, meal.getId());
        
        ResultSet rs = statement.executeQuery();
        rs.next();
        
        return rs.getDouble(1);
    }

    private List<Order> getOrders(PreparedStatement st) throws SQLException {
        
        ResultSet rs = st.executeQuery();
        
        List<Order> orders = new ArrayList<>();
        
        while (rs.next()) {

            Order order = new Order();
            
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(2));
            order.setPrice(rs.getDouble(3));
            order.setCustomer(getCustomer(rs.getInt(4)));
            order.setMeals(getOrderMeals(rs.getInt(1)));
            
            orders.add(order);
        }
        
        return orders;
    }

    private Map<Meal, Integer> getOrderMeals(int id) throws SQLException {
        Map<Meal, Integer> meals = new HashMap<>();
        PreparedStatement statement = conn.prepareStatement(MEALS_ORDER);
        statement.setInt(1, id);
        
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Meal meal = new Meal();
            meal.setId(rs.getInt(1));
            meal.setName(rs.getString(2));
            meal.setMealType(Meal.MealType.valueOf(rs.getString(3).toUpperCase()));
            meal.setPrice(rs.getDouble(4));
            meal.setOut(rs.getDouble(5));
            meal.setMeasure(rs.getString(6));
            meal.setName_ua(rs.getString(7));
            int qnt = rs.getInt(8);
           
            meals.put(meal, qnt);
        }
        return meals;

    }
    
    private Customer getCustomer(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(CUSTOMER_ID);
        statement.setInt(1, id);
        
        ResultSet rs = statement.executeQuery();
        
        Customer customer = new Customer();
        rs.next();
        
        customer.setId(rs.getInt(1));
        customer.setLogin(rs.getString(2));
        customer.setPass(rs.getString(3));
        customer.setE_mail(rs.getString(4));
        customer.setTel(rs.getString(5));
        
        return customer;
    }

    @Override
    public List<Order> getNotConfirmed() throws SQLException {
        PreparedStatement statement = conn.prepareStatement(NOT_CONFIRMED);
        
        return getOrders(statement);
    }

    @Override
    public List<Order> getNotPayed(Customer customer) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(NOT_PAYED);
        statement.setInt(1, customer.getId());
        
        return getOrders(statement);
    }

    @Override
    public boolean confirm(Integer id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(CONFIRM);
        statement.setInt(1, id);
        return statement.execute();
    }

    @Override
    public boolean pay(Integer id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(PAY);
        statement.setInt(1, id);
        return statement.execute();
    }
    
    public void closeConn() throws SQLException {
        conn.close();
    }

}
