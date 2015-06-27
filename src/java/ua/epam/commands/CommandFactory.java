/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.commands;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author margarita
 */
public class CommandFactory {
    static Map<String, Command> commands = new HashMap<>();
    
    static{
        commands.put("sign in", new AuthCommand());
        commands.put("register", new RegistrationCommand());
        commands.put("add", new AddToCartCommand());
        commands.put("make order", new MakeOrderCommand());
        commands.put("confirm", new ConfirmCommand());
        commands.put("log out", new LogOutCommand());
        commands.put("pay", new PayCommand());
        commands.put("select type", new SelectTypeCommand());
        commands.put("ua", new ChangeLocaleCommand());
        commands.put("en", new ChangeLocaleCommand());
    }
    
    public static Command getCommand(HttpServletRequest request) {
        
        String value = request.getParameter("send");
        String type = getValue(value);
        if (type != null) {
            request.removeAttribute("type");
            request.setAttribute("type", type);
            value = "select type";
        }
    
        return commands.get(value);
    }
    
    public static String getValue(String param) {
        if (!param.contains("select type")) {
            return null;
        } else {
            return param.replace("select type ", "").trim();
        }
    }
}
