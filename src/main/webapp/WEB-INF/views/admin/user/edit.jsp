<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>
	
</head>
<body>

	<div class="container register" >
		<div class="span24" >
			<div class="wrap" style="border: none;">
				<div class="main clearfix" style="border: none;">
					
					
					<%-- <form action="${pageContext.request.contextPath }/user/update" method="post">
						<input type="hidden" name="_method" value="PUT"/>
						<table style="width:500px;">
							<tbody>
								<!-- <tr>
									<td colspan="2" style="padding-left: 150px; padding-bottom:6px; font-size: 14px;">
										<strong>信息修改中心</strong>
									</td>
								</tr> -->
								
								<input type="hidden" name="userId" value="${requestScope.user.userId }"/>
								
								<input type="hidden" name="password" value="${requestScope.user.password }"/>
								
								<input type="hidden" name="activeCode" value="${requestScope.user.activeCode }"/>
								
								<!-- 类型转换时容易出现问题 -->
								<input type="hidden" name="registerTime" value="${requestScope.user.registerTime }"/>
								
								<input type="hidden" name="rank" value="${requestScope.user.rank }"/>
								
								
								<tr>
									<th>
										<span class="requiredField">*</span>用户名:
									</th>
									<td>
										<input type="text" readonly="readonly" name="username" value="${requestScope.user.username }" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>性别:
									</th>
									<td>
										<select name="gender" class="text">
											<c:if test="${requestScope.user.gender=='male' }">
												<option value="male" selected="selected">男</option>
											</c:if>
											<c:if test="${requestScope.user.gender!='male' }">
												<option value="male">男</option>
											</c:if>
											<c:if test="${requestScope.user.gender=='female' }">
												<option value="female" selected="selected">女</option>
											</c:if>
											<c:if test="${requestScope.user.gender!='female' }">
												<option value="female">女</option>
											</c:if>
										</select>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>年龄:
									</th>
									<td>
										<input type="text" name="age" value="${requestScope.user.age }" autocomplete="off"  class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>邮箱:
									</th>
									<td>
										<input type="text" name="email" value="${requestScope.user.email }" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>手机号:
									</th>
									<td>
										<input type="text" name="phone" value="${requestScope.user.phone }" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>地址:
									</th>
									<td>
										<input type="text" name="address" value="${requestScope.user.address }" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>是否激活:
									</th>
									<td>
										<select name="state" class="text">
											<c:if test="${requestScope.user.state==1 }">
												<option value="1" selected="selected">是</option>	
											</c:if>										
											<c:if test="${requestScope.user.state!=1 }">
												<option value="1">是</option>	
											</c:if>										
											<c:if test="${requestScope.user.state==0 }">
												<option value="0" selected="selected">否</option>
											</c:if>
											<c:if test="${requestScope.user.state!=0 }">
												<option value="0">否</option>
											</c:if>
										</select>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								
								<tr>
									<th>
										&nbsp;
									</th>
									<td>
										<input type="submit" class="submit" value="立 即 修 改"/>
									</td>
								</tr>
							</tbody>
						</table>
					
					</form> --%>
					
					<form:form action="${pageContext.request.contextPath }/user/update" method="post" modelAttribute="user">
						
						<input type="hidden" name="_method" value="PUT"/>
					
						<table style="width:500px;">
							<tbody>
								<tr>
									<td colspan="2" style="padding-left: 150px; padding-bottom:6px; font-size: 14px;">
										<strong>信息修改中心</strong>
									</td>
								</tr>
								
								<form:hidden path="userId"/>
								<form:hidden path="username"/>
								<form:hidden path="password"/>
								<form:hidden path="activeCode"/>
								<form:hidden path="registerTime"/>
								<form:hidden path="rank"/>
								
								<tr>
									<th>
										<span class="requiredField">*</span>用户名:
									</th>
									<td>
										<input type="text" disabled="disabled" name="username" value="${requestScope.user.username }" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>性别:
									</th>
									<td>
										<form:select path="gender" cssClass="text">
											<form:option value="male">男</form:option>
											<form:option value="female">女</form:option>
										</form:select>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>年龄:
									</th>
									<td>
										<form:input path="age" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>邮箱:
									</th>
									<td>
										<form:input path="email" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>手机号:
									</th>
									<td>
										<form:input path="phone" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>地址:
									</th>
									<td>
										<form:input path="address" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<c:if test="${sessionScope.user.rank==1 }">
									<tr>
										<th>
											<span class="requiredField">*</span>是否激活:
										</th>
										<td>
											<form:select path="state" cssClass="text">
												<form:option value="1">是</form:option>
												<form:option value="0">否</form:option>
											</form:select>
											<strong><span id="span1" style="color:#ff0000;"></span></strong>
										</td>
									</tr>
								</c:if>
								<c:if test="${sessionScope.user.rank!=1 }">
									<tr style="display: none;">
										<th>
											<span class="requiredField">*</span>是否激活:
										</th>
										<td>
											<form:select path="state" cssClass="text">
												<form:option value="1">是</form:option>
												<form:option value="0">否</form:option>
											</form:select>
											<strong><span id="span1" style="color:#ff0000;"></span></strong>
										</td>
									</tr>
								</c:if>
								
								<tr>
									<th>
										&nbsp;
									</th>
									<td>
										<input type="submit" class="submit" value="立 即 修 改"/>
									</td>
								</tr>
							</tbody>
						</table>
					</form:form> 
					
					
					<div class="login" style="margin-right: 30px; margin-top: 20px;">
						<div class="ad">
							<dl>
								<dt>
									用户修改的规则及注意事项
								</dt>
								<dd>
									1、商品的分类<br/>
									2、产品相关属性（如货号、规格等等）的填写
								</dd>
								
							</dl>
						</div>							
						<dl>
							<dt>内容描述主要从下面几点来填写：</dt>
							<dd>
								1、产品焦点图片（突出卖点、吸引眼球）<br/>
								2、产品卖点、优点、特点 <br/>
								3、表格化产品相关参数<br/>
								4、品牌文化简介（让买家觉得品牌质量可靠）<br/>
								5、同类产品比较（以自己产品的强比弱）
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>