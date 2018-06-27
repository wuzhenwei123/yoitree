function showDialog(title,msg) {
	$("#myModal").find(".modal-header h3").html(title);
    $("#myModal").attr('class','modal');
    $("#myModal").find(".modal-body p").html(msg);
    setTimeout(function(){
        $("#myModal").attr('class','modal hide');
    },3000);
}

function keydown(e)
{
    var e = e||event;
    var currKey = e.keyCode||e.which||e.charCode;
    if(currKey == 13)
    {
        checkLogin();
    }
}
function getImg(){
	var ctx = $("#ctx").val();
	var url = ctx+'/manageAdminUser/pcrimg?_t='+Math.random();
	$("#verfyImg").attr("src",url);
}
function checkLogin(){
    var username = $("input[name=username]").val();
    var password = $("input[name=password]").val();
    var verfycode = $("input[name=verfycode]").val();

    if ($.trim(username) == "") {
    	$("#username_tip").html("* 请输入账号");
    	$("input[name=username]").focus();
        return false;
    }else{
    	$("#username_tip").html("");
    }
    if ($.trim(password) == "") {
    	$("#password_tip").html("* 请输入密码");
    	$("input[name=password]").focus();
    	return false;
    }else{
    	$("#password_tip").html("");
    }
    if ($.trim(verfycode) == "") {
    	$("#verfycode_tip").html("* 请输入验证码");
    	$("input[name=verfycode]").focus();
    	return false;
    }else{
    	$("#verfycode_tip").html("");
    }
    var ctx = $("#ctx").val();
//    <c:if test="${error == '-1'}">用户名、密码、验证码不能为空!</c:if>
//	<c:if test="${error == '-2'}">验证码错误!</c:if>
//	<c:if test="${error == '-3'}">用户不存在!</c:if>
//	<c:if test="${error == '-4'}">用户名或密码错误!</c:if>
//	<c:if test="${error == '-5'}">用户异常，请联系系统管理员！</c:if>
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
        			getImg();
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
function showMsg(msg){
	$("#errortext").html(msg);
	$("#errortip").show();
}

function validatePwd(str){   
    if(str.length!=0){    
        reg=/^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$/;     
        if(!reg.test(str)){    
            return false;  
        } else {
            return true;
        }   
    } 

    return false;    
}


$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
	        var input = $(this);       
	       
	        $(input).val(input.attr('placeholder'));
	               
	        $(input).focus(function(){
	             if (input.val() == input.attr('placeholder')) {
	                 input.val('');
	             }
	        });
	       
	        $(input).blur(function(){
	            if (input.val() == '' || input.val() == input.attr('placeholder')) {
	                input.val(input.attr('placeholder'));
	            }
	        });
	    });    
    }

});