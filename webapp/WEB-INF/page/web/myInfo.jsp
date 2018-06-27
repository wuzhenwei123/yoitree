<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--个人中心</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css?t=<%=Math.random()%>">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<style type="text/css">
	.sswap {
	    position: absolute;
	    top: 70px;
	    left: 13%;
	    margin: 16px;
	}
	</style>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<%@ include file="/WEB-INF/page/common/left.jsp" %>
	<section class="sdmbox1 sswap" >
		<div class="srb1 clearfix">
			<div class="srbdmo clearfix">
				<img src="${admin_user.head_img}" alt="" onerror="javascript:this.src='${ctx}/images/web/toux.png'">
				<div class="spb">
					<p class="p1">${admin_user.name}</p>
					<p class="p2">${admin_user.company_name}</p>
				</div>
			</div>
			<div class="srbdmo2 clearfix">
				<p>
					<span>0</span><span>次</span><br>提交改善
				</p>
				<p>
					<span>${study_count}</span><span>个</span><br>完成课程
				</p>
				<p>
					<span>${study_time}</span><span>分</span><br>学习总时长
				</p>
<!-- 				<p class="s_nlq"> -->
<!-- 					<span><img src="img/img5.png" alt="">32</span><span></span><br>能量泉 -->
<!-- 				</p> -->
			</div>
		</div>
		<section class="ssrb">
			<div class="mobox1">
				<h1>个人资料</h1>
				<ul>
					<li><i>姓名：</i><em>${admin_user.name}</em></li>
					<li><i>生日：</i><em><fmt:formatDate pattern="yyyy-MM-dd" value="${admin_user.birthday}"/></em></li>
					<li><i>学历：</i><em>${admin_user.education}</em></li>
					<li><i>所在部门：</i><em>${admin_user.department}</em></li>
					<li><i>联系电话：</i><em>${admin_user.phone}</em></li>
				</ul>
			</div>
			<div class="mobox4">
				<div class="srb2 clearfix">
					<h6>我的成就</h6>
<!-- 					<p>我已在 优也树 累计学习4.7小时，击败80%的同事！</p> -->
<!-- 					<p>我已在 优也树 累计学习5门课程，掌握3个工具！</p> -->
					<ul class="u1 clearfix">
						<li>成员</li>
						<li>学习时间</li>
						<li>完成课程</li>
						<li>提交改善</li>
					</ul>
					<c:forEach items="${listSameLevelMsg}" var="sl">
						<ul  class="u2 clearfix">
							<c:if test="${admin_user.id==sl.id}">
								<li class="u5s"><span></span>我</li>
							</c:if>
							<c:if test="${admin_user.id!=sl.id}">
								<li class="u5s"><span></span>${sl.name}</li>
							</c:if>
							<li>${sl.study_time}</li>
							<li>${sl.studied_count}</li>
							<li>0</li>
						</ul>
					</c:forEach>
				</div>
			</div>
			<div class="mobox2">
				<h1>已学习的课程</h1>
				<c:forEach items="${list}" var="study">
					<dl class="clearfix" onclick="toPoint(${study.lesson_id})" style="cursor: pointer;">
						<dt><img src="${pic}${study.img_url}" alt="" onerror="javascript:this.src='${ctx}/images/wx/ty_pic.png'"></dt>
						<dd class="sdd1"><i>${study.module_name}</i> / ${study.lesson_name}<span><c:if test="${study.lastStudyTime!=null&&study.lastStudyTime>0}">${study.lastStudyTime}天前</c:if><c:if test="${study.lastStudyTime==null||study.lastStudyTime==0}">今天</c:if></span></dd>
						<dd class="sdd2">
							<c:forEach items="${study.listCourse}" var="course">
								<i>${course.name}</i>
							</c:forEach>
						</dd>
					</dl>
					<ul class="ss_list2 clearfix">
						<c:forEach items="${study.listAppendix}" var="appendix">
							<li style="cursor: pointer;" onclick="download('${appendix.url}')"><img src="${ctx}/images/web/img16.png" alt=""><span>${appendix.name}</span> </li>
						</c:forEach>
					</ul>
				</c:forEach>
				
<!-- 				<ul class="s_123 clearfix fr"> -->
<!-- 					<li><img src="img/s_you.png" alt=""></li> -->
<!-- 					<li class="sact">1</li> -->
<!-- 					<li>2</li> -->
<!-- 					<li>3</li> -->
<!-- 					<li><img src="img/s_you.png" alt=""></li> -->
<!-- 				</ul> -->
			</div>
			
<!-- 			<div class="mobox3"> -->
<!-- 				<h1>我的能量泉</h1> -->
<!-- 				<ul class="u1 clearfix"> -->
<!-- 					<li><span>12</span><br>观看视频</li> -->
<!-- 					<li><span>12</span><br>完成课程</li> -->
<!-- 					<li><span>8</span><br>提交改善</li> -->
<!-- 				</ul> -->
<!-- 				<ul class="u2"> -->
<!-- 					<li class="clearfix"> -->
<!-- 						<p class="p1"><span>观看视频</span><br><i>2017-09-09</i></p> -->
<!-- 						<p class="p2"><em>+ 2</em><img src="img/img5.png" alt=""></p> -->
<!-- 					</li> -->
<!-- 					<li class="clearfix"> -->
<!-- 						<p class="p1"><span>观看视频</span><br><i>2017-09-09</i></p> -->
<!-- 						<p class="p2"><em>+ 2</em><img src="img/img5.png" alt=""></p> -->
<!-- 					</li> -->
<!-- 					<li class="clearfix"> -->
<!-- 						<p class="p1"><span>观看视频</span><br><i>2017-09-09</i></p> -->
<!-- 						<p class="p2"><em>+ 2</em><img src="img/img5.png" alt=""></p> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 				<ul class="s_123 clearfix fr"> -->
<!-- 					<li><img src="img/s_you.png" alt=""></li> -->
<!-- 					<li class="sact">1</li> -->
<!-- 					<li>2</li> -->
<!-- 					<li>3</li> -->
<!-- 					<li><img src="img/s_you.png" alt=""></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
		</section>
	</section>
</section>
<script>
	s_nav();
	function download(path){
		window.location.href = "${ctx}/pic/"+path;
	}
	function toPoint(id){
		window.location.href = '${ctx}/web/toLessonDetail?lessonId='+id;
	}
</script>
</body>
</html>