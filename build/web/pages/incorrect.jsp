<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
     prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/Restaurant/css/style.css" media="all">
    </head>
    <body>
        <div class="wrapper">
        <fmt:setBundle basename="text" var="lang"/>  
        <fmt:setLocale value="${sessionScope.loc}"/>
       <h1><fmt:message key="login_incorrect" bundle="${lang}"/></h1>
        </div>
    </body>
</html>
