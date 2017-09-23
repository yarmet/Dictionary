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

    <script src="https://unpkg.com/react@15/dist/react.js"></script>
    <script src="https://unpkg.com/react-dom@15/dist/react-dom.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.38/browser.min.js"></script>
</head>

<body>

<div style="display: none">
    <div id="rootPath">${contextPath}</div>
    <div id="csrfToken">${_csrf.token}</div>
</div>

<div id="programm"></div>

<script src="${contextPath}/res/js/ajax.js"></script>
<script type="text/babel" src="${contextPath}/res/js/index.js"></script>


</body>
</html>