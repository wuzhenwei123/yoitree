<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="text/html;charset=utf-8" http-equiv="content-type">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<meta content="webkit|ie-comp|ie-stand" name="renderer">
		<title>${_title}--编辑员工信息</title>
		<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/base.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/staff.css" />
		<link rel="stylesheet" href="${ctx}/plus/date/skin/WdatePicker.css">
		<script src="${ctx}/js/web/jquery-2.1.4.min.js"></script>
		<script src="${ctx}/js/web/jquery.slimscroll.js"></script>
		<script src="${ctx}/plus/layer/layer.js"></script>
		<script src="${ctx}/js/web/cm.js"></script>
		<script src="${ctx}/plus/date/WdatePicker.js"></script>
		<style>
			body{
				background: #ffffff;
			}
			.content_order_list_all{
				position: relative;
			}
			.tk_add{
				position: absolute;
				right: 4px;
				top: 8px;
			}
			.tk_add img{
				width: 20px;
				height: 20px;
			}
		</style>
	</head>
	<body>
		<div class="edit_staff cl">
			<h3>编辑员工信息</h3>
			<div class="edit_s">
				<p class="edit_title"><i>1</i>员工基本信息</p>
				<ul>
					<li>
						<label for="ename">姓名</label>
						<input type="text" id="ename" value="${yyUser.name}">
					</li>

					<li>
						<label for="ephone" class="edit_phone">手机号<i>作为登录账号</i></label>
						<input type="text" id="ephone" value="${yyUser.login_name}">
					</li>

					<li>
						<label for="estaff">员工号</label>
						<input type="text" id="estaff" value="${yyUser.user_number}">
					</li>

					<li>
						<label for="eposition">职位</label>
						<input type="text" id="eposition" value="${yyUser.job}">
					</li>

					<li>
						<label for="department">所属部门</label>
						<input type="text" id="department" value="${yyUser.department}">
					</li>

					<li>
						<label>性别</label>
						<div class="check_box">
							<input type="hidden" id="sex" value="${yyUser.sex}">
							<span id="sex_1" <c:if test="${yyUser.sex==1}">class="active"</c:if>><s><i></i></s>男</span>
							<span id="sex_2" <c:if test="${yyUser.sex==2}">class="active"</c:if>><s></s>女</span>
						</div>
					</li>

					<li>
						<label for="edate">出生日期</label>
						<input type="text" id="birthday" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${yyUser.birthday}"/>" id="edate" class="btn_date" readonly>
					</li>

					<li>
						<label for="education">学历</label>
						<input type="text" id="education" value="${yyUser.education}">
					</li>
					<c:if test="${yyUser==null}">
						<div class="content_order_list_all">
							<div id="content_order_list" class="content_order_list">
								<li>
									<label for="education">职能</label>
									<div class="sel_box">
										<input type="text" id="fc1" placeholder="请选择" class="sel_text" readonly style="width: 185px;">
										<div class="sel_list">
											<ul>
												<c:forEach items="${listF}" var="fc1">
													<li id="fc1_${fc1.id}">${fc1.name}</li>
												</c:forEach>
											</ul>
										</div>
									</div>
									<div class="sel_box" id="sel_box_fc_0">
										<input type="text" class="sel_text" id="fct_0" placeholder="请选择" readonly style="width: 185px;">
										<input type="hidden" id="fc_0" name="fc" class="sel_text1" readonly>
										<div class="sel_list">
											<ul id="fc2_0">
												
											</ul>
										</div>
									</div>
								</li>
							</div>
							<a href="javascript:void(0);" class="tk_add"><img src="${ctx}/images/wx/add_icon1.png"/></a>
						</div>
					</c:if>
				</ul>
			</div>
			<div class="edit_s">
				<p class="edit_title"><i>2</i>组织关系信息</p>
				<ul>
					<li>
						<label for="manager">所属管理员</label>
						<div class="sel_box">
							<input type="text" id="manager" placeholder="请选择" class="sel_text" readonly value="${yyUser.parent_name}">
							<input type="hidden" id="parent_id" class="sel_text1" readonly value="${yyUser.parent_id}">
							<div class="sel_list">
								<ul>
									<c:forEach var="user" items="${list}">
										<li id="user_${user.id}">${user.name}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</li>

					<li>
						<label>是否为管理员</label>
                        <div class="check_box">
                        	<input type="hidden" id="is_manager" value="${yyUser.is_manager}">
                            <span id="is_manager_1" <c:if test="${yyUser.is_manager==1}">class="active"</c:if>><s><i></i></s>是</span>
                            <span id="is_manager_0" <c:if test="${yyUser.is_manager==0}">class="active"</c:if>><s></s>否</span>
                        </div>
					</li>

				</ul>
			</div>
			<div class="btn_box">
				<a href="javascript:close();" class="btn_cancel">取消</a>
				<a href="javascript:ok();" class="btn_ok">确定</a>
			</div>
		</div>
	</body>
	<script>
		function close(){
			 var index = parent.layer.getFrameIndex(window.name);  
             parent.layer.close(index); 
		}
        $(function () {
            slideUpAndDown();

            $(".check_box span").on("click",function () {
                $(this).addClass("active").siblings().removeClass("active");
                var id = $(this).attr("id");
                var len = id.split("_").length;
                if(parseInt(len,10)>2){
                	$("#"+id.split("_")[0]+"_"+id.split("_")[1]).val(id.split("_")[2]);
                }else{
                	$("#"+id.split("_")[0]).val(id.split("_")[1]);
                }
            })
        })
        
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
		        var len = $(this).attr("id");
                if(parseInt(len,10)>2){
                	$(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id").split("_")[2]);
                }else{
                	$(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id").split("_")[1]);
                }
		        $(this).parents(".sel_box").find('.sel_list').hide();
		        if("fc1"==$(this).attr("id").split("_")[0]){
		        	getFc2($(this).attr("id").split("_")[1],0);
		        }
		    })
		}
        function slideUpAndDown2(id,index){
		    var _this = $("#"+id);
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
		        var len = $(this).attr("id");
                if(parseInt(len,10)>2){
                	$(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id").split("_")[2]);
                }else{
                	$(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id").split("_")[1]);
                }
		        $(this).parents(".sel_box").find('.sel_list').hide();
		        if("fc1"==$(this).attr("id").split("_")[0]){
		        	getFc2($(this).attr("id").split("_")[1],index);
		        }
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
		function ok(){
			var ename = $("#ename").val();
			if(ename==""){
				layer.msg('请输入姓名');
				return false;
			}
			var ephone = $("#ephone").val();
			if(ephone==""){
				layer.msg('请输入手机号');
				return false;
			}else{
				if (!validatemobile(ephone)) {
					layer.msg('请输入正确的手机号');
					return false;
				}
			}
			var estaff = $("#estaff").val();
			if(estaff==""){
				layer.msg('请输入员工号');
				return false;
			}
			var eposition = $("#eposition").val();
			if(eposition==""){
				layer.msg('请输入职位');
				return false;
			}
			var department = $("#department").val();
			if(department==""){
				layer.msg('请输入所属部门');
				return false;
			}
			var sex = $("#sex").val();
			if(sex==""){
				layer.msg('请选择性别');
				return false;
			}
			var birthday = $("#birthday").val();
			if(birthday==""){
				layer.msg('请输入生日');
				return false;
			}
			var education = $("#education").val();
			if(education==""){
				layer.msg('请输入学历');
				return false;
			}
			
			var addFlag = '${addFlag}';
			var fc = "";
			if(addFlag!=""){
				$("input[name='fc']").each(function(){
					if(fc==""){
						fc = $(this).val();
					}else{
						fc = fc + "," + $(this).val();
					}
				})
				if(fc==""){
					layer.msg('请输选择职能');
					return false;
				}
			}
			var parent_id = $("#parent_id").val();
			if(parent_id==""){
				layer.msg('请选择所属管理员');
				return false;
			}
			var is_manager = $("#is_manager").val();
			if(is_manager==""){
				layer.msg('请选择是否为管理员');
				return false;
			}
			var index1 = layer.load(1, {
			  shade: [0.1,'#fff']
			});
			$.post("${ctx}/web/updateEmploye", {
				id : '${yyUser.id}',
				name : ename,
				fc : fc,
				login_name : ephone,
				phone : ephone,
				user_number : estaff,
				job : eposition,
				department : department,
				sex : sex,
				birthday : birthday,
				is_manager : is_manager,
				education : education,
				parent_id : parent_id,
				_t : Math.random()
			}, function(data) {
				var result = eval('(' + data + ')');
				if (result.c == '0') {
					layer.msg("操作成功");
					parent.window.location.href="${ctx}/web/toEmploye";
				} else {
					parent.layer.close(index1); 
					layer.msg(result.m);
				}
			});
		}
		
		function validatemobile(mobile) {
			if (mobile.length == 0) {
				return false;
			}
			if (mobile.length != 11) {
				return false;
			}
			var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
			if (!myreg.test(mobile)) {
				return false;
			}
			return true;
		}
		
		function getFc2(parent_id,index){
			$("#fc2_"+index).empty();
			$.getJSON("<c:url value='/yyFunction/getYyFunctionBaseList'/>",
	        {
				parent_id: parent_id, 
				_t: Math.random()
	        },function(data){
	            var result = data;
	            if (result.code == 1) {
	            	for (var k=0; k<result.items.length; k++){  
	            		$("#fc2_"+index).append("<li id='fcx_"+result.items[k].id+"'>"+result.items[k].name+"</li>");
	            	}
		            slideUpAndDown1(index);
	            } else {
	            	layer.meg("系统异常!");
	            } 
		    });
		}
		
		function slideUpAndDown1(index){
		    var _this = $("#sel_box_fc_"+index);
		    _this.find('li').on("click", function(e) {
		        stopPropagation(e);
		        $(this).parents(".sel_box").removeClass('active');
		        $(this).parents(".sel_box").find('.sel_text').val($(this).text());
		        var len = $(this).attr("id");
                if(parseInt(len,10)>2){
                	$(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id").split("_")[2]);
                }else{
                	$(this).parents(".sel_box").find('.sel_text1').val($(this).attr("id").split("_")[1]);
                }
		        $(this).parents(".sel_box").find('.sel_list').hide();
		    })
		}
		
		var i = 0;
		$(".tk_add").click(function(){
			i = i + 1;
		    $(".content_order_list_all").append($("#content_order_list").first().clone());
		    $(".content_order_list_all").find(".content_order_list").each(function(index){
		    	if(index==i){
		    		$(this).find(".sel_text").parents(".sel_box").each(function(index1){
		    			if(index1==1){
			    			$(this).attr("id","sel_box"+index);
		    			}else{
		    				$(this).attr("id","sel_box_fc_"+index);
		    			}
		    		})
		    		$("#sel_box"+index).find("input").each(function(){
						$(this).val('');
					});
		    		$("#sel_box_fc_"+index).find("input").each(function(){
						$(this).val('');
					});
		    		$("#sel_box_fc_"+index).find("ul").each(function(){
		    			$(this).attr("id","fc2_"+index);
		    		});
		    		slideUpAndDown2("sel_box"+index,index);
		    		slideUpAndDown2("sel_box_fc_"+index,index);
		    	}
		    })
		});
    </script>
</html>
