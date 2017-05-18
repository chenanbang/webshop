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
					<form:form action="${pageContext.request.contextPath }/goods/saveOrUpdate" method="post" enctype="multipart/form-data" modelAttribute="goods">
						<table style="width:500px;">
							<tbody>
								<!-- <tr>
									<td colspan="2" style="padding-left: 150px; padding-bottom:6px; font-size: 14px;">
										<strong>商品修改中心</strong>
									</td>
								</tr> -->
								<tr>
									<th>
										<span class="requiredField">*</span>商品名称:
									</th>
									<td>
										<form:hidden path="goodId"/><form:hidden path="datetime"/>
										<form:hidden path="goodPath"/><form:hidden path="goodDetailPath"/>
										<form:hidden path="goodSmallPath"/><form:hidden path="del"/>
										<form:input path="goodName" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品原价:
									</th>
									<td>
										<form:input path="goodPrimePrice" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品秒杀价:
									</th>
									<td>
										<form:input path="goodSpikePrice" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品图片:
									</th>
									<td>
										<input type="file" name="file" autocomplete="off" class="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品简述:
									</th>
									<td>
										<form:input path="goodSimpleDesc" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品详情:
									</th>
									<td>
										<form:input path="goodDescribe" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品库存:
									</th>
									<td>
										<form:input path="goodStore" autocomplete="off" cssClass="text"/>
										<strong><span id="span1" style="color:#ff0000;"></span></strong>
									</td>
								</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>商品类别:
									</th>
									<td>
										<form:select path="classify.id" cssStyle="width:90px;height: 27px;" cssClass="text">
											<c:forEach items="${classifyList }" var="classify">
										        <form:option value="${classify.id }">${classify.category }</form:option>
										    </c:forEach>
										</form:select>
										<%-- <select name="classifyId" style="width:90px;height: 27px;" class="text">
											<c:forEach items="${classifyList }" var="classify">
										        <option value="${classify.id }">${classify.category }</option>
										    </c:forEach>
									    </select> --%>
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
					</form:form>
					<div class="login" style="margin-right: 30px; margin-top: 20px;">
						<div class="ad">
							<dl>
								<dt>
									商品修改的规则及注意事项
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