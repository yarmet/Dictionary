<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 29.11.16
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

<section class="container">


    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="nav navbar-nav navbar-right">
                <div  div class="navbar-brand"><a href=" <c:url value="/registry" />">регистрация</a></div>
            </div>
        </div>
    </nav>



    <h2>войти</h2>
    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
            <form action="<c:url value='/login'/>" method='POST' role="form">

                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

                <div class="form-group">
                    <label for="login">Логин</label>
                    <input class="form-control" type="text" name="username" id="login" maxlength="15">
                </div>

                <div class="form-group">
                    <label for="pass">Пароль</label>
                    <input class="form-control" type="password" name="password" id="pass" maxlength="15">
                </div>

                <div class="checkbox">
                    <label><input name="remember-me" type="checkbox">запомнить меня</label>
                </div>

                <div class="form-group">
                    <input class="btn btn-success" type="submit" value="войти">
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
