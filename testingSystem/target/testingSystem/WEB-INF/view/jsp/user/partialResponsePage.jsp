<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<fmt:setBundle basename="webProject.i18n.messages" var="msg"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/quiz.css">
    <style>
        .messagebox-background {
            z-index: 100;
            position: absolute;
            top: 0;
            left: 0;
            margin: 0;
            padding: 0;
            height: 100%;
            width: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.6);
        }

        .messagebox-content {
            z-index: 101;
            position: relative;
            top: 50%;
            transform: translateY(-50%);
            margin: 0 auto;
            width: 50%;
            border-radius: 5px;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.9);
            font-size: large;
            color: #000000;
            text-align: center;
        }

    </style>
</head>
<body>

<div id="messageBox" class="messagebox-background" style="display: none">
    <div class="messagebox-content">
        <p> Please, answer on all questions </p>
        <button id="okButton" class="button-class"
                onclick="var err_box=document.getElementById('messageBox'); err_box.style.display='none';">OK
        </button>
    </div>
</div>

<c:if test="${!requestScope.isQuestionChecked}">
    <script>
        var err_box = document.getElementById("messageBox");
        err_box.style.display = "block";
    </script>
</c:if>

<form id="" action="/test" method="post">
    <div class="test-container">
        <ul>
            <c:set var="isTrue" value="${false}"/>
            <c:forEach items="${test}" var="entry">
                <div class="question">${entry.key.questionText}
                    <c:forEach items="${entry.value}" var="option">
                        <c:forEach items="${result}" var="result">
                            <c:forEach items="${result.value}" var="option_result">
                                <c:choose>
                                    <c:when test="${(option.id eq option_result.id)}">
                                        <div class="answer">
                                            <br> <input type="checkbox" name="${entry.key.id}" id="${option.id}"
                                                        value="${option.id}" checked/>
                                            <label for="${option.id}"> ${option.optionText}</label>
                                        </div>
                                        <c:set var="isTrue" value="${true}"/>
                                    </c:when>

                                </c:choose>
                            </c:forEach>
                        </c:forEach>
                        <c:if test="${!isTrue}">
                            <div class="answer">
                                <br> <input type="checkbox" name="${entry.key.id}" id="${option.id}"
                                            value="${option.id}"/>
                                <label for="${option.id}"> ${option.optionText}</label>
                            </div>
                        </c:if>
                        <c:set var="isTrue" value="${false}"/>
                    </c:forEach>
                </div>
            </c:forEach>
            <div class="button-center">
                <input class="button-class" type="submit" value="Submit Quiz"/>
            </div>
        </ul>
    </div>
</form>
</body>
</html>

