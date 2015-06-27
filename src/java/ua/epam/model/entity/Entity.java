/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.model.entity;

/**
 *
 * @author margarita
 */
public abstract class Entity {
    
    private static int count;
    
    private Integer id = ++count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
