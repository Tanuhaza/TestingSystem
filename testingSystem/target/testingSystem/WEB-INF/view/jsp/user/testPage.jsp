<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/WEB-INF/view/taglib/Paginator.tld" prefix="custom" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/quiz.css">
    <link rel="stylesheet" href="/css/home.css">
</head>
<body class="body-container" >
<c:set var="count" value="${0}"/>
<form id="" action="/test" method="post">
        <ul>
            <c:forEach items="${test}" var="entry">
                <c:set var="count" value="${count+1}"/>
                <div class="test-question"><c:out value="${count}."/> <span
                        style='padding-left:20px;'> </span> ${entry.key.questionText}
                    <c:forEach items="${entry.value}" var="item">
                        <div class="test-answer">
                            <br> <input type="checkbox" name="${entry.key.id}" id="${item.id}" value="${item.id}"
                                        class="variant"/>
                            <label for="${item.id}"> ${item.optionText}</label>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <div class = "button-center"> <input class="button-class" type="submit" value="Submit Quiz"/></div>
            <%--<jsp:include page="../fragment/paginator.jsp"/>--%>
        </ul>
</form>


</body>
</html>

