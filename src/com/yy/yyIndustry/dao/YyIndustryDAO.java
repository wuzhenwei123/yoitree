package com.yy.yyIndustry.dao;

import java.util.List;

import com.yy.yyIndustry.model.YyIndustry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-09 20:04:01
 */
 @Repository("yyIndustryDao")
public class YyIndustryDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyIndustry> getYyIndustryList(YyIndustry yyIndustry) {
		List<YyIndustry> list = sqlMapClient.queryForList("YyIndustry.getYyIndustryList", yyIndustry);
		return buildResponseList(list);
	}

	public List<YyIndustry> getYyIndustryBaseList(YyIndustry yyIndustry) {
		return sqlMapClient.queryForList("YyIndustry.getYyIndustry", yyIndustry);
	}

	public int getYyIndustryListCount(YyIndustry yyIndustry) {
		return (Integer)sqlMapClient.queryForObject("YyIndustry.getYyIndustryListCount", yyIndustry);
	}
	
	public YyIndustry getYyIndustry(YyIndustry yyIndustry) {
		return (YyIndustry)sqlMapClient.queryForObject("YyIndustry.getYyIndustry", yyIndustry);
	}

    public int insertYyIndustry(YyIndustry yyIndustry) throws Exception {
        return (Integer)sqlMapClient.insert("YyIndustry.insertYyIndustry", yyIndustry);
    }

    public int updateYyIndustry(YyIndustry yyIndustry) throws Exception {
        return sqlMapClient.update("YyIndustry.updateYyIndustry", yyIndustry);
    }
    
    public int removeYyIndustry(YyIndustry yyIndustry) throws Exception {
        return sqlMapClient.delete("YyIndustry.removeYyIndustry", yyIndustry);
    }

}
