package com.base.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.utils.ConfigConstants;
import com.base.utils.DateFormatToString;
import com.base.utils.RequestHandler;
import com.base.utils.SessionName;
import com.sys.manageActScs.model.ManageActScs;
import com.sys.manageActScs.service.ManageActScsService;
import com.sys.manageIpScs.model.ManageIpScs;
import com.sys.manageIpScs.service.ManageIpScsService;

@Repository
public class SystemInterceptor extends HandlerInterceptorAdapter {
	private List<String> paramList;
	Logger log = Logger.getLogger(SystemInterceptor.class);

	@Autowired
	private ManageActScsService manageactscsService = null;
	@Autowired
	private ManageIpScsService manageipscsService = null;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		// 请求的虚拟目录
		String basePath = request.getContextPath();
		String requestPath = request.getServletPath();

		try {
			if (!requestPath.contains(".")) {
				log.info(">>" + requestPath);
				requestScs(request);
				// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
				if (session == null || session.getAttribute(SessionName.ADMIN_USER_ID) == null) {// 未登录
					if (paramList.contains(requestPath)) {
						return super.preHandle(request, response, handler);
					} else {
						// 未登录  
	                    PrintWriter out = response.getWriter();  
	                    StringBuilder builder = new StringBuilder();  
	                    builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
	                    builder.append("alert(\"页面过期，请重新登录\");");  
	                    builder.append("window.top.location.href=\"");  
	                    builder.append(basePath);  
	                    builder.append("/web/toLogin\";</script>");  
	                    out.print(builder.toString());  
	                    out.close();  
	                    return false;  
//						response.sendRedirect(basePath + "/manageAdminUser/toLogin");// 登陆页面
					}
				} else {// 已登录
					request.setAttribute("requestPath", requestPath);
					return super.preHandle(request, response, handler);
				}
			} else {
				return super.preHandle(request, response, handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}
	/**
	 * 记录请求统计
	 * @param request
	 */
	private void requestScs(HttpServletRequest request){
		if(ConfigConstants.REQUEST_STATISTICS_STATE){
			String ipAddress = RequestHandler.getIpAddress(request);
			String requestPath = request.getServletPath();
			ManageActScs manageActScs = new ManageActScs();
			manageActScs.setRequestPath(requestPath);
			manageActScs.setRequestDateTime(DateFormatToString.getToday5());
			ManageActScs manageActScs1 = manageactscsService.getManageActScs(manageActScs);
			try {
				ManageIpScs manageIpScs = new ManageIpScs();
				manageIpScs.setRequestIP(ipAddress);
				manageIpScs.setRequestDateTime(DateFormatToString.getToday3());
				ManageIpScs manageIpScs1 = manageipscsService.getManageIpScs(manageIpScs);
				if(manageIpScs1 == null){
					manageipscsService.insertManageIpScs(manageIpScs);
				}else{
					manageIpScs1.setRequestCount(manageIpScs1.getRequestCount()+1);
					manageipscsService.updateManageIpScs(manageIpScs1);
				}
				if(manageActScs1 == null){
					manageactscsService.insertManageActScs(manageActScs);
				}else{
					manageActScs1.setRequestCount(manageActScs1.getRequestCount()+1);
					manageactscsService.updateManageActScs(manageActScs1);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
