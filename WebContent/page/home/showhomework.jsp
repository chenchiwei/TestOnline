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
<script type="text/javascript"
	src="${ctx }/common/js/utils.js"></script>
<script type="text/javascript"
	src="${ctx }/page/home/js/showhomework.js"></script>
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
				<li>${question.questionContent}</li>
					<ol>
						<c:choose>
							<c:when test="${flag eq 'edit' }">
								<c:forEach items="${question.options }" var="option"
									varStatus="j">
									<li><input type="radio" name="${question.questionId}"
										value="${option.optionId }" />${option.optionContent }</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${question.options }" var="option"
									varStatus="j">
									<c:choose>
										<c:when
											test="${option.optionId+'' eq question.answer.answerContent }">
											<li><input type="radio" name="${question.questionId}"
												value="${option.optionId }" checked="checked"
												disabled="disabled" />${option.optionContent }</li>
										</c:when>
										<c:otherwise>
											<li><input type="radio" name="${question.questionId}"
												value="${option.optionId }" disabled="disabled" />${option.optionContent }</li>
										</c:otherwise>
									</c:choose>

								</c:forEach>
							</c:otherwise>
						</c:choose>

					</ol>
				</c:when>

				<c:when test="${question.questionType==2 }">
				<li>${question.questionContent}</li>
					<ol>
						<c:choose>
							<c:when test="${flag eq 'edit' }">
								<c:forEach items="${question.options }" var="option"
									varStatus="j">

									<li><input type="checkbox" name="${question.questionId}"
										value="${option.optionId }" />${option.optionContent }</li>
								</c:forEach>

							</c:when>
							<c:otherwise>
								<c:forEach items="${question.options }" var="option"
									varStatus="j">
									<c:choose>
										<c:when
											test="${myfn:isCheckBoxAnswer(question.answers,option.optionId+'') }">
											<li><input type="checkbox" name="${question.questionId}"
												value="${option.optionId }" checked="checked"
												disabled="disabled" />${option.optionContent }</li>
										</c:when>
										<c:otherwise>
											<li><input type="checkbox" name="${question.questionId}"
												value="${option.optionId }" disabled="disabled" />${option.optionContent }</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</ol>
				</c:when>
				<c:otherwise>
				<li>${question.questionContent}</li>
					<c:choose>
						<c:when test="${flag eq 'edit' }">
							<textarea name="${question.questionId}"></textarea>
						</c:when>
						<c:otherwise><pre>${question.answer.answerContent}</pre>
							<%-- <textarea name="${question.questionId}" disabled="disabled">${question.answer.answerContent}</textarea> --%>
						</c:otherwise>
					</c:choose>

				</c:otherwise>
			</c:choose>
			<div class="line"></div>
		</c:forEach>
	</ol>
	<c:choose>
		<c:when test="${flag eq 'edit' }">
			<button class="btn btn-primary" type="button" id="submitBtn">提交</button>
		</c:when>
		<c:otherwise>
			<div style="padding:10px;width: 200px; height: 50px; border: 1px solid #ccc;"
				id="score">分数：<h3><span class="scoreNum">${score }</span></h3></div>
		</c:otherwise>
	</c:choose>

</body>
</html>