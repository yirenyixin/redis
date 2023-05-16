<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 25/6/2022
  Time: 下午6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报表</title>
    <script src="https://gw.alipayobjects.com/os/lib/antv/g2/3.4.10/dist/g2.min.js">
    </script>
</head>
<body>
<%String flag= String.valueOf(session.getAttribute("uflag"));%>
<input type="hidden" id="flag" value="<%=flag%>">
<script>
    var data=[];
</script>
<%if(flag.equals("0")){%>
<table  style="display: none" border="1" cellspacing="0" cellpadding="0" >
    <tr>

    </tr>
    <tr>
        <th>店铺名</th>
        <th>花费</th>
    </tr>
    <c:forEach items="${all}" var="reports" >
    <tr>
        <th>${reports.shopname}</th>
        <th>${reports.cost}</th>
    </tr>
        <script>
            var a={};
            a.shopname="${reports.shopname}";
            a.cost=parseInt(${reports.cost});
            data.push(a);
        </script>
</c:forEach>
</table>
<%}else{%>
<table style="display: none">
    <tr>
    </tr>
    <tr>
        <th>商品名</th>
        <th>销售数量</th>
        <th>销售额</th>
    </tr>
    <c:forEach items="${all}" var="reports" >
    <tr>
        <th>${reports.productname}</th>
        <th>${reports.num}</th>
        <th>${reports.profit}</th>
    </tr>
        <script>
            var a={};
            a.productname="${reports.productname}";
            a.num=parseInt(${reports.num});
            a.profit=parseInt(${reports.profit});
            data.push(a);
        </script>
    </c:forEach>
</table>
<%}%>

<div id="appearance"></div>
<script>
    var a=document.getElementById("flag").value;
    if (a==='0') {
        var chart = new G2.Chart({
            container: 'appearance',
            forceFit: true,
            height: window.innerHeight,
            label: {
                // 可手动配置 label 数据标签位置
                position: 'middle', // 'top', 'middle', 'bottom'
                // 可配置附加的布局方法
                layout: [
                    // 柱形图数据标签位置自动调整
                    { type: 'interval-adjust-position' },
                    // 数据标签防遮挡
                    { type: 'interval-hide-overlap' },
                    // 数据标签文颜色自动调整
                    { type: 'adjust-color' },
                ],
            },
        });
        chart.source(data);
        chart.scale('cost', {
            tickInterval: 20  // 用于指定坐标轴各个标度点的距离
        });
        chart.interval().position('shopname*cost');
        chart.render();
    }
    else{
        var chart = new G2.Chart({
            container: 'appearance',
            forceFit: true,
            height: window.innerHeight
        });
        chart.source(data);
        chart.scale('num', {
            tickInterval: 20  // 用于指定坐标轴各个标度点的距离
        });
        chart.interval().position('productname*num').color('yellow');
        chart.render();

        var chart1 = new G2.Chart({
            container: 'appearance',
            forceFit: true,
            height: window.innerHeight
        });
        chart1.source(data);
        chart1.scale('profit', {
            tickInterval: 20  // 用于指定坐标轴各个标度点的距离
        });
        chart.interval().position('productname*profit').color('red');
        chart.render();
    }
</script>

</body>
</html>
