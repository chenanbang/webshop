<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员中心</title>
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
				d.add('01',-1,'会员中心');

				d.add('0101','01','信息中心','','','mainFrame');
				d.add('010101','0101','修改信息','${pageContext.request.contextPath }/user/update','','mainFrame');
				d.add('010102','0101','我的收藏','${pageContext.request.contextPath}/user/shouCangInfo','','mainFrame');
				d.add('010103','0101','我的积分','${pageContext.request.contextPath}/user/jifenInfo','','mainFrame');

				d.add('0102','01','收货地址管理','','','mainFrame');
				d.add('010201','0102','查看地址','${pageContext.request.contextPath}/address/showListPage/1','','mainFrame');
				d.add('010202','0102','添加地址','${pageContext.request.contextPath}/address/add','','mainFrame');

				d.add('0103','01','我的订单','','','mainFrame');
				d.add('010301','0103','已提交','${pageContext.request.contextPath}/order/listOrdersByUserId/1','','mainFrame');
				d.add('010302','0103','已发货','${pageContext.request.contextPath}/order/listIsOrNotSendOrdersByUserId/1/1','','mainFrame');
				d.add('010303','0103','未发货','${pageContext.request.contextPath}/order/listIsOrNotSendOrdersByUserId/1/0','','mainFrame');
				d.add('010304','0103','已付款','${pageContext.request.contextPath}/order/listIsOrNotPayOrdersByUserId/1/1','','mainFrame');
				d.add('010305','0103','未付款','${pageContext.request.contextPath}/order/listIsOrNotPayOrdersByUserId/1/0','','mainFrame');

				d.add('0104','01','我的评论','','','mainFrame');
				d.add('010401','0104','好评','${pageContext.request.contextPath}/user/jifenInfo','','mainFrame');
				d.add('010402','0104','差评','${pageContext.request.contextPath}/user/jifenInfo','','mainFrame');

				document.write(d);
				
			</script>
		</div>	
	</td>
  </tr>
</table>
</body>
</html>
