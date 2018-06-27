<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="text/html;charset=utf-8" http-equiv="content-type">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<meta content="webkit|ie-comp|ie-stand" name="renderer">
		<title>${_title}--企业管理</title>
		<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/base.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/company.css" />
		<script src="${ctx}/js/web/jquery-2.1.4.min.js"></script>
		<script src="${ctx}/plus/layer/layer.js"></script>
	</head>
	<body>
		<%@ include file="/WEB-INF/page/common/new_team_header.jsp" %>
		<section class="wrap">
			<div class="content box_shadow">
				<h3 class="title">企业管理</h3>
				<div class="com_list">
					<ul>
						<li>
							<label>企业编号：</label>
							<span>${yyCompany.code}</span>
						</li>
						<li>
							<label>统一社会信用代码：</label>
							<span>${yyCompany.credit_code}</span>
						</li>
						<li>
							<label>服务期限：</label>
							<span><fmt:formatDate pattern="yyyy-MM-dd" value="${yyCompany.start_time}"/> 至 <fmt:formatDate pattern="yyyy-MM-dd" value="${yyCompany.end_time}"/></span>
						</li>
					</ul>
				</div>
			</div>
			<div class="content box_shadow">
				<h3 class="title">其他信息<a href="javascript:updateInfo()" class="btn_edit"></a></h3>
				<div class="com_list">
					<ul>
						<li>
							<label>行业：</label>
							<span>${yyCompany.industry_name}</span>
						</li>
						<li>
							<label>员工数：</label>
							<span>${yyCompany.employees_name}</span>
						</li>
						<li>
							<label>营业额：</label>
							<span>${yyCompany.turnover_name}</span>
						</li>
						<li>
							<label>联系人：</label>
							<span>${yyCompany.contact_name}</span>
						</li>
						<li>
							<label>联系电话：</label>
							<span>${yyCompany.contact_phone}</span>
						</li>
						<li>
							<label>学习风格：</label>
							<span>${learnStyle}</span>
						</li>
					</ul>
				</div>
			</div>
		</section>

		<footer>
			
		</footer>
	</body>
	<script type="text/javascript">
		function updateInfo(){
			layer.open({
				type: 2,
				title: '编辑企业信息',
				shadeClose: false,
				shade: 0.8,
				area: ['60%', '70%'],
				content: '${ctx}/web/toUpdateCompanyInfo' //iframe的url
			});
		}
	</script>
</html>
