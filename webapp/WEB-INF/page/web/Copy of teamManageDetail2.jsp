<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>学习详情_待学习课程</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
</head>
<body>
<div class="s_xqalert clearfix">
<!-- 	<p class="clearfix"><img class="s_closemc" src="img/close.png" alt=""></p> -->
	<h6>${yyUser.name}学习详情</h6>
	<div class="sbtn_group">
		<button <c:if test="${type==-1}">class="btntv"</c:if> onclick="showDetail(-1)">待学课程</button>
		<button <c:if test="${type==0}">class="btntv"</c:if> onclick="showDetail(0)">正在学习课程</button>
		<button <c:if test="${type==1}">class="btntv"</c:if> onclick="showDetail(1)">已学课程</button>
	</div>
	<ul class="sxq_ul clearfix">
		<li class="sli1 clearfix">
			<span>课程名称</span>
<!-- 			<span>已学时长</span> -->
<!-- 			<span>待学时长</span> -->
			<span>开始时间</span>
			<span>结束时间</span>
		</li>
		<c:forEach items="${list}" var="uc">
			<li class="sli2 clearfix">
				<span class="sp1">
					<c:if test="${uc.style==0}"><i class="i1">必修</i></c:if>
					<c:if test="${uc.style==1}"><i class="i2">自选</i></c:if>
					<em>${uc.themeName}</em> / ${uc.moduleName} / ${uc.lessonName}
				</span>
				<span><fmt:formatDate pattern="yyyy-MM-dd" value="${uc.start_time}"/></span>
<!-- 				<span>12</span> -->
				<span><fmt:formatDate pattern="yyyy-MM-dd" value="${uc.over_time}"/></span>
				<span><img class="img8" src="${ctx}/images/img8.png" alt=""><!-- <img class="img9" src="img/img9.png" alt=""> --></span>
			</li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
function showDetail(type){
	var index = layer.load(1, {
	  shade: [0.1,'#fff']
	});
	window.location.href = "${ctx}/web/toTeamManageDetail?userId=${userId}&type="+type;
}
</script>
</body>
</html>