<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <link rel="stylesheet" href="/css/home.css">
    <title>home page</title>
</head>
<body class="index-body">

<jsp:include page="/WEB-INF/view/jsp/fragment/header.jsp"/>

<div class="welcome-index"><fmt:message key="testing.system.welcome.to.testing.system" bundle="${msg}"/></div>
<div class ="welcome-text"><fmt:message key="testing.system.welcome.text" bundle="${msg}"/></div>
<div class="welcome-sign-in-out"> <fmt:message key="testing.system.sign.in.or.sign.out" bundle="${msg}"/></div>

<jsp:include page="/WEB-INF/view/jsp/fragment/footer.jsp"/>
</body>
</html>
