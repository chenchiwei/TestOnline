$.namespace("testonline.major");

testonline.major = {
	/**
	 * 初始化方法
	 */
	init : function() {
		testonline.major.setDataGrig();
		testonline.major.setAddBtn();
		testonline.major.setEditBtn();
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
							title : '专业列表',
							iconCls : 'icon icon-table',
							fit : true,
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							url : getRootpath() + "/major/list",
							/* queryParams : {jsondata:"{'title':''}"}, */
							toolbar : '#toolbar',
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [ {
								field : 'majorId',
								title : '编号',
								width : 100,
								align : 'center'
							}, {
								field : 'majorName',
								title : '名称',
								width : 100,
								align : 'center'
							}, {
								field : 'majorDesc',
								title : '描述',
								width : 100,
								align : 'center'
							}, ] ],
							pagination : true,
							pageSize : 15,
							pageList : [ 15, 30, 45, 60 ],
							rownumbers : true,
							onLoadSuccess : function(data) {
								$('#data').datagrid('uncheckAll');
								if (data.total == 0) {
									if (testonline.major.searchStat == '查询') {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">您查询的专业信息不存在！</p>');
										testonline.major.searchStat = '';
									} else {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">暂无专业信息！</p>');
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
			$("#submitBtn").attr("onclick", "testonline.major.addSubmit()");
			$('#adddlg').dialog('open', '添加专业信息');
		});
	},

	setEditBtn : function() {
		$('#editbtn').click(
				function() {
					var rows = $('#data').datagrid('getSelections');
					if (rows.length != 1) {
						$.messager.alert('提示', "请选择一条记录！");
					} else {
						var id = rows[0].majorId;
						$.ajax({
							url : getRootpath() + "/major/getById/" + id,
							type : 'POST',
							dataType : 'json',
							success : function(result) {
								if (result.success) {
									var major = result.data;
									$('#fm').form('clear');
									$("#submitBtn").attr("onclick",
											"testonline.major.editSubmit()");
									$("#majorId").val(major.majorId);
									$("#majorName").val(major.majorName);
									$("#majorDesc").val(major.majorDesc);

									$('#adddlg').dialog('open', '编辑专业信息');
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

					var major = new Object();
					var majorName = $("#majorName").val();
					var majorDesc = $("#majorDesc").val();
					var academyId = $("#academy").val();
					major.majorName = majorName;
					major.majorDesc = majorDesc;
					major.academyId = academyId;

					$.ajax({
						url : getRootpath() + "/major/add",
						type : 'POST',
						dataType : 'json',
						data : major,
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

					var major = new Object();
					var majorId = $("#majorId").val();
					var majorName = $("#majorName").val();
					var majorDesc = $("#majorDesc").val();
					var academyId = $("#academy").val();
					major.majorId = majorId;
					major.majorName = majorName;
					major.majorDesc = majorDesc;
					major.academyId = academyId;

					$.ajax({
						url : getRootpath() + "/major/edit",
						type : 'POST',
						dataType : 'json',
						data : major,
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
				id : rows[i].majorId
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
								url : getRootpath() + "/major/delete",
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
	testonline.major.init();
});