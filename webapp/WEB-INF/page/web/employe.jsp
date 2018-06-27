<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="text/html;charset=utf-8" http-equiv="content-type">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<meta content="webkit|ie-comp|ie-stand" name="renderer">
		<title>${_title}--员工管理</title>
		<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/base.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/staff.css" />
		<script src="${ctx}/js/web/jquery-2.1.4.min.js"></script>
		<script src="${ctx}/js/web/jquery.slimscroll.js"></script>
		<script src="${ctx}/plus/layer/layer.js"></script>
<!-- 		<script src="${ctx}/js/web/cm.js"></script> -->
	</head>
	<body>
		<%@ include file="/WEB-INF/page/common/new_team_header.jsp" %>
		<section class="wrap">
			<div class="content box_shadow">
				<div class="cl">
					<h3 class="title fl">员工信息</h3>
					<a href="javascript:addNew();" class="fr btn btn_addpeople"><img class="icon" src="${ctx}/images/newweb/add_people.png" alt="">添加新员工</a>
				</div>
				<div class="staff_top cl">
					<div class="fl">
						<label for="sort">排列方式</label>
						<div class="sel_box">
							<input type="text" id="sort" class="sel_text" value="普通" readonly>
							<div class="sel_list">
								<ul>
									<li>普通</li>
									<li>组织架构</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="fl">
						<label for="filter"><img class="icon" src="${ctx}/images/newweb/filter.png" alt="">筛选方式</label>
						<div class="sel_box">
							<input type="text" id="filter" class="sel_text" value="<c:if test="${parent_ids==null&&is_manager==null}">无</c:if><c:if test="${parent_ids!=null}">所有无上级人员</c:if><c:if test="${is_manager!=null}">所有管理员</c:if>" readonly>
							<div class="sel_list">
								<ul>
									<li>无</li>
									<li>所有管理员</li>
									<li>所有无上级人员</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="fr">
						<i style="left: 45%;top: 10px;"><img class="icon" src="${ctx}/images/newweb/search.png" alt=""></i>
						<input type="text" class="search" value="${name}" placeholder="员工搜索">
					</div>
				</div>
				<div class="staff_con">
					<dl>
						<dt class="staff_title">
							<ul class="cl">
								<li class="st_ll st_name">
									<a href="javascript:;">
										姓名
										<c:if test="${orderBy=='name'}">
											<c:if test="${sort=='desc'}"><i onclick="paixu('name',this)" class='down'></i></c:if>
											<c:if test="${sort=='asc'}"><i onclick="paixu('name',this)" class='up'></i></c:if>
										</c:if>
										<c:if test="${orderBy!='name'}">
											<i onclick="paixu('name',this)"></i>
										</c:if>
									</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">员工编号
										<c:if test="${orderBy=='user_number'}">
											<c:if test="${sort=='desc'}"><i onclick="paixu('user_number',this)" class='down'></i></c:if>
											<c:if test="${sort=='asc'}"><i onclick="paixu('user_number',this)" class='up'></i></c:if>
										</c:if>
										<c:if test="${orderBy!='user_number'}">
											<i onclick="paixu('user_number',this)"></i>
										</c:if>
									</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">职位</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">层级</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">所属部门</a>
								</li>
								<li class="st_ll">
									<a href="javascript:;">所属管理人员
										<c:if test="${orderBy=='parent_id'}">
											<c:if test="${sort=='desc'}"><i onclick="paixu('parent_id',this)" class='down'></i></c:if>
											<c:if test="${sort=='asc'}"><i onclick="paixu('parent_id',this)" class='up'></i></c:if>
										</c:if>
										<c:if test="${orderBy!='parent_id'}">
											<i onclick="paixu('parent_id',this)"></i>
										</c:if>
									</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">性别
										<c:if test="${orderBy=='sex'}">
											<c:if test="${sort=='desc'}"><i onclick="paixu('sex',this)" class='down'></i></c:if>
											<c:if test="${sort=='asc'}"><i onclick="paixu('sex',this)" class='up'></i></c:if>
										</c:if>
										<c:if test="${orderBy!='sex'}">
											<i onclick="paixu('sex',this)"></i>
										</c:if>
									</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">生日
										<c:if test="${orderBy=='birthday'}">
											<c:if test="${sort=='desc'}"><i onclick="paixu('birthday',this)" class='down'></i></c:if>
											<c:if test="${sort=='asc'}"><i onclick="paixu('birthday',this)" class='up'></i></c:if>
										</c:if>
										<c:if test="${orderBy!='birthday'}">
											<i onclick="paixu('birthday',this)"></i>
										</c:if>
									</a>
								</li>
								<li class="st_m">
									<a href="javascript:;">学历</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">手机号
										<c:if test="${orderBy=='phone'}">
											<c:if test="${sort=='desc'}"><i onclick="paixu('phone',this)" class='down'></i></c:if>
											<c:if test="${sort=='asc'}"><i onclick="paixu('phone',this)" class='up'></i></c:if>
										</c:if>
										<c:if test="${orderBy!='phone'}">
											<i onclick="paixu('birthday',this)"></i>
										</c:if>
									</a>
								</li>
								<li class="st_l"></li>
							</ul>
						</dt>
						<c:forEach items="${userList}" var="user" varStatus="status">
							<dd>
								<ul class="cl">
									<li class="st_ll st_cname">
										<a href="javascript:;"><c:if test="${user.is_super_manager==1||user.is_manager==1}"><i class="ic_admin"></i></c:if>${user.name}</a>
									</li>
									<li class="st_l">
										<a href="javascript:;">${user.user_number}<i></i></a>
									</li>
									<li class="st_l">
										<a href="javascript:;">${user.job}<i></i></a>
									</li>
									<li class="st_l">
										<a href="javascript:;">${user.is_select}<i></i></a>
									</li>
									<li class="st_l">
										<a href="javascript:;">${user.department}<i></i></a>
									</li>
									<li class="st_ll">
										<a href="javascript:;">${user.parent_name}<i></i></a>
									</li>
									<li class="st_l">
										<a href="javascript:;"><c:if test="${user.sex==2}">女</c:if><c:if test="${user.sex==1}">男</c:if><i></i></a>
									</li>
									<li class="st_l">
										<a href="javascript:;"><fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthday}"/><i></i></a>
									</li>
									<li class="st_m">
										<a href="javascript:;">${user.education}<i></i></a>
									</li>
									<li class="st_l">
										<a href="javascript:;">${user.phone}<i></i></a>
									</li>
									<li class="st_l">
										<c:if test="${user.is_super_manager==0}">
											<a href="javascript:;" class="btn_more">
												<img src="${ctx}/images/newweb/more.png" alt="">
											</a>
											<div class="st_box">
												<a href="javascript:toUpdateEmploye(${user.id});">编辑信息</a>
												<a href="javascript:toCourseDetail(${user.id});">课程详情</a>
												<a href="javascript:setAdmin(${user.id});">设为管理员</a>
												<a href="javascript:stopAccount(${user.id});" class="btn_disable">停用账号</a>
											</div>
										</c:if>
									</li>
								</ul>
							</dd>
						
						</c:forEach>
					</dl>
				</div>
			</div>
		</section>

		<footer>
			
		</footer>
	</body>
	<script>
        $(function () {
            slideUpAndDown();

            //排序
			$(".staff_title a").on("click",function () {
			    var _this = $(this).find("i")
				if(_this.hasClass("up")){
                    _this.removeClass("up").addClass("down");
                    return false;
				}else if(_this.hasClass("down")){
                    _this.removeClass("down").addClass("up");
                    return false;
                }else {
                    _this.addClass("up");//默认
				}
            })
            // 搜素
			$(".search").on("focus",function () {
				if($.trim($(this).val()) == ""){
				    $(this).siblings().hide();
				}
            })

            $(".search").on("blur",function () {
                if($.trim($(this).val()) == ""){
                    $(this).siblings().show();
                }
            })

			//更多操作
			$(".btn_more").on("click",function (e) {
                $(".st_box").hide()
			    stopPropagation(e);
				$(this).siblings(".st_box").show();
            })
			$(document).on("click",function () {
				$(".st_box").hide()
            })
        })
        
        function toUpdateEmploye(id){
        	layer.open({
				type: 2,
				title: '编辑员工信息',
				shadeClose: false,
				shade: 0.8,
				area: ['60%', '70%'],
				content: '${ctx}/web/toUpdateEmploye?id='+id //iframe的url
			});
        }
        
        function toCourseDetail(id){
        	layer.open({
        		type: 2,
        		title: '学习详情',
        		shadeClose: false,
        		shade: 0.8,
        		area: ['70%', '75%'],
        		content: '${ctx}/web/toTeamManageDetail?userId='+id//iframe的url
        	});
        }
        
        function stopAccount(id){
        	layer.msg('您确定要停用此账号吗？', {
        		  time: 0 //不自动关闭
        		  ,btn: ['确定', '取消']
        		  ,yes: function(index){
        		    layer.close(index);
        		    $.post("${ctx}/web/stopAccount",
   		    		{
       		    		id : id,
   		    			_t : Math.random()
   		    		},
   		    		function(data) {
   		    			var result = eval('(' + data + ')');
   		    			if (result.c == '0') {
   		    				layer.msg(result.m);
   		    			} else {
   		    				layer.msg(result.m);
   		    			}
   		    		});
        		  }
        	});
        }
        
        function setAdmin(id){
        	layer.msg('您确定要将此账号设为管理员吗？', {
        		  time: 0 //不自动关闭
        		  ,btn: ['确定', '取消']
        		  ,yes: function(index){
        		    layer.close(index);
        		    $.post("${ctx}/web/setAdmin",
   		    		{
       		    		id : id,
   		    			_t : Math.random()
   		    		},
   		    		function(data) {
   		    			var result = eval('(' + data + ')');
   		    			if (result.c == '0') {
   		    				layer.msg(result.m);
   		    			} else {
   		    				layer.msg(result.m);
   		    			}
   		    		});
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
		        $(this).parents(".sel_box").find('.sel_list').hide();
		        if("普通"==$(this).text()||"无"==$(this).text()){
		        	window.location.href = "${ctx}/web/toEmploye";
		        }else if("所有管理员"==$(this).text()){
		        	window.location.href = "${ctx}/web/toEmploye?is_manager=1";
		        }else if("所有无上级人员"==$(this).text()){
		        	window.location.href = "${ctx}/web/toEmploye?parent_ids=isnull";
		        }else if("组织架构"==$(this).text()){
		        	window.location.href = "${ctx}/web/toEmploye2";
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
		
		//回车事件
		document.onkeydown=function(event){
        	var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){ 
            	var search = $(".search").val();
    	      	window.location.href = "${ctx}/web/toEmploye?name="+search;
           	}
       	};
       	
       	function addNew(){
       		layer.open({
				type: 2,
				title: '编辑员工信息',
				shadeClose: false,
				shade: 0.8,
				area: ['60%', '70%'],
				content: '${ctx}/web/toAddEmploye' //iframe的url
			});
       	}
       	function paixu(val,obj){
       		if($(obj).hasClass("up")){
       			$(obj).attr("class","down");
       			window.location.href = "${ctx}/web/toEmploye?orderBy="+val+"&sort=desc";
       		}else if($(obj).hasClass("down")){
       			$(obj).attr("class","up");
       			window.location.href = "${ctx}/web/toEmploye?orderBy="+val+"&sort=asc";
       		}else{
       			$(obj).attr("class","up");
	       		window.location.href = "${ctx}/web/toEmploye?orderBy="+val+"&sort=asc";
       		}
       	}
	</script>
</html>
