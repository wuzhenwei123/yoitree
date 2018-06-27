package com.yy.yyCourseFunction.dao;

import java.util.List;

import com.yy.yyCourseFunction.model.YyCourseFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-22 13:22:53
 */
 @Repository("yyCourseFunctionDao")
public class YyCourseFunctionDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyCourseFunction> getYyCourseFunctionList(YyCourseFunction yyCourseFunction) {
		List<YyCourseFunction> list = sqlMapClient.queryForList("YyCourseFunction.getYyCourseFunctionList", yyCourseFunction);
		return buildResponseList(list);
	}

	public List<YyCourseFunction> getYyCourseFunctionBaseList(YyCourseFunction yyCourseFunction) {
		return sqlMapClient.queryForList("YyCourseFunction.getYyCourseFunction", yyCourseFunction);
	}

	public int getYyCourseFunctionListCount(YyCourseFunction yyCourseFunction) {
		return (Integer)sqlMapClient.queryForObject("YyCourseFunction.getYyCourseFunctionListCount", yyCourseFunction);
	}
	
	public YyCourseFunction getYyCourseFunction(YyCourseFunction yyCourseFunction) {
		return (YyCourseFunction)sqlMapClient.queryForObject("YyCourseFunction.getYyCourseFunction", yyCourseFunction);
	}

    public long insertYyCourseFunction(YyCourseFunction yyCourseFunction) throws Exception {
        return (Long)sqlMapClient.insert("YyCourseFunction.insertYyCourseFunction", yyCourseFunction);
    }

    public int updateYyCourseFunction(YyCourseFunction yyCourseFunction) throws Exception {
        return sqlMapClient.update("YyCourseFunction.updateYyCourseFunction", yyCourseFunction);
    }
    
    public int removeYyCourseFunction(YyCourseFunction yyCourseFunction) throws Exception {
        return sqlMapClient.delete("YyCourseFunction.removeYyCourseFunction", yyCourseFunction);
    }

}
