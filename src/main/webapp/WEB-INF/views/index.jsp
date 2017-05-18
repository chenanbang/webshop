<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
	body,div,img,span,p,ul,li,h1,h2,h3,h4,h5,h6,table{padding:0px; font-family:"微软雅黑"; margin:0px;}
       *{padding:0px; margin:0px;}
   .p a{color:#444455;}
   .p a:link{text-decoration:none;}
   .p a:visited{text-decoration:none;}
   .p a:hover{color:#D40000;}
   .p1{padding-top:10px; padding-left:12px; font-weight: bold; font-size: 16px;}
   
   
   .p2{padding-top:15px; padding-left:9px; color:#E22C37; font-size:23px;}
   .killprice{color:#979797; font-size:13px; text-decoration:line-through;}
  

   .out{width:222px; height:280px;  margin:6px 6px; padding-top:3px; float: left;}
   .out:hover{background:#E0E0E0; border-radius:4px;}
   
   .p{width:214px;height:274px; border:1px solid #E8E8E8; background:white; margin:auto;}
   .p:hover{border:1px solid red; width:214px;height:274px; }
   .p1{height:20px; }
   #sp{background-position:center; background-repeat: no-repeat; background-image: url("${pageContext.request.contextPath}/image/sq.png");}
   .imging{ width:160px; height:160px; margin:12px 0px 8px 12px; background:white;}
</style>

<script type="text/javascript">
onload=function(){
	if(document.getElementById("msg").innerHTML){
		alert(document.getElementById("msg").innerHTML);
	}
	
}
</script>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png" alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<%-- <c:if test="${user==null }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/showLoginPage">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/showRegisterPage">注册</a>|
					</li>
				</c:if>
				<c:if test="${user!=null }">
					<li id="headerUsername" class="headerUsername">
						Hi,${user.username } |
					</li>
					<li id="headerLogout" class="headerLogout">
						<a href="${pageContext.request.contextPath}/logout">[退出]</a>|
					</li>
				</c:if> --%>
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
	<span style="display: none;" id="msg">${message }</span>
	<div class="span24">
		<ul class="mainNav">
			<li>
				<a href="${pageContext.request.contextPath}/index">首页</a>
				|
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/goods/goodsClassify/1/1">商品分类</a>
				|
			</li>
			<li>
				<a >安全频道</a>
				|
			</li>
			<li>
				<a>商城卡</a>
				|
			</li>
			<li>
				<a>蔬菜基地</a>
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
</div>




<div class="container index">
		
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门商品</strong>
					</div>
					<ul class="tab">
						<li class="current">
							<a href="./蔬菜分类.htm?tagIds=1" target="_blank"></a>
						</li>
						<li>
							<a  target="_blank"></a>
						</li>
						<li>
							<a target="_blank"></a>
						</li>
					</ul>
					
					<c:forEach items="${requestScope.goodsList }" var="goods">
						<div class='out'>
							<div class="p fl" id="pid">
								<a href='${pageContext.request.contextPath}/goods/showGoodsDetail/${goods.goodId}' >
									<p class='imging'>
										<img src= "${pageContext.request.contextPath}${goods.goodPath }"/>
									</p>
									<p class="p1">
										<span>${goods.goodDescribe }</span>
										<span id="sp">&nbsp; &nbsp; &nbsp; &nbsp; </span>
									</p>
									<p class="p2">
										￥${goods.goodSpikePrice }
										<span class='killprice'>￥${goods.goodPrimePrice }</span>
									</p>
								</a>
							</div>
						</div> 
					
					</c:forEach>
					
			</div>
		</div>
		
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新商品</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
						<li class="current">
							<a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a>
						</li>
						<li>
							<a  target="_blank"></a>
						</li>
						<li>
							<a target="_blank"></a>
						</li>
					</ul>
					
					<c:forEach items="${requestScope.goodsListOrderByTime }" var="goods">
						<div class='out'>
							<div class="p fl" id="pid">
								<a href="${pageContext.request.contextPath}/goods/showGoodsDetail/${goods.goodId}" >
									<p class='imging'>
										<img src= "${pageContext.request.contextPath}${goods.goodPath }"/>
									</p>
									<p class="p1">
										<span>${goods.goodDescribe }</span>
										<span id="sp">&nbsp; &nbsp; &nbsp; &nbsp; </span>
									<p class="p2">
										￥${goods.goodSpikePrice }
										<span class='killprice'>￥${goods.goodPrimePrice }</span>
									</p>
								</a>
							</div>
						</div> 
					
					</c:forEach>
						
					
			</div>
		</div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>新手指南</dt>
							<dd>
								<a  target="_blank">支付方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">配送方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">售后服务</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助</a>
								|
							</dd>
							<dd>
								<a  target="_blank">蔬菜卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">礼品卡</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">亿家卡</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
			</div>
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
					<a>关于我们</a>
					|
				</li>
				<li>
					<a>联系我们</a>
					|
				</li>
				<li>
					<a>招贤纳士</a>
					|
				</li>
				<li>
					<a>法律声明</a>
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
					<a>服务声明</a>
					|
				</li>
				<li>
					<a>广告声明</a>
				</li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body></html>