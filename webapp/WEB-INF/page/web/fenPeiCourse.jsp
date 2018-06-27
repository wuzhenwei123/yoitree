<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--选课</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" href="${ctx}/plus/date/skin/WdatePicker.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/plus/date/WdatePicker.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<script src="${ctx}/js/web/fenPeiCourse.js?t=<%=Math.random()%>"></script>
</head>
<body>
<div class="s_xkalert">
	<input type="hidden" id="path" value="${ctx}"/>
	<input type="hidden" id="lessonId" value="${lessonId}"/>
<!-- 	<p class="clearfix"><img class="s_closemc" src="img/close.png" alt=""></p> -->
	<h6>分配课程</h6>
	<section class="s_bmo clearfix">
		<p class="p1">
			<span>1</span>
			<span>选择学员</span>
		</p>
<!-- 		<p class="p2">已选择2名学员</p> -->
		<p class="p3 clearfix" id="selDiv">
			<c:forEach items="${listUser}" var="u">
				<em id="em_${u.user_id}">${u.user_name}</em>
				<input type="hidden" name="selUser" value="${u.user_id}" id="hidd_${u.user_id}"/>
			</c:forEach>
		</p>
		<ul class="su1 clearfix">
			<c:forEach items="${list}" var="user">
				<li>
	                <div class="smon_checkbox">
						<label></label>
						<input type="checkbox" class="s_ckipt" value="${user.id}" <c:if test="${user.is_select!=null&&user.is_select==1}">checked="checked"</c:if> onclick="dianji('${user.id}','${user.name}',this)">${user.name}
	                </div>
					<span class="sptxt">${user.levelName}</span>
				</li>
			</c:forEach>
		</ul>
	</section>
	<section class="s_bmo2">
		<p class="p1">
			<span>2</span>
			<span>选择课程</span>
		</p>
		<ul class="su2">
			<li class="sli1 clearfix">
				<p>课程名称</p>	
				<p>开始时间</p>	
				<p>结束时间</p>	
			</li>
			<li class="sli2 clearfix">
				<p>
					<input class="s_jhua" list="s_jhua" value="${yyCourseClassify.name}"/>
<!-- 					<datalist id="s_jhua"> -->
<!-- 						<option value="${yyCourseClassify.name}"></option> -->
<!-- 						<option value="生产的计划和交付"></option> -->
<!-- 						<option value="生产的计划和交付"></option> -->
<!-- 					</datalist> -->
				</p>
				<p><input type="text" id="start_time" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${userCourse.start_time}"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="widows: 145px;height: 34px;margin-left: -16px;"/> </p>
				<p><input type="text" id="over_time" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${userCourse.over_time}"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="widows: 145px;height: 34px;margin-left: -16px;"/> </p>
			</li>
		</ul>
<!-- 		<p class="p2 clearfix"><img src="img/jia2.png" alt="">添加新课程</p> -->
		<p class="p3 clearfix">
			<button class="btn1" onclick="sure()">确认</button>
			<button class="btn2" onclick="closeLayer()">取消</button>
		</p>
	</section>
</div>
</body>
</html>