<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <link rel="stylesheet" href="/css/home.css">
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body class="body-container">

<jsp:include page="fragment/header.jsp"></jsp:include>
    <div class="user-welcome">
        <div  class ="welcome" align="center"><fmt:message key="testing.system.you.are.welcom" bundle="${msg}"/></div>
        <div class="user-first-last-name" align="center">${user.firstName}<span style='padding-left:10px;'> </span>${user.lastName}</div>
    </div>
<jsp:include page="fragment/footer.jsp"></jsp:include>
</body>
</html>