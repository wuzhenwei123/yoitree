<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>${jl}_title${jld}</title>
	<meta charset="UTF-8" />
	<%@ include file="/WEB-INF/page/common/css.jsp" %>	
	<link rel="stylesheet" href="${jl}ctx${jld}/css/style.css" />
	<%@ include file="/WEB-INF/page/common/js.jsp" %>	
	<script src="${jl}ctx${jld}/js/common.js"></script> 
</head>

<body style="background:#fff;">
    <input type="hidden" id="currPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum" value="10"><!-- 分页返回 -->
	<input type="hidden" id="sortColumn" value=""><!-- 排序字段 -->
	<div id="content1">
	  <div id="content-header">
	      <h1>${domainName}</h1>
	  </div>
		<div class="container-fluid">
		  <div class="row-fluid">
		    <div class="span12">
		      <div class="widget-box">
		        <div class="widget-title" data-toggle="collapse" href="#search-coll"> 
		        	<span class="icon"><i class="icon-chevron-down"></i></span>
		          	<h5>检索</h5>
		        </div>
		        <div class="widget-content nopadding collapse" id="search-coll">
		          <form class="form-horizontal">
		          	<#list columns as item>
	                	<#if item.type == "Date">
	                <div class="control-group name_title">
		                <label class="control-label name_label">${item.name}:</label>
	                	<div class="controls name_control">
			                <input type="text" class="span8 name_span" id="f${item.name?cap_first}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
			            </div>
	                </div>
	                	<#else>
	                	<#if item.name == "state">
	                <div class="control-group name_title">
		                <label class="control-label name_label">${item.name}:</label>
	                	<div class="controls name_control name_select">
	                    <select id="fState" class="span12"> 
	                        <option value="">--全部--</option> 
	                        <option value="1">正常</option> 
	                        <option value="0">禁用</option> 
	                	</select> 
                	     </div>
	                </div>
	                	<#else>
	                <div class="control-group name_title">
		                <label class="control-label name_label">${item.name}:</label>
	                	<div class="controls name_control">
			                <input type="text" class="span8 name_span" id="f${item.name?cap_first}"/>
			            </div>
	                </div>
	                	</#if>
	                	</#if>
                	</#list>
		            <div class="name_query_btn">
		              <button type="button" class="btn btn-success" onclick="searchData(1)"><i class="icon-search"></i> 查询</button>
		            </div>
		            <div style="height:10px;clear:both;"></div>
		          </form>
		        </div>
		      </div>
		    </div>
		  </div>
		<perm:tag permPath="/${domainName}/add${domainName?cap_first}" >
	    <button class="btn btn-success" onclick="toAdd()"><i class="icon-plus"></i>添加</button>
		</perm:tag>
		<perm:tag permPath="/${domainName}/removeAll${domainName?cap_first}" >
	    <button class="btn btn-danger" onclick="delAll()"><i class="icon-trash"></i>批量删除</button>
		</perm:tag>
		  <div class="row-fluid">
		    <div class="span12">
		   		<div class="widget-box">
		          <!-- <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
		            <h5>列表</h5>
		          </div> -->
		          <div class="widget-content tab_con">
		            <table class="table table-bordered table-striped with-check" id="exampleDTC"></table>
		          </div>
		          <div>
		            <div class="pagination alternate text-right" id="page-div"></div>
		          </div>
		        </div>
		    </div>
		  </div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
	    	searchData(1);
	    });
		function searchData(pageNo){
			parent.layer.load();
			var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
			<#list columns as item>
		    var ${item.name} = $("#f${item.name?cap_first}").val();
			</#list>
		    $.getJSON("<c:url value='/${domainName}/get${domainName?cap_first}List'/>",
	        {
	        	sortColumn:sortColumn,
	        <#list columns as item>
	    		${item.name} : ${item.name},
			</#list>
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	            var result = data;
	            parent.layer.closeAll('loading');
	            if (result.code == 1) {
	                setTableStr(result, pageNo, returnNum);
	            } else {
	            	tipError("系统异常!");
	            } 
		    });
		}
		function genTableHeader(){
			var str = "<thead><tr>" ;
		    	str+= "<th><input id=\"checkAllBtn\" type='checkbox' value='ON' onclick=\"checkAll('checkAllBtn','checkName');\" name='check-all'></th>";
		    	str+= "<th>序号</th>";
			<#list columns as item>
			<#if item.type == "Date">
		    	str+= "<th class=\"sortTh\" id=\"th_${item.name}\" width='135' column='${item.name}'>${item.name}<i class=\"icon-sort\"></i></th>";
		    <#else>
		    	str+= "<th class=\"sortTh\" id=\"th_${item.name}\" column='${item.name}'>${item.name}<i class=\"icon-sort\"></i></th>";
		    </#if>
			</#list>	
				str+="<th>操作</th>";
				str+="</tr></thead>";
			return str;
		}
		function setTableStr(result, pageNo, returnNum){
			var tableStr = "";
		    tableStr += genTableHeader();
	        tableStr += "<tbody>";
		    var number = (pageNo - 1) * returnNum;
		    for (var k=0; k<result.items.length; k++){      
		        tableStr += "<tr>";
		        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].${pkName}+"' value=\"0\" name=\"checkName\" /></td>";
		        tableStr += "<td >" + (number + k + 1) + "</td>";
		        
		        <#list columns as item>
				<#if item.type == "Date">
			        tableStr += "<td>" + genDateTimeAll(result.items[k].${item.name}) + "</td>";
				<#else>
				<#if item.name == "state">
					if(result.items[k].state == 1){
			        tableStr += "<td class='text-green'>正常</td>";
					}else{
			        tableStr += "<td class='text-red'>禁用</td>";
					}
	       		<#else>
			        tableStr += "<td>" + result.items[k].${item.name} + "</td>";
	       		</#if>
				</#if>
				</#list>
		        tableStr += "<td>";
	        	tableStr += "<perm:tag permPath='/${domainName}/update${domainName?cap_first}' ><button class=\"btn btn-mini btn-info\" onclick='toUpdate(" + result.items[k].${pkName} + ");'><i class=\"icon-edit\"></i> 编辑</button></perm:tag>";
	        	tableStr += "<perm:tag permPath='/${domainName}/remove${domainName?cap_first}' ><button class=\"btn btn-mini btn-danger\" onclick='del(" + result.items[k].${pkName} + ");'><i class=\"icon-trash\"></i>删除</button></perm:tag>";
	        	tableStr += "</td>";
		        tableStr += "</tr>";            
		    }
		    tableStr += "</tbody>";
		    $("#exampleDTC").html(tableStr);
		    $("#currPage").val(pageNo);	
		    initForm();
		    initTh();
		    genPageTag(pageNo,result.totalResults,returnNum,'page-div');
		}
		// 添加
 		function toAdd(){
   			top.art.dialog.open('<c:url value="/${domainName}/toAdd"/>',
			{
				id:456,
				fixed:true,
				esc:true,
				title:'添加${domainName}',
				width: '730px',
				height:'400px',
			    cancelVal: '取消',
		        cancel: true, //为true等价于function(){}
		        okVal : "保存",
				ok:function(){
					var iframe = this.iframe.contentWindow;
			         var flag = iframe.validate();
			        if(flag){
						add(iframe);
			        }
      				return flag;
				}
			});
   		} 
   		function add(iframe){
   		<#list columns as item>
			var ${item.name} = getIframeVal(iframe,"a${item.name?cap_first}");
		</#list> 
			var flag = true;
		    if (flag){ 
		        $.post("<c:url value='/${domainName}/add${domainName?cap_first}'/>",
		        	{
		        <#list columns as item>
		    		${item.name} : ${item.name},
				</#list>  			
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	goCurrentPage();
			            	tipOk("保存成功!");
			             } else {
			            	 tipError(result.message);
			             }
		        });
		    }
   		}
 		// 更新
		function toUpdate(id) {
			top.art.dialog.open('<c:url value="/${domainName}/toUpdate?${pkName}='+id+'"/>',
			{
				id:123,
				fixed:true,
				title:'${domainName}',
				esc:true,
				width: '730px',
				height:'400px',
			    cancelVal: '取消',
		        cancel: true, //为true等价于function(){}
		        okVal : "更新",
				ok:function(){
					var iframe = this.iframe.contentWindow;
			        var flag = iframe.validate();
			        if(flag){
						update(iframe);
			        }
      				return flag;
				}
			});
		}
		// 执行更新
   		function update(iframe){
   		<#list columns as item>
			var ${item.name} = getIframeVal(iframe,"m${item.name?cap_first}");
		</#list> 
			var flag = true;
		    if (flag){ 
		        $.post("<c:url value='/${domainName}/update${domainName?cap_first}'/>",
		        	{
		        <#list columns as item>
		    		${item.name} : ${item.name},
				</#list>  			
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	goCurrentPage();
			            	tipOk("更新成功!");
			             } else {
			            	 tipError(result.message);
			             }
		        });
		    }
   		}
   		// 删除所选
   		function delAll(){
   			var ids = '';
   			$("[name='checkName']").each(function(){
	   		    	var ck = $(this).attr("checked");
	   		    	if(ck == 'checked'){
	   		    		ids+=$(this).attr("id")+",";
	   		    	}else{
	   		    	}
	   		})
   		    if(ids == ''){
   		    	tipOk("请选择!");
   		    }else{
   		    	parent.layer.msg('你确定要删除吗？', {
  	   			  time: 0 //不自动关闭
  	   			  ,btn: ['确定', '取消']
  	   			  ,yes: function(index){
  	   				$.post("<c:url value='/${domainName}/removeAll${domainName?cap_first}'/>",
		        	{
						${pkName}s :ids,
						ranNum:Math.random()},
			        	function(data){
				        	var result = eval('('+data+')'); 
				            if (result.code == '1') {
				            	goCurrentPage();
				            	tipOk("删除完成!");
				             } else {
				            	 tipError(result.message);
				             }
			        });
   			        return true;
  	   			  }
  	   			});
   		    }
   			
   		}
		// 单条删除
		function del(${pkName}){
   		   if (${pkName} != ""){ 
	   			parent.layer.msg('你确定要删除吗？', {
	   			  time: 0 //不自动关闭
	   			  ,btn: ['确定', '取消']
	   			  ,yes: function(index){
	   			 	$.post("<c:url value='/${domainName}/remove${domainName?cap_first}'/>",
   		        	{
   						${pkName}	:${pkName},
   						ranNum:Math.random()
   					},
   		        	function(data){
   			        	var result = eval('('+data+')'); 
   			            if (result.code == '1') {
   			            	goCurrentPage();
   			              	tipOk("删除成功!");
   			             } else {
   			              	tipError("删除失败!");
   			             }
   			        });
	   			  }
	   			});
	   	  	}
	  	 }
		function goCurrentPage(){
			var pageNo = $("#currPage").val();           
		  	searchData(pageNo);
		}
	</script>
</body>
</html>
