package com.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UrlCheck {
	private static final Log log = LogFactory.getLog(UrlCheck.class);
	private static Map<String, CacheRule> checkUrlMap = null;//如果分布式应用，请把此处换成第三方缓存策略
	private static UrlCheck check = null;

	private UrlCheck() {
		if (checkUrlMap == null) {
			checkUrlMap = new HashMap<String, CacheRule>();
			List<CacheRule> ruleList = CacheModel.getInstance().getRuleList();
			for (CacheRule cacheRule : ruleList) {
				String uri = cacheRule.getUrl(); // url
				String open = cacheRule.getOnOff(); // enable/disable
				if ("on".equalsIgnoreCase(open)) {
					checkUrlMap.put(uri, cacheRule);
					log.debug(cacheRule);
				}
			}
		}
	}

	public static UrlCheck getInstance() {
		if (check == null) {
			check = new UrlCheck();
		}
		return check;
	}
	/**
	 * 
	 * @param url 请求地址
	 * @param expires 时间(s)
	 * @return true or false
	 */
	public boolean refresh(String url, int expires) {
		try {
			CacheRule cacheRule = checkUrlMap.get(url);
			if (cacheRule == null) {
				return false;
			} else {
				cacheRule.setExpires(expires);
				checkUrlMap.put(url, cacheRule);
				return true;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * check request
	 * 
	 * @param httpRequest
	 * @return
	 */
	public CacheRule checkReqCache(HttpServletRequest httpRequest) {
		String method = httpRequest.getMethod();
		if (!method.equalsIgnoreCase("get"))
			return null;
		String uri = httpRequest.getServletPath();
		CacheRule vo = checkUrlMap.get(uri);
		return vo;
	}

	/**
	 * check query string equal
	 * 
	 * @param params
	 * @param nowparams
	 * @return
	 */
	private boolean checkUriCache(Map<String, String> params, Map<String, String> nowparams) {

		for (Entry<String, String> en : params.entrySet()) {
			String key = en.getKey();
			String val = en.getValue();
			String val1 = nowparams.get(key);
			if (val1 == null || !val1.equals(val))
				return false;
		}
		return true;
	}
}
