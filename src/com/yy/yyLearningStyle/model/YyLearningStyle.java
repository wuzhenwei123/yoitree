package com.yy.yyLearningStyle.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 学习风格表	
 * @author	wzw
 * @time	2017-09-09 20:51:40
 */
public class YyLearningStyle extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 风格名称 **/
		private String name;
		/** 状态 1 正常 0 禁用 **/
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
		 * 风格名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 风格名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 状态 1 正常 0 禁用
		 * @return state
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 状态 1 正常 0 禁用
		 */
		public void setState(Integer state) {
			this.state = state;
		}
}