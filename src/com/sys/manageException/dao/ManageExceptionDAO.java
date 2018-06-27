package com.sys.manageException.dao;

import java.util.List;

import com.sys.manageException.model.ManageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	keeny
 * @time	2016-04-27 11:36:30
 */
 @Repository("manageExceptionDao")
public class ManageExceptionDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<ManageException> getManageExceptionList(ManageException manageException) {
		List<ManageException> list = sqlMapClient.queryForList("ManageException.getManageExceptionList", manageException);
		return buildResponseList(list);
	}

	public List<ManageException> getManageExceptionBaseList(ManageException manageException) {
		return sqlMapClient.queryForList("ManageException.getManageException", manageException);
	}

	public int getManageExceptionListCount(ManageException manageException) {
		return (Integer)sqlMapClient.queryForObject("ManageException.getManageExceptionListCount", manageException);
	}
	
	public ManageException getManageException(ManageException manageException) {
		return (ManageException)sqlMapClient.queryForObject("ManageException.getManageException", manageException);
	}

    public int insertManageException(ManageException manageException) throws Exception {
        return (Integer)sqlMapClient.insert("ManageException.insertManageException", manageException);
    }

    public int updateManageException(ManageException manageException) throws Exception {
        return sqlMapClient.update("ManageException.updateManageException", manageException);
    }
    
    public int removeManageException(ManageException manageException) throws Exception {
        return sqlMapClient.delete("ManageException.removeManageException", manageException);
    }

}
