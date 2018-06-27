package com.yy.yyTurnover.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyTurnover.dao.YyTurnoverDAO;
import com.yy.yyTurnover.model.YyTurnover;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-09 21:16:32
 */
 @Service("yyTurnoverService")
public class YyTurnoverService {

	@Resource(name = "yyTurnoverDao")
    private YyTurnoverDAO yyTurnoverDAO;
    
    public ResponseList<YyTurnover> getYyTurnoverList(YyTurnover yyTurnover) {
        return yyTurnoverDAO.getYyTurnoverList(yyTurnover);
    }
    
    public List<YyTurnover> getYyTurnoverBaseList(YyTurnover yyTurnover) {
        return yyTurnoverDAO.getYyTurnoverBaseList(yyTurnover);
    }
    
    public int getYyTurnoverListCount(YyTurnover yyTurnover) {
        return yyTurnoverDAO.getYyTurnoverListCount(yyTurnover);
    }

    public YyTurnover getYyTurnover(YyTurnover yyTurnover) { 
        return yyTurnoverDAO.getYyTurnover(yyTurnover);
    }

    public int insertYyTurnover(YyTurnover yyTurnover) throws Exception {
        return yyTurnoverDAO.insertYyTurnover(yyTurnover);
    }

    public int updateYyTurnover(YyTurnover yyTurnover) throws Exception {
        return yyTurnoverDAO.updateYyTurnover(yyTurnover);
    }
    
    public int removeYyTurnover(YyTurnover yyTurnover) throws Exception {
        return yyTurnoverDAO.removeYyTurnover(yyTurnover);
    }
    
}
