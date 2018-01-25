<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8">
    <title>Users</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../fragment/header.jsp"></jsp:include>

<div class="dropdown choose-country top-right">
    <table class="table borderless">
        <c:forEach items="${users}" var="user">
            <tr>
            <td> <a href="/admin/user/${user.id} ">
                 <h5>${user.firstName} <span style='padding-left:5px;'> </span> ${user.lastName}</h5> </a></td>
                <td><h5> ${user.email}</h5></td>
                <td><h5> ${user.role}</h5></td>
                <td><button class="btn-primary" id="${user.id}_button" name="${user.id}_button"><fmt:message key="testing.system.users.results" bundle="${msg}"/> </button></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="../fragment/footer.jsp"></jsp:include>

<%--<script type="text/javascript">--%>
    <%--$(document).on("click", ".btn-primary", function(){--%>
        <%--var but=$(this).attr('id').split('_')[0];--%>

        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url: "/admin/cards/unblock",--%>
            <%--data: {--%>
                <%--cardID:but--%>
            <%--},--%>
            <%--success: function(){--%>
                <%--var selector = "#"+but + "_button";--%>
                <%--$(selector).hide();--%>
                <%--alert('<fmt:message key="payments.successful.card.unblock" bundle="${msg}"/>');--%>
            <%--},--%>
            <%--error:function () {--%>
                <%--alert("<fmt:message key="payments.successful.card.unblock" bundle="${msg}"/>");--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

</body>
</html>