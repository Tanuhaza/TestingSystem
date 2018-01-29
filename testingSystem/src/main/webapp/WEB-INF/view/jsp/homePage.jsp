<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/home.css">
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>home page</title>
</head>
<body class="body-profile-container">
<jsp:include page="fragment/header.jsp"/>
<div class="user-welcome">
<div  class ="welcome" align="center"><fmt:message key="testing.system.you.are.welcom" bundle="${msg}"/></div>
<div class="user-first-last-name" align="center">${user.firstName}<span style='padding-left:10px;'> </span>${user.lastName}</div>
</div>
<jsp:include page="fragment/footer.jsp"/>
</body>
</html>


