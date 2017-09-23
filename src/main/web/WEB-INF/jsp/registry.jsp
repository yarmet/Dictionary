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

    <title>Create an account</title>

    <link href="${contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/res/css/registry.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<section class="container">

    <nav class="navbar navbar-default">
        <div class="container-fluid">

            <div class="nav navbar-nav">
                <div class="navbar-brand"><a href="<c:url value="/welcome"/>">главная</a></div>
            </div>

            <div class="nav navbar-nav navbar-right">
                <div div class="navbar-brand"><a href="<c:url value="/login" />">войти</a></div>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">

            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                <h2 class="form-signin-heading">Зарегистрироваться</h2>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" placeholder="Username"
                                    autofocus="true"/>
                        <form:errors path="username"/>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="password" class="form-control" placeholder="Password"/>
                        <form:errors path="password"/>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="confirmPassword" class="form-control"
                                    placeholder="Confirm your password"/>
                        <form:errors path="confirmPassword"/>
                    </div>
                </spring:bind>
                <button class="btn btn-lg btn-primary btn-block">Подтвердить</button>
            </form:form>
        </div>
    </div>

</section>
<!-- /container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/res/js/bootstrap.min.js"></script>

</body>
</html>