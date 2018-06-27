<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html lang="en" style="background:#eee;">
<head>
	<title>新增${domainName}</title>
	<meta charset="UTF-8" />
	<%@ include file="/WEB-INF/page/common/css.jsp" %>	
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/uniform.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/select2.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/matrix-style2.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/matrix/css/matrix-media.css" />
	<link rel="stylesheet" href="${jl}ctx${jld}/plus/date/skin/WdatePicker.css">
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
	      <h1>添加${domainName}</h1>
	  </div>
	  <div class="container-fluid">
	    <div class="row-fluid">
	      <div class="span12">
	        <div class="widget-box">
	          <div class="widget-content">
	            <form class="form-horizontal" method="post" action="#" name="add-form" id="add-form" novalidate="novalidate">
	            <#list columns as item>
	            	<#if item.name != pkName>
                	<#if item.type == "Date">
	            	<div class="control-group">
		                <label class="control-label">${item.name}:</label>
		                <div class="controls">
		                  <input type="text" name="a${item.name?cap_first}" id="a${item.name?cap_first}" value="" required onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		            	</div>
		            </div>
                	<#else>
                	<#if item.name == "state">
	            	<div class="control-group">
		                <label class="control-label">${item.name}:</label>
		                <div class="controls">
			                <select id="aState" class="select"> 
		                        <option value="1">正常</option> 
		                        <option value="0">禁用</option> 
		                	</select> 
		            	</div>
		            </div>
                	<#else>
	            	<div class="control-group">
		                <label class="control-label">${item.name}:</label>
		                <div class="controls">
		                  <input type="text" name="a${item.name?cap_first}" id="a${item.name?cap_first}"  value="" required/>
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
	<script src="${jl}ctx${jld}/matrix/js/matrix.js"></script> 
	<script src="${jl}ctx${jld}/plus/date/WdatePicker.js"></script>
	<script src="${jl}ctx${jld}/js/common.js"></script> 
	
	
	<script type="text/javascript">
		var add_form;
		$(document).ready(function(){
			add_form = $("#add-form").validate({
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
			var flag = add_form.form();
			return flag;
		}
	</script>
</body>
</html>