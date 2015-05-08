<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="${ctx }/page/login/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/page/login/js/login.js"></script>
</head>
<body>
	<div id="login-box">
		<div class="login-top"></div>
		<div class="login-main">
			<form name="form" method="post" action="">
				<input type="hidden" name="gotopage" value="" /> <input
					type="hidden" name="dopost" value="login" /> <input
					name='adminstyle' type='hidden' value='newdedecms' />
				<dl>
					<dt></dt>
					<dd>
						<input type="text" class="big" name="username" id="username" />
					</dd>
					<dt></dt>
					<dd>
						<input type="password" class="big" class="alltxt" name="password"
							id="password" />
					</dd>
					<dt></dt>
					<dd>
						<!-- <input id="vdcode" type="text" name="validate" style="text-transform:uppercase;"/> -->
						<input id="vdcode" class="big2" type="text" name="validate"
							style="border: 1px solid #ccc;" /> <img id="vdimgck"
							align="absmiddle" onClick="this.src=this.src+'?'"
							style="cursor: pointer;" alt="点击换" src="${ctx }/user/createVali" />
						<a href="#" onClick="changeAuthCode();">看不清？ </a>
					</dd>
					<dt>&nbsp;</dt>
					<!-- <dd><button type="submit" name="sm1" class="login-btn" onclick="this.form.submit();">登录</button></dd> -->
					<dd>
						<div class="radioGroup">
							<input type="radio" class="typeRadio" name="type" value="2" />学生<input
								type="radio" class="typeRadio" name="type" value="1" />教师<input
								type="radio" name="type" value="0" checked="checked" />管理员
						</div>
						<button type="button" name="sm1" onclick="login()"
							class="login-btn">登录</button>

					</dd>
				</dl>

			</form>
		</div>
		<div class="login-power"></div>
	</div>
</body>
</html>