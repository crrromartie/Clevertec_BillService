<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="bill_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-8 offset-2">
                <c:if test="${bill != null}">
                    <p><c:out value="CASH RECEIPT"/></p>
                    <p><c:out value='"CandyShop"'/></p>
                    <p><c:out value="Cashier #5"/></p>
                    <p><c:out value="${bill.getDate()}"/></p>
                    <table class="table">
                        <thead>
                        <tr>
                            <th><c:out value="QTY"/></th>
                            <th><c:out value="DESCRIPTION"/></th>
                            <th><c:out value="PRICE"/></th>
                            <th><c:out value="TOTAL"/></th>
                            <th><c:out value="PROMO"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="purshase" items="${bill.getSinglePurchases()}">
                            <tr>
                                <td><c:out value="${purshase.getQuantity()}"/></td>
                                <td><c:out value="${purshase.getProduct().getName()}"/></td>
                                <td><c:out value="${String.format('%.2f', purshase.getProduct().getPrice())}"/></td>
                                <td><c:out value="${String.format('%.2f', purshase.getTotal())}"/></td>
                                <td><c:out value="${String.format('%.2f', purshase.getPromoDiscount())}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p><c:out value="PROMO DISCOUNT: ${String.format('%.2f', bill.getPromoDiscount())}"/></p>
                    <c:choose>
                        <c:when test="${bill.getCardDiscountPercent() != 0}">
                            <p><c:out value="CARD DISCOUNT(${String.format('%d', bill.getCardDiscountPercent())}%):"/>
                                <c:out value="${String.format('%.2f', bill.getCardDiscount())}"/></p>
                        </c:when>
                        <c:otherwise>
                            <p><c:out value="NO DISCOUNT CARD"/></p>
                        </c:otherwise>
                    </c:choose>
                    <p><c:out value="TOTAL FOR PAY: ${String.format('%.2f', bill.getTotalForPay())}"/></p>
                    <form name="SaveBill"
                          action="${pageContext.request.contextPath}/CandyShop"
                          method="post">
                        <input type="hidden" name="command" value="write_bill"/>
                        <input type="hidden" name="writing_format" value="txt">
                        <button type="submit" class="butt">
                            <fmt:message key="bill_page.save_submit"/>
                        </button>
                    </form>
                    <form name="SaveBillToPDF"
                          action="${pageContext.request.contextPath}/CandyShop"
                          method="post">
                        <input type="hidden" name="command" value="write_bill"/>
                        <input type="hidden" name="writing_format" value="pdf">
                        <button type="submit" class="butt">
                            <fmt:message key="bill_page.save_submit_pdf"/>
                        </button>
                    </form>
                    <form name="SaveBillToPDFTemplate"
                          action="${pageContext.request.contextPath}/CandyShop"
                          method="post">
                        <input type="hidden" name="command" value="write_bill"/>
                        <input type="hidden" name="writing_format" value="pdf_clevertec">
                        <button type="submit" class="butt">
                            <fmt:message key="bill_page.save_submit_pdf_template"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${saveBill eq true}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="bill_page.save_bill_success"/></label>
                    </div>
                </c:if>
                <c:if test="${saveBill eq false}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="bill_page.save_bill_fail"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
