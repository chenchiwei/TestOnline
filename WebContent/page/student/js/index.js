$.namespace("testonline.student");

testonline.student= {
	/**
	 * 初始化方法
	 */
	init : function(){
		testonline.student.setDataGrig();
		testonline.student.setAddBtn();
		testonline.student.setEditBtn();
		/*testonline.student.setSearchBtn();*/
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
			title:'学生列表',
			iconCls:'icon icon-table',
			fit: true,
			fitColumns:true,
			nowrap:true,  
            autoRowHeight: false,
            striped: true,
			url:getRootpath()+"/student/list",  
            /*queryParams : {jsondata:"{'title':''}"},*/
            toolbar : '#toolbar',
            frozenColumns:[ 
                [{field:'ck',checkbox:true}] 
            ],
            columns:[[
                      {field:'stuId',title:'学号',width:100,align:'center'},
                      {field:'stuName',title:'姓名',width:100,align:'center'},
                      {field:'stuSex',title:'性别',width:100,align:'center',
                    	  formatter:function(value,rec){  
                    		  if(rec.stuSex==1){
                    			  return "男";  
                    		  }else {
                    			  return "女";
							}
                    		  
                            }},
                      {field:'grade',title:'年级',width:100,align:'center',
                    	  formatter:function(value,rec){  
                      		return rec.clazz.grade;
                          }
                      },
                      {field:'className',title:'班别',width:100,align:'center',
                    	  formatter:function(value,rec){  
                        		return rec.clazz.className;
                            }
                      }
                  ]],
            pagination:true,
            pageSize:15,
            pageList:[15,30,45,60],
            rownumbers:true,
            onLoadSuccess : function(data){
            	$('#data').datagrid('uncheckAll');
            	if(data.total == 0){
            		if(testonline.student.searchStat == '查询'){
            			$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">您查询的用户信息不存在！</p>');
            			testonline.student.searchStat='';
            		}else{
            			$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">暂无用户信息！</p>');
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
			$("#submitBtn").attr("onclick","testonline.student.addSubmit()");
			$('#adddlg').dialog('open','添加学生信息');
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
				var id=rows[0].stuId;
				$.ajax({
					url : getRootpath()+"/student/getById/"+id,
					type : 'POST',
					dataType:'json',
					success : function(result){
						if(result.success){
							var student=result.data;
							$('#fm').form('clear');
							$("#submitBtn").attr("onclick","testonline.student.editSubmit()");
							$("#stuId").val(student.stuId);
							$("#stuName").val(student.stuName);
							$("#stuPassword").val(student.stuPassword);
							$("#stuEmail").val(student.stuEmail);
							$("#stuPhone").val(student.stuPhone);
							$("input[name=stuSex]").each(function(){
								if(this.value==student.stuSex){
									$(this).attr("checked","checked");
								}
							});
							testonline.student.reShowData("#class", student.clazz.classId);
							
							$('#adddlg').dialog('open','编辑学生信息');
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
					
						var student=new Object();
						var classId = $('#class').val();
						var stuSex=$("input[name=stuSex]:checked").val();
						var stuEmail=$("#stuEmail").val();
						var stuPhone=$("#stuPhone").val();
						var stuName=$("#stuName").val();
						var stuPassword=$("#stuPassword").val();
						student.stuName=stuName;
						student.stuPassword=stuPassword;
						student.classId=classId;
						student.stuSex=stuSex;
						student.stuEmail=stuEmail;
						student.stuPhone=stuPhone;
						
						$.ajax({
							url : getRootpath()+"/student/add",
							type : 'POST',
							dataType:'json',
							data : student,
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
					
						var student=new Object();
						var stuId=$("#stuId").val();
						var classId = $('#class').val();
						var stuSex=$("input[name=stuSex]:checked").val();
						var stuEmail=$("#stuEmail").val();
						var stuPhone=$("#stuPhone").val();
						var stuName=$("#stuName").val();
						var stuPassword=$("#stuPassword").val();
						student.stuId=stuId;
						student.stuName=stuName;
						student.stuPassword=stuPassword;
						student.classId=classId;
						student.stuSex=stuSex;
						student.stuEmail=stuEmail;
						student.stuPhone=stuPhone;
						$.ajax({
							url : getRootpath()+"/student/editforadmin",
							type : 'POST',
							dataType:'json',
							data : student,
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
			ids.push({id:rows[i].stuId});
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
						url : getRootpath()+"/student/delete",
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
		
		
	},//回显下拉框数据
	reShowData:function(selector,value){
		$(selector).each(function(){
			var options=$(this).children('option');
			for(var i=0;i<options.length;i++){
				if(options[i].value==value){
					options[i].selected='selected';
					break;
				}
			}
		});
	}
	,
	initSelect:function(){
		$(".selectClass").each(function(){
			var select = $(this);
			var url = select.attr("uri");
			if(url && url != ""){
				$.ajax({
					type : 'POST',
					url : url,
					dataType : 'json',
					async : false,
					success : function(data){
						var options = select.children();
						var value = options.val();
						$(data).each(function(index, domEle){	
							var obj = data[index];
							if(obj.classId != value){
								select.append("<option value='" + obj.classId + "'>"+obj.grade+obj.major.majorName+obj.className+"</option>");
							}
						});
					},
					error : function(){
						alert("加载失败");
					}
					
				});
			}
		});
	}
	 
};
$(function() {
	testonline.student.init();
	testonline.student.initSelect();
});