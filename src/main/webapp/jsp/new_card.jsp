<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="new_card_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="AddCard" class="form-horizontal" action="${pageContext.request.contextPath}/CandyShop"
                      method="post">
                    <input type="hidden" name="command" value="add_card"/>
                    <span class="heading"><fmt:message key="cards_page.add"/></span>
                    <div class="form-group">
                        <input type="number"
                               class="form-control"
                               name="cardNumber"
                               value="${cardParameters.get("cardNumber")}"
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
                               value="${cardParameters.get("cardDiscountPercent")}"
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
