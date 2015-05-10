$.namespace("testonline.teacher");

var $testonlineteaId = null;

testonline.teacher = {
	/**
	 * 初始化方法
	 */
	init : function() {
		testonline.teacher.setDataGrig();
		testonline.teacher.setAddBtn();
		testonline.teacher.setEditBtn();
		$("#toolbar1").hide();
	},
	statusSave : "保存",
	statusPublish : "发布",
	statusIvalid : "失效",
	// 查询状态记录 ，""为未查询 ， "查询"则是查询状态
	searchStat : '',
	/**
	 * 初始化表格
	 */
	setDataGrig : function() {
		$('#data')
				.datagrid(
						{
							title : '教师列表',
							iconCls : 'icon icon-table',
							fit : true,
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							url : getRootpath() + "/teacher/list",
							/* queryParams : {jsondata:"{'title':''}"}, */
							toolbar : '#toolbar',
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [ {
								field : 'teaId',
								title : '编号',
								width : 100,
								align : 'center'
							}, {
								field : 'teaName',
								title : '姓名',
								width : 100,
								align : 'center'
							}, {
								field : 'teaSex',
								title : '性别',
								width : 100,
								align : 'center',
								formatter : function(value, rec) {
									if (rec.teaSex == 1) {
										return "男";
									} else {
										return "女";
									}

								}
							}, {
								field : 'teaEmail',
								title : '邮箱',
								width : 100,
								align : 'center'
							}, {
								field : 'teaPhone',
								title : '手机',
								width : 100,
								align : 'center'
							} ] ],
							pagination : true,
							pageSize : 15,
							pageList : [ 15, 30, 45, 60 ],
							rownumbers : true,
							onLoadSuccess : function(data) {
								$('#data').datagrid('uncheckAll');
								if (data.total == 0) {
									if (testonline.teacher.searchStat == '查询') {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">您查询的教师信息不存在！</p>');
										testonline.teacher.searchStat = '';
									} else {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">暂无教师信息！</p>');
									}
								}
							},
							onLoadError : function() {
								$('.datagrid-body')
										.eq(1)
										.html('')
										.append(
												'<p style="text-align:center; line-height:280px;">数据加载出错，请刷新重试！</p>');
							}

						});
		var pager = $('#data').datagrid('getPager');
		pager.pagination({
			showRefresh : false
		});
		$('.pagination-num').tooltip({
			position : 'top',
			content : '<span style="color:#666">按回车键可跳转</span>'
		});
	},

	/**
	 * 设置添加按钮
	 */
	setAddBtn : function() {
		$('#addbtn').click(function() {
			$('#fm').form('clear');
			$("#submitBtn").attr("onclick", "testonline.teacher.addSubmit()");
			$('#adddlg').dialog('open', '添加教师信息');
		});
	},
	setEditBtn : function() {
		$('#editbtn').click(
				function() {
					var rows = $('#data').datagrid('getSelections');
					if (rows.length != 1) {
						$.messager.alert('提示', "请选择一条记录！");
					} else {
						var id = rows[0].teaId;
						$.ajax({
							url : getRootpath() + "/teacher/getById/" + id,
							type : 'POST',
							dataType : 'json',
							success : function(result) {
								if (result.success) {
									var teacher = result.data;
									$('#fm').form('clear');
									$("#submitBtn").attr("onclick",
											"testonline.teacher.editSubmit()");
									$("#teaId").val(teacher.teaId);
									$("#teaName").val(teacher.teaName);
									$("#teaPassword").val(teacher.teaPassword);
									$("#teaEmail").val(teacher.teaEmail);
									$("#teaPhone").val(teacher.teaPhone);

									$('#adddlg').dialog('open', '编辑教师信息');
								} else {
									$.messager.alert('提示', "加载失败！");
								}

							}

						});
					}

				});
	},

	addSubmit : function() {
		if ($("#fm").form('validate')) {
			$.messager.confirm('提交窗口？', "你确定要提交吗？", function(result) {
				if (result) {

					var teacher = new Object();
					var teaName = $("#teaName").val();
					var teaPassword = $("#teaPassword").val();
					var teaEmail = $("#teaEmail").val();
					var teaSex = $("input[name=teaSex]:checked").val();
					var academyId = $("#academy").val();
					// var classId=$("#class").val();
					var teaPhone = $("#teaPhone").val();
					teacher.teaName = teaName;
					teacher.teaPassword = teaPassword;
					teacher.teaEmail = teaEmail;
					teacher.teaPhone = teaPhone;
					teacher.academyId = academyId;
					teacher.teaSex = teaSex;
					// teacher.classId=classId;
					// console.log(teacher);
					$.ajax({
						url : getRootpath() + "/teacher/add",
						type : 'POST',
						dataType : 'json',
						data : teacher,
						success : function(result) {
							if (result.success) {
								$.messager.alert('返回结果', "添加成功！");
								$('#adddlg').dialog('close');
								$('#data').datagrid('reload');
							} else {
								$.messager.alert('返回结果', "添加失败！");
							}

						}

					});

				}
			});
		}

	},

	editSubmit : function() {
		if ($("#fm").form('validate')) {
			$.messager.confirm('提交窗口？', "你确定要提交吗？", function(result) {
				if (result) {

					var teacher = new Object();
					var teaId = $("#teaId").val();
					var teaName = $("#teaName").val();
					var teaPassword = $("#teaPassword").val();
					var teaEmail = $("#teaEmail").val();
					var teaPhone = $("#teaPhone").val();
					teacher.teaId = teaId;
					teacher.teaName = teaName;
					teacher.teaPassword = teaPassword;
					teacher.teaEmail = teaEmail;
					teacher.teaPhone = teaPhone;

					$.ajax({
						url : getRootpath() + "/teacher/edit",
						type : 'POST',
						dataType : 'json',
						data : teacher,
						success : function(result) {
							if (result.success) {
								$.messager.alert('返回结果', "编辑成功！");
								$('#adddlg').dialog('close');
								$('#data').datagrid('reload');
							} else {
								$.messager.alert('返回结果', "编辑失败！");
							}

						}

					});

				}
			});
		}

	},
	/**
	 * 删除
	 */
	deleteSubmit : function() {
		var ids = new Array;
		var rows = $('#data').datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push({
				id : rows[i].teaId
			});
		}
		if (ids.length <= 0) {
			$.messager.alert('提示', "请选择至少一条记录！");
			return;
		} else {
			$.messager.confirm('提交窗口？', "你确定要删除" + ids.length + "条记录吗？",
					function(result) {
						if (result) {
							$.ajax({
								url : getRootpath() + "/teacher/delete",
								type : 'POST',
								dataType : 'json',
								contentType : "application/json",// 这个必须要加
								data : JSON.stringify(ids),
								success : function(result) {
									if (result.success) {
										$.messager.alert('返回结果', "删除成功！");
										$('#adddlg').dialog('close');
										$('#data').datagrid('reload');
									} else {
										$.messager.alert('返回结果', "删除失败！");
									}

								}

							});
						}
					});

		}

	},
	openWin : function() {
		var rows = $('#data').datagrid('getSelections');
		if (rows.length != 1) {
			alert("请选择一个老师！");
			return;
		}
		$testonlineteaId = rows[0].teaId;
		$('#classdlg').dialog('open');

	},
	sure : function() {
		var academyId = $("#academyId").val();
		if (academyId) {
			$('#win').window('open');
			$('#dg')
					.datagrid(
							{
								title : '班级列表',
								iconCls : 'icon icon-table',
								fit : true,
								fitColumns : true,
								nowrap : true,
								autoRowHeight : false,
								striped : true,
								url : getRootpath() + "/class/findByAcademyId/"
										+ academyId,
								queryParams : {
									jsondata : "{'title':''}"
								},
								toolbar : '#toolbar1',
								frozenColumns : [ [ {
									field : 'ck',
									checkbox : true
								} ] ],
								columns : [ [ {
									field : 'classId',
									title : '编号',
									width : 100,
									align : 'center'
								}, {
									field : 'className',
									title : '名称',
									width : 100,
									align : 'center'
								}, {
									field : 'grade',
									title : '年级',
									width : 100,
									align : 'center'
								}, {
									field : 'major',
									title : '专业',
									width : 100,
									align : 'center',
									formatter : function(value, rec) {
										return rec.major.majorName;
									}
								} ] ],
								onBeforeEdit : function(index, row) {
									row.editing = true;
									testonline.homework.updateActions(index,
											row);
								},
								onAfterEdit : function(index, row) {
									row.editing = false;
									testonline.homework.updateActions(index,
											row);
								},
								onCancelEdit : function(index, row) {
									row.editing = false;
									testonline.homework.updateActions(index,
											row);
								},
								pagination : true,
								pageSize : 15,
								pageList : [ 15, 30, 45, 60 ],
								rownumbers : true,
								onLoadSuccess : function(data) {
									$('#dg').datagrid('uncheckAll');
									if (data.total == 0) {
										if (testonline.homework.searchStat == '查询') {
											$('.datagrid-body')
													.eq(1)
													.html('')
													.append(
															'<p style="text-align:center; line-height:280px;">您查询的班级信息不存在！</p>');
											testonline.homework.searchStat = '';
										} else {
											$('.datagrid-body')
													.eq(1)
													.html('')
													.append(
															'<p style="text-align:center; line-height:280px;">暂无班级信息！</p>');
										}
									}
								},
								onLoadError : function() {
									$('.datagrid-body')
											.eq(1)
											.html('')
											.append(
													'<p style="text-align:center; line-height:280px;">数据加载出错，请刷新重试！</p>');
								}

							});
			var pager = $('#dg').datagrid('getPager');
			pager.pagination({
				showRefresh : false
			});
			$('.pagination-num').tooltip({
				position : 'top',
				content : '<span style="color:#666">按回车键可跳转</span>'
			});
		} else {
			$.messager.alert('提示', "请先选择学院！");
		}
	},
	submitSelect : function() {
		var ids = new Array;
		var rows = $('#dg').datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push({
				id : rows[i].classId
			});
		}
		if (ids.length <= 0) {
			$.messager.alert('提示', "请选择至少一条记录！");
			return;
		} else {
			$.messager.confirm('提交窗口？', "你确定要分配选中的几个班级吗？", function(result) {
				if (result) {
					$.ajax({
						url : getRootpath() + "/teacher/forClass/"
								+ $testonlineteaId,
						type : 'POST',
						dataType : 'json',
						contentType : "application/json",// 这个必须要加
						data : JSON.stringify(ids),
						success : function(result) {
							if (result.success) {
								$.messager.alert('返回结果', "分配成功！");
								$('#classdlg').dialog('close');
								$('#dg').datagrid('reload');
							} else {
								$.messager.alert('返回结果', "分配失败！");
							}

						}

					});
				}
			});

		}
	}

};
$(function() {
	testonline.teacher.init();
});