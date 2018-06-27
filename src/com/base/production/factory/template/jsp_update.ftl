<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>${domainName}</title>
	<meta charset="UTF-8" />
	<%@ include file="/WEB-INF/page/common/css.jsp" %>	
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/uniform.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/select2.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/matrix-style2.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/matrix-media.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/css/style.css" />
	<style type="text/css">
		#content{
			margin-left:0px;
		}
	</style>
</head>
<body id="wrapper">
	<div id="content">
	  <div id="content-header">
	      <h1>编辑${domainName}</h1>
	  </div>
	  <div class="container-fluid">
	    <div class="row-fluid">
	      <div class="span12">
	        <div class="widget-box">
	          <div class="widget-content nopadding">
	            <form class="form-horizontal" method="post" action="#" name="update-form" id="update-form" novalidate="novalidate">
	            	<#list columns as item>
	            	<#if item.name == pkName>
	            	<input type="hidden" name="m${item.name?cap_first}" id="m${item.name?cap_first}"  value="${jl}${domainName?lower_case}.${item.name}${jld}" required/>
	            	</#if>
	            	<#if item.name != pkName>
		            <#if item.type == "Date">
	            	<div class="control-group">
		                <label class="control-label">${item.name}:</label>
		                <div class="controls">
		                  <input type="text" name="m${item.name?cap_first}" id="m${item.name?cap_first}" value="<fmt:formatDate value="${jl}${domainName?lower_case}.${item.name}${jld}" type="both"/>" required onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		            	</div>
		            </div>
		            <#else>
	           		<#if item.name == "state">
	            	<div class="control-group">
		                <label class="control-label">${item.name}:</label>
		                <div class="controls">
			                <select id="mState" class="select" required> 
		                        <option <c:if test="${jl}${domainName?lower_case}.${item.name}== 1${jld}" >selected="selected"</c:if>  value="1">正常</option> 
		                        <option <c:if test="${jl}${domainName?lower_case}.${item.name}== 0${jld}" >selected="selected"</c:if> value="0">禁用</option> 
		                	</select> 
		            	</div>
		            </div>
	           		<#else>
	            	<div class="control-group">
		                <label class="control-label">${item.name}:</label>
		                <div class="controls">
		                  <input type="text" name="m${item.name?cap_first}" id="m${item.name?cap_first}"  value="${jl}${domainName?lower_case}.${item.name}${jld}" required/>
		            	</div>
		            </div>
	           		</#if>
		           	</#if>
		           	</#if>
					</#list>
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

	<script src="${jl}ctx${jld}/matrix/js/jquery.min.js"></script> 
	<script src="${jl}ctx${jld}/matrix/js/jquery.ui.custom.js"></script> 
	<script src="${jl}ctx${jld}/matrix/js/bootstrap.min.js"></script> 
	<script src="${jl}ctx${jld}/matrix/js/jquery.uniform.js"></script> 
	<script src="${jl}ctx${jld}/matrix/js/select2.min.js"></script> 
    <script src="${jl}ctx${jld}/matrix/js/nicescroll/jquery.nicescroll.min.js"></script> 
	<script src="${jl}ctx${jld}/matrix/js/jquery.validate.js"></script> 
	<script src="${jl}ctx${jld}/plus/date/WdatePicker.js"></script>
	<script src="${jl}ctx${jld}/matrix/js/matrix.js"></script> 
	<script src="${jl}ctx${jld}/js/common.js"></script> 
	
	<script type="text/javascript">
		var update_form;
		$(document).ready(function(){
			update_form = $("#update-form").validate({
				errorClass: "help-inline",
				errorElement: "span",
				submitHandler:function(form){
		        },
				highlight:function(element, errorClass, validClass) {
					$(element).parents('.control-group').addClass('error');
					$(element).parents('.control-group').removeClass('success');
				},
				unhighlight: function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('error');
					$(element).parents('.control-group').addClass('success');
				}
			});
			initScroll('wrapper');
		});
		function validate(){
			var flag = update_form.form();
			return flag;
		}
	</script>
</body>
</html>