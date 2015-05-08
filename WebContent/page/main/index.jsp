<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>在线作业系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/main/css/index.css">

<style type="text/css">
.indexPic {
	margin: 50px 0 0 300px;
}
</style>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" class="head"
		style="height: 60px;">
		<div class="titleTop"></div>
		<!-- <div class="indexhead">
			<div class="banner">
				<div class="container">
				
					<div class="logo fl">
					<h1>在线作业系统</h1>
						
					</div> 
					<div class="welcomeinfo fr">
				
						<div class="maininfo">
							<a href="files/operator.doc">系统操作说明</a>
							<span>欢迎您！</span>
							<span class="username">
								XXX<span>&nbsp;&nbsp;</span>
							
							</span>
							<a href="javascript:void(0)" class="newmessage none" id="msg">消息</a>
							<span class="divide">|</span>
							<a href="login/logout">安全退出</a>
						</div>
						<div class="time" id="time">
						</div>
					</div>
				</div>
			</div>
		</div> -->
		<div class="userInfo">
			<shiro:authenticated>
				<span><shiro:principal property="name" /> ,欢迎您！<span
					class="heightline">|</span><a href="#" onclick="logout()">退出</a></span>
			</shiro:authenticated>
		</div>
	</div>

	<div data-options="region:'west',title:'导航菜单',split:true"
		style="width: 160px;">
		<!-- 菜单开始 -->
		<div id="aa" class="easyui-accordion"
			data-options="border:false,fit:true">
			<shiro:hasPermission name="学生管理">
				<shiro:hasPermission name="学校管理">
					<shiro:hasPermission name="学院管理">
						<shiro:hasPermission name="专业管理">
							<shiro:hasPermission name="班级管理">
								<shiro:hasPermission name="教师管理">
									<shiro:hasPermission name="课程管理">
										<shiro:hasPermission name="管理员题库管理">
											<div title="数据管理"
												data-options="iconCls:'icon icon-set2',selected:false"
												style="overflow: auto; padding: 10px 20px;">
												<ul id="basicMenu" class="easyui-tree">

													<shiro:hasPermission name="学校管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>学校管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('学校管理','${ctx }/page/school/index.jsp')">学校管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

													<shiro:hasPermission name="学院管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>学院管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('学院管理','${ctx }/page/academy/index.jsp')">学院管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

													<shiro:hasPermission name="专业管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>专业管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('专业管理','${ctx }/page/major/index.jsp')">专业管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

													<shiro:hasPermission name="班级管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>班级管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('班级管理','${ctx }/page/class/index.jsp')">班级管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

													<shiro:hasPermission name="教师管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>教师管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('教师管理','${ctx }/page/teacher/index.jsp')">教师管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

													<shiro:hasPermission name="学生管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>学生管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('学生管理','${ctx }/page/student/index.jsp')">学生管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>


													<shiro:hasPermission name="课程管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>课程管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('课程管理','${ctx }/page/course/index.jsp')">课程管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

													<shiro:hasPermission name="管理员题库管理">
														<li
															data-options="iconCls:'icon icon-class',state:'closed'">
															<span>题库管理</span>
															<ul>
																<li data-options="iconCls:'icon icon-nav'"><a
																	href="#"
																	onclick="addTab('题库管理','${ctx }/page/question/index.jsp')">题库管理</a>
																</li>
															</ul>
														</li>
													</shiro:hasPermission>

												</ul>
											</div>
										</shiro:hasPermission>
									</shiro:hasPermission>
								</shiro:hasPermission>
							</shiro:hasPermission>
						</shiro:hasPermission>
					</shiro:hasPermission>
				</shiro:hasPermission>
			</shiro:hasPermission>

			<shiro:hasPermission name="作业模块">
				<div title="作业管理"
					data-options="iconCls:'icon icon-set2',selected:true"
					style="overflow: auto; padding: 10px 20px;">
					<ul id="basicMenu" class="easyui-tree">
						<shiro:hasPermission name="题目管理">
							<li data-options="iconCls:'icon icon-class',state:'closed'">
								<span>题库管理</span>
								<ul>
									<li data-options="iconCls:'icon icon-nav'"><a href="#"
										onclick="addTab('题库管理','${ctx }/page/question/index.jsp')">题库管理</a>
									</li>
								</ul>
							</li>
						</shiro:hasPermission>
						<li data-options="iconCls:'icon icon-class',state:'closed'">
							<span>作业管理</span> <shiro:hasPermission name="作业管理">
								<ul>
									<li data-options="iconCls:'icon icon-nav'"><a href="#"
										onclick="addTab('作业管理','${ctx }/page/homework/index.jsp')">作业管理</a>
									</li>
								</ul>
							</shiro:hasPermission> <shiro:hasPermission name="查看未完成作业">
								<ul>
									<li data-options="iconCls:'icon icon-nav'"><a href="#"
										onclick="addTab('未完成的作业','${ctx }/page/home/homeworkList.jsp?flag=1')">未完成作业</a>
									</li>
								</ul>
							</shiro:hasPermission> <shiro:hasPermission name="查看已完成作业">
								<ul>
									<li data-options="iconCls:'icon icon-nav'"><a href="#"
										onclick="addTab('已完成的作业','${ctx }/page/home/homeworkList.jsp?flag=0')">已完成作业</a>
									</li>
								</ul>
							</shiro:hasPermission>

						</li>
					</ul>
				</div>
			</shiro:hasPermission>

			<shiro:authenticated>
				<div title="用户设置"
					data-options="iconCls:'icon icon-set2',selected:true"
					style="overflow: auto; padding: 10px 20px;">
					<ul id="basicMenu" class="easyui-tree">

						<li data-options="iconCls:'icon icon-class',state:'closed'">
							<span>用户设置</span>
							<ul>
								<li data-options="iconCls:'icon icon-nav'"><a href="#"
									onclick="addTab('修改密码','${ctx }/page/user/changePwd.jsp')">修改密码</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</shiro:authenticated>

		</div>
		<!-- 菜单结束 -->

	</div>
	<div data-options="region:'center',border:true,minHeight:500">

		<div class="easyui-tabs" id="tt" data-options="fit:true">
			<div title="首页" style="padding: 20px;">
				<div class="hometitle">
					<h1>欢迎使用在线作业系统</h1>
				</div>
				<div id="main"></div>

			</div>
		</div>
	</div>
	<div data-options="region:'south',border:true"
		style="height: 30px; padding: 2px;">
		<div class="copyrightInfo">版权信息</div>
	</div>

	<script type="text/javascript" src="${ctx }/page/main/js/index.js"></script>
</body>
</html>