<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
		
			<li><a>会员中心</a> |</li>
			<li><a>购物指南</a> |</li>
			<li><a>关于我们</a></li>
		</ul>
	</div>
	<div class="cart">
		<a href="${pageContext.request.contextPath}/showMyCarts">购物车</a>
	</div>
	<div class="phone">
		客服热线: <strong>96008/53277764</strong>
	</div>
</div>
<div class="span24">
	<ul class="mainNav">
		<li><a href="${pageContext.request.contextPath}/index">首页</a> |</li>
		<li><a href="${pageContext.request.contextPath}/goods/goodsClassify/1/1">商品类别</a> |</li>
		<li><a>安全频道</a> |</li>
		<li><a>商城卡</a> |</li>
		<li><a>蔬菜基地</a> |</li>
		<li><a>节气养生</a> |</li>
		<li><a>便民服务</a> |</li>

	</ul>
</div>