<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8") ;
		response.setHeader("refresh","2;URL=Product?status=selectall&flag=0") ;
		boolean b = ((Boolean)request.getAttribute("flag")).booleanValue() ;
		if(b){
	%>
				修改成功，两秒后跳转到首页！！！<br/>
				如果没有跳转，请按 <a href="Product?status=selectall&flag=0">这里</a>！！！
	<%
		}else {
	%>
				修改失败，两秒后跳转到首页 ！！！<br/>
				如果没有跳转，请按<a href="Product?status=selectall&flag=0">这里</a>！！！
	<%
		}
	%>
</body>
</html>