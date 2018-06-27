<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--正在学习课程</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
</head>
<body>
<div class="s_xqalert clearfix">
	<h6>${yyUser.name}学习详情</h6>
	<div class="sbtn_group clearfix">
		<button <c:if test="${type==-1}">class="btntv"</c:if> onclick="showDetail(-1)" style="cursor: pointer;">待学课程</button>
		<button <c:if test="${type==0}">class="btntv"</c:if> onclick="showDetail(0)" style="cursor: pointer;">正在学习课程</button>
		<button <c:if test="${type==1}">class="btntv"</c:if> onclick="showDetail(1)" style="cursor: pointer;">已学课程</button>
	</div>
	<ul class="sxq_ul2 clearfix">
		<li class="sli3 clearfix">
			<span>课程名称</span>
			<span style="margin-left: 11px;">开始时间</span>
			<span>结束时间</span>
		</li>
		<c:forEach items="${listNever}" var="uc">
			<li class="sli4 clearfix" id="li_${uc.id}">
				<span class="sp1">
					<c:if test="${uc.style==0}"><i class="i1">必修</i></c:if>
					<c:if test="${uc.style==1}"><i class="i2">自选</i></c:if>
					<em>${uc.themeName}</em> / ${uc.moduleName} / ${uc.lessonName}
				</span>
				<c:if test="${uc.style==0}">
					<span><input class="s_ipt" id="${uc.course_classify_id}_start_${uc.user_id}" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" type="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${uc.start_time}"/>" /></span>
					<span><input class="s_ipt" id="${uc.course_classify_id}_over_${uc.user_id}" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" type="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${uc.over_time}"/>" /></span>
					<span class="s_hov">
						<img class="img8" src="${ctx}/images/web/img8.png" alt="">
						<p class="s_alt" style="display: none;cursor: pointer;"><i onclick="delUc(${uc.course_classify_id},${uc.user_id})">退选课程</i></p>
					</span>
				</c:if>
				<c:if test="${uc.style==1}">
					<span><fmt:formatDate pattern="yyyy-MM-dd" value="${uc.start_time}"/></span>
					<span><fmt:formatDate pattern="yyyy-MM-dd" value="${uc.over_time}"/></span>
				</c:if>
			</li>
		</c:forEach>
	</ul>
</div>
<script>
s_btnact();
$(function(){
	$(".s_alt").hide();
	$(".s_hov").each(function(i){
		$(this).mouseover(function(){
			$(".s_hov:eq("+i+") .s_alt").show();
		});
		$(".s_hov:eq("+i+") .s_alt").mouseout(function(){
			$(".s_alt").hide();
		});
	});
});
function showDetail(type){
	var index = layer.load(1, {
	  shade: [0.1,'#fff']
	});
	window.location.href = "${ctx}/web/toTeamManageDetail?userId=${userId}&type="+type;
}
function OnInput(event) {
    if(event.target.value!=""){
    	var id = event.target.id;
    	var value = event.target.value;
    	$.post("${ctx}/web/updateUserCourseTime",
		{
			lessonIds : id,
			time : value,
			_t : Math.random()
		},
		function(data) {});
    }
}
// Internet Explorer
function OnPropChanged(event) {
    if (event.propertyName.toLowerCase () == "value"&& event.srcElement.value!="") {
        var id = event.srcElement.id;
    	var value = event.srcElement.value;
    	$.post("${ctx}/web/updateUserCourseTime",
		{
			lessonIds : id,
			time : value,
			_t : Math.random()
		},
		function(data) {});
    }
}

function delUc(id,user_id){
	layer.confirm('您确定要退选课程？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.post("${ctx}/web/delUc",
		{
			course_classify_id : id,
			user_id : user_id,
			_t : Math.random()
		},
		function(data) {
			var result = eval('(' + data + ')');
			if (result.c == '0') {
				$("#li_"+id).remove();
				weui.alert("退选成功");
			} else {
				weui.alert(result.m);
			}
		});
	}, function(){
	});
}
</script>
</body>
</html>