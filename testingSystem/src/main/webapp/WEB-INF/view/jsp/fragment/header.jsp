<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>

<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/home.css">
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<fmt:requestEncoding value="UTF-8"/>

<div class="mainmenu-wrapper">
    <div class="container">
        <div class="menuextras">
            <div class="extras">
                <ul>
                    <li>
                        <jsp:include page="languageSelector.jsp"/>
                    </li>
                    <c:if test="${sessionScope.userId==null}">
                        <li><a href="/login"><fmt:message key="testing.system.login" bundle="${msg}"/></a></li>
                    </c:if>
                    <c:if test="${sessionScope.userId!=null}">
                        <li><a href="/logout"><fmt:message key="testing.system.logout" bundle="${msg}"/></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
        <nav id="mainmenu" class="mainmenu">
            <ul>
                <li>
                    <a href="/home"><fmt:message key="testing.system.menu.home" bundle="${msg}"/></a>
                </li>
                <li>
                    <a href="/topic"><fmt:message key="testing.system.menu.topics" bundle="${msg}"/></a>
                </li>
                <li>
                    <a href="/profile"><fmt:message key="testing.system.menu.profile" bundle="${msg}"/></a>
                </li>
                <c:if test="${sessionScope.userRole=='ADMIN'}">
                    <li>
                        <a href="/admin/users"><fmt:message key="testing.system.menu.users" bundle="${msg}"/></a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
