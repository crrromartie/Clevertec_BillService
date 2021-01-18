<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 23.11.2020
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="seller.page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<a href="${pageContext.request.contextPath}/CandyShop?command=product_pass"><fmt:message
        key="seller.change.product"/></a><br/>
<a href="${pageContext.request.contextPath}/CandyShop?command=card_pass"><fmt:message key="seller.change.card"/></a>
<jsp:include page="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>
</body>
</html>
