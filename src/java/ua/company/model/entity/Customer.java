/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.company.model.entity;

import java.io.Serializable;

/**
 *
 * @author margarita
 */
public class Customer extends Entity implements Serializable{
    
    private String login;
    private String pass;
    private String e_mail;
    private String tel;

    public Customer() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Customer{" + "login=" + login + ", pass=" + pass + ", e_mail=" + e_mail + ", tel=" + tel + '}';
    }
    
    
}
