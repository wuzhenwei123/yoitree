<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--团队管理</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<link rel="stylesheet" href="${ctx}/plus/date/skin/WdatePicker.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/js/echarts.common.min.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<script src="${ctx}/js/web/teamManage.js?_t=<%=Math.random()%>"></script>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<input type="hidden" id="path" value="${ctx}"/>
	<%@ include file="/WEB-INF/page/common/team_header.jsp" %>
	<div style="height: 70px;"></div>
<!-- 	<button class="s_xkbtn">选课</button> -->
	<div class="clearfix"><!-- 用来清楚浮动的 --></div>
	<section class="s_tdbox">
		<div class="sec1 clearfix">
			<h5 class="ss_h6">我的团队</h5>
			<p><span><i>${study_time_total}</i>分钟</span><br>总学习课程数</p>
			<p><span><i>${course_count_total}</i>课</span><br>总学习课程数</p>
			<p><span><i>${fn:length(listChild)}</i>人</span><br>总人数</p>
<!-- 			<p><span><i>2</i>位</span><br>总排名</p> -->
		</div>
		<section class="s_secbox clearfix">
			<div class="s_sdmofl">
				<h6 class="ss_h5">员工信息</h6>
				<div class="clearfix"><!-- 用来清楚浮动的 --></div>
				<!-- <p class="p1 clearfix">
					<span>姓名</span>
					<span>员工编号</span>
					<span>总学习课程数</span>
					<span>已学习课程数</span>
					<span>已学时长</span>
					<span>待学时长</span>
				</p> -->
				<ul>
					<li class="sp1 clearfix">
						<span>&nbsp;</span>
						<span>姓名</span>
						<span>员工编号</span>
						<span>总学习课程数</span>
						<span>已学习课程数</span>
						<span>已学时长</span>
<!-- 						<span>待学时长</span> -->
					</li>
					<c:forEach items="${listChild}" var="user" varStatus="status">
						<li>
							<span>${status.index+1}</span>
							<span>${user.name}</span>
							<span>${user.user_number}</span>
							<span>${user.lesson_count}</span>
							<span>${user.studied_count}</span>
							<span>${user.study_time}</span>
<!-- 							<span>${user.study_time}</span> -->
							<span onclick="showStudyDetail(${user.id})" style="cursor: pointer;text-decoration: underline;">学习详情</span>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="s_sdmofr">
				<h6 class="ss_h5">团队统计</h6>
				<div class="count_main" id="main" style="width: 98%;height: 207px;margin-top: 15px;"></div>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('main'));
var option = "{color: ['#feb830','#1eb4ec'],";
option = option + "tooltip : {trigger: 'axis'},legend: {data:['学习时长（分钟）','课程数量']},";
option = option + "grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},";
option = option + "xAxis : [{type : 'category',boundaryGap : false,data : [${user_names}],axisLabel:{interval:0,rotate:-30,}}],";
option = option + "yAxis : [{type : 'value'}],";
option = option + "series : [{name:'学习时长（分钟）',type:'line',label: {normal: {show: true,position: 'top'}},";
option = option + "data:[${study_times}]},{name:'课程数量',type:'line',label: {normal: {show: true,position: 'top'}},";
option = option + "data:[${course_counts}]}]}";
myChart.setOption(eval("("+option+")"));
</script>
</body>
</html>