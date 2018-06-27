package com.yy.yyLearningStyle.dao;

import java.util.List;

import com.yy.yyLearningStyle.model.YyLearningStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-09 20:51:40
 */
 @Repository("yyLearningStyleDao")
public class YyLearningStyleDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyLearningStyle> getYyLearningStyleList(YyLearningStyle yyLearningStyle) {
		List<YyLearningStyle> list = sqlMapClient.queryForList("YyLearningStyle.getYyLearningStyleList", yyLearningStyle);
		return buildResponseList(list);
	}

	public List<YyLearningStyle> getYyLearningStyleBaseList(YyLearningStyle yyLearningStyle) {
		return sqlMapClient.queryForList("YyLearningStyle.getYyLearningStyle", yyLearningStyle);
	}

	public int getYyLearningStyleListCount(YyLearningStyle yyLearningStyle) {
		return (Integer)sqlMapClient.queryForObject("YyLearningStyle.getYyLearningStyleListCount", yyLearningStyle);
	}
	
	public YyLearningStyle getYyLearningStyle(YyLearningStyle yyLearningStyle) {
		return (YyLearningStyle)sqlMapClient.queryForObject("YyLearningStyle.getYyLearningStyle", yyLearningStyle);
	}

    public int insertYyLearningStyle(YyLearningStyle yyLearningStyle) throws Exception {
        return (Integer)sqlMapClient.insert("YyLearningStyle.insertYyLearningStyle", yyLearningStyle);
    }

    public int updateYyLearningStyle(YyLearningStyle yyLearningStyle) throws Exception {
        return sqlMapClient.update("YyLearningStyle.updateYyLearningStyle", yyLearningStyle);
    }
    
    public int removeYyLearningStyle(YyLearningStyle yyLearningStyle) throws Exception {
        return sqlMapClient.delete("YyLearningStyle.removeYyLearningStyle", yyLearningStyle);
    }

}
