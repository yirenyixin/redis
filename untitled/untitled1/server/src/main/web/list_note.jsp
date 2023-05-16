<%@ page import="java.util.*"%>
<%@ page import="com.jspsmart.upload.SmartUpload" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<title>主页 </title>
	<link rel="stylesheet" href="css/list_note1.css" type="text/css">
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
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8") ;
		String keyword = request.getParameter("keyword") ;
	%>
	<%String uname= (String) session.getAttribute("uname");
	String flag= String.valueOf(session.getAttribute("uflag"));
	int pagesize=0;
	String a= String.valueOf(session.getAttribute("pagesize"));
	int len=0;
	if(!a.equals("null")) {
		pagesize = Integer.parseInt(a);
	}
		int pagestart = 0;
		int end = 0;

	if(pagesize==0) {
		 pagestart = 0;
		 end=4;
	}else {
		String m=String.valueOf(session.getAttribute("pagestart"));
		 pagestart = Integer.parseInt(m);
		 String n=String.valueOf(session.getAttribute("end"));
		 end= Integer.parseInt(n);
	}
		%>
<%--	<%=flag+"   "+uId%>--%>
	<div class="top">
		<%if(uname==null){%>
		<div class="head_portrait"> <input type="submit" value="" class="img_head" ST></div>
		<a href="login.jsp" class="a">登录</a>
		<%}else{%>
		<div class="head_portrait"> <img src="./imgweb/<%=session.getAttribute("uimg")%>" style="height: 35px;width: 35px"></div>
		<div class="head_list">
			<div class="list">
				<a href="Product?name=<%=session.getAttribute("uname")%>&id=<%=session.getAttribute("uId")%>&status=my" class="my">个人中心</a>
				<div class="list1" >
					<a href="login.jsp" class="sing_out" >退出登录</a>
				</div>
			</div>
		</div>
		<%}%>
	</div>
	<hr />	<br />
	<div class="searchbar" style="width: 900px;margin-bottom: 40px">
		<img src="https://gw.alicdn.com/tfs/TB176rg4VP7gK0jSZFjXXc5aXXa-286-118.png" style="float: left;height: 58px;width: 142px;margin-right: 30px">
    <form action="Product" method="post">
		<div class="searchbar_text"><h2 class="t">请输入查询内容： </h2></div>
		<div class="search_box"> <input  class="box" type="text" name="keyword"></div>
	   <input type="hidden" name="status" id="status" value="selectbylike">
		<div class="search">
			<input type="submit" value="搜索" style="background: #FF5000;color: #fff;border: none;">
		</div>
    </form>
	</div>
	<hr />	<br />
	<div class="clearfix">
	    <div class="classification" style="float: left">
		<h2 style="left: 0;font-size: 9px">分类</h2>
		<div class="classification1">
			<ul>
				<li>
				<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类1.png">
				</li>
				<li>
		<a href="Product?status=selectall&flag=1">
			<p>女装</p>
		</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
		<a href="Product?status=selectall&flag=2">
			<p>内衣</p>
		</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
		<a href="Product?status=selectall&flag=3">
			<p>奢品</p>
		</a>
				</li>
			</ul>
		</div>

		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类2.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=4">
						<p>女鞋</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=5">
						<p>男鞋</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=6">
						<p>箱包</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类3.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=7">
						<p>美妆</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=8">
						<p>饰品</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=9">
						<p>洗护</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类4.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=10">
						<p>男装</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=11">
						<p>运动</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=12">
						<p>百货</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类5.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=13">
						<p>手机</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=14">
						<p>数码</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=15">
						<p>企业礼品</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类6.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=16">
						<p>家装</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=17">
						<p>电器</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=18">
						<p>车品</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类7.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=19">
						<p>食品</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=20">
						<p>生鲜</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=21">
						<p>母婴</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="classification1">
			<ul>
				<li>
					<img style="height: 15px;width: 15px;margin-top: 15px;margin-left: 5px" src="./img/分类7.png">
				</li>
				<li>
					<a href="Product?status=selectall&flag=22">
						<p>医药</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=23">
						<p>保健</p>
					</a>
				</li>
				<li style="margin-top: 14px">/</li>
				<li>
					<a href="Product?status=selectall&flag=24">
						<p>进口</p>
					</a>
				</li>
			</ul>
		</div>
	    </div>
		<div class="J_Ald" style="   width: 33%;    height: 423px;   margin-left: 5px;    float: left;background-color: white">
			<img class="mod" src="./img/背景.png" style="width: 410px;height: 410px;margin-top: 10px;margin-left: 30px">
