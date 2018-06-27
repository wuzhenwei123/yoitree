package com.base.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class Common {

	/**
	 * 获得本机IP
	 * 
	 * @return
	 */
	public static String getLocalIp() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取真实IP
	 * @time : 2015年8月31日 上午11:17:59
	 * @param request
	 * @return
	 * @Description: TODO
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
}
