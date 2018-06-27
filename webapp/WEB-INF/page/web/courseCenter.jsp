<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${_title}--课程体系</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<style type="text/css">
	.s_kcbox{
		width: 85%;
	}
	</style>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<%@ include file="/WEB-INF/page/common/left.jsp" %>
	<section class="s_kcbox">
		<nav class="clearfix">
			<h5>课程分类</h5>
			<ul class="su1 clearfix">
				<c:forEach items="${listTheme}" var="theme">
					<li style="cursor: pointer;" onclick="toTheme(${theme.id})"><span <c:if test="${theme_id==theme.id}">class="sctv"</c:if>>
					<c:choose>
						<c:when test="${theme.id==3}">
							<c:if test="${theme_id==3}"><img src="${ctx}/images/web/list_icon1_on.png" alt=""></c:if>
							<c:if test="${theme_id!=3}"><img src="${ctx}/images/web/list_icon1.png" alt=""></c:if>
						</c:when>
						<c:when test="${theme.id==4}">
							<c:if test="${theme_id==4}"><img src="${ctx}/images/web/list_icon2_on.png" alt=""></c:if>
							<c:if test="${theme_id!=4}"><img src="${ctx}/images/web/list_icon2.png" alt=""></c:if>
						</c:when>
						<c:when test="${theme.id==5}">
							<c:if test="${theme_id==5}"><img src="${ctx}/images/web/list_icon3_on.png" alt=""></c:if>
							<c:if test="${theme_id!=5}"><img src="${ctx}/images/web/list_icon3.png" alt=""></c:if>
						</c:when>
						<c:when test="${theme.id==6}">
							<c:if test="${theme_id==6}"><img src="${ctx}/images/web/list_icon4_on.png" alt=""></c:if>
							<c:if test="${theme_id!=6}"><img src="${ctx}/images/web/list_icon4.png" alt=""></c:if>
						</c:when>
						<c:when test="${theme.id==7}">
							<c:if test="${theme_id==7}"><img src="${ctx}/images/web/list_icon5_on.png" alt=""></c:if>
							<c:if test="${theme_id!=7}"><img src="${ctx}/images/web/list_icon5.png" alt=""></c:if>
						</c:when>
						<c:when test="${theme.id==8}">
							<c:if test="${theme_id==8}"><img src="${ctx}/images/web/list_icon6_on.png" alt=""></c:if>
							<c:if test="${theme_id!=8}"><img src="${ctx}/images/web/list_icon6.png" alt=""></c:if>
						</c:when>
					</c:choose>
					</span><br>${theme.name }</li>
				</c:forEach>
