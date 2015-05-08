$.namespace("testonline.homework.statistics");

var $tatisticsdata=null;

testonline.homework.statistics = {
		
		init:function(){
			require.config({
	            paths: {
	                echarts: getRootpath()+'/common/echarts'
	            }
	        });
	        require(
	                [
	                    'echarts',
	                    //'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
	                    'echarts/chart/bar'
	                ],
	                function (ec) {
	                    var myChart = ec.init(document.getElementById('main'));
	                 // 过渡---------------------
	                    myChart.showLoading({
	                        text: '正在努力的读取数据中...',    //loading话术
	                    });

	                    // ajax getting data...............
	                    var teaHwId=$("#teaHwId").attr("value");
						$.ajax({
							url : getRootpath()+"/homework/findClassStatistics/"+teaHwId,
							type : 'POST',
							dataType:'json',
							success : function(result){
								// ajax callback
			                    myChart.hideLoading();
								if(result.success){
									$tatisticsdata=result;
									var option = {

				                    		title : {
				                    	        text: '各班级作业分数统计图示',
				                    	    },
				                    	    tooltip : {
				                    	        trigger: 'axis'
				                    	    },
				                    	    legend: {
				                    	        data:['平均分','最高分','最低分']
				                    	    },
				                    	    toolbox: {
				                    	        show : true,
				                    	        feature : {
				                    	            mark : {show: true},
				                    	            dataView : {show: true, readOnly: false},
				                    	            magicType : {show: true, type: ['line', 'bar']},
				                    	            restore : {show: true},
				                    	            saveAsImage : {show: true}
				                    	        }
				                    	    },
				                    	    calculable : true,
				                    	    xAxis : [
				                    	        {
				                    	            type : 'category',
				                    	            /* data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'] */
				                    	            data:result.classStr
				                    	        },
				                    	        {
				                    	            data:result.classIds
				                    	        }
				                    	    ],
				                    	    yAxis : [
				                    	        {
				                    	            type : 'value'
				                    	        }
				                    	    ],
				                    	    series : [
				                    	        {
				                    	            name:'平均分',
				                    	            type:'bar',
				                    	            /* data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3], */
				                    	            data:result.avgScroe,
				                    	            markPoint : {
				                    	                data : [
				                    	                    {type : 'max', name: '最大值'},
				                    	                    {type : 'min', name: '最小值'}
				                    	                ]
				                    	            },
				                    	            markLine : {
				                    	                data : [
				                    	                    {type : 'average', name: '平均值'}
				                    	                ]
				                    	            }
				                    	        },
				                    	        {
				                    	            name:'最高分',
				                    	            type:'bar',
				                    	            /* data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3], */
				                    	            data:result.maxScore,
				                    	            markPoint : {
				                    	                data : [
					                    	                    {type : 'max', name: '最大值'},
					                    	                    {type : 'min', name: '最小值'}
					                    	                ]
				                    	            },
				                    	            markLine : {
				                    	                data : [
				                    	                    {type : 'average', name : '平均值'}
				                    	                ]
				                    	            }
				                    	        },
				                    	        {
				                    	            name:'最低分',
				                    	            type:'bar',
				                    	            /* data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3], */
				                    	            data:result.minScore,
				                    	            markPoint : {
				                    	                data : [
					                    	                    {type : 'max', name: '最大值'},
					                    	                    {type : 'min', name: '最小值'}
					                    	                ]
				                    	            },
				                    	            markLine : {
				                    	                data : [
				                    	                    {type : 'average', name : '平均值'}
				                    	                ]
				                    	            }
				                    	        }
				                    	    ]
				                    };
				                    myChart.setOption(option);
								}else{
									// 图表清空-------------------
									myChart.clear();

									// 图表释放-------------------
									myChart.dispose();
									alert(2);
									$("#main").text("此作业还没有相关信息");
								}
									
							}
						
						});
	                    
						 var ecConfig = require('echarts/config');
					        /*function eConsole(param) {
					        	//console.log(param);
					        	if(!isNaN(param.data)){
					        		var teaHwId=$("#teaHwId").attr("value");
						        	var classId=data.classId[param.dataIndex];
						        	var isNotFinished=param.seriesIndex;
						        	window.parent.addTab(param.name+"完成情况",getRootpath()+"/page/homework/finishSituation.jsp?teaHwId="
						        	+teaHwId+"&classId="+classId+"&isNotFinished="+isNotFinished);
					        	}
					        	
					        }*/
					        
					        myChart.on(ecConfig.EVENT.CLICK, eConsole);
	                    
	                }
	            );
		}
};
	
	
$(function(){
	testonline.homework.statistics.init();
});
        
       