<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta name="format-detection" content="telephone=no" />
    <title>${_title}--提示</title>
    <link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/weui.css">
</head>
<body style="background:#fff;">
<div class="weui-msg">
    <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">操作失败,少年,这里是穿越页面,请回到现实吧</h2>
<!--         <p class="weui-msg__desc">请在微信里打开</p> -->
    </div>
<!--     <div class="weui-msg__opr-area"> -->
<!--         <p class="weui-btn-area"> -->
<!--             <a href="javascript:history.back();" class="weui-btn weui-btn_primary">推荐操作</a> -->
<!--             <a href="javascript:history.back();" class="weui-btn weui-btn_default">辅助操作</a> -->
<!--         </p> -->
<!--     </div> -->
    <div class="weui-msg__extra-area">
        <div class="weui-footer">
<!--             <p class="weui-footer__links"> -->
<!--                 <a href="javascript:void(0);" class="weui-footer__link">底部链接文本</a> -->
<!--             </p> -->
            <p class="weui-footer__text">Copyright &copy; 2008-2017 www.yoitree.com.cn</p>
        </div>
    </div>
</div>
<script src="${ctx}/js/wx/jquery.min.js"></script>
<script src="${ctx}/js/weui.js"></script>
<script type="text/javascript" class="picker js_show">

</script>
</body>
</html>