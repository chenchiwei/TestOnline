/**
 * 建立命名空间工具
 */
$.namespace = function(fullNS){
	var nsArray = fullNS.split('.');
	var sEval = "";
	var sNS = "";
	for (var i = 0; i < nsArray.length; i++) {
		if (i != 0)
			sNS += ".";
		sNS += nsArray[i];
		sEval += "if (typeof(" + sNS + ") == 'undefined') " + sNS
				+ " = new Object();";
	}
	if (sEval != "")
		eval(sEval);
};

var common={
		/**
		 * 设置文字过长省略号
		 */
		setEllipsis : function(){
			for(var i=0;i<arguments.length;i++){
				arguments[i].css({ 
	                "white-space":"nowrap",  
	                "text-overflow":"ellipsis",  
	                "-o-text-overflow":"ellipsis",  
	                "overflow":"hidden"  
	            });
				$.each(arguments[i],function(i,v){
					var $obj = $(v);
					$obj.attr('title',$(this).text());
				});
			}
		},
		initSelect:function(){
			$("select").each(function(){
				var select = $(this);
				var url = select.attr("url");
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
								if(obj.value != value){
									select.append("<option value='" + obj.id + "'>"+obj.value+"</option>");
								}
							});
						}
					});
				}
			});
		},
		parseDate:function(d){
			var date = new Date(d);
			Y = date.getFullYear() + '-';
			M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
			D = date.getDate() + ' ';
			h = date.getHours() + ':';
			m = date.getMinutes() + ':';
			s = date.getSeconds(); 
			return Y+M+D+h+m+s; 
		}
		
};

/**
 * 初始化下拉列表框
 */
$(function(){
	
	common.initSelect();
	
});