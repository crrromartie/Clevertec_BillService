<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 23.11.2020
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="card.page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<form name="AddCard" action="${pageContext.request.contextPath}/CandyShop" method="post">
    <label>
        <fmt:message key="card.page.add"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="add_discount_card"/>
    <input type="text" name="cardNumber" title="<fmt:message key="card.page.number"/>"
           pattern="\w{4}"
           size="25"
           placeholder="<fmt:message key="card.page.number"/>"
           required
    />
    <br/><br/>
    <input type="number" name="cardDiscountPercent" title="<fmt:message key="card.page.discount_percent"/>"
           pattern="\d{2}$"
           min="10"
           max="60"
           placeholder="<fmt:message key="card.page.discount_percent"/>"
           required
    />
    <br/>
    <c:if test="${incorrectCardData}">
        <fmt:message key="card.incorrect.card.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="card.page.submit"/>
    </button>
</form>

<br/>

<form name="DeleteCard" action="${pageContext.request.contextPath}/CandyShop" method="post">
    <label>
        <fmt:message key="card.page.delete"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="delete_discount_card"/>
    <input type="text" name="cardNumber" title="<fmt:message key="card.page.number"/>"
<%--           pattern="\w{4}"--%>
           size="25"
           placeholder="<fmt:message key="card.page.number"/>"
           required
    />
    <br/>
    <c:if test="${incorrectCardData}">
        <fmt:message key="card.incorrect.card.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="card.page.submit"/>
    </button>
</form>

<br/>

<form name="ChangeDiscount" action="${pageContext.request.contextPath}/CandyShop" method="post">
    <label>
        <fmt:message key="card.change.discount"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="change_discount_percent"/>
    <input type="text" name="cardNumber" title="<fmt:message key="card.page.number"/>"
           pattern="\w{4}"
           size="25"
           placeholder="<fmt:message key="card.page.number"/>"
           required
    />
    <br/><br/>
    <input type="text" name="cardDiscountPercent" title="<fmt:message key="card.page.discount_percent"/>"
           pattern="\d{2}$"
           min="10"
           max="60"
           placeholder="<fmt:message key="card.page.discount_percent"/>"
           required
    />
    <br/>
    <c:if test="${incorrectCardData}">
        <fmt:message key="card.incorrect.card.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="card.page.submit"/>
    </button>
</form>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
