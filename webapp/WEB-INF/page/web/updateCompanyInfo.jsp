<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="text/html;charset=utf-8" http-equiv="content-type">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<meta content="webkit|ie-comp|ie-stand" name="renderer">
		<title>${_title}--企业信息编辑</title>
		<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/base.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/company.css" />
		<script src="${ctx}/js/web/jquery-2.1.4.min.js"></script>
		<script src="${ctx}/js/web/jquery.slimscroll.js"></script>
		<script src="${ctx}/plus/layer/layer.js"></script>
		<script src="${ctx}/js/web/cm.js"></script>
		<style>
			body{
				background: #ffffff;
			}
			.check_box span {
			    float: left;
			    font-size: 14px;
			    color: #a4a4a4;
			    margin-right: 30px;
			    cursor: pointer;
			    height: 30px;
			    line-height: 30px;
			}
		</style>
	</head>
	<body>
		<div class="edit_box cl" style="margin: -33px auto;">
<!-- 			<a href="javascript:;" class="btn_close"></a> -->
			<h3>编辑企业信息</h3>
			<div class="edit_b">
				<ul>
					<li>
						<label for="industry">行业</label>
						<div class="sel_box">
							<input type="text" id="industry" value="${yyCompany.industry_name}" class="sel_text" readonly>
							<input type="hidden" id="industryId" value="${yyCompany.industry_id}" class="sel_text1">
							<div class="sel_list">
								<ul>
									<c:forEach var="industry" items="${listIndustry}">
										<li id="${industry.id}">${industry.name}</li>
									</c:forEach>
								</ul>
							</div>

						</div>
					</li>

					<li>
						<label for="staff">员工数</label>
						<div class="sel_box">
							<input type="text" id="staff" value="${yyCompany.employees_name}" class="sel_text" readonly>
							<input type="hidden" id="employeesId" value="${yyCompany.employees_id}" class="sel_text1">
							<div class="sel_list">
								<ul>
									<c:forEach var="employees" items="${listEmployees}">
										<li id="${employees.id}">${employees.name}</li>
									</c:forEach>
								</ul>
							</div>

						</div>
					</li>

					<li>
						<label for="turnover">营业额</label>
						<div class="sel_box">
							<input type="text" id="turnover" value="${yyCompany.turnover_name}" class="sel_text" readonly>
							<input type="hidden" id="turnoverId" value="${yyCompany.turnover_id}" class="sel_text1">
							<div class="sel_list">
								<ul>
									<c:forEach var="turnover" items="${listTurnover}">
										<li id="${turnover.id}">${turnover.name}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</li>

					<li>
						<label for="linkman">联系人</label>
						<input type="text" id="linkman" value="${yyCompany.contact_name}">
					</li>

					<li>
						<label for="linkphone">联系电话</label>
						<input type="text" id="linkphone" value="${yyCompany.contact_phone}">
					</li>

					<li>
						<label>学习风格</label>
						<div class="check_box" id="fg">
							<c:forEach items="${listLearningStyle}" var="ls">
								<span id="${ls.id}" <c:forEach items="${listCompanyLearnStyle}" var="ls1"><c:if test="${ls.id==ls1.learning_style_id}">class="active"</c:if></c:forEach>><s><i></i></s>${ls.name}</span>
							</c:forEach>
						</div>
					</li>
				</ul>
			</div>
			<div class="btn_box">
				<a href="javascript:closeD();" class="btn_cancel">取消</a>
				<a href="javascript:ok();" class="btn_ok">确定</a>
			</div>
		</div>
	</body>
	<script>
		$(function () {
			slideUpAndDown();
			$(".check_box span").on("click",function () {
//                 $(this).toggleClass("active");
                $(this).addClass("active").siblings().removeClass("active");
            })
        })
        function closeD(){
			 var index = parent.layer.getFrameIndex(window.name);  
             parent.layer.close(index);  
		}
		function ok(){
			var industryId = $("#industryId").val();
			var employeesId = $("#employeesId").val();
			var turnoverId = $("#turnoverId").val();
			var contact_name = $("#linkman").val();
			if(contact_name==""){
				layer.tips('请输入联系人', '#linkman');
				return false;
			}
			var contact_phone = $("#linkphone").val();
			if(contact_name==""){
				layer.tips('请输入联系电话', '#linkphone');
				return false;
			}
			var learningStyle_ids = "";
			$(".check_box span").each(function(){
				if($(this).hasClass("active")){
					if(learningStyle_ids==""){
						learningStyle_ids = $(this).attr("id");
					}else{
						learningStyle_ids = learningStyle_ids + "," + $(this).attr("id");
					}
				}
			})
			if(learningStyle_ids==""){
				layer.tips('请选择学习风格', '#fg');
				return false;
			}
			$.post("${ctx}/web/updateCompanyInfo",
			{
				industryId : industryId,
				employeesId : employeesId,
				turnoverId : turnoverId,
				contact_name : contact_name,
				contact_phone : contact_phone,
				learningStyle_ids : learningStyle_ids,
				_t : Math.random()
			},
			function(data) {
				var result = eval('(' + data + ')');
				if (result.c == '0') {
					layer.msg("编辑成功");
					setTimeout(toqiye(),2000);
				} else {
					layer.msg(result.m);
				}
			});
		}
		function slideUpAndDown(){
		    var _this = $(".sel_box");
		    _this.each(function() {
		        //下拉超过5行显示滚动条
		        if ($(this).find('li').length > 5) {
		            $(this).find('ul').slimscroll({
		                height: "200px"
		            })
		        }
		    });
		    _this.on("click", function(e) {
		        stopPropagation(e);
		        $(this).toggleClass('active');
		        if ($(this).hasClass('active')) {
		            $(this).find('.sel_list').show()
		        } else {
		            $(this).find('.sel_list').hide()
		        }
		    })
		    _this.find('li').on("click", function(e) {
		        stopPropagation(e);
		        $(this).parents(".sel_box").removeClass('active');
		        $(this).parents(".sel_box").find('.sel_text').val($(this).text());
		        $(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id"));
		        $(this).parents(".sel_box").find('.sel_list').hide();
		    })
		}
		
		function stopPropagation(e){
			e = e || window.event;
		    if (e.stopPropagation)
		        e.stopPropagation();
		    else {
		        e.cancelBubble = true;
		    }
		}
		
		function toqiye(){
			parent.window.location.href = "${ctx}/web/toCompanyInfo";
		}
	</script>
</html>
