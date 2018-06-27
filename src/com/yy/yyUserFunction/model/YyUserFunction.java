package com.yy.yyUserFunction.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 用户职能关系表	
 * @author	wzw
 * @time	2017-11-13 10:23:22
 */
public class YyUserFunction extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 用户id **/
		private Long user_id;
		/** 职能id **/
		private Integer function_id;
		
		private String function_name;
		
		public String getFunction_name() {
			return function_name;
		}
		public void setFunction_name(String function_name) {
			this.function_name = function_name;
		}
		/**
		 * 
		 * @return id
		 */
		public Integer getId() {
			return id;
		}
		/**
		 * 
		 */
		public void setId(Integer id) {
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