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
					
					<form action="${pageContext.request.contextPath }/address/add" method="post">
						<table style="width:500px;">
							<tbody>
								<tr>
									<td colspan="2" style="padding-left: 150px; padding-bottom:6px; font-size: 14px;">
										<strong>收货地址修改中心</strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>详细地址:
									</th>
									<td>
										<input type="text" name="address" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>收件人:
									</th>
									<td>
										<input type="text" name="receiver" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								
								<tr>
									<th>
										<span class="requiredField">*</span>手机号:
									</th>
									<td>
										<input name="receiverPhone" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								
								<tr>
									<th>
										&nbsp;
									</th>
									<td>
										<input type="submit" class="submit" value="立 即 添 加"/>
									</td>
								</tr>
							</tbody>
						</table>
						
					</form>
					
					<div class="login" style="margin-right: 30px; margin-top: 20px;">
						<div class="ad">
							<dl>
								<dt>
									地址修改的规则及注意事项
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