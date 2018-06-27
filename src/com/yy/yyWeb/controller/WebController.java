package com.yy.yyWeb.controller;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.jsms.api.JSMSClient;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.model.SMSPayload;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.ComparatorUser;
import com.base.utils.ConfigConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.utils.patchca.MyCustomBackgroundFactory;
import com.yy.yyCompany.model.YyCompany;
import com.yy.yyCompany.service.YyCompanyService;
import com.yy.yyCompanyLearnStyle.model.YyCompanyLearnStyle;
import com.yy.yyCompanyLearnStyle.service.YyCompanyLearnStyleService;
import com.yy.yyCourse.model.YyCourse;
import com.yy.yyCourse.service.YyCourseService;
import com.yy.yyCourseAppendix.model.YyCourseAppendix;
import com.yy.yyCourseAppendix.service.YyCourseAppendixService;
import com.yy.yyCourseClassify.model.YyCourseClassify;
import com.yy.yyCourseClassify.service.YyCourseClassifyService;
import com.yy.yyEmployees.model.YyEmployees;
import com.yy.yyEmployees.service.YyEmployeesService;
import com.yy.yyFunction.model.YyFunction;
import com.yy.yyFunction.service.YyFunctionService;
import com.yy.yyIndustry.model.YyIndustry;
import com.yy.yyIndustry.service.YyIndustryService;
import com.yy.yyLearningStyle.model.YyLearningStyle;
import com.yy.yyLearningStyle.service.YyLearningStyleService;
import com.yy.yyTurnover.model.YyTurnover;
import com.yy.yyTurnover.service.YyTurnoverService;
import com.yy.yyUser.model.YyUser;
import com.yy.yyUser.service.YyUserService;
import com.yy.yyUserCourse.model.YyUserCourse;
import com.yy.yyUserCourse.service.YyUserCourseService;
import com.yy.yyUserFunction.model.YyUserFunction;
import com.yy.yyUserFunction.service.YyUserFunctionService;
import com.yy.yyUserStudy.model.YyUserStudy;
import com.yy.yyUserStudy.service.YyUserStudyService;
import com.yy.yyWeb.service.WebService;

@Controller
@RequestMapping("/web")
public class WebController extends BaseController{
	
	private static Logger logger = Logger.getLogger(WebController.class);
	
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static Random random = new Random();
	
	/** 存放手机号-验证码缓存 **/
	public static Map<String, String> CODEMAP = new HashMap<String, String>();
	/** 存放手机号-时间缓存 **/
	public static Map<String, Date> TIMEMAP = new HashMap<String, Date>();
	
