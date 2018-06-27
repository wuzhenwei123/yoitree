<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>课程视频</title>
	<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/style.css?t=<%=Math.random()%>">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/web/styleup.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/weui.css">
	<script src="${ctx}/js/wx/jquery.min.js"></script>
	<script src="${ctx}/js/web/sjq.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<style type="text/css">
		video::-internal-media-controls-download-button {
		    display:none;
		}
		video::-webkit-media-controls-enclosure {
		    overflow:hidden;
		}
		video::-webkit-media-controls-panel {
		    width: calc(100% + 30px); 
		}
	</style>
</head>
<body style="background: #fafafa;">
<input type="hidden" id="path" value="${ctx}">
<section class="s_box2">
	<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<%@ include file="/WEB-INF/page/common/left.jsp" %>
	<p class="ss_back" style="cursor: pointer;" onclick="goBack()"><img src="${ctx}/images/web/back.png" alt="">返回课程详情</p>
	<section class="s_wap" style="top: 102px;">
		<div class="s_banner">
<!-- 			<p class="s_font20px fl" style=" white-space:nowrap;display:inline-block;line-height: 0.9;"><span style="width: 280px;display:inline-block;text-overflow:ellipsis;white-space: nowrap;overflow:hidden;">${pointName}</span><span class="s_yy" style="display:inline-block;"><i>${themeName}</i> / ${moduleName}/ ${lessonName} </span></p> -->
<!-- 			<button class="s_qhbtn" onclick="playVideo(this)">切换到音频模式</button> -->
			<p class="s_font20px fl" style="width: 100%;">${pointName}<span class="s_yy"><br><i>${themeName}</i> / ${moduleName}/ ${lessonName} </span><button class="s_qhbtn" style="margin: 0;" onclick="playVideo(this)">切换到音频模式</button></p>
			<video class="news_video" src="${yyCourse.video_play}" width="100%" height="378px;" controls autobuffer id="media">
			</video>
		</div>
		<div class="s_kctxt2 clearfix">
<!-- 			<h1>课程知识点<span>（${fn:length(listC)}）</span></h1> -->
<!-- 			<c:forEach items="${listC}" var="course"> -->
<!-- 				<dl class="s_mludl clearfix s_lefbor"> -->
<!-- 					<dt class="clearfix"> -->
<!-- 						<p class="p1"></p> -->
<!-- 						<p class="p2"> -->
<!-- 							<img src="${pic}${course.img_url}" alt="">${course.when_long_str} -->
<!-- 						</p> -->
<!-- 					</dt> -->
<!-- 					<dd> -->
<!-- 						<p class="p3">${course.name}</p> -->
<!-- 						<p class="p4">${course.info}</p> -->
<!-- 					</dd> -->
<!-- 				</dl> -->
<!-- 			</c:forEach> -->
			<h1>知识点介绍</h1>
			<p class="p1" style="font-size: 14px;">${yyCourse.info}</p>
		</div>
	</section>
	<section class="s_rb" style="top: 102px;">
		<div class="kc_mulu clearfix">
			<h1>课程目录<span>（${fn:length(listC)}）</span></h1>
			<c:forEach items="${listC}" var="point" varStatus="status">
				<dl class="s_mludl clearfix" style="cursor: pointer;" onclick="toCourse(${point.id})">
					<dt class="clearfix">
						<p <c:if test="${point.study_state==1}">class="s_typeof s_tpact"</c:if><c:if test="${point.study_state!=1}">class="s_typeof"</c:if>><i <c:if test="${status.index==0 }">class="i1"</c:if>></i><img src="${ctx}/images/web/yuan_icon.png" alt=""></p>
						<p class="p1"><img src="${pic}${point.img_url}" alt="" width="100px;" height="55px;"></p>
						<p class="p2" style="z-index: 9999;position:absolute;">
							<img src="${ctx}/images/web/img13.png" alt="">${point.when_long_str}
						</p>
						
					</dt>
					<dd>
						<p class="p3">第${status.index+1}课时</p>
						<p class="p4"  style="text-overflow:ellipsis;white-space: nowrap;overflow:hidden;">${point.name}</p>
					</dd>
				</dl>
			</c:forEach>
		</div>
		<div class="kc_gongju clearfix">
			<h1>课程工具<span>（${fn:length(listAppendix)}）</span></h1>
			<c:forEach items="${listAppendix}" var="appendix">
				<p class="ss_list">
					<img src="${ctx}/images/web/list_icon3_on.png" alt="">
					<img src="${ctx}/images/web/img16.png" alt="" onclick="downLoad('${appendix.url}')" style="cursor: pointer;">
					<span>${appendix.name} </span>
				</p>
			</c:forEach>
		</div>
	</section>
