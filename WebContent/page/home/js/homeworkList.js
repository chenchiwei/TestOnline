$.namespace("testonline.homeworkList");

testonline.homeworkList = {
	/**
	 * 初始化方法
	 */
	init : function() {
		testonline.homeworkList.setDataGrig();
	},

	/**
	 * 初始化表格
	 */
	setDataGrig : function() {
		var flag=$("#flag").attr("value");
		var stuId=$("#stuId").attr("value");
		var url;
		if(flag==0){
			url= getRootpath()
			+ "/homework/findFinishedHomework/"+stuId;
		}else{
			url= getRootpath()
			+ "/homework/findUnfinishHomework/"+stuId;
		}
		$('#data')
				.datagrid(
						{
							title : '完成情况列表',
							iconCls : 'icon icon-table',
							fit : true,
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							url :url,
							// queryParams : object,
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
										title : '作业名称',
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
										field : 'course',
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
											if (rec.status == 2) {
												return '<a onclick="testonline.homeworkList.addTab(this)" title="作业'
														+ rec.teaHwId
														+ '答案" url="answer/showAnswer/'
														+ rec.teaHwId
														+ '" href="#">查看答案</a>';
											} else {
												return "未公布答案";
											}
										}
									},
									{
										field : 'operation',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.flag == 0) {
												// 学生id
												return '<a onclick="testonline.homeworkList.addTab(this)" title="我的作业'
														+ rec.teaHwId
														+ '" url="homework/showMyHomework/'
														+ rec.teaHwId
														+ '/'+stuId+'" href="#">查看</a>';
											} else {
												return '<a onclick="testonline.homeworkList.addTab(this)" title="我的作业'
														+ rec.teaHwId
														+ '" url="homework/showHomework/'
														+ rec.teaHwId
														+ '" href="#">去完成</a>';
											}

										}
									} ] ],
							pagination : true,
							pageSize : 15,
							pageList : [ 15, 30, 45, 60 ],
							rownumbers : true,
							onLoadSuccess : function(data) {
								$('#data').datagrid('uncheckAll');
								if (data.total == 0) {
									if (testonline.homeworkList.searchStat == '查询') {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">您查询的作业信息不存在！</p>');
										testonline.homeworkList.searchStat = '';
									} else {
										$('.datagrid-body')
												.eq(1)
												.html('')
												.append(
														'<p style="text-align:center; line-height:280px;">暂无作业信息！</p>');
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
	addTab : function(e) {
		var url = $(e).attr("url");
		var title = $(e).attr("title");
		window.parent.addTab(title, getRootpath() + "/" + url);
	}
};
$(function() {
	testonline.homeworkList.init();
});