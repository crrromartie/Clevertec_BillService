<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="cards_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-8 offset-2">
                <c:if test="${cards != null}">
                    <div class="col-6 offset-5">
                        <h6><fmt:message key="cards_page.card_list"/></h6>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th><fmt:message key="cards_page.number"/></th>
                            <th><fmt:message key="cards_page.discount_percent"/></th>
                            <th><fmt:message key="cards_page.action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="card" items="${cards}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/CandyShop?command=card_pass&cardId=${card.getCardId()}">
                                        <c:out value="${card.getCardNumber()}"/>
                                    </a>
                                </td>
                                <td><c:out value="${card.getDiscountPercent()}"/></td>
                                <td>
                                    <form name="DeleteCard" action="${pageContext.request.contextPath}/CandyShop"
                                          method="get">
                                        <input type="hidden" name="command" value="delete_card"/>
                                        <input type="hidden" name="cardId" value="${card.getCardId()}"/>
                                        <button type="submit" class="submit-button" id="delButt">
                                            <fmt:message key="cards_page.delete_card"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${cards.size() == 0}">
                    <div class="message-empty">
                        <fmt:message key="cards_page.message_empty"/>
                    </div>
                </c:if>
                <a href="${pageContext.request.contextPath}/CandyShop?command=add_card_pass">
                    <fmt:message key="cards_page.add"/></a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
