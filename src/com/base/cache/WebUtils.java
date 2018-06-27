package com.base.cache;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebUtils {
	private static final Log log = LogFactory.getLog(WebUtils.class);
	/**
	 * parse queryString to Map
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParam(HttpServletRequest request){
		String queryStr=request.getQueryString();
		return getParam(queryStr);
	}
	/**
	 * parse queryString to Map
	 * @param queryStr
	 * @return
	 */
	public static Map<String, String> getParam(String queryStr){
		Map resuMap=new HashMap();
		if (queryStr == null)
			return resuMap;
		String value="";
		try {
			String[] ss=queryStr.split("&");
			String key="";
			value="";
			for (String str : ss) {
				String[] ssc=str.split("=");
				key=ssc[0];
				if(ssc.length==1)
					value="";
				else{
					value=ssc[1];
				}
				resuMap.put(key, value);
			}
		} catch (Throwable e) {
			log.error("queryStr parse error!"+value,e);
		}
		return resuMap;
	}
}
