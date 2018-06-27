package com.yy.yyUserStudy.model;

import java.util.Date;
import java.util.List;

import com.base.model.BaseModel;
import com.yy.yyCourse.model.YyCourse;
import com.yy.yyCourseAppendix.model.YyCourseAppendix;
/**
 * 用户学习记录表	
 * @author	wzw
 * @time	2017-10-11 10:06:01
 */
public class YyUserStudy extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 用户ID **/
		private Long user_id;
		/** 主题id **/
		private Long theme_id;
		/** 主题名称 **/
		private String theme_name;
		/** 木块id **/
		private Long module_id;
		/** 模块名称 **/
		private String module_name;
		/** 课程id **/
		private Long lesson_id;
		/** 课程名称 **/
		private String lesson_name;
		/** 知识点id **/
		private Long point_id;
		/** 知识点名称 **/
		private String point_name;
		/** 学习时长（秒） **/
		private Integer long_time;
		/** 记录时间 **/
		private Date create_time;
		/** 学习开始时间 **/
		private Date start_time;
		/** 学习结束时间 **/
		private Date end_time;
		/** 是否完成0未完成 1完成 **/
		private Integer is_over;
		
		private Integer count_time;//视频总时长
		
		private List<YyCourseAppendix> listAppendix;
		
		private List<YyCourse> listCourse;
		
		private String img_url;
		
		private Integer lastStudyTime;
		
		
		public Integer getLastStudyTime() {
			return lastStudyTime;
		}
		public void setLastStudyTime(Integer lastStudyTime) {
			this.lastStudyTime = lastStudyTime;
		}
		public String getImg_url() {
			return img_url;
		}
		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		public List<YyCourse> getListCourse() {
			return listCourse;
		}
		public void setListCourse(List<YyCourse> listCourse) {
			this.listCourse = listCourse;
		}
		public List<YyCourseAppendix> getListAppendix() {
			return listAppendix;
		}
		public void setListAppendix(List<YyCourseAppendix> listAppendix) {
			this.listAppendix = listAppendix;
		}
		public Integer getCount_time() {
			return count_time;
		}
		public void setCount_time(Integer count_time) {
			this.count_time = count_time;
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
		 * 用户ID
		 * @return user_id
		 */
		public Long getUser_id() {
			return user_id;
		}
		/**
		 * 用户ID
		 */
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		/**
		 * 主题id
		 * @return theme_id
		 */
		public Long getTheme_id() {
			return theme_id;
		}
		/**
		 * 主题id
		 */
		public void setTheme_id(Long theme_id) {
			this.theme_id = theme_id;
		}
		/**
		 * 主题名称
		 * @return theme_name
		 */
		public String getTheme_name() {
			return theme_name;
		}
		/**
		 * 主题名称
		 */
		public void setTheme_name(String theme_name) {
			this.theme_name = theme_name;
		}
		/**
		 * 木块id
		 * @return module_id
		 */
		public Long getModule_id() {
			return module_id;
		}
		/**
		 * 木块id
		 */
		public void setModule_id(Long module_id) {
			this.module_id = module_id;
		}
		/**
		 * 模块名称
		 * @return module_name
		 */
		public String getModule_name() {
			return module_name;
		}
		/**
		 * 模块名称
		 */
		public void setModule_name(String module_name) {
			this.module_name = module_name;
		}
		/**
		 * 课程id
		 * @return lesson_id
		 */
		public Long getLesson_id() {
			return lesson_id;
		}
		/**
		 * 课程id
		 */
		public void setLesson_id(Long lesson_id) {
			this.lesson_id = lesson_id;
		}
		/**
		 * 课程名称
		 * @return lesson_name
		 */
		public String getLesson_name() {
			return lesson_name;
		}
		/**
		 * 课程名称
		 */
		public void setLesson_name(String lesson_name) {
			this.lesson_name = lesson_name;
		}
		/**
		 * 知识点id
		 * @return point_id
		 */
		public Long getPoint_id() {
			return point_id;
		}
		/**
		 * 知识点id
		 */
		public void setPoint_id(Long point_id) {
			this.point_id = point_id;
		}
		/**
		 * 知识点名称
		 * @return point_name
		 */
		public String getPoint_name() {
			return point_name;
		}
		/**
		 * 知识点名称
		 */
		public void setPoint_name(String point_name) {
			this.point_name = point_name;
		}
		/**
		 * 学习时长（秒）
		 * @return long_time
		 */
		public Integer getLong_time() {
			return long_time;
		}
		/**
		 * 学习时长（秒）
		 */
		public void setLong_time(Integer long_time) {
			this.long_time = long_time;
		}
		/**
		 * 记录时间
		 * @return create_time
		 */
		public Date getCreate_time() {
			return create_time;
		}
		/**
		 * 记录时间
		 */
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		/**
		 * 学习开始时间
		 * @return start_time
		 */
		public Date getStart_time() {
			return start_time;
		}
		/**
		 * 学习开始时间
		 */
		public void setStart_time(Date start_time) {
			this.start_time = start_time;
		}
		/**
		 * 学习结束时间
		 * @return end_time
		 */
		public Date getEnd_time() {
			return end_time;
		}
		/**
		 * 学习结束时间
		 */
		public void setEnd_time(Date end_time) {
			this.end_time = end_time;
		}
		/**
		 * 是否完成0未完成 1完成
		 * @return is_over
		 */
		public Integer getIs_over() {
			return is_over;
		}
		/**
		 * 是否完成0未完成 1完成
		 */
		public void setIs_over(Integer is_over) {
			this.is_over = is_over;
		}
}