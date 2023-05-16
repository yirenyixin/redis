<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/16
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收货地址管理</title>
    <link rel="stylesheet" href="css/Receiving_address.css" type="text/css">
    <script>
        var x=0;
        function getflag(a){
            x++;
            if (x%2===1){
                document.getElementById("flag").value="1";
            }
            else  document.getElementById("flag").value="0";
        }
    </script>
</head>
<body>
<%String userid= String.valueOf(session.getAttribute("uId"));%>
<div class="page-index">
    <form action="address?status=insert" method="post" style="height: 310px;margin-left: 20%">
     <h2 style="color: #FF4400;font-size: 10px">新增收货地址</h2>

        <div style="display: flex">
       <h2 style="font-size: 10px;margin-top: 0px;margin-bottom: 0px;">城市</h2>
       <input type="text" style="height: 20px;margin-top: 0px;float: left" name="city" id="city">
       </div>
        <div style="display: flex;margin-top: 20px">
        <h2 style="display: flex;font-size: 10px;margin-top: 0px;margin-bottom: 0px;">详细地址</h2>
        <input type="text" style="height: 40px;margin-top: 0px;width: 300px" name="addr" id="addr">
        </div>
        <div style="display: flex;margin-top: 20px">
            <h2 style="display: flex;font-size: 10px;margin-top: 0px;margin-bottom: 0px;">收件人姓名</h2>
            <input type="text" style="height: 20px;margin-top: 0px" name="addressee" id="addressee">
        </div>
        <div style="display: flex;margin-top: 20px">
            <h2 style="display: flex;font-size: 10px;margin-top: 0px;margin-bottom: 0px;">手机号码</h2>
            <input type="text" style="height: 20px;margin-top: 0px" name="phone" id="phone">
        </div>
        <div style="display: flex;margin-top: 20px">
            <input type="checkbox" name="flag1" id="flag1" onclick="getflag(this)">
            <input type="hidden" name="flag" id="flag" value="0">
            <h2  style="display: flex;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >设置为默认地址</h2>
        </div>
        <div style="display: flex;margin-top: 20px">
<%--            <input type="hidden" name="status" id="status" value="insert">--%>
            <input type="hidden" name="userid" id="userid" value="<%=userid%>">
            <input type="submit" value="保存" style="background-color: #0eb4dd;color:white;border: none;margin-left: 50px ">
        </div>
    </form>
    <c:forEach items="${all}" var="address" >
    <table class="table" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <th>收件人</th>
            <th>所在地区</th>
            <th>详细地址</th>
            <th>电话/手机</th>
            <th>是否默认</th>
            <th>设置默认</th>
            <th>删除</th>
        </tr>
        <tr>
            <td>${address.addressee}</td>
            <td>${address.city}</td>
            <td>${address.addr}</td>
            <td>${address.phone}</td>
            <td><c:if test="${address.flag=='1'}">是</c:if><c:if test="${address.flag=='0'}">否</c:if></td>
            <td><a href="address?status=update&id=${address.id}&userid=<%=session.getAttribute("uId")%>">设为默认</a></td>
            <td><a href="address?status=delect&id=${address.id}">删除</a></td>
        </tr>
    </table>
    </c:forEach>
</div>
</body>
</html>
