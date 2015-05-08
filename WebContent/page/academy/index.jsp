<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学院管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/page/academy/css/index.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="padding: 8px;">
		<table id="data"></table>
	</div>

	<!-- start toolbar -->
	<div id="toolbar" class="datagrid-toolbar">


		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" id="addbtn" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增学院</a> 
				<a href="javascript:void(0)" id="editbtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑学院</a>
				<a
				href="javascript:void(0)" id="removebtn" class="easyui-linkbutton"
				onclick="testonline.academy.deleteSubmit()" iconCls="icon-remove"
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
		style="padding: 5px; width: 400px; height: 300px;" iconCls="icon-ok"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">学院信息</div>
		<form id="fm" method="post">
			<input type="hidden" id="academyId" name="academyId" value="" />
			<div class="fitem">
				<label><span>*</span>名称：</label> <input name="academyName"
					class="easyui-validatebox" required="true" class="easyui-combobox"
					id="academyName">
			</div>
			<div class="fitem">
				<label>学校：</label> <select id="school" url="${ctx }/school/findAll" name="school" required="true">
					<option value="请选择" selected="selected">请选择</option>

				</select>

			</div>
			<div class="fitem">
				<label><span>*</span>描述：</label> <input name="academyDesc"
					class="easyui-validatebox" required="true" class="easyui-combobox"
					id="academyDesc">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submitBtn"
			onclick="testonline.academy.addSubmit()">提交</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#adddlg').dialog('close')">取消</a>
	</div>


	<script type="text/javascript" src="${ctx }/common/js/utils.js"></script>
	<script type="text/javascript" src="${ctx }/page/academy/js/index.js"></script>
</body>
</html>