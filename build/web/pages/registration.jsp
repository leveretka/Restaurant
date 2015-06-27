<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="text" var="lang"/>  
    <fmt:setLocale value="${sessionScope.loc}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/Restaurant/css/style.css" media="all">
    </head>
    <body>
        <div class="wrapper">
        <h1 style="text-align:center"><fmt:message key="register" bundle="${lang}"/></h1>
        <br>
        <form style="text-align:center" action="/Restaurant/controller" method="post">
            <fmt:message key="login" bundle="${lang}"/> = 
            <input type="text" name="login"/>
            <br>
            <br>
            <fmt:message key="pass" bundle="${lang}"/> =
            <input type="text" name="password"/>
            <br>
            <br>
            E-mail =
            <input type="text" name="e-mail"/>
            <br>
            <br>
            <fmt:message key="tel" bundle="${lang}"/> =
            <input type="text" name="tel"/>
            <br>
            <br>
            <input type="submit" name="send" value="register"/>
        </form>
<!--        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="registration" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>-->
        </div>
     </body>
</html>
