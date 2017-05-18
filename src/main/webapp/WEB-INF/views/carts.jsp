<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

	onload=function(){
		 //减操作
        var sub=document.getElementsByName("sub");
        
        var gps=document.getElementsByName("gp");
        var stps=document.getElementsByName("stp");
        
        var oiNums=document.getElementsByName("oiNums");
        var oiSubTotals=document.getElementsByName("oiSubTotals");
        for(var i=0;i<sub.length;i++){
			sub.item(i).index=i;
            sub.item(i).onclick= function () {
                var targetElement=this.nextElementSibling;
				var currentVal=parseInt(targetElement.value);
				
				var gpObj=gps[this.index];
				var stpObj=stps[this.index];
				
				var oiNumObj=oiNums[this.index];
				var oiSubTotalObj=oiSubTotals[this.index];
				
				if(currentVal>1){
					oiNumObj.value=targetElement.value=currentVal-1;
					oiSubTotalObj.value=stpObj.value=stpObj.value*1-gpObj.value*1;
					document.getElementById("effectivePoint").value=document.getElementById("effectivePoint").value*1-gpObj.value*1;
					document.getElementById("effectivePrice").value=document.getElementById("effectivePrice").value*1-gpObj.value*1;
					document.getElementById("totalPrice").value=document.getElementById("effectivePrice").value*1;
				}
            }
        }
        //加操作
        var add=document.getElementsByName("add");
        for(var i=0;i<add.length;i++){
            add.item(i).index=i;
            add.item(i).onclick= function () {
                var targetElement=this.previousElementSibling;
                var currentVal=parseInt(targetElement.value);
                
                var gpObj=gps[this.index];
				var stpObj=stps[this.index];

				var oiNumObj=oiNums[this.index];
				var oiSubTotalObj=oiSubTotals[this.index];
				
				if(currentVal<targetElement.previousElementSibling.previousElementSibling.value*1){
					oiNumObj.value=targetElement.value=currentVal+1;
					oiSubTotalObj.value=stpObj.value=stpObj.value*1+gpObj.value*1;
					document.getElementById("effectivePoint").value=document.getElementById("effectivePoint").value*1+gpObj.value*1;
					document.getElementById("effectivePrice").value=document.getElementById("effectivePrice").value*1+gpObj.value*1;
					document.getElementById("totalPrice").value=document.getElementById("effectivePrice").value*1;
				}
            }
        }
	}

	function generateOrder(){
		var targetForm=document.getElementById("orderForm");
		targetForm.action="${pageContext.request.contextPath}/order/generateOrder";
		targetForm.submit();
	}
	
</script>

</head>
<body>
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
			<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
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
						<a href="#">我的订单</a>
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
			<a href="${pageContext.request.contextPath}/cart/showMyCarts">查看购物车</a>
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
				<a >亿家卡</a>
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
	
	<form id="orderForm" style="display: none;" method="post">
		<c:forEach items="${requestScope.cartResultList }" var="cart">
			<!-- 生成订单所需要的数据 -->
			<input type="text" name="cIds" value="${cart.cId }"/>
			<input type="text" name="goodIds" value="${cart.goodId }"/>
			<input type="text" name="oiNums" value="${cart.cNum }"/>
			<input type="text" name="oiSubTotals" value="${cart.subTotalPrice }"/>
			
			<!-- 显示所需要的数据 -->
			<input type="text" name="smallImagePaths" value="${cart.smallImagePath }"/>
			<input type="text" name="goodDescribes" value="${cart.goodDescribe }"/>
			<input type="text" name="goodPrices" value="${cart.goodPrice }"/>
		</c:forEach>
			<input type="text" id="totalPrice" name="totalPrice" value="${requestScope.totalPrice }"/>
			
			<!-- 收货地址相关信息 
			<input type="text" name="addrId" value="${requestScope.address.addrId}"/>
			<input type="text" name="address" value="${requestScope.address.address}"/>
			<input type="text" name="receiver" value="${requestScope.address.receiver}"/>
			<input type="text" name="receiverPhone" value="${requestScope.address.receiverPhone }"/>
			-->
	</form>
	
</div>	
	<div class="container cart" style="margin-top: 24px;">
		<div class="span24">
			<table>
				<tbody style="text-align: center;">
					<tr>
						<th style="text-align: center;">图片</th>
						<th style="text-align: center;">商品</th>
						<th style="text-align: center;">价格</th>
						<th style="text-align: center;">数量</th>
						<th style="text-align: center;">小计</th>
						<th style="text-align: center;">操作</th>
					</tr>
					
					<c:forEach items="${requestScope.cartResultList }" var="cart">
						<tr>
							<td width="60px;">
								<input type="hidden" name="id" value="22">
								<img src="${pageContext.request.contextPath}${cart.smallImagePath}">
							</td>
							<td width="240px;" style="text-align: left;">
								<a target="_blank" style=" padding-left: 30px;">${cart.goodDescribe }</a>
							</td>
							<td width="120px;">
								<!-- ￥${cart.goodPrice } -->
								￥<input type="text" readonly="readonly" name="gp" value="${cart.goodPrice }" style="width: 46px; border:none;"/>
							</td>
							<td width="200px;">		<!--  class="quantity" -->
								<input name="storeNum" type="hidden" value="${cart.goodStore }"/>
								<input type="button" value="-" name="sub"/>
							    <input style="width:40px; text-align: center;" readonly="readonly" type="text" value="${cart.cNum }" name="goodCount"/>
							    <input type="button" value="+" name="add"/>
							</td>
							<td width="140">
								<!-- ￥<span class="subtotal">${cart.subTotalPrice }</span> -->
								￥<input type="text" readonly="readonly" name="stp" value="${cart.subTotalPrice }" style="width: 60px; border:none;"/>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/cart/delete/${cart.cId}" class="delete">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				
				<!-- 当当前用户的购物车中没有商品记录的时候显示如下内容 -->
				<c:if test="${requestScope.isNull==true }">
					<img style="margin-top: 20px;margin-bottom: 20px;" alt="还在犹豫神马,赶紧行动吧!" src="${pageContext.request.contextPath}/image/addcartmentation.png">
				
				</c:if>
				
				<!-- 当当前用户的购物车中有商品记录的时候显示商品信息 -->
				<c:if test="${requestScope.isNull==false }">
					<div class="total">
						<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
						赠送积分: <input id="effectivePoint" readonly="readonly" style="width:60px; border:none;  color: #FF6F4F;" value="${requestScope.totalPrice }"/>
						
						商品金额: <strong>￥<input id="effectivePrice" readonly="readonly" style="width:70px; border:none; font-weight: bold; color: red;" value="${requestScope.totalPrice }"/></strong>
					</div>
				
				
					<div class="bottom" >
						<a href="javascript:;" id="clear" class="clear">清空购物车</a>
						<a href="javascript:void(0)" id="submit" class="submit" onclick="generateOrder()">生成订单</a>
					</div>
					
				</c:if>
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
				<a >关于我们</a>
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
				<a  target="_blank">配送方式</a>
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
</body>
</html>