/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.filters;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author margarita
 */
public class SessionCheck implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) arg0;
        
        HttpServletResponse response = (HttpServletResponse) arg1;

        String url = request.getRequestURI();

        if (null==((String) request.getSession().getAttribute("login")) || ((String) request.getSession().getAttribute("login")).equals("")) {
            if (!url.endsWith("registration.jsp")) {
                response.sendRedirect("/Restaurant/index.jsp");
            }
        }
        
        if (url.contains("confirm.jsp") && "admin".equals(request.getSession().getAttribute("login"))) {
            response.sendRedirect("/Restaurant/menu.jsp");        
        }
        arg2.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}