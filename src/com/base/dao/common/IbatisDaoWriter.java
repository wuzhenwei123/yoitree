package com.base.dao.common;
/**
 * 
 * @author zhangLibo on 2015年4月2日 上午9:18:51
 *
 */
public interface IbatisDaoWriter {
	/**
	 * 保存一个实体对象
	 * 
	 * @param statementName
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract Object insert(String statementName, Object obj) throws Exception;

	/**
	 * 保存
	 * 
	 * @param statementName
	 * @return
	 * @throws Exception
	 */
	public abstract Object insert(String statementName) throws Exception;

	/**
	 * 更新一个实体对象
	 * 
	 * @param statementName
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract int update(String statementName, Object obj) throws Exception;

	/**
	 * 更新
	 * 
	 * @param statementName
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public abstract int update(String statementName) throws Exception;

	/**
	 * 按照条件删除记录
	 * 
	 * @param statementName
	 * @param paramObj
	 * @return
	 * @throws Exception
	 */
	public abstract int delete(String statementName, Object obj) throws Exception;

	/**
	 * 按照条件删除记录
	 * 
	 * @param statementName
	 * @return
	 * @throws Exception
	 */
	public abstract int delete(String statementName) throws Exception;
}
