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
<c:if test="${bill != null}">
    <p>CASH RECEIPT</p>
    <p>"CandyShop"</p>
    <p>Cashier #5</p>
    <p>${bill.getDate()}</p>
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
        <c:forEach var="purshase" items="${bill.getSinglePurchases()}">
            <tr>
                <td>${purshase.getQuantity()}</td>
                <td>${purshase.getProduct().getName()}</td>
                <td>${String.format("%.2f",purshase.getProduct().getPrice())}</td>
                <td>${String.format("%.2f", purshase.getTotal())}</td>
                <td>${String.format("%.2f", purshase.getPromoDiscount())}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>PROMO DISCOUNT: ${String.format("%.2f",bill.getPromoDiscount())}</p>
    <c:choose>
        <c:when test="${bill.getCardDiscountPercent() != 0}">
            <p>CARD
                DISCOUNT(${String.format("%d",bill.getCardDiscountPercent())}%): ${String.format("%.2f",getCardDiscount())}</p>
        </c:when>
        <c:otherwise>
            <p>NO DISCOUNT CARD</p>
        </c:otherwise>
    </c:choose>
    <p>TOTAL FOR PAY: ${String.format("%.2f",bill.getTotalForPay())}</p>
    <form name="SaveBill" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="write_bill"/>
        <input type="hidden" name="writing_format" value="txt">
        <button type="submit">
            <fmt:message key="bill.save.submit"/>
        </button>
    </form>
    <form name="SaveBillToPDF" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="write_bill"/>
        <input type="hidden" name="writing_format" value="pdf">
        <button type="submit">
            <fmt:message key="bill.save.submit_pdf"/>
        </button>
    </form>
    <form name="SaveBillToPDFTemplate" action="${pageContext.request.contextPath}/CandyShop" method="post">
        <input type="hidden" name="command" value="write_bill"/>
        <input type="hidden" name="writing_format" value="pdf_clevertec">
        <button type="submit">
            <fmt:message key="bill.save.submit_pdf_template"/>
        </button>
    </form>
</c:if>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
