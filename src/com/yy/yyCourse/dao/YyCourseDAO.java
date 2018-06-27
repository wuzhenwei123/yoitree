package com.yy.yyCourse.dao;

import java.util.List;

import com.yy.yyCourse.model.YyCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-11 16:02:52
 */
 @Repository("yyCourseDao")
public class YyCourseDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyCourse> getYyCourseList(YyCourse yyCourse) {
		List<YyCourse> list = sqlMapClient.queryForList("YyCourse.getYyCourseList", yyCourse);
		return buildResponseList(list);
	}

	public List<YyCourse> getYyCourseBaseList(YyCourse yyCourse) {
		return sqlMapClient.queryForList("YyCourse.getYyCourse", yyCourse);
	}
	
	/**
	 * 根据职能ID获取课程
	 * @param yyCourse
	 * @return
	 */
	public List<YyCourse> getYyCourseListByFunction(YyCourse yyCourse) {
		return sqlMapClient.queryForList("YyCourse.getYyCourseListByFunction", yyCourse);
	}
	
	/**
	 * 根据职能ID获取课程
	 * @param yyCourse
	 * @return
	 */
	public List<YyCourse> getYyCourseTotal(YyCourse yyCourse) {
		return sqlMapClient.queryForList("YyCourse.getYyCourseTotal", yyCourse);
	}
	
	/**
	 * 根据分类ID获取课程
	 * @param yyCourse
	 * @return
	 */
	public List<YyCourse> getYyCourseListByClassify(YyCourse yyCourse) {
		return sqlMapClient.queryForList("YyCourse.getYyCourseListByClassify", yyCourse);
	}
	
	public List<YyCourse> getYyCourseListByFunctionS(YyCourse yyCourse) {
		return sqlMapClient.queryForList("YyCourse.getYyCourseListByFunctionS", yyCourse);
	}

	public int getYyCourseListCount(YyCourse yyCourse) {
		return (Integer)sqlMapClient.queryForObject("YyCourse.getYyCourseListCount", yyCourse);
	}
	
	public YyCourse getYyCourse(YyCourse yyCourse) {
		return (YyCourse)sqlMapClient.queryForObject("YyCourse.getYyCourse", yyCourse);
	}

    public long insertYyCourse(YyCourse yyCourse) throws Exception {
        return (Long)sqlMapClient.insert("YyCourse.insertYyCourse", yyCourse);
    }

    public int updateYyCourse(YyCourse yyCourse) throws Exception {
        return sqlMapClient.update("YyCourse.updateYyCourse", yyCourse);
    }
    
    public int removeYyCourse(YyCourse yyCourse) throws Exception {
        return sqlMapClient.delete("YyCourse.removeYyCourse", yyCourse);
    }

}
