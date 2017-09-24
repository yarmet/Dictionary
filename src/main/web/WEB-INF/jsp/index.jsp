<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <title>Dictionary</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link href="${contextPath}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/res/css/index.css" rel="stylesheet">
</head>

<body>

<div style="display: none">
    <div id="rootPath">${contextPath}</div>
    <div id="csrfToken">${_csrf.token}</div>
</div>

<noscript>You need to enable JavaScript to run this app.</noscript>

<section class="container">
    <div id="root"></div>
</section>

<script type="text/javascript" src="${contextPath}/res/js/main.00d7bb0a.js"></script>
</body>


</html>