package com.yy.yyEmployees.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyEmployees.dao.YyEmployeesDAO;
import com.yy.yyEmployees.model.YyEmployees;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-09 21:07:34
 */
 @Service("yyEmployeesService")
public class YyEmployeesService {

	@Resource(name = "yyEmployeesDao")
    private YyEmployeesDAO yyEmployeesDAO;
    
    public ResponseList<YyEmployees> getYyEmployeesList(YyEmployees yyEmployees) {
        return yyEmployeesDAO.getYyEmployeesList(yyEmployees);
    }
    
    public List<YyEmployees> getYyEmployeesBaseList(YyEmployees yyEmployees) {
        return yyEmployeesDAO.getYyEmployeesBaseList(yyEmployees);
    }
    
    public int getYyEmployeesListCount(YyEmployees yyEmployees) {
        return yyEmployeesDAO.getYyEmployeesListCount(yyEmployees);
    }

    public YyEmployees getYyEmployees(YyEmployees yyEmployees) { 
        return yyEmployeesDAO.getYyEmployees(yyEmployees);
    }

    public int insertYyEmployees(YyEmployees yyEmployees) throws Exception {
        return yyEmployeesDAO.insertYyEmployees(yyEmployees);
    }

    public int updateYyEmployees(YyEmployees yyEmployees) throws Exception {
        return yyEmployeesDAO.updateYyEmployees(yyEmployees);
    }
    
    public int removeYyEmployees(YyEmployees yyEmployees) throws Exception {
        return yyEmployeesDAO.removeYyEmployees(yyEmployees);
    }
    
}
