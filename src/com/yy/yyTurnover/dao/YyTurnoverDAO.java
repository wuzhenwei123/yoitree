package com.yy.yyTurnover.dao;

import java.util.List;

import com.yy.yyTurnover.model.YyTurnover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IbatisBaseDaoImpl;
import com.base.utils.ResponseList;
/**
 * @author	wzw
 * @time	2017-09-09 21:16:32
 */
 @Repository("yyTurnoverDao")
public class YyTurnoverDAO extends BaseDao{
	
	@Autowired
	private IbatisBaseDaoImpl sqlMapClient;

	public ResponseList<YyTurnover> getYyTurnoverList(YyTurnover yyTurnover) {
		List<YyTurnover> list = sqlMapClient.queryForList("YyTurnover.getYyTurnoverList", yyTurnover);
		return buildResponseList(list);
	}

	public List<YyTurnover> getYyTurnoverBaseList(YyTurnover yyTurnover) {
		return sqlMapClient.queryForList("YyTurnover.getYyTurnover", yyTurnover);
	}

	public int getYyTurnoverListCount(YyTurnover yyTurnover) {
		return (Integer)sqlMapClient.queryForObject("YyTurnover.getYyTurnoverListCount", yyTurnover);
	}
	
	public YyTurnover getYyTurnover(YyTurnover yyTurnover) {
		return (YyTurnover)sqlMapClient.queryForObject("YyTurnover.getYyTurnover", yyTurnover);
	}

    public int insertYyTurnover(YyTurnover yyTurnover) throws Exception {
        return (Integer)sqlMapClient.insert("YyTurnover.insertYyTurnover", yyTurnover);
    }

    public int updateYyTurnover(YyTurnover yyTurnover) throws Exception {
        return sqlMapClient.update("YyTurnover.updateYyTurnover", yyTurnover);
    }
    
    public int removeYyTurnover(YyTurnover yyTurnover) throws Exception {
        return sqlMapClient.delete("YyTurnover.removeYyTurnover", yyTurnover);
    }

}
