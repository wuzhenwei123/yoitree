package com.base.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheUrlVo {
	private String uri; //地址栏
	private Map<String,String> params=new LinkedHashMap<String, String>(); //参数
	private int cacheTime=30;	//缓存时间,秒
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public int getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}
	
	
	
	
	
}
