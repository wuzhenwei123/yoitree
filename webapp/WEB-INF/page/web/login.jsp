<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="text/html;charset=utf-8" http-equiv="content-type">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<meta content="webkit|ie-comp|ie-stand" name="renderer">
		<title>${_title}--登录</title>
		<link rel="icon" type="image/ico" href="${ctx}/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/newweb/base.css" />
	</head>
	<body>
		<input type="hidden" value="${ctx}" id="path">
		<div class="login">
            <div class="l_logo"><img src="${ctx}/images/newweb/logo.png" alt=""></div>
            <div class="l_box">
                <ul>
                    <li>
                        <div  class="border">
                            <div class="sel_box">
                                <input class="sel_text" value="+86" readonly/>
                                <div class="sel_list">
                                    <ul>
                                        <li>+10</li>
                                        <li>+11</li>
                                    </ul>
                                </div>
                            </div>
                            <input type="text" class="my_phone pcolor" placeholder="请输入手机号" id="mobile" style="color: #4a4a4a">
                        </div>
                    </li>
                    <li class="cl">
                        <div  class="border fl">
                            <input type="text" class="icode pcolor" placeholder="请输入验证码" id="imgCode" style="color: #4a4a4a">
                        </div>
                        <div class="border fr">
                            <img onClick="this.src=this.src+'?'" style="cursor: pointer;" title="看不清？点击更换" src="${ctx }/web/pcrimg" class="imgcode" alt="">
                        </div>
                    </li>
                    <li>
                        <div  class="border">
                            <input type="text" class="code pcolor" placeholder="请输入短信验证码" id="code" style="color: #4a4a4a">
                            <a href="javascript:;" class="btn_code" onclick="getCode()" id="codeBtn">获取短信验证码</a>
                        </div>
                    </li>
                </ul>
                <a href="javascript:save();" class="btn_login">手机号登录</a>
            </div>
		</div>
	</body>
	<script src="${ctx}/js/web/jquery-2.1.4.min.js"></script>
	<script src="${ctx}/js/web/jquery.slimscroll.js"></script>
	<script src="${ctx}/js/web/cm.js"></script>
	<script src="${ctx}/plus/layer/layer.js"></script>
	<script src="${ctx}/js/web/login.js"></script>
</html>
