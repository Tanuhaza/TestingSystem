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
    <title>Users</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="../fragment/header.jsp"></jsp:include>


<ul>
    <h4><fmt:message key="testing.system.response.total.score" bundle="${msg}"/> </h4>
    <h4> <c:out value="${requestScope.sum}"/>  </h4>
    <c:set var="isTrue" value="${false}"/>
    <c:forEach items="${test}" var="entry">
        <h3>${entry.key.questionText}</h3><br>
        <c:forEach items="${entry.value}" var="option">
            <c:forEach items="${result}" var="result">
                <c:forEach items="${result.value}" var="option_result">
                <c:choose>
                    <c:when test="${(option.id eq option_result.id)&&(option.isCorrect())}">
                    <p>&#9745; ${option.optionText}<span style='padding-left:20px;'> </span>
                            <%--${option.score} </p>--%>
                        <%--<p> ${option.comment}</p><br>--%>
                        <c:set var="isTrue" value="${true}"/>
                    </c:when>
                    <c:when test="${(option.id eq option_result.id)&&(!option.isCorrect())}">
                        <p>&#9746;  ${option.optionText}</p> <span style='padding-left:20px;'> </span>
                        <%--${option.score} </p>--%>
                        <%--<p> ${option.comment}</p><br>--%>
                        <c:set var="isTrue" value="${true}"/>
                    </c:when>
                </c:choose>
                </c:forEach >
            </c:forEach>
            <c:if test="${!isTrue}"><p>&#9744; ${option.optionText}</p><span style='padding-left:20px;'> </span>
                 <%--${option.score} </p>--%>
                <%--<p> ${option.comment}</p><br>--%>
            </c:if>
            <c:set var="isTrue" value="${false}"/>
        </c:forEach>
    </c:forEach>
</ul>
<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
</html>