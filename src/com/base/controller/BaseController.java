package com.base.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.base.exception.BaseException;
import com.base.utils.ConfigConstants;
import com.base.utils.MD5;
import com.base.utils.ResultRsp;
import com.base.utils.SessionName;
import com.base.utils.StandardOutput;

/**
 * 
 * @author zhanglib
 *
 */
@Controller
public class BaseController extends BaseException {
	protected static MD5 MD5 = new MD5();
	
	
	private static Logger logger = Logger.getLogger(BaseController.class); // Logger
	final static String[] patterns = { 
		"yyyyMMdd", 
		"yyyy-MM-dd",
		"yyyy-MM-dd HH:mm:ss", 
		"yyyy.MM.dd", 
		"yyyy/MM/dd HH:mm:ss" 
		};// 限定日期的格式字符串数组

	@ExceptionHandler(RuntimeException.class)
	public String operateExp(RuntimeException ex, HttpServletRequest request) {
		String exceptionCode = exception(ex);
		logger.info("系统异常" + exceptionCode);
		request.setAttribute("errorTips", exceptionCode);
		return "/sys/exception/exception";
	}

	public static <T> T requst2Bean(HttpServletRequest request, Class<T> bean) {
		T t = null;
		try {
			t = bean.newInstance();
			Enumeration<?> parameterNames = request.getParameterNames();
			DateConverter convert = new DateConverter();// 写一个日期转换器
			convert.setPatterns(patterns);
			ConvertUtils.register(convert, Date.class);
			while (parameterNames.hasMoreElements()) {
				String name = (String) parameterNames.nextElement();
				String value = request.getParameter(name);
				if (value != null && !"".equals(value) && !"null".equals(value)) {
					BeanUtils.setProperty(t, name, value);// 使用BeanUtils来设置对象属性的值
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;

	}

	protected void writeSuccessMsg(String message, Object data,
			HttpServletResponse paramHttpServletResponse) {
		ResultRsp localObject = new ResultRsp("1", message, data);
		StandardOutput.printObject(paramHttpServletResponse, localObject);
	}

	protected void write(Object data,
			HttpServletResponse paramHttpServletResponse) {
		StandardOutput.print(paramHttpServletResponse, data);
	}

	protected void writeErrorMsg(String message, Object data,
			HttpServletResponse paramHttpServletResponse) {
		ResultRsp localObject = new ResultRsp("-1", message, data);
		StandardOutput.printObject(paramHttpServletResponse, localObject);
	}

	protected void addSession(HttpServletRequest request, String key,
			Object value) {
		if (key != null) {
			request.getSession().setAttribute(key, value);
		}
	}
	
	protected void writeJsonObject(Object data,
			HttpServletResponse paramHttpServletResponse) {
		StandardOutput.printObject(paramHttpServletResponse, data);
	}

	protected void removeSession(HttpServletRequest request, String key) {
		if (key != null) {
			request.getSession().removeAttribute(key);
		}
	}
	

	protected Object getSession(HttpServletRequest request, String key) {
		if (key != null) {
			return request.getSession().getAttribute(key);
		}
		return null;
	}
	protected Integer getCurrentAdminUserId(HttpServletRequest request) {
		Object obj = getSession(request, SessionName.ADMIN_USER_ID);
		if(obj != null){
			return Integer.valueOf(obj.toString());
		}
		return null;
	}
	
	
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
