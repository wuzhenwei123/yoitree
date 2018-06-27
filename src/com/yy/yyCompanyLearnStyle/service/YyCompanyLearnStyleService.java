package com.yy.yyCompanyLearnStyle.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCompanyLearnStyle.dao.YyCompanyLearnStyleDAO;
import com.yy.yyCompanyLearnStyle.model.YyCompanyLearnStyle;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-09 21:55:38
 */
 @Service("yyCompanyLearnStyleService")
public class YyCompanyLearnStyleService {

	@Resource(name = "yyCompanyLearnStyleDao")
    private YyCompanyLearnStyleDAO yyCompanyLearnStyleDAO;
    
    public ResponseList<YyCompanyLearnStyle> getYyCompanyLearnStyleList(YyCompanyLearnStyle yyCompanyLearnStyle) {
        return yyCompanyLearnStyleDAO.getYyCompanyLearnStyleList(yyCompanyLearnStyle);
    }
    
    public List<YyCompanyLearnStyle> getYyCompanyLearnStyleBaseList(YyCompanyLearnStyle yyCompanyLearnStyle) {
        return yyCompanyLearnStyleDAO.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
    }
    
    public int getYyCompanyLearnStyleListCount(YyCompanyLearnStyle yyCompanyLearnStyle) {
        return yyCompanyLearnStyleDAO.getYyCompanyLearnStyleListCount(yyCompanyLearnStyle);
    }

    public YyCompanyLearnStyle getYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) { 
        return yyCompanyLearnStyleDAO.getYyCompanyLearnStyle(yyCompanyLearnStyle);
    }

    public long insertYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) throws Exception {
        return yyCompanyLearnStyleDAO.insertYyCompanyLearnStyle(yyCompanyLearnStyle);
    }

    public int updateYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) throws Exception {
        return yyCompanyLearnStyleDAO.updateYyCompanyLearnStyle(yyCompanyLearnStyle);
    }
    
    public int removeYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) throws Exception {
        return yyCompanyLearnStyleDAO.removeYyCompanyLearnStyle(yyCompanyLearnStyle);
    }
    
}
