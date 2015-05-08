<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%Integer flag=Integer.parseInt(request.getParameter("flag")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业列表</title>
<%-- <link rel="stylesheet" type="text/css"
	href="${ctx }/common/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/common/css/bootstrap-responsive.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/common/js-plugins/easyui/themes/bootstrap/easyui.css">
<script type="text/javascript"
	src="${ctx }/common/js-plugins/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/common/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ctx }/common/js-plugins/easyui/jquery.easyui.min.js"></script> --%>
</head>
<body class="easyui-layout">
	<input type="hidden" name="flag" value="<%=flag %>" id="flag" />
	<input type="hidden" name="stuId" value="<shiro:principal property="username"/>" id="stuId" />
	<div data-options="region:'center',border:false" style="padding: 8px;">
		<table id="data"></table>
	</div>

	<%-- <table class="table table-striped">
		<thead>
			<tr>
				<td>序号</td>
				<td>作业名称</td>
				<td>发布时间</td>
				<td>课程</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${pageVo.rows }" var="homework" varStatus="i">
				<tr>
					<td>${homework.teaHwId }</td>
					<td>${homework.teaHwName }</td>
					<td>${homework.teaHwAskTime }</td>
					<td>${homework.course.courseName }</td>
					<td><c:choose>
							<c:when test="${homework.status == 2 }">
								<span>已公布答案</span>&nbsp;&nbsp;<a href="${ctx }/answer/showAnswer/${homework.teaHwId}">查看答案</a>
							</c:when>
							<c:otherwise>
								<span>未公布答案</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td><c:choose>
							<c:when test="${flag eq 'finished' }">
								<a href="${ctx }/homework/showMyHomework/${homework.teaHwId}/1">查看</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx }/homework/showHomework/${homework.teaHwId}">去完成</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>

		</tbody>
	</table> --%>
	<script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
	<script type="text/javascript" src="${ctx }/page/home/js/homeworkList.js"></script>
</body>
</html>