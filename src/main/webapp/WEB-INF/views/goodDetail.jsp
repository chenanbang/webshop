<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function increase(){
		var count=document.getElementById("quantity").value*1;
		if(count<document.getElementById("store").innerHTML*1){
			document.getElementById("cNum").value=document.getElementById("quantity").value=count+1;
		}
	}
	function decrease(){
		var count=document.getElementById("quantity").value*1;
		if(count>1){
			document.getElementById("orderItemNum").value=document.getElementById("quantity").value=count-1;
		}
	}

	function addCart(){
		var targetForm=document.getElementById("addCart");
		targetForm.action="${pageContext.request.contextPath }/cart/addCart";
		document.getElementById("cNum").value=document.getElementById("quantity").value;
		targetForm.submit();
	}

	function buyNow() {
		var targetForm=document.getElementById("buyNow");
		targetForm.action="${pageContext.request.contextPath }/cart/buyNow";
		document.getElementById("orderItemNum").value=document.getElementById("quantity").value;
		targetForm.submit();
	}
	
</script>

</head>
<body>
	<form id="addCart" style="display: none;" method="post">
		<input type="text" name="goodId" value="${requestScope.goods.goodId }"/>
		<input type="text" id="cNum" name="cNum"/>
	</form>
	<form id="buyNow" style="display: none;" method="post">
		<input type="text" name="goodId" value="${requestScope.goods.goodId }"/>
		<input type="text" id="orderItemNum" name="orderItemNum"/>
	</form>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png" alt="网上商城">
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/header.jpg" alt="正品保障" title="正品保障" height="50" width="320">
		</div>	
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:if test="${user==null }">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/login">登录</a>|</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a href="${pageContext.request.contextPath}/showRegisterPage">注册</a>|
				</li>
			</c:if>
			<c:if test="${user!=null }">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					Hi,${user.username }	
				|</li>
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/lookOrders">我的订单</a>
				|</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a href="${pageContext.request.contextPath}/logout">退出</a>|
				</li>
			</c:if>
				<li>
					<a href="${pageContext.request.contextPath}/userCenter">会员中心</a>
					|
				</li>
		
				<li>
					<a href="${pageContext.request.contextPath}/manageCenter">管理中心</a>
					|
				</li>
				<li>
					<a>关于我们</a>
					
				</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath}/cart/showMyCarts">查看购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath}/index">首页</a>
						|
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/goods/goodsClassify/1/1">商品类别</a>
						|
					</li>
					<li>
						<a >安全频道</a>
						|
					</li>
					<li>
						<a>亿家卡</a>
						|
					</li>
					<li>
						<a >蔬菜基地</a>
						|
					</li>
					<li>
						<a>节气养生</a>
						|
					</li>
					<li>
						<a>便民服务</a>
						|
					</li>
		</ul>
	</div>

