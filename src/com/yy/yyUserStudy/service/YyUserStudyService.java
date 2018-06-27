package com.yy.yyUserStudy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.yyCourse.dao.YyCourseDAO;
import com.yy.yyCourse.model.YyCourse;
import com.yy.yyCourseClassify.dao.YyCourseClassifyDAO;
import com.yy.yyCourseClassify.model.YyCourseClassify;
import com.yy.yyUserStudy.dao.YyUserStudyDAO;
import com.yy.yyUserStudy.model.YyUserStudy;
import com.base.utils.ResponseList;

/**
 * @author	wzw
 * @time	2017-10-11 10:06:01
 */
 @Service("yyUserStudyService")
public class YyUserStudyService {

	@Resource(name = "yyUserStudyDao")
    private YyUserStudyDAO yyUserStudyDAO;
	@Resource(name = "yyCourseDao")
    private YyCourseDAO yyCourseDAO;
	@Resource(name = "yyCourseClassifyDao")
    private YyCourseClassifyDAO yyCourseClassifyDAO;
    
    public ResponseList<YyUserStudy> getYyUserStudyList(YyUserStudy yyUserStudy) {
        return yyUserStudyDAO.getYyUserStudyList(yyUserStudy);
    }
    
    public List<YyUserStudy> getYyUserStudyBaseList(YyUserStudy yyUserStudy) {
        return yyUserStudyDAO.getYyUserStudyBaseList(yyUserStudy);
    }
    
    public List<YyUserStudy> getYyUserStudyByLesson(YyUserStudy yyUserStudy) {
    	return yyUserStudyDAO.getYyUserStudyByLesson(yyUserStudy);
    }
    
    public List<YyUserStudy> getYyUserStudyByLessonPage(YyUserStudy yyUserStudy) {
    	return yyUserStudyDAO.getYyUserStudyByLessonPage(yyUserStudy);
    }
    
    public int getYyUserStudyByLessonCount(YyUserStudy yyUserStudy) {
        return yyUserStudyDAO.getYyUserStudyByLessonCount(yyUserStudy);
    }
    
    public int getYyUserStudyListCount(YyUserStudy yyUserStudy) {
    	return yyUserStudyDAO.getYyUserStudyListCount(yyUserStudy);
    }
    
    public int getUserStudyTime(YyUserStudy yyUserStudy) {
    	return yyUserStudyDAO.getUserStudyTime(yyUserStudy);
    }

    public YyUserStudy getYyUserStudy(YyUserStudy yyUserStudy) { 
        return yyUserStudyDAO.getYyUserStudy(yyUserStudy);
    }

    public long insertYyUserStudy(YyUserStudy yyUserStudy) throws Exception {
        return yyUserStudyDAO.insertYyUserStudy(yyUserStudy);
    }

    public int updateYyUserStudy(YyUserStudy yyUserStudy) throws Exception {
        return yyUserStudyDAO.updateYyUserStudy(yyUserStudy);
    }
    
    public int removeYyUserStudy(YyUserStudy yyUserStudy) throws Exception {
        return yyUserStudyDAO.removeYyUserStudy(yyUserStudy);
    }
    
    /**
     * 查询知识点上级信息
     * @param yyUserStudy
     * @return
     */
    public YyUserStudy getParentMsgByPointId(YyUserStudy yyUserStudy){
    	try{
    		//查询课程信息
    		YyCourse yyCourse = new YyCourse();
			yyCourse.setId(yyUserStudy.getPoint_id());
			yyCourse = yyCourseDAO.getYyCourse(yyCourse);
			if(yyCourse!=null){
				yyUserStudy.setPoint_name(yyCourse.getName());
				yyUserStudy.setCount_time(yyCourse.getWhen_long());
				//查询父级
				YyCourseClassify yyCourseClassifyP = new YyCourseClassify();
				yyCourseClassifyP.setId(yyCourse.getClassify_id());
				yyCourseClassifyP = yyCourseClassifyDAO.getYyCourseClassify(yyCourseClassifyP);
				if(yyCourseClassifyP!=null){
					yyUserStudy.setLesson_name(yyCourseClassifyP.getName());
					yyUserStudy.setLesson_id(yyCourseClassifyP.getId());
					//查询父级
					YyCourseClassify yyCourseClassifyPP = new YyCourseClassify();
					yyCourseClassifyPP.setId(yyCourseClassifyP.getParent_id());
					yyCourseClassifyPP = yyCourseClassifyDAO.getYyCourseClassify(yyCourseClassifyPP);
					if(yyCourseClassifyPP!=null){
						yyUserStudy.setModule_id(yyCourseClassifyPP.getId());
						yyUserStudy.setModule_name(yyCourseClassifyPP.getName());
						//查询父级
						YyCourseClassify yyCourseClassifyPPP = new YyCourseClassify();
						yyCourseClassifyPPP.setId(yyCourseClassifyPP.getParent_id());
						yyCourseClassifyPPP = yyCourseClassifyDAO.getYyCourseClassify(yyCourseClassifyPPP);
						if(yyCourseClassifyPPP!=null){
							yyUserStudy.setTheme_id(yyCourseClassifyPPP.getId());
							yyUserStudy.setTheme_name(yyCourseClassifyPPP.getName());
						}
					}
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return yyUserStudy;
    }
}
