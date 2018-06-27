package com.base.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.base.utils.ConfigConstants;

public class StartupListener extends ContextLoaderListener {
	protected static Logger logger = Logger.getLogger(StartupListener.class);
	private static ServletContext servletContext;

	public static ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("web contextDestroyed-----");
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("web contextInitialized-----");
		ConfigConstants.init();
		super.contextInitialized(event);
	}

}
