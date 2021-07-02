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
        <a href="${pageContext.request.contextPath}/user/userHome"><li>个人主页</li></a>
        <c:if test="${param.type != 0}">
            <a href="${pageContext.request.contextPath}/vacation/list"><li>请假情况</li></a>
        </c:if>
        <c:if test="${param.type == 2}">
            <a href="${pageContext.request.contextPath}/state/request"><li>每日打卡</li></a>
            <a href="${pageContext.request.contextPath}/state/list"><li>打卡情况</li></a>
            <a href="${pageContext.request.contextPath}/vacation/request"><li>请 假</li></a>
        </c:if>
        <a href="${pageContext.request.contextPath}/user/profile"><li>个人资料</li></a>
        <a href="${pageContext.request.contextPath}/user/logout"><li>注销</li></a>
    </ul>
</div>