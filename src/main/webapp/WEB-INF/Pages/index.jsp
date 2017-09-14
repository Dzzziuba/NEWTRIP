<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  @author Roma Dziuba
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
    <head>
        <title>${o.name}</title>
        <link href="/Resources/CSS/style.css" rel="stylesheet" />
    </head>
    <body>
    <div style="float: right;">
        <a href="/admin/objects/">Admin</a>
    </div>
        <header class="zi-1 dark">
            <div class="logo newtrip-1 center opacity2">NewTrip</div>
        </header>
        <section id="img-info" class="left hide">
            <div class="header"></div>
            <div class="description"></div>
            <div class="author"></div>
        </section>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="/Resources/JS/script.js"></script>
    </body>
</html>
