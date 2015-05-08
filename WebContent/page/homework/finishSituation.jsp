<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<% Integer classId=Integer.parseInt(request.getParameter("classId"));
	Integer teaHwId=Integer.parseInt(request.getParameter("teaHwId"));
	Integer isNotFinished=Integer.parseInt(request.getParameter("isNotFinished"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业完成情况</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/homework/css/index.css">
</head>
<body class="easyui-layout">
	<input type="hidden" name="teaHwId" value="<%=teaHwId %>" id="teaHwId" />
	<input type="hidden" name="classId" value="<%=classId %>" id="classId" />
	<input type="hidden" name="isNotFinished" value="<%=isNotFinished %>" id="isNotFinished" />
	
	<div data-options="region:'center',border:false" style="padding: 8px;">
		<table id="data"></table>
	</div>

	<script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
	<script type="text/javascript" src="${ctx }/page/homework/js/finishSituation.js"></script>
</body>
</html>