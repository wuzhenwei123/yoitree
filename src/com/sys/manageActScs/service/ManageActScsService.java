package com.sys.manageActScs.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.manageActScs.dao.ManageActScsDAO;
import com.sys.manageActScs.model.ManageActScs;
import com.base.utils.ResponseList;

/**
 * @author	keeny
 * @time	2016-05-04 10:19:15
 */
 @Service("manageActScsService")
public class ManageActScsService {

	@Resource(name = "manageActScsDao")
    private ManageActScsDAO manageActScsDAO;
    
    public ResponseList<ManageActScs> getManageActScsList(ManageActScs manageActScs) {
        return manageActScsDAO.getManageActScsList(manageActScs);
    }
    
    public List<ManageActScs> getManageActScsBaseList(ManageActScs manageActScs) {
        return manageActScsDAO.getManageActScsBaseList(manageActScs);
    }
    
    public int getManageActScsListCount(ManageActScs manageActScs) {
        return manageActScsDAO.getManageActScsListCount(manageActScs);
    }

    public ManageActScs getManageActScs(ManageActScs manageActScs) { 
        return manageActScsDAO.getManageActScs(manageActScs);
    }

    public int insertManageActScs(ManageActScs manageActScs) throws Exception {
        return manageActScsDAO.insertManageActScs(manageActScs);
    }

    public int updateManageActScs(ManageActScs manageActScs) throws Exception {
        return manageActScsDAO.updateManageActScs(manageActScs);
    }
    
    public int removeManageActScs(ManageActScs manageActScs) throws Exception {
        return manageActScsDAO.removeManageActScs(manageActScs);
    }
    
}
