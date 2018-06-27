<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="clearfix">
	<p class="sp1"><img src="${ctx}/images/web/logo2.png" alt=""></p>
	<c:if test="${admin_user.lower_level_number>0}">
	<p style="cursor: pointer;" class="s_xkp1 <c:if test="${nav_h=='team'}">s_xkpsct</c:if>" onclick="toTeamManage()">团队管理</p>
	<p style="cursor: pointer;" class="s_xkp2 <c:if test="${nav_h=='xuanke'}">s_xkpsct</c:if>" onclick="toXuanKe()">选课管理</p>
	</c:if>
	<c:if test="${admin_user.is_manager==1||admin_user.is_super_manager==1}">
		<p style="cursor: pointer;" class="s_xkp1 <c:if test="${nav_h=='yuangong'}">s_xkpsct</c:if>" onclick="toYuangong()">员工管理</p>
		<p style="cursor: pointer;" class="s_xkp2 <c:if test="${nav_h=='qiye'}">s_xkpsct</c:if>" onclick="toqiye()">企业管理</p>
	</c:if>
	
	<p class="sp2">
		<span>${admin_user.name}</span>
		<span><img src="${admin_user.head_img}" alt="" onerror="javascript:this.src='${ctx}/images/web/toux.png'"></span>
	</p>
	<p class="s_xkp3" onclick="toLeanrCenter()" style="cursor: pointer;">学习中心</p>
	<p class="s_xkp3" onclick="loginOut()" style="cursor: pointer;">退出登录</p>
</header>
<script src="${ctx}/plus/layer/layer.js"></script>
<script type="text/javascript">
function toLeanrCenter(){
	window.location.href = "${ctx}/web/toLearnCenter";
}
function toXuanKe(){
	window.location.href = "${ctx}/web/toXuanKe";
}
function toTeamManage(){
	window.location.href = "${ctx}/web/toTeamManage";
}
function toqiye(){
	window.location.href = "${ctx}/web/toCompanyInfo";
}
function toYuangong(){
	window.location.href = "${ctx}/web/toEmploye";
}
function loginOut(){
	layer.msg('您确定要退出登录吗？', {
	  time: 0 //不自动关闭
	  ,btn: ['确定', '取消']
	  ,yes: function(index){
	    layer.close(index);
	    window.location.href = "${ctx}/web/loginout";
	  }
	});
}
</script>