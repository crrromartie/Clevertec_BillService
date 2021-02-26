<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="product_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="EditProduct" class="form-horizontal" action="${pageContext.request.contextPath}/CandyShop"
                      method="post">
                    <input type="hidden" name="command" value="edit_product"/>
                    <span class="heading"><fmt:message key="product_page.edit_product"/></span>
                    <div class="form-group">
                        <a class="form-link"
                           href="${pageContext.request.contextPath}/CandyShop?command=delete_product&productId=${product.getProductId()}">
                            <fmt:message key="product_page.delete"/></a>
                    </div>
                    <div class="form-group">
                        <input type="text"
                               class="form-control"
                               name="name"
                               value="${product.getName()}"
                               title="<fmt:message key="product_page.name"/>"
                               pattern=".{3,30}"
                               required
                               placeholder="<fmt:message key="product_page.name"/>"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.product_name_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <input type="number"
                               step="0.01"
                               class="form-control"
                               name="price"
                               value="${product.getPrice()}"
                               title="<fmt:message key="product_page.price"/>"
                               min="1.00"
                               max="99.00"
                               required
                               placeholder="<fmt:message key="product_page.price"/>"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.product_price_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <label for="select"><fmt:message key="product_page.change_promo"/></label>
                        <select class="custom-select" id="select" name="productPromo">
                            <option selected value="${product.isPromo()}">
                                <fmt:message key="product_page.no_changes"/></option>
                            <option value="true"><fmt:message key="product_page.set_promo"/></option>
                            <option value="false"><fmt:message key="product_page.remove_promo"/></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="product_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${uniqueProductNameError}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="product_page.product_name_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${incorrectProductData}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="product_page.incorrect_product_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
