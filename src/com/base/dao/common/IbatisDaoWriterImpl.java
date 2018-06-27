package com.base.dao.common;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 对数据库写操作的实现类
 * @author zhangLibo on 2015年4月2日 上午9:19:15
 *
 */
public final class IbatisDaoWriterImpl extends SqlMapClientDaoSupport implements IbatisDaoWriter {
	@Resource(name = "sqlMapMaster")
	private SqlMapClient sqlMapClientWriter;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClientWriter);
	}

	public Object insert(String statementName, Object obj) {
		Assert.notNull(statementName);
		Assert.notNull(obj);
		return this.getSqlMapClientTemplate().insert(statementName, obj);
	}

	@Override
	public Object insert(String statementName) {
		Assert.notNull(statementName);
		return this.getSqlMapClientTemplate().insert(statementName);
	}

	@Override
	public int update(String statementName, Object obj) {
		Assert.notNull(statementName);
		Assert.notNull(obj);
		return (Integer) this.getSqlMapClientTemplate().update(statementName, obj);
	}

	@Override
	public int update(String statementName) {
		Assert.notNull(statementName);
		return (Integer) this.getSqlMapClientTemplate().update(statementName);
	}

	@Override
	public int delete(String statementName) {
		Assert.notNull(statementName);
		return this.getSqlMapClientTemplate().delete(statementName);
	}

	@Override
	public int delete(String statementName, Object obj) {
		Assert.notNull(statementName);
		return this.getSqlMapClientTemplate().delete(statementName, obj);
	}
}