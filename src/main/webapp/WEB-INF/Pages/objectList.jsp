<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: romachka
  Date: 31.07.17
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Objects</title>
        <link rel="stylesheet" href="/Resources/CSS/bootstrap.css">
        <link rel="stylesheet" href="/Resources/CSS/main.css">
        <script type="text/javascript" src="/Resources/JS/jquery.min.js"></script>
    </head>
    <body>
    <div class="content">
        <table class="table table-striped">
            <tr class="col">
                <th>#</th>
                <th>Name</th>
                <th>Date</th>
                <th>Functions</th>
            </tr>
            <%int i=1;%>
            <c:forEach items="${objects}" var="o">
                <tr>
                    <td><%=i++%></td>
                    <td>${o.name}</td>
                    <td>${o.date}</td>
                    <td>
                        <div class = "row">
                            <a href="/admin/objects/details/${o.name}">Details</a>&nbsp;|&nbsp;
                            <a href="/admin/objects/edit/${o.id}">Edit</a>&nbsp;|&nbsp;
                            <a href="/admin/objects/delete/${o.id}">Delete</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <form action="/admin/objects/add" method="get">
        <input type="submit" id="add" class="btn btn-success" value="Add object">
    </form>
    </body>
</html>
