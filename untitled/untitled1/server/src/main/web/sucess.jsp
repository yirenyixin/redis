<%@ page import="com.jspsmart.upload.SmartUpload" %><%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/1
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    SmartUpload smart = new SmartUpload();

    smart.initialize(pageContext);  //初始化上传
    smart.upload();  //上传准备
    smart.save("C:\\Users\\yirenyixin\\Desktop\\untitled1\\server\\src\\main\\web\\imgweb");
    out.print("文件上传成功");
%>
</body>
</html>
