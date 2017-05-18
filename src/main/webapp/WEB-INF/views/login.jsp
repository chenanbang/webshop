<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员登录</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xmlHttp.js">
</script>
<script type="text/javascript">

	onload=function(){
		if(document.getElementById("msg").innerHTML){
			alert(document.getElementById("msg").innerHTML);
		}
		if(document.getElementById("account").innerHTML){
			document.getElementById("username").value=document.getElementById("account").innerHTML;
		}
		if(document.getElementById("pwd").innerHTML){
			document.getElementById("password").value=document.getElementById("pwd").innerHTML;
		}

		if(document.getElementById("rememberMeResult").value=="true"){
			document.getElementById("showRememberMe").checked=true;
			document.getElementById("isRememberMe").value="true";
			//alert("true");
		}else{
			document.getElementById("showRememberMe").checked=false;
			//alert("我还是默认值");
		}

			
		
	}

	var result;

	function checkForm(){
		if(checkUsername(document.getElementById("username").value)==false){
			return false;
		}
		if(checkPassword(document.getElementById("password").value)==false){
			return false;
		}
		if(document.getElementById("captcha").value==null || document.getElementById("captcha").value==''){
			document.getElementById("span3").innerHTML=" 验证码不能为空!";
			return false;
		}else{
			if(document.getElementById("captcha").value.toLocaleLowerCase()!=document.getElementById("hideCode").value){
				document.getElementById("span3").innerHTML=" 验证码输入有误!";
				return false;
			}
		}
		
	}
	function checkUsername(username){
		//I.校验用户名是否为null或空字符串
		if(username == null || username == '' || username=='用户名/E-mail'){
			document.getElementById("span1").innerHTML="*用户名不能为空!";
			return false;
		}else{
			document.getElementById("span1").innerHTML="";
		}
	}

	function checkPassword(password) {
		if(password == null || password == ''){
			document.getElementById("span2").innerHTML="*密码不能为空!";
			return false;
		}else{
			document.getElementById("span2").innerHTML="";
		}
	}

	//对用户名进行校验
	function hiddenInfo(username){
		if(username.value=='用户名/E-mail'){
			username.value="";
			username.style.color='#000000';
			document.getElementById("span1").innerHTML='';
		}
	}
	
	function showInfo(username){
		if(username.value==null || username.value==''){
			username.style.color='#ccc';
			username.value="用户名/E-mail";
			document.getElementById("span1").innerHTML="*用户名不能为空!";
		}
	}

	//对密码进行校验
	function hidePasswordInfo(){
		document.getElementById("span2").innerHTML='';
	}
	function showPasswordInfo(password){
		if(password==null || password==''){
			document.getElementById("span2").innerHTML='*密码不能为空!';
		}
	}

	//获取验证码
	function getChangingAuthCode(authCodeObj){
		var img1 = document.getElementById("checkImg");
		img1.src="${pageContext.request.contextPath}/getAuthImage?time="+new Date().getTime();
		authCodeObj.value="";
		document.getElementById("span3").innerHTML=" 验证码不能为空,请输入!";
	}

	//校验验证码输入是否正确
	function checkAuthCode(authCode){
		if(authCode==null||authCode==''){
			document.getElementById("span3").innerHTML=" 验证码不能为空,请输入!";
			return false;
		}
		
		if(authCode.length==1){
			// 1.创建异步交互对象
			var xmlHttp = createXmlHttp();
			// 2.设置监听
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
					result = xmlHttp.responseText;
				}
			}
			// 3.打开连接
			xmlHttp.open("GET","${pageContext.request.contextPath}/getAuthCodeFromSession?time="+new Date().getTime(),false);
			// 4.发送
			xmlHttp.send();
		}
		if(authCode.toLocaleLowerCase()==result.substr(0,authCode.length)){
			if(authCode.length==4){
				document.getElementById("span3").innerHTML="";
				document.getElementById("hideCode").value=result;
				createElementImg("span3");
			}else{
				document.getElementById("span3").innerHTML='';
			}
		}else{
			document.getElementById("span3").innerHTML=" 验证码有误,请重新输入!";
		}
	}

	//用来创建correct的img图标
	function createElementImg(id){

		var parent=document.getElementById(id);
		//先将parent元素里面的img标签清空
		var removeObj=parent.getElementsByTagName("img")[0];
		//如果这个img对象存在的话,就先将其清掉
		if(removeObj){
			removeObj.parentNode.removeChild(removeObj);
		}
		//创建一个img对象
		var img=document.createElement("img");
		//为该img对象添加属性
		img.setAttribute("src", "${pageContext.request.contextPath}/image/r___________renleipic_01/correct.png");
		parent.appendChild(img);
	}

	
	var i=1;
	function rememberMe(){
		if(document.getElementById("rememberMeResult").value=="true"){
			if(i%2==0){
				document.getElementById("isRememberMe").value="true";
			}else{
				document.getElementById("isRememberMe").value="false";
			}
		}else{
			if(i%2==0){
				document.getElementById("isRememberMe").value="false";
			}else{
				document.getElementById("isRememberMe").value="true";
			}
		}
		i++;
		
	}
	
