package com.yy.yyUserFunction.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyUserFunction.dao.YyUserFunctionDAO;
import com.yy.yyUserFunction.model.YyUserFunction;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-11-13 10:23:22
 */
 @Service("yyUserFunctionService")
public class YyUserFunctionService {

	@Resource(name = "yyUserFunctionDao")
    private YyUserFunctionDAO yyUserFunctionDAO;
    
    public ResponseList<YyUserFunction> getYyUserFunctionList(YyUserFunction yyUserFunction) {
        return yyUserFunctionDAO.getYyUserFunctionList(yyUserFunction);
    }
    
    public List<YyUserFunction> getYyUserFunctionBaseList(YyUserFunction yyUserFunction) {
        return yyUserFunctionDAO.getYyUserFunctionBaseList(yyUserFunction);
    }
    
    public int getYyUserFunctionListCount(YyUserFunction yyUserFunction) {
        return yyUserFunctionDAO.getYyUserFunctionListCount(yyUserFunction);
    }

    public YyUserFunction getYyUserFunction(YyUserFunction yyUserFunction) { 
        return yyUserFunctionDAO.getYyUserFunction(yyUserFunction);
    }

    public int insertYyUserFunction(YyUserFunction yyUserFunction) throws Exception {
        return yyUserFunctionDAO.insertYyUserFunction(yyUserFunction);
    }

    public int updateYyUserFunction(YyUserFunction yyUserFunction) throws Exception {
        return yyUserFunctionDAO.updateYyUserFunction(yyUserFunction);
    }
    
    public int removeYyUserFunction(YyUserFunction yyUserFunction) throws Exception {
        return yyUserFunctionDAO.removeYyUserFunction(yyUserFunction);
    }
    
}
