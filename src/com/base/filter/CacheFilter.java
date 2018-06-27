package com.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.base.cache.CacheRule;
import com.base.cache.Md5Utils;
import com.base.cache.UrlCheck;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.filter.CacheHttpServletResponseWrapper;
import com.opensymphony.oscache.web.filter.ResponseContent;

public class CacheFilter implements Filter {
	private static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
	private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

	private static final long LAST_MODIFIED_OFF = 0;
	private static final long EXPIRES_OFF = 0;
	private final Log log = LogFactory.getLog(this.getClass());
	private FilterConfig config;
	private long lastModified = LAST_MODIFIED_OFF;
	private long expires = EXPIRES_OFF;
	private long cacheControlMaxAge = 0;
	private String cachePre = "PP_";
	private GeneralCacheAdministrator cacheAdmin;

	public void init(FilterConfig filterConfig) {
		config = filterConfig;
		log.info("OSCache: Initializing CacheFilter with filter name " + config.getFilterName());
		cacheAdmin = new GeneralCacheAdministrator();//如果分布式缓存，请修改此地，用第三方缓存策略
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		long l1 = System.currentTimeMillis();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		UrlCheck urlCheck = UrlCheck.getInstance();
		// 判断当前请求是否在需要缓存的列表中
		CacheRule vo = urlCheck.checkReqCache(httpRequest);
		if (vo == null) {
			// 如果当前不请求符合缓存要求,就继续执行.
			chain.doFilter(request, response);
			return;
		}
		// 如果当前请求符合缓存要求.
		// 生成缓存的key
		String key = genCacheKey(httpRequest);
		// key太长,用md5编码
		String md5key = Md5Utils.MD5(key);
		// 获取当前请求可以缓存的时间.
		int cacheTime = vo.getExpires();
		try {
			// 直接从缓存中获取数据
			ResponseContent respContent = (ResponseContent) cacheAdmin.getFromCache(md5key, cacheTime);

			boolean acceptsGZip = false;
			if (lastModified != LAST_MODIFIED_OFF) {
				long clientLastModified = httpRequest.getDateHeader(HEADER_IF_MODIFIED_SINCE);
				if ((clientLastModified != -1) && (clientLastModified >= respContent.getLastModified())) {
					((HttpServletResponse) response).setStatus(HttpServletResponse.SC_NOT_MODIFIED);
					return;
				}
				acceptsGZip = respContent.isContentGZiped() && acceptsGZipEncoding(httpRequest);
			}
			respContent.writeTo(response, false, acceptsGZip);
			if (log.isInfoEnabled()) {
				log.debug("cache," + ",TIME=" + (System.currentTimeMillis() - l1) + "ms," + key);
			}
		} catch (NeedsRefreshException nre) {
			boolean updateSucceeded = false;
			try {
				// 包装response
				CacheHttpServletResponseWrapper cacheResponse = new CacheHttpServletResponseWrapper(
						(HttpServletResponse) response, false, cacheTime * 1000L, lastModified, expires,
						cacheControlMaxAge);
				// 继续执行
				chain.doFilter(request, cacheResponse);
				cacheResponse.flushBuffer();
				// 保存到cache中
				cacheAdmin.putInCache(md5key, cacheResponse.getContent());
				updateSucceeded = true;
				if (log.isInfoEnabled()) {
					log.debug("new cache" + ",cacheTime=" + vo.getExpires() + "second,TIME="
							+ (System.currentTimeMillis() - l1) + "ms," + key);
				}
			} finally {
				if (!updateSucceeded) {
					cacheAdmin.cancelUpdate(md5key); // 如果执行失败就取消更新
				}
			}
		}
	}

	private String genCacheKey(HttpServletRequest httpRequest) {
		String uri = httpRequest.getServletPath();
		return cachePre + uri;
	}

	public boolean acceptsGZipEncoding(HttpServletRequest request) {
		String acceptEncoding = request.getHeader(HEADER_ACCEPT_ENCODING);
		return (acceptEncoding != null) && (acceptEncoding.indexOf("gzip") != -1);
	}

}
