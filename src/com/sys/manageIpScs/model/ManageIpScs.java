package com.sys.manageIpScs.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * IP地址访问次数统计	
 * @author	keeny
 * @time	2016-05-04 11:40:08
 */
public class ManageIpScs extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Integer id;
		/** 请求地址 **/
		private String requestIP;
		/** 请求次数 **/
		private Integer requestCount;
		/** 请求日期(yyyyMMdd) **/
		private String requestDateTime;
		
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
		 * 请求地址
		 * @return requestIP
		 */
		public String getRequestIP() {
			return requestIP;
		}
		/**
		 * 请求地址
		 */
		public void setRequestIP(String requestIP) {
			this.requestIP = requestIP;
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