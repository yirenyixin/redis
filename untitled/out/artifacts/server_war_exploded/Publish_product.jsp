<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/17
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布商品</title>
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
    </script>
</head>
<body>

<div class="pub-wrapper">
    <div class="product-img">
    <form action="sucess.jsp" method="post" enctype="multipart/form-data" target="hidden_frame">
        <div id="img1" style="display: flex">
            <img  id="getImg1" style="width: 150px;height: 150px;float: left">
        </div>
        <input type="file" name="pic" id="file" onchange="getName(this)" >
        <input type="submit" value="上传" onclick="showDialog()" style="width: 100px">
    </form>
    </div>

    <div class="product-info">
    <form action="Product" method="post">
        <div class="name-wrapper" style="display: flex">
            <h2 style="font-size: 10px">商品名：</h2>
        <input type="text" name="product-name">
        </div>
        <div class="name-wrapper" style="display: flex">
            <h2 style="font-size: 10px">颜色：</h2>
            <input type="text" name="product-color" style="margin-left: 12px">
        </div>
        <div class="name-wrapper" style="display: flex">
            <h2 style="font-size: 10px">尺码类型：</h2>
            <select name="size" style="height: 20px;margin-left: 0px;margin-top: 5px" >
                <option value="0">请选择标签（默认无尺码）</option>
                <option value="1" >衣服</option>
                <option value="2" >物品</option>
            </select>
        </div>
        <div class="name-wrapper" style="display: flex">
            <h2 style="font-size: 10px">类型：</h2>
            <select name="type" style="height: 20px;margin-left: 12px" >
                <option value="0">请选择类型（默认其他）</option>
                <option value="1" >女装</option>
                <option value="2" >内衣</option>
                <option value="3" >奢品</option>
                <option value="4" >女鞋</option>
                <option value="5" >男鞋</option>
                <option value="6" >箱包</option>
                <option value="7" >美妆</option>
                <option value="8" >饰品</option>
                <option value="9" >洗护</option>
                <option value="10" >男装</option>
                <option value="11" >运动</option>
                <option value="12" >百货</option>
                <option value="13" >手机</option>
                <option value="14" >数码</option>
                <option value="15" >企业礼品</option>
                <option value="16" >家装</option>
                <option value="17" >电器</option>
                <option value="18" >车品</option>
                <option value="19" >食品</option>
                <option value="20" >生鲜</option>
                <option value="21" >母婴</option>
                <option value="22" >医药</option>
                <option value="23" >保险</option>
                <option value="24" >进口</option>
            </select>
        </div>
        <div class="name-wrapper" style="display: flex">
            <h2 style="font-size: 10px">价格：</h2>
            <input type="text" name="product-price" style="margin-left: 12px">
        </div>
        <div class="name-wrapper" style="display: flex">
            <h2 style="font-size: 10px">库存：</h2>
            <input type="text" name="product-stock" style="margin-left: 12px">
        </div>
        <div class="post" style="display: flex">
            <input type="hidden" name="status" value="insert">
            <input type="hidden" name="uId" value="<%=session.getAttribute("uId")%>">
            <input type="hidden" id="imgfile" name="img">
            <input type="submit" value="发布商品" style="border: none;background-color: #f10215;color: white">
        </div>
    </form>
    </div>
</div>



<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>
