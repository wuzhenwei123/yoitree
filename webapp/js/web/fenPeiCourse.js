function dianji(id,name,obj){
	if(!$(obj).is(':checked')){
		$("#em_"+id).remove();
		$("#hidd_"+id).remove();
	}else{
		$("#selDiv").append("<em id='em_"+id+"'>"+name+"</em>");
		$("#selDiv").append("<input type='hidden' name='selUser' value='"+id+"' id='hidd_"+id+"'/>");
	}
}
var flag = true;
function sure(){
	var userId="";
	$("input[name='selUser']").each(function(){
		if(userId==""){
			userId = $(this).val();
		}else{
			userId = userId + "," + $(this).val();
		}
	})
	var start_time = $("#start_time").val();
	var over_time = $("#over_time").val();
	if(userId==""){
		layer.msg("请选择学员");
	}else if(start_time==""){
		layer.msg("请选择开始时间");
	}else if(start_time==""){
		layer.msg("请选择结束时间");
	}else{
		if(flag){
			flag = false;
			var index = layer.load(1, {
			  shade: [0.1,'#fff']
			});
			$.post($("#path").val() + "/web/fpCourse",
			{
				userId : userId,
				start_time : start_time,
				over_time : over_time,
				lessonId : $("#lessonId").val(),
				_t : Math.random()
			},
			function(data) {
				flag = true;
				var result = eval('(' + data + ')');
				if (result.c == '0') {
					layer.msg("分配成功");
					setTimeout(closeLayer(),3000);
				} else {
					weui.alert(result.m);
				}
			});
		}
	}
}

function closeLayer(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);   
}