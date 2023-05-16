<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jmu.factory.DAOFactory" %>
<%@ page import="jmu.model.Revert" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/5/14
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table width="80%" border="1">
  <tr><td>留言ID</td><td>帖子ID </td><td>内容 </td>
    <td>回帖者 </td><td>发帖者 </td><td>日期</td><td>删除</td>
  </tr>
  <c:forEach items="${all1}" var="revert">
    <tr>
      <td>${revert.revertID}</td>
      <td>${revert.noteID}</td>
      <td>${revert.content}<img src="./img/${revert.img}" style="height: 50px;width: 50px"></td>
      <td>${revert.writer}</td>
      <td>${revert.author}</td>
        <td>${revert.writeDate}</td>
      <td><a href="Revert?revertID=${revert.revertID}&status=delete&writer=${revert.writer}&author=${revert.author}&uname=<%=session.getAttribute("uname")%>">删除</a></td>
    </tr>
  </c:forEach>
</table>
      <%String title=request.getParameter("title");
      String author=request.getParameter("author");
      String noteid=request.getParameter("noteid");
%>
      <h3><a href="insert_revert.jsp?title=<%=title%>&author=<%=author%>&noteid=<%=noteid%>&status=insert" >新增回帖</a></h3>
</body>
</html>
