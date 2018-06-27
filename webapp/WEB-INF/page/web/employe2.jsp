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
	</head>
	<body>
		<%@ include file="/WEB-INF/page/common/new_team_header.jsp" %>
		<section class="wrap" style="min-width: 1200px;">
			<div class="content box_shadow">
				<div class="cl">
					<h3 class="title fl">员工信息</h3>
					<a href="javascript:addNew();" class="fr btn btn_addpeople"><img class="icon" src="${ctx}/images/newweb/add_people.png" alt="">添加新员工</a>
				</div>
				<div class="staff_top cl">
					<div class="fl">
						<label for="sort">排列方式</label>
						<div class="sel_box">
							<input type="text" id="sort" class="sel_text" value="组织架构" readonly>
							<div class="sel_list">
								<ul>
									<li>普通</li>
									<li>组织架构</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="fr">
						<i style="left: 45%;top: 10px;"><img class="icon" src="${ctx}/images/newweb/search.png" alt=""></i>
						<input type="text" class="search" value="${name}" placeholder="员工搜索">
					</div>
				</div>
				<!--组织架构-->
				<div class="staff_con staff_organ">
					<dl>
						<dt class="staff_title">
							<ul class="cl">
								<li class="st_ll st_name">
									<a href="javascript:;">姓名</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">员工编号</a>
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
									<a href="javascript:;">所属管理人员</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">性别</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">生日</a>
								</li>
								<li class="st_m">
									<a href="javascript:;">学历</a>
								</li>
								<li class="st_l">
									<a href="javascript:;">手机号</a>
								</li>
								<li class="st_l"></li>
							</ul>
						</dt>
						<!--头部end-->
						<c:forEach items="${userList}" var="user" varStatus="status">
							<dd>
								<ul class="cl" id="ul_${user.id}">
									<li class="st_ll st_cname" id="li_${user.id}" style="color: #4a4a4a">
										<c:if test="${user.lower_level_number>0}">
											<a href="javascript:void(0);" onclick="openLevel(${user.id},this)" class="js_unfold"><s></s><i <c:if test="${user.is_super_manager==1||user.is_manager==1}">class="ic_admin"</c:if>></i>${user.name}</a>
										</c:if>
										<c:if test="${user.lower_level_number==0}">
											<a href="javascript:;"><i <c:if test="${user.is_super_manager==1||user.is_manager==1}">class="ic_admin"</c:if>></i>${user.name}</a>
										</c:if>
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
		function openLevel(id,obj){
			$.get("${ctx}/web/getChildEmploye?id="+id,function(data){
				var result = eval('(' + data + ')');
    			if (result.c == '0') {
    				$("#le_"+id).remove();
    				var item = result.d;
    				var html = '<div class="staff_list" id="le_'+id+'"><i class="line"></i><dl>';
    				for(var i=0;i<item.length;i++){
    					html = html + '<dd><ul class="cl" id="ul_'+item[i].id+'">';
    					if(item[i].lower_level_number>0){
    						if(item[i].is_super_manager=="1"||item[i].is_manager=="1"){
	    						html = html + '<li class="st_ll st_cname" id="'+item[i].id+'" style="color: #4a4a4a"><a href="javascript:void(0);" onclick="openLevel('+item[i].id+',this)"  class="js_unfold"><s></s><i class="ic_admin"></i>'+item[i].name+'</li>';
    						}else{
    							html = html + '<li class="st_ll st_cname" id="'+item[i].id+'" style="color: #4a4a4a"><a href="javascript:void(0);" onclick="openLevel('+item[i].id+',this)"  class="js_unfold"><s></s><i></i>'+item[i].name+'</li>';
    						}
    					}else{
    						if(item[i].is_super_manager=="1"||item[i].is_manager=="1"){
	    						html = html + '<li class="st_ll st_cname" style="color: #4a4a4a"><a href="javascript:;"><s></s><i class="ic_admin"></i>'+item[i].name+'</a></li>';
    						}else{
	    						html = html + '<li class="st_ll st_cname" style="color: #4a4a4a"><a href="javascript:;"><i></i>'+item[i].name+'</a></li>';
    						}
    					}
    					html = html + '<li class="st_l"><a href="javascript:;">'+item[i].user_number+'</a></li>';
    					if(typeof(item[i].education)=="undefined"){
	    					html = html + '<li class="st_l"><a href="javascript:;"></a></li>';
    					}else{
	    					html = html + '<li class="st_l"><a href="javascript:;">'+item[i].job+'</a></li>';
    					}
    					html = html + '<li class="st_l"><a href="javascript:;">'+item[i].is_select+'</a></li>';
    					html = html + '<li class="st_l"><a href="javascript:;">'+item[i].department+'</a></li>';
    					html = html + '<li class="st_ll"><a href="javascript:;">'+item[i].parent_name+'</a></li>';
    					if(item[i].sex=="1"){
    						html = html + '<li class="st_l"><a href="javascript:;">男</a></li>';
    					}else{
    						html = html + '<li class="st_l"><a href="javascript:;">女</a></li>';
    					}
    					if(typeof(item[i].education)=="undefined"){
	    					html = html + '<li class="st_l"><a href="javascript:;"></a></li>';
    					}else{
	    					html = html + '<li class="st_l"><a href="javascript:;">'+item[i].remark1+'</a></li>';
    					}
    					if(typeof(item[i].education)=="undefined"){
	    					html = html + '<li class="st_m"><a href="javascript:;"></a></li>';
    					}else{
	    					html = html + '<li class="st_m"><a href="javascript:;">'+item[i].education+'</a></li>';
    					}
    					html = html + '<li class="st_l"><a href="javascript:;">'+item[i].phone+'</a></li>';
    					html = html + '<li class="st_l"><a href="javascript:;" class="btn_more"><img src="${ctx}/images/newweb/more.png" alt=""></a><div class="st_box">';
    					html = html + '<a href="javascript:toUpdateEmploye('+item[i].id+');">编辑信息</a><a href="javascript:toCourseDetail('+item[i].id+');">课程详情</a><a href="javascript:setAdmin('+item[i].id+');">设为管理员</a><a href="javascript:stopAccount('+item[i].id+');" class="btn_disable">停用账号</a>';
    					html = html + '</div></li></ul></dd>';
    				}
    				html = html + "</dl></div>";
    				$("#ul_"+id).after(html);
    				
    				$(obj).toggleClass("open");
    				var w = $(obj).parents("li").width()-18;
                    var ow = $(obj).parents("ul").width();
    				if($(obj).hasClass("open")){
    	                $(obj).parents("ul").css("background","#f9f9f9");
    				    $(obj).parents("ul").siblings(".staff_list").slideDown();
    	                var e = $(obj).parents("ul").siblings(".staff_list").children("dl").children("dd").children("ul").find(".st_cname");
    	                var ww = e.parents("ul").width();
    	                w = ow == ww ? w+18 : w;
    					e.css("width",parseFloat(w/ww*100).toFixed(2)+"%")
    				}else {
    	                $(obj).parents("ul").siblings(".staff_list").slideUp();
    	                $(obj).parents("ul").css("background","#ffffff");
    				}
    				$(".btn_more").on("click",function (e) {
    	                $(".st_box").hide()
    				    stopPropagation(e);
    					$(this).siblings(".st_box").show();
    	            })
	    		}else{
	    			layer.msg(result.m);
	    		}
			});
		}
        $(function () {
        	slideUpAndDown();

            
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

			//展开架构
// 			$(".js_unfold").on("click",function () {
// 				$(this).toggleClass("open");
// 				var w = $(this).parents("li").width()-18;
//                 var ow = $(this).parents("ul").width();
// 				if($(this).hasClass("open")){
//                     $(this).parents("ul").css("background","#f9f9f9");
// 				    $(this).parents("ul").siblings(".staff_list").slideDown();
//                     var e = $(this).parents("ul").siblings(".staff_list").children("dl").children("dd").children("ul").find(".st_cname");
//                     var ww = e.parents("ul").width();
//                     w = ow == ww ? w+18 : w;
// 					e.css("width",parseFloat(w/ww*100).toFixed(2)+"%")
// 				}else {
//                     $(this).parents("ul").siblings(".staff_list").slideUp();
//                     $(this).parents("ul").css("background","#ffffff");
// 				}
//             })
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
    	      	window.location.href = "${ctx}/web/toEmploye2?name="+search;
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
	</script>
</html>
