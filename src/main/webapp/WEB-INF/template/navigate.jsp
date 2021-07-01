<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/basic.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/navigate.css" rel="stylesheet">
<nav>
    <div class="logo">${sessionScope.user.userType == 0 ? "Admin" : "User"}</div>
    <div class="nav-item">
        <li><a href="${pageContext.request.contextPath}/user/userHome">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/user/profile">Profile</a></li>
        <c:if test="${sessionScope.user.userType == 2}">
            <li><a href="${pageContext.request.contextPath}/vacation/request">Ask For Leave</a></li>
        </c:if>
        <c:if test="${sessionScope.user.userType != 0}">
        <li><a href="${pageContext.request.contextPath}/vacation/list">Vacation</a></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
    </div>
    <c:if test="${sessionScope.user.userType != 2}">
        <form action="#">
            <input type="search" class="search-data" placeholder="Search" required/>
            <button type="submit" class="fas fa-search"></button>
        </form>
    </c:if>
</nav>