package com.yy.yyCompanyLearnStyle.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 公司学习风格关系表	
 * @author	wzw
 * @time	2017-09-09 21:55:38
 */
public class YyCompanyLearnStyle extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 公司ID **/
		private Long company_id;
		/** 学习风格ID **/
		private Integer learning_style_id;
		
		private String learning_style_name;
		
		public String getLearning_style_name() {
			return learning_style_name;
		}
		public void setLearning_style_name(String learning_style_name) {
			this.learning_style_name = learning_style_name;
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
		 * 公司ID
		 * @return company_id
		 */
		public Long getCompany_id() {
			return company_id;
		}
		/**
		 * 公司ID
		 */
		public void setCompany_id(Long company_id) {
			this.company_id = company_id;
		}
		/**
		 * 学习风格ID
		 * @return learning_style_id
		 */
		public Integer getLearning_style_id() {
			return learning_style_id;
		}
		/**
		 * 学习风格ID
		 */
		public void setLearning_style_id(Integer learning_style_id) {
			this.learning_style_id = learning_style_id;
		}
}