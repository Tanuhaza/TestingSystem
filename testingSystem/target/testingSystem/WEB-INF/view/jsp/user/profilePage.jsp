<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <title>Users</title>
</head>
<body>
<jsp:include page="../fragment/header.jsp"></jsp:include>

<div class="dropdown choose-country top-right">
    <h3>${user.firstName}<span style='padding-left:10px;'> </span>${user.lastName}</h3>
    <h3><fmt:message key="testing.system.your.results" bundle="${msg}"/></h3>
    <table class="table borderless">
        <tr>
            <td><h4>${"Test"}</h4></td>
            <td><h4>${"Score"}</h4></td>
        </tr>
        <c:forEach items="${firstTestResultMap}" var="map">
            <tr>
                <td><h4>${map.key.name}</h4></td>
                <td><h4>${map.value}</h4></td>
            </tr>
        </c:forEach>
        <c:forEach items="${lastTestResultMap}" var="map">
            <tr>
                <td><h4>${map.key.name}</h4></td>
                <td><h4>${map.value}</h4></td>
            </tr>
        </c:forEach>

    </table>
</div>
<jsp:include page="../fragment/footer.jsp"></jsp:include>

</body>
</html>