$.namespace("testonline.situation");

testonline.situation= {
	/**
	 * 初始化方法
	 */
	init : function(){
		testonline.situation.setDataGrig();
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
		var teaHwId=$("#teaHwId").attr("value");
		var classId=$("#classId").attr("value");
		var isNotFinished=$("#isNotFinished").attr("value");
		var object=new Object();
		object.teaHwId=teaHwId;
		object.classId=classId;
		object.isNotFinished=isNotFinished;
		$('#data').datagrid({  
			title:'完成情况列表',
			iconCls:'icon icon-table',
			fit: true,
			fitColumns:true,
			nowrap:true,  
            autoRowHeight: false,
            striped: true,
			url:getRootpath()+"/homework/findFinishedStudent",  
            queryParams : object,
            toolbar : '#toolbar',
            frozenColumns:[ 
                [{field:'ck',checkbox:true}] 
            ],
            columns:[[
                      {field:'stuId',title:'学号',width:100,align:'center'},
                      {field:'stuName',title:'学生',width:100,align:'center'},
                      {field:'className',title:'班级',width:100,align:'center'},
                      {field:'status',title:'状态',width:100,align:'center',
                    	  formatter:function(value,rec){  
                    		  if(rec.submitTime){
                    			  return "已完成";
                    		  }else{
                    			  return "未完成";
                    		  }
                            }},
                      {field:'submitTime',title:'完成时间',width:100,align:'center',
                    	  formatter:function(value,rec){  
                    		  if(rec.submitTime){
                    			  return common.parseDate(rec.submitTime);
                    		  }else{
                    			  return "---";
                    		  }
                      		
                          }
                      },
                      
                            
                    {field:'operation',title:'操作',width:50,align:'center',
                    	formatter:function(value,row,index){  
                    		// 学生id
							return '<a onclick="testonline.situation.addTab(this)" title="'+row.stuName+'的作业'+'" url="homework/showMyHomework/'
									+ teaHwId
									+ '/'+row.stuId+'" href="#">查看情況</a>';
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
            		if(testonline.situation.searchStat == '查询'){
            			$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">您查询的作业信息不存在！</p>');
            			testonline.situation.searchStat='';
            		}else{
            			$('.datagrid-body').eq(1).html('').append('<p style="text-align:center; line-height:280px;">暂无作业信息！</p>');
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
	addTab : function(e) {
		var url = $(e).attr("url");
		var title = $(e).attr("title");
		window.parent.addTab(title, getRootpath() + "/" + url);
	}
};
$(function() {
	testonline.situation.init();
});