<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/14
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/Category_page.css" type="text/css">
    <title>分类主页</title>
</head>
<body>
<%String flag= String.valueOf(session.getAttribute("uflag"));%>
<div class="mainsrp-itemlist">
<c:forEach items="${all}" var="product" >

    <a href="Product_page.jsp?name=${product.name}&price=${product.price}&img=${product.img}&type=${product.type}&size=${product.size}&color=${product.color}&shopid=${product.shopid}&id=${product.id}&uid=<%=session.getAttribute("uId")%>">

        <div class="item-commodity">
        <div class="pic">
            <div  class="pic_link">
                <img src="./imgweb/${product.img}" class="pic_img">
            </div>
        </div>
        <div class="ctx-box">
            <div class="row_1"><div class="row_1-price">
                <span style="font-size: 18px;color: #F40">¥</span><strong style="color: #F40;font-weight: 700;">${product.price}</strong>
            </div></div>
            <div class="row_2">
                <div  class="row_2-title">${product.name}</div></div>
            <div class="row_3">
                    <div class="shop" >${product.shopname}</div>
<%--                <span style="float: right;">地区</span>--%>
            </div>
            <div class="row_4">
                <img src="./img/天猫.png" style="width: 16px;height: 16px">
                <img src="./img/淘宝.png" style="width: 16px;height: 16px;float: right">
            </div>
        </div>
    </div>
        <a>
</c:forEach>
<%--    <div class="item-commodity">--%>
<%--        <div class="pic">--%>
<%--            <a href="" class="pic_link">--%>
<%--                <img src="./img/头像.png" class="pic_img">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div class="ctx-box">--%>
<%--            <div class="row_1"><div class="row_1-price">--%>
<%--                <span style="font-size: 18px;color: #F40">¥</span><strong style="color: #F40;font-weight: 700;">29.80</strong>--%>
<%--            </div></div>--%>
<%--            <div class="row_2">--%>
<%--                <a href="" class="row_2-title">标题</a></div>--%>
<%--            <div class="row_3">--%>
<%--                <a class="shop" href="">店铺名</a>--%>
<%--                <span style="float: right;">地区</span>--%>
<%--            </div>--%>
<%--            <div class="row_4">--%>
<%--                <img src="./img/天猫.png" style="width: 16px;height: 16px">--%>
<%--                <img src="./img/淘宝.png" style="width: 16px;height: 16px;float: right">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="item-commodity">--%>
<%--        <div class="pic">--%>
<%--            <a href="" class="pic_link">--%>
<%--                <img src="./img/头像.png" class="pic_img">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div class="ctx-box">--%>
<%--            <div class="row_1"><div class="row_1-price">--%>
<%--                <span style="font-size: 18px;color: #F40">¥</span><strong style="color: #F40;font-weight: 700;">29.80</strong>--%>
<%--            </div></div>--%>
<%--            <div class="row_2">--%>
<%--                <a href="" class="row_2-title">标题</a></div>--%>
<%--            <div class="row_3">--%>
<%--                <a class="shop" href="">店铺名</a>--%>
<%--                <span style="float: right;">地区</span>--%>
<%--            </div>--%>
<%--            <div class="row_4">--%>
<%--                <img src="./img/天猫.png" style="width: 16px;height: 16px">--%>
<%--                <img src="./img/淘宝.png" style="width: 16px;height: 16px;float: right">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="item-commodity">--%>
<%--        <div class="pic">--%>
<%--            <a href="" class="pic_link">--%>
<%--                <img src="./img/头像.png" class="pic_img">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div class="ctx-box">--%>
<%--            <div class="row_1"><div class="row_1-price">--%>
<%--                <span style="font-size: 18px;color: #F40">¥</span><strong style="color: #F40;font-weight: 700;">29.80</strong>--%>
<%--            </div></div>--%>
<%--            <div class="row_2">--%>
<%--                <a href="" class="row_2-title">标题</a></div>--%>
<%--            <div class="row_3">--%>
<%--                <a class="shop" href="">店铺名</a>--%>
<%--                <span style="float: right;">地区</span>--%>
<%--            </div>--%>
<%--            <div class="row_4">--%>
<%--                <img src="./img/天猫.png" style="width: 16px;height: 16px">--%>
<%--                <img src="./img/淘宝.png" style="width: 16px;height: 16px;float: right">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="item-commodity">--%>
<%--        <div class="pic">--%>
<%--            <a href="" class="pic_link">--%>
<%--                <img src="./img/头像.png" class="pic_img">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div class="ctx-box">--%>
<%--            <div class="row_1"><div class="row_1-price">--%>
<%--                <span style="font-size: 18px;color: #F40">¥</span><strong style="color: #F40;font-weight: 700;">29.80</strong>--%>
<%--            </div></div>--%>
<%--            <div class="row_2">--%>
<%--                <a href="" class="row_2-title">标题</a></div>--%>
<%--            <div class="row_3">--%>
<%--                <a class="shop" href="">店铺名</a>--%>
<%--                <span style="float: right;">地区</span>--%>
<%--            </div>--%>
<%--            <div class="row_4">--%>
<%--                <img src="./img/天猫.png" style="width: 16px;height: 16px">--%>
<%--                <img src="./img/淘宝.png" style="width: 16px;height: 16px;float: right">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="item-commodity">--%>
<%--        <div class="pic">--%>
<%--            <a href="" class="pic_link">--%>
<%--                <img src="./img/头像.png" class="pic_img">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div class="ctx-box">--%>
<%--            <div class="row_1"><div class="row_1-price">--%>
<%--                <span style="font-size: 18px;color: #F40">¥</span><strong style="color: #F40;font-weight: 700;">29.80</strong>--%>
<%--            </div></div>--%>
<%--            <div class="row_2">--%>
<%--                <a href="" class="row_2-title">标题</a></div>--%>
<%--            <div class="row_3">--%>
<%--                <a class="shop" href="">店铺名</a>--%>
<%--                <span style="float: right;">地区</span>--%>
<%--            </div>--%>
<%--            <div class="row_4">--%>
<%--                <img src="./img/天猫.png" style="width: 16px;height: 16px">--%>
<%--                <img src="./img/淘宝.png" style="width: 16px;height: 16px;float: right">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>
</body>
</html>
