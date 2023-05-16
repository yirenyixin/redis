<%@ page import="com.jspsmart.upload.SmartUpload" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>发布商品</title>
	<script type="text/javascript">
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
	<h1>留言管理范例--MVC+DAO实现</h1>
	<hr />	<br />
	<form action="Note" method="post">
		<table >
			<tr><td colspan="2">添加新留言</td></tr>
			<tr>
				<td>标题： </td>

			</tr>
			<tr>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
			<tr>
				<td>作者：</td>
			</tr>
			<tr>
				<td><%=session.getAttribute("uname")%>
			</tr>
				<input type="hidden" id="imgfile" name="img">
			<input type="hidden" name="authorimg" value="<%=session.getAttribute("uimg")%>">
			<tr>
				<input type="hidden" name="author" value="<%=session.getAttribute("uname")%>"/></td>
			</tr>
			<tr>
				<select name="type"  >
					<option value="-1">-请选择类型-</option>
					<option value="1"  >娱乐明星</option>
					<option value="2"  >体育</option>
					<option value="3"  >小说</option>
					<option value="4"  >生活家</option>
					<option value="5"  >闲趣</option>
					<option value="6"  >游戏</option>
					<option value="7"  >动漫</option>
					<option value="8"  >其他</option>
				</select>
			</tr>
			<tr>
				<td>内容：</td>
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
<%--	<form action="thisform" method="post" enctype="multipart/form-data" target="hidden_frame">--%>
<%--		<img   id="getImg1" style="width: 100px;height: 100px;"><br>--%>
<%--		<input type="file" name="pic" id="file" onchange="getName(this)">--%>
<%--		<input type="submit" value="保存图片" onclick="showDialog()">--%>
<%--	</form>--%>
	<form action="sucess.jsp" method="post" enctype="multipart/form-data" target="hidden_frame">

		<div id="img1">
			<img  id="getImg1" style="width: 100px;height: 100px;">
		</div>
		<input type="file" name="pic" id="file" onchange="getName(this)">
		<input type="submit" value="上传" onclick="showDialog()">
	</form>
	<h3><a href="list_note.jsp">回到首页</a></h3>
	<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>