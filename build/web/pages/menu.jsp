<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/Restaurant/css/style.css" media="all">
    </head>
    <body>
        <fmt:setBundle basename="text" var="lang"/>  
        <fmt:setLocale value="${sessionScope.loc}"/>

        <div class="wrapper">
        <h1 style="alignment-baseline: central; text-align: center"><fmt:message key="menu" bundle="${lang}"/></h1>
       
            <form action="/Restaurant/controller" method="post">
                <button type="submit" name="send" value="select type none"><fmt:message key="all" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type alcohol"><fmt:message key="alcohol" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type soft_drinks"><fmt:message key="soft" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type hot_dish"><fmt:message key="hot" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type side_dish"><fmt:message key="side" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type garnish"><fmt:message key="garnish" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type dessert"><fmt:message key="dessert" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type soup"><fmt:message key="soup" bundle="${lang}"/></button>
                <button type="submit" name="send" value="select type pizza"><fmt:message key="pizza" bundle="${lang}"/></button>
            </form>
            <br><br>                
            <table>
                <tr>
                    <th width="100px" style="text-align: center"><fmt:message key="name" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="price" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="out" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="measure" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="add" bundle="${lang}"/></th>
                </tr>
                <c:forEach var="meal" items="${sessionScope.meals}">
                    <tr>
                        <td width="150px" style="text-align: center">${meal.getName(sessionScope.loc)}</td>
                        <td width="150px" style="text-align: center">${meal.price}</td>
                        <td width="150px" style="text-align: center">${meal.out}</td>
                        <td width="150px" style="text-align: center">${meal.measure}</td>
                        <td width="150px" style="text-align: center">
                            <form action="/Restaurant/controller" method="post">
                                <input type="hidden" name="id" value="${meal.id}" />
                                <input type="submit" name ="send" value="add" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form action="/Restaurant/controller" method="post">
                <input type="submit" name ="send" value="make order" />
                <input type="submit" name="send" value="log out" />
            </form>
<!--        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="menu" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>-->
        </div>
    </body>
</html>
