<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--课程详情</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css?t=<%=Math.random()%>">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/weui.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<%@ include file="/WEB-INF/page/common/left.jsp" %>
	<p class="ss_back" style="cursor: pointer;" onclick="goBack()"><img src="${ctx}/images/web/back.png" alt="">返回课程体系</p>
	<section class="s_wap" style="top: 102px;">
		<div class="s_banner">
			<p class="s_font20px">${lessonName}<span class="s_yy"><i>${themeName}</i> / ${moduleName} </span></p>
			<img src="${pic}${lessonCover}" alt="" onerror="javascript:this.src='${ctx}/images/web/j.PNG'">
		</div>
		<div class="s_kctxt">
<!-- 			<h1>课程概述：</h1> -->
			<h1>课程介绍：</h1>	
			<p class="p1">${lessonDesc}</p>
		</div>
	</section>
	<section class="s_rb" style="top: 102px;">
		<div class="kc_mulu clearfix">
			<h1>课程目录<span>（${fn:length(listPoint)}）</span></h1>
			<c:forEach items="${listPoint}" var="point" varStatus="status">
				<dl class="s_mludl clearfix" onclick="toPoint(${point.id})" style="cursor: pointer;">
					<dt class="clearfix">
						<p <c:if test="${point.study_state==1}">class="s_typeof s_tpact"</c:if><c:if test="${point.study_state!=1}">class="s_typeof"</c:if>><i <c:if test="${status.index==0 }">class="i1"</c:if>></i><img src="${ctx}/images/web/yuan_icon.png" alt=""></p>
						<p class="p1"><img src="${pic}${point.img_url}" alt="" width="100px;" height="55px;"></p>
						<p class="p2" style="z-index: 9999;position:absolute;">
							<img src="${ctx}/images/web/img13.png" alt="">${point.when_long_str}
						</p>
					</dt>
					<dd>
						<p class="p3">第${status.index+1}课时</p>
						<p class="p4" style="text-overflow:ellipsis;white-space: nowrap;overflow:hidden;">${point.name}</p>
					</dd>
				</dl>
			</c:forEach>
		</div>
	</section>
</section>
<script>
s_nav();//nav选中
s_li();//课程体系li标签选中
$(function(){
	$(".s_tpact img").attr("src","${ctx}/images/web/yuan_icon_on.png");
});
function toPoint(pointId){
	window.location.href = "${ctx}/web/toVideo?id="+pointId;
}
function goBack(){
	window.location.href = "${ctx}/web/toCourseCenter";
}
</script>
</body>
</html>