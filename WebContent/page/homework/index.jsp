<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/homework/css/index.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="padding: 8px;">
		<table id="data"></table>
	</div>

	<div id="adddlg" class="easyui-dialog"
		style="padding: 5px; width: 400px; height: 300px;" iconCls="icon-ok"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">作业信息</div>

		<!-- 教师ID待补充 -->
		<input type="hidden" id="teaId" name="teaId" value="<shiro:principal property="username"/>" />
		<form id="fm" method="post">
			<input type="hidden" id="homeworkId" name="homeworkId" value="" />
			
			<div class="fitem">
				<label><span>*</span>作业名称：</label> <input name="teaHwName"
					class="easyui-validatebox" required="true" class="easyui-combobox"
					id="teaHwName">
			</div>
			<div class="fitem">
				<label><span>*</span>课程：</label> <select id="course" name="course"
					style="width: 150px;" url="${ctx}/course/findAllByTeaId/<shiro:principal property="username"/>"
					required="true">
					<option value="请选择" selected="selected">请选择</option>
				</select>
			</div>
			
			<div class="fitem" id="questionDiv">
				<a href="#" class="easyui-linkbutton" id="selectQuestion"
					onclick="testonline.homework.openWin()">选择题目</a>
			</div>
			
			<div class="fitem">
				<pre id="quelabel"></pre>
			</div>
			
			<div class="fitem" id="classDiv">
				<a href="#" class="easyui-linkbutton" id="selectQuestion1"
					onclick="testonline.homework.openClassWin()">选择班级</a>
			</div>
		</form>
	</div>

	<div id="win" class="easyui-window" title="选择题目" closed="true"
		style="width: 700px; height: 500px; padding: 5px;">
		<table id="dg"></table>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submitBtn"
			onclick="testonline.homework.addSubmit()">提交</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#adddlg').dialog('close')">取消</a>
	</div>

<!-- start toolbar -->
	<div id="toolbar" class="datagrid-toolbar">


		<div style="margin-bottom: 5px">
			<a href="javascript:void(0)" id="addbtn" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增作业</a>  <a
				href="javascript:void(0)" id="removebtn" class="easyui-linkbutton"
				onclick="testonline.homework.deleteSubmit()" iconCls="icon-remove"
				plain="true">删除选中项</a>
		</div>
		<div>
			关键字：<input type="text" name="name" id="keyword" class="mr5"
				placeholder="请输入关键字" maxlength="30" /> <a id="searchbtn"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search">查询</a> <a id="showAll"
				href="javascript:void(0)" class="easyui-linkbutton">显示全部记录</a>
		</div>
		<div style="clear: both"></div>
	</div>
	
	<div id="toolbar1" class="datagrid-toolbar">
		<div>
			关键字：<input type="text" name="name" id="keyword1" class="mr5"
				placeholder="请输入关键字" maxlength="30" /> <a id="searchbtn1"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search">查询</a> <a id="showAll1"
				href="javascript:void(0)" class="easyui-linkbutton">显示全部记录</a>
				<a id="submitSelect" style="float:right;"
				href="javascript:void(0)" class="easyui-linkbutton">确定选择</a>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
	<script type="text/javascript" src="${ctx }/page/homework/js/index.js"></script>
</body>
</html>