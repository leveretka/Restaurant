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
        <link rel="stylesheet" type="text/css" href="/Restaurant/css/style.css" media="all">
    </head>
    <body>
        <div class="wrapper">
        <h1 style="text-align: center; color: red"><fmt:message key="order_error" bundle="${lang}"/></h1>
            <a href="/Restaurant/pages/menu.jsp"><fmt:message key="back" bundle="${lang}"/></a>
<!--        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="ordererror" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>-->
        </div>
    </body>
</html>
