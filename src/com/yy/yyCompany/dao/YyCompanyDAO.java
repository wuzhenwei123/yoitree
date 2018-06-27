package com.yy.yyCompany.dao;

import java.util.List;

import com.yy.yyCompany.model.YyCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-09 21:54:36
 */
 @Repository("yyCompanyDao")
public class YyCompanyDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyCompany> getYyCompanyList(YyCompany yyCompany) {
		List<YyCompany> list = sqlMapClient.queryForList("YyCompany.getYyCompanyList", yyCompany);
		return buildResponseList(list);
	}

	public List<YyCompany> getYyCompanyBaseList(YyCompany yyCompany) {
		return sqlMapClient.queryForList("YyCompany.getYyCompany", yyCompany);
	}
	
	public List<YyCompany> companyAnalysis(YyCompany yyCompany) {
		return sqlMapClient.queryForList("YyCompany.companyAnalysis", yyCompany);
	}

	public int getYyCompanyListCount(YyCompany yyCompany) {
		return (Integer)sqlMapClient.queryForObject("YyCompany.getYyCompanyListCount", yyCompany);
	}
	
	public YyCompany getYyCompany(YyCompany yyCompany) {
		return (YyCompany)sqlMapClient.queryForObject("YyCompany.getYyCompany", yyCompany);
	}

    public long insertYyCompany(YyCompany yyCompany) throws Exception {
        return (Long)sqlMapClient.insert("YyCompany.insertYyCompany", yyCompany);
    }

    public int updateYyCompany(YyCompany yyCompany) throws Exception {
        return sqlMapClient.update("YyCompany.updateYyCompany", yyCompany);
    }
    
    public int removeYyCompany(YyCompany yyCompany) throws Exception {
        return sqlMapClient.delete("YyCompany.removeYyCompany", yyCompany);
    }

}
