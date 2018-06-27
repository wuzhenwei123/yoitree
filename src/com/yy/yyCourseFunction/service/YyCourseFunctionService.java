package com.yy.yyCourseFunction.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCourseFunction.dao.YyCourseFunctionDAO;
import com.yy.yyCourseFunction.model.YyCourseFunction;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-22 13:22:53
 */
 @Service("yyCourseFunctionService")
public class YyCourseFunctionService {

	@Resource(name = "yyCourseFunctionDao")
    private YyCourseFunctionDAO yyCourseFunctionDAO;
    
    public ResponseList<YyCourseFunction> getYyCourseFunctionList(YyCourseFunction yyCourseFunction) {
        return yyCourseFunctionDAO.getYyCourseFunctionList(yyCourseFunction);
    }
    
    public List<YyCourseFunction> getYyCourseFunctionBaseList(YyCourseFunction yyCourseFunction) {
        return yyCourseFunctionDAO.getYyCourseFunctionBaseList(yyCourseFunction);
    }
    
    public int getYyCourseFunctionListCount(YyCourseFunction yyCourseFunction) {
        return yyCourseFunctionDAO.getYyCourseFunctionListCount(yyCourseFunction);
    }

    public YyCourseFunction getYyCourseFunction(YyCourseFunction yyCourseFunction) { 
        return yyCourseFunctionDAO.getYyCourseFunction(yyCourseFunction);
    }

    public long insertYyCourseFunction(YyCourseFunction yyCourseFunction) throws Exception {
        return yyCourseFunctionDAO.insertYyCourseFunction(yyCourseFunction);
    }

    public int updateYyCourseFunction(YyCourseFunction yyCourseFunction) throws Exception {
        return yyCourseFunctionDAO.updateYyCourseFunction(yyCourseFunction);
    }
    
    public int removeYyCourseFunction(YyCourseFunction yyCourseFunction) throws Exception {
        return yyCourseFunctionDAO.removeYyCourseFunction(yyCourseFunction);
    }
    
}
