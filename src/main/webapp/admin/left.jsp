<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理中心</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
		<div class="dtree" style="margin-left: 16px;">
		
			<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
			
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
			<script type="text/javascript">
				d = new dTree('d');
				d.add('01',-1,'管理中心');
				d.add('0101','01','用户管理','','','mainFrame');
				d.add('010101','0101','用户管理','${pageContext.request.contextPath}/user/userManage/1','','mainFrame');
				d.add('0102','01','商品类别管理','','','mainFrame');
				d.add('010201','0102','类别管理','${pageContext.request.contextPath}/user/jifenInfo','','mainFrame');
				d.add('010202','0102','添加类别','${pageContext.request.contextPath}/user/jifenInfo','','mainFrame');
				d.add('0104','01','商品管理','','','mainFrame');
				d.add('010401','0104','添加商品','${pageContext.request.contextPath}/goods/showAddPage','','mainFrame');
				d.add('010402','0104','商品管理','${pageContext.request.contextPath}/goods/showListPage/1','','mainFrame');
				d.add('0105','01','订单管理','','','mainFrame');
				d.add('010501','0105','所有订单','${pageContext.request.contextPath}/order/listOrders/1','','mainFrame');
				d.add('010502','0105','未付款订单','${pageContext.request.contextPath}/order/listIsOrNotPayOrders/1/0','','mainFrame');
				d.add('010503','0105','已付款订单','${pageContext.request.contextPath}/order/listIsOrNotPayOrders/1/1','','mainFrame');
				d.add('010504','0105','未发货订单','${pageContext.request.contextPath}/order/listIsOrNotSendOrders/1/0','','mainFrame');
				d.add('010505','0105','完成的订单','${pageContext.request.contextPath}/order/listIsOrNotSendOrders/1/1','','mainFrame');
				document.write(d);
			
			</script>
		</div>	
	</td>
  </tr>
</table>
</body>
</html>
