<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/5/4
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回复</title>
    <script>
        function getName(cell){
            var myFile = document.getElementById("file").value;
            var length = myFile.length;
            var x = myFile.lastIndexOf("\\");
            x++;
            var fileName = myFile.substring(x,length);
            document.getElementById("imgfile").value = fileName;
            document.getElementById('getImg1').src= window.URL.createObjectURL(cell.files[0]);
        }
        function showDialog(){
            alert("上传成功");
        }
    </script>
</head>
<body>
<hr />	<br />
<%String title=request.getParameter("title");
    String noteid=request.getParameter("noteid");
    String author=request.getParameter("author");%>
<form action="Revert" method="post">
    <table>
        <tr><td colspan="2">添加新回复</td></tr>
        <tr>
        </tr>

        <tr>
            <td><input type="hidden" name="noteid" onfocus=this.blur() value="<%=noteid%>"></td>
        </tr>
        <tr>

            <td><input type="hidden" name="author" value="<%=author%>" onfocus=this.blur()></td>
        </tr>
            <input type="hidden" id="imgfile" name="img">
        <tr>
            <td><input type="hidden" name="writer" value="<%=session.getAttribute("uname")%>" onfocus=this.blur()></td>
            <input type="hidden" name="authorimg" value="<%=session.getAttribute("uimg")%>">
        </tr>
        <tr>
            <td><textarea name="content" cols="30" rows="6"></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="status" value="insert">
                <input type="submit" value="添加">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
<form action="sucess.jsp" method="post" enctype="multipart/form-data" target="hidden_frame">
    <img  id="getImg1" style="width: 100px;height: 100px;"><br>
    <input type="file" name="pic" id="file" onchange="getName(this)">
    <input type="submit" value="保存图片" onclick="showDialog()">
</form>

<h3><a href="list_note.jsp">回到留言列表页</a></h3>
<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>
