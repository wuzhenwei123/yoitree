package com.yy.yyCourseClassify.dao;

import java.util.List;

import com.yy.yyCourseClassify.model.YyCourseClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-10 22:02:35
 */
 @Repository("yyCourseClassifyDao")
public class YyCourseClassifyDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyCourseClassify> getYyCourseClassifyList(YyCourseClassify yyCourseClassify) {
		List<YyCourseClassify> list = sqlMapClient.queryForList("YyCourseClassify.getYyCourseClassifyList", yyCourseClassify);
		return buildResponseList(list);
	}

	public List<YyCourseClassify> getYyCourseClassifyBaseList(YyCourseClassify yyCourseClassify) {
		return sqlMapClient.queryForList("YyCourseClassify.getYyCourseClassify", yyCourseClassify);
	}
	
	public List<YyCourseClassify> getCourseClassifyByKeyWord(YyCourseClassify yyCourseClassify) {
		return sqlMapClient.queryForList("YyCourseClassify.getCourseClassifyByKeyWord", yyCourseClassify);
	}
	
	public List<YyCourseClassify> getCourseClassifyParents(YyCourseClassify yyCourseClassify) {
		return sqlMapClient.queryForList("YyCourseClassify.getCourseClassifyParents", yyCourseClassify);
	}

	public int getYyCourseClassifyListCount(YyCourseClassify yyCourseClassify) {
		return (Integer)sqlMapClient.queryForObject("YyCourseClassify.getYyCourseClassifyListCount", yyCourseClassify);
	}
	
	public YyCourseClassify getYyCourseClassify(YyCourseClassify yyCourseClassify) {
		return (YyCourseClassify)sqlMapClient.queryForObject("YyCourseClassify.getYyCourseClassify", yyCourseClassify);
	}

    public long insertYyCourseClassify(YyCourseClassify yyCourseClassify) throws Exception {
        return (Long)sqlMapClient.insert("YyCourseClassify.insertYyCourseClassify", yyCourseClassify);
    }

    public int updateYyCourseClassify(YyCourseClassify yyCourseClassify) throws Exception {
        return sqlMapClient.update("YyCourseClassify.updateYyCourseClassify", yyCourseClassify);
    }
    
    public int removeYyCourseClassify(YyCourseClassify yyCourseClassify) throws Exception {
        return sqlMapClient.delete("YyCourseClassify.removeYyCourseClassify", yyCourseClassify);
    }

}
