package com.base.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 获取配置文件
 * 
 * @author ll
 *
 */
public class ConfigConstants {

	/** 文件上传根目录* */
	public static String UPLOAD_FILE_ROOT = null;
	/** 文件上传根目录* */
	public static String WEB_PIC = null;
	/** 请求统计开关 **/
	public static Boolean REQUEST_STATISTICS_STATE = null;
	
	/** appid**/
	public static String APPID = null;
	/** appsecret**/
	public static String APPSECRET = null;
	/** 访问地址**/
	public static String URL_PATH = "";
	/** 微信token**/
	public static String TOKEN = "";
	/** 微信消息类型 **/
	public static String RECV_TYPE_TEXT = null;
	public static String RECV_TYPE_LINK = null;
	public static String RECV_TYPE_LOCATION = null;
	public static String RECV_TYPE_IMAGE = null;
	public static String RECV_TYPE_EVENT = null;
	public static String RECV_TYPE_VOICE = null;
	public static String RECV_TYPE_VIDEO = null;
	/**腾讯云点播参数**/
	public static String SECRETID = null;
	/**腾讯云点播参数**/
	public static String SECRETKEY = null;
	/**腾讯云点播回调地址**/
	public static String CALLBACK_URL = null;
	/**微信网页授权回调地址**/
	public static String REDIRECT_URI = null;
	/**极光短信**/
	public static String DEV_KEY = null;
	/**极光短信**/
	public static String DEV_SECRET = null;
	
	
	
	
	protected static Logger logger = Logger.getLogger(ConfigConstants.class);
	private static Properties prop = null;
	public static void init(){
		logger.info("config init ... ");
		String path = "/com/conf/system_windows.properties";
		if (isLinux()) {
			path = "/com/conf/system_linux.properties";
		}
		InputStream in = ConfigConstants.class.getResourceAsStream(path);
		if (in != null) {
			prop = new Properties();
			try {
				prop.load(in);
				UPLOAD_FILE_ROOT = (String) prop.get("upload_file_root");
				REQUEST_STATISTICS_STATE = Boolean.valueOf((String) prop.get("request.statistics.state"));
				APPID = (String) prop.get("appid");
				APPSECRET = (String) prop.get("appsecret");
				URL_PATH = (String) prop.get("urlPath");
				TOKEN = (String) prop.get("token");
				RECV_TYPE_TEXT = (String) prop.get("recv_type_text");
				RECV_TYPE_LINK = (String) prop.get("recv_type_link");
				RECV_TYPE_LOCATION = (String) prop.get("recv_type_location");
				RECV_TYPE_IMAGE = (String) prop.get("recv_type_image");
				RECV_TYPE_EVENT = (String) prop.get("recv_type_event");
				RECV_TYPE_VOICE = (String) prop.get("recv_type_voice");
				RECV_TYPE_VIDEO = (String) prop.get("recv_type_video");
				WEB_PIC = (String) prop.get("web_pic");
				SECRETID = (String) prop.get("SecretId");
				SECRETKEY = (String) prop.get("SecretKey");
				CALLBACK_URL = (String) prop.get("callback_url");
				REDIRECT_URI = (String) prop.get("redirect_uri");
				DEV_KEY = (String) prop.get("DevKey");
				DEV_SECRET = (String) prop.get("DevSecret");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String getPropValue(String key) {
		String val = null;
		try {
			val = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println("=====================get property file error!");
			throw new RuntimeException(e);
		}
		return val;
	}

	public static boolean isLinux() {
		String osType = System.getProperties().getProperty("os.name").toLowerCase();
		if (osType.startsWith("windows")) {
			return false;
		} else {
			return true;
		}
	}
}
