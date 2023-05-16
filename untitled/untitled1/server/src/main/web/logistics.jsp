<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/23
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/Shopping_Cart.css" type="text/css">
    <title></title>
</head>
<body>
<%String flag= String.valueOf(session.getAttribute("uflag"));%>
<div class="J_FileBar">
</div>
<hr>
<div style="display: flex">
    <div style="float: left;">
        <h2 style="float: left;font-size: 10px;margin-top: 0;padding-left: 117px">商品图</h2>
    </div>
    <div style="float: left;padding-right:42px;width: 100px;height: 50px "></div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">商品名</h2>
    </div>
    <div style="float: left;padding-right:42px;width: 100px;height: 50px "></div>
    <div style="float: left;width: 120px;height: 50px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">状态</h2>
    </div>
    <div style="float: left;padding-left: 10px;height: 50px;width: 94px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">城市</h2>
    </div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">详细地址</h2>
    </div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">电话</h2>
    </div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">收件人</h2>
    </div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">数量</h2>
    </div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">价格</h2>
    </div>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">退货</h2>
    </div>
    <%if(flag.equals("0")){%>
    <div style="float: left;height: 50px;width: 105px">
        <h2 style="float: left;font-size: 10px;margin-top: 0">送达</h2>
    </div>
    <%}%>

</div >
<c:forEach items="${all}" var="ProductState" >
    <div style="width: 100%;height: 90px;background-color: bisque">
        <div style="margin-top: 25px">
            <div style="width: 100px;height: 110px;float: left;margin-left: 100px">
                <img src="./imgweb/${ProductState.img}" style="height: 80px;width: 80px;float: left">
            </div>
            <div style="width: 105px;height: 110px;float: left;margin-left: 70px">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.productname}</h2>
            </div>
            <div style="width: 125px;height: 110px;float: left;margin-left: 160px">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.state}</h2>
            </div>
            <div style="width: 105px;height: 110px;float: left;">
                <h2 style="float: left;font-size: 10px;margin-top: 0;">${ProductState.city}</h2>
            </div>
            <div style="width: 105px;height: 110px;float: left;">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.addr}</h2>
            </div>
            <div style="width: 105px;height: 110px;float: left;">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.phone}</h2>
            </div>
            <div style="width: 105px;height: 110px;float: left;">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.addressee}</h2>
            </div>
            <div style="width: 105px;height: 110px;float: left;">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.num}</h2>
            </div>
            <div style="width: 105px;height: 110px;float: left;">
                <h2 style="float: left;font-size: 10px;margin-top: 0">${ProductState.cost}</h2>
            </div>
<%--            <div style="width: 105px;height: 110px;float: left;">--%>
<%--                <td><a href="Indent?status=affirm&id=${ProductState.id}">确认订单</a></td>--%>
<%--            </div>--%>
        </div>
    </div>
</c:forEach>
</body>
</html>
