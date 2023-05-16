<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/17
  Time: 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单确认</title>
    <link rel="stylesheet" href="css/Confirm_order.css" type="text/css">
    <script>
        function get(c){
            var a=c.value;
            document.getElementById("city").value=document.getElementById("city"+a).value;
            document.getElementById("addr").value=document.getElementById("addr"+a).value;
            document.getElementById("phone").value=document.getElementById("phone"+a).value;
            document.getElementById("addressid").value=document.getElementById("addressid"+a).value;
            document.getElementById("buyername").value=document.getElementById("addressee"+a).value;
        }
        function check(){
            var a=document.getElementById("balance_buyer").value;
            var b=document.getElementById("price").value;
            if(parseInt(a)>=parseInt(b))return true;
            else {
                alert("余额不足，请充值！！！")
                return false;
            }
        }
        function reduce(){
            var x=document.getElementById("product_num").value;
            var y=x;
            if(x==1){
                alert("不能再少了！！！")
            }else{
                y=parseInt(x)-1;
            }
            document.getElementById("product_num").value=y;
            document.getElementById("num").value=y;
            var n=document.getElementById("price0").value;
            var t=parseInt(n)*parseInt(y);
            document.getElementById("price").value=t;
        }
        function add(){
            var x=document.getElementById("product_num").value;
            var q=document.getElementById("stock").value;
            if(parseInt(x)>=parseInt(q)){
                alert("没有库存了！！！")
            }else {
                var y = parseInt(x) + 1;
                document.getElementById("product_num").value = y;
                document.getElementById("num").value = y;
                var n = document.getElementById("price0").value;
                var t = parseInt(n) * parseInt(y);
                document.getElementById("price").value = t;
            }
        }
        // function getnum(){
        //     var x=document.getElementById("product_num").value;
        //     var y=document.getElementById("price1").value;
        //     var t=parseInt(x)*parseInt(y);
        //     document.getElementById("price1").value=t;
        //     document.getElementById("price").value=t;
        // }
    </script>
</head>
<body>
<div class="header-wrapper">
<h2 style="font-size: 15px;color: #f10215">选择收货地址</h2>
</div>
<div class="address-list">
    <%int a=0;
    String num= String.valueOf(session.getAttribute("num"));%>
<c:forEach items="${address}" var="address" >
<div class="addr-item-wrapper" >

    <div style="display: flex;margin-top: 2px;margin-bottom: 2px;width: 100%;height: 96px;flex-wrap: wrap" >
        <input type="hidden" name="city<%=a%>" id="city<%=a%>" value="${address.city}">
        <input type="hidden" name="addr<%=a%>"  id="addr<%=a%>" value="${address.addr}">
        <input type="hidden" name="phone<%=a%>" id="phone<%=a%>" value="${address.phone}">
        <input type="hidden" name="flag<%=a%>" id="flag<%=a%>" value="${address.flag}">
        <input type="hidden" name="addressid<%=a%>" id="addressid<%=a%>" value="${address.id}">
        <input type="hidden" name="addressee<%=a%>" id="addressee<%=a%>" value="${address.addressee}">
        <h2  style="display: flex;font-size: 8px;margin-top: 0px;margin-bottom: 0px;width: 100%;height: 25px" >${address.city}（${address.addressee}）</h2>
        <h2 style="display: flex;font-size: 5px;margin-top: 0px;margin-bottom: 0px;width: 100%;height: 25px">${address.addr}</h2>
        <h2 style="display: flex;font-size: 5px;margin-top: 0px;margin-bottom: 0px;width: 100%;height: 25px">${address.phone}</h2>
        <input type="radio" name="check" id="check<%=a%>" value="<%=a%>" onclick="get(this)" >
        <script>
            var x=document.getElementById("flag<%=a%>").value;
            if(x==1){
                document.getElementById("check<%=a%>").checked=true;
            }
        </script>
    </div>
</div>
    <%a++;%>
</c:forEach>
    <input type="hidden" name="a" id="a" value="<%=a%>">
</div>
<a href="Receiving_address.jsp" style="display: flex">使用新地址</a>


<div class="item-row">
<c:forEach items="${product}" var="product" >
    <img src="imgweb/${product.img}" style="width: 150px;height: 150px;float: left">
    <h2 style="float: left">${product.name}</h2>
    <h2 style="float: left;margin-left: 20px">库存：${product.stock}</h2>
    <input type="hidden" id="stock" value="${product.stock}">
    <input type="hidden" id="productname0" value="${product.name}">
    <c:forEach items="${person}"  var="person">
    <h2 style="float: left;margin-left: 30px" > ${person.name}</h2>
        <input type="hidden" id="shopname1" value=" ${person.name}">
    </c:forEach>
    <h2 style="margin-left: 40px;color: #f10215">¥${product.price}</h2>
    <input type="hidden" name="shopid" id="shopid" value="${product.shopid}">
    <input type="hidden" name="productID" id="productID" value="${product.id}">
