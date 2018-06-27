package com.yy.yyFunction.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyFunction.dao.YyFunctionDAO;
import com.yy.yyFunction.model.YyFunction;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-22 11:31:15
 */
 @Service("yyFunctionService")
public class YyFunctionService {

	@Resource(name = "yyFunctionDao")
    private YyFunctionDAO yyFunctionDAO;
    
    public ResponseList<YyFunction> getYyFunctionList(YyFunction yyFunction) {
        return yyFunctionDAO.getYyFunctionList(yyFunction);
    }
    
    public List<YyFunction> getYyFunctionBaseList(YyFunction yyFunction) {
        return yyFunctionDAO.getYyFunctionBaseList(yyFunction);
    }
    
    public int getYyFunctionListCount(YyFunction yyFunction) {
        return yyFunctionDAO.getYyFunctionListCount(yyFunction);
    }

    public YyFunction getYyFunction(YyFunction yyFunction) { 
        return yyFunctionDAO.getYyFunction(yyFunction);
    }

    public int insertYyFunction(YyFunction yyFunction) throws Exception {
        return yyFunctionDAO.insertYyFunction(yyFunction);
    }

    public int updateYyFunction(YyFunction yyFunction) throws Exception {
        return yyFunctionDAO.updateYyFunction(yyFunction);
    }
    
    public int removeYyFunction(YyFunction yyFunction) throws Exception {
        return yyFunctionDAO.removeYyFunction(yyFunction);
    }
    
}
