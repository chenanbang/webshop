<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	欢迎来到首页！
	<br/>
	${session.existUser==null }
	
	<br/>
	
	<a href="${pageContext.request.contextPath }/executeRegister?username=zhangsan&password=123456&gender=male&age=18&email=zhangsan@126.com&phone=13027769629&address=henannanyang">go to register a user</a>
	<br/>
	
	<a href="${pageContext.request.contextPath }/login?username=zhangsan&password=123456">login in</a>
	<br/>
	
	<a href="${pageContext.request.contextPath }/logout">logout</a>
	
	<br/>
</body>
</html>