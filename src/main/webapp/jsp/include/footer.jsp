<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 19.11.2020
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title>Footer</title>
</head>
<body>
<div class="line"></div>
<div class="footer">
    <div class="container footer-content">
        <span><fmt:message key="footer.copyright"/></span>
    </div>
</div>
<div class="line"></div>
</body>
</html>