</c:forEach>
    <div class="num1" style="margin-top: 60px;float: right">
        <h2 style="font-size: 20px;margin-left: 10px;float: left;margin-top: 0px">数量：</h2>
        <input type="button" value="-" onclick="reduce()">
        <input type="text" id="product_num" name="product_num" value="<%=num%>" onchange="getnum()">
        <input type="button" value="+" onclick="add()">
    </div>
</div>
<div>
<c:forEach items="${buyer}"  var="wallet">
    <h2 style="float: left">买家余额</h2>
    <h2 style="float: left;color: #f10215">${wallet.balance}</h2>
    <input type="hidden" name="balance0" id="balance0" value="${wallet.balance}">
</c:forEach>
</div>
<div>
    <c:forEach items="${seller}"  var="wallet">
        <input type="hidden" name="balance1" id="balance1" value="${wallet.balance}">
    </c:forEach>
</div>
<form action="ProductState?status=insert" method="post" onsubmit="return check()">
    <div class="box-show">
        <div class="price" style="display: flex">
            <h2 style="font-size: 15px">实付款:</h2>
            <c:forEach items="${product}" var="product" >
            <h2 style="font-size: 25px;float: left;color: #f10215;margin-top: 6px;margin-bottom: 0px">¥</h2>
                <input type="text" id="price" name="price" style="color: red"  value="${product.price*num}">
            <input type="hidden" name="price0" id="price0" value="${product.price}"  >
</c:forEach>
        </div>
        <div class="confirm-addr" style="display: flex">
            <h2 style="font-size: 8px;float: left">寄送至:</h2>
            <input type="text" name="city" id="city"  style="font-size:8px; float: left;border: none;margin-left: 10px;margin-top: 7px " >
            <input type="text" name="addr" id="addr"  style="font-size:8px;float: left;border: none;margin-left: 5px;margin-top: 7px " >
        </div>
        <div class="confirm-addr-user" style="display: flex">
            <h2 style="font-size: 8px;float: left">收货人:</h2>
            <input type="text" name="buyername" id="buyername" >
            <input type="text" name="phone" id="phone"   style="font-size:8px;float: left;border: none;margin-left: 5px;margin-top: 7px " >
        </div>
        <input type="submit"  style="float: right;border: none;background-color: #f10215;color: white;margin-top: 10px" value="提交订单">
        <input type="hidden" value="<%=a--%>" id="int">
        <input type="hidden" name="addressid" id="addressid">
        <input type="hidden" name="buyerid" id="buyerid" value="<%=session.getAttribute("uId")%>">
        <input type="hidden" name="sellerid" id="sellerid">
        <input type="hidden" name="productid1" id="productid1">
        <input type="hidden" name="shopname" id="shopname">
        <input type="hidden" name="balance_buyer" id="balance_buyer">
        <input type="hidden" name="balance_seller" id="balance_seller">
        <input type="hidden" name="num" id="num" value="<%=num%>">
        <input type="hidden" name="productname" id="productname">
        <script>
            var  x=document.getElementById("int").value;
            for(var i=0;i<x;i++){
                if(document.getElementById("check" + i).checked == true){
                    document.getElementById("city").value=document.getElementById("city"+i).value;
                    document.getElementById("addr").value=document.getElementById("addr"+i).value;
                    document.getElementById("phone").value=document.getElementById("phone"+i).value;
                    document.getElementById("addressid").value=document.getElementById("addressid"+i).value;
                    document.getElementById("sellerid").value=document.getElementById("shopid").value;
                    document.getElementById("productid1").value=document.getElementById("productID").value;
                    document.getElementById("shopname").value=document.getElementById("shopname1").value;
                    document.getElementById("buyername").value=document.getElementById("addressee"+i).value;
                    document.getElementById("balance_buyer").value=document.getElementById("balance0").value;
                    document.getElementById("balance_seller").value=document.getElementById("balance1").value;
                    document.getElementById("productname").value=document.getElementById("productname0").value;
                    document.getElementById("num").value=document.getElementById("product_num").value;
                }
            }
        </script>
    </div>
</form>
</body>
</html>
