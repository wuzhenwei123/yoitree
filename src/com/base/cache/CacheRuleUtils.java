package com.base.cache;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CacheRuleUtils {
	private static final Log log = LogFactory.getLog(CacheRuleUtils.class);

	public static List<CacheRule> getRule() {
		log.info("CacheRuleUtils getRule");
		SAXReader reader = new SAXReader();
		List<CacheRule> rules = null;
		try {
			Document document = reader.read(getFile("cache.url.rule.xml"));
			Element rootEle = document.getRootElement();
			List<Element> cacheList = getChildList(rootEle);
			rules = new ArrayList<CacheRule>();
			for (Element element : cacheList) {
				List<Element> cache = getChildList(element);
				CacheRule cacheRule = new CacheRule();
				for (Element element2 : cache) {
					String name = element2.getName();
					if(name.equals("url")){
						cacheRule.setUrl(element2.getText());
					}
					if(name.equals("expires")){
						cacheRule.setExpires(Integer.valueOf(element2.getText().trim()));
					}
					if(name.equals("on-off")){
						cacheRule.setOnOff(element2.getText());
					}
				}
				rules.add(cacheRule);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return rules;
	}

	private static List<Element> getChildList(Element rootEle) {
		List<Element> childList = new ArrayList<Element>();
		for (Iterator<?> i = rootEle.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			childList.add(element);
		}
		return childList;
	}

	public static void main(String[] args) {
		List<CacheRule> rules = CacheRuleUtils.getRule();
		for (CacheRule cacheRule : rules) {
			System.out.println(cacheRule);
		}
	}

	private static File getFile(String fileName) {
		File m_file = null;
		try {
			URI fileUri = CacheRuleUtils.class.getClassLoader().getResource(fileName).toURI();
			m_file = new File(fileUri);
		} catch (URISyntaxException e) {
			log.error(fileName + "文件路径不正确");
		} catch (Exception e) {
			log.error(fileName + "文件读取异常");
		}
		return m_file;
	}
}
