<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--选课管理</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<style>
	.s_kcbox{
		position: absolute;
		top: 70px;
		left: 0%; 
		margin: 16px;
		width: 64%;
		/*min-height: 903px;*/
	}
	.s_jiaru{
		background-color: #d8d8d8;
		color: #ffffff!important;
		border: 1px solid #d8d8d8!important;
	}
	.s_yixuan{
		border: solid 1px #a4a4a4!important;
		color: #a4a4a4!important;
	}
	</style>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<%@ include file="/WEB-INF/page/common/team_header.jsp" %>
	<section class="s_kcbox">
		<nav class="clearfix">
			<h5>课程分类</h5>
			<ul class="su1 clearfix">
				<li style="cursor: pointer;" onclick="toTheme(3)"><span <c:if test="${theme_id==3}">class="sctv"</c:if>><c:if test="${theme_id==3}"><img src="${ctx}/images/web/list_icon1_on.png" alt=""></c:if><c:if test="${theme_id!=3}"><img src="${ctx}/images/web/list_icon1.png" alt=""></c:if></span><br>通用</li>
				<li style="cursor: pointer;" onclick="toTheme(4)"><span <c:if test="${theme_id==4}">class="sctv"</c:if>><c:if test="${theme_id==4}"><img src="${ctx}/images/web/list_icon2_on.png" alt=""></c:if><c:if test="${theme_id!=4}"><img src="${ctx}/images/web/list_icon2.png" alt=""></c:if></span><br>领导力</li>
				<li style="cursor: pointer;" onclick="toTheme(8)"><span <c:if test="${theme_id==8}">class="sctv"</c:if>><c:if test="${theme_id==8}"><img src="${ctx}/images/web/list_icon3_on.png" alt=""></c:if><c:if test="${theme_id!=8}"><img src="${ctx}/images/web/list_icon3.png" alt=""></c:if></span><br>运营</li>
				<li style="cursor: pointer;" onclick="toTheme(6)"><span <c:if test="${theme_id==6}">class="sctv"</c:if>><c:if test="${theme_id==6}"><img src="${ctx}/images/web/list_icon4_on.png" alt=""></c:if><c:if test="${theme_id!=6}"><img src="${ctx}/images/web/list_icon4.png" alt=""></c:if></span><br>人力资源</li>
				<li style="cursor: pointer;" onclick="toTheme(7)"><span <c:if test="${theme_id==7}">class="sctv"</c:if>><c:if test="${theme_id==7}"><img src="${ctx}/images/web/list_icon5_on.png" alt=""></c:if><c:if test="${theme_id!=7}"><img src="${ctx}/images/web/list_icon5.png" alt=""></c:if></span><br>财务</li>
				<li style="cursor: pointer;" onclick="toTheme(5)"><span <c:if test="${theme_id==5}">class="sctv"</c:if>><c:if test="${theme_id==5}"><img src="${ctx}/images/web/list_icon6_on.png" alt=""></c:if><c:if test="${theme_id!=5}"><img src="${ctx}/images/web/list_icon6.png" alt=""></c:if></span><br>精益</li>
			</ul>
			<ul class="su2 clearfix">
				<li onclick="toModule(0)" <c:if test="${module_id==null}">class="sctv"</c:if>>全部</li>
				<c:forEach items="${listSub1}" var="sub1">
					<li style="cursor: pointer;" onclick="toModule(${sub1.id})" <c:if test="${module_id!=null&&sub1.id==module_id}">class="sctv"</c:if>>${sub1.name}</li>
				</c:forEach>
			</ul>
		</nav>
		<c:if test="${module_id==null}">
			<c:forEach items="${listSub1}" var="sub1">
				<section class="s_ubox">
					<h6>${sub1.name}</h6>
					<ul>
						<c:forEach items="${sub1.listSub}" var="lession">
							<li class="clearfix">
								<div style="cursor: pointer;" class="subimg" onclick="toPoint(${lession.id})"><img src="${pic}${lession.img_url}" onerror="javascript:this.src='${ctx}/images/web/j5.PNG'" alt=""></div>
								<div class="subtxt clearfix">
									<p class="p1 clearfix">
										<span>${lession.name}</span>
										<span style="cursor: pointer;" onclick="fenPei(${lession.id})"><img src="${ctx}/images/web/jia.png" alt="">分配课程</span>
									</p>
									<p class="p2">
										<span><i>课时：</i>${lession.point_total}课</span>
										<span><i>时长：</i>${lession.point_time}分钟/课</span>
									</p>
									<p class="p3" title="${lession.info}">${lession.info}</p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</section>
			</c:forEach>
		</c:if>
		<c:if test="${module_id!=null}">
			<section class="s_ubox">
				<ul>
					<c:forEach items="${listSub2}" var="lession">
						<li class="clearfix">
							<div style="cursor: pointer;" class="subimg" onclick="toPoint(${lession.id})"><img src="${pic}${lession.img_url}" onerror="javascript:this.src='${ctx}/images/web/j5.PNG'" alt=""></div>
							<div class="subtxt clearfix">
								<p class="p1 clearfix">
									<span>${lession.name}</span>
									<span style="cursor: pointer;" onclick="fenPei(${lession.id})"><img src="${ctx}/images/web/jia.png" alt="">分配课程</span>
								</p>
								<p class="p2">
									<span><i>课时：</i>${lession.point_total}课</span>
									<span><i>时长：</i>${lession.point_time}分钟/课</span>
								</p>
								<p class="p3">${lession.info}</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</section>
		</c:if>
	</section>
	<section class="s_ybmo">
		<p class="pto clearfix">
			<span class="s1">选课情况</span>
