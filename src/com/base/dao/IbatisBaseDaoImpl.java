package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.common.IbatisDaoReader;
import com.base.dao.common.IbatisDaoWriter;

/**
 * 对数据库操作的基础类，实现了读写分离
 * @author zhangLibo on 2015年4月2日 上午9:20:51
 *
 */
public class IbatisBaseDaoImpl implements IbatisBaseDao {
	private IbatisDaoReader ibatisDaoReader;
	private IbatisDaoWriter ibatisDaoWriter;

	public IbatisDaoReader getIbatisDaoReader() {
		return ibatisDaoReader;
	}

	public void setIbatisDaoReader(IbatisDaoReader ibatisDaoReader) {
		this.ibatisDaoReader = ibatisDaoReader;
	}

	public IbatisDaoWriter getIbatisDaoWriter() {
		return ibatisDaoWriter;
	}

	public void setIbatisDaoWriter(IbatisDaoWriter ibatisDaoWriter) {
		this.ibatisDaoWriter = ibatisDaoWriter;
	}

	@Override
	public Object insert(String statementName, Object obj) throws Exception {
		return ibatisDaoWriter.insert(statementName, obj);
	}

	@Override
	public Object insert(String statementName) throws Exception {
		return ibatisDaoWriter.insert(statementName);
	}

	@Override
	public int update(String statementName, Object obj) throws Exception {
		return ibatisDaoWriter.update(statementName, obj);
	}

	@Override
	public int update(String statementName) throws Exception {
		return ibatisDaoWriter.update(statementName);
	}

	@Override
	public int delete(String statementName, Object obj) throws Exception {
		return ibatisDaoWriter.delete(statementName, obj);
	}

	@Override
	public int delete(String statementName) throws Exception {
		return ibatisDaoWriter.delete(statementName);
	}

	@Override
	public <T> T queryForObject(String statementName) {
		return ibatisDaoReader.queryForObject(statementName);
	}

	@Override
	public <T> T queryForObject(String statementName, Object obj) {
		return ibatisDaoReader.queryForObject(statementName, obj);
	}

	@Override
	public <T> List<T> queryForList(String statementName) {
		return ibatisDaoReader.queryForList(statementName);
	}

	@Override
	public <T> List<T> queryForList(String statementName, Object obj) {
		return ibatisDaoReader.queryForList(statementName, obj);
	}

	@Override
	public <T> Map queryForMap(String statementName, Object obj, String keyProperty) {
		return ibatisDaoReader.queryForMap(statementName, obj, keyProperty);
	}

	@Override
	public <T> Map queryForMap(String statementName, Object obj, String keyProperty, String valueProperty) {
		return ibatisDaoReader.queryForMap(statementName, obj, keyProperty, valueProperty);
	}
}