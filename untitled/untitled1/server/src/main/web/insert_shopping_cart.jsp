<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 27/6/2022
  Time: 下午9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<hr />	<br />
<%
    request.setCharacterEncoding("utf-8") ;
    response.setHeader("refresh","2;URL=Product?status=selectall&flag=0") ;
    boolean b = ((Boolean)request.getAttribute("flag")).booleanValue() ;
    if(b){
%>
添加成功，两秒后跳转到首页！！！<br/>
如果没有跳转，请按 <a href="Product?status=selectall&flag=0">这里 </a>！！！
<%
}else{
%>
添加失败购物车内已存在，两秒后跳转到首页 ！！！<br/>
如果没有跳转，请按<a href="Product?status=selectall&flag=0">这里</a>！！！
<%
    }
%>
</body>
</html>
