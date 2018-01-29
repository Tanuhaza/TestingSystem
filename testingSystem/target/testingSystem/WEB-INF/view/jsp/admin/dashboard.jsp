<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="${bundleFile}" var="msg"/>
    <title>Admin page</title>
</head>
<body>
<jsp:include page="../fragment/header.jsp"></jsp:include>

<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
</html>
