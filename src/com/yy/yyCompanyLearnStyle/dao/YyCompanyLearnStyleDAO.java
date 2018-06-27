package com.yy.yyCompanyLearnStyle.dao;

import java.util.List;

import com.yy.yyCompanyLearnStyle.model.YyCompanyLearnStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-09 21:55:38
 */
 @Repository("yyCompanyLearnStyleDao")
public class YyCompanyLearnStyleDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyCompanyLearnStyle> getYyCompanyLearnStyleList(YyCompanyLearnStyle yyCompanyLearnStyle) {
		List<YyCompanyLearnStyle> list = sqlMapClient.queryForList("YyCompanyLearnStyle.getYyCompanyLearnStyleList", yyCompanyLearnStyle);
		return buildResponseList(list);
	}

	public List<YyCompanyLearnStyle> getYyCompanyLearnStyleBaseList(YyCompanyLearnStyle yyCompanyLearnStyle) {
		return sqlMapClient.queryForList("YyCompanyLearnStyle.getYyCompanyLearnStyle", yyCompanyLearnStyle);
	}

	public int getYyCompanyLearnStyleListCount(YyCompanyLearnStyle yyCompanyLearnStyle) {
		return (Integer)sqlMapClient.queryForObject("YyCompanyLearnStyle.getYyCompanyLearnStyleListCount", yyCompanyLearnStyle);
	}
	
	public YyCompanyLearnStyle getYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) {
		return (YyCompanyLearnStyle)sqlMapClient.queryForObject("YyCompanyLearnStyle.getYyCompanyLearnStyle", yyCompanyLearnStyle);
	}

    public long insertYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) throws Exception {
        return (Long)sqlMapClient.insert("YyCompanyLearnStyle.insertYyCompanyLearnStyle", yyCompanyLearnStyle);
    }

    public int updateYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) throws Exception {
        return sqlMapClient.update("YyCompanyLearnStyle.updateYyCompanyLearnStyle", yyCompanyLearnStyle);
    }
    
    public int removeYyCompanyLearnStyle(YyCompanyLearnStyle yyCompanyLearnStyle) throws Exception {
        return sqlMapClient.delete("YyCompanyLearnStyle.removeYyCompanyLearnStyle", yyCompanyLearnStyle);
    }

}
