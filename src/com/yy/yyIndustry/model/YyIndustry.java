package com.yy.yyIndustry.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 行业表	
 * @author	wzw
 * @time	2017-09-09 20:04:01
 */
public class YyIndustry extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 行业名称 **/
		private String name;
		/** 状态 1 可用 0 禁用 **/
		private Integer state;
		
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
		 * 行业名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 行业名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 状态 1 可用 0 禁用
		 * @return state
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 状态 1 可用 0 禁用
		 */
		public void setState(Integer state) {
			this.state = state;
		}
}