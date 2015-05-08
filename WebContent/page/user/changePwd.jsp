<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>修改用户密码</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<%-- <link rel="stylesheet" type="text/css"
	href="${ctx }/page/main/css/index.css">
 --%>
 <link rel="stylesheet" type="text/css"
	href="${ctx }/common/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx }/page/user/js/changepwd.js"></script>
<style type="text/css">
body {
	padding: 25px;
}
.title {
	width: auto;
	color: #479af5;
	text-align: center;
	margin: 0 auto;
}  
.changeform{
text-align: center;
width: auto;
margin: 0 auto;
}
</style>
</head>
<body>
	
		<div class="title"><h1>修改用户密码</h1></div>
	<div class="changeform">
	<form action="">
		<input type="hidden" name="type"
			value="<shiro:principal property="username" />" id="username">
		<input type="hidden" name="type"
			value="<shiro:principal property="type" />" id="type"> 旧密码：<input
			type="password" name="oldpwd" id="oldpwd" /><br>
			 新密码：<input
			type="password" name="newpwd" id="newpwd" /><br> 确认密码：<input
			type="password" name="checkpwd" id="checkpwd" /><span id="tip"
			style="display: none;">两次密码输入有误！</span><br> <input type="button"
			onclick="changepwd()" class="btn btn-primary" value="提交" />
	</form>
	</div>
</body>
</html>