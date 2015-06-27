
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="pages\error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Restaurant</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all">
    </head>
    <body>
        <c:set var="loc" value="en_US" scope="request"/>
        <c:if test="${sessionScope.loc != null}">
            <c:set var="loc" value="${sessionScope.loc}"/>
        </c:if>
        <fmt:setLocale value="${loc}" scope="session"/>
        <fmt:setBundle basename="text" var="lang"/>  
        <div class="wrapper">
            <h1 style="text-align:center"><fmt:message key="welcome" bundle="${lang}"/></h1>
            <br>
            <form style="text-align:center" action="controller" method="post" class='form'>
                <div class='input_field'>
                <fmt:message key="login" bundle="${lang}"/> =                 
                    <input type="text" name="login">
                    <p><fmt:message key="pass" bundle="${lang}"/> =
                    <input type="text" name="password">
                    </p>
                </div>
                <input type="submit" name="send" value="sign in">
                <a> </a>
                <a href ="pages\registration.jsp"><fmt:message key="registration" bundle="${lang}"/></a>
            </form>
            <br>
            <br>
            <form style="text-align:center" action="controller" method="post">
                <input type="hidden" name="pageName" value="index" />
                <input type="submit" name="send" value="ua"/>
                <input type="submit" name="send" value="en"/>
            </form>
        </div>  
   </body>
</html>
