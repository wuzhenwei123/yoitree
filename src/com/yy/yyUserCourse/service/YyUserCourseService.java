package com.yy.yyUserCourse.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyUserCourse.dao.YyUserCourseDAO;
import com.yy.yyUserCourse.model.YyUserCourse;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-10-04 11:30:06
 */
 @Service("yyUserCourseService")
public class YyUserCourseService {

	@Resource(name = "yyUserCourseDao")
    private YyUserCourseDAO yyUserCourseDAO;
    
    public ResponseList<YyUserCourse> getYyUserCourseList(YyUserCourse yyUserCourse) {
        return yyUserCourseDAO.getYyUserCourseList(yyUserCourse);
    }
    
    public List<YyUserCourse> getYyUserCourseBaseList(YyUserCourse yyUserCourse) {
        return yyUserCourseDAO.getYyUserCourseBaseList(yyUserCourse);
    }
    
    public List<YyUserCourse> getDataByUserAndClassify(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getDataByUserAndClassify(yyUserCourse);
    }
    
    public List<YyUserCourse> getYyUserCourseState(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getYyUserCourseState(yyUserCourse);
    }
    
    public List<YyUserCourse> getYyUserCourseByUser(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getYyUserCourseByUser(yyUserCourse);
    }
    
    public List<YyUserCourse> getStudyUser(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getStudyUser(yyUserCourse);
    }
    
    public List<YyUserCourse> getYyUserCourseParent(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getYyUserCourseParent(yyUserCourse);
    }
    
    public List<YyUserCourse> getUserStudyList(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getUserStudyList(yyUserCourse);
    }
    
    public List<YyUserCourse> getUserStudyListAll(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getUserStudyListAll(yyUserCourse);
    }
    
    public List<YyUserCourse> getUserStudyStateList(YyUserCourse yyUserCourse) {
    	return yyUserCourseDAO.getUserStudyStateList(yyUserCourse);
    }
    
    public int getYyUserCourseListCount(YyUserCourse yyUserCourse) {
        return yyUserCourseDAO.getYyUserCourseListCount(yyUserCourse);
    }

    public YyUserCourse getYyUserCourse(YyUserCourse yyUserCourse) { 
        return yyUserCourseDAO.getYyUserCourse(yyUserCourse);
    }

    public long insertYyUserCourse(YyUserCourse yyUserCourse) throws Exception {
        return yyUserCourseDAO.insertYyUserCourse(yyUserCourse);
    }

    public int updateYyUserCourse(YyUserCourse yyUserCourse) throws Exception {
        return yyUserCourseDAO.updateYyUserCourse(yyUserCourse);
    }
    
    public int updateYyUserCourseByUserAndPointId(YyUserCourse yyUserCourse) throws Exception {
    	return yyUserCourseDAO.updateYyUserCourseByUserAndPointId(yyUserCourse);
    }
    
    public int removeYyUserCourse(YyUserCourse yyUserCourse) throws Exception {
        return yyUserCourseDAO.removeYyUserCourse(yyUserCourse);
    }
    
    public int removeYyUserCourseByOther(YyUserCourse yyUserCourse) throws Exception {
    	return yyUserCourseDAO.removeYyUserCourseByOther(yyUserCourse);
    }
    
    /**
	 * date2比date1多的天数
	 * @param date1 
	 * @param date2
	 * @return 
	 */
	public int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			System.out.println("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}
    
}
