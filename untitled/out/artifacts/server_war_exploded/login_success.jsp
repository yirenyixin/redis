<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登陆成功</title>
	<link rel="stylesheet" href="css/login.css" type="text/css">
</head>
<body>
<%String flag= String.valueOf(session.getAttribute("uflag"));%>
	<%
		if(session.getAttribute("uname")!=null&&("0").equals(flag))
		{
			// 用户已登陆
	%>
	<div class="form-wrapper">
			<h2>登陆成功 </h2>
			<h2>欢迎用户 <span style="color: red;size: 12px">
				<%=session.getAttribute("uname")%>
			</span>光临淘宝网</h2>
			<h3><a href="Product?status=selectall&flag=0">进入首页</a></h3>
	</div>

	<%
		}else if(session.getAttribute("uname")!=null&&flag.equals("1")){
	%>

	<div class="form-wrapper">
		<h2>登陆成功 </h2>
		<h2>欢迎商家 <span style="color: red;size: 12px">
				<%=session.getAttribute("uname")%>
			</span>光临淘宝网</h2>
		<h3><a href="Product?status=selectall&flag=0">进入主页</a></h3>
	</div>
	<%
		}else{
		// 用户未登陆，提示用户登陆，并跳转
		response.setHeader("refresh","2;URL=login.jsp") ;
	%>
	<div class="form-wrapper">
		您还未登陆，请先登陆！！！<br/>
		两秒后跳转到登陆页面！！！<br/>
		如果没有跳转，请按<a href="login.jsp">这里 </a>！！！<br/>
	</div>
<%}%>
</body>
</html>