</script>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png" alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
		</div>	
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<%-- <c:if test="${user==null }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/showLoginPage">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/showRegisterPage">注册</a>|
					</li>
				</c:if>
				<c:if test="${user!=null }">
					<li id="headerUsername" class="headerUsername"></li>
					<li id="headerLogout" class="headerLogout">
						<a href="${pageContext.request.contextPath}/logout">[退出]</a>|
					</li>
				</c:if> --%>
				
				<c:if test="${user==null }">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/login">登录</a>|</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a href="${pageContext.request.contextPath}/showRegisterPage">注册</a>|
				</li>
			</c:if>
			<c:if test="${user!=null }">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					Hi,${user.username }	
				|</li>
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/ordre/myOrder">我的订单</a>
				|</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a href="${pageContext.request.contextPath}/logout">退出</a>|
				</li>
			</c:if>
				
				<li>
					<a href="${pageContext.request.contextPath}/userCenter">会员中心</a>
					|
				</li>
		
				<li>
					<a href="${pageContext.request.contextPath}/manageCenter">管理中心</a>
					|
				</li>
				<li>
					<a>关于我们</a>
					
				</li>
			</ul>
		</div>
		<div class="cart">
			<a href="${pageContext.request.contextPath}/cart/showMyCarts">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath}/index">首页</a>
						|
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/goods/goodsClassify/1/1">商品类别</a>
						|
					</li>
					<li>
						<a >安全频道</a>
						|
					</li>
					<li>
						<a >亿家卡</a>
						|
					</li>
					<li>
						<a >蔬菜基地</a>
						|
					</li>
					<li>
						<a >节气养生</a>
						|
					</li>
					<li>
						<a>便民服务</a>
						|
					</li>
					
		</ul>
	</div>
	
</div>	
<div class="container login">
	<div class="span12">
		<div class="ad">
			<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
		</div>		
	</div>
	<div class="span12 last">
		<div class="wrap">
			<div class="main">
				<div class="title">
					<strong>会员登录</strong>USER LOGIN 
					<span id="msg">${message }</span>
				</div>
				
				<%
					Cookie[] cookies=request.getCookies();
					if(cookies!=null){
						for(Cookie cookie : cookies){
							if("userInfo".equals(cookie.getName())){
								String res=URLDecoder.decode(cookie.getValue(), "UTF-8");
								String[] up=res.split(",");
								request.setAttribute("account", up[0]);
								request.setAttribute("pwd", up[1]);
								request.setAttribute("rememberMe", true);
							}
						}
					}
				%>
				<span style="display: none;" id="account">${requestScope.account }</span><span style="display: none;" id="pwd">${requestScope.pwd }</span>
				<form id="loginForm" action="${pageContext.request.contextPath }/login" method="post" novalidate="novalidate" onsubmit="return checkForm();">
					<table>
						<tbody><tr>
							<th>
									账&nbsp; &nbsp;号:
							</th>
							<td>
								<input type="text" id="username" style="color: #ccc;" name="username" class="text" maxlength="20" autocomplete="off" value="用户名/E-mail" 
									onfocus="hiddenInfo(this)" onblur="showInfo(this)" />
								<strong><span id="span1" style="color:#ff0000;"></span></strong>
							</td>
						</tr>
						<tr>
							<th>
								密&nbsp; &nbsp;码:
							</th>
							<td>
								<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"
									onfocus="hidePasswordInfo()" onblur="showPasswordInfo(this.value)"/>
								<strong><span id="span2" style="color:#ff0000;"></span></strong>
							</td>
						</tr>
							<tr>
								<th>
									验证码:
								</th>
								<td>
									<span class="fieldSet">
										<input type="text" style="width:60px;" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" onkeyup="checkAuthCode(this.value)"/><img id="checkImg" class="captchaImage" src="${pageContext.request.contextPath}/getAuthImage" onclick="getChangingAuthCode(this.previousSibling)" title="点我更换验证码"/><strong><span id="span3" style="color:#ff0000;"></span></strong>
										<input id="hideCode" type="hidden"/>
									</span>
								</td>
							</tr>
						<tr>
							<th>&nbsp;
								
							</th>
							<td>
								<label>
									<input id="showRememberMe" type="checkbox" name="isRememberUsername" value="false" onclick="rememberMe()">记住用户名和密码(一周)
								</label>
								<input style="display: none;" type="text" id="isRememberMe" name="isRememberMe" value="false"/>
								<input type="hidden" id="rememberMeResult" value="${requestScope.rememberMe }"/>
								<label>
									&nbsp; &nbsp; &nbsp;<a href="#">找回密码</a>
								</label>
							</td>
						</tr>
						<tr>
							<th>&nbsp;
								
							</th>
							<td>
								<input type="submit" class="submit" value="登 录">
							</td>
						</tr>
						<tr class="register">
							<th>&nbsp;
								
							</th>
							<td>
								<dl>
									<dt>还没有注册账号？</dt>
									<dd>
										立即注册即可体验在线购物！
										<a href="${pageContext.request.contextPath }/showRegisterPage">立即注册</a>
									</dd>
								</dl>
							</td>
						</tr>
					</tbody></table>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="container footer">
	<div class="span24">
	  <div class="footerAd"><img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势" /></div>	
	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>