<!-- 			<span class="s2">选课</span> -->
		</p>
		<ul class="ptu1 clearfix">
			<li style="cursor: pointer;" class="pli1 s_borderb" onclick="showTab(1,this)">
				<span class="sp1">${fn:length(listW)}</span><br>
				<span class="sp2">未开始<br>已选课程</span>
			</li>
			<li style="cursor: pointer;" class="pli2" onclick="showTab(2,this)">
				<span class="sp3">${fn:length(listJ)}</span><br>
				<span class="sp4">进行中 <br>已选课程</span>
			</li>
			<li style="cursor: pointer;" class="pli2" onclick="showTab(3,this)">
				<span class="sp3">${fn:length(listE)}</span><br>
				<span class="sp4">已结束课程</span>
			</li>
		</ul>
		<ul class="ptu2" id="wei">
			<c:forEach items="${listW}" var="uc">
				<li>
					<p class="p1 clearfix">
						<span class="sp1"><i>${uc.themeName}</i> / ${uc.moduleName} /  ${uc.lessonName}</span>
						<span class="sp2"><fmt:formatDate pattern="yyyy-MM-dd" value="${uc.start_time}"/>    即将开始</span>
					</p>
					<p class="p2 clearfix">
						<span class="sp1 clearfix">
							<c:forEach items="${uc.listUC}" var="sub">
								<em>${sub.user_name}</em>
							</c:forEach>
						</span>
						<span class="sp2">共${fn:length(uc.listUC)}人</span>
					</p>
				</li>
			</c:forEach>
		</ul>
		<ul class="ptu2" id="jin" style="display: none;">
			<c:forEach items="${listJ}" var="uc">
				<li>
					<p class="p1 clearfix">
						<span class="sp1"><i>${uc.themeName}</i> / ${uc.moduleName} /  ${uc.lessonName}</span>
						<span class="sp2">    进行中</span>
					</p>
					<p class="p2 clearfix">
						<span class="sp1 clearfix">
							<c:forEach items="${uc.listUC}" var="sub">
								<em>${sub.user_name}</em>
							</c:forEach>
						</span>
						<span class="sp2">共${fn:length(uc.listUC)}人</span>
					</p>
				</li>
			</c:forEach>
		</ul>
		<ul class="ptu2" id="jie" style="display: none;">
			<c:forEach items="${listE}" var="uc">
				<li>
					<p class="p1 clearfix">
						<span class="sp1"><i>${uc.themeName}</i> / ${uc.moduleName} /  ${uc.lessonName}</span>
						<span class="sp2"><fmt:formatDate pattern="yyyy-MM-dd" value="${uc.over_time}"/>    已结束</span>
					</p>
					<p class="p2 clearfix">
						<span class="sp1 clearfix">
							<c:forEach items="${uc.listUC}" var="sub">
								<em>${sub.user_name}</em>
							</c:forEach>
						</span>
						<span class="sp2">共${fn:length(uc.listUC)}人</span>
					</p>
				</li>
			</c:forEach>
		</ul>
	</section>
</section>
<script>

// s_li();//课程体系li标签选中
s_xkpsct();
function toTheme(theme_id){
	window.location.href = "${ctx}/web/toXuanKe?theme_id="+theme_id;
}
function toPoint(id){
	window.location.href = '${ctx}/web/toLessonDetail?lessonId='+id;
}
function toModule(module_id){
	if(module_id==0){
		module_id="";
	}
	window.location.href = "${ctx}/web/toXuanKe?theme_id=${theme_id}&module_id="+module_id;
}
function showTab(val,obj){
	$(obj).parent().find("li").each(function(){
		$(this).removeClass("s_borderb");
	})
	$(obj).addClass("s_borderb");
	if(val=="1"){
		$("#wei").show();
		$("#jin").hide();
		$("#jie").hide();
	}else if(val=="2"){
		$("#wei").hide();
		$("#jin").show();
		$("#jie").hide();
	}else if(val=="3"){
		$("#wei").hide();
		$("#jin").hide();
		$("#jie").show();
	}
}
function fenPei(id){
	layer.open({
		type: 2,
		title: '分配课程',
		shadeClose: false,
		shade: 0.8,
		area: ['65%', '75%'],
		content: '${ctx}/web/toFpCourse?lessonId='+id//iframe的url
	});
}
</script>
</body>
</html>