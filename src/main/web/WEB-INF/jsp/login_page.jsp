<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link href="<c:url value="/res/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/res/css/registry.css" />" rel="stylesheet">
</head>


<body>

<section class="container">

    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
            <form action="<c:url value='/login'/>" method='POST' class="form-signin">

                <h2 class="form-signin-heading">Войти</h2>

                <div class="form-group">
                    <input class="form-control" autofocus name="username" id="login" maxlength="15" placeholder="Логин">
                </div>

                <div class="form-group">
                    <input class="form-control" type="password" name="password" id="pass" maxlength="15"
                           placeholder="Пароль">
                </div>

                <div class="form-group">
                    <label for="remember">запомнить меня</label>
                    <input id="remember" name="remember-me" type="checkbox">
                </div>

                <button class="btn btn-lg btn-primary btn-block" >войти</button>

                <a href=" <c:url value="/registry" />">регистрация</a>
                <br/>
                <span class="has-error">${message}</span>
                <span class="has-error">${error}</span>
            </form>
        </div>
    </div>
</section>


</body>
</html>
