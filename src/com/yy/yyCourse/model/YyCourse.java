package com.yy.yyCourse.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * 课程表	
 * @author	wzw
 * @time	2017-09-11 16:02:52
 */
public class YyCourse extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  **/
		private Long id;
		/** 课程名称 **/
		private String name;
		/** 课程编码 **/
		private String code;
		/** 分类id **/
		private Long classify_id;
		/** 封面地址 **/
		private String img_url;
		/** 视频地址 **/
		private String video_url;
		/** 音频地址 **/
		private String mp3_url;
		/** 课程简介 **/
		private String info;
		/** 时长 单位秒 **/
		private Integer when_long;
		/** 显示顺序 **/
		private Integer sort_id;
		/** 视频id（腾讯云） **/
		private String video_fileId;
		/** 音频id（腾讯云） **/
		private String audio_fileId;
		/** 视频转码状态 2转码中 1 转码成功 0转码失败 **/
		private Integer video_state;
		/** 音频转码状态 2转码中 1 转码成功 0转码失败 **/
		private Integer audio_state;
		
		private String video_play;
		private String audio_play;
		
		private Integer function_id;
		
		
		/**  **/
		private Long otherId;
		
		/** 木块id **/
		private Long module_id;
		/** 主题id **/
		private Long theme_id;
		
		private String module_name;
		private String theme_name;
		private String lesson_name;
		
		private Integer study_state;
		
		private String when_long_str;
		
		private Integer total_number;
		private String function_ids;
		public String getFunction_ids() {
			return function_ids;
		}
		public void setFunction_ids(String function_ids) {
			this.function_ids = function_ids;
		}
		public Integer getTotal_number() {
			return total_number;
		}
		public void setTotal_number(Integer total_number) {
			this.total_number = total_number;
		}
		public String getWhen_long_str() {
			return when_long_str;
		}
		public void setWhen_long_str(String when_long_str) {
			this.when_long_str = when_long_str;
		}
		public Integer getStudy_state() {
			return study_state;
		}
		public void setStudy_state(Integer study_state) {
			this.study_state = study_state;
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
		public String getLesson_name() {
			return lesson_name;
		}
		public void setLesson_name(String lesson_name) {
			this.lesson_name = lesson_name;
		}
		public Long getModule_id() {
			return module_id;
		}
		public void setModule_id(Long module_id) {
			this.module_id = module_id;
		}
		public Long getTheme_id() {
			return theme_id;
		}
		public void setTheme_id(Long theme_id) {
			this.theme_id = theme_id;
		}
		public Long getOtherId() {
			return otherId;
		}
		public void setOtherId(Long otherId) {
			this.otherId = otherId;
		}
		public Integer getFunction_id() {
			return function_id;
		}
		public void setFunction_id(Integer function_id) {
			this.function_id = function_id;
		}
		public String getVideo_play() {
			return video_play;
		}
		public void setVideo_play(String video_play) {
			this.video_play = video_play;
		}
		public String getAudio_play() {
			return audio_play;
		}
		public void setAudio_play(String audio_play) {
			this.audio_play = audio_play;
		}
		public Integer getAudio_state() {
			return audio_state;
		}
		public void setAudio_state(Integer audio_state) {
			this.audio_state = audio_state;
		}
		public Integer getVideo_state() {
			return video_state;
		}
		public void setVideo_state(Integer video_state) {
			this.video_state = video_state;
		}
		public String getAudio_fileId() {
			return audio_fileId;
		}
		public void setAudio_fileId(String audio_fileId) {
			this.audio_fileId = audio_fileId;
		}
		public String getVideo_fileId() {
			return video_fileId;
		}
		public void setVideo_fileId(String video_fileId) {
			this.video_fileId = video_fileId;
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
		 * 课程名称
		 * @return name
		 */
		public String getName() {
			return name;
		}
		/**
		 * 课程名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * 课程编码
		 * @return code
		 */
		public String getCode() {
			return code;
		}
		/**
		 * 课程编码
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * 分类id
		 * @return classify_id
		 */
		public Long getClassify_id() {
			return classify_id;
		}
		/**
		 * 分类id
		 */
		public void setClassify_id(Long classify_id) {
			this.classify_id = classify_id;
		}
		/**
		 * 封面地址
		 * @return img_url
		 */
		public String getImg_url() {
			return img_url;
		}
		/**
		 * 封面地址
		 */
		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		/**
		 * 视频地址
		 * @return video_url
		 */
		public String getVideo_url() {
			return video_url;
		}
		/**
		 * 视频地址
		 */
		public void setVideo_url(String video_url) {
			this.video_url = video_url;
		}
		/**
		 * 音频地址
		 * @return mp3_url
		 */
		public String getMp3_url() {
			return mp3_url;
		}
		/**
		 * 音频地址
		 */
		public void setMp3_url(String mp3_url) {
			this.mp3_url = mp3_url;
		}
		/**
		 * 课程简介
		 * @return info
		 */
		public String getInfo() {
			return info;
		}
		/**
		 * 课程简介
		 */
		public void setInfo(String info) {
			this.info = info;
		}
		/**
		 * 时长 单位秒
		 * @return when_long
		 */
		public Integer getWhen_long() {
			return when_long;
		}
		/**
		 * 时长 单位秒
		 */
		public void setWhen_long(Integer when_long) {
			this.when_long = when_long;
		}
		/**
		 * 显示顺序
		 * @return sort_id
		 */
		public Integer getSort_id() {
			return sort_id;
		}
		/**
		 * 显示顺序
		 */
		public void setSort_id(Integer sort_id) {
			this.sort_id = sort_id;
		}
}