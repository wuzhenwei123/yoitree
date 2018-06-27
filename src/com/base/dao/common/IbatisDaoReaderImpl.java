package com.base.dao.common;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 对数据库读操作的实现类
 * @author zhangLibo on 2015年4月2日 上午9:19:36
 *
 */
public final class IbatisDaoReaderImpl extends SqlMapClientDaoSupport implements IbatisDaoReader {
	@Resource(name = "sqlMapSlave")
	private SqlMapClient sqlMapClientReader;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClientReader);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryForObject(String statementName) {
		Assert.notNull(statementName);
		return (T) this.getSqlMapClientTemplate().queryForObject(statementName);
	}

	@Override
	public <T> T queryForObject(String statementName, Object obj) {
		Assert.notNull(statementName);
		Assert.notNull(obj);
		return (T) this.getSqlMapClientTemplate().queryForObject(statementName, obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryForList(String statementName) {
		Assert.notNull(statementName);
		return (List<T>) this.getSqlMapClientTemplate().queryForList(statementName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryForList(String statementName, Object obj) {
		Assert.notNull(statementName);
		Assert.notNull(obj);
		return (List<T>) this.getSqlMapClientTemplate().queryForList(statementName, obj);
	}

	@Override
	public <T> Map<?, ?> queryForMap(String statementName, Object obj, String keyProperty) {
		Assert.notNull(statementName);
		Assert.notNull(obj);
		return this.getSqlMapClientTemplate().queryForMap(statementName, obj, keyProperty);
	}

	@Override
	public <T> Map<?, ?> queryForMap(String statementName, Object obj, String keyProperty, String valueProperty) {
		Assert.notNull(statementName);
		Assert.notNull(obj);
		return this.getSqlMapClientTemplate().queryForMap(statementName, obj, keyProperty, valueProperty);
	}
}