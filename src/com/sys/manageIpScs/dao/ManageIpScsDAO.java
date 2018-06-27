package com.sys.manageIpScs.dao;

import java.util.List;

import com.sys.manageIpScs.model.ManageIpScs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	keeny
 * @time	2016-05-04 11:40:08
 */
 @Repository("manageIpScsDao")
public class ManageIpScsDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<ManageIpScs> getManageIpScsList(ManageIpScs manageIpScs) {
		List<ManageIpScs> list = sqlMapClient.queryForList("ManageIpScs.getManageIpScsList", manageIpScs);
		return buildResponseList(list);
	}

	public List<ManageIpScs> getManageIpScsBaseList(ManageIpScs manageIpScs) {
		return sqlMapClient.queryForList("ManageIpScs.getManageIpScs", manageIpScs);
	}

	public int getManageIpScsListCount(ManageIpScs manageIpScs) {
		return (Integer)sqlMapClient.queryForObject("ManageIpScs.getManageIpScsListCount", manageIpScs);
	}
	
	public ManageIpScs getManageIpScs(ManageIpScs manageIpScs) {
		return (ManageIpScs)sqlMapClient.queryForObject("ManageIpScs.getManageIpScs", manageIpScs);
	}

    public int insertManageIpScs(ManageIpScs manageIpScs) throws Exception {
        return (Integer)sqlMapClient.insert("ManageIpScs.insertManageIpScs", manageIpScs);
    }

    public int updateManageIpScs(ManageIpScs manageIpScs) throws Exception {
        return sqlMapClient.update("ManageIpScs.updateManageIpScs", manageIpScs);
    }
    
    public int removeManageIpScs(ManageIpScs manageIpScs) throws Exception {
        return sqlMapClient.delete("ManageIpScs.removeManageIpScs", manageIpScs);
    }

}
