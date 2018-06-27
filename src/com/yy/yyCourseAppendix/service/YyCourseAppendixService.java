package com.yy.yyCourseAppendix.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCourseAppendix.dao.YyCourseAppendixDAO;
import com.yy.yyCourseAppendix.model.YyCourseAppendix;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-14 18:26:43
 */
 @Service("yyCourseAppendixService")
public class YyCourseAppendixService {

	@Resource(name = "yyCourseAppendixDao")
    private YyCourseAppendixDAO yyCourseAppendixDAO;
    
    public ResponseList<YyCourseAppendix> getYyCourseAppendixList(YyCourseAppendix yyCourseAppendix) {
        return yyCourseAppendixDAO.getYyCourseAppendixList(yyCourseAppendix);
    }
    
    public List<YyCourseAppendix> getYyCourseAppendixBaseList(YyCourseAppendix yyCourseAppendix) {
        return yyCourseAppendixDAO.getYyCourseAppendixBaseList(yyCourseAppendix);
    }
    
    public int getYyCourseAppendixListCount(YyCourseAppendix yyCourseAppendix) {
        return yyCourseAppendixDAO.getYyCourseAppendixListCount(yyCourseAppendix);
    }

    public YyCourseAppendix getYyCourseAppendix(YyCourseAppendix yyCourseAppendix) { 
        return yyCourseAppendixDAO.getYyCourseAppendix(yyCourseAppendix);
    }

    public long insertYyCourseAppendix(YyCourseAppendix yyCourseAppendix) throws Exception {
        return yyCourseAppendixDAO.insertYyCourseAppendix(yyCourseAppendix);
    }
    
    public long insertYyCourseAppendix1(YyCourseAppendix yyCourseAppendix) throws Exception {
    	return yyCourseAppendixDAO.insertYyCourseAppendix1(yyCourseAppendix);
    }

    public int updateYyCourseAppendix(YyCourseAppendix yyCourseAppendix) throws Exception {
        return yyCourseAppendixDAO.updateYyCourseAppendix(yyCourseAppendix);
    }
    
    public int removeYyCourseAppendix(YyCourseAppendix yyCourseAppendix) throws Exception {
        return yyCourseAppendixDAO.removeYyCourseAppendix(yyCourseAppendix);
    }
    
}
