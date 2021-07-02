<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${param.type != 2}">
    <script src="${pageContext.request.contextPath}/js/dynamicStatistic.js"></script>
</c:if>
<div class="side-nav">
    <div class="logo">
        <i class="fa fa-user-circle fa-3x"></i>
        <h1>${param.type == 0 ? "Admin" : "User"}</h1>
    </div>
    <ul class="nav-item">
        <a href="${pageContext.request.contextPath}/user/userHome"><li>Home</li></a>
        <c:if test="${param.type != 0}">
            <a href="${pageContext.request.contextPath}/vacation/list"><li>Vacation</li></a>
        </c:if>
        <c:if test="${param.type == 2}">
            <a href="${pageContext.request.contextPath}/state/request"><li>State</li></a>
            <a href="${pageContext.request.contextPath}/vacation/request"><li>Ask for leave</li></a>
        </c:if>
        <a href="${pageContext.request.contextPath}/user/logout"><li>Logout</li></a>
    </ul>
</div>