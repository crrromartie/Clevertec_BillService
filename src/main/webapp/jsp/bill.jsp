<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 20.11.2020
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="bill.page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<c:if test="${purchases != null}">
    <p>CASH RECEIPT</p>
    <p>"CandyShop"</p>
    <p>Cashier #5</p>
    <p>${date}</p>
    <table>
        <thead>
        <tr>
            <th>QTY</th>
            <th>DESCRIPTION</th>
            <th>PRICE</th>
            <th>TOTAL</th>
            <th>PROMO</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="purshase" items="${purchases}">
            <tr>
                <td>${purshase.getQuantity()}</td>
                <td>${purshase.getProduct().getName()}</td>
                <td>${String.format("%.1f",purshase.getProduct().getPrice())}</td>
                <td>${String.format("%.1f", purshase.getTotal())}</td>
                <td>${String.format("%.1f", purshase.getPromoDiscount())}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>PROMO DISCOUNT: ${String.format("%.1f",totalPromoDiscount)}</p>
    <c:choose>
        <c:when test="${cardDiscount != 0}">
            <p>CARD DISCOUNT(${String.format("%.0f",cardDiscountPercent)}%): ${String.format("%.1f",cardDiscount)}</p>
        </c:when>
        <c:otherwise>
            <p>NO DISCOUNT CARD</p>
        </c:otherwise>
    </c:choose>
    <p>TOTAL FOR PAY: ${String.format("%.1f",totalForPay)}</p>
    <form name="saveBillForm" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="write_bill_txt"/>
        <button type="submit">
            <fmt:message key="bill.save.submit"/>
        </button>
    </form>
    <form name="saveBillToPDFForm" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="write_bill_pdf"/>
        <button type="submit">
            <fmt:message key="bill.save.submit_pdf"/>
        </button>
    </form>
    <form name="saveBillToPDFForm" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="write_bill_pdf_template"/>
        <button type="submit">
            <fmt:message key="bill.save.submit_pdf_template"/>
        </button>
    </form>
</c:if>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
