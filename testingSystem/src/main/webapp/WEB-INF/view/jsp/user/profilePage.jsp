<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <title>Users</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../fragment/header.jsp"></jsp:include>

<div class="dropdown choose-country top-right">
    <h3>${user.firstName}<span style='padding-left:10px;'> </span>${user.lastName}</h3>
    <h3><fmt:message key="testing.system.your.results" bundle="${msg}"/></h3>
    <ul>
        <h4>${"Test"}<span style='padding-left:25px;'> </span> ${"Score"}</h4>
        <c:forEach items="${testResultMap}" var="map">
            <li> <h4>${map.key.name}<span style='padding-left:20px;'> </span> ${map.value}</h4> </li>
        </c:forEach>
    </ul>
</div>
<jsp:include page="../fragment/footer.jsp"></jsp:include>

</body>
</html>