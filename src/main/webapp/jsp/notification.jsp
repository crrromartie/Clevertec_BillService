<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 22.11.2020
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="notification.page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<c:choose>
    <c:when test="${saveBill eq true}">
        <p><fmt:message key="notification.save.bill.success"/></p>
    </c:when>
    <c:when test="${saveBill eq false}">
        <p><fmt:message key="notification.save.bill.fail"/></p>
    </c:when>
</c:choose>

<c:if test="${addProduct}">
    <p><fmt:message key="notification.add.product"/></p>
</c:if>

<c:if test="${deleteProduct}">
    <p><fmt:message key="notification.delete.product"/></p>
</c:if>

<c:if test="${changePrice}">
    <p><fmt:message key="notification.change.price"/></p>
</c:if>

<c:if test="${promoYes}">
    <p><fmt:message key="notification.promo.yes"/></p>
</c:if>

<c:if test="${promoNo}">
    <p><fmt:message key="notification.promo.no"/></p>
</c:if>

<c:if test="${addCard}">
    <p><fmt:message key="notification.add.card"/></p>
</c:if>

<c:if test="${deleteCard}">
    <p><fmt:message key="notification.delete.card"/></p>
</c:if>

<c:if test="${changeDiscount}">
    <p><fmt:message key="notification.change.discount"/></p>
</c:if>

<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
