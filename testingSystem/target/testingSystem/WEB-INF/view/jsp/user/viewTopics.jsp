<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<fmt:setBundle basename="webProject.i18n.messages" var="msg"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <link rel="stylesheet" href="/css/home.css">
    <title>Users</title>

</head>
<body class="body-container">
<jsp:include page="../fragment/header.jsp"></jsp:include>

<div class="dropdown choose-country top-right">
    <table class="table borderless">

        <c:forEach items="${topics}" var="topic">
            <tr>
                <td><h4><a href="/topic/${topic.id} ">${topic.title} </a></h4></td>
                <td><h4>${topic.info}</h4></td>
            </tr>
        </c:forEach>

    </table>
</div>
<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
</html>