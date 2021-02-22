<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="products_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-8 offset-2">
                <c:if test="${products != null}">
                    <form name="OrderForm" class="form-horizontal"
                          action="${pageContext.request.contextPath}/CandyShop" method="post">
                        <input type="hidden" name="command" value="make_bill"/>
                        <div class="col-6 offset-3">
                            <h2><fmt:message key="products_page.product_list"/></h2>
                        </div>
                        <h6><fmt:message key="products_page.promo_text"/></h6>
                        <div class="form-group">
                            <a class="form-link"
                               href="${pageContext.request.contextPath}/CandyShop?command=add_product_pass">
                                <fmt:message key="product_page.add"/></a>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><fmt:message key="products_page.name"/></th>
                                <th><fmt:message key="products_page.price"/></th>
                                <th><fmt:message key="products_page.promo"/></th>
                                <th><fmt:message key="products_page.quantity"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/CandyShop?command=product_pass&productId=${product.getProductId()}">
                                            <c:out value="${product.getName()}"/>
                                        </a>
                                    </td>
                                    <td><c:out value="${String.format('%1.2f', product.getPrice())}"/></td>
                                    <c:choose>
                                        <c:when test="${product.isPromo() eq true}">
                                            <td><fmt:message key="products_page.yes"/></td>
                                        </c:when>
                                        <c:when test="${product.isPromo() eq false}">
                                            <td><fmt:message key="products_page.no"/></td>
                                        </c:when>
                                    </c:choose>
                                    <td>
                                        <input type="number"
                                               class="form-control"
                                               name="${product.getProductId()}"
                                               title="<fmt:message key="products_page.enter_quantity"/>"
                                               placeholder="<fmt:message key="products_page.enter_quantity"/>"
                                               max="99"
                                               value=""
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="oninvalid.quantity_pattern"/>')"
                                               onchange="this.setCustomValidity('')"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="row">
                            <c:if test="${products.size() > 0}">
                                <div class="col-8 offset-1">
                                    <input type="text"
                                           class="form-control"
                                           name="cardNumber"
                                           title="<fmt:message key="products_page.card"/>"
                                           placeholder="<fmt:message key="products_page.card"/>"
                                           pattern="\d{4}"
                                           value=""
                                           oninvalid="this.setCustomValidity('<fmt:message
                                                   key="oninvalid.card_number_pattern"/>')"
                                           onchange="this.setCustomValidity('')"/>
                                </div>
                                <button type="submit" class="btn btn-default">
                                    <fmt:message key="products_page.submit"/>
                                </button>
                            </c:if>
                        </div>
                    </form>
                </c:if>
                <c:if test="${products.size() == 0}">
                    <div class="message-empty">
                        <fmt:message key="products_page.message_empty"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
