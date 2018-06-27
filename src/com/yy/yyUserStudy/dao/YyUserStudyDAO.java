package com.yy.yyUserStudy.dao;

import java.util.List;

import com.yy.yyUserStudy.model.YyUserStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-10-11 10:06:01
 */
 @Repository("yyUserStudyDao")
public class YyUserStudyDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyUserStudy> getYyUserStudyList(YyUserStudy yyUserStudy) {
		List<YyUserStudy> list = sqlMapClient.queryForList("YyUserStudy.getYyUserStudyList", yyUserStudy);
		return buildResponseList(list);
	}

	public List<YyUserStudy> getYyUserStudyBaseList(YyUserStudy yyUserStudy) {
		return sqlMapClient.queryForList("YyUserStudy.getYyUserStudy", yyUserStudy);
	}
	
	public List<YyUserStudy> getYyUserStudyByLesson(YyUserStudy yyUserStudy) {
		return sqlMapClient.queryForList("YyUserStudy.getYyUserStudyByLesson", yyUserStudy);
	}
	
	public List<YyUserStudy> getYyUserStudyByLessonPage(YyUserStudy yyUserStudy) {
		return sqlMapClient.queryForList("YyUserStudy.getYyUserStudyByLessonPage", yyUserStudy);
	}

	public int getYyUserStudyListCount(YyUserStudy yyUserStudy) {
		return (Integer)sqlMapClient.queryForObject("YyUserStudy.getYyUserStudyListCount", yyUserStudy);
	}
	
	public int getYyUserStudyByLessonCount(YyUserStudy yyUserStudy) {
		return (Integer)sqlMapClient.queryForObject("YyUserStudy.getYyUserStudyByLessonCount", yyUserStudy);
	}
	
	public int getUserStudyTime(YyUserStudy yyUserStudy) {
		return (Integer)sqlMapClient.queryForObject("YyUserStudy.getUserStudyTime", yyUserStudy);
	}
	
	public YyUserStudy getYyUserStudy(YyUserStudy yyUserStudy) {
		return (YyUserStudy)sqlMapClient.queryForObject("YyUserStudy.getYyUserStudy", yyUserStudy);
	}

    public long insertYyUserStudy(YyUserStudy yyUserStudy) throws Exception {
        return (Long)sqlMapClient.insert("YyUserStudy.insertYyUserStudy", yyUserStudy);
    }

    public int updateYyUserStudy(YyUserStudy yyUserStudy) throws Exception {
        return sqlMapClient.update("YyUserStudy.updateYyUserStudy", yyUserStudy);
    }
    
    public int removeYyUserStudy(YyUserStudy yyUserStudy) throws Exception {
        return sqlMapClient.delete("YyUserStudy.removeYyUserStudy", yyUserStudy);
    }

}
