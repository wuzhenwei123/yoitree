package com.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSfilter implements Filter {

	@Override
	public void destroy() {
	}

	static String urls = "/msXieyi/addMsXieyi,/msXieyi/updateMsXieyi,/zuowen/studentPingLun,/zuowen/studentPingLunHF,/zuowen/teacherPingLun,/msComposition/addMsComposition";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String requestPath = ((HttpServletRequest) request).getServletPath();
		if (urls.contains(requestPath)) {
			chain.doFilter(request, response);
		} else {
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
			chain.doFilter(xssRequest, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
