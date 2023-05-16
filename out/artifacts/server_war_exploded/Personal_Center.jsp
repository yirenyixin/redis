<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.jspsmart.upload.SmartUpload" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/5/25
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/personal.css" type="text/css">
    <title>个人中心</title>
    <script>
            myDate = new Date();
            var y = myDate.getFullYear();
            var m = myDate.getMonth()+1;//获取当前月份的日期
            var d = myDate.getDate();
            document.getElementById("date").innerHTML=d+"&nbsp;&nbsp;";
    </script>
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
    <script type="text/javascript">
        function img(a){
            var b='main0'+a.alt;
            var c='main2'+a.alt;
            if(a.height==100){
                a.height=300;
                a.width=300;
                document.getElementById(b).style.height='400px';
                document.getElementById(c).style.height='400px'
            }else{
                a.height=100;
                a.width=100;
                document.getElementById(b).style.height='200px';
                document.getElementById(c).style.height='200px'
            }
        }
    </script>
    <script>
        function get(a){
            var x=document.getElementById("balance0").value;
            var y=a.value;
            document.getElementById("balance").value=parseInt(x)+parseInt(y);
        }
    </script>
</head>
<body  >
<%String name=request.getParameter("name");
String id=request.getParameter("id");
String flag= String.valueOf(session.getAttribute("uflag"));%>
<%
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    java.util.Date currentTime = new java.util.Date();//得到当前系统时间
    String str_date1 = formatter.format(currentTime); //将日期时间格式化
    String str_date2 = currentTime.toString(); //将Date型日期时间转换成字符串形式
    String yy = str_date1.substring(0,4);
    String mm = str_date1.substring(5,7);
    String dd = str_date1.substring(8,10);
    Date date=new Date();

    SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");

    String xq=dateFm.format(date);
%>
<div class="person_main">
    <div class="my_select">
        <div class="my_tree">
            <h2>全部功能</h2>
        </div>
        <%if (flag.equals("0")){%>
        <div class="my_tree">
        <a href="Shopping_Cart?id=<%=session.getAttribute("uId")%>&status=selectall">我的购物车</a>
        </div>
        <%}%>
<%--        <div class="my_tree">--%>
<%--            <a href="">已买到的宝贝</a>--%>
<%--        </div>--%>
    </div>

    <div class="main_wrap">
    <form action="sucess.jsp" method="post" enctype="multipart/form-data" target="hidden_frame">

        <div id="img1" style="display: flex">
        <img src="./imgweb/<%=session.getAttribute("uimg")%>" id="getImg1" style="width: 100px;height: 100px;float: left">
            <h2 ><%=session.getAttribute("uname")%></h2>
            <%if(flag.equals("0")){%>
            <a href="address?uid=<%=session.getAttribute("uId")%>&status=selectall" style="float: left;margin-left: 100px;margin-top: 30px">我的收货地址</a>
            <%}%>
        </div>
        <input type="file" name="pic" id="file" onchange="getName(this)">
        <input type="submit" value="上传" onclick="showDialog()">
    </form>

    <form action="Login" method="post" >
        <input type="hidden" name="status" value="update">
<%--        图片名--%>
        <input type="hidden" id="imgfile" name="img" value="<%=session.getAttribute("uimg")%>">

        <input type="hidden"  name="name" value="<%=session.getAttribute("uname")%>">
<%--        <input type="hidden" name="status" value="insert">--%>
        <input type="submit" value="保存更改">
    </form>
        <div class="my_content">
<%--            <div class="my_content_select">--%>
<%--            <a href="" style="display: flex;margin-top: 10px">待付款</a>--%>
<%--            </div>--%>
    <%if(flag.equals("0")){%>
            <div class=" my_content_select">
                <a href="ProductState?id=<%=session.getAttribute("uId")%>&status=logistics&flag=<%=session.getAttribute("uflag")%>" style="display: flex;margin-top: 10px">待发货</a>
            </div>
            <div class=" my_content_select">
                <a href="ProductState?id=<%=session.getAttribute("uId")%>&status=translate" style="display: flex;margin-top: 10px">待收货</a>
            </div>
    <div class=" my_content_select">
        <a href="ProductState?id=<%=session.getAttribute("uId")%>&status=goods&flag=<%=session.getAttribute("uflag")%>" style="display: flex;margin-top: 10px">已收货</a>
    </div>
    <div class=" my_content_select">
        <a href="Reports?id=<%=session.getAttribute("uId")%>&status=report&flag=<%=session.getAttribute("uflag")%>" style="display: flex;margin-top: 10px">查看报表</a>
    </div>
    <%}else{%>
    <div class=" my_content_select">
        <a href="ProductState?id=<%=session.getAttribute("uId")%>&status=logistics&flag=<%=session.getAttribute("uflag")%>" style="display: flex;margin-top: 10px">物流管理</a>
    </div>
    <div class=" my_content_select">
        <a href="Reports?id=<%=session.getAttribute("uId")%>&status=report&flag=<%=session.getAttribute("uflag")%>" style="display: flex;margin-top: 10px">查看报表</a>
    </div>
    <%}%>
<%--            <div class=" my_content_select">--%>
<%--                <a href="" style="display: flex;float: left;margin-top: 10px">待评价</a>--%>
<%--                <h2 style="color: #f10215;float: left;margin-top: 5px" >0</h2>--%>
<%--            </div>--%>
<%--            <div class=" my_content_select">--%>
<%--                <a href="" style="display: flex;margin-top: 10px">退款</a>--%>
<%--            </div>--%>
        </div>
    </div>
    <div class="main_wrap1">
        <div class="box">
            <div class="box_title" style="margin-top: 0px"><h2 style="margin-left: 30%;margin-top: 0px;font-size: 30px;color: white">我的日历</h2></div>
            <div class="box_time">
                <h2 id="date" style="font-size: 30px;display: flex;margin-top: 30px;margin-left: 40%;color: aquamarine"><%=dd%></h2>
                <h2 id="xq" style="display: flex;font-size: 20px;margin-top: 15px;color: aquamarine;margin-left: 39%;margin-bottom: 0px"><%=xq%></h2>
                <h2 id="year" style="display: flex;font-size: 20px;color: aquamarine;margin-left: 37%;margin-top: 0px"><%=yy+"-"+mm%></h2>
            </div>
        </div>
        <form action="Wallet?id=<%=session.getAttribute("uId")%>&status=update" method="post">
        <div>
            <c:forEach items="${all1}" var="wallet" >
                <h2 style="">余额:</h2>
            <h2 style="color: #f10215">${wallet.balance}</h2>
            <input type="hidden" name="balance0" id="balance0" value="${wallet.balance}">
            </c:forEach>
            <%if(flag.equals("0")){%>
            <h2>请输入充值金额：</h2>
            <input type="text" id="balance1" name="balance1" onchange="get(this)">
<%--            <a href="Wallet?id=<%=session.getAttribute("uId")%>"&stauts="update">充值</a>--%>
            <input type="hidden" id="balance" name="balance">
            <input type="submit" value="充值">
            <%}%>
        </div>
        </form>
    </div>
</div>

<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>
