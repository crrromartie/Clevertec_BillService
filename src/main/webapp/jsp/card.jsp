<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="card_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="EditCard" class="form-horizontal" action="${pageContext.request.contextPath}/CandyShop"
                      method="post">
                    <input type="hidden" name="command" value="edit_card"/>
                    <span class="heading"><fmt:message key="card_page.edit_card"/></span>
                    <div class="form-group">
                        <input type="number"
                               class="form-control"
                               name="cardNumber"
                               value="${card.getCardNumber()}"
                               title="<fmt:message key="card_page.card_number"/>"
                               min="1000"
                               max="9999"
                               required
                               placeholder="<fmt:message key="card_page.card_number"/>"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.card_number_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <input type="number"
                               class="form-control"
                               name="cardDiscountPercent"
                               value="${card.getDiscountPercent()}"
                               title="<fmt:message key="card_page.discount_percent"/>"
                               min="10"
                               max="40"
                               required
                               placeholder="<fmt:message key="card_page.discount_percent"/>"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.card_discount_percent_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="card_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${uniqueCardNumberError}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="card_page.card_number_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${incorrectCardData}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="card_page.incorrect_card_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
