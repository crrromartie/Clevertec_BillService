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
<form name="addCardForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="card.page.add"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="add_card"/>
    <input type="text" name="cardNumber" title="<fmt:message key="card.page.number"/>"
           pattern="\w{4}"
           size="25"
           placeholder="<fmt:message key="card.page.number"/>"
           required
    />
    <br/>
    <c:if test="${cardUnique}">
        <fmt:message key="message.card.not.unique"/>
    </c:if>
    <br/>
    <input type="text" name="discount" title="<fmt:message key="card.page.discount"/>"
           pattern="^[0-9]*[.,]?[0-9]+$"
           placeholder="<fmt:message key="card.page.discount"/>"
           required
    />
    <br/>
    <c:if test="${incorrectCardData}">
        <fmt:message key="message.incorrect.card.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="card.page.submit"/>
    </button>
</form>

<br/>

<form name="deleteCardForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="card.page.delete"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="delete_card"/>
    <input type="text" name="cardNumber" title="<fmt:message key="card.page.number"/>"
           pattern="\w{4}"
           size="25"
           placeholder="<fmt:message key="card.page.number"/>"
           required
    />
    <br/>
    <c:if test="${incorrectCardData}">
        <fmt:message key="message.incorrect.card.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="card.page.submit"/>
    </button>
</form>

<br/>

<form name="changeDiscountForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="card.change.discount"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="change_promo"/>
    <input type="text" name="cardNumber" title="<fmt:message key="card.page.number"/>"
           pattern="\w{4}"
           size="25"
           placeholder="<fmt:message key="card.page.number"/>"
           required
    />
    <br/><br/>
    <input type="text" name="discount" title="<fmt:message key="card.page.discount"/>"
           pattern="^[0-9]*[.,]?[0-9]+$"
           placeholder="<fmt:message key="card.page.discount"/>"
           required
    />
    <br/>
    <c:if test="${incorrectCardData}">
        <fmt:message key="message.incorrect.card.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="card.page.submit"/>
    </button>
</form>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
