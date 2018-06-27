<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta name="format-detection" content="telephone=no" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/new/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/wx/new/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/weui.css">
</head>
<body style="background:#fff;">
<div class="wrapper">
	<input type="hidden" value="${ctx}" id="path">
    <div class="content_order">
        <div class="login_box">
            <img src="${ctx}/images/wx/login_icon.png"/>
        </div> 
        <div class="content_order_list clearfix">
            <span class="sp_l" id="numberPicker" style="position:relative;">
                <lable>+86</lable>
                <b class="triangle-down"></b>
            </span>
            <div class="sp_r sp_r1">
                <input type="text" class="order_input1" placeholder="请输入手机号" id="mobile"/>
            </div>
        </div>            
        <div class="content_order_list clearfix">
            <span class="sp_l">
                <lable>验证码</lable>
            </span>
            <div class="sp_r sp_r1" style="width:33%;">
                <input type="text" class="order_input1" placeholder="请输入验证码" id="code"/>
            </div>
            <div class="bind_btn_box">
                <input type="button" class="bind_btn" value="获取短信验证码" onclick="getCode()" id="codeBtn"/>
            </div>
        </div>   
    </div>
    <div class="weui-dialog__btn weui-dialog__btn_primary receive_btn_phone">
        <a href="javascript:save();">登录</a>
    </div>
</div>
<script src="${ctx}/js/wx/jquery.min.js"></script>
<script src="${ctx}/js/weui.min.js"></script>
<script src="${ctx}/js/web/login.js"></script>
</body>
</html>