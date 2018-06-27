package com.sys.manageActScs.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 请求统计	
 * @author	keeny
 * @time	2016-05-04 10:19:15
 */
public class ManageActScs extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 请求路径 **/
		private String requestPath;
		/** 请求次数 **/
		private Integer requestCount;
		/** 请求日期(yyyyMMdd) **/
		private String requestDateTime;
		private String createTime1;
		private String createTime2;
		private String aliasName;//别名
		
		public String getAliasName() {
			return aliasName;
		}
		public void setAliasName(String aliasName) {
			this.aliasName = aliasName;
		}
		public String getCreateTime1() {
			return createTime1;
		}
		public void setCreateTime1(String createTime1) {
			this.createTime1 = createTime1;
		}
		public String getCreateTime2() {
			return createTime2;
		}
		public void setCreateTime2(String createTime2) {
			this.createTime2 = createTime2;
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
		 * 请求路径
		 * @return requestPath
		 */
		public String getRequestPath() {
			return requestPath;
		}
		/**
		 * 请求路径
		 */
		public void setRequestPath(String requestPath) {
			this.requestPath = requestPath;
		}
		/**
		 * 请求次数
		 * @return requestCount
		 */
		public Integer getRequestCount() {
			return requestCount;
		}
		/**
		 * 请求次数
		 */
		public void setRequestCount(Integer requestCount) {
			this.requestCount = requestCount;
		}
		/**
		 * 请求日期(yyyyMMdd)
		 * @return requestDateTime
		 */
		public String getRequestDateTime() {
			return requestDateTime;
		}
		/**
		 * 请求日期(yyyyMMdd)
		 */
		public void setRequestDateTime(String requestDateTime) {
			this.requestDateTime = requestDateTime;
		}
}