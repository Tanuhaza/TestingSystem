<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <link rel="stylesheet" href="/css/home.css">
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body class="body-container">

<div class="top-bar">
    <a href="/home"><fmt:message key="testing.system.menu.home" bundle="${msg}"/> </a>
</div>
<div class="login-page">
    <p><fmt:message key="${confirmMessage}" bundle="${msg}"/> </p>
</div>
</body>
</html>