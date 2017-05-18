<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xmlHttp.js"></script>

<script type="text/javascript">


	$(function(){
		$("#testJson").click(function(){
			var url = this.href;
			var args = {};
			$.post(url, args, function(data){
				for(var i = 0; i < data.length; i++){
					var id = data[i].userId;
					var username = data[i].username;
					
					alert(id + ": " + username);
				}
			});
			return false;
		});
	})
	
	
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
	
</script>

</head>
<body>
	
	<a href="testJson" id="testJson">Test Json</a>
	<br><br>
	
	<form action="testHttpMessageConverter" method="POST" enctype="multipart/form-data">
		File: <input type="file" name="file"/>
		Desc: <input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>
	
	<br><br>

	<a href="javascript:void(0);" onclick="sendGoods(this,2)">
		发货
	</a>
	
	<br><br>
	
</body>
</html>