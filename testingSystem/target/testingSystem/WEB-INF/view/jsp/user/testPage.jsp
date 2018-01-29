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
</head>
<body>
<c:set var="count" value="${0}"/>
<form id="" action="/test" method="post">
    <div class="test-container">
        <ul>
            <c:forEach items="${test}" var="entry">
                <c:set var="count" value="${count+1}"/>
                <div class="question"><c:out value="${count}."/> <span
                        style='padding-left:20px;'> </span> ${entry.key.questionText}
                    <c:forEach items="${entry.value}" var="item">
                        <div class="answer">
                            <br> <input type="checkbox" name="${entry.key.id}" id="${item.id}" value="${item.id}"
                                        class="variant"/>
                            <label for="${item.id}"> ${item.optionText}</label>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <c:set var="count" value="${count+3}"/>
            <jsp:include page="../fragment/paginator.jsp"/>
            <div class = "button-center"> <input class="button-class" type="submit" value="Submit Quiz"/></div>
        </ul>
    </div>
</form>


</body>
</html>

