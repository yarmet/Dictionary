<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Регистрация</title>

    <link href="${contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/res/css/registry.css" rel="stylesheet">
</head>

<body>

<section class="container">

    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">

            <form:form method="POST" modelAttribute="userForm" class="form-signin">

                <h2 class="form-signin-heading">Зарегистрироваться</h2>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" placeholder="логин"
                                    autofocus="true"/>
                        <form:errors path="username"/>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="password" class="form-control" placeholder="пароль"/>
                        <form:errors path="password"/>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="confirmPassword" class="form-control"
                                    placeholder="повторить"/>
                        <form:errors path="confirmPassword"/>
                    </div>
                </spring:bind>
                <button class="btn btn-lg btn-primary btn-block">Подтвердить</button>
            </form:form>
        </div>
    </div>

</section>
<!-- /container -->

<script src="${contextPath}/res/js/bootstrap.min.js"></script>
</body>
</html>