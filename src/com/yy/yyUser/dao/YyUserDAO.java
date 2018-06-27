package com.yy.yyUser.dao;

import java.util.List;

import com.yy.yyUser.model.YyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-10 14:03:43
 */
 @Repository("yyUserDao")
public class YyUserDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyUser> getYyUserList(YyUser yyUser) {
		List<YyUser> list = sqlMapClient.queryForList("YyUser.getYyUserList", yyUser);
		return buildResponseList(list);
	}

	public List<YyUser> getYyUserBaseList(YyUser yyUser) {
		return sqlMapClient.queryForList("YyUser.getYyUser", yyUser);
	}
	
	public List<YyUser> userAnalysis(YyUser yyUser) {
		return sqlMapClient.queryForList("YyUser.userAnalysis", yyUser);
	}
	
	public List<YyUser> userCountAnalysis(YyUser yyUser) {
		return sqlMapClient.queryForList("YyUser.userCountAnalysis", yyUser);
	}
	
	public int checkLoginName(YyUser yyUser) {
		return (Integer)sqlMapClient.queryForObject("YyUser.checkLoginName", yyUser);
	}

	public int getYyUserListCount(YyUser yyUser) {
		return (Integer)sqlMapClient.queryForObject("YyUser.getYyUserListCount", yyUser);
	}
	
	public YyUser getYyUser(YyUser yyUser) {
		return (YyUser)sqlMapClient.queryForObject("YyUser.getYyUser", yyUser);
	}

    public long insertYyUser(YyUser yyUser) throws Exception {
        return (Long)sqlMapClient.insert("YyUser.insertYyUser", yyUser);
    }

    public int updateYyUser(YyUser yyUser) throws Exception {
        return sqlMapClient.update("YyUser.updateYyUser", yyUser);
    }
    
    public int unBind(YyUser yyUser) throws Exception {
    	return sqlMapClient.update("YyUser.unBind", yyUser);
    }
    
    public int updateCompany_name(YyUser yyUser) throws Exception {
    	return sqlMapClient.update("YyUser.updateCompany_name", yyUser);
    }
    
    public int updateParent_name(YyUser yyUser) throws Exception {
    	return sqlMapClient.update("YyUser.updateParent_name", yyUser);
    }
    
    public int removeYyUser(YyUser yyUser) throws Exception {
        return sqlMapClient.delete("YyUser.removeYyUser", yyUser);
    }

}
