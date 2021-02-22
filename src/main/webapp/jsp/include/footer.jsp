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
<div class="wrapper">
    <div class="container">
        <div class="footer">
            <span><fmt:message key="footer.copyright"/></span>
        </div>
    </div>
</div>
</body>
</html>