<!-- 				<li style="cursor: pointer;" onclick="toTheme(4)"><span <c:if test="${theme_id==4}">class="sctv"</c:if>><c:if test="${theme_id==4}"><img src="${ctx}/images/web/list_icon2_on.png" alt=""></c:if><c:if test="${theme_id!=4}"><img src="${ctx}/images/web/list_icon2.png" alt=""></c:if></span><br>领导力</li> -->
<!-- 				<li style="cursor: pointer;" onclick="toTheme(8)"><span <c:if test="${theme_id==8}">class="sctv"</c:if>><c:if test="${theme_id==8}"><img src="${ctx}/images/web/list_icon3_on.png" alt=""></c:if><c:if test="${theme_id!=8}"><img src="${ctx}/images/web/list_icon3.png" alt=""></c:if></span><br>运营</li> -->
<!-- 				<li style="cursor: pointer;" onclick="toTheme(6)"><span <c:if test="${theme_id==6}">class="sctv"</c:if>><c:if test="${theme_id==6}"><img src="${ctx}/images/web/list_icon4_on.png" alt=""></c:if><c:if test="${theme_id!=6}"><img src="${ctx}/images/web/list_icon4.png" alt=""></c:if></span><br>人力资源</li> -->
<!-- 				<li style="cursor: pointer;" onclick="toTheme(7)"><span <c:if test="${theme_id==7}">class="sctv"</c:if>><c:if test="${theme_id==7}"><img src="${ctx}/images/web/list_icon5_on.png" alt=""></c:if><c:if test="${theme_id!=7}"><img src="${ctx}/images/web/list_icon5.png" alt=""></c:if></span><br>财务</li> -->
<!-- 				<li style="cursor: pointer;" onclick="toTheme(5)"><span <c:if test="${theme_id==5}">class="sctv"</c:if>><c:if test="${theme_id==5}"><img src="${ctx}/images/web/list_icon6_on.png" alt=""></c:if><c:if test="${theme_id!=5}"><img src="${ctx}/images/web/list_icon6.png" alt=""></c:if></span><br>精益</li> -->
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
					<ul style="width: 100%">
						<c:forEach items="${sub1.listSub}" var="lession">
							<li class="clearfix" style="width: 100%">
								<div style="cursor: pointer;" class="subimg" onclick="toPoint(${lession.id})"><img src="${pic}${lession.img_url}" onerror="javascript:this.src='${ctx}/images/web/j5.PNG'" alt=""></div>
								<div class="subtxt clearfix">
									<p class="p1 clearfix" id="button_${lession.id}">
										<span>${lession.name}</span>
										<c:if test="${lession.is_joined==0}">
											<span style="cursor: pointer;" onclick="addMyCourse(${lession.id},0,this)"><img src="${ctx}/images/web/jia.png" alt="">加入课程表</span>
										</c:if>
										<c:if test="${lession.is_joined==1}">
											<c:if test="${lession.style==1}">
												<span style="cursor: pointer;" onclick="addMyCourse(${lession.id},1,this)"><img src="${ctx}/images/web/jian.png" alt="">移除课程表</span>
											</c:if>
										</c:if>
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
			</c:forEach>
		</c:if>
		<c:if test="${module_id!=null}">
			<section class="s_ubox">
				<ul style="width: 100%">
					<c:forEach items="${listSub2}" var="lession">
						<li class="clearfix" style="width: 100%">
							<div style="cursor: pointer;" class="subimg" onclick="toPoint(${lession.id})"><img src="${pic}${lession.img_url}" onerror="javascript:this.src='${ctx}/images/web/j5.PNG'" alt=""></div>
							<div class="subtxt clearfix">
								<p class="p1 clearfix" id="button_${lession.id}">
									<span>${lession.name}</span>
									<c:if test="${lession.is_joined==0}">
										<span style="cursor: pointer;" onclick="addMyCourse(${lession.id},0,this)"><img src="${ctx}/images/web/jia.png" alt="">加入课程表</span>
									</c:if>
									<c:if test="${lession.is_joined==1}">
										<c:if test="${lession.style==1}">
											<span style="cursor: pointer;" onclick="addMyCourse(${lession.id},1,this)"><img src="${ctx}/images/web/jian.png" alt="">移除课程表</span>
										</c:if>
									</c:if>
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
</section>
<script>
s_nav();//nav选中
// s_li();//课程体系li标签选中
function toTheme(theme_id){
	window.location.href = "${ctx}/web/toCourseCenter?theme_id="+theme_id;
}
function toPoint(id){
	window.location.href = '${ctx}/web/toLessonDetail?lessonId='+id;
}
function toModule(module_id){
	if(module_id==0){
		module_id="";
	}
	window.location.href = "${ctx}/web/toCourseCenter?theme_id=${theme_id}&module_id="+module_id;
}
var b = true;
function addMyCourse(id,type,obj){
	if(b){
		b = false;
		$.post("${ctx}/web/addMyCourse",
		{
			lessonId : id,
			type : type,
			_t : Math.random()
		},
		function(data) {
			b = true;
			var result = eval('(' + data + ')');
			if (result.c == '0') {
				layer.msg(result.m);
				$(obj).remove();
				if(type=="1"){
					$("#button_"+id).append("<span onclick='addMyCourse("+id+",0,this)'><img src='${ctx}/images/web/jia.png'>加入课程表</span>");
				}else{
					$("#button_"+id).append("<span onclick='addMyCourse("+id+",1,this)'><img src='${ctx}/images/web/jian.png'>移除课程表</span>");
				}
			} else {
				layer.msg(result.m);
			}
		});
	}
}
</script>
</body>
</html>