	@Autowired
	private YyUserService yyuserService = null;
	@Autowired
	private WebService webService = null;
	@Autowired
	private YyCourseClassifyService yycourseclassifyService = null;
	@Autowired
	private YyUserStudyService yyuserstudyService = null;
	@Autowired
	private YyUserCourseService yyusercourseService = null;
	@Autowired
	private YyCourseService yycourseService = null;
	@Autowired
	private YyCourseAppendixService yycourseappendixService = null;
	@Autowired
	private YyCompanyService yycompanyService = null;
	@Autowired
	private YyCompanyLearnStyleService yycompanylearnstyleService = null;
	@Autowired
	private YyIndustryService yyindustryService = null;
	@Autowired
	private YyLearningStyleService yylearningstyleService = null;
	@Autowired
	private YyEmployeesService yyemployeesService = null;
	@Autowired
	private YyTurnoverService yyturnoverService = null;
	@Autowired
	private YyFunctionService yyfunctionService = null;
	@Autowired
	private YyUserFunctionService yyuserfunctionService = null;
	
	
	static {
		// cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		cs.setColorFactory(new ColorFactory() {
			@Override
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("1234567890");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
		cs.setBackgroundFactory(new MyCustomBackgroundFactory());
	}
	
	
	/**
	 * 进入下载页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/downLoad", method = RequestMethod.GET)
	public String downLoad(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long id = RequestHandler.getLong(request, "id");
		try{
			YyCourseAppendix yyCourseAppendix = new YyCourseAppendix();
			yyCourseAppendix.setId(id);
			yyCourseAppendix = yycourseappendixService.getYyCourseAppendix(yyCourseAppendix);
			model.addAttribute("name", yyCourseAppendix.getName());
			model.addAttribute("link", yyCourseAppendix.getUrl());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/common/excelup";
	}
	
	/**
	 * 进入员工页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getChildEmploye", method = RequestMethod.GET)
	public String getChildEmploye(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long id = RequestHandler.getLong(request, "id");
		JSONObject json = new JSONObject();
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			YyUser yyUser = new YyUser();
			yyUser.setParent_id(id);
			List<YyUser> list = yyuserService.getYyUserBaseList(yyUser);
			if(list!=null&&list.size()>0){
				for(YyUser u:list){
					if(StringUtils.isNotBlank(u.getParent_ids())){
						u.setIs_select(u.getParent_ids().split(",").length);
					}else{
						u.setIs_select(1);
					}
					if(u.getBirthday()!=null){
						u.setRemark1(sf.format(u.getBirthday()));
					}
				}
			}
			json.put("c", 0);
			json.put("d", list);
			json.put("m", "停用成功");
		}catch(Exception e){
			json.put("c", -1);
			json.put("m", "系统异常，请重试");
			logger.info(exception(e));
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 进入员工页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEmploye2", method = RequestMethod.GET)
	public String toEmploye2(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		String name = RequestHandler.getString(request, "name");
		String parent_ids = RequestHandler.getString(request, "parent_ids");
		Integer is_manager = RequestHandler.getInteger(request, "is_manager");
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				YyUser user = new YyUser();
				user.setName(name);
				user.setIs_manager(is_manager);
				if(StringUtils.isNotBlank(name)){
					
				}else{
					user.setParent_flag(1);
				}
				user.setCompany_id(yyUser.getCompany_id());
				List<YyUser> userList = yyuserService.getYyUserBaseList(user);
				if(userList!=null&&userList.size()>0){
					for(YyUser u:userList){
						if(StringUtils.isNotBlank(u.getParent_ids())){
							u.setIs_select(u.getParent_ids().split(",").length);
						}else{
							u.setIs_select(1);
						}
					}
				}
				model.addAttribute("userList", userList);
				model.addAttribute("name", name);
				model.addAttribute("is_manager", is_manager);
				model.addAttribute("parent_ids", parent_ids);
			}else{
				return "/web/tips";
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav_h", "yuangong");
		return "/web/employe2";
	}
	
	
	/**
	 * 设为管理员
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/setAdmin", method = RequestMethod.POST)
	public String setAdmin(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long id = RequestHandler.getLong(request, "id");
		JSONObject json = new JSONObject();
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				YyUser yyuser = new YyUser();
				yyuser.setId(id);
				yyuser.setIs_manager(1);
				yyuserService.updateYyUser(yyuser);
				json.put("c", 0);
				json.put("m", "设置成功");
			}
		}catch(Exception e){
			json.put("c", -1);
			json.put("m", "系统异常，请重试");
			logger.info(exception(e));
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 停用账号
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/stopAccount", method = RequestMethod.POST)
	public String stopAccount(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long id = RequestHandler.getLong(request, "id");
		JSONObject json = new JSONObject();
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				YyUser yyuser = new YyUser();
				yyuser.setId(id);
				yyuser.setState(0);
				yyuserService.updateYyUser(yyuser);
				json.put("c", 0);
				json.put("m", "停用成功");
			}
		}catch(Exception e){
			json.put("c", -1);
			json.put("m", "系统异常，请重试");
			logger.info(exception(e));
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 进入员工编辑页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateEmploye", method = RequestMethod.POST)
	public String updateEmploye(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		JSONObject json = new JSONObject();
		String fc = RequestHandler.getString(request, "fc");
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				YyUser yyuser = requst2Bean(request,YyUser.class);
				//检查手机号是否重复
				int count = yyuserService.checkLoginName(yyuser);
				if(count>0){
					json.put("c", -1);
					json.put("m", "更新失败，手机号被占用，请修改！");
				}else{
					//查询所有付id
					if(yyuser.getParent_id()!=null){
						YyUser yyUser1 = new YyUser();
						yyUser1.setId(yyuser.getParent_id());
						yyUser1 = yyuserService.getYyUser(yyUser1);
						yyuser.setParent_name(yyUser1.getName());
						if(StringUtils.isNotBlank(yyUser1.getParent_ids())){
							String parent_ids = yyUser1.getParent_ids() + "," + yyuser.getParent_id();
							yyuser.setParent_ids(parent_ids);
						}else{
							yyuser.setParent_ids(yyUser1.getId()+"");
						}
					}
					if(yyuser.getId()!=null){
						
						YyUser uy = new YyUser();
						uy.setId(yyuser.getId());
						uy = yyuserService.getYyUser(uy);
						yyuserService.updateYyUser(yyuser);
						//重新计算父级直属下级人数
						YyUser yyUser2 = new YyUser();
						yyUser2.setParent_id(yyuser.getParent_id());
						int countP = yyuserService.getYyUserListCount(yyUser2);
						YyUser yyUser3 = new YyUser();
						yyUser3.setId(yyuser.getParent_id());
						yyUser3.setLower_level_number(countP);
						yyuserService.updateYyUser(yyUser3);
						////重新计算原来父级下级人数
						YyUser yyUser22 = new YyUser();
						yyUser22.setParent_id(uy.getParent_id());
						int countP2 = yyuserService.getYyUserListCount(yyUser2);
						YyUser yyUser33 = new YyUser();
						yyUser33.setId(yyuser.getParent_id());
						yyUser33.setLower_level_number(countP2);
						yyuserService.updateYyUser(yyUser33);
					}else{
						yyuser.setCompany_id(yyUser.getCompany_id());
						yyuser.setCompany_name(yyUser.getCompany_name());
						yyuser.setNick_name(yyuser.getName());
//						yyuser.setJob_info(fc);
						yyuser.setCreate_time(new Date());
						yyuser.setPassword(MD5.getMD5ofStr(yyuser.getPhone().substring(yyuser.getPhone().length()-6, yyuser.getPhone().length())));
						Long id = yyuserService.insertYyUser(yyuser);
						if(id!=null&&id.intValue()>0){
							if(StringUtils.isNotBlank(fc)){
								String fcs[] = fc.split(",");
								for(String fcid:fcs){
									YyUserFunction yyUserFunction = new YyUserFunction();
									yyUserFunction.setUser_id(id);
									yyUserFunction.setFunction_id(Integer.valueOf(fcid));
									yyuserfunctionService.insertYyUserFunction(yyUserFunction);
								}
								//查询职能下面所有课程
								YyCourse yyCourse = new YyCourse();
								yyCourse.setFunction_ids(fc);
								List<YyCourse> listC = yycourseService.getYyCourseListByFunctionS(yyCourse);
								if(listC!=null&&listC.size()>0){
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(new Date());
									calendar.add(Calendar.MONTH, 1);
									for(YyCourse course:listC){
										YyUserCourse yyUserCourse = new YyUserCourse();
										yyUserCourse.setUser_id(id);
										yyUserCourse.setCourse_id(course.getId());
										yyUserCourse.setStyle(0);
										yyUserCourse.setStudy_state(-1);
										yyUserCourse.setStart_time(new Date());
										yyUserCourse.setCourse_classify_id(course.getClassify_id());
										yyUserCourse.setOver_time(calendar.getTime());
										yyusercourseService.insertYyUserCourse(yyUserCourse);
									}
								}
							}
							//重新计算父级直属下级人数
							YyUser yyUser2 = new YyUser();
							yyUser2.setParent_id(yyuser.getParent_id());
							int countP = yyuserService.getYyUserListCount(yyUser2);
							YyUser yyUser3 = new YyUser();
							yyUser3.setId(yyuser.getParent_id());
							yyUser3.setLower_level_number(countP);
							yyuserService.updateYyUser(yyUser3);
						}
					}
					json.put("c", 0);
					json.put("m", "更新成功");
				}
			}
		}catch(Exception e){
			json.put("c", -1);
			json.put("m", "系统异常，请重试");
			logger.info(exception(e));
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 进入员工编辑页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpdateEmploye", method = RequestMethod.GET)
	public String toUpdateEmploye(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long id = RequestHandler.getLong(request, "id");
		try{
			YyUser yyUser = new YyUser();
			yyUser.setId(id);
			yyUser = yyuserService.getYyUser(yyUser);
			model.addAttribute("yyUser", yyUser);
			YyUser yyUser1 = new YyUser();
			yyUser1.setCompany_id(yyUser.getCompany_id());
			List<YyUser> list = yyuserService.getYyUserBaseList(yyUser1);
			model.addAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(exception(e));
		}
		return "/web/updateEmploye";
	}
	/**
	 * 进入员工编辑页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAddEmploye", method = RequestMethod.GET)
	public String toAddEmploye(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			YyUser yyUser1 = new YyUser();
			yyUser1.setCompany_id(yyUser.getCompany_id());
			List<YyUser> list = yyuserService.getYyUserBaseList(yyUser1);
			model.addAttribute("list", list);
			//获取职能
			YyFunction yyFunction = new YyFunction();
			yyFunction.setParent_id(1);
			List<YyFunction> listF = yyfunctionService.getYyFunctionBaseList(yyFunction);
			model.addAttribute("listF", listF);
			model.addAttribute("addFlag", 1);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(exception(e));
		}
		return "/web/updateEmploye";
	}
	
	/**
	 * 进入员工页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEmploye", method = RequestMethod.GET)
	public String toEmploye(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		String name = RequestHandler.getString(request, "name");
		String parent_ids = RequestHandler.getString(request, "parent_ids");
		String orderBy = RequestHandler.getString(request, "orderBy");
		String sort = RequestHandler.getString(request, "sort");
		Integer is_manager = RequestHandler.getInteger(request, "is_manager");
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				YyUser user = new YyUser();
				user.setName(name);
				user.setIs_manager(is_manager);
				if("isnull".equals(parent_ids)){
					user.setParent_flag(1);
				}
				if(StringUtils.isNotBlank(orderBy)){
					user.setSortColumn("a."+orderBy+" " + sort);
				}
				user.setCompany_id(yyUser.getCompany_id());
				List<YyUser> userList = yyuserService.getYyUserBaseList(user);
				if(userList!=null&&userList.size()>0){
					for(YyUser u:userList){
						if(StringUtils.isNotBlank(u.getParent_ids())){
							u.setIs_select(u.getParent_ids().split(",").length);
						}else{
							u.setIs_select(1);
						}
					}
				}
				model.addAttribute("userList", userList);
				model.addAttribute("name", name);
				model.addAttribute("is_manager", is_manager);
				model.addAttribute("parent_ids", parent_ids);
				model.addAttribute("orderBy", orderBy);
				model.addAttribute("sort", sort);
			}else{
				return "/web/tips";
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav_h", "yuangong");
		return "/web/employe";
	}
	
	/**
	 * 修改企业信息
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateCompanyInfo", method = RequestMethod.POST)
	public String updateCompanyInfo(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		JSONObject json = new JSONObject();
		Integer industryId = RequestHandler.getInteger(request, "industryId");
		Integer employeesId = RequestHandler.getInteger(request, "employeesId");
		Integer turnoverId = RequestHandler.getInteger(request, "turnoverId");
		String contact_name = RequestHandler.getString(request, "contact_name");
		String contact_phone = RequestHandler.getString(request, "contact_phone");
		String learningStyle_ids = RequestHandler.getString(request, "learningStyle_ids");
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(industryId!=null&&employeesId!=null&&turnoverId!=null&&StringUtils.isNotBlank(contact_name)&&StringUtils.isNotBlank(contact_phone)){
				YyCompany yycompany = new YyCompany();
				//所属行业
				YyIndustry yyIndustry = new YyIndustry();
				yyIndustry.setId(industryId);
				yyIndustry = yyindustryService.getYyIndustry(yyIndustry);
				if(yyIndustry!=null){
					yycompany.setIndustry_name(yyIndustry.getName());
				}
				//员工数量
				YyEmployees yyEmployees = new YyEmployees();
				yyEmployees.setId(employeesId);
				yyEmployees = yyemployeesService.getYyEmployees(yyEmployees);
				if(yyEmployees!=null){
					yycompany.setEmployees_name(yyEmployees.getName());
				}
				//营业额
				YyTurnover yyTurnover = new YyTurnover();
				yyTurnover.setId(turnoverId);
				yyTurnover = yyturnoverService.getYyTurnover(yyTurnover);
				if(yyTurnover!=null){
					yycompany.setTurnover_name(yyTurnover.getName());
				}
				yycompany.setContact_name(contact_name);
				yycompany.setContact_phone(contact_phone);
				yycompany.setId(yyUser.getCompany_id());
				int flag = yycompanyService.updateYyCompany(yycompany);
				if(flag>0&&StringUtils.isNotBlank(learningStyle_ids)){
					YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
					yyCompanyLearnStyle.setCompany_id(yyUser.getCompany_id());
					List<YyCompanyLearnStyle> list = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
					if(list!=null&&list.size()>0){
						for(YyCompanyLearnStyle cl:list){
							yycompanylearnstyleService.removeYyCompanyLearnStyle(cl);
						}
					}
					String[] learningStyle_id = learningStyle_ids.split(",");
					for(String str:learningStyle_id){
						YyCompanyLearnStyle yyCompanyLearnStyle1 = new YyCompanyLearnStyle();
						yyCompanyLearnStyle1.setCompany_id(yyUser.getCompany_id());
						yyCompanyLearnStyle1.setLearning_style_id(Integer.valueOf(str));
						yycompanylearnstyleService.insertYyCompanyLearnStyle(yyCompanyLearnStyle1);
					}
				}
				json.put("c", 0);
				json.put("m", "成功");
			}else{
				json.put("c", -1);
				json.put("m", "失败");
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
			json.put("c", -1);
			json.put("m", "失败");
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 修改企业信息
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpdateCompanyInfo", method = RequestMethod.GET)
	public String toUpdateCompanyInfo(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				YyIndustry yyIndustry = new YyIndustry();
				yyIndustry.setState(1);
				List<YyIndustry> listIndustry = yyindustryService.getYyIndustryBaseList(yyIndustry);
				//学习风格
				YyLearningStyle yyLearningStyle = new YyLearningStyle();
				yyLearningStyle.setState(1);
				List<YyLearningStyle> listLearningStyle = yylearningstyleService.getYyLearningStyleBaseList(yyLearningStyle);
				//员工数量
				YyEmployees yyEmployees = new YyEmployees();
				yyEmployees.setState(1);
				List<YyEmployees> listEmployees = yyemployeesService.getYyEmployeesBaseList(yyEmployees);
				//营业额
				YyTurnover yyTurnover = new YyTurnover();
				yyTurnover.setState(1);
				List<YyTurnover> listTurnover = yyturnoverService.getYyTurnoverBaseList(yyTurnover);
				
				YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
				yyCompanyLearnStyle.setCompany_id(yyUser.getCompany_id());
				List<YyCompanyLearnStyle> listCompanyLearnStyle = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
				
				model.addAttribute("listIndustry", listIndustry);
				model.addAttribute("listLearningStyle", listLearningStyle);
				model.addAttribute("listEmployees", listEmployees);
				model.addAttribute("listTurnover", listTurnover);
				model.addAttribute("listCompanyLearnStyle", listCompanyLearnStyle);
				//获取企业信息
				YyCompany yyCompany = new YyCompany();
				yyCompany.setId(yyUser.getCompany_id());
				yyCompany = yycompanyService.getYyCompany(yyCompany);
				model.addAttribute("yyCompany", yyCompany);
			}else{
				return "/web/tips";
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		return "/web/updateCompanyInfo";
	}
	
	/**
	 * 进入企业信息
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toCompanyInfo", method = RequestMethod.GET)
	public String toCompanyInfo(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(yyUser.getIs_manager().intValue()==1||yyUser.getIs_super_manager().intValue()==1){
				//获取企业信息
				YyCompany yyCompany = new YyCompany();
				yyCompany.setId(yyUser.getCompany_id());
				yyCompany = yycompanyService.getYyCompany(yyCompany);
				model.addAttribute("yyCompany", yyCompany);
				//
				YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
				yyCompanyLearnStyle.setCompany_id(yyUser.getCompany_id());
				List<YyCompanyLearnStyle> list = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
				String learnStyle = null;
				if(list!=null&&list.size()>0){
					for(YyCompanyLearnStyle style:list){
						if(learnStyle==null){
							learnStyle = style.getLearning_style_name();
						}else{
							learnStyle = learnStyle + "," + style.getLearning_style_name();
						}
					}
				}
				model.addAttribute("learnStyle", learnStyle);
			}else{
				return "/web/tips";
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav_h", "qiye");
		return "/web/companyInfo";
	}
	
	/**
	 * 退选必修课
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delUc", method = RequestMethod.POST)
	public String delUc(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long course_classify_id = RequestHandler.getLong(request, "course_classify_id");
		Long user_id = RequestHandler.getLong(request, "user_id");
		JSONObject json = new JSONObject();
		try{
			if(user_id!=null&&course_classify_id!=null){
				YyUserCourse yyUserCourse = new YyUserCourse();
				yyUserCourse.setUser_id(user_id);
				yyUserCourse.setCourse_classify_id(course_classify_id);
				List<YyUserCourse> list = yyusercourseService.getYyUserCourseBaseList(yyUserCourse);
				if(list!=null&&list.size()>0){
					for(YyUserCourse uc:list){
						yyusercourseService.removeYyUserCourse(uc);
					}
				}
				json.put("c", 0);
				json.put("m", "成功");
			}else{
				json.put("c", -1);
				json.put("m", "失败");
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
			json.put("c", -1);
			json.put("m", "失败");
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	/**
	 * 修改必修课时间
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateUserCourseTime", method = RequestMethod.POST)
	public String updateUserCourseTime(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		String lessonIds = RequestHandler.getString(request, "lessonIds");
		String time = RequestHandler.getString(request, "time");
		JSONObject json = new JSONObject();
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(lessonIds)&&StringUtils.isNotBlank(time)){
				Long ucId = Long.valueOf(lessonIds.split("_")[0]);
				Long user_id = Long.valueOf(lessonIds.split("_")[2]);
				YyUserCourse yyUserCourse = new YyUserCourse();
				yyUserCourse.setCourse_classify_id(ucId);
				yyUserCourse.setUser_id(user_id);
				List<YyUserCourse> list= yyusercourseService.getYyUserCourseBaseList(yyUserCourse);
				if(list!=null&&list.size()>0){
					for(YyUserCourse uc:list){
						if("start".equals(lessonIds.split("_")[1])){
							uc.setStart_time(sf.parse(time));
						}else if("over".equals(lessonIds.split("_")[1])){
							uc.setOver_time(sf.parse(time));
						}
						yyusercourseService.updateYyUserCourse(uc);
					}
				}
				json.put("c", 0);
				json.put("m", "成功");
			}else{
				json.put("c", -1);
				json.put("m", "失败");
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
			json.put("c", -1);
			json.put("m", "失败");
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 看视频
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toVideo", method = RequestMethod.GET)
	public String toVideo(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long pointId = RequestHandler.getLong(request, "id");
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			YyCourse yyCourse = new YyCourse();
			yyCourse.setId(pointId);
			yyCourse = yycourseService.getYyCourse(yyCourse);
			if(yyCourse!=null){
				model.addAttribute("pointId", yyCourse.getId());
				model.addAttribute("pointCover", yyCourse.getImg_url()!=null?yyCourse.getImg_url():"");
				model.addAttribute("pointVideoCover", yyCourse.getImg_url()!=null?yyCourse.getImg_url():"");
				model.addAttribute("pointVideoUrl", yyCourse.getVideo_play());
				model.addAttribute("pointDesc", yyCourse.getInfo());
				model.addAttribute("lessonId", yyCourse.getClassify_id());
				model.addAttribute("pointName", yyCourse.getName());
				model.addAttribute("yyCourse", yyCourse);
				//查询附件
				YyCourseAppendix yyCourseAppendix = new YyCourseAppendix();
				yyCourseAppendix.setCourse_id(yyCourse.getId());
				List<YyCourseAppendix> listAppendix = yycourseappendixService.getYyCourseAppendixBaseList(yyCourseAppendix);
				model.addAttribute("listAppendix", listAppendix);
				//查询父级
				YyCourseClassify yyCourseClassifyP = new YyCourseClassify();
				yyCourseClassifyP.setId(yyCourse.getClassify_id());
				yyCourseClassifyP = yycourseclassifyService.getYyCourseClassify(yyCourseClassifyP);
				if(yyCourseClassifyP!=null){
					model.addAttribute("lessonName", yyCourseClassifyP.getName());
					//查询父级
					YyCourseClassify yyCourseClassifyPP = new YyCourseClassify();
					yyCourseClassifyPP.setId(yyCourseClassifyP.getParent_id());
					yyCourseClassifyPP = yycourseclassifyService.getYyCourseClassify(yyCourseClassifyPP);
					if(yyCourseClassifyPP!=null){
						model.addAttribute("moduleId", yyCourseClassifyPP.getId());
						model.addAttribute("moduleName", yyCourseClassifyPP.getName());
						//查询父级
						YyCourseClassify yyCourseClassifyPPP = new YyCourseClassify();
						yyCourseClassifyPPP.setId(yyCourseClassifyPP.getParent_id());
						yyCourseClassifyPPP = yycourseclassifyService.getYyCourseClassify(yyCourseClassifyPPP);
						if(yyCourseClassifyPPP!=null){
							model.addAttribute("themeId", yyCourseClassifyPPP.getId());
							model.addAttribute("themeName", yyCourseClassifyPPP.getName());
						}else{
							model.addAttribute("themeId", 0);
							model.addAttribute("themeName", "");
						}
					}else{
						model.addAttribute("moduleId", 0);
						model.addAttribute("moduleName", "");
					}
				}else{
					model.addAttribute("lessonName", "");
					model.addAttribute("moduleId", 0);
					model.addAttribute("moduleName", "");
					model.addAttribute("themeId", 0);
					model.addAttribute("themeName", "");
				}
				//其他知识点
				YyCourse yyCourse1 = new YyCourse();
				yyCourse1.setOtherId(pointId);
				yyCourse1.setClassify_id(yyCourse.getClassify_id());
				List<YyCourse> listC = yycourseService.getYyCourseBaseList(yyCourse1);
				if(listC!=null&&listC.size()>0){
					for(YyCourse course:listC){
						course.setWhen_long_str(yycourseService.secToTime(course.getWhen_long()));
						if(yyUser!=null){
							YyUserCourse yyUserCourse = new YyUserCourse();
							yyUserCourse.setUser_id(yyUser.getId());
							yyUserCourse.setCourse_id(course.getId());
							List<YyUserCourse> listCC = yyusercourseService.getYyUserCourseBaseList(yyUserCourse);
							if(listCC!=null&&listCC.size()>0){
								YyUserCourse uc = listCC.get(0);
								course.setStudy_state(uc.getStudy_state()<1?0:1);
							}else{
								course.setStudy_state(0);
							}
						}else{
							course.setStudy_state(0);
						}
					}
				}
				model.addAttribute("listC", listC);
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav", "learn");
		return  "/web/video";
	}
	
	/**
	 * 视频统计
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studyRecord", method = RequestMethod.POST)
	public String studyRecord(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
//		String token = RequestHandler.getString(request, "token");
		String start_time = RequestHandler.getString(request, "start_time");
		String end_time = RequestHandler.getString(request, "end_time");
		Long pointId = RequestHandler.getLong(request, "pointId");
		Integer long_time = RequestHandler.getInteger(request, "long_time");
		Integer is_over = RequestHandler.getInteger(request, "is_over");
		JSONObject json = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			if(pointId!=null&&long_time!=null&&StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){
				//查询用户身份
				YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
				if(yyUser!=null){
					YyUserStudy yyUserStudy = new YyUserStudy();
					yyUserStudy.setCreate_time(new Date());
					yyUserStudy.setEnd_time(sf.parse(end_time));
					yyUserStudy.setStart_time(sf.parse(start_time));
					yyUserStudy.setLong_time(long_time);
					yyUserStudy.setPoint_id(pointId);
					//获取父级信息
					yyUserStudy = yyuserstudyService.getParentMsgByPointId(yyUserStudy);
					yyUserStudy.setIs_over(is_over);
					yyUserStudy.setUser_id(yyUser.getId());
					yyuserstudyService.insertYyUserStudy(yyUserStudy);
					//判断是否播放完成
					//计算学习总时长
					YyUserStudy yyUserStudy1 = new YyUserStudy();
					yyUserStudy1.setUser_id(yyUser.getId());
					yyUserStudy1.setPoint_id(pointId);
					int study_time = yyuserstudyService.getUserStudyTime(yyUserStudy1);
					if(is_over.intValue()==1){
						if(study_time>=yyUserStudy.getCount_time()){
							YyUserCourse yyUserCourse = new YyUserCourse();
							yyUserCourse.setUser_id(yyUser.getId());
							yyUserCourse.setCourse_id(pointId);
							yyUserCourse.setStudy_state(1);
							yyusercourseService.updateYyUserCourseByUserAndPointId(yyUserCourse);
						}else{
							YyUserCourse yyUserCourse = new YyUserCourse();
							yyUserCourse.setUser_id(yyUser.getId());
							yyUserCourse.setCourse_id(pointId);
							yyUserCourse.setStudy_state(0);
							yyusercourseService.updateYyUserCourseByUserAndPointId(yyUserCourse);
						}
					}else{
						if(study_time>=yyUserStudy.getCount_time()){
							//是否有过看完记录
							YyUserStudy yyUserStudy2 = new YyUserStudy();
							yyUserStudy2.setIs_over(1);
							yyUserStudy2.setUser_id(yyUser.getId());
							yyUserStudy2.setPoint_id(pointId);
							int count = yyuserstudyService.getYyUserStudyListCount(yyUserStudy2);
							if(count>0){
								YyUserCourse yyUserCourse = new YyUserCourse();
								yyUserCourse.setUser_id(yyUser.getId());
								yyUserCourse.setCourse_id(pointId);
								yyUserCourse.setStudy_state(1);
								yyusercourseService.updateYyUserCourseByUserAndPointId(yyUserCourse);
							}else{
								YyUserCourse yyUserCourse = new YyUserCourse();
								yyUserCourse.setUser_id(yyUser.getId());
								yyUserCourse.setCourse_id(pointId);
								yyUserCourse.setStudy_state(0);
								yyusercourseService.updateYyUserCourseByUserAndPointId(yyUserCourse);
							}
						}else{
							YyUserCourse yyUserCourse = new YyUserCourse();
							yyUserCourse.setUser_id(yyUser.getId());
							yyUserCourse.setCourse_id(pointId);
							yyUserCourse.setStudy_state(0);
							yyusercourseService.updateYyUserCourseByUserAndPointId(yyUserCourse);
						}
					}
					json.put("c", 0);
					json.put("d", new JSONObject());
					json.put("m", "保存成功");
				}else{
					json.put("c", -1);
					json.put("d", new JSONObject());
					json.put("m", "用户不存在");
				}
			}else{
				json.put("c", -1);
				json.put("d", new JSONObject());
				json.put("m", "参数不能为空");
			}
		}catch(Exception e){
			json.put("c", -1);
			json.put("d", new JSONObject());
			json.put("m", "系统异常，请重试");
			logger.info(exception(e));
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 课程详情
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toLessonDetail", method = RequestMethod.GET)
	public String toLessonDetail(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long lessonId = RequestHandler.getLong(request, "lessonId");
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			YyCourseClassify yyCourseClassify = new YyCourseClassify();
			yyCourseClassify.setId(lessonId);
			yyCourseClassify = yycourseclassifyService.getYyCourseClassify(yyCourseClassify);
			if(yyCourseClassify!=null&&yyCourseClassify.getId().intValue()>0){
				model.addAttribute("lessonCover", yyCourseClassify.getImg_url()!=null?yyCourseClassify.getImg_url():"");
				model.addAttribute("lessonDesc", yyCourseClassify.getInfo());
				model.addAttribute("lessonId", lessonId);
				model.addAttribute("lessonName", yyCourseClassify.getName());
				//查询加入课程状态
				//查询用户身份
				if(yyUser!=null){
					YyUserCourse yyUserCourse = new YyUserCourse();
					yyUserCourse.setUser_id(yyUser.getId());
					yyUserCourse.setCourse_classify_id(lessonId);
					int count = yyusercourseService.getYyUserCourseListCount(yyUserCourse);
					if(count>0){
						List<YyUserCourse> listUC = yyusercourseService.getYyUserCourseBaseList(yyUserCourse);
						for(YyUserCourse cccc:listUC){
							model.addAttribute("studyType", cccc.getStyle());
						}
						model.addAttribute("joinStatus", 1);
					}else{
						model.addAttribute("joinStatus", 0);
					}
				}else{
					model.addAttribute("joinStatus", 0);
				}
				//查询父级
				YyCourseClassify yyCourseClassifyP = new YyCourseClassify();
				yyCourseClassifyP.setId(yyCourseClassify.getParent_id());
				yyCourseClassifyP = yycourseclassifyService.getYyCourseClassify(yyCourseClassifyP);
				if(yyCourseClassifyP!=null){
					model.addAttribute("moduleId", yyCourseClassify.getParent_id());
					model.addAttribute("moduleName", yyCourseClassifyP.getName());
					//查询父级
					YyCourseClassify yyCourseClassifyPP = new YyCourseClassify();
					yyCourseClassifyPP.setId(yyCourseClassifyP.getParent_id());
					yyCourseClassifyPP = yycourseclassifyService.getYyCourseClassify(yyCourseClassifyPP);
					if(yyCourseClassifyPP!=null){
						model.addAttribute("themeId", yyCourseClassifyP.getParent_id());
						model.addAttribute("themeName", yyCourseClassifyPP.getName());
					}
				}
				//查询视频
				YyCourse yyCourse = new YyCourse();
				yyCourse.setClassify_id(lessonId);
				List<YyCourse> list = yycourseService.getYyCourseBaseList(yyCourse);
				if(list!=null&&list.size()>0){
					for(YyCourse course:list){
						YyUserCourse yyUserCourse = new YyUserCourse();
						yyUserCourse.setUser_id(yyUser.getId());
						yyUserCourse.setCourse_id(course.getId());
						List<YyUserCourse> listC = yyusercourseService.getYyUserCourseBaseList(yyUserCourse);
						if(listC!=null&&listC.size()>0){
							YyUserCourse uc = listC.get(0);
							course.setStudy_state(uc.getStudy_state()<1?0:1);
						}else{
							course.setStudy_state(0);
						}
						course.setWhen_long_str(yycourseService.secToTime(course.getWhen_long()));
					}
				}
				model.addAttribute("listPoint", list);
			}
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav", "studycenter");
		return  "/web/lessonDetail";
	}
	
	/**
	 * 进入个人中心
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toMyInfo", method = RequestMethod.GET)
	public String toMyInfo(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//统计我的学习时长
			YyUserStudy yyUserStudy = new YyUserStudy();
			yyUserStudy.setUser_id(yyUser.getId());
			int study_time = yyuserstudyService.getUserStudyTime(yyUserStudy);
			//统计完成课程数量
			YyUserCourse yyUserCourse1 = new YyUserCourse();
			yyUserCourse1.setUser_id(yyUser.getId());
			yyUserCourse1.setStudy_state(1);
			int study_count = yyusercourseService.getYyUserCourseListCount(yyUserCourse1);
			//计算同级别同事
			List<YyUser> listSameLevelMsg = webService.getSameLevelMsg(yyUser);
			//内部排序
			ComparatorUser comparator=new ComparatorUser();
			Collections.sort(listSameLevelMsg, comparator);
			//计算已学课程
			
			YyUserStudy yyUserStudy1 = new YyUserStudy();
			yyUserStudy1.setUser_id(yyUser.getId());
			yyUserStudy1.setRowStart(0);
			yyUserStudy1.setRowCount(10);
//			int count = yyuserstudyService.getYyUserStudyByLessonCount(yyUserStudy);
			List<YyUserStudy> list = yyuserstudyService.getYyUserStudyByLesson(yyUserStudy1);
			if(list!=null&&list.size()>0){
				for(YyUserStudy uc:list){
					YyCourseClassify yyCourseClassify = new YyCourseClassify();
					yyCourseClassify.setId(uc.getLesson_id());
					yyCourseClassify = yycourseclassifyService.getYyCourseClassify(yyCourseClassify);
					uc.setImg_url(yyCourseClassify.getImg_url());
					//查询最后一次学习时间
					YyUserStudy yyUserStudy2 = new YyUserStudy();
					yyUserStudy2.setUser_id(yyUser.getId());
					yyUserStudy2.setLesson_id(uc.getLesson_id());
					yyUserStudy2.setRowCount(1);
					yyUserStudy2.setRowStart(0);
					yyUserStudy2.setSortColumn("a.create_time desc");
					ResponseList<YyUserStudy> listStudy = yyuserstudyService.getYyUserStudyList(yyUserStudy2);
					if(listStudy!=null&&listStudy.size()>0){
						Iterator<Object> it = listStudy.iterator();
						while(it.hasNext()){
							yyUserStudy = (YyUserStudy)it.next();
						}
					}
					if(yyUserStudy!=null&&yyUserStudy.getId()!=null){
						uc.setLastStudyTime(yyusercourseService.differentDays(yyUserStudy.getCreate_time(), new Date()));
					}
					//查询下面知识点
					YyCourse yyCourse = new YyCourse();
					yyCourse.setClassify_id(uc.getLesson_id());
					List<YyCourse> listC = yycourseService.getYyCourseBaseList(yyCourse);
					uc.setListCourse(listC);
					//查询下面附件
					YyCourseAppendix yyCourseAppendix = new YyCourseAppendix();
					yyCourseAppendix.setClassify_id(uc.getLesson_id());
					List<YyCourseAppendix> listCA = yycourseappendixService.getYyCourseAppendixBaseList(yyCourseAppendix);
					uc.setListAppendix(listCA);
				}
			}
			model.addAttribute("list", list);
			model.addAttribute("listSameLevelMsg", listSameLevelMsg);
			model.addAttribute("study_time", RequestHandler.secToTime(study_time));
			model.addAttribute("study_count", study_count);
//			model.addAttribute("count", count);
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav", "mycenter");
		return "/web/myInfo";
	}
	
	
	/**
	 * 加入或移除课表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addMyCourse", method = RequestMethod.POST)
	public String addMyCourse(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		JSONObject json = new JSONObject();
		Long lessonId = RequestHandler.getLong(request, "lessonId");
		Integer type = RequestHandler.getInteger(request, "type");
		try{
			if(lessonId!=null&&type!=null){
				//查询用户身份
				YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
				if(yyUser!=null){
					if(type.intValue()==0){//加入课程
						//查询是否已经加入课程
						YyUserCourse yyUserCourse = new YyUserCourse();
						yyUserCourse.setUser_id(yyUser.getId());
						yyUserCourse.setCourse_classify_id(lessonId);
						int count = yyusercourseService.getYyUserCourseListCount(yyUserCourse);
						if(count==0){
							YyCourse yyCourse = new YyCourse();
							yyCourse.setClassify_id(lessonId);
							List<YyCourse> listC = yycourseService.getYyCourseBaseList(yyCourse);
							if(listC!=null&&listC.size()>0){
								for(YyCourse course:listC){
									YyUserCourse yyUserCourse1 = new YyUserCourse();
									yyUserCourse1.setCourse_classify_id(lessonId);
									yyUserCourse1.setUser_id(yyUser.getId());
									yyUserCourse1.setCourse_id(course.getId());
									yyUserCourse1.setStudy_state(-1);
									yyUserCourse1.setStyle(1);
									yyusercourseService.insertYyUserCourse(yyUserCourse1);
								}
								json.put("c", 0);
								json.put("d", new JSONObject());
								json.put("m", "添加成功");
							}else{
								json.put("c", -1);
								json.put("d", new JSONObject());
								json.put("m", "该课程没有知识点，暂时无法添加");
							}
						}else{
							json.put("c", -1);
							json.put("d", new JSONObject());
							json.put("m", "课程已经存在");
						}
					}else if(type.intValue()==1){//移除课程
						YyUserCourse yyUserCourse = new YyUserCourse();
						yyUserCourse.setUser_id(yyUser.getId());
						yyUserCourse.setCourse_classify_id(lessonId);
						int flag = yyusercourseService.removeYyUserCourseByOther(yyUserCourse);
						if(flag>0){
							json.put("c", 0);
							json.put("d", new JSONObject());
							json.put("m", "移除成功");
						}else{
							json.put("c", -1);
							json.put("d", new JSONObject());
							json.put("m", "移除失败，请重试");
						}
					}
				}else{
					json.put("c", -1);
					json.put("d", new JSONObject());
					json.put("m", "用户不存在");
				}
			}else{
				json.put("c", -1);
				json.put("d", new JSONObject());
				json.put("m", "参数不能为空");
			}
			
		}catch(Exception e){
			json.put("c", -1);
			json.put("d", new JSONObject());
			json.put("m", "系统异常，请重试");
			logger.info(exception(e));
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 转向团队管理详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toTeamManageDetail", method = RequestMethod.GET)
	public String toTeamManageDetail(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		Long userId = RequestHandler.getLong(request, "userId");
		Integer type = RequestHandler.getInteger(request, "type");
		String url = "/web/teamManageDetail";
		try{
			if(type==null){
				type = 0;
			}
			if(type.intValue()==-1){
				url = "/web/teamManageDetail2";
			}else if(type.intValue()==1){
				url = "/web/teamManageDetail3";
			}
			YyUser yyUser = new YyUser();
			yyUser.setId(userId);
			yyUser = yyuserService.getYyUser(yyUser);
			YyUserCourse yyUserCourse = new YyUserCourse();
			yyUserCourse.setUser_id(userId);
//			yyUserCourse.setStudy_state(type);
			List<YyUserCourse> list = yyusercourseService.getYyUserCourseState(yyUserCourse);
			List<YyUserCourse> listOver = new ArrayList<YyUserCourse>();
			List<YyUserCourse> listIng = new ArrayList<YyUserCourse>();
			List<YyUserCourse> listNever = new ArrayList<YyUserCourse>();
			if(list!=null&&list.size()>0){
				for(YyUserCourse uc:list){
					
					YyUserStudy yyUserStudy = new YyUserStudy();
					yyUserStudy.setUser_id(userId);
					yyUserStudy.setLesson_id(uc.getCourse_classify_id());
					int long_time = yyuserstudyService.getUserStudyTime(yyUserStudy);
					if(yyUserStudy!=null){
						uc.setCount(RequestHandler.secToTime(long_time));
					}
					if(uc.getStyle()==0){
						int overDays = yyusercourseService.differentDays(new Date(), uc.getOver_time());
						if(overDays<0){
							overDays = 0;
						}
						uc.setOverDays(overDays);
					}
					
					//查询知识点数量
					YyCourse yyCourse = new YyCourse();
					yyCourse.setClassify_id(uc.getCourse_classify_id());
					int count = yycourseService.getYyCourseListCount(yyCourse);
					if(type.intValue()==1){
						uc.setStudyProgress(count);
						uc.setStudyCount(count);
					}else if(type.intValue()==-1){
						uc.setStudyProgress(0);
						uc.setStudyCount(count);
					}else{
						//查询已经完成的数量
						YyUserCourse yyUserCourse1 = new YyUserCourse();
						yyUserCourse1.setUser_id(userId);
						yyUserCourse1.setStudy_state(1);
						yyUserCourse1.setCourse_classify_id(uc.getCourse_classify_id());
						int countPoint = yyusercourseService.getYyUserCourseListCount(yyUserCourse1);
						uc.setStudyProgress(countPoint);
						uc.setStudyCount(count);
					}
					if(uc.getTotal()-uc.getStudy_state()==0){
						listOver.add(uc);
					}else if(uc.getTotal()+uc.getStudy_state()==0){
						listNever.add(uc);
					}else{
						listIng.add(uc);
					}
				}
			}
			model.addAttribute("listIng", listIng);
			model.addAttribute("listOver", listOver);
			model.addAttribute("listNever", listNever);
			model.addAttribute("yyUser", yyUser);
			model.addAttribute("type", type);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("userId", userId);
		model.addAttribute("nav_h", "team");
		return url;
	}
	
	/**
	 * 转向团队管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toTeamManage", method = RequestMethod.GET)
	public String toTeamManage(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//查询用户下属
			YyUser yyUserChild = new YyUser();
			yyUserChild.setParent_id(yyUser.getId());
			List<YyUser> listChild = yyuserService.getYyUserBaseList(yyUserChild);
			String user_names = null;
			String study_times = null;
			String course_counts = null;
			Integer study_time_total = 0;
			Integer course_count_total = 0;
			if(listChild!=null&&listChild.size()>0){
				for(YyUser child:listChild){
					if(user_names==null){
						user_names = "'" + child.getName() + "'";
					}else{
						user_names = user_names + ",'" + child.getName() + "'";
					}
					//查询学习时间
					YyUserStudy yyUserStudy = new YyUserStudy();
					yyUserStudy.setUser_id(child.getId());
					int study_time = yyuserstudyService.getUserStudyTime(yyUserStudy);
					child.setStudy_time(RequestHandler.secToTime(study_time));
					study_time_total = study_time_total + RequestHandler.secToTime(study_time);
					if(study_times==null){
						study_times = RequestHandler.secToTime(study_time) + "";
					}else{
						study_times = study_times + "," +RequestHandler.secToTime(study_time);
					}
					//计算完成课程数量
					YyUserCourse yyUserCourse = new YyUserCourse();
					yyUserCourse.setUser_id(child.getId());
					yyUserCourse.setStyle(0);
					List<YyUserCourse> list = yyusercourseService.getUserStudyList(yyUserCourse);
					Set<Long> set = new HashSet<Long>();
					int lessonCount = 0;
					int lessonCounting = 0;
					if(list!=null&&list.size()>0){
						for(YyUserCourse uc:list){
							set.add(uc.getCourse_classify_id());
						}
						Iterator<Long> it = set.iterator();
						while(it.hasNext()){
							Long lessonId = it.next();
							boolean b = true;
							for(YyUserCourse uc:list){
								if(lessonId.intValue()==uc.getCourse_classify_id().intValue()&&uc.getStudy_state()==0){
									b = false;
									break;
								}
							}
							if(b){
								lessonCount = lessonCount + 1;
							}
						}
						lessonCounting = list.size() - lessonCount;
						child.setLesson_count(list.size());
						course_count_total = course_count_total + list.size();
						if(course_counts==null){
							course_counts = "" + list.size();
						}else{
							course_counts = course_counts + "," + list.size();
						}
					}else{
						if(course_counts==null){
							course_counts = "" + 0;
						}else{
							course_counts = course_counts + "," + 0;
						}
						child.setLesson_count(0);
					}
					//计算完成课程数量结束
					child.setStudied_count(lessonCount);
					child.setStuding_count(lessonCounting);
				}
			}
			model.addAttribute("study_time_total", study_time_total);
			model.addAttribute("course_count_total", course_count_total);
			model.addAttribute("listChild", listChild);
			model.addAttribute("user_names", user_names);
			model.addAttribute("study_times", study_times);
			model.addAttribute("course_counts", course_counts);
		}catch(Exception e){
			logger.info(exception(e));
			e.printStackTrace();
		}
		model.addAttribute("nav", "myinfo");
		model.addAttribute("nav_h", "team");
		return  "/web/teamManage";
		
	}
	
	/**
	 * 分配课程
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fpCourse", method = RequestMethod.POST)
	public String fpCourse(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		Long lessonId = RequestHandler.getLong(request, "lessonId"); 
		String userId = RequestHandler.getString(request, "userId");
		String start_time = RequestHandler.getString(request, "start_time");
		String over_time = RequestHandler.getString(request, "over_time");
		JSONObject json = new JSONObject();
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(over_time)&&StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(userId)&&lessonId!=null){
				YyCourse yyCourse = new YyCourse();
				yyCourse.setClassify_id(lessonId);
				List<YyCourse> listCourse = yycourseService.getYyCourseBaseList(yyCourse);
				if(listCourse!=null&&listCourse.size()>0){
					String userIds[] = userId.split(",");
					for(String id:userIds){
						//查询是否已经分配过
						YyUserCourse yyUserCourse1 = new YyUserCourse();
						yyUserCourse1.setCourse_classify_id(lessonId);
//						yyUserCourse1.setStyle(0);
						yyUserCourse1.setUser_id(Long.valueOf(id));
						List<YyUserCourse> listUser = yyusercourseService.getUserStudyStateList(yyUserCourse1);
						if(listUser!=null&&listUser.size()>0){//已经分配过，只修改完成时间
							for(YyUserCourse uc:listUser){
								uc.setStart_time(sf.parse(start_time));
								uc.setOver_time(sf.parse(over_time));
								uc.setStyle(0);
								yyusercourseService.updateYyUserCourse(uc);
							}
						}else{//分配
							for(YyCourse course:listCourse){
								YyUserCourse yyUserCourse = new YyUserCourse();
								yyUserCourse.setCourse_classify_id(lessonId);
								yyUserCourse.setStyle(0);
								yyUserCourse.setUser_id(Long.valueOf(id));
								yyUserCourse.setStart_time(sf.parse(start_time));
								yyUserCourse.setOver_time(sf.parse(over_time));
								yyUserCourse.setCourse_id(course.getId());
								yyusercourseService.insertYyUserCourse(yyUserCourse);
							}
						}
					}
					json.put("c", 0);
					json.put("m", "分配成功");
				}else{
					json.put("c", -1);
					json.put("m", "课程暂无知识点，分配失败");
				}
			}else{
				json.put("c", -1);
				json.put("m", "参数不能为空");
			}
		}catch(Exception e){
			json.put("c", -1);
			json.put("m", "系统异常");
			e.printStackTrace();
			logger.info(exception(e));
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	
	
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request, HttpServletResponse response, Model model) {

		request.getSession().removeAttribute(SessionName.ADMIN_USER_NAME);
		request.getSession().removeAttribute(SessionName.ADMIN_USER_ID);
		request.getSession().removeAttribute(SessionName.ADMIN_USER);

		return "redirect:/web/toLogin";
	}
	
	/**
	 * 转向分配课程页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toFpCourse", method = RequestMethod.GET)
	public String toFpCourse(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long lessonId = RequestHandler.getLong(request, "lessonId"); 
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//查询下级人员
			YyUser yyUser1 = new YyUser();
			yyUser1.setParent_id(yyUser.getId());
			yyUser1.setState(1);
			List<YyUser> listAll = yyuserService.getYyUserBaseList(yyUser1);
			if(listAll!=null&&listAll.size()>0){
				YyUserCourse yyUserCourse1 = new YyUserCourse();
				yyUserCourse1.setCourse_classify_id(lessonId);
				yyUserCourse1.setStyle(0);
				yyUserCourse1.setUser_parent_id(yyUser.getId());
				List<YyUserCourse> listUser = yyusercourseService.getStudyUser(yyUserCourse1);
				if(listUser!=null&&listUser.size()>0){
					for(YyUser user:listAll){
						if(listUser!=null&&listUser.size()>0){
							for(YyUserCourse uc:listUser){
								if(user.getId().intValue()==uc.getUser_id().intValue()){
									user.setIs_select(1);
								}
							}
						}
					}
					model.addAttribute("listUser", listUser);
					model.addAttribute("userCourse", listUser.get(0));
				}
				
			}
			//
			YyCourseClassify yyCourseClassify = new YyCourseClassify();
			yyCourseClassify.setId(lessonId);
			yyCourseClassify = yycourseclassifyService.getYyCourseClassify(yyCourseClassify);
			
			
			model.addAttribute("yyCourseClassify", yyCourseClassify);
			model.addAttribute("list", listAll);
			model.addAttribute("lessonId", lessonId);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(exception(e));
		}
		return "/web/fenPeiCourse";
	}
	
	/**
	 * 转向选课界面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toXuanKe", method = RequestMethod.GET)
	public String toXuanKe(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long theme_id = RequestHandler.getLong(request, "theme_id"); 
		Long module_id = RequestHandler.getLong(request, "module_id"); 
		try{
			//查询主题下面数据
			if(theme_id==null){
				theme_id = 3L;
			}
			YyCourseClassify yyCourseClassifySub1 = new YyCourseClassify();
			yyCourseClassifySub1.setParent_id(theme_id);
			List<YyCourseClassify> listSub1 = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassifySub1);
			if(listSub1!=null&&listSub1.size()>0){
				if(module_id==null){
					for(YyCourseClassify cc:listSub1){
						//查询模块下面数据
						YyCourseClassify yyCourseClassifySub2 = new YyCourseClassify();
						yyCourseClassifySub2.setParent_id(cc.getId());
						List<YyCourseClassify> listSub2 = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassifySub2);
						//
						YyCourse yyCourse = new YyCourse();
						yyCourse.setModule_id(cc.getId());
						List<YyCourse> listCourse = yycourseService.getYyCourseTotal(yyCourse);
						
						if(listSub2!=null&&listSub2.size()>0){
							for(YyCourseClassify ccsub2:listSub2){
								if(listCourse!=null&&listCourse.size()>0){
									for(YyCourse course:listCourse){
										if(ccsub2.getId().intValue()==course.getClassify_id().intValue()){
											ccsub2.setPoint_total(course.getTotal_number());
											ccsub2.setPoint_time(RequestHandler.secToTime(course.getWhen_long()/course.getTotal_number()));
										}
									}
								}
							}
							
						}
						cc.setListSub(listSub2);
					}
				}else{
					//查询模块下面数据
					YyCourseClassify yyCourseClassifySub2 = new YyCourseClassify();
					yyCourseClassifySub2.setParent_id(module_id);
					List<YyCourseClassify> listSub2 = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassifySub2);
					//
					YyCourse yyCourse = new YyCourse();
					yyCourse.setModule_id(module_id);
					List<YyCourse> listCourse = yycourseService.getYyCourseTotal(yyCourse);
					
					if(listSub2!=null&&listSub2.size()>0){
						for(YyCourseClassify ccsub2:listSub2){
							if(listCourse!=null&&listCourse.size()>0){
								for(YyCourse course:listCourse){
									if(ccsub2.getId().intValue()==course.getClassify_id().intValue()){
										ccsub2.setPoint_total(course.getTotal_number());
										ccsub2.setPoint_time(course.getWhen_long()/course.getTotal_number());
									}
								}
							}
						}
						
					}
					model.addAttribute("listSub2", listSub2);
				}
			}
			model.addAttribute("listSub1", listSub1);
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//查询所有下级课程
			YyUserCourse yyUserCourse = new YyUserCourse();
			yyUserCourse.setStyle(0);
			yyUserCourse.setUser_parent_id(yyUser.getId());
			List<YyUserCourse> listUC = yyusercourseService.getYyUserCourseState(yyUserCourse);
			List<YyUserCourse> listW = new ArrayList<YyUserCourse>();//未开始
			List<YyUserCourse> listE = new ArrayList<YyUserCourse>();//已结束
			List<YyUserCourse> listJ = new ArrayList<YyUserCourse>();//进行中
			if(listUC!=null&&listUC.size()>0){
				for(YyUserCourse uc:listUC){
					//查询学习人员
					YyUserCourse yyUserCourse1 = new YyUserCourse();
					yyUserCourse1.setCourse_classify_id(uc.getCourse_classify_id());
					yyUserCourse1.setStyle(0);
					yyUserCourse1.setUser_parent_id(yyUser.getId());
					List<YyUserCourse> listUser = yyusercourseService.getStudyUser(yyUserCourse1);
					uc.setListUC(listUser);
					if(uc.getTotal()-uc.getStudy_state()==0){
						listE.add(uc);
					}else if(uc.getTotal()+uc.getStudy_state()==0){
						listW.add(uc);
					}else{
						listJ.add(uc);
					}
				}
			}
			model.addAttribute("listW", listW);
			model.addAttribute("listE", listE);
			model.addAttribute("listJ", listJ);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(exception(e));
		}
		model.addAttribute("nav", "coursecenter");
		model.addAttribute("nav_h", "xuanke");
		model.addAttribute("theme_id", theme_id);
		model.addAttribute("module_id", module_id);
		return "/web/xuanke";
	}
	
	/**
	 * 转向课程体系
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toCourseCenter", method = RequestMethod.GET)
	public String toCourseCenter(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		Long theme_id = RequestHandler.getLong(request, "theme_id"); 
		Long module_id = RequestHandler.getLong(request, "module_id"); 
		try{
			YyCourseClassify theme = new YyCourseClassify();
			theme.setParent_id(1L);
			theme.setSortColumn("a.sort_id asc");
			List<YyCourseClassify> listTheme = yycourseclassifyService.getYyCourseClassifyBaseList(theme);
			
			//查询主题下面数据
			if(theme_id==null){
				theme_id = listTheme.get(0).getId();
			}
			
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			YyCourseClassify yyCourseClassifySub1 = new YyCourseClassify();
			yyCourseClassifySub1.setParent_id(theme_id);
			yyCourseClassifySub1.setSortColumn("a.sort_id asc");
			List<YyCourseClassify> listSub1 = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassifySub1);
			if(listSub1!=null&&listSub1.size()>0){
				if(module_id==null){
					for(YyCourseClassify cc:listSub1){
						//查询模块下面数据
						YyCourseClassify yyCourseClassifySub2 = new YyCourseClassify();
						yyCourseClassifySub2.setParent_id(cc.getId());
						yyCourseClassifySub2.setSortColumn("a.sort_id asc");
						List<YyCourseClassify> listSub2 = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassifySub2);
						//
						YyCourse yyCourse = new YyCourse();
						yyCourse.setModule_id(cc.getId());
						List<YyCourse> listCourse = yycourseService.getYyCourseTotal(yyCourse);
						
						
						//判读是否已经加入课表
						YyUserCourse yyUserCourse1 = new YyUserCourse();
						yyUserCourse1.setUser_id(yyUser.getId());
						List<YyUserCourse> listUC = yyusercourseService.getDataByUserAndClassify(yyUserCourse1);
						
						
						if(listSub2!=null&&listSub2.size()>0){
							for(YyCourseClassify ccsub2:listSub2){
								if(listUC!=null&&listUC.size()>0){
									boolean b = false;
									Integer style = 0;
									for(YyUserCourse uc111:listUC){
										if(uc111.getCourse_classify_id().intValue()==ccsub2.getId().intValue()){
											b = true;
											style = uc111.getStyle();
											break;
										}
									}
									if(b){
										ccsub2.setIs_joined(1);
										ccsub2.setStyle(style);
									}else{
										ccsub2.setIs_joined(0);
									}
								}else{
									ccsub2.setIs_joined(0);
								}
								
								if(listCourse!=null&&listCourse.size()>0){
									for(YyCourse course:listCourse){
										if(ccsub2.getId().intValue()==course.getClassify_id().intValue()){
											ccsub2.setPoint_total(course.getTotal_number());
											ccsub2.setPoint_time(RequestHandler.secToTime(course.getWhen_long()/course.getTotal_number()));
										}
									}
								}
							}
							
						}
						cc.setListSub(listSub2);
					}
				}else{
					//查询模块下面数据
					YyCourseClassify yyCourseClassifySub2 = new YyCourseClassify();
					yyCourseClassifySub2.setParent_id(module_id);
					yyCourseClassifySub2.setSortColumn("a.sort_id asc");
					List<YyCourseClassify> listSub2 = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassifySub2);
					//
					YyCourse yyCourse = new YyCourse();
					yyCourse.setModule_id(module_id);
					List<YyCourse> listCourse = yycourseService.getYyCourseTotal(yyCourse);
					
					//判读是否已经加入课表
					YyUserCourse yyUserCourse1 = new YyUserCourse();
					yyUserCourse1.setUser_id(yyUser.getId());
					List<YyUserCourse> listUC = yyusercourseService.getDataByUserAndClassify(yyUserCourse1);
					
					if(listSub2!=null&&listSub2.size()>0){
						for(YyCourseClassify ccsub2:listSub2){
							if(listUC!=null&&listUC.size()>0){
								boolean b = false;
								Integer style = 0;
								for(YyUserCourse uc111:listUC){
									if(uc111.getCourse_classify_id().intValue()==ccsub2.getId().intValue()){
										b = true;
										style = uc111.getStyle();
										break;
									}
								}
								if(b){
									ccsub2.setIs_joined(1);
									ccsub2.setStyle(style);
								}else{
									ccsub2.setIs_joined(0);
								}
							}else{
								ccsub2.setIs_joined(0);
							}
							if(listCourse!=null&&listCourse.size()>0){
								for(YyCourse course:listCourse){
									if(ccsub2.getId().intValue()==course.getClassify_id().intValue()){
										ccsub2.setPoint_total(course.getTotal_number());
										ccsub2.setPoint_time(course.getWhen_long()/course.getTotal_number());
									}
								}
							}
						}
						
					}
					model.addAttribute("listSub2", listSub2);
				}
			}
			model.addAttribute("listSub1", listSub1);
			model.addAttribute("listTheme", listTheme);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(exception(e));
		}
		model.addAttribute("nav", "coursecenter");
		model.addAttribute("theme_id", theme_id);
		model.addAttribute("module_id", module_id);
		return  "/web/courseCenter";
	}
	
	/**
	 * 转向学习中心
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toLearnCenter", method = RequestMethod.GET)
	public String toLearnCenter(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			YyUser yyUser = (YyUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//轮播图
			List<YyCourseClassify> listBanner = webService.getBannerList();
			List<YyUserCourse> listB = new ArrayList<YyUserCourse>();//我的必修课
			List<YyUserCourse> listX = new ArrayList<YyUserCourse>();//我的选修课
			YyUserCourse yyUserCourse = new YyUserCourse();
			yyUserCourse.setUser_id(yyUser.getId());
			List<YyUserCourse> listMyCourse = yyusercourseService.getYyUserCourseParent(yyUserCourse);
			if(listMyCourse!=null&&listMyCourse.size()>0){
				YyUserCourse yyUserCourse1 = new YyUserCourse();
				yyUserCourse1.setUser_id(yyUser.getId());
				List<YyUserCourse> list2 = yyusercourseService.getUserStudyStateList(yyUserCourse1);
				for(YyUserCourse uc1:listMyCourse){
					YyCourse yyCourse = new YyCourse();
					yyCourse.setClassify_id(uc1.getCourse_classify_id());
					int count = yycourseService.getYyCourseListCount(yyCourse);//总课数
					int status = 0;//未开始
					int overDays = 0;
					uc1.setStudyProgress(0);
					if(list2!=null&&list2.size()>0){
						for(YyUserCourse uc2:list2){
							if(uc2.getCourse_classify_id().intValue()==uc1.getCourse_classify_id().intValue()){
								if(uc2.getStudy_state()==1){//已学完
//									count = count + uc2.getCount();
									uc1.setStudyProgress(uc2.getCount());
									status = 1;
								}
//								if(uc2.getStudy_state()==0){//未学完
//									count = count + uc2.getCount();
//								}
								if(uc1.getStyle().intValue()==0){//必修
									overDays = yyusercourseService.differentDays(new Date(), uc2.getOver_time());
									if(overDays<0){
										overDays = 0;
									}
								}
							}
						}
						uc1.setStudyCount(count);
						uc1.setOverDays(overDays);
						uc1.setStatus(status);
						if(uc1.getStyle().intValue()==0){//必修
							listB.add(uc1);
						}else{
							listX.add(uc1);
						}
					}
				}
			}
			
			model.addAttribute("listB", listB);
			model.addAttribute("listX", listX);
			//统计我的学习时长
			YyUserStudy yyUserStudy = new YyUserStudy();
			yyUserStudy.setUser_id(yyUser.getId());
			int study_time = yyuserstudyService.getUserStudyTime(yyUserStudy);
			//统计完成课程数量
			YyUserCourse yyUserCourse1 = new YyUserCourse();
			yyUserCourse1.setUser_id(yyUser.getId());
			yyUserCourse1.setStudy_state(1);
			int study_count = yyusercourseService.getYyUserCourseListCount(yyUserCourse1);
			//计算同级别同事
			List<YyUser> listSameLevelMsg = webService.getSameLevelMsg(yyUser);
			//内部排序
			ComparatorUser comparator=new ComparatorUser();
			Collections.sort(listSameLevelMsg, comparator);
			model.addAttribute("listSameLevelMsg", listSameLevelMsg);
			model.addAttribute("listBanner", listBanner);
			model.addAttribute("study_time", RequestHandler.secToTime(study_time));
			model.addAttribute("study_count", study_count);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(exception(e));
		}
		model.addAttribute("nav", "studycenter");
		return "/web/learnCenter";
	}
	
	/**
	 * 转向登录页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/web/login";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		String mobile = RequestHandler.getString(request, "mobile");
		String code = RequestHandler.getString(request, "code");
		JSONObject json = new JSONObject();
		try{
			if (!StringUtils.isNotBlank(mobile)) {
				json.put("c", -1);
				json.put("d", new JSONObject());
				json.put("m", "请输入手机号");
			}else if (!StringUtils.isNotBlank(code)) {
				json.put("c", -1);
				json.put("d", new JSONObject());
				json.put("m", "请输入手机验证码");
			}else{
				String code_session = CODEMAP.get(mobile);
				if(code.equals(code_session)){
					//验证超时
					Date d = TIMEMAP.get(mobile);
					Calendar c = Calendar.getInstance();
					c.setTime(d);
					c.add(Calendar.MINUTE, 1);
					if((new Date()).before(c.getTime())){
						YyUser yyUser = new YyUser();
						yyUser.setPhone(mobile);
						yyUser.setState(1);
						int count = yyuserService.getYyUserListCount(yyUser);
						if(count==1){
							yyUser = yyuserService.getYyUser(yyUser);
							
							//验证公司状态
							YyCompany yyCompany = new YyCompany();
							yyCompany.setId(yyUser.getCompany_id());
							yyCompany = yycompanyService.getYyCompany(yyCompany);
							if(yyCompany.getState().intValue()==1){
								CODEMAP.remove(mobile);
								TIMEMAP.remove(mobile);
								YyUser session_user = new YyUser();
								session_user.setId(yyUser.getId());
								session_user = yyuserService.getYyUser(session_user);
								//获取职能
								YyUserFunction yyUserFunction = new YyUserFunction();
								yyUserFunction.setUser_id(yyUser.getId());
								List<YyUserFunction> listUF = yyuserfunctionService.getYyUserFunctionBaseList(yyUserFunction);
								if(listUF!=null&&listUF.size()>0){
									String levelName = null;
									for(YyUserFunction uf:listUF){
										if(levelName==null){
											levelName = uf.getFunction_name();
										}else{
											levelName = levelName + "," + uf.getFunction_name();
										}
									}
									session_user.setLevelName(levelName);
								}
								// 正常
								request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, session_user.getName());
								request.getSession().setAttribute(SessionName.ADMIN_USER_ID, session_user.getId());
								request.getSession().setAttribute(SessionName.ADMIN_USER, session_user);
								
								json.put("c", 0);
								json.put("m", "登录成功");
							}else{
								json.put("c", -1);
								json.put("d", new JSONObject());
								json.put("m", "登录失败，贵公司服务已暂停");
							}
						}else if(count>1){
							json.put("c", -1);
							json.put("d", new JSONObject());
							json.put("m", "用户状态异常，请联系管理员");
						}else{
							json.put("c", -1);
							json.put("d", new JSONObject());
							json.put("m", "用户不存在，请先注册");
						}
					}else{
						json.put("c", -1);
						json.put("d", new JSONObject());
						json.put("m", "手机验证码已过期，请重新输入");
					}
				}else{
					json.put("c", -1);
					json.put("d", new JSONObject());
					json.put("m", "手机验证码错误，请重新输入");
				}
			}
		} catch (Exception e) {
			json.put("c", -1);
			json.put("d", new JSONObject());
			json.put("m", "发送失败，请重试");
			e.printStackTrace();
			logger.info(exception(e));
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(json.toString());
		return null;
	}
	
	/**
	 * 发送验证码
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/sendsms", method = RequestMethod.GET)
	public String sendCode(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception{
		String mobile = RequestHandler.getString(request, "mobile");
		String imgCode = RequestHandler.getString(request, "imgCode");
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.isNotBlank(mobile)) {
				json.put("c", -1);
				json.put("d", new JSONObject());
				json.put("m", "请输入手机号");
			}else if(!StringUtils.isNotBlank(imgCode)){
				json.put("c", -1);
				json.put("d", new JSONObject());
				json.put("m", "请输入图形验证码");
			}else{
				String token = (String)request.getSession().getAttribute(SessionName.TOKEN);
				if(imgCode.equals(token)){
					JSMSClient jSMSClient = new JSMSClient(ConfigConstants.DEV_SECRET, ConfigConstants.DEV_KEY);
					SMSPayload.Builder builder = SMSPayload.newBuilder();
					builder.setMobileNumber(mobile);
					builder.setTempId(1);
					builder.setTTL(60);
					Map<String,String> map = new HashMap<String,String>();
					String randomCode = webService.getRandomCode(4);
					map.put("code", randomCode);
					builder.setTempPara(map);
					SendSMSResult result = jSMSClient.sendTemplateSMS(builder.build());
					if(200==result.getResponseCode()){
						System.out.println("-----验证码-------->"+randomCode);
						CODEMAP.put(mobile, randomCode);
						TIMEMAP.put(mobile, new Date());
						json.put("c", 0);
						json.put("d", randomCode);
						json.put("m", "手机验证码已经发送，请注意查收");
					}else{
						json.put("c", -1);
						json.put("d", new JSONObject());
						json.put("m", "手机发送失败，请重试");
					}
				}else{
					json.put("c", -1);
					json.put("d", new JSONObject());
					json.put("m", "图形验证码错误，请重新输入");
				}
			}
		} catch (Exception e) {
			json.put("c", -1);
			json.put("d", new JSONObject());
			json.put("m", "手机发送失败，请重试");
			e.printStackTrace();
			logger.info(exception(e));
		}
		response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control","no-cache");
        response.getWriter().write(json.toString());
		return null;
	}
	
	@RequestMapping("/pcrimg")
	public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		switch (random.nextInt(5)) {
		case 0:
			cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
			break;
		case 1:
			cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		setResponseHeaders(response);
		String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		session.setAttribute(SessionName.TOKEN, token);
	}

	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
}
