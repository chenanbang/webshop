<%@page import="java.util.ArrayList"%>
<%@page import="com.cab.graduation.entities.User"%>
<%@page import="java.util.List"%>
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
	
	<script type="text/javascript">

		function userDelete(userId){
			var targetForm=document.getElementById("userDelete");
			targetForm.action="${pageContext.request.contextPath }/user/delete/"+userId;
			targetForm.submit();
		}
	
	</script>
	
</head>
<body>

	<form id="userDelete" action="" style="display: none" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>

	<div class="container register" style="width: 1100px;">
		<div class="span24" style="width: 1100px; border: none;">
			<div class="wrap" style="border: none; width: 1100px;">
				<div class="main clearfix" style="border: none; width: 1020px; margin: 0px auto; margin-top: 20px;">
					<table style="text-align: center; width:1020px; margin: 0px auto;" id="tb">
						<thead>
							<tr style="border: none; ">
								<td colspan="9" style="border-right: none;padding-bottom: 9px; text-align: center; font-size: 16px;"><strong>用 户 管 理 中 心</strong></td>
							</tr>
							<tr style="font-size: 15px;">
								<td style="width:90px;"><strong>用户名</strong></td>
								<td style="width:60px;"><strong>性别</strong></td>
								<td style="width:60px;"><strong>年龄</strong></td>
								<td style="width:156px;"><strong>邮 箱</strong></td>
								<td style="width:120px;"><strong>手机号</strong></td>
								<td style="width:210px;"><strong>地 址</strong></td>
								<td style="width:180px;"><strong>创建时间</strong></td>
								<td style="width:70px;"><strong>是否激活</strong></td>
								
								<td style="font-size: 14px;border-right: none; width:90px;"><strong>操 作</strong></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.pageBean.items }" var="user">
								<tr>
									<td>${user.username }</td>
									<td>
									<c:if test="${user.gender=='male' }">
										男
									</c:if>
									<c:if test="${user.gender=='female' }">
										女
									</c:if>
									</td>
									<td>${user.age }</td>
									<td>${user.email }</td>
									<td>${user.phone }</td>
									<td>${user.address }</td>
									<td>${user.registerTime }</td>
									<td style="">
										<c:if test="${user.state==1 }">
											是
										</c:if>
										<c:if test="${user.state==0 }">
											否
										</c:if>
									</td>
									<td style="border-right: none;">
										<a class="a" href="${pageContext.request.contextPath }/user/update/${user.userId }">修改</a>
										<a class="a" href="javascript:void(0);" onclick="userDelete(${user.userId })">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="9">
									<a class="a" href="${pageContext.request.contextPath }/user/userManage/1">首页</a>
									<c:if test="${pageBean.pageStartNum > 1}">
										<a class="a" href="${pageContext.request.contextPath }/user/userManage/${pageBean.pageStartNum-1 }">上一页</a>
									</c:if>
									<%
										Page<User> pageBean=(Page<User>)request.getAttribute("pageBean");
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
												%><a href="${pageContext.request.contextPath }/user/userManage/<%=i %>" class="pagingStyle"><%=i %></a><%
											}
										}
									%>
									<c:if test="${pageBean.totalPage > pageBean.pageStartNum}">
										<a class="a" href="${pageContext.request.contextPath }/user/userManage/${pageBean.pageStartNum+1 }">下一页</a>
									</c:if>
									
									<c:if test="${pageBean.totalPage==0}">
										<a class="a" href="${pageContext.request.contextPath }/user/userManage/${pageBean.totalPage+1}">尾页</a>
									</c:if>
									<c:if test="${pageBean.totalPage!=0}">
										<a class="a" href="${pageContext.request.contextPath }/user/userManage/${pageBean.totalPage}">尾页</a>
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