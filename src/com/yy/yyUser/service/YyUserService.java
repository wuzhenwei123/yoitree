package com.yy.yyUser.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyUser.dao.YyUserDAO;
import com.yy.yyUser.model.YyUser;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-10 14:03:43
 */
 @Service("yyUserService")
public class YyUserService {

	@Resource(name = "yyUserDao")
    private YyUserDAO yyUserDAO;
    
    public ResponseList<YyUser> getYyUserList(YyUser yyUser) {
        return yyUserDAO.getYyUserList(yyUser);
    }
    
    public List<YyUser> getYyUserBaseList(YyUser yyUser) {
        return yyUserDAO.getYyUserBaseList(yyUser);
    }
    
    public List<YyUser> userAnalysis(YyUser yyUser) {
    	return yyUserDAO.userAnalysis(yyUser);
    }
    
    public List<YyUser> userCountAnalysis(YyUser yyUser) {
    	return yyUserDAO.userCountAnalysis(yyUser);
    }
    
    public int getYyUserListCount(YyUser yyUser) {
        return yyUserDAO.getYyUserListCount(yyUser);
    }
    
    public int checkLoginName(YyUser yyUser) {
    	return yyUserDAO.checkLoginName(yyUser);
    }

    public YyUser getYyUser(YyUser yyUser) { 
        return yyUserDAO.getYyUser(yyUser);
    }

    public long insertYyUser(YyUser yyUser) throws Exception {
        return yyUserDAO.insertYyUser(yyUser);
    }

    public int updateYyUser(YyUser yyUser) throws Exception {
        return yyUserDAO.updateYyUser(yyUser);
    }
    
    public int unBind(YyUser yyUser) throws Exception {
    	return yyUserDAO.unBind(yyUser);
    }
    
    public int updateCompany_name(YyUser yyUser) throws Exception {
    	return yyUserDAO.updateCompany_name(yyUser);
    }
    
    public int updateParent_name(YyUser yyUser) throws Exception {
    	return yyUserDAO.updateParent_name(yyUser);
    }
    
    public int removeYyUser(YyUser yyUser) throws Exception {
        return yyUserDAO.removeYyUser(yyUser);
    }
    
}
