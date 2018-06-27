//nav切换
function s_nav(){
	var path = $("#path").val();
	$("aside ul li").each(function(){
		$(this).click(function(){
			$(this).addClass("s_act").siblings().removeClass("s_act");
			if ($("aside ul li:eq(0)").hasClass("s_act")) {
				$("aside ul li:eq(0) img").attr("src",path+"/images/web/tab0a.png");
				$("aside ul li:eq(1) img").attr("src",path+"/images/web/tab1.png");
				$("aside ul li:eq(2) img").attr("src",path+"/images/web/tab2.png");
			};
			if ($("aside ul li:eq(1)").hasClass("s_act")) {
				$("aside ul li:eq(0) img").attr("src",path+"/images/web/tab0.png");
				$("aside ul li:eq(1) img").attr("src",path+"/images/web/tab1a.png");
				$("aside ul li:eq(2) img").attr("src",path+"/images/web/tab2.png");
			};
			if ($("aside ul li:eq(2)").hasClass("s_act")) {
				$("aside ul li:eq(0) img").attr("src",path+"/images/web/tab0.png");
				$("aside ul li:eq(1) img").attr("src",path+"/images/web/tab1.png");
				$("aside ul li:eq(2) img").attr("src",path+"/images/web/tab2a.png");
			};
		});
	});
};
//课程体系中选中的li状态
function s_li(){
	var path = $("#path").val();
	//默认第三个有下划线
//	$(".s_kcbox .su1 li:eq(0)").css({"border-bottom":"4px solid #2c7ec2","color":"#4a4a4a"});
	$(".s_kcbox .su1 li").each(function(index){
//		$(this).click(function(){
		if($(this).find("span").hasClass("sctv")){
			$(this).css({"border-bottom":"4px solid #2c7ec2","color":"#4a4a4a"}).siblings().css({"border-bottom":"0","color":"#a4a4a4"});
			$(this).find("span").addClass("sctv");
			$(".s_kcbox .su1 li:not(:eq("+index+")) span").removeClass("sctv");
			if ($(".s_kcbox .su1 li:eq(0) span").hasClass("sctv")) {
				$(".s_kcbox .su1 li:eq(0) span img").attr("src",path+"/images/web/list_icon1_on.png");
				$(".s_kcbox .su1 li:eq(1) span img").attr("src",path+"/images/web/list_icon2.png");
				$(".s_kcbox .su1 li:eq(2) span img").attr("src",path+"/images/web/list_icon3.png");
				$(".s_kcbox .su1 li:eq(3) span img").attr("src",path+"/images/web/list_icon4.png");
				$(".s_kcbox .su1 li:eq(4) span img").attr("src",path+"/images/web/list_icon5.png");
				$(".s_kcbox .su1 li:eq(5) span img").attr("src",path+"/images/web/list_icon6.png");
			};
			if ($(".s_kcbox .su1 li:eq(1) span").hasClass("sctv")) {
				$(".s_kcbox .su1 li:eq(0) span img").attr("src",path+"/images/web/list_icon1.png");
				$(".s_kcbox .su1 li:eq(1) span img").attr("src",path+"/images/web/list_icon2_on.png");
				$(".s_kcbox .su1 li:eq(2) span img").attr("src",path+"/images/web/list_icon3.png");
				$(".s_kcbox .su1 li:eq(3) span img").attr("src",path+"/images/web/list_icon4.png");
				$(".s_kcbox .su1 li:eq(4) span img").attr("src",path+"/images/web/list_icon5.png");
				$(".s_kcbox .su1 li:eq(5) span img").attr("src",path+"/images/web/list_icon6.png");
			};
			if ($(".s_kcbox .su1 li:eq(2) span").hasClass("sctv")) {
				$(".s_kcbox .su1 li:eq(0) span img").attr("src",path+"/images/web/list_icon1.png");
				$(".s_kcbox .su1 li:eq(1) span img").attr("src",path+"/images/web/list_icon2.png");
				$(".s_kcbox .su1 li:eq(2) span img").attr("src",path+"/images/web/list_icon3_on.png");
				$(".s_kcbox .su1 li:eq(3) span img").attr("src",path+"/images/web/list_icon4.png");
				$(".s_kcbox .su1 li:eq(4) span img").attr("src",path+"/images/web/list_icon5.png");
				$(".s_kcbox .su1 li:eq(5) span img").attr("src",path+"/images/web/list_icon6.png");
			};
			if ($(".s_kcbox .su1 li:eq(3) span").hasClass("sctv")) {
				$(".s_kcbox .su1 li:eq(0) span img").attr("src",path+"/images/web/list_icon1.png");
				$(".s_kcbox .su1 li:eq(1) span img").attr("src",path+"/images/web/list_icon2.png");
				$(".s_kcbox .su1 li:eq(2) span img").attr("src",path+"/images/web/list_icon3.png");
				$(".s_kcbox .su1 li:eq(3) span img").attr("src",path+"/images/web/list_icon4_on.png");
				$(".s_kcbox .su1 li:eq(4) span img").attr("src",path+"/images/web/list_icon5.png");
				$(".s_kcbox .su1 li:eq(5) span img").attr("src",path+"/images/web/list_icon6.png");
			};
			if ($(".s_kcbox .su1 li:eq(4) span").hasClass("sctv")) {
				$(".s_kcbox .su1 li:eq(0) span img").attr("src",path+"/images/web/list_icon1.png");
				$(".s_kcbox .su1 li:eq(1) span img").attr("src",path+"/images/web/list_icon2.png");
				$(".s_kcbox .su1 li:eq(2) span img").attr("src",path+"/images/web/list_icon3.png");
				$(".s_kcbox .su1 li:eq(3) span img").attr("src",path+"/images/web/list_icon4.png");
				$(".s_kcbox .su1 li:eq(4) span img").attr("src",path+"/images/web/list_icon5_on.png");
				$(".s_kcbox .su1 li:eq(5) span img").attr("src",path+"/images/web/list_icon6.png");
			};
			if ($(".s_kcbox .su1 li:eq(5) span").hasClass("sctv")) {
				$(".s_kcbox .su1 li:eq(0) span img").attr("src",path+"/images/web/list_icon1.png");
				$(".s_kcbox .su1 li:eq(1) span img").attr("src",path+"/images/web/list_icon2.png");
				$(".s_kcbox .su1 li:eq(2) span img").attr("src",path+"/images/web/list_icon3.png");
				$(".s_kcbox .su1 li:eq(3) span img").attr("src",path+"/images/web/list_icon4.png");
				$(".s_kcbox .su1 li:eq(4) span img").attr("src",path+"/images/web/list_icon5.png");
				$(".s_kcbox .su1 li:eq(5) span img").attr("src",path+"/images/web/list_icon6_on.png");
			};
		}
			
//		});
	});
	//点击选中背景变色
	$(".s_kcbox .su2 li").each(function(index){
		$(this).click(function(){
			$(this).addClass("sctv").siblings().removeClass("sctv");
		});
	});
};
// 学习详情按钮
function s_btnact(){
	$(".sbtn_group button").each(function(){
		$(this).click(function(){
			$(this).addClass("btntv").siblings().removeClass("btntv");
		});
	});
};
// 团队管理
function s_xkpsct(){
	//.s_xkpsct
	$(".s_xkp1").click(function(){
		$(this).addClass("s_xkpsct");
		$(".s_xkp2").removeClass("s_xkpsct");
	});
	$(".s_xkp2").click(function(){
		$(this).addClass("s_xkpsct");
		$(".s_xkp1").removeClass("s_xkpsct");
	});
};