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
    <title>Users</title>
</head>
<body class="home">
<jsp:include page="../fragment/header.jsp"></jsp:include>
<table class="table borderless">
    <c:forEach items="${tests}" var="test">
        <tr>
            <td><a href="/test/${test.id} ">${test.name}</a></td>
            <td>
                <button class="btn-primary" id="${test.id}_button" name="${test.id}_button"><fmt:message
                        key="testing.system.pass.test" bundle="${msg}"/></button>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../fragment/footer.jsp"></jsp:include>

</body>
</html>