package com.yy.yyWeb.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.utils.RequestHandler;
import com.yy.yyCourseClassify.dao.YyCourseClassifyDAO;
import com.yy.yyCourseClassify.model.YyCourseClassify;
import com.yy.yyUser.dao.YyUserDAO;
import com.yy.yyUser.model.YyUser;
import com.yy.yyUserCourse.dao.YyUserCourseDAO;
import com.yy.yyUserCourse.model.YyUserCourse;
import com.yy.yyUserStudy.dao.YyUserStudyDAO;
import com.yy.yyUserStudy.model.YyUserStudy;

@Service("webService")
public class WebService {
	
	@Resource(name = "yyCourseClassifyDao")
    private YyCourseClassifyDAO yyCourseClassifyDAO;
	@Resource(name = "yyUserCourseDao")
    private YyUserCourseDAO yyUserCourseDAO;
	@Resource(name = "yyUserStudyDao")
    private YyUserStudyDAO yyUserStudyDAO;
	@Resource(name = "yyUserDao")
    private YyUserDAO yyUserDAO;
	
	/**
	 * 获取同级同事学习信息
	 * @param yyUser
	 * @return
	 */
	public List<YyUser> getSameLevelMsg(YyUser yyUser){
		List<YyUser> listChild = null;
		try{
			YyUser yyUserChild = new YyUser();
			yyUserChild.setParent_id(yyUser.getParent_id());
			yyUserChild.setCompany_id(yyUser.getCompany_id());
			listChild = yyUserDAO.getYyUserBaseList(yyUserChild);
			String user_names = null;
			String study_times = null;
			String course_counts = null;
			if(listChild!=null&&listChild.size()>0){
				for(YyUser child:listChild){
					if(user_names==null){
						user_names = "'" + child.getName() + "'";
					}else{
						user_names = user_names + ",'" + child.getName() + "'";
					}
					//查询学习时间
					YyUserStudy yyUserStudy = new YyUserStudy();
					yyUserStudy.setUser_id(child.getId());
					int study_time = yyUserStudyDAO.getUserStudyTime(yyUserStudy);
					child.setStudy_time(RequestHandler.secToTime(study_time));
					if(study_times==null){
						study_times = RequestHandler.secToTime(study_time) + "";
					}else{
						study_times = study_times + "," +RequestHandler.secToTime(study_time);
					}
					//计算完成课程数量
					YyUserCourse yyUserCourse = new YyUserCourse();
					yyUserCourse.setUser_id(child.getId());
					yyUserCourse.setStyle(0);
					List<YyUserCourse> list = yyUserCourseDAO.getUserStudyList(yyUserCourse);
					Set<Long> set = new HashSet<Long>();
					int lessonCount = 0;
					int lessonCounting = 0;
					if(list!=null&&list.size()>0){
						for(YyUserCourse uc:list){
							set.add(uc.getCourse_classify_id());
						}
						Iterator<Long> it = set.iterator();
						while(it.hasNext()){
							Long lessonId = it.next();
							boolean b = true;
							for(YyUserCourse uc:list){
								if(lessonId.intValue()==uc.getCourse_classify_id().intValue()&&uc.getStudy_state()==0){
									b = false;
									break;
								}
							}
							if(b){
								lessonCount = lessonCount + 1;
							}
						}
						lessonCounting = list.size() - lessonCount;
						child.setLesson_count(list.size());
						if(course_counts==null){
							course_counts = "" + list.size();
						}else{
							course_counts = course_counts + "," + list.size();
						}
					}else{
						if(course_counts==null){
							course_counts = "" + 0;
						}else{
							course_counts = course_counts + "," + 0;
						}
						child.setLesson_count(0);
					}
					//计算完成课程数量结束
					child.setStudied_count(lessonCount);
					child.setStuding_count(lessonCounting);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listChild;
	}
	
	/**
	 * 返回随机数
	 * 
	 * @param bit
	 *            位数
	 * @return
	 */
	public static String getRandomCode(int bit) {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bit; i++) {
			int ranNum = ran.nextInt(10);
			sb.append(ranNum);
		}
		return sb.toString();
	}
	
	/**
	 * 获取轮播图
	 * @return
	 */
	public List<YyCourseClassify> getBannerList(){
		List<YyCourseClassify> listBanner = null;
		try{
			YyCourseClassify yyCourseClassify = new YyCourseClassify();
			yyCourseClassify.setRowStart(0);
			yyCourseClassify.setRowCount(4);
			yyCourseClassify.setLevel(3);
			yyCourseClassify.setFenye(1L);
			yyCourseClassify.setSortColumn("a.id desc");
			listBanner = yyCourseClassifyDAO.getCourseClassifyParents(yyCourseClassify);
		}catch(Exception e){
			e.printStackTrace();
		}
		return listBanner;
	}
	
	/**
	 * 获取我未学习课程
	 * @param yyUser
	 * @return
	 */
	public List<YyUserCourse> getMyStudyCourseNo(YyUser yyUser){
		List<YyUserCourse> list = new ArrayList<YyUserCourse>();
		try{
			//正在学习课程
			YyUserStudy yyUserStudy = new YyUserStudy();
			yyUserStudy.setUser_id(yyUser.getId());
			List<YyUserStudy> listUS = yyUserStudyDAO.getYyUserStudyByLesson(yyUserStudy);
			//所有课程
			YyUserCourse yyUserCourse = new YyUserCourse();
			yyUserCourse.setUser_id(yyUser.getId());
			List<YyUserCourse> listAll = yyUserCourseDAO.getYyUserCourseByUser(yyUserCourse);
			if(listAll!=null&&listAll.size()>0){
				for(YyUserCourse uc:listAll){
					boolean b = true;
					for(YyUserStudy us:listUS){
						if(uc.getLessonId().intValue()==us.getLesson_id().intValue()){
							b = false;
						}
					}
					if(b){
						list.add(uc);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取我正在学习课程
	 * @param yyUser
	 * @return
	 */
	public List<YyUserStudy> getMyStudyCourse(YyUser yyUser){
		List<YyUserStudy> listUS = null;
		try{
			//正在学习课程
			YyUserStudy yyUserStudy = new YyUserStudy();
			yyUserStudy.setUser_id(yyUser.getId());
			listUS = yyUserStudyDAO.getYyUserStudyByLesson(yyUserStudy);
		}catch(Exception e){
			e.printStackTrace();
		}
		return listUS;
	}

}
