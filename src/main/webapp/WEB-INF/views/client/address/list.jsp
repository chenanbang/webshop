<%@page import="com.cab.graduation.entities.Address"%>
<%@page import="com.cab.graduation.utils.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>地址管理页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>
	
	<style type="text/css">
		#tb td{width:180px; height:30px; font-size: 14px; border-right: 1px green dashed;}
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
	
	<script type="text/javascript">
		function del(action){
			var deleteForm=document.getElementById("delete");

			//如果form 的action属性不指定的话,默认为当前页面过来是 的url;
			//alert(deleteForm.action);

			deleteForm.action=action;
			deleteForm.submit();
		}
		
	</script>
	
</head>
<body>

<form id="delete" style="hidden" action="" method="post">
	<input type="hidden" name="_method" value="DELETE"/>
</form>

<div class="container register" >
		<div class="span24" style="width: 1000px;">
			<div class="wrap" style="border: none; width: 1000px;">
				<div class="main clearfix" style="border: none; width: 960px;">
					<table style="text-align: center; width:960px;" id="tb">
						<thead>
							<tr style="border: none; ">
								<td colspan="7" style="border-right: none;padding-bottom: 9px; text-align: center; font-size: 16px;"><strong>地 址 管 理 中 心</strong></td>
							</tr>
							<tr style="font-size: 15px;">
								<td><strong>地址编号</strong></td>
								<td><strong>详细地址</strong></td>
								<td><strong>收件人</strong></td>
								<td><strong>手机号</strong></td>
								<td><strong>默认地址</strong></td>
								<td><strong>创建时间</strong></td>
								<td style="border-right: none;"><strong>操 作</strong></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.pageBean.items }" var="address">
								<tr>
									<td style="width: 120px;">${address.addrId }</td>
									<td style="width: 560px; text-align: left;">${address.address }</td>
									<td style="width:150px;">${address.receiver }</td>
									<td style="width:150px;">${address.receiverPhone }</td>
									<td style="width: 180px;">${address.defaultAddress }</td>
									<td style="width: 290px;">${address.createTime }</td>
									<td style="border-right: none;">
										<a class="a" href="${pageContext.request.contextPath }/address/update/${address.addrId }">修改</a>
										<a class="a" href="javascript:;" onclick="del('${pageContext.request.contextPath }/address/delete/${address.addrId }')">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="7">
									<a class="a" href="${pageContext.request.contextPath }/address/showListPage/1">首页</a>
									<c:if test="${pageBean.pageStartNum > 1}">
										<a class="a" href="${pageContext.request.contextPath }/address/showListPage/${pageBean.pageStartNum-1 }">上一页</a>
									</c:if>
									<%
										Page<Address> pageBean=(Page<Address>)request.getAttribute("pageBean");
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
												%><a href="${pageContext.request.contextPath }/address/showListPage/<%=i %>" class="pagingStyle"><%=i %></a><%
											}
										}
									%>
									<c:if test="${pageBean.totalPage > pageBean.pageStartNum}">
										<a class="a" href="${pageContext.request.contextPath }/address/showListPage/${pageBean.pageStartNum+1 }">下一页</a>
									</c:if>
									<c:if test="${pageBean.totalPage==0 }">
										<a class="a" href="${pageContext.request.contextPath }/address/showListPage/${pageBean.totalPage+1}">尾页</a>
									</c:if>
									<c:if test="${pageBean.totalPage!=0 }">
										<a class="a" href="${pageContext.request.contextPath }/address/showListPage/${pageBean.totalPage}">尾页</a>
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