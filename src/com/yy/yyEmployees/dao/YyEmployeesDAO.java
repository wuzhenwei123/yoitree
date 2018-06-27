package com.yy.yyEmployees.dao;

import java.util.List;

import com.yy.yyEmployees.model.YyEmployees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-09 21:07:34
 */
 @Repository("yyEmployeesDao")
public class YyEmployeesDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyEmployees> getYyEmployeesList(YyEmployees yyEmployees) {
		List<YyEmployees> list = sqlMapClient.queryForList("YyEmployees.getYyEmployeesList", yyEmployees);
		return buildResponseList(list);
	}

	public List<YyEmployees> getYyEmployeesBaseList(YyEmployees yyEmployees) {
		return sqlMapClient.queryForList("YyEmployees.getYyEmployees", yyEmployees);
	}

	public int getYyEmployeesListCount(YyEmployees yyEmployees) {
		return (Integer)sqlMapClient.queryForObject("YyEmployees.getYyEmployeesListCount", yyEmployees);
	}
	
	public YyEmployees getYyEmployees(YyEmployees yyEmployees) {
		return (YyEmployees)sqlMapClient.queryForObject("YyEmployees.getYyEmployees", yyEmployees);
	}

    public int insertYyEmployees(YyEmployees yyEmployees) throws Exception {
        return (Integer)sqlMapClient.insert("YyEmployees.insertYyEmployees", yyEmployees);
    }

    public int updateYyEmployees(YyEmployees yyEmployees) throws Exception {
        return sqlMapClient.update("YyEmployees.updateYyEmployees", yyEmployees);
    }
    
    public int removeYyEmployees(YyEmployees yyEmployees) throws Exception {
        return sqlMapClient.delete("YyEmployees.removeYyEmployees", yyEmployees);
    }

}
