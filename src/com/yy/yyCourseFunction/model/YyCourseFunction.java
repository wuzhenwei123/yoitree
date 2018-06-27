package com.yy.yyCourseFunction.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 课程职能关系表	
 * @author	wzw
 * @time	2017-09-22 13:22:53
 */
public class YyCourseFunction extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 课程id **/
		private Long course_classify_id;
		public Long getCourse_classify_id() {
			return course_classify_id;
		}
		public void setCourse_classify_id(Long course_classify_id) {
			this.course_classify_id = course_classify_id;
		}
		/** 职能id **/
		private Integer function_id;
		
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
		 * 职能id
		 * @return function_id
		 */
		public Integer getFunction_id() {
			return function_id;
		}
		/**
		 * 职能id
		 */
		public void setFunction_id(Integer function_id) {
			this.function_id = function_id;
		}
}