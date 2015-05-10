$.namespace("testonline.classes");

testonline.classes = {
	/**
	 * 初始化方法
	 */
	init : function() {
		testonline.classes.setDataGrig();
		testonline.classes.setAddBtn();
		testonline.classes.setEditBtn();
	},
	statusSave : "保存",
	statusPublish : "发布",
	statusIvalid : "失效",
	searchStat : '',
	/**
	 * 初始化表格
	 */
	setDataGrig : function() {
		$('#data')
				.datagrid(
						{
							title : '班级列表',
							iconCls : 'icon icon-table',
							fit : true,
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							url : getRootpath() + "/class/listPage",
							toolbar : '#toolbar',
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
								title : '班别',
								width : 100,
								align : 'center'
							}, {
								field : 'grade',
								title : '年级',
								width : 100,
								align : 'center'
							}, {
								field : 'majorName',
								title : '专业',
								width : 100,
								align : 'center',
								formatter : function(value, rec) {
									return rec.major.majorName;
								}
							} ] ],
							pagination : true,
							pageSize : 15,
							pageList : [ 15, 30, 45, 60 ],
							rownumbers : true,
							onLoadSuccess : function(data) {
								$('#data').datagrid('uncheckAll');
								if (data.total == 0) {
									if (testonline.classes.searchStat == '查询') {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">您查询的班级信息不存在！</p>');
										testonline.classes.searchStat = '';
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
			$("#submitBtn").attr("onclick", "testonline.classes.addSubmit()");
			$('#adddlg').dialog('open', '添加班级信息');
		});
	},

	setEditBtn : function() {
		$('#editbtn').click(
				function() {
					var rows = $('#data').datagrid('getSelections');
					if (rows.length != 1) {
						$.messager.alert('提示', "请选择一条记录！");
					} else {
						var id = rows[0].classId;
						$.ajax({
							url : getRootpath() + "/class/getById/" + id,
							type : 'POST',
							dataType : 'json',
							success : function(result) {
								if (result.success) {
									var classes = result.data;
									$('#fm').form('clear');
									$("#submitBtn").attr("onclick",
											"testonline.classes.editSubmit()");
									$("#classId").val(classes.classId);
									$("#className").val(classes.className);
									$("#grade").val(classes.grade);

									$('#adddlg').dialog('open', '编辑班级信息');
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

					var classes = new Object();
					var className = $("#className").val();
					var grade = $("#grade").val();
					var majorId = $("#major").val();
					classes.className = className;
					classes.grade = grade;
					classes.majorId = majorId;

					$.ajax({
						url : getRootpath() + "/class/add",
						type : 'POST',
						dataType : 'json',
						data : classes,
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

					var classes = new Object();
					var classId = $("#classId").val();
					var className = $("#className").val();
					var grade = $("#grade").val();
					var majorId = $("#major").val();
					classes.classId = classId;
					classes.className = className;
					classes.grade = grade;
					classes.majorId = majorId;

					$.ajax({
						url : getRootpath() + "/class/edit",
						type : 'POST',
						dataType : 'json',
						data : classes,
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
				id : rows[i].classId
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
								url : getRootpath() + "/class/delete",
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

	}

};
$(function() {
	testonline.classes.init();
});