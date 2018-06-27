package com.yy.yyCourseAppendix.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 课程附件表	
 * @author	wzw
 * @time	2017-09-14 18:26:43
 */
public class YyCourseAppendix extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 课程id **/
		private Long course_id;
		/** 附件地址 **/
		private String url;
		/** 附件名称 **/
		private String name;
		
		private Integer state;
		
		private String course_name;
		
		private Long classify_id;
		
		public Long getClassify_id() {
			return classify_id;
		}
		public void setClassify_id(Long classify_id) {
			this.classify_id = classify_id;
		}
		public String getCourse_name() {
			return course_name;
		}
		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}
		public Integer getState() {
			return state;
		}
		public void setState(Integer state) {
			this.state = state;
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
		/**
		 * 附件地址
		 * @return url
		 */
		public String getUrl() {
			return url;
		}
		/**
		 * 附件地址
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/**
		 * 附件名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 附件名称
		 */
		public void setName(String name) {
			this.name = name;
		}
}