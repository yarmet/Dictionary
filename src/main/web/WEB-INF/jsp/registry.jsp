<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 27.01.2019
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>


<%--<form action="<c:url value='/login'/>" method='POST'>--%>

<form action="<c:url value='/registry'/>"   method="POST" >
    <input type="text" name="username"/> <br/>
    <input type="text" name="password"/>
    <input type="submit" />
</form>


</body>
</html>
