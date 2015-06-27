<%-- 
    Document   : confirm
    Created on : 21 черв 2015, 3:02:58
    Author     : margarita
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <h1 style="alignment-baseline: central; text-align: center"><fmt:message key="for_pay" bundle="${lang}"/></h1>
            <br>                
            <table>
                <c:forEach var="order" items="${sessionScope.notpayed}">
                    <tr>
                        <th width="100px" style="text-align: center">id</th>
                        <th width="100px" style="text-align: center"><fmt:message key="date" bundle="${lang}"/></th>
                        <th width="100px" style="text-align: center"><fmt:message key="price" bundle="${lang}"/></th>
                        <th width="100px" style="text-align: center"><fmt:message key="pay" bundle="${lang}"/></th>
                    </tr>
                    <tr>
                        <td width="150px" style="text-align: center">${order.id}</td>
                        <td width="150px" style="text-align: center">${order.date}</td>
                        <td width="150px" style="text-align: center">${order.price}</td>
                        <td width="150px" style="text-align: center">
                            <form action="/Restaurant/controller" method="post">
                                <input type="hidden" name="order_id" value="${order.id}" />
                                <input type="submit" name="send" value="pay" />
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th width="100px" style="text-align: center"><fmt:message key="name" bundle="${lang}"/></th>
                        <th width="100px" style="text-align: center"><fmt:message key="price1" bundle="${lang}"/></th>
                        <th width="100px" style="text-align: center"><fmt:message key="qnt" bundle="${lang}"/></th>
                        <th width="100px" style="text-align: center"><fmt:message key="price" bundle="${lang}"/></th>
                    </tr>
                     
                    <c:forEach var="meal" items="${order.meals.keySet()}">
                        <tr>
                            <td width="100px" style="text-align: center">${meal.getName(sessionScope.loc)}</td>
                            <td width="100px" style="text-align: center">${meal.price}</td>
                            <td width="100px" style="text-align: center">${order.meals.get(meal)}</td>
                            <td width="100px" style="text-align: center">${order.meals.get(meal) * meal.price}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
            <form action="/Restaurant/controller" method="post">
                <input type="submit" name="send" value="log out" />
                <a href="/Restaurant/pages/menu.jsp"><fmt:message key="show_menu" bundle="${lang}"/></a>
            </form>
<!--        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="payment" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>-->
        </div>
    </body>
</html>
