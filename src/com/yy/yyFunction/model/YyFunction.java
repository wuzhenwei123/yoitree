package com.yy.yyFunction.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 职能表	
 * @author	wzw
 * @time	2017-09-22 11:31:15
 */
public class YyFunction extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 职能名称 **/
		private String name;
		/** 父id **/
		private Integer parent_id;
		
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
		 * 职能名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 职能名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 父id
		 * @return parent_id
		 */
		public Integer getParent_id() {
			return parent_id;
		}
		/**
		 * 父id
		 */
		public void setParent_id(Integer parent_id) {
			this.parent_id = parent_id;
		}
}