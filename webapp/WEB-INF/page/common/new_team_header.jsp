<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<div class="header">
		<a class="logo" href=""><img src="${ctx}/images/newweb/logo1.png" alt=""></a>
		<div class="topbar">
			<ul class="cl">
				<c:if test="${admin_user.lower_level_number>0}">
					<li><a href="${ctx}/web/toTeamManage">团队管理</a></li>
					<li><a href="${ctx}/web/toXuanKe">选课管理</a></li>
				</c:if>
				<li><a href="${ctx}/web/toEmploye" <c:if test="${nav_h=='yuangong'}">class="active"</c:if>>员工管理</a></li>
				<li><a href="${ctx}/web/toCompanyInfo" <c:if test="${nav_h=='qiye'}">class="active"</c:if>>企业管理</a></li>
			</ul>
		</div>
		<div class="p_center">
			<a href="javascript:loginOut();" class="l_center" style="margin-right: 80px;">退出登录</a>
			<a href="${ctx}/web/toLearnCenter" class="l_center">学习中心</a>
			<span class="text_el">${admin_user.name}</span>
			<div class="h_portrait">
				<img src="${admin_user.head_img}" alt="" onerror="javascript:this.src='${ctx}/images/web/toux.png'">
			</div>
		</div>
	</div>
</header>
<script src="${ctx}/plus/layer/layer.js"></script>
<script type="text/javascript">
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