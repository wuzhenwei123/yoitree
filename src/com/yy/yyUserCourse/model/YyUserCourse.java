package com.yy.yyUserCourse.model;

import java.util.Date;
import java.util.List;

import com.base.model.BaseModel;
/**
 * 用户课程表	
 * @author	wzw
 * @time	2017-10-04 11:30:06
 */
public class YyUserCourse extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 用户id **/
		private Long user_id;
		/** 课程id **/
		private Long course_id;
		/** 课程分类id **/
		private Long course_classify_id;
		/** 学习状态 0 未学完 1 已学完 **/
		private Integer study_state;
		/** 学习状态 0 必修 1 选修 **/
		private Integer style;
		
		private String lessonName;
		private String moduleName;
		private String themeName;
		
		private Long lessonId;
		private Long moduleId;
		private Long themeId;
		
		private Integer status;//学习状态 0未开始 1进行中
		private Integer studyCount;//总学习个数
		private Integer studyProgress;//已学习个数
		private Integer overDays;//距离结束还有N天
		private Integer count;
		
		private Date over_time;
		
		private String img_url;
		private String course_name;
		
		private Long user_parent_id;
		
		private String user_name;
		
		private List<YyUserCourse> listUC;
		
		private Date start_time;
		
		private Integer total;
		
		public Integer getTotal() {
			return total;
		}
		public void setTotal(Integer total) {
			this.total = total;
		}
		public Date getStart_time() {
			return start_time;
		}
		public void setStart_time(Date start_time) {
			this.start_time = start_time;
		}
		public List<YyUserCourse> getListUC() {
			return listUC;
		}
		public void setListUC(List<YyUserCourse> listUC) {
			this.listUC = listUC;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public Long getUser_parent_id() {
			return user_parent_id;
		}
		public void setUser_parent_id(Long user_parent_id) {
			this.user_parent_id = user_parent_id;
		}
		public String getCourse_name() {
			return course_name;
		}
		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}
		public String getImg_url() {
			return img_url;
		}
		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		public Date getOver_time() {
			return over_time;
		}
		public void setOver_time(Date over_time) {
			this.over_time = over_time;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Integer getStudyCount() {
			return studyCount;
		}
		public void setStudyCount(Integer studyCount) {
			this.studyCount = studyCount;
		}
		public Integer getStudyProgress() {
			return studyProgress;
		}
		public void setStudyProgress(Integer studyProgress) {
			this.studyProgress = studyProgress;
		}
		public Integer getOverDays() {
			return overDays;
		}
		public void setOverDays(Integer overDays) {
			this.overDays = overDays;
		}
		public String getLessonName() {
			return lessonName;
		}
		public void setLessonName(String lessonName) {
			this.lessonName = lessonName;
		}
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		public String getThemeName() {
			return themeName;
		}
		public void setThemeName(String themeName) {
			this.themeName = themeName;
		}
		public Long getLessonId() {
			return lessonId;
		}
		public void setLessonId(Long lessonId) {
			this.lessonId = lessonId;
		}
		public Long getModuleId() {
			return moduleId;
		}
		public void setModuleId(Long moduleId) {
			this.moduleId = moduleId;
		}
		public Long getThemeId() {
			return themeId;
		}
		public void setThemeId(Long themeId) {
			this.themeId = themeId;
		}
		public Integer getStyle() {
			return style;
		}
		public void setStyle(Integer style) {
			this.style = style;
		}
		public Integer getStudy_state() {
			return study_state;
		}
		public void setStudy_state(Integer study_state) {
			this.study_state = study_state;
		}
		public Long getCourse_classify_id() {
			return course_classify_id;
		}
		public void setCourse_classify_id(Long course_classify_id) {
			this.course_classify_id = course_classify_id;
		}
		/**
		 * 
		 * @return id
		 */
		public Long getId() {
			return id;
		}
		/**
		 * 
		 */
		public void setId(Long id) {
			this.id = id;
		}
		/**
		 * 用户id
		 * @return user_id
		 */
		public Long getUser_id() {
			return user_id;
		}
		/**
		 * 用户id
		 */
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		/**
		 * 课程id
		 * @return course_id
		 */
		public Long getCourse_id() {
			return course_id;
		}
		/**
		 * 课程id
		 */
		public void setCourse_id(Long course_id) {
			this.course_id = course_id;
		}
}