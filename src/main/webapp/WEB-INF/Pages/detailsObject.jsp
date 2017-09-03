<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: romachka
  Date: 01.08.17
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${o.name}</title>
        <link rel="stylesheet" href="/Resources/CSS/bootstrap.css">
        <link rel="stylesheet" href="/Resources/CSS/main.css">
        <script type="text/javascript" src="/Resources/JS/jquery.min.js"></script>
        <script type="text/javascript" src="/Resources/JS/slider.js"></script>
    </head>
    <body>
    <div class="slider">
        <div class="next"></div>
        <div class="prev"></div>
        <div class="slides">
            <c:forEach items="${images}" var="image">
                <div class="slide">
                    <img src="${image}">
                </div>
            </c:forEach>
        </div>
    </div>
    <div>
        <p>Name:&nbsp;${o.name}</p>
        <p>Date:&nbsp;${o.date}</p>
        <p>Description:&nbsp;${o.description}</p>
        <p>Authors:&nbsp;
            <c:forEach items="${authors}" var="a">
                <c:out value="${a}"/><br/>
            </c:forEach>
    </div>
    </body>
</html>
