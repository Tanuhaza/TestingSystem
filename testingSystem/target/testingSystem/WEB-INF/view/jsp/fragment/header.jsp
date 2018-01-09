<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<fmt:setBundle basename="webProject.i18n.messages" var="msg"/>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
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
                    <a href="/home"><fmt:message key="home" bundle="${msg}"/></a>
                </li>
                <%--<li>--%>
                    <%--<a href="/cards"><fmt:message key="payments.menu.cards" bundle="${msg}"/></a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="/payments"><fmt:message key="payments" bundle="${msg}"/></a>--%>
                <%--</li>--%>
            </ul>
        </nav>
    </div>
</div>

