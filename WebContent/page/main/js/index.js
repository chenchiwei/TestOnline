
function addTab(title, url) {
	if ($('#tt').tabs('exists', title)) {
		$('#tt').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0" src="'
				+ url + '" style="width:100%; height:100%;"></iframe>';
		$('#tt').tabs('add', {
			title : title,
			content : content,
			selected : true,
			closable : true
		});
	}
};

function getRootpath() {
	var ctx = window.location.pathname;
	var rootPath = ctx.substring(0, ctx.substr(1).indexOf('/') + 1);
	return rootPath;
};

function logout(){
	$.messager.confirm('提交窗口？', "你确定要退出吗？", function(result) {
		if (result) {
			$.ajax({
				url : getRootpath() + "/user/logout",
				type : 'POST',
				dataType : 'json',
				data : {},
				success : function(result) {
					if (result.success) {
						$.messager.alert('提示', "退出成功！");
						window.location.href=getRootpath()+"/page/login/login.jsp";
					} else {
						$.messager.alert('提示', "退出失败！");
					}

				}

			});
		}
	});
	
}