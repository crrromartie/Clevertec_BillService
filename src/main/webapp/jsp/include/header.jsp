<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 19.11.2020
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general-styles.css">
    <title>Header</title>
</head>
<body>
<div class="line"></div>
<div class="header">
    <a href="${pageContext.request.contextPath}/CandyShop?command=main_pass"><fmt:message key="header.name"/></a><br/>
    <a href="${pageContext.request.contextPath}/CandyShop?command=seller_pass"><fmt:message
            key="seller.page.title"/></a>
    <c:choose>
        <c:when test="${sessionScope.lang eq 'en'}">
            <fmt:message key="header.language_en"/>
        </c:when>
        <c:otherwise>
            <fmt:message key="header.language_ru"/>
        </c:otherwise>
    </c:choose>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/CandyShop?command=change_locale&lang=ru"><span><fmt:message
                    key="header.language_ru"/></span></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/CandyShop?command=change_locale&lang=en"><span><fmt:message
                    key="header.language_en"/></span></a>
        </li>
    </ul>
</div>
<div class="line"></div>
</body>
</html>
