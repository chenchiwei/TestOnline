<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<% Integer teaHwId=Integer.parseInt(request.getParameter("teaHwId")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业完成情况</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/homework/css/index.css">

 <script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
</head>
<body class="easyui-layout">
	<input type="hidden" name="teaHwId" id="teaHwId" value="<%=teaHwId %>" />
	 <div id="main" style="height:400px;"></div>
    
    <script src="${ctx }/common/echarts/echarts.js"></script>
	<script type="text/javascript" src="${ctx }/page/homework/js/showStatistics.js"></script>
</body>
</html>