<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/view/taglib/Paginator.tld" prefix="custom" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <title>Users</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/home.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body class="body-container">
<jsp:include page="../fragment/header.jsp"/>

<div class="dropdown choose-country top-right">
    <table class="table borderless">
        <c:forEach items="${users}" var="user">
            <tr>
                <td><h5>${user.firstName} <span style='padding-left:5px;'> </span> ${user.lastName}</h5></a></td>
                <td><h5> ${user.email}</h5></td>
                <td><h5> ${user.role}</h5></td>
                <td>
                    <button class="btn-primary"
                            onclick="window.location = '/admin/user/${user.id}'" id="${user.id}_button"
                            name="${user.id}_button"><fmt:message key="testing.system.users.results"
                                                                  bundle="${msg}"/></button>
                </td>
            </tr>
        </c:forEach>
        <jsp:include page="../fragment/paginator.jsp"/>
    </table>
</div>

<jsp:include page="../fragment/footer.jsp"/>

</body>
</html>