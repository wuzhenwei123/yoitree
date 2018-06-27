package com.yy.yyUserCourse.dao;

import java.util.List;

import com.yy.yyUserCourse.model.YyUserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-10-04 11:30:06
 */
 @Repository("yyUserCourseDao")
public class YyUserCourseDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyUserCourse> getYyUserCourseList(YyUserCourse yyUserCourse) {
		List<YyUserCourse> list = sqlMapClient.queryForList("YyUserCourse.getYyUserCourseList", yyUserCourse);
		return buildResponseList(list);
	}

	public List<YyUserCourse> getYyUserCourseBaseList(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getYyUserCourse", yyUserCourse);
	}
	
	public List<YyUserCourse> getDataByUserAndClassify(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getDataByUserAndClassify", yyUserCourse);
	}
	
	public List<YyUserCourse> getYyUserCourseByUser(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getYyUserCourseByUser", yyUserCourse);
	}
	
	public List<YyUserCourse> getYyUserCourseState(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getYyUserCourseState", yyUserCourse);
	}
	
	public List<YyUserCourse> getStudyUser(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getStudyUser", yyUserCourse);
	}
	
	public List<YyUserCourse> getYyUserCourseParent(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getYyUserCourseParent", yyUserCourse);
	}
	
	public List<YyUserCourse> getUserStudyList(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getUserStudyList", yyUserCourse);
	}
	
	public List<YyUserCourse> getUserStudyListAll(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getUserStudyListAll", yyUserCourse);
	}
	
	public List<YyUserCourse> getUserStudyStateList(YyUserCourse yyUserCourse) {
		return sqlMapClient.queryForList("YyUserCourse.getUserStudyStateList", yyUserCourse);
	}

	public int getYyUserCourseListCount(YyUserCourse yyUserCourse) {
		return (Integer)sqlMapClient.queryForObject("YyUserCourse.getYyUserCourseListCount", yyUserCourse);
	}
	
	public YyUserCourse getYyUserCourse(YyUserCourse yyUserCourse) {
		return (YyUserCourse)sqlMapClient.queryForObject("YyUserCourse.getYyUserCourse", yyUserCourse);
	}

    public long insertYyUserCourse(YyUserCourse yyUserCourse) throws Exception {
        return (Long)sqlMapClient.insert("YyUserCourse.insertYyUserCourse", yyUserCourse);
    }

    public int updateYyUserCourse(YyUserCourse yyUserCourse) throws Exception {
        return sqlMapClient.update("YyUserCourse.updateYyUserCourse", yyUserCourse);
    }
    
    public int updateYyUserCourseByUserAndPointId(YyUserCourse yyUserCourse) throws Exception {
    	return sqlMapClient.update("YyUserCourse.updateYyUserCourseByUserAndPointId", yyUserCourse);
    }
    
    public int removeYyUserCourse(YyUserCourse yyUserCourse) throws Exception {
        return sqlMapClient.delete("YyUserCourse.removeYyUserCourse", yyUserCourse);
    }
    
    public int removeYyUserCourseByOther(YyUserCourse yyUserCourse) throws Exception {
    	return sqlMapClient.delete("YyUserCourse.removeYyUserCourseByOther", yyUserCourse);
    }

}
