/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.dao;

import java.sql.SQLException;
import ua.epam.model.entity.Order;
import ua.epam.model.entity.Order;
import java.util.List;
import ua.epam.model.entity.Customer;

/**
 *
 * @author margarita
 */
public interface OrderDAO {

    String FIND = "SELECT COUNT(*) FROM orders WHERE date=? AND price=? AND customer=?";
    String INSERT = "INSERT INTO orders(date, price, customer, confirmed, payed) VALUES(?, ?, ?, ?, ?)";
    String INSERT_MENU_ORDER = "INSERT INTO menu_orders(order_id, meal_id, qnt, price) VALUES(?, ?, ?, ?)";
    String ORDERS_CUSTOMER = "SELECT * FROM orders WHERE customer=?";
    String ALL_ORDERS = "SELECT * FROM orders";
    String ORDER_ID = "SELECT id FROM orders where date=? AND price=? AND customer=?";
    String MEAL_PRICE = "SELECT price FROM meals WHERE id=?";
    String MEALS_ORDER = "SELECT meals.id, meals.name, types.name, meals.price, out, measure, meals.name_ua, qnt FROM menu_orders, meals, types, orders WHERE menu_orders.order_id = orders.id AND  menu_orders.meal_id = meals.id AND meals.type = types.id AND orders.id=?";
    String CUSTOMER_ID = "SELECT * FROM customers WHERE id=?";
    String NOT_CONFIRMED = "SELECT * FROM orders WHERE confirmed=false";
    String NOT_PAYED = "SELECT * FROM orders WHERE payed=false AND customer=? AND confirmed=true";
    String CONFIRM = "UPDATE orders SET confirmed=true WHERE id=?";
    String PAY = "UPDATE orders SET payed=true WHERE id=?";
    
    boolean insert(Order order) throws SQLException;
    boolean find(Order order) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    List<Order> getAllCustomerOrders(Customer customer) throws SQLException;
    List<Order> getNotConfirmed() throws SQLException;
    List<Order> getNotPayed(Customer customer) throws SQLException;
    boolean confirm(Integer id) throws SQLException;
    boolean pay(Integer id) throws SQLException;
    
    void closeConn() throws SQLException;

    
}