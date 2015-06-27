<%-- 
    Document   : success
    Created on : 15 черв 2015, 15:45:19
    Author     : margarita
--%>
<%@ page session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <fmt:setBundle basename="text" var="lang"/>  
    <fmt:setLocale value="${sessionScope.loc}"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <c:url var="cssUrl" value="css/style.css" />
        <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    </head>
    <body>
        <div class="wrapper">
            <h1 style="text-align: center"><fmt:message key="reg_success" bundle="${lang}"/></h1>
            <a href="/Restaurant/index.jsp"><fmt:message key="home" bundle="${lang}"/></a>
        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="success" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>

        </div>
    </body>
</html>
