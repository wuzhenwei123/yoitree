package com.yy.yyCourseAppendix.dao;

import java.util.List;

import com.yy.yyCourseAppendix.model.YyCourseAppendix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-14 18:26:43
 */
 @Repository("yyCourseAppendixDao")
public class YyCourseAppendixDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyCourseAppendix> getYyCourseAppendixList(YyCourseAppendix yyCourseAppendix) {
		List<YyCourseAppendix> list = sqlMapClient.queryForList("YyCourseAppendix.getYyCourseAppendixList", yyCourseAppendix);
		return buildResponseList(list);
	}

	public List<YyCourseAppendix> getYyCourseAppendixBaseList(YyCourseAppendix yyCourseAppendix) {
		return sqlMapClient.queryForList("YyCourseAppendix.getYyCourseAppendix", yyCourseAppendix);
	}

	public int getYyCourseAppendixListCount(YyCourseAppendix yyCourseAppendix) {
		return (Integer)sqlMapClient.queryForObject("YyCourseAppendix.getYyCourseAppendixListCount", yyCourseAppendix);
	}
	
	public YyCourseAppendix getYyCourseAppendix(YyCourseAppendix yyCourseAppendix) {
		return (YyCourseAppendix)sqlMapClient.queryForObject("YyCourseAppendix.getYyCourseAppendix", yyCourseAppendix);
	}

    public long insertYyCourseAppendix(YyCourseAppendix yyCourseAppendix) throws Exception {
        return (Long)sqlMapClient.insert("YyCourseAppendix.insertYyCourseAppendix", yyCourseAppendix);
    }
    
    public long insertYyCourseAppendix1(YyCourseAppendix yyCourseAppendix) throws Exception {
    	return (Long)sqlMapClient.insert("YyCourseAppendix.insertYyCourseAppendix1", yyCourseAppendix);
    }

    public int updateYyCourseAppendix(YyCourseAppendix yyCourseAppendix) throws Exception {
        return sqlMapClient.update("YyCourseAppendix.updateYyCourseAppendix", yyCourseAppendix);
    }
    
    public int removeYyCourseAppendix(YyCourseAppendix yyCourseAppendix) throws Exception {
        return sqlMapClient.delete("YyCourseAppendix.removeYyCourseAppendix", yyCourseAppendix);
    }

}
