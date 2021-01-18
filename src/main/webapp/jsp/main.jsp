<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 19.11.2020
  Time: 19:16
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
    <title><fmt:message key="main.page.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general-styles.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<c:if test="${products == null}">
    <br/>
    <form name="viewOrderForm" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="view_products"/>
        <button id="but-prod" type="submit">
            <fmt:message key="main.show.product.list"/>
        </button>
    </form>
</c:if>
<c:if test="${products != null}">
    <form name="orderForm" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="make_bill"/>
        <h2><fmt:message key="main.page.product.list"/></h2>
        <h4><fmt:message key="main.page.promo.text"/></h4>
        <c:forEach var="product" items="${products}">
            <label>${product.getName()}</label>
            <c:if test="${product.isPromotional()}">
                <label>*</label>
            </c:if>
            <input type="text"
                   name="${product.getProductId()}"
                   title="<fmt:message key="main.enter.quantity"/>"
                   placeholder="<fmt:message key="main.enter.quantity"/>"
                   pattern="\d{1,2}"
                   size="28"
                   value=""
            />
            <br/><br/>
        </c:forEach>
        <input type="text"
               name="cardNumber"
               placeholder="<fmt:message key="main.page.card"/>"
               title="<fmt:message key="main.page.card"/>"
               pattern="\d{4}"
               size="39"
               value=""
        />
        <button type="submit">
            <fmt:message key="main.page.submit"/>
        </button>
    </form>
</c:if>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
