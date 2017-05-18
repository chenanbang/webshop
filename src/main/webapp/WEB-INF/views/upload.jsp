<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

${pageContext.request.contextPath }<br/>
	<form action="${pageContext.request.contextPath }/upload" enctype="multipart/form-data" method="post">
		请选择要上传的文件:<input type="file" name="file" /><br/>
			            用戶名:<input type="text" name="username"/><br/>
			                密碼:<input type="password" name="password"/><br/>
		&nbsp; &nbsp; &nbsp; &nbsp;<input type="submit" value="上传"/>
	</form>
</body>
</html>