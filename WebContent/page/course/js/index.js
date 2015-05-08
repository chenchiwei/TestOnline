$.namespace("testonline.course");

testonline.course= {
	/**
	 * 初始化方法
	 */
	init : function(){
		testonline.course.setDataGrig();
		testonline.course.setAddBtn();
		testonline.course.setEditBtn();
		/*testonline.course.setSearchBtn();*/
		/*$.ajax({
			type:'post',
			url:"home/findAllSupplyInfo",
			dataType:'json',
//			data:{jsondata:"{'groupTypeId':1,'commonTraitId':29,'flowVelocity':0.11,'waveHeight':2,'maxTideRange':11," +
//					"'waterHeight':56,'takeUpAcreage':80000,'typhoonLevel':12,'tideType':'半日潮','seareaType':'内海','feedClass':'大鱼'}",page:1,rows:10}
			data:{page:1,rows:15,jsondata:"{'dataType':'供应','sort':'readCountDesc','ids':[46,37]}"}
		});*/
	},
	statusSave : "保存",
	statusPublish : "发布",
	statusIvalid : "失效",
	//查询状态记录 ，""为未查询 ， "查询"则是查询状态
	searchStat : '',
	/**
	 * 初始化表格
	 */
	setDataGrig : function(){
		$('#data').datagrid({  
			title:'课程列表',
			iconCls:'icon icon-table',
			fit: true,
			fitColumns:true,
			nowrap:true,  
            autoRowHeight: false,
            striped: true,
			url:getRootpath()+"/course/list",  
            /*queryParams : {jsondata:"{'title':''}"},*/
            toolbar : '#toolbar',
            frozenColumns:[ 
                [{field:'ck',checkbox:true}] 
            ],
            columns:[[
                      {field:'courseId',title:'编号',width:100,align:'center'},
                      {field:'courseName',title:'名称',width:100,align:'center'},
                      {field:'courseDesc',title:'描述',width:100,align:'center'}
                     
                  ]],
            pagination:true,
            pageSize:15,
            pageList:[15,30,45,60],
            rownumbers:true,
            onLoadSuccess : function(data){
            	$('#data').datagrid('uncheckAll');
            	if(data.total == 0){
            		if(testonline.course.searchStat == '查询'){
            			$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">您查询的课程信息不存在！</p>');
            			testonline.course.searchStat='';
            		}else{
            			$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">暂无课程信息！</p>');
            		}
            	}else{
            		//common.setEllipsis($('.datagrid-cell-c1-title'),$('.datagrid-cell-c1-content'),$('.datagrid-cell-c1-resource'),$('.datagrid-cell-c1-author'));//设置name列内容过长省略
				}
            	
            },
            onLoadError : function(){
				$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">数据加载出错，请刷新重试！</p>');
			}
            
		});
		var pager = $('#data').datagrid('getPager');
		pager.pagination({
			showRefresh : false
		});
		$('.pagination-num').tooltip({
			position: 'top',
		    content: '<span style="color:#666">按回车键可跳转</span>'
		});
	},
	
	/**
	 * 设置添加按钮
	 */
	setAddBtn : function(){
		$('#addbtn').click(function(){
			$('#fm').form('clear');
			$("#submitBtn").attr("onclick","testonline.course.addSubmit()");
			$('#adddlg').dialog('open','添加课程信息');
		});
	},
	
	setEditBtn : function(){
		$('#editbtn').click(function(){
			var rows = $('#data').datagrid('getSelections');
			if(rows.length!=1){
				$.messager.alert('提示',
						 "请选择一条记录！"
					);
			}else{
				var id=rows[0].courseId;
				$.ajax({
					url : getRootpath()+"/course/getById/"+id,
					type : 'POST',
					dataType:'json',
					success : function(result){
						if(result.success){
							var course=result.data;
							$('#fm').form('clear');
							$("#submitBtn").attr("onclick","testonline.course.editSubmit()");
							$("#courseId").val(course.courseId);
							$("#courseName").val(course.courseName);
							$("#courseDesc").val(course.courseDesc);
							
							$('#adddlg').dialog('open','编辑课程信息');
						}else{
							$.messager.alert(
								 '提示',
								 "加载失败！"
							);
						}
							
					}
				
				});
			}
			
		});
	},
	
	addSubmit:function(){
		if($("#fm").form('validate')){
				$.messager.confirm('提交窗口？',"你确定要提交吗？",function(result){
					if(result){
					
						var course=new Object();
						var courseName=$("#courseName").val();
						var courseDesc=$("#courseDesc").val();
						var teacherId=$("#teacher").val();
						course.courseName=courseName;
						course.courseDesc=courseDesc;
						course.teacherId=teacherId;
						
						$.ajax({
							url : getRootpath()+"/course/add",
							type : 'POST',
							dataType:'json',
							data : course,
							success : function(result){
								if(result.success){
									$.messager.alert('返回结果',
										 "添加成功！"
									);
									$('#adddlg').dialog('close');
									$('#data').datagrid('reload');
								}else{
									$.messager.alert(
										 '返回结果',
										 "添加失败！"
									);
								}
									
							}
						
						});
						
					}
				});
		}
		
	},
	
	editSubmit:function(){
		if($("#fm").form('validate')){
				$.messager.confirm('提交窗口？',"你确定要提交吗？",function(result){
					if(result){
					
						var course=new Object();
						var courseId=$("#courseId").val();
						var courseName=$("#courseName").val();
						var courseDesc=$("#courseDesc").val();
						var teacherId=$("#teacher").val();
						course.courseId=courseId;
						course.courseName=courseName;
						course.courseDesc=courseDesc;
						course.teacherId=teacherId;
						
						$.ajax({
							url : getRootpath()+"/course/edit",
							type : 'POST',
							dataType:'json',
							data : course,
							success : function(result){
								if(result.success){
									$.messager.alert('返回结果',
										 "编辑成功！"
									);
									$('#adddlg').dialog('close');
									$('#data').datagrid('reload');
								}else{
									$.messager.alert(
										 '返回结果',
										 "编辑失败！"
									);
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
	deleteSubmit : function(){
		var ids = new Array;
		var rows = $('#data').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			ids.push({id:rows[i].courseId});
		}
		if(ids.length<=0){
			$.messager.alert('提示',
					 "请选择至少一条记录！"
				);
			return;
		}else{
			$.messager.confirm('提交窗口？',"你确定要删除"+ids.length+"条记录吗？",function(result){
				if(result){
					$.ajax({
						url : getRootpath()+"/course/delete",
						type : 'POST',
						dataType:'json',
						contentType : "application/json",//这个必须要加
						data : JSON.stringify(ids),
						success : function(result){
							if(result.success){
								$.messager.alert('返回结果',
									 "删除成功！"
								);
								$('#adddlg').dialog('close');
								$('#data').datagrid('reload');
							}else{
								$.messager.alert(
									 '返回结果',
									 "删除失败！"
								);
							}
								
						}
					
					});
				}
			});
			
		}
		
		
	}
	 
};
$(function() {
	testonline.course.init();

});