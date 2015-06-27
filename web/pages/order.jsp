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
        <h1 style="alignment-baseline: central; text-align: center"><fmt:message key="your_order" bundle="${lang}"/></h1>
        <div class="wrapper">
            <table>
                <tr>
                    <th width="100px" style="text-align: center"><fmt:message key="name" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="price" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="out" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="measure" bundle="${lang}"/></th>
                    <th width="100px" style="text-align: center"><fmt:message key="qnt" bundle="${lang}"/></th>
                </tr>
                <c:forEach var="meal" items="${sessionScope.meals}">
                    <tr>
                        <td width="150px" style="text-align: center">${meal.getName(requestScope.loc)}</td>
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
<!--                        <br>
        <br>
        <form style="text-align:center" action="controller" method="post">
            <input type="hidden" name="pageName" value="order" />
            <input type="submit" name="send" value="ua"/>
            <input type="submit" name="send" value="en"/>
        </form>
-->
        </div>
    </body>
</html>