<%--			<button style="size: 30px;margin-top: -150px"><</button>--%>
<%--			<button style="size: 30px;margin-top: -150px;margin-left: 330px">></button>--%>
		</div>
		<div class="tbh-user" style="   width: 33%;   height: 423px;    float: left;background-color: white">
			<%if(flag.equals("0")){%>
			<a href="Product?name=<%=session.getAttribute("uname")%>&id=<%=session.getAttribute("uId")%>&status=my"><img src="./imgweb/<%=session.getAttribute("uimg")%>" style="margin-left:42%;margin-top: 50px; width: 60px;height: 60px;"></a>
			<a href="Product?name=<%=session.getAttribute("uname")%>&id=<%=session.getAttribute("uId")%>&status=my" style="text-align: center;font-size: 16px;margin-top: 24px;display: block;height: 24px;line-height: 24px"><%=session.getAttribute("uname")%></a>
			<div class="member-column-4" style="margin-top: 25px;display: block;width: 100%;height: 36px">
				<a href="Shopping_Cart?id=<%=session.getAttribute("uId")%>&status=selectall" style="width: 55px;height: 36px;float:left;text-align: center;margin-left:  10%">
					<strong style="    display: block;    font-size: 14px;color: #F40;"><c:forEach items="${shopping_cart}" var="shopping_cart" >${shopping_cart.id}</c:forEach></strong>
					购物车
				</a>
				<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=logistics&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left:  10%">
					<%int j=0;%>
					<c:forEach items="${order1}" var="order1" ><%j++;%></c:forEach>
					<strong style="    display: block;    font-size: 14px;color: #F40;"><%=j%></strong>
					待发货
				</a>
				<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=translate" style="width: 55px;height: 36px;float:left;text-align: center;margin-left:  10%">
					<strong style="    display: block;    font-size: 14px;color: #F40;"><c:forEach items="${order2}" var="order2" >${order2.id}</c:forEach></strong>
					待收货
				</a>
				<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=goods&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">
					<strong style="    display: block;    font-size: 14px;color: #F40;"><c:forEach items="${order3}" var="order3" >${order3.id}</c:forEach></strong>
					已收货
				</a>
				<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=confirm&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">
					<%int i=0;%>
					<c:forEach items="${indent}" var="indent" ><%i++;%></c:forEach>
					<strong style="    display: block;    font-size: 14px;color: #F40;"><%=i%></strong>
					等待确认
				</a>
			</div>
			<div class="user-mytao" style="margin-top: 30px;display: block">
				<a href="Shopping_Cart?id=<%=session.getAttribute("uId")%>&status=selectall" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">
					<img src="./img/星号.png" style="width: 35px;">
					<h2 style="font-size: 13px">宝贝收藏</h2>
				</a>
<%--				<a href="" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">--%>
<%--					<img src="./img/女包.png" style="width: 35px;">--%>
<%--					<h2 style="font-size: 13px">买过的店</h2>--%>
<%--				</a>--%>
<%--				<a href="" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">--%>
<%--					<img src="./img/店铺.png" style="width: 35px;">--%>
<%--					<h2 style="font-size: 13px">收藏的店</h2>--%>
<%--				</a>--%>
<%--				<a href="" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">--%>
<%--					<img src="./img/时钟.png" style="width: 35px;">--%>
<%--					<h2 style="font-size: 13px">我的足迹</h2>--%>
<%--				</a>--%>
				<%}else{%>
				<a href="Product?name=<%=session.getAttribute("uname")%>&id=<%=session.getAttribute("uId")%>&status=my"><img src="./imgweb/<%=session.getAttribute("uimg")%>" style="margin-left:42%;margin-top: 50px; width: 60px;height: 60px;"></a>
				<a href="Product?name=<%=session.getAttribute("uname")%>&id=<%=session.getAttribute("uId")%>&status=my" style="text-align: center;font-size: 16px;margin-top: 24px;display: block;height: 24px;line-height: 24px"><%=session.getAttribute("uname")%></a>
				<div class="member-column-4" style="margin-top: 25px;display: block;width: 100%;height: 36px">
<%--					<a href="Shopping_Cart?id=<%=session.getAttribute("uId")%>&status=selectall" style="width: 55px;height: 36px;float:left;text-align: center;margin-left:  10%">--%>
<%--						<strong style="    display: block;    font-size: 14px;color: #F40;"><c:forEach items="${shopping_cart}" var="shopping_cart" >${shopping_cart.id}</c:forEach></strong>--%>
<%--						购物车--%>
<%--					</a>--%>
					<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=logistics&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left:  10%">
						<%int j=0;%>
						<c:forEach items="${order1}" var="order1" ><%j++;%></c:forEach>
						<strong style="    display: block;    font-size: 14px;color: #F40;"><%=j%></strong>
						物流管理
					</a>
					<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=translate" style="width: 55px;height: 36px;float:left;text-align: center;margin-left:  10%">
						<strong style="    display: block;    font-size: 14px;color: #F40;"><c:forEach items="${order02}" var="order02" >${order02.id}</c:forEach></strong>
						待签收
					</a>
					<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=goods&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">
						<strong style="    display: block;    font-size: 14px;color: #F40;"><c:forEach items="${order03}" var="order03" >${order03.id}</c:forEach></strong>
						已签收
					</a>
				</div>
				<div style="margin-top: 30px">
				<a href="Publish_product.jsp" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">
					<img src="./img/星号.png" style="width: 35px;">
					<h2 style="font-size: 13px">发布商品</h2>
				</a>
<%--				<a href="ProductState?id=<%=session.getAttribute("uId")%>&status=logistics&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">--%>
<%--					<img src="./img/女包.png" style="width: 35px;">--%>
<%--					<h2 style="font-size: 13px">物流管理</h2>--%>
<%--				</a>--%>
				<a href="Product?id=<%=session.getAttribute("uId")%>&status=myproduct&flag=<%=session.getAttribute("uflag")%>" style="width: 55px;height: 36px;float:left;text-align: center;margin-left: 10%">
					<img src="./img/店铺.png" style="width: 35px;">
					<h2 style="font-size: 13px">我的商品</h2>
				</a>
				</div>
				<%}%>
			</div>
		</div>
	</div>
		<hr />	<br />

	<%
		 len=0;%>
	<c:forEach items="${all}" var="product" >
		<%len++;%>
		</c:forEach>

	<%if(pagesize==0) {
		if (len >= 4) end = 4;
		else end = len;
	}
	if(len>pagesize)pagesize=len;
	%>
