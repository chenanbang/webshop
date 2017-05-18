<%@page import="com.cab.graduation.entities.dto.Goods"%>
<%@page import="com.cab.graduation.utils.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
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
   
   .pagingStyle{
		border:1px blue solid;
		padding: 0px 4px;
		color:blue;
		font-weight: 200px;
		margin: 2px 6px; 
	}
	.a{
		color: blue;
		margin:2px 3px;
	}
   
</style>
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
		</div>	
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:if test="${user==null }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/showLoginPage">登录</a>|</li>
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
					<a >关于我们</a>
					
				</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath}/cart/showMyCarts">查看购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>15726607618</strong>
			</div>
	</div>
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
						<a >蔬菜基地</a>
						|
					</li>
					<li>
						<a>节气养生</a>
						|
					</li>
					<li>
						<a >便民服务</a>
						|
					</li>
		</ul>
	</div>
	
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/goods/goodsClassify/3/1">蔬菜</a>
							</dt>
									<dd>
										<a href="#">无公害蔬菜</a>
									</dd>
									<dd>
										<a href="#">特菜类</a>
									</dd>
									<dd>
										<a href="#">有机蔬菜</a>
									</dd>
									<dd>
										<a href="#">蔬菜套餐</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/goods/goodsClassify/1/1">水果</a>
							</dt>
									<dd>
										<a href="#">国产</a>
									</dd>
									<dd>
										<a href="#">进口</a>
									</dd> 
						</dl>
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/goods/goodsClassify/2/1">肉类</a>
							</dt>
									<dd>
										<a href="#">猪肉</a>
									</dd>
									<dd>
										<a href="#">牛羊肉</a>
									</dd>
									<dd>
										<a href="#">家禽</a>
									</dd>
									<dd>
										<a href="#">鱼</a>
									</dd>
									<dd>
										<a href="#">虾</a>
									</dd>
									<dd>
										<a href="#">加工水产</a>
									</dd>
									<dd>
										<a href="#">其他水产</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a href="#">蛋、奶及豆制品类</a>
							</dt>
									<dd>
										<a href="#">蛋</a>
									</dd>
									<dd>
										<a href="#">奶</a>
									</dd>
									<dd>
										<a href="#">豆制品</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a href="#">干果</a>
							</dt>
									<dd>
										<a href="#">干制坚果</a>
									</dd>
									<dd>
										<a href="#">干制果实/果肉</a>
									</dd>
									<dd>
										<a href="#">干制种仁</a> 
									</dd>
						</dl>
						<dl>
							<dt>
								<a >谷薯杂粮</a>
							</dt>
									<dd>
										<a href="#">米类</a>
									</dd>
									<dd>
										<a href="#">杂粮</a>
									</dd>
									<dd>
										<a href="#">面粉</a>
									</dd>
									<dd>
										<a href="#">薯类</a>
									</dd>
									<dd>
										<a href="#">礼盒</a>
									</dd>
									<dd>
										<a href="#">干货</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a href="#">油</a>
							</dt>
							        <dd>
										<a href="#">茶油</a>
									</dd>
									<dd>
										<a href="#">核桃油</a>
									</dd>
									<dd>
										<a href="#">橄榄油</a>
									</dd>
									<dd>
										<a href="#">芥花籽油</a>
									</dd>
									<dd>
										<a href="#">玉米油</a>
									</dd>
									<dd>
										<a href="#">花生油</a>
									</dd>
									<dd>
										<a href="#">红花籽油</a>
									</dd>
									<dd>
										<a href="#">葡萄籽油</a>
									</dd>
									<dd>
										<a href="#">亚麻籽油</a>
									</dd>
									<dd>
										<a href="#">葵花仁油</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a href="#">水、软饮</a>
							</dt>
									<dd>
										<a href="#">水</a>
									</dd>
									<dd>
										<a href="#">软饮</a>
									</dd>
						</dl>
						<dl>
							<dt>
								<a href="#">茶</a> 
							</dt>
									<dd>
										<a href="#">绿茶</a>
									</dd>
									<dd>
										<a href="#">红茶</a>
									</dd>
									<dd>
										<a href="#">乌龙茶</a>
									</dd>
									<dd>
										<a href="#">白茶</a>
									</dd>
									<dd>
										<a href="#">黄茶</a>
									</dd>
									<dd>
										<a href="#">保健茶</a>
									</dd>
									<dd>
										<a href="#">黑茶</a>
									</dd>
						</dl>
						<dl class="last">
							<dt>
								<a href="#">商城卡</a>
							</dt>
									<dd>
										<a href="#">商城卡</a>
									</dd>
						</dl>
						<dl class="last">
							<dt>
								<a href="#">定制套餐</a>
							</dt>
									<dd>
										<a >2-3人套餐</a>
									</dd>
									<dd>
										<a>4-6人套餐</a>
									</dd>
									<dd>
										<a >1-2人套餐</a>
									</dd>
									<dd>
										<a>标准套餐</a>
									</dd>
									<dd>
										<a >乳母套餐</a>
									</dd>
									<dd>
										<a >营养师1对1服务</a>
									</dd>
									<dd>
										<a >儿童套餐</a>
									</dd>
									<dd>
										<a>高考套餐</a>
									</dd>
									<dd>
										<a >学生套餐</a>
									</dd>
									<dd>
										<a >护眼套餐</a>
									</dd>
									<dd>
										<a >世杯套餐</a>
									</dd>
						</dl>
						<dl class="last">
							<dt>
								<a >健康生活附属品</a>
							</dt>
									<dd>
										<a >空气净化器</a>
									</dd>
									<dd>
										<a >薰衣草</a>
									</dd>
						</dl>
			</div>
		</div>
		
		<div class="last">
			<c:forEach items="${requestScope.pageBean.items }" var="goods">
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
			<div style="width:700px; float: left; margin-top: 10px; text-align: center; border:1px gray solid; font-size: 16px;">
				<a class="a" href="${pageContext.request.contextPath }/goods/goodsClassify/${requestScope.classifyId }/1">首页</a>
				<c:if test="${pageBean.pageStartNum > 1}">
					<a class="a" href="${pageContext.request.contextPath }/goods/goodsClassify/${requestScope.classifyId }/${pageBean.pageStartNum-1 }">上一页</a>
				</c:if>
				<%
					Page<Goods> pageBean=(Page<Goods>)request.getAttribute("pageBean");
					Integer currentPageNum=pageBean.getPageStartNum();
					Integer totalPage=pageBean.getTotalPage();
					
					int begin=currentPageNum-4;
					int end=currentPageNum+4;
					if(begin<=1){
						begin=1;
					}
					if(end>totalPage){
						end=totalPage;
					}
					
					for(int i=begin;i<=end;i++){
						if(i==currentPageNum){
							out.print(i);
						}else{
							%><a href="${pageContext.request.contextPath }/goods/goodsClassify/${requestScope.classifyId }/<%=i %>" class="pagingStyle"><%=i %></a><%
						}
					}
				%>
				<c:if test="${pageBean.totalPage > pageBean.pageStartNum}">
					<a class="a" href="${pageContext.request.contextPath }/goods/goodsClassify/${requestScope.classifyId }/${pageBean.pageStartNum+1 }">下一页</a>
				</c:if>
				<c:if test="${pageBean.totalPage==0 }">
					<a class="a" href="${pageContext.request.contextPath }/goods/goodsClassify/${requestScope.classifyId }/1">尾页</a>
				</c:if>
				<c:if test="${pageBean.totalPage>0 }">
					<a class="a" href="${pageContext.request.contextPath }/goods/goodsClassify/${requestScope.classifyId }/${pageBean.totalPage}">尾页</a>
				</c:if>
			</div>
			
		</div>
	</div>
<br/><br/>
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
				<a >诚聘英才</a>
				|
			</li>
			<li>
				<a >法律声明</a>
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
				<a >官网</a>
				|
			</li>
			<li>
				<a >论坛</a>
				
			</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>