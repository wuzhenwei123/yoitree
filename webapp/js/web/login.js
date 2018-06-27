var flagSubmit = true;
function save() {
	var mobile = $("#mobile").val();
	if (mobile == "") {
//		layer.msg("请输入手机号");
		layer.tips('请输入手机号', '#mobile');
//		$("#mobile").focus();
		return false;
	} else {
		if (!validatemobile(mobile)) {
//			layer.msg("请输入正确的手机号");
//			$("#mobile").focus();
			layer.tips('请输入正确的手机号', '#mobile');
			return false;
		}
	}
	var imgCode = $.trim($("#imgCode").val());
	if(imgCode == ''){
		layer.tips('请输入图形验证码', '#imgCode');
//		layer.msg("请输入图形验证码");
//		$("#imgCode").focus();
		return false;
	}
	var code = $("#code").val();
	if (code == "") {
		layer.tips('请输入手机验证码', '#code');
//		layer.msg("请输入验证码");
		return false;
	}
	if (flagSubmit) {
		flagSubmit = false;
//		var loading = weui.loading('登录中...', {
//			className : 'custom-classname'
//		});
		layer.msg('登录中...', {
		  icon: 16
		  ,shade: 0.01
		});
		$.post($("#path").val() + "/web/login",
		{
			mobile : mobile,
			imgCode : imgCode,
			code : code,
			_t : Math.random()
		},
		function(data) {
//			loading.hide();
			flagSubmit = true;
			var result = eval('(' + data + ')');
			if (result.c == '0') {
				layer.msg("登录成功");
				window.location.href = $("#path").val() + "/web/toLearnCenter";
			} else {
				layer.msg(result.m);
			}
		});
	}
}
var dxflag = true;
function getCode() {
	var phone = $.trim($("#mobile").val());
	var imgCode = $.trim($("#imgCode").val());
	if(imgCode == ''){
//		layer.msg("请输入图形验证码");
//		$("#imgCode").focus();
		layer.tips('请输入图形验证码', '#imgCode');
		return false;
	}
	if (phone == '') {
		layer.tips('请输入手机号', '#mobile');
//		layer.msg("请输入手机号");
//		$("#phone").focus();
		return false;
	} else {
		if (!validatemobile(phone)) {
//			layer.msg("请输入正确的手机号");
			layer.tips('请输入正确的手机号', '#mobile');
//			$("#phone").focus();
			return false;
		}
	}
	if (dxflag) {
		dxflag = false;
		$.get($("#path").val() + "/web/sendsms", {
			mobile : phone,
			imgCode : imgCode,
			_t : Math.random()
		}, function(data) {
			dxflag = true;
			var result = eval('(' + data + ')');
			if (result.c == '0') {
				btnNum();
				layer.msg("发送成功，请注意查收验证码");
			} else {
				countdown = 0;
				layer.msg(result.m);
			}
		});
	}
}
function btnNum() {
	var num = 179;
	$("#codeBtn").removeAttr('onclick');
	$("#codeBtn").val('还剩' + num-- + '秒');
	var intervalID = setInterval(function() {
		if (num > 0) {
			$("#codeBtn").val('还剩' + num + '秒');
			num--;
		} else {
			$("#codeBtn").val('获取验证码');
			$("#codeBtn").attr('onclick', 'getCode()');
			clearInterval(intervalID)
		}
	}, 1000);
}
function validatemobile(mobile) {
	if (mobile.length == 0) {
		return false;
	}
	if (mobile.length != 11) {
		return false;
	}
	var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
	if (!myreg.test(mobile)) {
		return false;
	}
	return true;
}

function golearn() {
	window.location.href = "${ctx}/weixin/toLearn?openId=${openId}";
}