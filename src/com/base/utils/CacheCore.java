package com.base.utils;

import java.util.HashMap;
import java.util.Map;

public class CacheCore {
	private static CacheCore cacheCore = null;
	private static Map<String, Object> MAP = new HashMap<String, Object>();

	private CacheCore() {
	}

	public static CacheCore getInstens() {
		if (cacheCore == null) {
			cacheCore = new CacheCore();
		}
		return cacheCore;
	}

	public void put(String key, Object val) {
		MAP.put(key, val);
	}

	public Object get(String key) {
		return MAP.get(key);
	}
}
