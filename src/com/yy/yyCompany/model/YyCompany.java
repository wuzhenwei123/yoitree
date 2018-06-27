package com.yy.yyCompany.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 客户表（公司表）	
 * @author	wzw
 * @time	2017-09-09 21:54:36
 */
public class YyCompany extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 企业名称 **/
		private String name;
		/** 编号 **/
		private String code;
		/** 统一社会信用代码 **/
		private String credit_code;
		/** 行业类型ID **/
		private Integer industry_id;
		/** 行业类型名称 **/
		private String industry_name;
		/** 员工数量ID **/
		private Integer employees_id;
		/** 员工数量名称 **/
		private String employees_name;
		/** 营业额范围ID **/
		private Integer turnover_id;
		/** 营业额范围名称 **/
		private String turnover_name;
		/** 服务日期开始时间 **/
		private Date start_time;
		/** 服务日期结束时间 **/
		private Date end_time;
		/** 状态 1活跃、-1欠费、0停用 **/
		private Integer state;
		/** 联系人姓名 **/
		private String contact_name;
		/** 联系人电话 **/
		private String contact_phone;
		/** 负责人姓名 **/
		private String charge_name;
		/** 负责人电话 **/
		private String charge_phone;
		/** 备用 **/
		private String remark1;
		/** 备用 **/
		private String remark2;
		/** 备用 **/
		private String remark3;
		
		private Date create_time;
		
		private Integer companyCount;//公司数量
		
		private String mouthA;//月份
		
		private String year_str;
		
		public Integer getCompanyCount() {
			return companyCount;
		}
		public void setCompanyCount(Integer companyCount) {
			this.companyCount = companyCount;
		}
		public String getMouthA() {
			return mouthA;
		}
		public void setMouthA(String mouthA) {
			this.mouthA = mouthA;
		}
		public String getYear_str() {
			return year_str;
		}
		public void setYear_str(String year_str) {
			this.year_str = year_str;
		}
		public Date getCreate_time() {
			return create_time;
		}
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
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
		 * 企业名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 企业名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 编号
		 * @return code
		 */
		public String getCode() {
			return code;
		}
		/**
		 * 编号
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * 统一社会信用代码
		 * @return credit_code
		 */
		public String getCredit_code() {
			return credit_code;
		}
		/**
		 * 统一社会信用代码
		 */
		public void setCredit_code(String credit_code) {
			this.credit_code = credit_code;
		}
		/**
		 * 行业类型ID
		 * @return industry_id
		 */
		public Integer getIndustry_id() {
			return industry_id;
		}
		/**
		 * 行业类型ID
		 */
		public void setIndustry_id(Integer industry_id) {
			this.industry_id = industry_id;
		}
		/**
		 * 行业类型名称
		 * @return industry_name
		 */
		public String getIndustry_name() {
			return industry_name;
		}
		/**
		 * 行业类型名称
		 */
		public void setIndustry_name(String industry_name) {
			this.industry_name = industry_name;
		}
		/**
		 * 员工数量ID
		 * @return employees_id
		 */
		public Integer getEmployees_id() {
			return employees_id;
		}
		/**
		 * 员工数量ID
		 */
		public void setEmployees_id(Integer employees_id) {
			this.employees_id = employees_id;
		}
		/**
		 * 员工数量名称
		 * @return employees_name
		 */
		public String getEmployees_name() {
			return employees_name;
		}
		/**
		 * 员工数量名称
		 */
		public void setEmployees_name(String employees_name) {
			this.employees_name = employees_name;
		}
		/**
		 * 营业额范围ID
		 * @return turnover_id
		 */
		public Integer getTurnover_id() {
			return turnover_id;
		}
		/**
		 * 营业额范围ID
		 */
		public void setTurnover_id(Integer turnover_id) {
			this.turnover_id = turnover_id;
		}
		/**
		 * 营业额范围名称
		 * @return turnover_name
		 */
		public String getTurnover_name() {
			return turnover_name;
		}
		/**
		 * 营业额范围名称
		 */
		public void setTurnover_name(String turnover_name) {
			this.turnover_name = turnover_name;
		}
		/**
		 * 服务日期开始时间
		 * @return start_time
		 */
		public Date getStart_time() {
			return start_time;
		}
		/**
		 * 服务日期开始时间
		 */
		public void setStart_time(Date start_time) {
			this.start_time = start_time;
		}
		/**
		 * 服务日期结束时间
		 * @return end_time
		 */
		public Date getEnd_time() {
			return end_time;
		}
		/**
		 * 服务日期结束时间
		 */
		public void setEnd_time(Date end_time) {
			this.end_time = end_time;
		}
		/**
		 * 状态 1活跃、-1欠费、0停用
		 * @return state
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 状态 1活跃、-1欠费、0停用
		 */
		public void setState(Integer state) {
			this.state = state;
		}
		/**
		 * 联系人姓名
		 * @return contact_name
		 */
		public String getContact_name() {
			return contact_name;
		}
		/**
		 * 联系人姓名
		 */
		public void setContact_name(String contact_name) {
			this.contact_name = contact_name;
		}
		/**
		 * 联系人电话
		 * @return contact_phone
		 */
		public String getContact_phone() {
			return contact_phone;
		}
		/**
		 * 联系人电话
		 */
		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}
		/**
		 * 负责人姓名
		 * @return charge_name
		 */
		public String getCharge_name() {
			return charge_name;
		}
		/**
		 * 负责人姓名
		 */
		public void setCharge_name(String charge_name) {
			this.charge_name = charge_name;
		}
		/**
		 * 负责人电话
		 * @return charge_phone
		 */
		public String getCharge_phone() {
			return charge_phone;
		}
		/**
		 * 负责人电话
		 */
		public void setCharge_phone(String charge_phone) {
			this.charge_phone = charge_phone;
		}
		/**
		 * 备用
		 * @return remark1
		 */
		public String getRemark1() {
			return remark1;
		}
		/**
		 * 备用
		 */
		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}
		/**
		 * 备用
		 * @return remark2
		 */
		public String getRemark2() {
			return remark2;
		}
		/**
		 * 备用
		 */
		public void setRemark2(String remark2) {
			this.remark2 = remark2;
		}
		/**
		 * 备用
		 * @return remark3
		 */
		public String getRemark3() {
			return remark3;
		}
		/**
		 * 备用
		 */
		public void setRemark3(String remark3) {
			this.remark3 = remark3;
		}
}