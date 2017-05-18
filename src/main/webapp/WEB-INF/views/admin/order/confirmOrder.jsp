<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>确认订单页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content=""/>
 <meta name="format-detection" content="telephone=no" />
 <meta name=""/>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath }/css/orderconfirm.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/tasp.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
	#page{width:auto;}
	#comm-header-inner,#content{width:950px;margin:auto;}
	#logo{padding-top:26px;padding-bottom:12px;}
	#header .wrap-box{margin-top:-67px;}
	#logo .logo{position:relative;overflow:hidden;display:inline-block;width:140px;height:35px;font-size:35px;line-height:35px;color:#f40;}
	#logo .logo .i{position:absolute;width:140px;height:35px;top:0;left:0;background:url(http://a.tbcdn.cn/tbsp/img/header/logo.png);}
	
</style>

<script type="text/javascript">

	function submitOrder(){
		var targetForm=document.getElementById("orderForm");
		targetForm.action="${pageContext.request.contextPath}/order/submitOrder";
		targetForm.submit();
	}
	
</script>


</head>

<body data-spm="1">

	<form id="orderForm" style="display: none;" action="" method="post">
		<input type="text" name="cIds" value="${requestScope.cIds }"/>
		<input type="text" name="goodIds" value="${requestScope.goodIds }"/>
		<input type="text" name="oiNums" value="${requestScope.oiNums }"/>
		<input type="text" name="oiSubTotals" value="${requestScope.oiSubTotals }"/>
		<input type="text" name="totalPrice" value="${requestScope.orderTotalPrice }"/>
		
		<input type="text" name="addrId" value="${requestScope.address.addrId }"/>
		
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
		<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
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
	
</div>

<div id="page">

<div id="content" class="grid-c">

<div id="address" class="address" style="margin-top: 20px; height: auto;" data-spm="2">

	<form name="addrForm" id="addrForm" action="#">
		<h3>确认收货地址
			<span class="manage-address">
				<c:if test="${requestScope.address.addrId==0 }">
					<a href="${pageContext.request.contextPath}/addReceiveAddress" target="_blank" title="管理我的收货地址"
					class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.7">添加收货地址</a>
				</c:if>
				<c:if test="${requestScope.address.addrId!=0 }">
					<a href="${pageContext.request.contextPath}/addressManageCenter" target="_blank" title="管理我的收货地址"
					class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.7">管理收货地址</a>
				</c:if>
			</span>
		</h3>
		
		<c:if test="${requestScope.address.addrId==0 }">
			<img alt="添加地址提示信息" src="${pageContext.request.contextPath}/image/addaddressmentation.png">
		
		</c:if>
		<c:if test="${requestScope.address.addrId!=0 }">
			<ul id="address-list" class="address-list" style="">
			     <li style="padding-left: 20px;" class="J_Addr J_MakePoint clearfix  J_DefaultAddr " data-point-url="http://log.mmstat.com/buy.1.20">
			 	 	<s class="J_Marker marker"></s>
			 		<!-- <span class="marker-tip">寄送至</span> -->
					 <div class="address-info"><!-- class="modify" -->
						 <a href="${pageContext.request.contextPath}/addressManageCenter" style="float:right; display: block; margin-left: 16px;" data-point-url="#">修改本地址</a>
						 <a href="${pageContext.request.contextPath}/addressManageCenter" style="float:right; display: block; margin-left: 22px;" data-point-url="#">更换收获地址</a>
						 
						 <!-- <a href="javascript:void(0);" style="float: right;  width:200px;" class="J_DefaultHandle set-default J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.18">设置为默认收货地址</a> -->
						 <input name="address" class="J_MakePoint " type="radio" value="674944241"
							 id="addrId_674944241" data-point-url="http://log.mmstat.com/buy.1.20"
							 ah:params="id=674944241^^stationId=0^^address=湖北民族学院（信息工程学院）  男生宿舍楼5栋102^^postCode=445000^^addressee=朱万雄^^phone=^^mobile=18727717260^^areaCode=422801"
						  	 checked="checked" >
						 <label for="addrId_674944241" class="user-address">
						         ${requestScope.address.address }(${requestScope.address.receiver }收)
						         <em>${requestScope.address.receiverPhone }</em>
						         <!-- <em id="tip" class="tip" style="display: block;">(默认地址)</em> -->
						 </label>
						 <!-- <em id="tip" class="tip" style="display: block;">默认地址</em> -->
					 </div>
				  </li>
			  </ul>
		  </c:if>
		  
		  
		  <ul id="J_MoreAddress" class="address-list hidden">
			   
		  </ul>
		<!-- 
			<div class="address-bar">
				<a href="#" class="new J_MakePoint" id="J_NewAddressBtn">使用新地址</a>
			</div>
		 -->
	</form>
