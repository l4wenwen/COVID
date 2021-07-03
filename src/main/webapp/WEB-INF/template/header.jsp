<%--
  Created by IntelliJ IDEA.
  User: Fish_Brother
  Date: 2021-07-02
  Time: 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="menu">
        <i class="fa fa-bars fa-lg"></i>
        <span style="margin-left: 5px;">${param.position}</span>
    </div>

    <div class="title">COVID</div>
    <div id="times" style="width: 200px; height: 20px;"></div>
    <div class="icon">${param.userName}</div>
    <script type="text/javascript">
        //得到时间并写入div
        function getDate(){
            //获取当前时间
            var date = new Date();
            //格式化为本地时间格式
            var date1 = date.toLocaleString();
            //获取div
            var div1 = document.getElementById("times");
            //将时间写入div
            div1.innerHTML = date1;
        }
        //使用定时器每秒向div写入当前时间
        setInterval("getDate()",1);
    </script>
</div>