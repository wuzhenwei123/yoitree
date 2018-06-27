package com.yy.yyCompany.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCompany.dao.YyCompanyDAO;
import com.yy.yyCompany.model.YyCompany;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-09 21:54:36
 */
 @Service("yyCompanyService")
public class YyCompanyService {

	@Resource(name = "yyCompanyDao")
    private YyCompanyDAO yyCompanyDAO;
    
    public ResponseList<YyCompany> getYyCompanyList(YyCompany yyCompany) {
        return yyCompanyDAO.getYyCompanyList(yyCompany);
    }
    
    public List<YyCompany> getYyCompanyBaseList(YyCompany yyCompany) {
        return yyCompanyDAO.getYyCompanyBaseList(yyCompany);
    }
    
    public List<YyCompany> companyAnalysis(YyCompany yyCompany) {
    	return yyCompanyDAO.companyAnalysis(yyCompany);
    }
    
    public int getYyCompanyListCount(YyCompany yyCompany) {
        return yyCompanyDAO.getYyCompanyListCount(yyCompany);
    }

    public YyCompany getYyCompany(YyCompany yyCompany) { 
        return yyCompanyDAO.getYyCompany(yyCompany);
    }

    public long insertYyCompany(YyCompany yyCompany) throws Exception {
        return yyCompanyDAO.insertYyCompany(yyCompany);
    }

    public int updateYyCompany(YyCompany yyCompany) throws Exception {
        return yyCompanyDAO.updateYyCompany(yyCompany);
    }
    
    public int removeYyCompany(YyCompany yyCompany) throws Exception {
        return yyCompanyDAO.removeYyCompany(yyCompany);
    }
    
}
