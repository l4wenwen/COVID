<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-07-01
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="template/import.jsp" />
    <link rel="stylesheet" href="../css/input-submit-for-all.css" />
</head>
<body>
<%request.setCharacterEncoding("UTF-8");%>

<jsp:include page="template/navigate.jsp">
    <jsp:param name="type" value="${sessionScope.user.userType}"/>
</jsp:include>
<div class="container">
    <jsp:include page="template/header.jsp" >
        <jsp:param name="position" value="PROFILE"/>
        <jsp:param name="userName" value="${sessionScope.user.userName}"/>
    </jsp:include>
    <div class="context">
        <div class="content">
            姓名：${requestScope.userProfile.userName}<br/>
            学号：${requestScope.userProfile.userNum}<br/>
            性别：${requestScope.userProfile.sex}<br/>
            学院：${requestScope.userProfile.collegeName}<br/>
            专业：${requestScope.userProfile.majorName}<br/>
            类型：${requestScope.userProfile.userType}<br/>
            <form action="${pageContext.request.contextPath}/user/update" method="post">
                手机号码：<input type="text" value="${requestScope.userProfile.telephone}" name="telephone">
                <input type="submit" value="申请修改" />
            </form>

            <button id="modal-button">修改密码</button>
            <div class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="close">x</span>
                        <h2>修改密码</h2>
                    </div>
                    <div class="modal-body">
                        密码：<input type="password" id="pwd" /><br/>
                        确认密码：<input type="password" id="repwd" /><br/>
                        <button id="changePwd">更改密码</button><br/>
                    </div>
                    <div class="modal-footer">
                        <div id="hide" style="display: none"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
