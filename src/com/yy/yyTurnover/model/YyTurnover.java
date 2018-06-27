package com.yy.yyTurnover.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 营业额范围表	
 * @author	wzw
 * @time	2017-09-09 21:16:32
 */
public class YyTurnover extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 营业额范围 **/
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
		 * 营业额范围
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 营业额范围
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