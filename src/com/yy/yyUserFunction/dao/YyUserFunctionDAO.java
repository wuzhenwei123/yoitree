package com.yy.yyUserFunction.dao;

import java.util.List;

import com.yy.yyUserFunction.model.YyUserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-11-13 10:23:22
 */
 @Repository("yyUserFunctionDao")
public class YyUserFunctionDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyUserFunction> getYyUserFunctionList(YyUserFunction yyUserFunction) {
		List<YyUserFunction> list = sqlMapClient.queryForList("YyUserFunction.getYyUserFunctionList", yyUserFunction);
		return buildResponseList(list);
	}

	public List<YyUserFunction> getYyUserFunctionBaseList(YyUserFunction yyUserFunction) {
		return sqlMapClient.queryForList("YyUserFunction.getYyUserFunction", yyUserFunction);
	}

	public int getYyUserFunctionListCount(YyUserFunction yyUserFunction) {
		return (Integer)sqlMapClient.queryForObject("YyUserFunction.getYyUserFunctionListCount", yyUserFunction);
	}
	
	public YyUserFunction getYyUserFunction(YyUserFunction yyUserFunction) {
		return (YyUserFunction)sqlMapClient.queryForObject("YyUserFunction.getYyUserFunction", yyUserFunction);
	}

    public int insertYyUserFunction(YyUserFunction yyUserFunction) throws Exception {
        return (Integer)sqlMapClient.insert("YyUserFunction.insertYyUserFunction", yyUserFunction);
    }

    public int updateYyUserFunction(YyUserFunction yyUserFunction) throws Exception {
        return sqlMapClient.update("YyUserFunction.updateYyUserFunction", yyUserFunction);
    }
    
    public int removeYyUserFunction(YyUserFunction yyUserFunction) throws Exception {
        return sqlMapClient.delete("YyUserFunction.removeYyUserFunction", yyUserFunction);
    }

}
