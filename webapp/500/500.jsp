<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>${_title }</title>
	<meta charset="UTF-8" />
	<%@ include file="/WEB-INF/page/common/css.jsp" %>	
	
	<link rel="stylesheet" href="${ctx }/matrix/css/matrix-style2.css" />
	<link rel="stylesheet" href="${ctx }/matrix/css/matrix-media.css" />
	<link rel="stylesheet" href="${ctx }/css/style.css" />
	
	<script src="${ctx }/matrix/js/jquery.min.js"></script> 
	<script src="${ctx }/matrix/js/jquery.ui.custom.js"></script> 
	<script src="${ctx }/matrix/js/bootstrap.min.js"></script> 
	<script src="${ctx }/matrix/js/jquery.uniform.js"></script> 
	<script src="${ctx }/matrix/js/matrix.js"></script>
</head>

<body style="background:#eee;">
	<div id="content1">
	   <div id="content-header">
	      <h1>500错误页面</h1>
	  </div>
	  <div class="container-fluid">
	    <div class="row-fluid">
	      <div class="span12">
	        <div class="widget-box">
	          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
	            <h5>Error 500</h5>
	          </div>
	          <div class="widget-content">
	            <div class="error_ex">
	              <h1>500</h1>
	              <h3>Something is wrong here. Method not allowed!</h3>
	              <p>Access to this page is forbidden</p>
	             </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>
 