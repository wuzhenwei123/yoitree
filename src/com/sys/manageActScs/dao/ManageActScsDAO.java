package com.sys.manageActScs.dao;

import java.util.List;

import com.sys.manageActScs.model.ManageActScs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	keeny
 * @time	2016-05-04 10:19:14
 */
 @Repository("manageActScsDao")
public class ManageActScsDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<ManageActScs> getManageActScsList(ManageActScs manageActScs) {
		List<ManageActScs> list = sqlMapClient.queryForList("ManageActScs.getManageActScsList", manageActScs);
		return buildResponseList(list);
	}

	public List<ManageActScs> getManageActScsBaseList(ManageActScs manageActScs) {
		return sqlMapClient.queryForList("ManageActScs.getManageActScs", manageActScs);
	}

	public int getManageActScsListCount(ManageActScs manageActScs) {
		return (Integer)sqlMapClient.queryForObject("ManageActScs.getManageActScsListCount", manageActScs);
	}
	
	public ManageActScs getManageActScs(ManageActScs manageActScs) {
		return (ManageActScs)sqlMapClient.queryForObject("ManageActScs.getManageActScs", manageActScs);
	}

    public int insertManageActScs(ManageActScs manageActScs) throws Exception {
        return (Integer)sqlMapClient.insert("ManageActScs.insertManageActScs", manageActScs);
    }

    public int updateManageActScs(ManageActScs manageActScs) throws Exception {
        return sqlMapClient.update("ManageActScs.updateManageActScs", manageActScs);
    }
    
    public int removeManageActScs(ManageActScs manageActScs) throws Exception {
        return sqlMapClient.delete("ManageActScs.removeManageActScs", manageActScs);
    }

}
