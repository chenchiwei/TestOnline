$.namespace("testonline.question");

testonline.question = {
	/**
	 * 初始化方法
	 */
	init : function() {
		testonline.question.setDataGrig();
		testonline.question.setAddBtn();
		testonline.question.setImportBtn();
		testonline.question.setEditBtn();
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
							title : '试题列表',
							iconCls : 'icon icon-table',
							fit : true,
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							url : getRootpath() + "/question/listPage",
							/* queryParams : {jsondata:"{'title':''}"}, */
							toolbar : '#toolbar',
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [ {
								field : 'questionId',
								title : '编号',
								width : 100,
								align : 'center'
							}, {
								field : 'questionContent',
								title : '试题',
								width : 100,
								align : 'center'
							}, {
								field : 'questionType',
								title : '类型',
								width : 100,
								align : 'center'
							}, {
								field : 'questionLevel',
								title : '难度',
								width : 100,
								align : 'center'
							}, {
								field : 'courseName',
								title : '课程',
								width : 100,
								align : 'center',
								formatter : function(value, rec) {
									return rec.course.courseName;
								}
							} ] ],
							pagination : true,
							pageSize : 15,
							pageList : [ 15, 30, 45, 60 ],
							rownumbers : true,
							onLoadSuccess : function(data) {
								$('#data').datagrid('uncheckAll');
								if (data.total == 0) {
									if (testonline.question.searchStat == '查询') {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">您查询的试题信息不存在！</p>');
										testonline.question.searchStat = '';
									} else {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">暂无试题信息！</p>');
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
			$("#submitBtn").attr("onclick", "testonline.question.addSubmit()");
			$('#adddlg').dialog('open', '添加试题信息');
		});
	},

	/**
	 * 设置导入按钮
	 */
	setImportBtn : function() {
		$('#importbtn').click(
				function() {
					$('#importfm').form('clear');
					$("#submitBtn").attr("onclick",
							"testonline.question.importSubmit()");
					$('#importdlg').dialog('open', '添加试题信息');
				});
	},

	setEditBtn : function() {
		$('#editbtn')
				.click(
						function() {
							var rows = $('#data').datagrid('getSelections');
							if (rows.length != 1) {
								$.messager.alert('提示', "请选择一条记录！");
							} else {
								var id = rows[0].questionId;
								$
										.ajax({
											url : getRootpath()
													+ "/question/getById/" + id,
											type : 'POST',
											dataType : 'json',
											success : function(result) {
												if (result.success) {
													var question = result.data;
													$('#fm').form('clear');
													$("#submitBtn")
															.attr("onclick",
																	"testonline.question.editSubmit()");
													$("#questionId")
															.val(
																	question.questionId);
													$("#questionContent")
															.val(
																	question.questionContent);

													$('#adddlg').dialog('open',
															'编辑试题信息');
												} else {
													$.messager.alert('提示',
															"加载失败！");
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

					var question = new Object();
					var questionContent = $("#questionContent").val();
					var questionType = $("#questionType").val();
					var questionLevel = $("#questionLevel").val();
					var chapterId = $("#chapter").val();
					question.questionContent = questionContent;
					question.questionType = questionType;
					question.questionLevel = questionLevel;
					question.chapterId = chapterId;

					$.ajax({
						url : getRootpath() + "/question/add",
						type : 'POST',
						dataType : 'json',
						data : question,
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
	importSubmit : function() {
		if ($("#importfm").form('validate')) {
			$.messager.confirm('提交窗口？', "你确定要提交吗？", function(result) {
				if (result) {
					var courseId = $("#course").val();
					if (courseId) {
						var options = {
							url : getRootpath() + "/question/importExcel/"
									+ courseId,
							type : "POST",
							success : function(result) {
								if (result.success != null) {
									$.messager.alert('返回结果', "上传成功！");
								} else {
									$.messager.alert('返回结果', "上传失败！");
								}
								$("#uploading").removeClass("loading");
								location.reload(true);
								$('#importdlg').dialog('close');

							}
						};

						$('#importfm').ajaxForm(options);
						$("#importfm").submit();
					}

				}
			});
		}

	},
	editSubmit : function() {
		if ($("#fm").form('validate')) {
			$.messager.confirm('提交窗口？', "你确定要提交吗？", function(result) {
				if (result) {

					var question = new Object();
					var questionId = $("#questionId").val();
					var questionContent = $("#questionContent").val();
					var questionType = $("#questionType").val();
					var questionLevel = $("#questionLevel").val();
					var chapterId = $("#chapter").val();
					question.questionId = questionId;
					question.questionContent = questionContent;
					question.questionType = questionType;
					question.questionLevel = questionLevel;
					question.chapterId = chapterId;

					$.ajax({
						url : getRootpath() + "/question/edit",
						type : 'POST',
						dataType : 'json',
						data : question,
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
				id : rows[i].questionId
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
								url : getRootpath() + "/question/delete",
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
	testonline.question.init();
});