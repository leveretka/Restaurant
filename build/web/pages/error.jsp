<%@ page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
     prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
     prefix="fmt" %>
<html>
    <fmt:setBundle basename="text" var="lang"/>  
    <fmt:setLocale value="${sessionScope.loc}"/>
    <head>
        <title>Error Occured</title>
        <link rel="stylesheet" type="text/css" href="/Restaurant/css/style.css" media="all">
    </head>
    <body bgcolor="white">
        <div class="wrapper">
        <h1 style="text-align: center">
            <fmt:message key="error" bundle="${lang}"/>
        </h1>
        <h2 style="text-align: center"> ${pageContext.errorData.statusCode}</h2>
        <h2 style="text-align: center"> ${pageContext.exception.message}</h2>
<!--        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="error" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>-->
        </div>
    </body>
</html>