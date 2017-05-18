<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
	body{
		SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
	}
</style>

</head>

<frameset rows="105,*,100" frameborder=0 border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/client/top.jsp" name="topFrame" scrolling="NO" noresize>
  <frameset cols="179,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/client/left.jsp" name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/address/add" name="mainFrame">
  </frameset>
  <frame src="${pageContext.request.contextPath}/client/bottom.jsp" name="bottomFrame" scrolling="NO"  noresize>
</frameset>

</html>