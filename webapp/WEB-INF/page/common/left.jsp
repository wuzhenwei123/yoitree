<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside>
	<ul>
		<li style="cursor: pointer;" <c:if test="${nav=='studycenter'}">class="s_act"</c:if> onclick="toStudyCenter()"><span><c:if test="${nav=='studycenter'}"><img src="${ctx}/images/web/tab0a.png" alt=""></c:if><c:if test="${nav!='studycenter'}"><img src="${ctx}/images/web/tab0.png" alt=""></c:if>学习中心</span></li>
		<li style="cursor: pointer;" <c:if test="${nav=='coursecenter'}">class="s_act"</c:if> onclick="toCourseCenter()"><span><c:if test="${nav=='coursecenter'}"><img src="${ctx}/images/web/tab1a.png" alt=""></c:if><c:if test="${nav!='coursecenter'}"><img src="${ctx}/images/web/tab1.png" alt=""></c:if>课程体系</span></li>
		<li style="cursor: pointer;" <c:if test="${nav=='mycenter'}">class="s_act"</c:if> onclick="toMyCenter()"><span><c:if test="${nav=='mycenter'}"><img src="${ctx}/images/web/tab2a.png" alt=""></c:if><c:if test="${nav!='mycenter'}"><img src="${ctx}/images/web/tab2.png" alt=""></c:if>个人中心</span></li>
	</ul>
</aside>
<script type="text/javascript">
function toStudyCenter(){
	window.location.href = "${ctx}/web/toLearnCenter";
}
function toCourseCenter(){
	window.location.href = "${ctx}/web/toCourseCenter";
}
function toMyCenter(){
	window.location.href = "${ctx}/web/toMyInfo";
}
</script>