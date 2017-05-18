<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			BODY {
				MARGIN: 0px;
				BACKGROUND-COLOR: #ffffff
			}
			
			BODY {
				FONT-SIZE: 12px;
				COLOR: #000000
			}
			
			TD {
				FONT-SIZE: 12px;
				COLOR: #000000
			}
			
			TH {
				FONT-SIZE: 12px;
				COLOR: #000000
			}
		</style>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="20%">
					<img src="${pageContext.request.contextPath}/images/wanjiasong.png">
				</td>

				<td width="80%">
					<img alt="" src="${pageContext.request.contextPath}/images/top_100.jpg"/>
				</td>
			</tr>
		</table>
		<table width="100%" height="30%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30px" valign="bottom" background="${pageContext.request.contextPath}/images/mis_01.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="80%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#000000">
									<strong>
										<script language="JavaScript">
											var tmpDate = new Date();
											var date = tmpDate.getDate();
											var month= tmpDate.getMonth() + 1 ;
											var year= tmpDate.getFullYear();
											document.write(year);
											document.write("年");
											document.write(month);
											document.write("月");
											document.write(date);
											document.write("日 ");
											
											var myArray=new Array(6);
											myArray[0]="星期日"
											myArray[1]="星期一"
											myArray[2]="星期二"
											myArray[3]="星期三"
											myArray[4]="星期四"
											myArray[5]="星期五"
											myArray[6]="星期六"
											var weekday=tmpDate.getDay();
											if (weekday==0 | weekday==6){
												document.write(myArray[weekday]);
											}
											else{
												document.write(myArray[weekday]);
											}
											
										</script>
									</strong> 
								</font>
							</td>
							<td width="20%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="16"
											background="${pageContext.request.contextPath}/images/mis_05b.jpg">
											<img width="6px" height="18px" src="${pageContext.request.contextPath}/images/mis_05a.jpg"/>
										</td>
										<td width="155px" valign="bottom" style="text-align: center; font-size: 17px;"
											background="${pageContext.request.contextPath}/images/mis_05b.jpg">
											<strong>
												管理员:<font color="blue">${sessionScope.user.username }</font>
												&nbsp;<a style="font-size: 17px;" href="${pageContext.request.contextPath}/logout" target="_parent">[注销]</a>
											</strong>
										</td>
										<td width="10" align="right"
											background="${pageContext.request.contextPath}/images/mis_05b.jpg">
											<img src="${pageContext.request.contextPath}/images/mis_05c.jpg" width="6px" height="18px">
										</td>
									</tr>
								</table>
							</td>
							<td align="right" width="5%">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
