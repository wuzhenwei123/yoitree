package com.yy.yyFunction.dao;

import java.util.List;

import com.yy.yyFunction.model.YyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-22 11:31:15
 */
 @Repository("yyFunctionDao")
public class YyFunctionDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyFunction> getYyFunctionList(YyFunction yyFunction) {
		List<YyFunction> list = sqlMapClient.queryForList("YyFunction.getYyFunctionList", yyFunction);
		return buildResponseList(list);
	}

	public List<YyFunction> getYyFunctionBaseList(YyFunction yyFunction) {
		return sqlMapClient.queryForList("YyFunction.getYyFunction", yyFunction);
	}

	public int getYyFunctionListCount(YyFunction yyFunction) {
		return (Integer)sqlMapClient.queryForObject("YyFunction.getYyFunctionListCount", yyFunction);
	}
	
	public YyFunction getYyFunction(YyFunction yyFunction) {
		return (YyFunction)sqlMapClient.queryForObject("YyFunction.getYyFunction", yyFunction);
	}

    public int insertYyFunction(YyFunction yyFunction) throws Exception {
        return (Integer)sqlMapClient.insert("YyFunction.insertYyFunction", yyFunction);
    }

    public int updateYyFunction(YyFunction yyFunction) throws Exception {
        return sqlMapClient.update("YyFunction.updateYyFunction", yyFunction);
    }
    
    public int removeYyFunction(YyFunction yyFunction) throws Exception {
        return sqlMapClient.delete("YyFunction.removeYyFunction", yyFunction);
    }

}
