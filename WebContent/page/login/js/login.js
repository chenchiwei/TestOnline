$ = jQuery;
	function changeAuthCode() {
		var num = new Date().getTime();
		var rand = Math.round(Math.random() * 10000);
		num = num + rand;
		$('#ver_code').css('visibility', 'visible');
		if ($("#vdimgck")[0]) {
			$("#vdimgck")[0].src = getRootpath()+"/user/createVali?flag"+num;
		}
		return false;
	}

	function getRootpath() {
		var ctx = window.location.pathname;
		var rootPath = ctx.substring(0, ctx.substr(1).indexOf('/') + 1);
		return rootPath;
	};

	function login() {
		var username=$("#username").val();
		var password=$("#password").val();
		var type=$("input[name=type]:checked").val();
		var validateCode=$("#vdcode").val();
		var obj=new Object();
		obj.username=username;
		obj.password=password;
		obj.type=type;
		obj.validateCode=validateCode;
		$.ajax({
			url : getRootpath() + "/user/login",
			type : 'POST',
			dataType : 'json',
			data : obj,
			success : function(result) {
				if (result.success) {
					window.location.href = getRootpath()
							+ "/page/main/index.jsp";
				} else {
					alert(result.message);
					changeAuthCode();
				}

			}

		});

	}