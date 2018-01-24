<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<fmt:setBundle basename="webProject.i18n.messages" var="msg"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <%--<fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>--%>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/login.js"></script>
    <script src="/js/login-form-initializer.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>

<div class="dropdown choose-country top-right">
    <ul>
        <c:forEach items="${tests}" var="test">
            <li><a href="/test ">${}</a></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>