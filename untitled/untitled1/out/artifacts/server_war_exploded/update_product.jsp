<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 27/6/2022
  Time: 上午8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改商品</title>
    <link rel="stylesheet" href="css/Publish_product.css" type="text/css">
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
        function post1(){
            document.getElementById("form").action="Product?status=update";
        }
        function post2(){
            document.getElementById("form").action="Product?status=delete";
        }
        function check_price(){

        }
        function onCheck(){
        }
    </script>
</head>
<body>
<%String shopid=request.getParameter("shopid");
String id=request.getParameter("id");
String name=request.getParameter("name");
String img=request.getParameter("img");
String size=request.getParameter("size");
String price=request.getParameter("price");
String type=request.getParameter("type");
String color=request.getParameter("color");
String num=request.getParameter("num");
String packed=request.getParameter("packed");
    request.setAttribute("type",type);
    request.setAttribute("size",size);%>
<div class="pub-wrapper">
    <div class="product-img">
        <form action="sucess.jsp" method="post" enctype="multipart/form-data" target="hidden_frame" onsubmit="onCheck()">
            <div id="img1" style="display: flex">
                <img src="./imgweb/<%=img%>" id="getImg1" style="width: 150px;height: 150px;float: left">
            </div>
            <input type="file" name="pic" id="file" onchange="getName(this)" >
            <input type="submit" value="上传" onclick="showDialog()" style="width: 100px">
        </form>
    </div>

    <div class="product-info">
        <form  method="post" id="form" onsubmit="return check_price()">
            <div class="name-wrapper" style="display: flex">
                <h2 style="font-size: 10px">商品名：</h2>
                <input type="text" name="product-name" value="<%=name%>">
            </div>
            <div class="name-wrapper" style="display: flex">
                <h2 style="font-size: 10px">颜色：</h2>
                <input type="text" name="product-color" style="margin-left: 12px" value="<%=color%>">
            </div>
            <div class="name-wrapper" style="display: flex">
                <h2 style="font-size: 10px">尺码类型：</h2>
                <select name="size" style="height: 20px;margin-left: 0px;margin-top: 5px" >
                    <option value="0" <c:if test="${requestScope.size=='0'}">selected="selected"</c:if>>请选择标签（默认无尺码）</option>
                    <option value="1" <c:if test="${requestScope.size=='1'}">selected="selected"</c:if>>衣服</option>
                    <option value="2" <c:if test="${requestScope.size=='2'}">selected="selected"</c:if>>物品</option>
                </select>
            </div>

            <div class="name-wrapper" style="display: flex">
                <h2 style="font-size: 10px">类型：</h2>
                <select name="type" style="height: 20px;margin-left: 12px" >
                    <option value="0" <c:if test="${requestScope.type=='0'}">selected="selected"</c:if>>请选择类型（默认其他）</option>
                    <option value="1" <c:if test="${requestScope.type=='1'}">selected="selected"</c:if>>女装</option>
                    <option value="2" <c:if test="${requestScope.type=='2'}">selected="selected"</c:if>>内衣</option>
                    <option value="3" <c:if test="${requestScope.type=='3'}">selected="selected"</c:if>>奢品</option>
                    <option value="4" <c:if test="${requestScope.type=='4'}">selected="selected"</c:if>>女鞋</option>
                    <option value="5" <c:if test="${requestScope.type=='5'}">selected="selected"</c:if>>男鞋</option>
                    <option value="6" <c:if test="${requestScope.type=='6'}">selected="selected"</c:if>>箱包</option>
                    <option value="7" <c:if test="${requestScope.type=='7'}">selected="selected"</c:if>>美妆</option>
                    <option value="8" <c:if test="${requestScope.type=='8'}">selected="selected"</c:if>>饰品</option>
                    <option value="9" <c:if test="${requestScope.type=='9'}">selected="selected"</c:if>>洗护</option>
                    <option value="10" <c:if test="${requestScope.type=='10'}">selected="selected"</c:if>>男装</option>
                    <option value="11" <c:if test="${requestScope.type=='11'}">selected="selected"</c:if>>运动</option>
                    <option value="12" <c:if test="${requestScope.type=='12'}">selected="selected"</c:if>>百货</option>
                    <option value="13" <c:if test="${requestScope.type=='13'}">selected="selected"</c:if>>手机</option>
                    <option value="14" <c:if test="${requestScope.type=='14'}">selected="selected"</c:if>>数码</option>
                    <option value="15" <c:if test="${requestScope.type=='15'}">selected="selected"</c:if>>企业礼品</option>
                    <option value="16" <c:if test="${requestScope.type=='16'}">selected="selected"</c:if>>家装</option>
                    <option value="17" <c:if test="${requestScope.type=='17'}">selected="selected"</c:if>>电器</option>
                    <option value="18" <c:if test="${requestScope.type=='18'}">selected="selected"</c:if>>车品</option>
                    <option value="19" <c:if test="${requestScope.type=='19'}">selected="selected"</c:if>>食品</option>
                    <option value="20" <c:if test="${requestScope.type=='20'}">selected="selected"</c:if>>生鲜</option>
                    <option value="21" <c:if test="${requestScope.type=='21'}">selected="selected"</c:if>>母婴</option>
                    <option value="22" <c:if test="${requestScope.type=='22'}">selected="selected"</c:if>>医药</option>
                    <option value="23" <c:if test="${requestScope.type=='24'}">selected="selected"</c:if>>保险</option>
                    <option value="24" <c:if test="${requestScope.type=='24'}">selected="selected"</c:if>>进口</option>
                </select>
            </div>
            <div class="name-wrapper" style="display: flex">
                <h2 style="font-size: 10px">价格：</h2>
                <input type="text" name="product-price" style="margin-left: 12px" value="<%=price%>">
            </div>
            <div class="name-wrapper" style="display: flex">
                <h2 style="font-size: 10px">库存：</h2>
                <input type="text" name="new_stock" style="margin-left: 12px" value="<%=num%>" id="new_stock">
                <input type="hidden" name="old_stock" value="<%=num%>">
            </div>
            <div class="post" style="display: flex">
                <input type="hidden" name="status" value="insert">
                <input type="hidden" name="uId" value="<%=session.getAttribute("uId")%>">
                <input type="hidden" id="imgfile" name="img" value="<%=img%>">
                <input type="hidden" name="shopid"  value="<%=shopid%>">
                <input type="hidden" name="id" value="<%=id%>">
                <input type="hidden" name="packed" value="<%=packed%>">
                <input type="submit" id="btn1" value="修改商品" style="border: none;background-color: #f10215;color: white" onclick="post1()">
                <input type="submit"  id="btn2" value="删除商品" style="border: none;background-color: #f10215;color: white;margin-left: 50px" onclick="post2()">
            </div>
        </form>
    </div>
</div>



<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>
