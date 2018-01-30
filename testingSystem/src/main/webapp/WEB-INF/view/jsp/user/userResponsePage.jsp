<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/quiz.css">
    <link rel="stylesheet" href="/css/home.css">
    <title>Users</title>
</head>
<body class="body-container">
<jsp:include page="../fragment/header.jsp"></jsp:include>


<h4 align="center"><fmt:message key="testing.system.response.total.score" bundle="${msg}"/> <span
        style='padding-left:10px;'> </span> <c:out value="${requestScope.sum}"/></h4>
<c:set var="isTrue" value="${false}"/>
<c:forEach items="${quiz}" var="entry">
    <div class="quiz-question">${entry.key.questionText}
        <c:forEach items="${entry.value}" var="option">
            <c:forEach items="${result}" var="result">
                <c:forEach items="${result.value}" var="option_result">
                    <c:choose>
                        <c:when test="${(option.id eq option_result.id)&&(option.isCorrect())}">
                            <div class="quiz-answer">
                                <div class="correct"> &#9745; ${option.optionText} <span
                                        style='padding-left:10px;'> </span>${option.score}</div>
                            </div>
                            <c:set var="isTrue" value="${true}"/>
                        </c:when>
                        <c:when test="${(option.id eq option_result.id)&&(!option.isCorrect())}">
                            <div class="quiz-answer">
                                <div class="incorrect">&#9746; ${option.optionText}<span
                                        style='padding-left:10px;'> ${option.score}</span></div>
                            </div>
                            <c:set var="isTrue" value="${true}"/>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </c:forEach>
            <c:if test="${!isTrue}">
                <c:choose>
                    <c:when test="${option.isCorrect()}">
                        <div class="quiz-answer">
                            <div class="correct">&#9744; ${option.optionText} <span
                                    style='padding-left:10px;'> </span>${option.score}</div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="quiz-answer">&#9744; ${option.optionText}<span
                                style='padding-left:10px;'> </span>${option.score}</div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:set var="isTrue" value="${false}"/>
        </c:forEach>
    </div>
</c:forEach>

<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
</html>