<%@page import="com.cab.graduation.entities.dto.Goods"%>
<%@page import="com.cab.graduation.utils.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xmlHttp.js">
	</script>
	
	<script type="text/javascript">

		function showCurrentPage(a ,currentPageNum){
			var url = "${pageContext.request.contextPath }/goods/showListPage/"+currentPageNum;
			a.href = url;
		}

		function sendGoods(send,orderId){
			// 1.创建异步交互对象
			var xmlHttp=createXmlHttp();
			// 2.设置监听
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					if(xmlHttp.status == 200){
						var data=xmlHttp.responseText;
						if(data==1){
							send.innerHTML = '已发货';
						}
					}
				}
			}
			// 3.打开连接
			xmlHttp.open("POST","${pageContext.request.contextPath}/order/updateOrderStatus/"+orderId,true);
			// 4.发送
			xmlHttp.send();
			
		}


		function paying(send,orderId){
			// 1.创建异步交互对象
			var xmlHttp=createXmlHttp();
			// 2.设置监听
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					if(xmlHttp.status == 200){
						var data=xmlHttp.responseText;
						if(data==1){
							send.innerHTML = '已付款';
						}
					}
				}
			}
			// 3.打开连接
			xmlHttp.open("POST","${pageContext.request.contextPath}/order/updateOrderPayState/"+orderId,true);
			// 4.发送
			xmlHttp.send();

		}

		function deleteOrder(orderId){
			var targetForm=document.getElementById("form");
			targetForm.action="${pageContext.request.contextPath }/order/delete/notPay/"+orderId;
			targetForm.submit();
		}

	</script>
	
	<style type="text/css">
		#tb td{height:30px; font-size: 14px; border-right: 1px green dashed;}
		#tb tr{border: 1px green solid}
		
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

	<form id="form" style="display: none;" action="" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>

	<div class="container register" style="width: 1180px;">
		<div class="span24" style="width: 1180px; border: none;">
			<div class="wrap" style="border: none; width: 1180px;">
				<div class="main clearfix" style="border: none; width: 1100px; margin: 0px auto; margin-top: 20px;">
					<table style="text-align: center; width:1100px; margin: 0px auto;" id="tb">
						<thead>
							<tr style="border: none; ">
								<td colspan="9" style="border-right: none;padding-bottom: 9px; text-align: center; font-size: 16px;"><strong>商 品 管 理 中 心</strong></td>
							</tr>
							<tr style="font-size: 15px;">
								<td style="width:200px;"><strong>订单号</strong></td>
								<td style="width:100px;"><strong>所属用户</strong></td>
								<td style="width:300px;"><strong>订单详情</strong></td>
								<td style="width:320px;"><strong>配送地址</strong></td>
								<td style="width:220px;"><strong>创建时间</strong></td>
								<td style="width:100px;"><strong>订单总价</strong></td>
								<td style="width:100px;"><strong>是否付款</strong></td>
								<td style="width:90px;"><strong>是否发货</strong></td>
								<td style="width:80px; border-right: none;"><strong>操 作</strong></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.pageBean.items }" var="order">
								<tr>
									<td>${order.orderNo }</td>
									<td>${order.user.username }</td>
									<td style=" text-align: left; padding-left: 3px;">
										<c:forEach items="${order.orderItems }" var="orderItem">
											<p style="padding: 3px 0px;">
												<!-- 商品名称、商品单价、商品数量、小计 -->
												<span>品名:${orderItem.goods.goodName }</span>&nbsp; 
												<span>单价:${orderItem.goods.goodSpikePrice }</span>&nbsp; 
												<span>数量:${orderItem.orderItemNum }</span>&nbsp; 
												<span>小计:${orderItem.orderItemSubtotal }</span>
											</p>
										</c:forEach>
									</td>
									<td style=" text-align: left; padding-left: 3px;">
										${order.address.address }&nbsp; (${order.address.receiver }收 &nbsp; ${order.address.receiverPhone })
									</td>
									<td>${order.orderCreateTime }</td>
									<td>￥${order.orderTotalPrice }</td>
									
									
									 <td>
										<c:if test="${sessionScope.user.rank==1 }">
											<c:if test="${order.isPay=='1' }">
												已付款
											</c:if>
											<c:if test="${order.isPay=='0' }">
												未付款
											</c:if>
										</c:if>
										
										<c:if test="${sessionScope.user.rank==0 }">
											<c:if test="${order.isPay=='1' }">
												已付款
											</c:if>
											<c:if test="${order.isPay=='0' }">
												<a style="color: blue;" href="javascript:void(0);" onclick="paying(this,${order.orderId})">
													<strong>付款</strong>
												</a>
											</c:if>
										</c:if>
										
									</td>
									
									<td>
										<c:if test="${sessionScope.user.rank==1 }">
											<c:if test="${order.orderStatus==1 }">
												已发货
											</c:if>
											<c:if test="${order.isPay=='1' }">
												<c:if test="${order.orderStatus==0 }">
													<a style="color: blue;" href="javascript:void(0);" onclick="sendGoods(this,${order.orderId})">
														<strong>发货</strong>
													</a>
												</c:if>
											</c:if>
											<c:if test="${order.isPay=='0' }">
												<strong style="color: blue;">发货</strong>
											</c:if>
										</c:if>
										
										<c:if test="${sessionScope.user.rank==0 }">
											<c:if test="${order.orderStatus==1 }">
												已发货
											</c:if>
											<c:if test="${order.orderStatus==0 }">
												未发货
											</c:if>
										</c:if>
									</td>
									
									<td style="border-right: none;">
										<a class="a" href="javascript:void(0);" onclick="deleteOrder(${order.orderId})">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="9">
									<a class="a" href="${pageContext.request.contextPath }/order/listIsOrNotPayOrdersByUserId/1/0">首页</a>
									<c:if test="${pageBean.pageStartNum > 1}">
										<a class="a" href="${pageContext.request.contextPath }/order/listIsOrNotPayOrdersByUserId/${pageBean.pageStartNum-1 }/0">上一页</a>
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
												%><a href="${pageContext.request.contextPath }/order/listIsOrNotPayOrdersByUserId/<%=i %>/0" class="pagingStyle"><%=i %></a><%
											}
										}
									%>
									<c:if test="${pageBean.totalPage > pageBean.pageStartNum}">
										<a class="a" href="${pageContext.request.contextPath }/order/listIsOrNotPayOrdersByUserId/${pageBean.pageStartNum+1 }/0">下一页</a>
									</c:if>
									
									<c:if test="${pageBean.totalPage==0 }">
										<a class="a" href="${pageContext.request.contextPath }/order/listIsOrNotPayOrdersByUserId/${pageBean.totalPage+1 }/0">尾页</a>
									</c:if>
									
									<c:if test="${pageBean.totalPage!=0 }">
										<a class="a" href="${pageContext.request.contextPath }/order/listIsOrNotPayOrdersByUserId/${pageBean.totalPage }/0">尾页</a>
									</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>