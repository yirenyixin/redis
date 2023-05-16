<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> </title>
</head>
<body>
<%
    response.setHeader("refresh","2;URL=Revert?status=selectall") ;
    boolean b = ((Boolean)request.getAttribute("flag")).booleanValue() ;
    if(b){
%>
回帖删除成功，两秒后跳转到留言列表页！！！<br/>
如果没有跳转，请按 <a href="Note?status=selectall">这里</a>！！！
<%
}else{
%>
回帖删除失败，两秒后跳转到留言列表页 ！！！<br/>
如果没有跳转，请按 <a href="Note?status=selectall">这里</a>！！！
<%
    }
%>
</body>
</html>