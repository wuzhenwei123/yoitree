package com.yy.yyUser.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 公司用户表	
 * @author	wzw
 * @time	2017-09-10 14:03:43
 */
public class YyUser extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 用户姓名 **/
		private String name;
		/** 昵称 **/
		private String nick_name;
		/** 员工编号 **/
		private String user_number;
		/** 所属公司id **/
		private Long company_id;
		/** 是否是管理员 1 是 0 否 **/
		private Integer is_manager;
		/** 上级ID 没有上级为-1 **/
		private Long parent_id;
		/** 所有父Id **/
		private String parent_ids;
		/** 性别 1 男 2女 **/
		private Integer sex;
		/** 登录名 **/
		private String login_name;
		/** 登录密码 **/
		private String password;
		/** 状态 1 正常 0 暂停 **/
		private Integer state;
		/** 头像 **/
		private String head_img;
		/** 手机 **/
		private String phone;
		/** 邮箱 **/
		private String mail;
		/** 下级人数 **/
		private Integer lower_level_number;
		/** 上级姓名 **/
		private String parent_name;
		/** 加入公司时间 **/
		private Date create_time;
		/** 职位 **/
		private String job;
		/** 职级 **/
		private String job_level;
		/** 所在部门 **/
		private String department;
		/** 职能 **/
		private Integer job_info;
		/** 历史职位 **/
		private String history_job;
		/** 备用 **/
		private String remark1;
		/** 备用 **/
		private String remark2;
		/** 备用 **/
		private String remark3;
		/** 公司名称 **/
		private String company_name;
		/** openId **/
		private String openId;
		
		
		
		private Long user_id;
		
		private String year_str;
		
		private Integer userCount;
		
		private String mouthA;
		
		private String token;
		private String levelName;
		
		private Integer study_time;
		private Integer lesson_count;
		private Integer studing_count;
		private Integer studied_count;
		
		private Integer is_select;
		private Integer is_super_manager;
		
		private Date birthday;
		private String education;
		
		private Integer parent_flag;
		
		public Integer getParent_flag() {
			return parent_flag;
		}
		public void setParent_flag(Integer parent_flag) {
			this.parent_flag = parent_flag;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public Integer getIs_super_manager() {
			return is_super_manager;
		}
		public void setIs_super_manager(Integer is_super_manager) {
			this.is_super_manager = is_super_manager;
		}
		public Integer getIs_select() {
			return is_select;
		}
		public void setIs_select(Integer is_select) {
			this.is_select = is_select;
		}
		public Integer getStudied_count() {
			return studied_count;
		}
		public void setStudied_count(Integer studied_count) {
			this.studied_count = studied_count;
		}
		public Integer getStuding_count() {
			return studing_count;
		}
		public void setStuding_count(Integer studing_count) {
			this.studing_count = studing_count;
		}
		public Integer getLesson_count() {
			return lesson_count;
		}
		public void setLesson_count(Integer lesson_count) {
			this.lesson_count = lesson_count;
		}
		public Integer getStudy_time() {
			return study_time;
		}
		public void setStudy_time(Integer study_time) {
			this.study_time = study_time;
		}
		public String getLevelName() {
			return levelName;
		}
		public void setLevelName(String levelName) {
			this.levelName = levelName;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getMouthA() {
			return mouthA;
		}
		public void setMouthA(String mouthA) {
			this.mouthA = mouthA;
		}
		public Integer getUserCount() {
			return userCount;
		}
		public void setUserCount(Integer userCount) {
			this.userCount = userCount;
		}
		public String getYear_str() {
			return year_str;
		}
		public void setYear_str(String year_str) {
			this.year_str = year_str;
		}
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public String getOpenId() {
			return openId;
		}
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public String getCompany_name() {
			return company_name;
		}
		public void setCompany_name(String company_name) {
			this.company_name = company_name;
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
		 * 用户姓名
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 用户姓名
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 昵称
		 * @return nick_name
		 */
		public String getNick_name() {
			return nick_name;
		}
		/**
		 * 昵称
		 */
		public void setNick_name(String nick_name) {
			this.nick_name = nick_name;
		}
		/**
		 * 员工编号
		 * @return user_number
		 */
		public String getUser_number() {
			return user_number;
		}
		/**
		 * 员工编号
		 */
		public void setUser_number(String user_number) {
			this.user_number = user_number;
		}
		/**
		 * 所属公司id
		 * @return company_id
		 */
		public Long getCompany_id() {
			return company_id;
		}
		/**
		 * 所属公司id
		 */
		public void setCompany_id(Long company_id) {
			this.company_id = company_id;
		}
		/**
		 * 是否是管理员 1 是 0 否
		 * @return is_manager
		 */
		public Integer getIs_manager() {
			return is_manager;
		}
		/**
		 * 是否是管理员 1 是 0 否
		 */
		public void setIs_manager(Integer is_manager) {
			this.is_manager = is_manager;
		}
		/**
		 * 上级ID 没有上级为-1
		 * @return parent_id
		 */
		public Long getParent_id() {
			return parent_id;
		}
		/**
		 * 上级ID 没有上级为-1
		 */
		public void setParent_id(Long parent_id) {
			this.parent_id = parent_id;
		}
		/**
		 * 所有父Id
		 * @return parent_ids
		 */
		public String getParent_ids() {
			return parent_ids;
		}
		/**
		 * 所有父Id
		 */
		public void setParent_ids(String parent_ids) {
			this.parent_ids = parent_ids;
		}
		/**
		 * 性别 1 男 2女
		 * @return sex
		 */
		public Integer getSex() {
			return sex;
		}
		/**
		 * 性别 1 男 2女
		 */
		public void setSex(Integer sex) {
			this.sex = sex;
		}
		/**
		 * 登录名
		 * @return login_name
		 */
		public String getLogin_name() {
			return login_name;
		}
		/**
		 * 登录名
		 */
		public void setLogin_name(String login_name) {
			this.login_name = login_name;
		}
		/**
		 * 登录密码
		 * @return password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * 登录密码
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * 状态 1 正常 0 暂停
		 * @return state
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 状态 1 正常 0 暂停
		 */
		public void setState(Integer state) {
			this.state = state;
		}
		/**
		 * 头像
		 * @return head_img
		 */
		public String getHead_img() {
			return head_img;
		}
		/**
		 * 头像
		 */
		public void setHead_img(String head_img) {
			this.head_img = head_img;
		}
		/**
		 * 手机
		 * @return phone
		 */
		public String getPhone() {
			return phone;
		}
		/**
		 * 手机
		 */
		public void setPhone(String phone) {
			this.phone = phone;
		}
		/**
		 * 邮箱
		 * @return mail
		 */
		public String getMail() {
			return mail;
		}
		/**
		 * 邮箱
		 */
		public void setMail(String mail) {
			this.mail = mail;
		}
		/**
		 * 下级人数
		 * @return lower_level_number
		 */
		public Integer getLower_level_number() {
			return lower_level_number;
		}
		/**
		 * 下级人数
		 */
		public void setLower_level_number(Integer lower_level_number) {
			this.lower_level_number = lower_level_number;
		}
		/**
		 * 上级姓名
		 * @return parent_name
		 */
		public String getParent_name() {
			return parent_name;
		}
		/**
		 * 上级姓名
		 */
		public void setParent_name(String parent_name) {
			this.parent_name = parent_name;
		}
		/**
		 * 加入公司时间
		 * @return create_time
		 */
		public Date getCreate_time() {
			return create_time;
		}
		/**
		 * 加入公司时间
		 */
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		/**
		 * 职位
		 * @return job
		 */
		public String getJob() {
			return job;
		}
		/**
		 * 职位
		 */
		public void setJob(String job) {
			this.job = job;
		}
		/**
		 * 职级
		 * @return job_level
		 */
		public String getJob_level() {
			return job_level;
		}
		/**
		 * 职级
		 */
		public void setJob_level(String job_level) {
			this.job_level = job_level;
		}
		/**
		 * 所在部门
		 * @return department
		 */
		public String getDepartment() {
			return department;
		}
		/**
		 * 所在部门
		 */
		public void setDepartment(String department) {
			this.department = department;
		}
		/**
		 * 负责职能
		 * @return job_info
		 */
		public Integer getJob_info() {
			return job_info;
		}
		/**
		 * 负责职能
		 */
		public void setJob_info(Integer job_info) {
			this.job_info = job_info;
		}
		/**
		 * 历史职位
		 * @return history_job
		 */
		public String getHistory_job() {
			return history_job;
		}
		/**
		 * 历史职位
		 */
		public void setHistory_job(String history_job) {
			this.history_job = history_job;
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