<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生用户管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/student/css/index.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="padding: 8px;">
		<table id="data"></table>
	</div>

	<!-- start toolbar -->
	<div id="toolbar" class="datagrid-toolbar">


		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" id="addbtn" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增学生</a> 
				<a href="javascript:void(0)" id="editbtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑学生</a>
				<a
				href="javascript:void(0)" id="removebtn" class="easyui-linkbutton"
				onclick="testonline.student.deleteSubmit()" iconCls="icon-remove"
				plain="true">删除选中项</a> 
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
		style="padding: 5px; width: 400px; height: 350px;" iconCls="icon-ok"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">学生信息</div>
		<form id="fm" method="post">
			<input type="hidden" id="stuId" name="stuId" value="" />
			<div class="fitem">
				<label><span>*</span>姓名：</label> <input name="stuName"
					class="easyui-validatebox" required="true" class="easyui-combobox"
					id="stuName">
			</div>
			<div class="fitem" id="password">
				<label><span>*</span>密码：</label> <input type="password"
					class="easyui-validatebox" required="true" name="stuPassword"
					maxlength="30" id="stuPassword">
			</div>
			<div class="fitem">
				<label><span>*</span>确认密码：</label> <input type="password"
					class="easyui-validatebox" required="true" name="stuPassword"
					maxlength="30">
			</div>

			<div class="fitem">
				<label>班别：</label> <select id="class" class="selectClass" uri="${ctx }/class/findAll" name="class" required="true">
					<option value="请选择" selected="selected">请选择</option>
				</select>

			</div>
			
			<div class="fitem">
				<label>性别：</label> 
					<input type="radio" name="stuSex" value="1">男
					<input type="radio" name="stuSex" value="2">女
			</div>

			<div class="fitem">
				<label>邮箱：</label> <input name="stuEmail"
					value=""
					id="stuEmail">
			</div>
			
			<div class="fitem">
				<label>手机：</label> <input name="stuPhone"
					value=""
					id="stuPhone">
			</div>

		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submitBtn"
			onclick="testonline.student.addSubmit()">提交</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#adddlg').dialog('close')">取消</a>
	</div>


	<script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
	<script type="text/javascript" src="${ctx }/page/student/js/index.js"></script>
</body>
</html>