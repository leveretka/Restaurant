/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.model.entity;

import java.util.Objects;
import java.io.Serializable;


/**
 *
 * @author margarita
 */
public class Meal extends Entity implements Serializable{
    
    public static enum MealType{NONE, ALCOHOL, SOFT_DRINKS, HOT_DISH, SIDE_DISH, GARNISH, DESSERT, SOUP, PIZZA}
    
    private String name;
    private MealType mealType;
    private double price;
    private double out;
    private String measure;
    private String name_ua;

    public String getName_ua() {
        return name_ua.replace("_", " ");
    }

    public void setName_ua(String name_ua) {
        this.name_ua = name_ua;
    }

    public Meal() {
    }

    public String getName() {
        return name.replace("_", " ");
    }

    public void setName(String name) {
        this.name = name;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOut() {
        return out;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Meal{" + "name=" + name + ", mealType=" + mealType + ", price=" + price + ", out=" + out + ", measure=" + measure + ", name_ua=" + name_ua + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.mealType);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.out) ^ (Double.doubleToLongBits(this.out) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.measure);
        hash = 59 * hash + Objects.hashCode(this.name_ua);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meal other = (Meal) obj;

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.mealType != other.mealType) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (Double.doubleToLongBits(this.out) != Double.doubleToLongBits(other.out)) {
            return false;
        }
        if (!Objects.equals(this.measure, other.measure)) {
            return false;
        }
        if (!Objects.equals(this.name_ua, other.name_ua)) {
            return false;
        }
        return true;
    }
    
    public String getName(String locale) {
        if (locale.contains("uk")) {
            return getName_ua();
        }
        return getName();
    }
    
}
