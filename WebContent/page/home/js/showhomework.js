$.namespace("home.homework");

home.homework={
	submit:function(){
		$("#submitBtn").click(function(){
			$.messager.confirm('提交窗口？',"你确定要提交吗？",function(result1){
				if(result1){
					var result=new Object();
					var radioResult=new Array();
					var radioColl=$("input[type=radio]:checked");
					radioColl.each(function(){
						radioResult.push({id:this.name,value:this.value});
					});
					
					var checkboxResult=new Array();
					var checkboxColl=$("input[type=checkbox]:checked");
					checkboxColl.each(function(){
						checkboxResult.push({id:this.name,value:this.value});
					});
					
					var textResult=new Array();
					var textColl=$("textarea");
					textColl.each(function(){
						textResult.push({id:this.name,value:this.value});
					});
					
					result.radioResult=radioResult;
					result.checkboxResult=checkboxResult;
					result.textResult=textResult;
					
					var stuId=$("#stuId").val();
					var tea_hw_id=$("#tea_hw_id").val();
					$.ajax({
						url : getRootpath()+"/answer/addAnswer/"+stuId+"/"+tea_hw_id,
						type : 'POST',
						dataType:'json',
						contentType : "application/json",
						data : JSON.stringify(result),
						success : function(result2){
							if(result2.success){
								window.location.href= getRootpath()+"/page/home/homeworkList.jsp?flag=1";
							}else{
								alert(
									 "提交失败！"
								);
							}
								
						}
					
					});
				}
			});
			
		});
	},
	generateScore:function(){
		$("#genScore").click(function(){
			var teaHwId=$("#tea_hw_id").attr("value");
			var stuId=$("#stuId").attr("value");
			$.ajax({
				url : getRootpath()+"/homework/generateScore/"+teaHwId+"/"+stuId,
				type : 'POST',
				dataType:'json',
				data : {},
				success : function(result){
					if(result.success){
						$("#score").html("分数为："+result.score);
						$("#genScore").attr("disableed","disabled");
					}else{
						alert(
							 "操作失败！"
						);
					}
						
				}
			
			});
			
		});
	}
};

$(function() {
	home.homework.submit();
	//home.homework.generateScore();
});