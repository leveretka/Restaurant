/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.model.entity;

import java.sql.Date;
import java.util.Map;
import java.io.Serializable;


/**
 *
 * @author margarita
 */
public class Order extends Entity implements Serializable{
    
    private Date date;
    private double price;
    private Map<Meal, Integer> meals;
    private Customer customer;
    private boolean confirmed;
    private boolean payed;
    
    public Map<Meal, Integer> getMeals() {
        return meals;
    }

    public void setMeals(Map<Meal, Integer> meals) {
        this.meals = meals;
    }

    public Order() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
    
    


    @Override
    public String toString() {
        return "Order{" + "date=" + date + ", price=" + price + ",\n\t meals=" + meals + ", \n\tcustomer=" + customer + '}';
    }
    
    
    
}
