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
    <title><fmt:message key="product.page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<form name="addProductForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="product.page.add"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="add_product"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/>
    <c:if test="${nameUnique}">
        <fmt:message key="message.name.not.unique"/>
    </c:if>
    <br/>
    <input type="text" name="price" title="<fmt:message key="product.page.price"/>"
           pattern="\d{1,5}"
           placeholder="<fmt:message key="product.page.price"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="message.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="deleteProductForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="product.page.delete"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="delete_product"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="message.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="changePriceForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="product.change.price"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="change_price"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/><br/>
    <input type="text" name="price" title="<fmt:message key="product.page.price"/>"
           pattern="\d{1,5}"
           placeholder="<fmt:message key="product.page.price"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="message.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="makePromoForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="product.make.promo"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="promo_yes"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="message.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="makeNoPromoForm" action="${pageContext.request.contextPath}/controller" method="post">
    <label>
        <fmt:message key="product.no.promo"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="promo_no"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="message.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
