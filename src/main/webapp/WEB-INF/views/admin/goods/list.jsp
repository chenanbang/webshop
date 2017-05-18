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
	
	<script type="text/javascript">

		function showCurrentPage(a ,currentPageNum){
			var url = "${pageContext.request.contextPath }/goods/showListPage/"+currentPageNum;
			a.href = url;
		}
		
	</script>
	
	<style type="text/css">
		#tb td{width:190px; height:30px; font-size: 14px; border-right: 1px green dashed;}
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

	<div class="container register" >
		<div class="span24" style="width: 1000px;">
			<div class="wrap" style="border: none; width: 1000px;">
				<div class="main clearfix" style="border: none; width: 960px;">
					<table style="text-align: center; width:960px;" id="tb">
						<thead>
							<tr style="border: none; ">
								<td colspan="9" style="border-right: none;padding-bottom: 9px; text-align: center; font-size: 16px;"><strong>商 品 管 理 中 心</strong></td>
							</tr>
							<tr style="font-size: 15px;">
								<td><strong>商品编号</strong></td>
								<td><strong>商品名称</strong></td>
								<td><strong>商品类别</strong></td>
								<td><strong>商品原价</strong></td>
								<td><strong>商品秒杀价</strong></td>
								<td><strong>商品库存</strong></td>
								<td><strong>商品简介</strong></td>
								<td><strong>创建时间</strong></td>
								<td style="border-right: none;"><strong>操 作</strong></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.pageBean.items }" var="goods">
								<tr>
									<td>${goods.goodId }</td>
									<td>${goods.goodName }</td>
									<td>${goods.classifyName }</td>
									<td>${goods.goodPrimePrice }</td>
									<td>${goods.goodSpikePrice }</td>
									<td>${goods.goodStore }</td>
									<td style="width:280px;">${goods.goodDescribe }</td>
									<td style="width:320px;">${goods.datetime }</td>
									<td style="border-right: none;">
										<a class="a" href="${pageContext.request.contextPath }/goods/showGoodsEdit/${goods.goodId }">修改</a>
										<a class="a" href="${pageContext.request.contextPath }/goods/executeDelete/${goods.goodId }">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="9">
									<a class="a" href="${pageContext.request.contextPath }/goods/showListPage/1">首页</a>
									<c:if test="${pageBean.pageStartNum > 1}">
										<a class="a" href="${pageContext.request.contextPath }/goods/showListPage/${pageBean.pageStartNum-1 }">上一页</a>
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
												%><a href="${pageContext.request.contextPath }/goods/showListPage/<%=i %>" class="pagingStyle"><%=i %></a><%
											}
										}
									%>
									<c:if test="${pageBean.totalPage > pageBean.pageStartNum}">
										<a class="a" href="${pageContext.request.contextPath }/goods/showListPage/${pageBean.pageStartNum+1 }">下一页</a>
									</c:if>
									<a class="a" href="${pageContext.request.contextPath }/goods/showListPage/${pageBean.totalPage}">尾页</a>
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