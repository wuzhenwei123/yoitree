package com.yy.yyCourseClassify.model;

import java.util.Date;
import java.util.List;

import com.base.model.BaseModel;
import com.yy.yyCourse.model.YyCourse;
/**
 * 课程体系表	
 * @author	wzw
 * @time	2017-09-10 22:02:35
 */
public class YyCourseClassify extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 名称 **/
		private String name;
		/** 级别 0 最高 **/
		private Integer level;
		/** 父id **/
		private Long parent_id;
		
		private String img_url;
		private String info;
		/** 搜索关键字 **/
		private String keyword;
		
		private String module_name;
		private String theme_name;
		private Long theme_id;
		private Long fenye;
		//知识点总数
		private Integer point_total;
		private Integer point_time;
		
		private Integer is_joined;
		private Integer style;
		private Integer sort_id;
		
		public Integer getSort_id() {
			return sort_id;
		}
		public void setSort_id(Integer sort_id) {
			this.sort_id = sort_id;
		}
		public Integer getIs_joined() {
			return is_joined;
		}
		public void setIs_joined(Integer is_joined) {
			this.is_joined = is_joined;
		}
		public Integer getStyle() {
			return style;
		}
		public void setStyle(Integer style) {
			this.style = style;
		}
		public Integer getPoint_total() {
			return point_total;
		}
		public void setPoint_total(Integer point_total) {
			this.point_total = point_total;
		}
		public Integer getPoint_time() {
			return point_time;
		}
		public void setPoint_time(Integer point_time) {
			this.point_time = point_time;
		}
		public Long getFenye() {
			return fenye;
		}
		public void setFenye(Long fenye) {
			this.fenye = fenye;
		}
		private List<YyCourseClassify> listSub;
		
		public List<YyCourseClassify> getListSub() {
			return listSub;
		}
		public void setListSub(List<YyCourseClassify> listSub) {
			this.listSub = listSub;
		}
		
		public String getModule_name() {
			return module_name;
		}
		public void setModule_name(String module_name) {
			this.module_name = module_name;
		}
		public String getTheme_name() {
			return theme_name;
		}
		public void setTheme_name(String theme_name) {
			this.theme_name = theme_name;
		}
		public Long getTheme_id() {
			return theme_id;
		}
		public void setTheme_id(Long theme_id) {
			this.theme_id = theme_id;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getImg_url() {
			return img_url;
		}
		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
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
		 * 名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 级别 0 最高
		 * @return level
		 */
		public Integer getLevel() {
			return level;
		}
		/**
		 * 级别 0 最高
		 */
		public void setLevel(Integer level) {
			this.level = level;
		}
		/**
		 * 父id
		 * @return parent_id
		 */
		public Long getParent_id() {
			return parent_id;
		}
		/**
		 * 父id
		 */
		public void setParent_id(Long parent_id) {
			this.parent_id = parent_id;
		}
}