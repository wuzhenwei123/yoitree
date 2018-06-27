package com.yy.yyLearningStyle.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyLearningStyle.dao.YyLearningStyleDAO;
import com.yy.yyLearningStyle.model.YyLearningStyle;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-09 20:51:40
 */
 @Service("yyLearningStyleService")
public class YyLearningStyleService {

	@Resource(name = "yyLearningStyleDao")
    private YyLearningStyleDAO yyLearningStyleDAO;
    
    public ResponseList<YyLearningStyle> getYyLearningStyleList(YyLearningStyle yyLearningStyle) {
        return yyLearningStyleDAO.getYyLearningStyleList(yyLearningStyle);
    }
    
    public List<YyLearningStyle> getYyLearningStyleBaseList(YyLearningStyle yyLearningStyle) {
        return yyLearningStyleDAO.getYyLearningStyleBaseList(yyLearningStyle);
    }
    
    public int getYyLearningStyleListCount(YyLearningStyle yyLearningStyle) {
        return yyLearningStyleDAO.getYyLearningStyleListCount(yyLearningStyle);
    }

    public YyLearningStyle getYyLearningStyle(YyLearningStyle yyLearningStyle) { 
        return yyLearningStyleDAO.getYyLearningStyle(yyLearningStyle);
    }

    public int insertYyLearningStyle(YyLearningStyle yyLearningStyle) throws Exception {
        return yyLearningStyleDAO.insertYyLearningStyle(yyLearningStyle);
    }

    public int updateYyLearningStyle(YyLearningStyle yyLearningStyle) throws Exception {
        return yyLearningStyleDAO.updateYyLearningStyle(yyLearningStyle);
    }
    
    public int removeYyLearningStyle(YyLearningStyle yyLearningStyle) throws Exception {
        return yyLearningStyleDAO.removeYyLearningStyle(yyLearningStyle);
    }
    
}
