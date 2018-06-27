package com.base.cache;

public class CacheRule {

	private String url;
	private int expires;
	private String onOff;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public String getOnOff() {
		return onOff;
	}

	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}

	@Override
	public String toString() {
		return "CacheModel [url=" + url + ", expires=" + expires + ", onOff=" + onOff + "]";
	}

}
