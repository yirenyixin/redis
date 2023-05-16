<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/16
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="css/Shopping_Cart.css" type="text/css">
    <script>

        function get(c){
            var a=c.value;
            document.getElementById("city").value=document.getElementById("city"+a).value;
            document.getElementById("addr").value=document.getElementById("addr"+a).value;
            document.getElementById("phone").value=document.getElementById("phone"+a).value;
            document.getElementById("addressid").value=document.getElementById("addressid"+a).value;
            document.getElementById("buyername").value=document.getElementById("addressee"+a).value;
        }
        function reduce(a){
            var x=a.alt;
            var n="";
            var m="";
            var index=0;
            for(var i=0;i<x.length;i++){
                if(x.charAt(i)==="-"){
                    index=1;
                }
                if (index===0&&x.charAt(i)!=="-")m+=x.charAt(i);
                else if (index===1&&x.charAt(i)!=="-") n+=x.charAt(i);
            }
            x=document.getElementById("head_list"+m+"_num_"+n).value;
            var y=x;
            if(x==1){
                alert("不能再少了！！！")
            }else{
                y=parseInt(x)-1;
                document.getElementById("head_list"+m+"_num_"+n).value=y;
                var price=document.getElementById("head_list"+m+"_price_"+n+"_static").value;
                price=parseInt(price)*parseInt(y)
                document.getElementById("head_list"+m+"_price_"+n).value=price;
                document.getElementById("head_list"+m+"_num_"+n).value=y;
                document.getElementById("head_list"+m+"_price_"+n).value=price;
                all_price();
            }

        }
        function add(a){
            var x=a.alt;
            var n="";
            var m="";
            var index=0;
            for(var i=0;i<x.length;i++){
                if(x.charAt(i)==="-"){
                    index=1;
                }
                if (index===0&&x.charAt(i)!=="-")m+=x.charAt(i);
                else if (index===1&&x.charAt(i)!=="-") n+=x.charAt(i);
            }
            x=document.getElementById("head_list"+m+"_num_"+n).value;
            a=document.getElementById("head_list"+m+"_stock_"+n).value;
            if(parseInt(x)>=parseInt(a)){
                alert("不能再多了！！！");
            }else {
                var y = x;
                y = parseInt(y) + 1;
                var price = document.getElementById("head_list" + m + "_price_" + n + "_static").value;
                price = parseInt(price) * parseInt(y)
                document.getElementById("head_list" + m + "_price_" + n).value = price;
                document.getElementById("head_list" + m + "_num_" + n).value = y;
                all_price();
            }
        }
        function check_box(a){
            var x=a.alt;
            // alert(x);
            var n="";
            var m="";
            var index=0;
            var i;
            for( i=0;i<x.length;i++){
                if(x.charAt(i)==="-"){
                    index=1;
                }
                if (index===0&&x.charAt(i)!=="-")m+=x.charAt(i);
                else if (index===1&&x.charAt(i)!=="-") n+=x.charAt(i);
            }
            var i=document.getElementById("head_list"+m+"_num_"+n).value;
            var j=document.getElementById("head_list"+m+"_stock_"+n).value;
            if(parseInt(i)>parseInt(j)){
                alert("有商品已缺货，请检查！！");
                document.getElementById("head_list" + m + "_check_" + n).checked = false;
            }else {
                x = document.getElementById("head_list" + m + "_check_" + n).value;
                if (x == 1) {
                    // list0_content0
                    document.getElementById("list" + m + "_content" + n).style.background = "#fff9f6";
                    document.getElementById("head_list" + m + "_check_" + n).value = 0;
                    document.getElementById("head_list" + m + "_check_" + n + "_value").value = 0;
                } else if (x == 0) {
                    document.getElementById("list" + m + "_content" + n).style.background = "#d9d9d9";
                    document.getElementById("head_list" + m + "_check_" + n).value = 1;
                    document.getElementById("head_list" + m + "_check_" + n + "_value").value = 1;
                }
                //检查该店铺是否全选

                x = document.getElementById("check_list" + m).alt;
                index = 0;
                m = "";
                n = "";
                for (i = 0; i < x.length; i++) {
                    if (x.charAt(i) === "-") {
                        index = 1;
                    }
                    if (index === 0 && x.charAt(i) !== "-") m += x.charAt(i);
                    else if (index === 1 && x.charAt(i) !== "-") n += x.charAt(i);
                }
                // alert(m+"a"+n);
                var flag = 1;
                for (var i = 0; i < n; i++) {
                    // document.getElementById("head_list"+m+"_check_"+i).checked;
                    if (document.getElementById("head_list" + m + "_check_" + i).checked === false) {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    document.getElementById("check_list" + m).checked = true;
                    document.getElementById("check_list" + m).value = 0;
                    check_allbox1();
                } else {
                    document.getElementById("check_list" + m).checked = false;
                    document.getElementById("check_list" + m).value = 1;
                    check_allbox1();
                }
                all_price();
            }
        }
        function check_allbox1(){
            m=document.getElementById("all").alt;
            index=1;
            for(i=0;i<m;i++){
                if(document.getElementById("check_list"+i).checked==false){
                    index=0;
                }
            }
            if(index===1){
                document.getElementById("all").value=0;
                document.getElementById("all").checked=true;
            }
            else {
                document.getElementById("all").value=1;
                document.getElementById("all").checked=false;
            }
            all_price();
        }
        function check_allbox(a){//商店全选框
            var x=a.alt;
            var n="";
            var m="";
            var index=0;
            for(var i=0;i<x.length;i++){
                if(x.charAt(i)==="-"){
                    index=1;
                }
                if (index===0&&x.charAt(i)!=="-")m+=x.charAt(i);
                else if (index===1&&x.charAt(i)!=="-") n+=x.charAt(i);
            }
            x=document.getElementById("check_list"+m).value;
            if(x==1){
                for(var i=0;i<n;i++){
                    var q=document.getElementById("head_list"+m+"_num_"+i).value;
                    var w=document.getElementById("head_list"+m+"_stock_"+i).value;
                    if(parseInt(q)>parseInt(w)){
                        alert("有商品已缺货，请检查！！");
                        document.getElementById("head_list" + m + "_check_" + i).checked = false;
                        document.getElementById("check_list"+m).checked=false;
                        return false;
                    }
                    document.getElementById("list"+m+"_content"+i).style.background="#fff9f6";
                    document.getElementById("head_list"+m+"_check_"+i).value=0;
                    document.getElementById("head_list"+m+"_check_"+i+"_value").value=0;
                    document.getElementById("head_list"+m+"_check_"+i).checked=true;
                    document.getElementById("check_list"+m).value=0;
                }
            }
            else{
                for(var i=0;i<n;i++){
                    var q=document.getElementById("head_list"+m+"_num_"+i).value;
                    var w=document.getElementById("head_list"+m+"_stock_"+i).value;
                    if(parseInt(q)>parseInt(w)){
                        alert("有商品已缺货，请检查！！");
                        document.getElementById("head_list" + m + "_check_" + i).checked = false;
                        document.getElementById("check_list"+m).checked=false;
                        return false;
                    }
                    document.getElementById("list"+m+"_content"+i).style.background="#d9d9d9";
                    document.getElementById("head_list"+m+"_check_"+i).value=1;
                    document.getElementById("head_list"+m+"_check_"+i+"_value").value=1;
                    document.getElementById("head_list"+m+"_check_"+i).checked=false;
                    document.getElementById("check_list"+m).value=1;
                }
            }
            m=document.getElementById("all").alt;
            index=1;
            for(i=0;i<m;i++){
                if(document.getElementById("check_list"+i).checked==false){
                    index=0;
                }
            }
            if(index===1){
                document.getElementById("all").value=0;
                document.getElementById("all").checked=true;
            }
            else {
                document.getElementById("all").value=1;
                document.getElementById("all").checked=false;
            }
            all_price();
        }
        function all_price(){
            var a=document.getElementById("all_list").value;
            var i=0;
            document.getElementById("all_price").value=0;
            var price0=0;
            for(i=0;i<a;i++){
                var m="";
                var n="";
                var index=0;
                var x=document.getElementById("check_list"+i).alt;
                for(var k=0;k<x.length;k++) {
                    if (x.charAt(k) === "-") {
                        index = 1;
                        // alert("这是index："+index);
                    }
                    if (index === 0 && x.charAt(k) !== "-") m+= x.charAt(k);
                    else if (index === 1 && x.charAt(k) !== "-") n += x.charAt(k);
                }
                // alert(m+"a"+n);
                for(var j=0;j<parseInt(n);j++){
                    if(document.getElementById("head_list"+m+"_check_"+j).checked===true) {
                        // alert("进入循环");
                        var num = document.getElementById("head_list" + m + "_num_" + j).value;
                        var price=document.getElementById("head_list"+m+"_price_"+j+"_static").value;
                        // alert("这是价格"+price);
                        price=parseInt(price)*parseInt(num);
                        price0=parseInt(price0)+parseInt(price);
                    }
                }
            }
            document.getElementById("all_price").value=price0;
        }
        function check() {
            var a = document.getElementById("balance_buyer").value;
            var b = document.getElementById("all_price").value;
            if (parseInt(a) >= parseInt(b)) return true;
            else {
                alert("余额不足，请充值！！！")
                return false;
            }

        }
        function all_check(a){//全选框
            var x=a.value;
            if(x==1){

                var y=a.alt;
                for(var i=0;i<parseInt(y);i++){
                    // alert(i);
                    document.getElementById("check_list"+i).checked=true;
                    document.getElementById("check_list"+i).value=1;
                    check_allbox(document.getElementById("check_list"+i));
                }
            }
            else {
                var y=a.alt;
                for(var i=0;i<y;i++){
                    document.getElementById("check_list"+i).checked=false;
                    document.getElementById("check_list"+i).value=0;
                    check_allbox(document.getElementById("check_list"+i));
                }
            }
        }
    </script>
</head>
<body bgcolor="#a9a9a9">
<div style="height: 50px">
    <c:forEach items="${buyer}"  var="wallet">
        <h2 style="float: left">买家余额</h2>
        <h2 style="float: left;color: #f10215">${wallet.balance}</h2>
        <input type="hidden" name="balance0" id="balance0" value="${wallet.balance}">
    </c:forEach>
</div>
<div class="header-wrapper">
    <h2 style="font-size: 15px;color: #f10215">选择收货地址</h2>
</div>
<div class="address-list" style=" margin-left: 10%">
    <%int q=0;%>
    <c:forEach items="${address}" var="address" >
        <div class="addr-item-wrapper" >

            <div style="display: flex;margin-top: 2px;margin-bottom: 2px;width: 100%;height: 96px;flex-wrap: wrap" >
                <input type="hidden" name="city<%=q%>" id="city<%=q%>" value="${address.city}">
                <input type="hidden" name="addr<%=q%>"  id="addr<%=q%>" value="${address.addr}">
                <input type="hidden" name="phone<%=q%>" id="phone<%=q%>" value="${address.phone}">
                <input type="hidden" name="flag<%=q%>" id="flag<%=q%>" value="${address.flag}">
                <input type="hidden" name="addressid<%=q%>" id="addressid<%=q%>" value="${address.id}">
                <input type="hidden" name="addressee<%=q%>" id="addressee<%=q%>" value="${address.addressee}">
                <h2  style="display: flex;font-size: 8px;margin-top: 0px;margin-bottom: 0px;width: 100%;height: 25px" >${address.city}（${address.addressee}）</h2>
                <h2 style="display: flex;font-size: 5px;margin-top: 0px;margin-bottom: 0px;width: 100%;height: 25px">${address.addr}</h2>
                <h2 style="display: flex;font-size: 5px;margin-top: 0px;margin-bottom: 0px;width: 100%;height: 25px">${address.phone}</h2>
                <input type="radio" name="check" id="check<%=q%>" value="<%=q%>" onclick="get(this)" >
                <script>
                    var x=document.getElementById("flag<%=q%>").value;
                    if(x==1){
                        document.getElementById("check<%=q%>").checked=true;
                    }
                </script>
            </div>
        </div>
        <%q++;%>
    </c:forEach>
    <input type="hidden" name="q" id="q" value="<%=q%>">
</div>
<a href="Receiving_address.jsp" style="display: flex">使用新地址</a>


<form action="ProductState?status=insertList" method="post" onsubmit="return check()">
    <div class="J_car" >
        <div class="J_FileBar">
            <h2>购物车（全部）</h2>
        </div>
        <hr>
        <div style="display: flex">
            <div style="float: left;">
                <input type="checkbox" name="all" id="all"  style="float: left" onclick="all_check(this)" value="1">
                <h2 style="float: left;font-size: 10px;margin-top: 0">全选</h2>
                <h2 style="float: left;font-size: 10px;margin-top: 0;padding-left: 117px">商品信息</h2>
            </div>
            <div style="float: left;padding-right:42px;width: 172px;height: 50px "></div>
            <div style="float: left;width: 120px;height: 50px">
                <h2 style="float: left;font-size: 10px;margin-top: 0">单价</h2>
            </div>
            <div style="float: left;padding-left: 10px;height: 50px;width: 94px">
                <h2 style="float: left;font-size: 10px;margin-top: 0">数量</h2>
            </div>
            <div style="float: left;height: 50px;width: 105px">
                <h2 style="float: left;font-size: 10px;margin-top: 0">金额</h2>
            </div>
            <div style="float: left;height: 50px;width: 27px">
                <h2 style="float: left;font-size: 10px;margin-top: 0">操作</h2>
            </div>
            <h2 style="float: left;font-size: 10px;margin-top: 0;margin-left: 50px">库存</h2>
        </div>
        <div class="J_list" style="display: flex;flex-direction: column-reverse;">
                <%int a=0;
        int b=0;
            int c;%>
            <c:forEach items="${all}" var="shopping_cart" varStatus="flag" >
            <c:choose>
                <%--            数组不wei为第一个值--%>
            <c:when test="${all[flag.index-1].shopid!=all[flag.index].shopid&&flag.index!=0}" >
            <div id="list<%=a%>" style="display: flex;flex-direction: column-reverse;">
                <div id="list<%=a%>_list<%=b%>" style="flex-direction: column-reverse;display: flex;">

                    <c:forEach items="${all}" var="shopping_cart1" >

                        <c:if test="${shopping_cart1.shopid==shopping_cart.shopid}" >
                            <div class="content" id="list<%=a%>_content<%=b%>" style="width: 100%;background-color: #d9d9d9;">
                                <div class="bundle_main0" style="height: 130px">
                                    <div style="width: 45px;height: 82px;float: left">
                                        <input type="checkbox" name="head_list<%=a%>_check_<%=b%>" id="head_list<%=a%>_check_<%=b%>"  style="float: left;" alt="<%=a%>-<%=b%>" value="1" onchange="check_box(this)">
                                        <input type="hidden" name="head_list<%=a%>_check_<%=b%>_value" id="head_list<%=a%>_check_<%=b%>_value" value="1">
                                        <input type="hidden" name="list<%=a%>_id_<%=b%>" value="${shopping_cart1.id}">
                                        <input type="hidden" name="list<%=a%>_productid_<%=b%>" value="${shopping_cart1.productid}">
                                    </div>
                                    <div style="width: 302px;height: 110px;float: left">
                                        <img src="./imgweb/${shopping_cart1.img}" style="height: 80px;width: 80px;float: left">
                                        <h2 style="float: left;font-size: 10px;margin-top: 0">${shopping_cart1.productname}</h2>
                                        <input type="hidden" name="list<%=a%>_productname_<%=b%>" value="${shopping_cart1.productname}">
                                    </div>
                                    <div style="width: 174px;height: 84px;float: left"></div>
                                    <div class="price" style="float: left;width: 120px;height: 119px">
                                        <h2 style="float: left;font-size: 10px;margin-top: 0">￥</h2>
                                        <input type="text" id="head_list<%=a%>_price_<%=b%>" id="head_list<%=a%>_price_<%=b%>" value="${shopping_cart1.price}" style="float: left;margin-top: 0;color: #f10215;width: 60px;font-size: 20px">
                                        <input type="hidden" id="head_list<%=a%>_price_<%=b%>_static" name="head_list<%=a%>_price_<%=b%>_static" value="${shopping_cart1.price}" >
                                    </div>
                                    <div class="num" style="float: left;width: 104px;height: 119px">
                                        <input type="button" value="-" onclick="reduce(this)" style="float: left" alt="<%=a%>-<%=b%>">
                                        <input type="text" value="${shopping_cart1.num}" name="head_list<%=a%>_num_<%=b%>" id="head_list<%=a%>_num_<%=b%>" style="width: 50px;float: left">
                                        <input type="button" value="+" onclick="add(this)" style="float: left" alt="<%=a%>-<%=b%>">
                                    </div>
                                    <div class="delete" style="float: left;width: 64px;height: 119px">
                                        <a href="Shopping_Cart?id=${shopping_cart1.id}&status=delete" style="float: left;font-size: 10px;margin-top: 0">删除</a>
                                    </div>
                                    <input type="text" id="head_list<%=a%>_stock_<%=b%>" id="head_list<%=a%>_stock_<%=b%>" value="${shopping_cart1.stock}" style="float: left;margin-top: 0;color: #f10215;width: 60px;font-size: 20px">
                                </div>
                            </div>
                            <% b=b+1;%>
                        </c:if>
                    </c:forEach>
                    <%c=b+1;%>
                    <div class="head_list<%=a%>" style="display: flex">
                        <input type="checkbox" name="check_list<%=a%>" id="check_list<%=a%>"  style="float: left" alt="<%=a%>-<%=b%>" onchange="check_allbox(this)" value="1">
                        <h2 style="float: left;font-size: 10px;margin-top: 0">${shopping_cart.shopname}</h2>
                        <input type="hidden" name="head_list<%=a%>_shopname" value="${shopping_cart.shopname}">
                        <input type="hidden" name="head_list<%=a%>_shopid" value="${shopping_cart.shopid}">
                        <input type="hidden" name="list<%=a%>_b" value="<%=b%>" >
                    </div>
                    <%b=0;%>
                </div>
                <%a=a+1;%>
                </c:when>
                    <%--            数组为第一个值--%>
                <c:when test="${flag.index==0}">

                    <div id="list<%=a%>" style="display: flex;flex-direction: column-reverse;">
                        <div id="list<%=a%>_list<%=b%>" style="flex-direction: column-reverse;display: flex;">

                            <c:forEach items="${all}" var="shopping_cart1" >

                                <c:if test="${shopping_cart1.shopid==shopping_cart.shopid}" >
                                    <div class="content" id="list<%=a%>_content<%=b%>" style="width: 100%;background-color: #d9d9d9;">
                                        <div class="bundle_main0" style="height: 130px">
                                            <div style="width: 45px;height: 82px;float: left">
                                                <input type="checkbox" name="head_list<%=a%>_check_<%=b%>" id="head_list<%=a%>_check_<%=b%>"  style="float: left;" alt="<%=a%>-<%=b%>" value="1" onchange="check_box(this)">
                                                <input type="hidden" name="head_list<%=a%>_check_<%=b%>_value" id="head_list<%=a%>_check_<%=b%>_value" value="1">
                                                <input type="hidden" name="list<%=a%>_id_<%=b%>" value="${shopping_cart1.id}">
                                                <input type="hidden" name="list<%=a%>_productid_<%=b%>" value="${shopping_cart1.productid}">
                                            </div>
                                            <div style="width: 302px;height: 110px;float: left">
                                                <img src="./imgweb/${shopping_cart1.img}" style="height: 80px;width: 80px;float: left">
                                                <h2 style="float: left;font-size: 10px;margin-top: 0">${shopping_cart1.productname}</h2>
                                                <input type="hidden" name="list<%=a%>_productname_<%=b%>" value="${shopping_cart1.productname}">
                                            </div>
                                            <div style="width: 174px;height: 84px;float: left"></div>
                                            <div class="price" style="float: left;width: 120px;height: 119px">
                                                <h2 style="float: left;font-size: 10px;margin-top: 0">￥</h2>
                                                <input type="text" id="head_list<%=a%>_price_<%=b%>" id="head_list<%=a%>_price_<%=b%>" value="${shopping_cart1.price}" style="float: left;margin-top: 0;color: #f10215;width: 60px;font-size: 20px">
                                                <input type="hidden" id="head_list<%=a%>_price_<%=b%>_static" name="head_list<%=a%>_price_<%=b%>_static" value="${shopping_cart1.price}" >
                                            </div>
                                            <div class="num" style="float: left;width: 104px;height: 119px">
                                                <input type="button" value="-" onclick="reduce(this)" style="float: left" alt="<%=a%>-<%=b%>">
                                                <input type="text" value="${shopping_cart1.num}" name="head_list<%=a%>_num_<%=b%>" id="head_list<%=a%>_num_<%=b%>" style="width: 50px;float: left">
                                                <input type="button" value="+" onclick="add(this)" style="float: left" alt="<%=a%>-<%=b%>">
                                            </div>
                                            <div class="delete" style="float: left;width: 64px;height: 119px">
                                                <a href="Shopping_Cart?id=${shopping_cart1.id}&status=delete" style="float: left;font-size: 10px;margin-top: 0">删除</a>
                                            </div>
                                            <input type="text" id="head_list<%=a%>_stock_<%=b%>" id="head_list<%=a%>_stock_<%=b%>" value="${shopping_cart1.stock}" style="float: left;margin-top: 0;color: #f10215;width: 60px;font-size: 20px">
                                        </div>
                                    </div>

                                    <% b=b+1;%>
                                </c:if>
                            </c:forEach>
                            <%c=b+1;%>
                            <div class="head_list<%=a%>" style="display: flex">
                                <%c=b+1;%>
                                <input type="checkbox" name="check_list<%=a%>" id="check_list<%=a%>"  style="float: left" alt="<%=a%>-<%=b%>" onchange="check_allbox(this)" value="1">
                                <h2 style="float: left;font-size: 10px;margin-top: 0">${shopping_cart.shopname}</h2>
                                <input type="hidden" name="head_list<%=a%>_shopname" value="${shopping_cart.shopname}">
                                <input type="hidden" name="head_list<%=a%>_shopid" value="${shopping_cart.shopid}">
                                <input type="hidden" name="list<%=a%>_b" value="<%=b%>" >
                            </div>
                            <%b=0;%>
                        </div>
                    </div>
                    <%a=a+1;%>
                </c:when>
                </c:choose>

                </c:forEach>
                <input type="hidden" id="a" name="a" value="<%=a%>">

                <script>
                    document.getElementById("all").alt=document.getElementById("a").value;
                </script>

                <div class="float-bar-wrapper fixed-bottom" style="width:100%;position: fixed;bottom: -1px;z-index: 9999;background-color: #fff;border-top: 1px solid #e6e6e6;">
                    <div style="width: 60%;float: left;height: 60px;float: left">

                    </div>

                    <h2 style="float: left">合计（不含运费）：</h2>
                    <input type="hidden" id="all_list" value="<%=a%>">
                    <input type="text" name="all_price" id="all_price" style="color: #f10215;float: left;border: 0px white;font-size: 40px;margin-top: 10px;width: 180px" value="0">
                    <input type="submit"  value="结算" style="float: left;background-color: #666666;color: #d9d9d9;margin-top: 20px;width: 35px;height: 35px">

                    <input type="hidden" value="<%=q--%>" id="int">
                    <input type="hidden" name="addressid" id="addressid">
                    <input type="hidden" name="buyerid" id="buyerid" value="<%=session.getAttribute("uId")%>">
                    <input type="hidden" name="sellerid" id="sellerid">
                    <input type="hidden" name="productid1" id="productid1">
                    <input type="hidden" name="shopname" id="shopname">
                    <input type="hidden" name="balance_buyer" id="balance_buyer">
                    <input type="hidden" name="balance_seller" id="balance_seller">
                    <input type="hidden" name="num" id="num" value="1">
                    <input type="hidden" name="productname" id="productname">

                    <input type="hidden" name="city" id="city"  style="font-size:8px; float: left;border: none;margin-left: 10px;margin-top: 7px " >
                    <input type="hidden" name="addr" id="addr"  style="font-size:8px;float: left;border: none;margin-left: 5px;margin-top: 7px " >

                    <input type="hidden" name="buyername" id="buyername" >
                    <input type="hidden" name="phone" id="phone"   style="font-size:8px;float: left;border: none;margin-left: 5px;margin-top: 7px " >

                    <div>

                    </div>
                    <script>
                        var  x=document.getElementById("int").value;
                        for(var i=0;i<x;i++){
                            if(document.getElementById("check" + i).checked == true){
                                document.getElementById("city").value=document.getElementById("city"+i).value;
                                document.getElementById("addr").value=document.getElementById("addr"+i).value;
                                document.getElementById("phone").value=document.getElementById("phone"+i).value;
                                document.getElementById("addressid").value=document.getElementById("addressid"+i).value;
                                // document.getElementById("sellerid").value=document.getElementById("shopid").value;
                                // document.getElementById("productid1").value=document.getElementById("productID").value;
                                // document.getElementById("shopname").value=document.getElementById("shopname1").value;
                                document.getElementById("buyername").value=document.getElementById("addressee"+i).value;
                                document.getElementById("balance_buyer").value=document.getElementById("balance0").value;
                                // document.getElementById("balance_seller").value=document.getElementById("balance1").value;
                                // document.getElementById("productname").value=document.getElementById("productname0").value;
                            }
                        }
                    </script>
                </div>
            </div>
</form>
</body>
</html>