<div class="tb-commend" style="    width: 1200px;    padding: 0 20px;height: 400px;margin-left: 20px" >
	<h3 class="tb-recommend-header" style="display: block">猜你喜欢</h3>
	<%--<c:forEach items="${all}" var="product"   varStatus="flag" begin="<%=pagestart%>" end="<%=end-1%>">--%>
	<c:forEach items="${all}" var="product"   varStatus="flag" >
			<a href="Product_page.jsp?name=${product.name}&price=${product.price}&img=${product.img}&type=${product.type}&size=${product.size}&color=${product.color}&shopid=${product.shopid}&id=${product.id}&uid=<%=session.getAttribute("uId")%>&stock=${product.stock}" style="display: inline-block;width: 32%;height: 150px;background-color: #d9d9d9;margin-left: 10px;margin-top: 10px" class="item-link">
				<div class="img-wrapper" style="    position: absolute;width: 150px;height: 150px;overflow: hidden;background-color: rgba(27, 23, 67, 0.03);border-radius: 10px;">
					<img src="./imgweb/${product.img}" style="display: block;width: 100%;height: 100%;border-radius: 10px;">
				</div>
				<div class="info-wrapper" style="height: 116px;margin-left: 162px;overflow: hidden;">
					<div class="tb-title" style="display: flex;max-height: 46px;overflow: hidden;color: #333;font-size: 16px;line-height: 23px;margin: 6px 0 2px 0px;">${product.name}</div>
					<div class="tb-title" style="display: inline-block;max-height: 46px;overflow: hidden;color: #333;font-size: 16px;line-height: 23px;margin: 6px 0 2px 0px;">${product.shopname}</div>

					<div class="tb-stock" style="display: flex;max-height: 46px;overflow: hidden;color: #333;font-size: 16px;line-height: 23px;margin: 6px 0 2px 0px;">库存：${product.stock}</div>
				</div>
				<div class="price-wrapper" style="margin-left: 162px; color: #ff5000;">
					<span class="price-value" style="bottom:0;display: inline-block;font-size: 22px;line-height: 22px;"><em>¥</em>${product.price}</span>
				</div>
			</a>
	</c:forEach>
</div>
	<%--
	<%int thispage=0;
	if(end%4==0)thispage=end/4;
	else thispage=end/4+1;
	int allpage=0;
		if(pagesize%4==0)allpage=pagesize/4;
		else allpage=pagesize/4+1;
	%>
	<div style="width: 100%;height: 100px">
	<a href="Product?status=reduce_page&flag=0&pagesize=<%=pagesize%>&pagestart=<%=pagestart%>&end=<%=end%>&uflag=<%=session.getAttribute("uflag")%>&id=<%=session.getAttribute("uId")%>" onclick="return check_reduce()" style="margin-left: 200px;float: left">上一页</a>
		<h2 style="margin-left: 500px;float: left;margin-top: 0px;margin-bottom: 0px">[第<%=thispage%>页/共<%=allpage%>页]</h2>
	<a href="Product?status=add_page&flag=0&pagesize=<%=pagesize%>&pagestart=<%=pagestart%>&end=<%=end%>&uflag=<%=session.getAttribute("uflag")%>&id=<%=session.getAttribute("uId")%>" onclick="return check_add()" style="float: right">下一页</a>
	</div>--%>
</body>
</html>
<script>
	function check_add(){
		var a='<%=pagesize%>';
		var b='<%=end%>';
		if(parseInt(a)>=0){
			if(parseInt(a)>parseInt(b)) return true;
			else {
				alert("已经到底了！！！");
				return false;
			}
		}

	}
	function check_reduce(){
		var a='<%=pagesize%>';
		var b='<%=end%>';
		if(parseInt(a)>=0){
			if(parseInt(b)>4) return true;
			else {
				alert("已经是第一页了！！！");
				return false;
			}
		}

	}
</script>