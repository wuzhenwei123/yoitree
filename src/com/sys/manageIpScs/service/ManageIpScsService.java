package com.sys.manageIpScs.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.manageIpScs.dao.ManageIpScsDAO;
import com.sys.manageIpScs.model.ManageIpScs;
import com.base.utils.ResponseList;

/**
 * @author	keeny
 * @time	2016-05-04 11:40:08
 */
 @Service("manageIpScsService")
public class ManageIpScsService {

	@Resource(name = "manageIpScsDao")
    private ManageIpScsDAO manageIpScsDAO;
    
    public ResponseList<ManageIpScs> getManageIpScsList(ManageIpScs manageIpScs) {
        return manageIpScsDAO.getManageIpScsList(manageIpScs);
    }
    
    public List<ManageIpScs> getManageIpScsBaseList(ManageIpScs manageIpScs) {
        return manageIpScsDAO.getManageIpScsBaseList(manageIpScs);
    }
    
    public int getManageIpScsListCount(ManageIpScs manageIpScs) {
        return manageIpScsDAO.getManageIpScsListCount(manageIpScs);
    }

    public ManageIpScs getManageIpScs(ManageIpScs manageIpScs) { 
        return manageIpScsDAO.getManageIpScs(manageIpScs);
    }

    public int insertManageIpScs(ManageIpScs manageIpScs) throws Exception {
        return manageIpScsDAO.insertManageIpScs(manageIpScs);
    }

    public int updateManageIpScs(ManageIpScs manageIpScs) throws Exception {
        return manageIpScsDAO.updateManageIpScs(manageIpScs);
    }
    
    public int removeManageIpScs(ManageIpScs manageIpScs) throws Exception {
        return manageIpScsDAO.removeManageIpScs(manageIpScs);
    }
    
}
