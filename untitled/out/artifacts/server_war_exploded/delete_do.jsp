<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>删除 </title>
</head>
<body>
	<hr />	<br />
	<%
			response.setHeader("refresh","2;URL=Product?status=selectall&flag=0") ;
			boolean b = ((Boolean)request.getAttribute("flag")).booleanValue() ;
			if(b){
	%>
				删除成功，两秒后跳转到留言列表页！！！<br/>
				如果没有跳转，请按 <a href="Product?status=selectall&flag=0">这里</a>！！！
	<%
			}else{
	%>
				删除失败，两秒后跳转到留言列表页 ！！！<br/>
				如果没有跳转，请按 <a href="Product?status=selectall&flag=0">这里</a>！！！
	<%
			}
	%>
</body>
</html>