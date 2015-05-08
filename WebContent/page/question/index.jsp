<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/question/css/index.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="padding: 8px;">
		<table id="data"></table>
	</div>

	<!-- start toolbar -->
	<div id="toolbar" class="datagrid-toolbar">


		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" id="addbtn" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增试题</a> 
				<a href="javascript:void(0)" id="editbtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑试题</a>
				<a
				href="javascript:void(0)" id="removebtn" class="easyui-linkbutton"
				onclick="testonline.question.deleteSubmit()" iconCls="icon-remove"
				plain="true">删除选中项</a> 
			<a href="javascript:void(0)" id="importbtn" class="easyui-linkbutton"
				plain="true">导入试题</a>
		</div>
		<div>关键字：<input type="text" name="name"
				id="keyword" class="mr5" placeholder="请输入关键字" maxlength="30" />
			
			<a id="searchbtn" href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search">查询</a> 
			<a id="showAll"
				href="javascript:void(0)" class="easyui-linkbutton">显示全部记录</a>
		</div>
		<div style="clear: both"></div>
	</div>

	<div id="adddlg" class="easyui-dialog"
		style="padding: 5px; width: 400px; height: 300px;" iconCls="icon-ok"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">试题信息</div>
		<form id="fm" method="post">
			<input type="hidden" id="questionId" name="questionId" value="" />
			<div class="fitem">
				<label><span>*</span>试题内容：</label> <input name="questionContent"
					class="easyui-validatebox" required="true" class="easyui-combobox"
					id="questionContent">
			</div>
			
			<div class="fitem">
				<label>类型：</label> <select id="questionType" name="questionType" required="true">
					<option value="请选择" selected="selected">请选择</option>
					<option value="1">单选</option>
					<option value="2">多选</option>
					<option value="3">大题</option>

				</select>

			</div>
			
			<div class="fitem">
				<label>难度：</label> <select id="questionLevel" name="questionLevel" required="true">
					<option value="请选择" selected="selected">请选择</option>
					<option value="1">简单</option>
					<option value="2">中等</option>
					<option value="3">困难</option>

				</select>

			</div>
			
		</form>
	</div>
	
	<div id="importdlg" class="easyui-dialog"
		style="padding: 5px; width: 400px; height: 300px;" iconCls="icon-ok"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">试题信息</div>
		<form id="importfm" method="post">
			<div class="fitem">
				<label><span>*</span>课程：</label> 
				<shiro:hasPermission name="管理员题库管理">
				<select id="course" name="course"
					style="width: 150px;" url="${ctx}/course/findAll<shiro:principal property="username" />"
					required="true">
					<option value="请选择" selected="selected">请选择</option>
				</select>
				</shiro:hasPermission>
				<shiro:hasPermission name="题库管理">
				<select id="course" name="course"
					style="width: 150px;" url="${ctx}/course/findAllByTeaId/<shiro:principal property="username" />"
					required="true">
					<option value="请选择" selected="selected">请选择</option>
				</select>
				</shiro:hasPermission>
			</div>
			<div class="fitem">
				<label><span>*</span>选择文件(.xls)：</label><br> <input
					type="file" id="file" name="file" class="easyui-validatebox" required="true">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submitBtn"
			onclick="testonline.question.addSubmit()">提交</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#adddlg').dialog('close')">取消</a>
	</div>


	<script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
	<script type="text/javascript" src="${ctx }/common/js-plugins/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx }/page/question/js/index.js"></script>
</body>
</html>