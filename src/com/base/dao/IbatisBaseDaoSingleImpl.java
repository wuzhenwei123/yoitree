package com.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.base.dao.common.IbatisDaoReader;
import com.base.dao.common.IbatisDaoWriter;

/**
 * 对数据库操作的基础类，实现了读写分离
 * @author zhangLibo on 2015年4月2日 上午9:20:51
 *
 */
public class IbatisBaseDaoSingleImpl implements IbatisBaseDao {
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@Override
	public Object insert(String statementName, Object obj) throws Exception {
		return sqlMapClient.insert(statementName, obj);
	}

	@Override
	public Object insert(String statementName) throws Exception {
		return sqlMapClient.insert(statementName);
	}

	@Override
	public int update(String statementName, Object obj) throws Exception {
		return sqlMapClient.update(statementName, obj);
	}

	@Override
	public int update(String statementName) throws Exception {
		return sqlMapClient.update(statementName);
	}

	@Override
	public int delete(String statementName, Object obj) throws Exception {
		return sqlMapClient.delete(statementName, obj);
	}

	@Override
	public int delete(String statementName) throws Exception {
		return sqlMapClient.delete(statementName);
	}

	@Override
	public <T> T queryForObject(String statementName) {
		return (T) sqlMapClient.queryForObject(statementName);
	}

	@Override
	public <T> T queryForObject(String statementName, Object obj) {
		return (T) sqlMapClient.queryForObject(statementName, obj);
	}

	@Override
	public <T> List<T> queryForList(String statementName) {
		return sqlMapClient.queryForList(statementName);
	}

	@Override
	public <T> List<T> queryForList(String statementName, Object obj) {
		return sqlMapClient.queryForList(statementName, obj);
	}

	@Override
	public <T> Map queryForMap(String statementName, Object obj, String keyProperty) {
		return sqlMapClient.queryForMap(statementName, obj, keyProperty);
	}

	@Override
	public <T> Map queryForMap(String statementName, Object obj, String keyProperty, String valueProperty) {
		return sqlMapClient.queryForMap(statementName, obj, keyProperty, valueProperty);
	}
}