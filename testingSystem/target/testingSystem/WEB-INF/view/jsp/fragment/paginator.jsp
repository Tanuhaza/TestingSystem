<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.kiyv.training.testingSystem.utils.constants.Attributes" %>
<%@ taglib uri="/WEB-INF/view/taglib/Paginator.tld" prefix="custom" %>
<fmt:setBundle basename="${bundleFile}" var="msg"/>

<fmt:message key="paginator.first" var="first" bundle="${msg}"/>
<fmt:message key="paginator.previous" var="previous" bundle="${msg}"/>
<fmt:message key="paginator.next" var="next" bundle="${msg}"/>
<fmt:message key="paginator.last" var="last" bundle="${msg}"/>
<custom:paginator labelFirst="${first}"
                  labelPrevious="${previous}"
                  labelNext="${next}"
                  labelLast="${last}"
                  currentPageNumber="${requestScope[Attributes.CURRENT_PAGE]}"
                  lastPageNumber="${requestScope[Attributes.LAST_PAGE]}"
                  parameterPage="${Attributes.PAGE}"
/>
