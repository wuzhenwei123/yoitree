<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--学习中心</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/weui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/swiper.min.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<script src="${ctx}/js/web/swiper.min.js"></script>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<%@ include file="/WEB-INF/page/common/left.jsp" %>
	<section class="s_wap">
		<div class="s_banner">
			<p class="s_p1">最新课程</p>
			<div class="swiper-container" id="swiper-container1">
	            <div class="swiper-wrapper">
	            	<c:forEach items="${listBanner}" var="banner">
	            		<div class="swiper-slide" onclick="toLession(${banner.id})">
		                	<a><img src="${pic}${banner.img_url}" onerror="javascript:this.src='${ctx}/images/web/j.PNG'"/></a>
		                	<p class="s_p2"><span>${banner.theme_name} / ${banner.module_name} / </span>${banner.name}</p>
		                </div>
	            	</c:forEach>
	            </div>
	            <!-- Add Pagination -->
	            <div class="swiper-pagination"></div>
	        </div>
		</div>
		<div class="s_dmo clearfix">
			<h6>
				<span class="h6sp1">必修课程</span>
<!-- 				<span class="h6sp2">更多</span> -->
			</h6>
			<c:forEach items="${listB}" var="bx">
				<div class="smbo s_fl clearfix" onclick="toLession(${bx.course_classify_id})" style="cursor: pointer;">
					<img onerror="javascript:this.src='${ctx}/images/web/j2.PNG'" src="${pic}${us.img_url}" alt="">
					<p class="p1"><span class="s832676">${bx.moduleName} /</span> ${bx.lessonName}</p>
					<p class="p2"><span style="width: <fmt:formatNumber value='${(bx.studyProgress/bx.studyCount)*100}' pattern='#'/>%;background: #832676;">&nbsp;<!-- 进度条 --></span></p>
					<p class="p3 clearfix">
						<span>完成${bx.studyProgress}/${bx.studyCount}</span>
						<span>剩余${bx.overDays}天</span>
					</p>
				</div>
			</c:forEach>
		</div>
		<div class="s_dmo clearfix">
			<h6>
				<span class="h6sp1">选修课程</span>
<!-- 				<span class="h6sp2">更多</span> -->
			</h6>
			<c:forEach items="${listX}" var="bx">
				<div class="smbo s_fl clearfix" onclick="toLession(${bx.course_classify_id})" style="cursor: pointer;">
					<img src="${pic}${bx.img_url}" alt="" onerror="javascript:this.src='${ctx}/images/web/j2.PNG'">
					<p class="p1"><span class="s832676">${bx.moduleName} /</span> ${bx.lessonName}</p>
					<p class="p2"><span style="width: <fmt:formatNumber value='${(bx.studyProgress/bx.studyCount)*100}' pattern='#'/>%;background: #832676;">&nbsp;<!-- 进度条 --></span></p>
					<p class="p3 clearfix">
						<span>完成${bx.studyProgress}/${bx.studyCount}</span>
<!-- 						<span>剩余9天</span> -->
					</p>
				</div>
			</c:forEach>
		</div>
	</section>
	<section class="s_rb">
		<div class="srb1 clearfix">
			<div class="srbdmo clearfix">
				<img src="${admin_user.head_img}" alt="">
				<div class="spb">
					<p class="p1">${admin_user.name}</p>
					<p class="p2">${admin_user.company_name}</p>
<!-- 					<p class="p3"> -->
<!-- 						<i>32</i> 能量泉 -->
<!-- 					</p> -->
				</div>
			</div>
			<div class="srbdmo2 clearfix">
				<p>
					<span>${study_time}</span><span>分</span><br>学习总时长
				</p>
				<p>
					<span>${study_count}</span><span>个</span><br>完成课程
				</p>
				<p>
					<span>0</span><span>次</span><br>提交改善
				</p>
			</div>
			<p class="smor" onclick="toMyCenter()" style="cursor: pointer;">进入个人中心</p>
		</div>
		<div class="srb2 clearfix">
			<h6>我的成就</h6>
<!-- 			<p>我已在 优也树 累计学习4.7小时，击败80%的同事！</p> -->
<!-- 			<p>我已在 优也树 累计学习5门课程，掌握3个工具！</p> -->
			<ul class="u1 clearfix">
				<li>成员</li>
				<li>学习时间</li>
				<li>完成课程</li>
				<li>提交改善</li>
			</ul>
			<c:forEach items="${listSameLevelMsg}" var="sl" varStatus="status">
				<ul  class="u5 clearfix">
					<c:if test="${admin_user.id==sl.id}">
						<li class="u5s"><span>${status.index+1}</span>我</li>
					</c:if>
					<c:if test="${admin_user.id!=sl.id}">
						<li class="u5s"><span>${status.index+1}</span>${sl.name}</li>
					</c:if>
					<li>${sl.study_time}</li>
					<li>${sl.studied_count}</li>
					<li>0</li>
				</ul>
			</c:forEach>
			
<!-- 			<ul  class="u3 clearfix"> -->
<!-- 				<li>小王</li> -->
<!-- 				<li>3</li> -->
<!-- 				<li>1</li> -->
<!-- 				<li>9</li> -->
<!-- 			</ul> -->
<!-- 			<ul  class="u4 clearfix"> -->
<!-- 				<li>小陈</li> -->
<!-- 				<li>3</li> -->
<!-- 				<li>2</li> -->
<!-- 				<li>4</li> -->
<!-- 			</ul> -->
			
		</div>
	</section>
</section>
<script>
	s_nav();
	function toLession(id){
		window.location.href = "${ctx}/web/toLessonDetail?lessonId="+id;
	}
	//轮播图
	window.onload=function(){
		 var swiper = new Swiper('#swiper-container1', {
		    pagination: '.swiper-pagination',
		    paginationClickable: true,
		    spaceBetween: 0,
		    centeredSlides: true,
		    autoplay: 2500,
		    autoplayDisableOnInteraction: false,
		    loop: true
		});  

		//手滑动
		var swiper = new Swiper('.swiper-container2', {
		    slidesPerView: 2.5,
		    paginationClickable: true,
		    spaceBetween: 10,
		    freeMode: true
		});
	};
	function toMyCenter(){
		window.location.href = "${ctx}/web/toMyInfo";
	}
</script>
</body>
</html>