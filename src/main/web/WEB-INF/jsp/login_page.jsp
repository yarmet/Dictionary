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
    <link href="<c:url value="/res/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/res/css/login.css" />" rel="stylesheet">
</head>


<body>

<section class="container">


    <nav class="navbar navbar-default">
        <div class="container-fluid">

            <div class="nav navbar-nav">
                <div class="navbar-brand"><a href="<c:url value="/welcome"/>">главная</a></div>
            </div>

            <div class="nav navbar-nav navbar-right">
                <div div class="navbar-brand"><a href=" <c:url value="/registry" />">регистрация</a></div>
            </div>
        </div>
    </nav>


    <h2>войти</h2>
    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
            <form action="<c:url value='/login'/>" method='POST'>
                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

                <div class="form-group has-error">
                    <label for="login">Логин</label>
                    <input class="form-control" autofocus name="username" id="login" maxlength="15"
                           oninput="loginChanged(this)">
                </div>

                <div class="form-group has-error">
                    <label for="pass">Пароль</label>
                    <input class="form-control" type="password" name="password" id="pass" maxlength="15"
                           oninput="passwordChanged(this)">
                </div>

                <div class="checkbox">
                    <label><input name="remember-me" type="checkbox">запомнить меня</label>
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" type="submit" value="войти" id="button">
                </div>
                <span>${message}</span>
                <span>${error}</span>
            </form>
        </div>
    </div>
</section>


<script>
    var loginIsValid = false;
    var passIsValid = false;
    var button = document.getElementById("button");
    button.disabled = true;

    function setValid(inp, addClass, removeClass) {
        inp.classList.add(addClass);
        inp.classList.remove(removeClass);
    }

    function loginChanged(input) {
        if (/\s/g.test(input.value) || input.value.length < 3) {
            setValid(input.parentNode, 'has-error', 'has-success');
            loginIsValid = false;
        } else {
            setValid(input.parentNode, 'has-success', 'has-error');
            loginIsValid = true;
        }
        checkButton();
    }

    function passwordChanged(input) {
        if (/\s/g.test(input.value) || input.value.length < 3) {
            setValid(input.parentNode, 'has-error', 'has-success');
            passIsValid = false;
        } else {
            setValid(input.parentNode, 'has-success', 'has-error');
            passIsValid = true;
        }
        checkButton();
    }

    function checkButton() {
        button.disabled = !(loginIsValid && passIsValid);
    }

</script>


</body>
</html>
