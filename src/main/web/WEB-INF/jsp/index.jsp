<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title>Spring</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/angular.min.js" />"></script>
</head>

<body ng-app="app">

<section class="container" ng-controller="controller">

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div ng-show="role_admin" class="nav navbar-nav">
                <div class="navbar-brand"><a href="javascript:void(0);" ng-click="openWordEditorWindow()"> добавить слово</a></div>
            </div>
            <div class="nav navbar-nav navbar-right">
                <div class="navbar-brand"><a href="javascript:void(0);" ng-click="logout()">выйти</a></div>
            </div>
        </div>
    </nav>

    <h2>Словарик</h2>

    <div class="group">

        <div class="group1">
            <label> <input type="radio" name="loadlast" data-ng-model="loadLast" data-ng-value="true"> загрузить последние </label>
            <label> <input type="radio" name="loadlast" data-ng-model="loadLast" data-ng-value="false" checked>загрузить все </label>
        </div>

        <div class="group1">
            <label> <input type="radio" ng-click="showRussianF()" name="languageChoice" checked> русский </label>
            <label> <input type="radio" ng-click="showEnglishF()" name="languageChoice"> английский </label>
        </div>
    </div>

    <div class="popUpMsg-bg" ng-show="showWordEditorWindow">
        <div class="popUpMsg">
            <div class="close" ng-click="closeWordEditorWindow()">x</div>
            <div class="form-inline">
                <input class="form-control" type="text" placeholder="русский" ng-model="russian">
                <input class="form-control" type="text" placeholder="английский" ng-model="english"/>
                <button ng-show="showAddButton" class="btn btn-danger" ng-click="sendToAddWord(english, russian)">запомнить</button>
                <button ng-show="showEditButton" class="btn btn-danger" ng-click="sendToEditWord(english, russian)">ред.</button>
            </div>
        </div>
    </div>

    <div id="wordsContainer">
        <button id="newWords" class="btn btn-default" ng-click="newWords()"> загрузить еще</button>
        <table>
            <thead>
            <tr>
                <td>русский</td>
                <td>английский</td>
                <td colspan="2" ng-show="role_admin">админка</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="row in rows">
                <td>
                    <a href="javascript:void(0);" ng-click="showRussian = !showRussian" ng-show="!showRussian">показать</a>
                    <div ng-show="showRussian"> {{row.russian}}</div>
                </td>
                <td>
                    <a href="javascript:void(0);" ng-click="showEnglish = !showEnglish" ng-show="!showEnglish">показать</a>
                    <div ng-show="showEnglish"> {{row.english}}</div>
                </td>
                <td ng-show="role_admin">
                    <a href="javascript:void(0);" ng-click="openEditWindow(row.id, row.russian, row.english)">ред.</a>
                </td>
                <td ng-show="role_admin">
                    <a href="javascript:void(0);" ng-click="sendToDeleteWord(row.id, rows.indexOf(row))">уд.</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<script>
    var purchaseApp = angular.module("app", [])
        .directive('popUpMsg', function () {
        });

    purchaseApp.controller("controller", function ($scope, $http) {
        $scope.showRussian = true;
        $scope.showEnglish = false;

        $scope.loadLast = false;

        $scope.showEditButton = false;
        $scope.showAddButton = false;

        $scope.showWordEditorWindow = false;

        $scope.role_admin = false;

        $scope.closeWordEditorWindow = clearEditWindow;

        $scope.openWordEditorWindow = function () {
            $scope.showAddButton = true;
            $scope.showWordEditorWindow = true;
        };

        $scope.showRussianF = function () {
            $scope.showRussian = true;
            $scope.showEnglish = false;
        };

        $scope.showEnglishF = function () {
            $scope.showRussian = false;
            $scope.showEnglish = true;
        };

        $scope.openEditWindow = function (id, russian, english) {
            $scope.english = english;
            $scope.russian = russian;
            $scope.edit_word_id = id;
            $scope.showEditButton = true;
            $scope.showWordEditorWindow = true;
        };

        $scope.sendToDeleteWord = function (id, index) {
            $http.post("<c:url value='/deleteWord'/>", JSON.stringify({id: id})).success(function () {
                $scope.rows.splice(index, 1);
            });
        };

        $scope.sendToEditWord = function (english, russian) {
            if (english == null || russian == null)  return;
            $scope.showEditButton = false;
            send("<c:url value='/editWord'/>", JSON.stringify({
                id: $scope.edit_word_id,
                english: english,
                russian: russian
            }));
        };

        $scope.sendToAddWord = function (english, russian) {
            if (english == null || russian == null)  return;
            $scope.showAddButton = false;
            send("<c:url value='/addWord'/>", JSON.stringify({english: english, russian: russian}))
        };

        function send(url, json) {
            $http.post(url, json).success(function () {
                clearEditWindow();
            });
        }

        function clearEditWindow() {
            $scope.english = null;
            $scope.russian = null;
            $scope.showAddButton = false;
            $scope.showEditButton = false;
            $scope.showWordEditorWindow = false;
        }

        $http.get("<c:url value='/getWords'/>").success(function (words) {
            $scope.rows = words;
        });

        $http.post("<c:url value='/getUserRoles'/>").success(function (userRoles) {
            userRoles.forEach(function (role) {
                if (role.authority === 'ROLE_ADMIN') $scope.role_admin = true;
            });
        });
//----------------------------------------------------------------------------------------------------------------------
        $scope.logout = function () {
            $http.post("<c:url value='/logout'/>").success(function () {
                window.location.href = "<c:url value='/'/>";
            })
        };

        $scope.newWords = function () {
            var url = $scope.loadLast === false ? "<c:url value='/getWords'/>" : "<c:url value='/getLastWords'/>";
            $http.get(url).success(function (words) {
                $scope.rows = words;
            });
        }
    });
</script>

</body>
</html>