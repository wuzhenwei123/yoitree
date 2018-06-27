package com.yy.yyCourse.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCourse.dao.YyCourseDAO;
import com.yy.yyCourse.model.YyCourse;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-09-11 16:02:52
 */
 @Service("yyCourseService")
public class YyCourseService {

	@Resource(name = "yyCourseDao")
    private YyCourseDAO yyCourseDAO;
    
    public ResponseList<YyCourse> getYyCourseList(YyCourse yyCourse) {
        return yyCourseDAO.getYyCourseList(yyCourse);
    }
    
    public List<YyCourse> getYyCourseBaseList(YyCourse yyCourse) {
        return yyCourseDAO.getYyCourseBaseList(yyCourse);
    }
    
    public List<YyCourse> getYyCourseTotal(YyCourse yyCourse) {
    	return yyCourseDAO.getYyCourseTotal(yyCourse);
    }
    
    /**
     * 根据职能ID获取课程
     * @param yyCourse
     * @return
     */
    public List<YyCourse> getYyCourseListByFunction(YyCourse yyCourse) {
    	return yyCourseDAO.getYyCourseListByFunction(yyCourse);
    }
    
    /**
     * 根据分类ID获取课程
     * @param yyCourse
     * @return
     */
    public List<YyCourse> getYyCourseListByClassify(YyCourse yyCourse) {
    	return yyCourseDAO.getYyCourseListByClassify(yyCourse);
    }
    
    public List<YyCourse> getYyCourseListByFunctionS(YyCourse yyCourse) {
    	return yyCourseDAO.getYyCourseListByFunctionS(yyCourse);
    }
    
    public int getYyCourseListCount(YyCourse yyCourse) {
        return yyCourseDAO.getYyCourseListCount(yyCourse);
    }

    public YyCourse getYyCourse(YyCourse yyCourse) { 
        return yyCourseDAO.getYyCourse(yyCourse);
    }

    public long insertYyCourse(YyCourse yyCourse) throws Exception {
        return yyCourseDAO.insertYyCourse(yyCourse);
    }

    public int updateYyCourse(YyCourse yyCourse) throws Exception {
        return yyCourseDAO.updateYyCourse(yyCourse);
    }
    
    public int removeYyCourse(YyCourse yyCourse) throws Exception {
        return yyCourseDAO.removeYyCourse(yyCourse);
    }
    
    public static String secToTime(int time) {  
        String timeStr = null;  
//        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
//            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
//            } else {  
//                hour = minute / 60;  
//                if (hour > 99)  
//                    return "99:59:59";  
//                minute = minute % 60;  
//                second = time - hour * 3600 - minute * 60;  
//                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
//            }  
        }  
        return timeStr;  
    }  
  
    public static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    } 
}
