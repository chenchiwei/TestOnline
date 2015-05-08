<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="/WEB-INF/myfunction.tld" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业列表</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/common/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/home/css/showhomework.css">
</head>
<body>
	<input type="hidden" name="stuId" value="<shiro:principal property="username"/>" id="stuId" />
	<input type="hidden" name="tea_hw_id" value="${tea_hw_id }"
		id="tea_hw_id" />
		<div class="title"><h2>${homework.teaHwName }</h2></div>
	<ol>
		<c:forEach items="${list }" var="question" varStatus="i">
		
			

			<c:choose>
				<c:when test="${question.questionType==1 }">
					<li>${question.questionContent}(&nbsp;&nbsp;<span class="answer">${question.questionAnswer }</span>&nbsp;&nbsp;)</li>
					<ul>

						<c:forEach items="${question.options }" var="option" varStatus="j">

							<li>${option.optionContent }</li>


						</c:forEach>


					</ul>
				</c:when>

				<c:when test="${question.questionType==2 }">
				<li>${question.questionContent}(&nbsp;&nbsp;<span class="answer">${question.questionAnswer }</span>&nbsp;&nbsp;)</li>
					<ul>

						<c:forEach items="${question.options }" var="option" varStatus="j">

							<li>${option.optionContent }</li>

						</c:forEach>

					</ul>
				</c:when>
				<c:otherwise>
				<li>${question.questionContent}</li>
					<pre>${question.questionAnswer }</pre>
				</c:otherwise>

			</c:choose>
		<div class="line"></div>
		</c:forEach>
	</ol>
</body>
</html>