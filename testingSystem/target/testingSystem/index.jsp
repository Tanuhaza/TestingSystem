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
    <title>home page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/view/jsp/fragment/header.jsp"/>

<h3><fmt:message key="testing.system.welcome.to.testing.system" bundle="${msg}"/></h3>
<h4><fmt:message key="testing.system.welcome.text" bundle="${msg}"/></h4>
<h2><fmt:message key="testing.system.sign.in.or.sign.out" bundle="${msg}"/></h2>

<jsp:include page="/WEB-INF/view/jsp/fragment/footer.jsp"/>
</body>
</html>
