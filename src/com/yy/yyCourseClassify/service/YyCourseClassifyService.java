package com.yy.yyCourseClassify.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCourseClassify.dao.YyCourseClassifyDAO;
import com.yy.yyCourseClassify.model.YyCourseClassify;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-10 22:02:35
 */
 @Service("yyCourseClassifyService")
public class YyCourseClassifyService {

	@Resource(name = "yyCourseClassifyDao")
    private YyCourseClassifyDAO yyCourseClassifyDAO;
    
    public ResponseList<YyCourseClassify> getYyCourseClassifyList(YyCourseClassify yyCourseClassify) {
        return yyCourseClassifyDAO.getYyCourseClassifyList(yyCourseClassify);
    }
    
    public List<YyCourseClassify> getYyCourseClassifyBaseList(YyCourseClassify yyCourseClassify) {
        return yyCourseClassifyDAO.getYyCourseClassifyBaseList(yyCourseClassify);
    }
    
    public List<YyCourseClassify> getCourseClassifyParents(YyCourseClassify yyCourseClassify) {
    	return yyCourseClassifyDAO.getCourseClassifyParents(yyCourseClassify);
    }
    
    public List<YyCourseClassify> getCourseClassifyByKeyWord(YyCourseClassify yyCourseClassify) {
    	return yyCourseClassifyDAO.getCourseClassifyByKeyWord(yyCourseClassify);
    }
    
    public int getYyCourseClassifyListCount(YyCourseClassify yyCourseClassify) {
        return yyCourseClassifyDAO.getYyCourseClassifyListCount(yyCourseClassify);
    }

    public YyCourseClassify getYyCourseClassify(YyCourseClassify yyCourseClassify) { 
        return yyCourseClassifyDAO.getYyCourseClassify(yyCourseClassify);
    }

    public long insertYyCourseClassify(YyCourseClassify yyCourseClassify) throws Exception {
        return yyCourseClassifyDAO.insertYyCourseClassify(yyCourseClassify);
    }

    public int updateYyCourseClassify(YyCourseClassify yyCourseClassify) throws Exception {
        return yyCourseClassifyDAO.updateYyCourseClassify(yyCourseClassify);
    }
    
    public int removeYyCourseClassify(YyCourseClassify yyCourseClassify) throws Exception {
        return yyCourseClassifyDAO.removeYyCourseClassify(yyCourseClassify);
    }
    
}
