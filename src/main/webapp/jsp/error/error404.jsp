<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 19.11.2020
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="error.page.title"/></title>
</head>
<body>
<h1><fmt:message key="error.page.body404"/></h1>
</body>
</html>
