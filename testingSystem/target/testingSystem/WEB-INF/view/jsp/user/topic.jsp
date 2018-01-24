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
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Users</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="../fragment/header.jsp"></jsp:include>

<div class="dropdown choose-country top-right">
    <ul>
        <c:forEach items="${topics}" var="topic">
            <%--<input  type="hidden" id="Attributes.TOPIC_ID" name="${Attributes.TOPIC_ID}" value="${topic.getId()}">--%>
            <li><a href="/topic/${topic.id} ">${topic.title} </a></li>
        </c:forEach>
    </ul>
</div>
<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
</html>