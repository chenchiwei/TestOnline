$.namespace("testonline.homework");

testonline.homework = {
	/**
	 * 初始化方法
	 */
	init : function() {
		testonline.homework.setDataGrig();
		testonline.homework.setAddBtn();
		testonline.homework.setEditBtn();
		$("#toolbar1").hide();
		$("#toolbar2").hide();
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
		var teaId=$("#teaId").attr("value");
		$('#data')
				.datagrid(
						{
							title : '作业列表',
							iconCls : 'icon icon-table',
							fit : true,
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							url : getRootpath()
									+ "/homework/findAllHomeworkByTea/"+teaId,
							/* queryParams : {jsondata:"{'title':''}"}, */
							toolbar : '#toolbar',
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [
									{
										field : 'teaHwId',
										title : '编号',
										width : 100,
										align : 'center'
									},
									{
										field : 'teaHwName',
										title : '名称',
										width : 100,
										align : 'center'
									},
									{
										field : 'teaHwAskTime',
										title : '发布时间',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											return common.parseDate(rec.teaHwAskTime);
										}
									},
									{
										field : 'course_id',
										title : '课程',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											return rec.course.courseName;
										}
									},
									{
										field : 'status',
										title : '状态',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.status == 1) {
												return "完成中";
											} else {
												return "已完成";
											}
											return rec.status;
										}
									},

									{
										field : 'show',
										title : '查看',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {

											return "<a href='#' onclick='testonline.homework.addTab(this)' url='answer/showAnswer/"
													+ row.teaHwId
													+ "' title='作业"
													+ row.teaHwId
													+ "' class='alter' showAnswer='true'><span>查看作业</span></a>";
										}
									},

									{
										field : 'situations',
										title : '操作',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {

											return "<a href='#' onclick='testonline.homework.addTab(this)' url='showSituation.jsp?teaHwId="
													+ row.teaHwId
													+ "' title='作业"
													+ row.teaHwId
													+ "完成情况' class='alter '><span>完成情況</span></a>";
										}
									},
									{
										field : 'statistics',
										title : '统计',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {

											return "<a href='#' onclick='testonline.homework.addTab(this)' url='showStatistics.jsp?teaHwId="
													+ row.teaHwId
													+ "' title='作业"
													+ row.teaHwId
													+ "分数统计' class='alter '><span>分数统计</span></a>";
										}
									},
									{
										field : 'operation',
										title : '公布答案',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {

											return "<a href='#' onclick='testonline.homework.publishAnswer(this)' teaHwId='"
													+ row.teaHwId
													+ "' class='alter '><span>公布答案</span></a>";
										}
									} ] ],
							pagination : true,
							pageSize : 15,
							pageList : [ 15, 30, 45, 60 ],
							rownumbers : true,
							onLoadSuccess : function(data) {
								$('#data').datagrid('uncheckAll');
								if (data.total == 0) {
									if (testonline.homework.searchStat == '查询') {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">您查询的作业信息不存在！</p>');
										testonline.homework.searchStat = '';
									} else {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">暂无作业信息！</p>');
									}
								} else {
									// common.setEllipsis($('.datagrid-cell-c1-title'),$('.datagrid-cell-c1-content'),$('.datagrid-cell-c1-resource'),$('.datagrid-cell-c1-author'));//设置name列内容过长省略
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
			$("#submitBtn").attr("onclick", "testonline.homework.addSubmit()");
			$('#adddlg').dialog('open', '添加作业信息');
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
								var id = rows[0].homeworkId;
								$
										.ajax({
											url : getRootpath()
													+ "/homework/getById/" + id,
											type : 'POST',
											dataType : 'json',
											success : function(result) {
												if (result.success) {
													var homework = result.data;
													$('#fm').form('clear');
													$("#submitBtn")
															.attr("onclick",
																	"testonline.homework.editSubmit()");
													$("#homeworkId")
															.val(
																	homework.homeworkId);
													$("#homeworkName")
															.val(
																	homework.homeworkName);
													$("#homeworkDesc")
															.val(
																	homework.homeworkDesc);

													$('#adddlg').dialog('open',
															'编辑作业信息');
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
					var classIds = new Array();
					$(".selectQuestion1").each(function() {
						classIds.push($(this).attr("value"));
					});

					var questionIds = new Array();
					var scores = new Array();
					$(".selectQuestion").each(function() {
						questionIds.push($(this).attr("value"));
						scores.push($(this).attr("score"));
					});
					var courseId = $("#course").val();
					var teaHwName = $("#teaHwName").val();
					var object = new Object();
					object.teaHwName = teaHwName;
					object.courseId = courseId;
					object.questionIds = questionIds;
					object.scores = scores;
					object.classIds = classIds;

					$.ajax({
						url : getRootpath() + "/homework/addHomework",
						type : 'POST',
						dataType : 'json',
						contentType : "application/json",
						data : JSON.stringify(object),
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

					var homework = new Object();
					var homeworkId = $("#homeworkId").val();
					var homeworkName = $("#homeworkName").val();
					var homeworkDesc = $("#homeworkDesc").val();
					var teacherId = $("#teacher").val();
					homework.homeworkId = homeworkId;
					homework.homeworkName = homeworkName;
					homework.homeworkDesc = homeworkDesc;
					homework.teacherId = teacherId;

					$.ajax({
						url : getRootpath() + "/homework/edit",
						type : 'POST',
						dataType : 'json',
						data : homework,
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
				id : rows[i].teaHwId
			});
		}
		if (ids.length <= 0) {
			$.messager.alert('提示', "请选择至少一条记录！");
			return;
		} else {
			$.messager.confirm('提交窗口？', "你确定要删除" + ids.length
					+ "条记录吗？删除后作业及相关信息将无法复原。", function(result) {
				if (result) {
					$.ajax({
						url : getRootpath() + "/homework/delete",
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
		var courseId = $("#course").val();
		$("#submitSelect")
				.attr("onclick", "testonline.homework.submitSelect()");
		if (courseId) {
			$('#win').window('open');
			$('#dg')
					.datagrid(
							{
								title : '题目列表',
								iconCls : 'icon icon-table',
								fit : true,
								fitColumns : true,
								nowrap : true,
								autoRowHeight : false,
								striped : true,
								url : getRootpath()
										+ "/question/findAllByCourseId/"
										+ courseId,
								queryParams : {
									jsondata : "{'title':''}"
								},
								toolbar : '#toolbar1',
								frozenColumns : [ [ {
									field : 'ck',
									checkbox : true
								} ] ],
								columns : [ [
										{
											field : 'questionId',
											title : '编号',
											width : 100,
											align : 'center'
										},
										{
											field : 'questionContent',
											title : '名称',
											width : 100,
											align : 'center'
										},
										{
											field : 'questionType',
											title : '类型',
											width : 100,
											align : 'center',
											formatter : function(value, rec) {
												if (rec.questionType == 1) {
													return "单选题";
												} else if (rec.questionType == 2) {
													return "多选题";
												} else if (rec.questionType == 3) {
													return "简答题";
												} else {
													return "其他";
												}
											}
										},
										{
											field : 'questionLevel',
											title : '难度',
											width : 100,
											align : 'center',
											formatter : function(value, rec) {
												if (rec.questionLevel == 1) {
													return "简单";
												} else if (rec.questionLevel == 2) {
													return "中等";
												} else if (rec.questionLevel == 3) {
													return "困难";
												} else {
													return "其他";
												}
											}
										},
										{
											field : 'score',
											title : '分值',
											width : 100,
											align : 'center',
											editor : 'numberbox'
										},
										{
											field : 'action',
											title : '编辑分值',
											width : 70,
											align : 'center',
											formatter : function(value, row,
													index) {
												if (row.editing) {
													var s = '<a href="#" onclick="testonline.homework.saverow(this)">保存</a> ';
													var c = '<a href="#" onclick="testonline.homework.cancelrow(this)">取消</a>';
													return s + c;
												} else {
													var e = '<a href="#" onclick="testonline.homework.editrow(this)">编辑</a> ';
													return e;
												}
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
															'<p style="text-align:center; line-height:280px;">您查询的题目信息不存在！</p>');
											testonline.homework.searchStat = '';
										} else {
											$('.datagrid-body')
													.eq(1)
													.html('')
													.append(
															'<p style="text-align:center; line-height:280px;">暂无题目信息！</p>');
										}
									} else {
										// common.setEllipsis($('.datagrid-cell-c1-title'),$('.datagrid-cell-c1-content'),$('.datagrid-cell-c1-resource'),$('.datagrid-cell-c1-author'));//设置name列内容过长省略
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
			$.messager.alert('提示', "请先选择一门课程！");
		}

	},
	submitSelect : function() {

		var rows = $('#dg').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', "请选择一条记录！");
		} else {
			$(".selectQuestion").remove();
			console.log(rows);
			for (var i = 0; i < rows.length; i++) {

				// $("#question").append("&nbsp;<a href='#'
				// onclick='javascript:$(this).remove()'
				// style='background-color:#3a87ad;border-radius:3px;padding:3px;color:white;text-decoration:none;'
				// class='selectQuestion'
				// value='"+rows[i].questionId+"'>"+rows[i].questionContent.substring(0,5)+"</a>&nbsp;");
				$("#selectQuestion")
						.after(
								"<a href='#' onclick='javascript:$(this).remove()' style='background-color:#3a87ad;border-radius:3px;padding:3px;color:white;text-decoration:none;margin:0 2px;' score='"
										+ rows[i].score
										+ "' class='selectQuestion' value='"
										+ rows[i].questionId
										+ "'>"
										+ rows[i].questionContent.substring(0,
												5) + "</a>");

			}
			$('#win').window('close');
		}

	},
	openClassWin : function() {
		var teaId = $("#teaId").val();
		$("#submitSelect").attr("onclick",
				"testonline.homework.submitSelect1()");
		if (teaId) {
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
								url : getRootpath() + "/class/findAllByTeaId/"
										+ teaId,
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
									} else {
										// common.setEllipsis($('.datagrid-cell-c1-title'),$('.datagrid-cell-c1-content'),$('.datagrid-cell-c1-resource'),$('.datagrid-cell-c1-author'));//设置name列内容过长省略
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
		}

	},
	submitSelect1 : function() {

		var rows = $('#dg').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', "请选择一条记录！");
		} else {
			$(".selectQuestion1").remove();
			// $("#classDiv").after("<div class='fitem' id='class'></div>");
			for (var i = 0; i < rows.length; i++) {

				// $("#class").append("&nbsp;<a href='#'
				// onclick='javascript:$(this).remove()'
				// style='background-color:#3a87ad;border-radius:3px;padding:3px;color:white;text-decoration:none;'
				// class='selectQuestion1'
				// value='"+rows[i].classId+"'>"+rows[i].className+"</a>&nbsp;");
				$("#selectQuestion1")
						.after(
								"<a href='#' onclick='javascript:$(this).remove()' style='background-color:#3a87ad;border-radius:3px;padding:3px;color:white;text-decoration:none;margin:0 2px;' class='selectQuestion1' id='"
										+ rows[i].classId
										+ "' value='"
										+ rows[i].classId
										+ "'>"
										+ rows[i].className + "</a>");

			}
			$('#win').window('close');
		}

	},
	publishAnswer : function(e) {
		$.messager.confirm('提交窗口？', "你确定要公布答案吗？", function(result) {
			if (result) {
				var teaHwId = $(e).attr("teaHwId");
				$.ajax({
					url : getRootpath() + "/homework/publishAnswer/" + teaHwId,
					type : 'POST',
					dataType : 'json',
					data : {},
					success : function(result) {
						if (result.success) {
							$.messager.alert('返回结果', "公布成功！");
							$('#adddlg').dialog('close');
							$('#data').datagrid('reload');
						} else {
							$.messager.alert('返回结果', "公布失败！");
						}

					}

				});
			}
		});

	},
	addTab : function(e) {
		var url = $(e).attr("url");
		var title = $(e).attr("title");
		if ($(e).attr("showAnswer")) {
			window.parent
					.addTab(title, getRootpath() + "/" + url);
		} else {
			window.parent
					.addTab(title, getRootpath() + "/page/homework/" + url);
		}

	},
	updateActions : function(index, row) {
		$('#dg').datagrid('updateRow', {
			index : index,
			row : row
		});
	},
	getRowIndex : function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	},
	editrow : function(target) {
		$('#dg').datagrid('beginEdit', testonline.homework.getRowIndex(target));
	},
	saverow : function(target) {
		$('#dg').datagrid('endEdit', testonline.homework.getRowIndex(target));
	},
	cancelrow : function(target) {
		$('#dg')
				.datagrid('cancelEdit', testonline.homework.getRowIndex(target));
	}
};
$(function() {
	testonline.homework.init();
	testonline.homework.submitSelect();
	testonline.homework.submitSelect1();
});