</div><div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
						<dl>
							<dt>
								<a href="./蔬菜分类.htm">蔬菜</a>
							</dt>
									<dd>
										<a >无公害蔬菜</a>
									</dd>
									<dd>
										<a>特菜类</a>
									</dd>
									<dd>
										<a>有机蔬菜</a>
									</dd>
									<dd>
										<a>蔬菜套餐</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a>水果</a>
							</dt>
									<dd>
										<a>国产</a>
									</dd>
									<dd>
										<a>进口</a>
									</dd>
									
						</dl>
						<dl>
							<dt>
								<a >肉类</a>
							</dt>
									<dd>
										<a>猪肉</a>
									</dd>
									<dd>
										<a>牛羊肉</a>
									</dd>
									<dd>
										<a>家禽</a>
									</dd>
									<dd>
										<a>鱼</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a>蛋、奶及肉制品类</a>
							</dt>
									<dd>
										<a>蛋</a>
									</dd>
									<dd>
										<a>奶</a>
									</dd>
									<dd>
										<a>豆制品</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a >干果</a>
							</dt>
									<dd>
										<a>干制坚果</a>
									</dd>
									<dd>
										<a>干制果实/果肉</a>
									</dd>
									<dd>
										<a >干制种仁</a>
									</dd>
									<dd>
										<a>    </a>
									</dd>
						</dl>
						
						<dl>
							<dt>
								<a>油</a>
							</dt>
									<dd>
										<a >茶油</a>
									</dd>
									<dd>
										<a >核桃油</a>
									</dd>
									<dd>
										<a >橄榄油</a>
									</dd>
									<dd>
										<a>芥花籽油</a>
									</dd>
						</dl>
						
						<dl>
							<dt>
								<a >茶</a>
							</dt>
							<dd>
								<a >绿茶</a>
							</dd>
							<dd>
								<a>红茶</a>
							</dd>
							<dd>
								<a >乌龙茶</a>
							</dd>
							<dd>
								<a>白茶</a>
							</dd>
						</dl>
						
				</div>
			

		</div>
		<div class="span18 last" style="margin-top: 9px;">
			<div class="productImage" style="height: 320px;">
				<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="" rel="gallery">
					<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }${requestScope.goods.goodDetailPath}">
				</a>
			</div>
			<div class="name">品称: ${requestScope.goods.goodName }</div>
			<div class="sn" style="margin-top: 3px; margin-bottom: 3px;"></div>
			<div class="name">描述: ${requestScope.goods.goodDescribe }</div>
			
			<div class="sn" style="margin-top: 3px; margin-bottom: 3px;"></div>
			<div class="info">
				<dl>
					<dt style="color:black;">秒杀价:</dt>
					<dd>
						<strong>￥：${requestScope.goods.goodSpikePrice }元/件</strong>
							<span style="color:black;">原 价：</span>
							<del>￥${requestScope.goods.goodPrimePrice }元/件</del>
					</dd>
				</dl>
				<div class="sn" style="margin-top: 3px; margin-bottom: 3px;"></div>
					<dl>
						<dt style="color:black;">促销:</dt>
						<dd>
							<a target="_blank" title="限时抢购 (2017-05-16 ~ 2017-07-06)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt></dt>
						<dd>
							<span></span>
						</dd>
					</dl>
			</div>
			<div class="sn" style="margin-top: 3px; margin-bottom: 3px;"></div>
			<div class="name">服务承诺: 坏单包退、满88包邮、一站式购齐、正品保证</div>
			<div>&nbsp;</div>
			
			<div class="action">
				<dl class="quantity">
					<dt style="color:black;">购买数量:</dt>
					<dd>
						<input id="quantity" name="cNum" value="1" maxlength="4" onpaste="return false;" type="text">
						<div>
							<span id="increase" class="increase" onclick="increase()">&nbsp;</span>
							<span id="decrease" class="decrease" onclick="decrease()">&nbsp;</span>
						</div>
					</dd>
					<dd style="color:black;">
						件
					</dd>
					<dd style="color:black;">
						&nbsp; &nbsp; 库存<span id="store">${requestScope.goods.goodStore }</span>件
					</dd>
				</dl>
				<div class="buy" style="padding-left: 6px;">
					<!-- <input id="addCart" class="addCart" value="加入购物车" type="button"> -->
					<a style="width:80px; background: url(${pageContext.request.contextPath}/images/product.gif) -26px -600px no-repeat;" id="addCart" class="addCart" onclick="buyNow()">
						立即购买
					</a>
					<a style="margin-left:20px;background: url(${pageContext.request.contextPath}/images/product.gif) 0px -600px no-repeat;" id="addCart" class="addCart" onclick="addCart()">
						&nbsp; &nbsp;加入购物车
					</a>
				</div>
			</div>
			<div id="bar" class="bar" style="margin-top: 0px;">
				<ul>
					<li id="introductionTab">
						<!-- <a href="#introduction">商品介绍</a> -->
						<span style="font-size: 14px;"><strong>商 品 详 情</strong></span>
					</li>
				</ul>
			</div>
			<div>
				<img alt="产品详情" src="${pageContext.request.contextPath}/image/2017-04-10_005639.png">
			</div>
			
			<!-- <div id="introduction" name="introduction" class="introduction">
				<div class="title">
					<strong>商品介绍</strong>
				</div>
				<div>
					<img src="image/r___________renleipic_01/bigPic139f030b-d68b-41dd-be6d-b94cc568d3c5.jpg">
				</div>
			</div> -->
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
		</div>
	</div>
	<div class="span24">
		<ul class="bottomNav">
			<li>
				<a href="#">关于我们</a>
				|
			</li>
			<li>
				<a href="#">联系我们</a>
				|
			</li>
			<li>
				<a href="#">诚聘英才</a>
				|
			</li>
			<li>
				<a href="#">法律声明</a>
				|
			</li>
			<li>
				<a>友情链接</a>
				|
			</li>
			<li>
				<a target="_blank">支付方式</a>
				|
			</li>
			<li>
				<a target="_blank">配送方式</a>
				|
			</li>
			<li>
				<a >SHOP++官网</a>
				|
			</li>
			<li>
				<a>SHOP++论坛</a>
				
			</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>