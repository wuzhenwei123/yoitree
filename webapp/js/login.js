jQuery(function($){
	 var ctx = $("#ctx").val();
    $.supersized({
        // Functionality
        slide_interval     : 4000,    // Length between transitions
        transition         : 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
        transition_speed   : 1000,    // Speed of transition
        performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)
        // Size & Position
        min_width          : 0,    // Min width allowed (in pixels)
        min_height         : 0,    // Min height allowed (in pixels)
        vertical_center    : 1,    // Vertically center background
        horizontal_center  : 1,    // Horizontally center background
        fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
        fit_portrait       : 1,    // Portrait images will not exceed browser height
        fit_landscape      : 0,    // Landscape images will not exceed browser width
        // Components
        slide_links        : 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
        slides             : [    // Slideshow Images
                                 {image : ctx+'/matrix/login/img/backgrounds/1.jpg'},
                                 {image : ctx+'/matrix/login/img/backgrounds/2.jpg'},
                                 {image : ctx+'/matrix/login/img/backgrounds/3.jpg'}
                             ]
    });
    
    document.onkeydown = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
        	checkLogin();
         }
    }

});
function checkLogin(){
    var username = $("input[name=username]").val();
    var password = $("input[name=password]").val();
    var verfycode = $("input[name=verfycode]").val();

    if ($.trim(username) == "") {
    	$("input[name=username]").focus();
    	tips('用户名不能为空!','username');
        return false;
    }else{
    }
    if ($.trim(password) == "") {
    	$("input[name=password]").focus();
    	tips('密码不能为空!','password');
    	return false;
    }else{
    }
    if ($.trim(verfycode) == "") {
    	$("input[name=verfycode]").focus();
    	tips('验证码不能为空!','verfycode');
    	return false;
    }else{
    }
    var ctx = $("#ctx").val();
    $.ajax({    
        url: ctx+'/manageAdminUser/login',
        data: {
        	adminName : username,
            verify : verfycode,
            passwd : password
        },
        type:'post',   
        success:function(data) {
        	var result = eval('('+data+')'); 
        	if(result.code = '1'){
        		if (result.message == 1) {
        			window.location.href=ctx+"/manageAdminUser/main";
        		}
        		if (result.message == -1) {
        			showMsg("用户名、密码、验证码不能为空");
        			$("input[name=username]").focus();
        		}
        		if (result.message == -2) {
        			showMsg("验证码错误!");
        			$("input[name=verfycode]").focus();
        		}
        		if (result.message == -3) {
        			showMsg("用户不存在!");
        			$("input[name=username]").focus();
        		}
        		if (result.message == -4) {
        			showMsg("用户名或密码错误!");
        			$("input[name=username]").focus();
        		}
        		if (result.message == -5) {
        			showMsg("用户异常，请联系系统管理员！");
        		}
        	}else{
        		showMsg("系统异常");
        	}
        },    
        error : function() {}    
    });
}
function tips(msg,divname){
	layer.tips(msg, 'input[name='+divname+']', {
		  tips: [3,'#000000']
		});
}
function showMsg(msg){
	layer.msg(msg, {time: 2000});
}