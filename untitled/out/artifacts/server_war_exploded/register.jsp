<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" href="css/login.css" type="text/css">
</head>

<body>
<%--<%--%>
<%--    // 判断是否有错误信息，如果有则打印--%>
<%--    // 如果没有此段代码，则显示时会直接打印null--%>
<%--    if(request.getAttribute("err")!=null)--%>
<%--    {--%>
<%--%>--%>
<%--<h2><%=request.getAttribute("err")%></h2>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>
<%--   <h1>留言管理程序--登陆页面</h1>--%>
<%--   	<hr/> 	<br/>--%>

<%--  <div class="login"   >--%>
<%--      <div class="1" style="height: 45%"></div>--%>
<%--      <div class="login1">--%>
<%--	<form action="Login" method="post"  >--%>
<%--	   用户ID:  <input type="text" name="id" /><br/>--%>
<%--	   密码： <input type="password" name="password"/><br/>--%>
<%--	   <input type="submit" value="登陆 "/>--%>
<%--	   <input type="reset" value="注册 "/>--%>
<%--	</form>--%>
<%--          </div>--%>
<%--  </div>--%>


<div class="form-wrapper">
    <form action="Register" method="post"  >
        <div class="header">
            立即注册
        </div>
        <div class="input-wrapper">
            <div class="border-wrapper">
                <input type="text" name="name" placeholder="name" class="border-item" autocomplete="off">
            </div>
            <div class="border-wrapper">
                <input type="text" name="id" placeholder="username" class="border-item" autocomplete="off">
            </div>
            <div class="border-wrapper">
                <input type="password" name="password" placeholder="password" class="border-item" autocomplete="off">
            </div>
            <select name="type" style="height: 20px" >
                <option value="0">用户注册</option>
                <option value="1"  >商家注册</option>
            </select>
        </div>
        <div class="action">
            <input type="submit" value="注册 " class="btn"/>
            <input type="reset" value="重置 " class="btn1"/>
        </div>
    </form>
</div>
</body>
</html>