</section>
<script>
var start_time;
var end_time;
var is_over=0;
var midea_type=2;
$.ajaxSetup({
	async: false
});
s_nav();//nav选中
s_li();//课程体系li标签选中
$(function(){
	//课程知识点鼠标滑过有边框
	$(".s_kctxt2 .s_mludl").hover(function(){
		$(this).addClass("s_lefbor").siblings().removeClass("s_lefbor");
	})
	//课程目录
	$(".s_tpact img").attr("src","${ctx}/images/web/yuan_icon_on.png");
	
	/*视频播放状态*/
    $('.news_video').bind('play', function () {
        start_time = new Date().Format("yyyy-MM-dd HH:mm:ss"); 
    });

    /*视频结束或错误*/
    $('.news_video').bind('error ended', function(){
        is_over = 1;
        end_time = new Date().Format("yyyy-MM-dd HH:mm:ss");
        var d1 = new Date(start_time);
        var d2 = new Date(end_time);
        var long_time = parseInt(d2 - d1) / 1000;
        $.post("<c:url value='/web/studyRecord'/>",
        {
        	start_time : start_time,
        	end_time : end_time,
        	long_time : long_time,
        	is_over : is_over,
        	pointId : '${yyCourse.id}',
  			 _t:Math.random()},
  	       	function(data){});
    })

    /*视频暂停*/
    $('.news_video').bind('pause', function () {
        end_time = new Date().Format("yyyy-MM-dd HH:mm:ss");
        var d1 = new Date(start_time);
        var d2 = new Date(end_time);
        var long_time = parseInt(d2 - d1) / 1000;
        $.post("<c:url value='/web/studyRecord'/>",
        {
        	start_time : start_time,
        	end_time : end_time,
        	long_time : long_time,
        	is_over : is_over,
        	pointId : '${yyCourse.id}',
  			 _t:Math.random()},
  	       	function(data){
  				start_time = new Date().Format("yyyy-MM-dd HH:mm:ss"); 
  			 });
    });
	
	$(document).bind("keydown",function(e){ 
		e=window.event||e; 
		if(e.keyCode==116){ 
			e.keyCode = 0; 
			return false; 
		} 
	});
});
function downLoad(url){
	window.location.href = "${ctx}/pic"+url;
}

function playVideo(obj){
	var myVideo = document.getElementById("media");
	var curTime=myVideo.currentTime;
	//获取当前播放时间
	if(midea_type==1){
		var src = '${yyCourse.video_play}';
		$("#media").attr("src",src);
		$("#videobutton").addClass("s_active");
		$("#audiobutton").removeClass("s_active");
		$(obj).html("切换到音频模式");
		midea_type = 2;
	}else{
		midea_type = 1;
		var src = '${yyCourse.audio_play}';
		$("#media").attr("src",src);
		$("#audiobutton").addClass("s_active");
		$("#videobutton").removeClass("s_active");
		$(obj).html("切换到视频模式");
	}
	var myVideo1 = document.getElementById("media");
	myVideo1.currentTime=curTime;
	myVideo1.play();
}

Date.prototype.Format = function (fmt) { //author: meizz   
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "H+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
}

function toCourse(pointId){
	window.location.href = "${ctx}/web/toVideo?id="+pointId;
}

function goBack(){
	window.location.href = "${ctx}/web/toLessonDetail?lessonId=${lessonId}";
}
</script>
</body>
</html>