<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tag/taglib.tld" prefix="perm"%> 
<%-- 项目路径 --%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<c:set var="_title" value="优也树" />
<%-- 图片根路径前缀 --%>
<c:set var="pic" value="/pic" />
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>
