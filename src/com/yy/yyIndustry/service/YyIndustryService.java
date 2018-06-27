package com.yy.yyIndustry.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyIndustry.dao.YyIndustryDAO;
import com.yy.yyIndustry.model.YyIndustry;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-09 20:04:01
 */
 @Service("yyIndustryService")
public class YyIndustryService {

	@Resource(name = "yyIndustryDao")
    private YyIndustryDAO yyIndustryDAO;
    
    public ResponseList<YyIndustry> getYyIndustryList(YyIndustry yyIndustry) {
        return yyIndustryDAO.getYyIndustryList(yyIndustry);
    }
    
    public List<YyIndustry> getYyIndustryBaseList(YyIndustry yyIndustry) {
        return yyIndustryDAO.getYyIndustryBaseList(yyIndustry);
    }
    
    public int getYyIndustryListCount(YyIndustry yyIndustry) {
        return yyIndustryDAO.getYyIndustryListCount(yyIndustry);
    }

    public YyIndustry getYyIndustry(YyIndustry yyIndustry) { 
        return yyIndustryDAO.getYyIndustry(yyIndustry);
    }

    public int insertYyIndustry(YyIndustry yyIndustry) throws Exception {
        return yyIndustryDAO.insertYyIndustry(yyIndustry);
    }

    public int updateYyIndustry(YyIndustry yyIndustry) throws Exception {
        return yyIndustryDAO.updateYyIndustry(yyIndustry);
    }
    
    public int removeYyIndustry(YyIndustry yyIndustry) throws Exception {
        return yyIndustryDAO.removeYyIndustry(yyIndustry);
    }
    
}
