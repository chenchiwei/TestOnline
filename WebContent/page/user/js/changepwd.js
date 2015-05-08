$(function() {
	$("#checkpwd").blur(function() {
		var newpwd = $("#newpwd").val();
		if (newpwd != this.value) {
			$("#tip").show();
		} else {
			$("#tip").hide();
		}
	});
});

function getRootpath() {
	var ctx = window.location.pathname;
	var rootPath = ctx.substring(0, ctx.substr(1).indexOf('/') + 1);
	return rootPath;
};
function changepwd() {
	var oldpwd = $("#oldpwd").val();
	var newpwd = $("#newpwd").val();
	var checkpwd = $("#checkpwd").val();
	var type = $("#type").attr("value");
	var username = $("#username").attr("value");
	if (oldpwd != null && newpwd != null) {
		if (checkpwd != newpwd) {
			$("#tip").show();
			return;
		}

		var obj = new Object();
		obj.oldpwd = oldpwd;
		obj.newpwd = newpwd;
		obj.type = type;
		obj.username = username;

		$.ajax({
			url : getRootpath() + "/user/changepwd",
			type : 'POST',
			dataType : 'json',
			data : obj,
			success : function(result) {
				if (result.success) {
					alert("修改密码成功！");
					location.reload(true);
				} else {
					alert("修改密码失败！");
				}

			}

		});
	}

}