</div>

<form id="J_Form" name="J_Form" action="/auction/order/unity_order_confirm.htm" method="post">
 
	<div>
	 <h3 class="dib">确认订单信息</h3>
	 <table cellspacing="0" cellpadding="0" class="order-table" id="J_OrderTable" summary="统一下单订单信息区域">
		 <caption style="display: none">统一下单订单信息区域</caption>
		 <thead>
		 <tr>
		 <th class="s-title">宝贝<hr/></th>
		 <th class="s-price">单价(元)<hr/></th>
		 <th class="s-amount">数量<hr/></th>
		 <th class="s-agio">优惠方式(元)<hr/></th>
		 <th class="s-total">小计(元)<hr/></th>
		 </tr>
		 </thead>
	
		<tbody data-spm="3" class="J_Shop" data-tbcbid="0" data-outorderid="47285539868"  data-isb2c="false" data-postMode="2" data-sellerid="1704508670">
			<tr class="first"><td colspan="5"></td></tr>
			
		 <c:forEach items="${requestScope.carts }" var="cart">
		 	
		 	<tr class="item" data-lineid="19614514619:31175333266:35612993875" data-pointRate="0">
			  <td class="s-title">
			     <a href="javascript:;" style="width: 60px;height:60px;" target="_blank" title="Huawei/华为 G520新款双卡双待安卓系统智能手机4.5寸四核手手机" class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.5">
			     	<img style="width: 60px;height:60px;" src="${pageContext.request.contextPath}/${cart.smallImagePath}" class="itempic">
			     	<span style="width: 200px; padding-left: 10px;" class="title J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.5">
			     		${cart.goodDescribe }
			     	</span>
			     </a>
				 
			     <div class="props" style="padding-left: 11px;">
				     <span>
				     	质量:正品行货,质量有保障     性价比:史上之最   
				     	<br/> 
				     	 版本: 中国大陆
				     </span>
				 </div>
			     <div style="padding-left: 11px;">
			    	<span style="color:gray;">卖家承诺72小时内发货</span>
			 	 </div>
		      </td>
		      
			 <td class="s-price">
				 <span class='price '>
				   <em class="style-normal-small-black J_ItemPrice">￥${cart.goodPrice }</em>
				 </span>
			 </td>
			 <td class="s-amount" data-point-url="">
			     ${cart.cNum }
			 </td>
			 <td class="s-agio">
			     <div class="J_Promotion promotion" data-point-url="">无优惠</div>
			 </td>
			 <td class="s-total">
			   <span class='price '>
			      <em class="style-normal-bold-red J_ItemTotal">￥${cart.subTotalPrice }</em>
			   </span>
			   <input id="furniture_service_list_b_47285539868" type="hidden" name="furniture_service_list_b_47285539868"/>
			 </td>
		  </tr>
		</c:forEach>
		
			<tr class="item-service">
				<td colspan="5" class="servicearea" style="display: none"></td>
			</tr>
			<tr class="blue-line" style="height: 2px;"><td colspan="5"></td></tr>
			
			
			<!-- -->
			<tr class="other other-line">
				 <td colspan="5">
					 <ul class="dib-wrap">
						 <li class="dib user-info">
							 <ul class="wrap">
								 <li>
								  <div class="field gbook">
								   <label class="label">给商城留言：</label>
								   <textarea class="text" style="width:350px;height:80px;" title="选填：对本次交易的补充说明（建议填写已经和卖家达成一致的说明）" name=""></textarea>
								 </div>
								 </li>
							 </ul>
						 </li>
					 	 <li class="dib extra-info">
					
						 <div class="shoparea">
							 <ul class="dib-wrap">
								 <li class="dib title">商城优惠：</li>
								 <li class="dib sel"><div class="J_ShopPromo J_Promotion promotion clearfix" data-point-url="http://log.mmstat.com/buy.1.16"></div></li>
								 <li class="dib fee">  <span class='price '>
								 -<em class="style-normal-bold-black J_ShopPromo_Result">0.00</em>
								  </span>
								 </li>
							</ul>
						 </div>
					
					 	 <div class="shoppointarea"></div>
					
						 <div class="farearea">
							 <ul class="dib-wrap J_farearea">
								 <li class="dib title">运送方式：</li>
								 <li class="dib sel" data-point-url="http://log.mmstat.com/jsclick?cache=*&tyxd=wlysfs">
								   <select name="1704508670:2|post" class="J_Fare" style="margin-top: 10px;">
								     <option data-fare="1500" value=" 1 " data-codServiceType="1" data-level="" selected="selected">
								 		平邮 免费 
								 	 </option>
								     <option data-fare="2500" value=" 7 " data-codServiceType="7" data-level=""  >
								 		EMS 25.00元 
								 	 </option>
								 	 <option data-fare="1500" value=" 2 " data-codServiceType="2" data-level="">
								 		空运 95.00元 
							 	 	 </option>
								   </select>
							 	   <em tabindex="0" class="J_FareFree" style="display: none">免邮费</em>
							     </li>
							 	 <li class="dib fee">  
							 	 	<span class='price '>
							 			<em class="style-normal-bold-red J_FareSum"  >30.00</em>
							  		</span>
						 		</li>
						 	 </ul>
					 	 </div>
						 
						 <div class="extra-area">
							 <ul class="dib-wrap">
								 <li class="dib title">发货时间：</li>
								 <li class="dib content">卖家承诺订单在买家付款后，72小时内<a href="#">发货</a></li>
							 </ul>
						 </div>
					   
					 	 <div class="servicearea" style="display: none"></div>
					 	 
					  </li>
				   </ul>
			    </td>
		    </tr>
		 
		
			<tr class="shop-total blue-line">
			 <td colspan="5">店铺合计(<span class="J_Exclude" style="display: none">不</span>含运费<span class="J_ServiceText" style="display: none">，服务费</span>)：
				 <span class='price g_price '>
				 <span>&yen;</span><em class="style-middle-bold-red J_ShopTotal">${requestScope.orderTotalPrice }</em>
				 </span>
				 <input type="hidden" name="1704508670:2|creditcard" value="false" />
				 <input type="hidden" id="J_IsLadderGroup" name="isLadderGroup" value="false"/>
			 </td>
			</tr>
		</tbody>
		
	  <tfoot>
	 	<tr>
	 		<td colspan="5">
	
			 <div class="order-go" data-spm="4">
			 <div class="J_AddressConfirm address-confirm">
			 <div class="kd-popup pop-back" style="margin-bottom: 40px;">
			 <div class="box">
			 <div class="bd">
			 <div class="point-in">
	   
	     	 <em class="t">实付款：</em>  
		     <span class='price g_price '>
		     	<span>&yen;</span>
		     	<em class="style-large-bold-red"  id="J_ActualFee">${requestScope.orderTotalPrice }</em>
		     </span>
		</div>
	
	 	<ul >
			 <li>
			 	<em>寄送至:</em>
			 	<span id="J_AddrConfirm" style="word-break: break-all;">
			  		  ${requestScope.address.address }
			   </span>
			 </li>
			 <li>
			 	<em>收货人:</em>
			 	<span id="J_AddrNameConfirm">
			 		${requestScope.address.receiver }   
			 		${requestScope.address.receiverPhone } </span>
			 </li>
	 	</ul>
	</div>
  </div>
	<a href="${pageContext.request.contextPath}/cart/showMyCarts" class="back J_MakePoint" target="_top" data-point-url="">返回购物车</a>
	<a id="J_Go" href="javascript:void(0);" class=" btn-go"  data-point-url=""  tabindex="0" title="点击此按钮，提交订单。" onclick="submitOrder()">提交订单<b class="dpl-button"></b></a>
</div>
</div>
	
	 <div class="J_confirmError confirm-error">
		 <div class="msg J_shopPointError" style="display: none;">
		 	<p class="error">积分点数必须为大于0的整数</p>
		 </div>
	 </div>
	
	
	 <div class="msg" style="clear: both;">
	 	<p class="tips naked" style="float:right;padding-right: 0">若价格变动，请在提交订单后联系卖家改价，并查看已买到的宝贝</p>
 	 </div>
 	 
</div>
</td>
</tr>
</tfoot>
</table>
</div>
  
</form>
</div>


<div id="footer"></div>


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

</div>

</body>
</html>
