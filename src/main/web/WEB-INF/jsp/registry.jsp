<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 02.12.16
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>регистрация</title>
    <%--<meta name="_csrf" content="${_csrf.token}"/>--%>
    <%--<meta name="_csrf_header" content="${_csrf.headerName}"/>--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/angular.min.js "/>"></script>
</head>

<body ng-app="app">

<section class="container">

    <h2>регистрация</h2>

    <div class="row" ng-controller="controller">
        <div class="col-lg-4 col-lg-offset-4">

            <div class="form-group">
                <label for="login">Логин</label>
                <input class="form-control" type="text" ng-model="username" id="login" maxlength="15">
            </div>

            <div class="form-group">
                <label for="pass">Пароль</label>
                <input class="form-control" type="password" ng-model="password" id="pass" maxlength="15">
            </div>

            <div class="form-group">
                <label for="repeat">подтвердить</label>
                <input class="form-control" type="password" ng-model="repeat" id="repeat" maxlength="15">
            </div>

            <div class="form-group">
                <button class="btn btn-success" ng-click="sendInputs(username, password, repeat)">зарегистрироваться
                </button>
            </div>

            <div id="answer"> {{answer}}</div>
        </div>
    </div>
</section>

<script>
    var purchaseApp = angular.module("app", []);
    purchaseApp.controller("controller", function ($scope, $http) {

        $scope.sendInputs = function (username, password, repeat) {
            $http.post("<c:url value='/registry'/>", JSON.stringify({
                username: username,
                password: password,
                repeat: repeat
            }))
                .success(function (data, status, headers, config) {
                    if (data.status === 'ok') {
                        window.location.href = '/';
                    } else {
                        var errors = "";
                        data.description.forEach(function (txt) {
                            errors = errors.concat(txt).concat("\n");
                        });
                        $scope.answer = errors;
                    }
                }).error(function (data, status, headers, config) {
                $scope.answer("failure message: " + JSON.stringify({data: data}));
            });
        }
    });
</script>
</body>
</html>
