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
<form name="AddProduct" action="${pageContext.request.contextPath}/CandyShop" method="post">
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
    <br/><br/>
    <input type="text" name="price" title="<fmt:message key="product.page.price"/>"
           pattern="\d{1,2}[.,]\d{1,2}"
           placeholder="<fmt:message key="product.page.price"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="product.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="DeleteProduct" action="${pageContext.request.contextPath}/CandyShop" method="post">
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
        <fmt:message key="product.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="ChangePrice" action="${pageContext.request.contextPath}/CandyShop" method="post">
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
           pattern="\d{1,2}[.,]\d{1,2}"
           placeholder="<fmt:message key="product.page.price"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="product.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="MakePromo" action="${pageContext.request.contextPath}/CandyShop" method="post">
    <label>
        <fmt:message key="product.make.promo"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="make_promo"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="product.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>

<br/>

<form name="RemovePromo" action="${pageContext.request.contextPath}/CandyShop" method="post">
    <label>
        <fmt:message key="product.no.promo"/>
    </label>
    <br/><br/>
    <input type="hidden" name="command" value="remove_promo"/>
    <input type="text" name="name" title="<fmt:message key="product.page.name"/>"
           pattern="\w{3,30}"
           placeholder="<fmt:message key="product.page.name"/>"
           required
    />
    <br/>
    <c:if test="${incorrectProductData}">
        <fmt:message key="product.incorrect.product.data"/>
    </c:if>
    <br/>
    <button type="submit">
        <fmt:message key="product.page.submit"/>
    </button>
</form>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
