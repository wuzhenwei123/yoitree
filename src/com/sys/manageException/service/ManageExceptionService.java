package com.sys.manageException.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.manageException.dao.ManageExceptionDAO;
import com.sys.manageException.model.ManageException;
import com.base.utils.ResponseList;

/**
 * @author	keeny
 * @time	2016-04-27 11:36:30
 */
 @Service("manageExceptionService")
public class ManageExceptionService {

	@Resource(name = "manageExceptionDao")
    private ManageExceptionDAO manageExceptionDAO;
    
    public ResponseList<ManageException> getManageExceptionList(ManageException manageException) {
        return manageExceptionDAO.getManageExceptionList(manageException);
    }
    
    public List<ManageException> getManageExceptionBaseList(ManageException manageException) {
        return manageExceptionDAO.getManageExceptionBaseList(manageException);
    }
    
    public int getManageExceptionListCount(ManageException manageException) {
        return manageExceptionDAO.getManageExceptionListCount(manageException);
    }

    public ManageException getManageException(ManageException manageException) { 
        return manageExceptionDAO.getManageException(manageException);
    }

    public int insertManageException(ManageException manageException) throws Exception {
        return manageExceptionDAO.insertManageException(manageException);
    }

    public int updateManageException(ManageException manageException) throws Exception {
        return manageExceptionDAO.updateManageException(manageException);
    }
    
    public int removeManageException(ManageException manageException) throws Exception {
        return manageExceptionDAO.removeManageException(manageException);
    }
    
}
