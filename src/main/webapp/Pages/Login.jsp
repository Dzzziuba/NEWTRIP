<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
  Created by IntelliJ IDEA.
  User: Romachka
  Date: 31.07.17
  Time: 21:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="/Resources/CSS/bootstrap.css">
        <link rel="stylesheet" href="/Resources/CSS/main.css">
        <script type="text/javascript" src="/Resources/JS/jquery.min.js"></script>
    </head>
    <body>
    <div>
        <div>
            <form class="form-horizontal" action="/login" method="post" style="padding-top: 18%;">
                <div class="form-group">
                    <label for="j_username" class="col-sm-4 control-label">Email</label>
                    <div class="col-sm-4">
                        <input type="text" <%--type="email"--%> class="form-control" name="j_username" id="j_username" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="j_password" class="col-sm-4 control-label">Password</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="j_password" name="j_password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-4">
                        <button type="submit" class="btn btn-default">Log in</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </body>
</html>