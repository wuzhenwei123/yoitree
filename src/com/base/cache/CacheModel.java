package com.base.cache;

import java.util.List;

public class CacheModel {

	private List<CacheRule> ruleList;//如果分布式应用，请换成第三方缓存策略

	private static CacheModel cacheModel = null;

	private CacheModel() {
		init();
	}

	public static CacheModel getInstance() {
		if (cacheModel == null){
			cacheModel = new CacheModel();
		}
		return cacheModel;
	}

	public void init() {
		ruleList= CacheRuleUtils.getRule();
	}

	public List<CacheRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<CacheRule> ruleList) {
		this.ruleList = ruleList;
	}

}
