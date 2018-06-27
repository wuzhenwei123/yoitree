package com.yy.yyUser.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yy.yyCompany.model.YyCompany;
import com.yy.yyCompany.service.YyCompanyService;
import com.yy.yyCourse.model.YyCourse;
import com.yy.yyCourse.service.YyCourseService;
import com.yy.yyCourseClassify.model.YyCourseClassify;
import com.yy.yyCourseClassify.service.YyCourseClassifyService;
import com.yy.yyCourseFunction.model.YyCourseFunction;
import com.yy.yyCourseFunction.service.YyCourseFunctionService;
import com.yy.yyFunction.model.YyFunction;
import com.yy.yyFunction.service.YyFunctionService;
import com.yy.yyUser.model.YyUser;
import com.yy.yyUser.service.YyUserService;
import com.yy.yyUserCourse.model.YyUserCourse;
import com.yy.yyUserCourse.service.YyUserCourseService;
import com.yy.yyUserStudy.model.YyUserStudy;
import com.yy.yyUserStudy.service.YyUserStudyService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.ConfigConstants;
import com.base.utils.MatrixUtil;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-10 14:03:43
 */
@Controller
@RequestMapping("/yyUser")
public class YyUserController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyUserController.class); //Logger
	
	@Autowired
	private YyUserService yyuserService = null;
	@Autowired
	private YyCompanyService yycompanyService = null;
	@Autowired
	private YyFunctionService yyfunctionService = null;
	@Autowired
	private YyCourseFunctionService yycoursefunctionService = null;
	@Autowired
	private YyCourseService yycourseService = null;
	@Autowired
	private YyUserCourseService yyusercourseService = null;
	@Autowired
	private YyCourseClassifyService yycourseclassifyService = null;
	@Autowired
	private YyUserStudyService yyuserstudyService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUser/yyUserIndex";
	}
	@RequestMapping(value = "/toReg", method = RequestMethod.GET)
	public String toReg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String openId = RequestHandler.getString(request, "openId");
		Long company_id = RequestHandler.getLong(request, "company_id");
		Long parent_id = RequestHandler.getLong(request, "parent_id");
		JSONArray arrayAll = new JSONArray();
		try{
			if(company_id!=null){
				YyCompany yyCompany = new YyCompany();
				yyCompany.setId(company_id);
				yyCompany = yycompanyService.getYyCompany(yyCompany);
				model.addAttribute("yyCompany", yyCompany);
			}
			if(parent_id!=null){
				YyUser yyuser = new YyUser();
				yyuser.setId(parent_id);
				yyuser = yyuserService.getYyUser(yyuser);
				model.addAttribute("yyUser", yyuser);
			}
			//获取职能
			YyFunction yyFunction = new YyFunction();
			yyFunction.setParent_id(1);
			List<YyFunction> listF = yyfunctionService.getYyFunctionBaseList(yyFunction);
			if(listF!=null&&listF.size()>0){
				for(YyFunction f:listF){
					JSONObject jsonP = new JSONObject();
					jsonP.put("label", f.getName());
					jsonP.put("value", f.getId());
					JSONArray arrayP = new JSONArray();
					YyFunction yyFunction1 = new YyFunction();
					yyFunction1.setParent_id(f.getId());
					List<YyFunction> listF1 = yyfunctionService.getYyFunctionBaseList(yyFunction1);
					if(listF1!=null&&listF1.size()>0){
						for(YyFunction f1:listF1){
							JSONObject jsonC = new JSONObject();
							jsonC.put("label", f1.getName());
							jsonC.put("value", f1.getId());
							arrayP.add(jsonC);
						}
					}
					jsonP.put("children", arrayP);
					arrayAll.add(jsonP);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("openId", openId);
		model.addAttribute("company_id", company_id);
		model.addAttribute("parent_id", parent_id);
		model.addAttribute("arrayAll", arrayAll);
		return "/wx/reg";
	}
	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public String showUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Long company_id = RequestHandler.getLong(request, "company_id");
		Long user_id = RequestHandler.getLong(request, "user_id");
		model.addAttribute("company_id", company_id);
		model.addAttribute("user_id", user_id);
		return "/yyUser/showUser";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUser/yyUserAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyUser yyuser1 = requst2Bean(request,YyUser.class);
		YyUser yyuser = yyuserService.getYyUser(yyuser1);
		model.addAttribute("yyuser", yyuser);
		return "/yyUser/yyUserUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyUserList", method = RequestMethod.GET)
	public String getYyUserList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUser yyuser = requst2Bean(request,YyUser.class);
			int yyuserListCount = yyuserService.getYyUserListCount(yyuser);
			ResponseList<YyUser> yyuserList = null;
			if ( yyuserListCount > 0 )
			{
				yyuserList = yyuserService.getYyUserList(yyuser);
			} else
			{
				yyuserList = new ResponseList<YyUser>();
			}
			// 设置数据总数
			yyuserList.setTotalResults(yyuserListCount);
			
			writeSuccessMsg("ok", yyuserList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyUserBaseList", method = RequestMethod.GET)
	public String getYyUserBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUser yyuser = requst2Bean(request,YyUser.class);
			List<YyUser> yyuserList = yyuserService.getYyUserBaseList(yyuser);
			writeSuccessMsg("ok", yyuserList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyUser yyuser = requst2Bean(request,YyUser.class);
			yyuser.setPassword(MD5.getMD5ofStr(yyuser.getPassword()));
			yyuserService.insertYyUser(yyuser);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyUser", method = RequestMethod.POST)
	public String updateYyUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			
			YyUser yyuser = requst2Bean(request,YyUser.class);
			
			YyUser yyuser1 = new YyUser();
			yyuser1.setId(yyuser.getId());
			yyuser1 = yyuserService.getYyUser(yyuser1);
			if(yyuser1!=null&&StringUtils.isNotBlank(yyuser.getPassword())&&!yyuser1.getPassword().equals(yyuser.getPassword())){
				yyuser.setPassword(MD5.getMD5ofStr(yyuser.getPassword()));
			}
			if(StringUtils.isNotBlank(yyuser1.getName())&&!yyuser1.getName().equals(yyuser.getName())){
				//修改直属上级名称
				YyUser yyUser = new YyUser();
				yyUser.setParent_id(yyuser.getId());
				yyUser.setParent_name(yyuser.getName());
				yyuserService.updateParent_name(yyUser);
			}
			yyuserService.updateYyUser(yyuser);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyUser", method = RequestMethod.POST)
	public String removeYyUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUser yyuser = new YyUser();
			Long id = RequestHandler.getLong(request, "id");
			yyuser.setId(id);

			yyuserService.removeYyUser(yyuser);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyUser", method = RequestMethod.POST)
	public String removeAllYyUser(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyUser yyUser = new YyUser();
						yyUser.setId(Long.valueOf(id));
						yyuserService.removeYyUser(yyUser);
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/checkUserLoginName", method = RequestMethod.GET)
	public String checkUserLoginName(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String login_name = RequestHandler.getString(request, "login_name");
			Long id = RequestHandler.getLong(request, "id");
			YyUser yyUser = new YyUser();
			yyUser.setUser_id(id);
			yyUser.setLogin_name(login_name);
			int count = yyuserService.getYyUserListCount(yyUser);
			if(count==0){
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("登录名已存在", null, response);
			}
			
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/checkUserPhone", method = RequestMethod.GET)
	public String checkUserPhone(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String phone = RequestHandler.getString(request, "phone");
			Long id = RequestHandler.getLong(request, "id");
			YyUser yyUser = new YyUser();
			yyUser.setUser_id(id);
			yyUser.setPhone(phone);
			int count = yyuserService.getYyUserListCount(yyUser);
			if(count==0){
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("手机号已存在", null, response);
			}
			
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/toqRCode", method = RequestMethod.GET)
	public String toqRCode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String fileToday = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
			String uuName = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
			Long parent_id = RequestHandler.getLong(request, "parent_id");
			
			YyUser yyUser = new YyUser();
			yyUser.setId(parent_id);
			yyUser = yyuserService.getYyUser(yyUser);
			//生成二维码
			String state = "a_" + yyUser.getCompany_id() + "_" + parent_id;
			String url = ConfigConstants.URL_PATH + "/redirect_uri.jsp?state="+state;
			//二维码地址
			// 获取保存文件的最终路径
			String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT ;
			String newFileName =  File.separator + fileToday + File.separator + uuName + ".jpg";// 新路径
			if(!new File(saveFilePath+File.separator + fileToday + File.separator).exists()){
				new File(saveFilePath+File.separator + fileToday + File.separator).mkdirs();
			}
			
	        String format = "jpg";  
	        File outputFile = new File(saveFilePath + newFileName);  
	        MatrixUtil.writeToFile(MatrixUtil.toQRCodeMatrix(url, null, null), format, outputFile);  
	        String result = MatrixUtil.decode(outputFile);
			model.addAttribute("imgUrl",  newFileName.replace("\\", "/"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "/yyUser/qRCode";
	}
	
	
	
	@RequestMapping(value = "/function_set", method = RequestMethod.GET)
	public String function_set(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Long id = RequestHandler.getLong(request, "id");
		Long userId = RequestHandler.getLong(request, "userId");
		
		JSONArray array = new JSONArray(); 
		try{
			
			YyFunction yyFunction = new YyFunction();
			List<YyFunction> list = yyfunctionService.getYyFunctionBaseList(yyFunction);
			
			if(list!=null&&list.size()>0){
				for (YyFunction cc : list) { 
					JSONObject jsonObject = new JSONObject(); 
					jsonObject.put("pId", cc.getParent_id());
					jsonObject.put("id", cc.getId());
					jsonObject.put("name", cc.getName());
					jsonObject.put("open", true);// 默认打开
					boolean b = false;
					if(id!=null&&cc.getId().intValue()==id.intValue()){
						b = true;
					}
					if(b){
						jsonObject.put("checked", true);// 默认不选上
					}else{
						jsonObject.put("checked", false);// 默认不选上
					}
					array.add(jsonObject);
	            }  
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("nodes", array.toString());
		return "/yyUser/function_set";
	}
	
	@RequestMapping(value = "/updateFunction", method = RequestMethod.GET)
	public String updateFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer function_id = RequestHandler.getInteger(request, "function_id");
		Long userId = RequestHandler.getLong(request, "userId");
		
		try{
			YyUser yyUser = new YyUser();
			yyUser.setId(userId);
			yyUser.setJob_info(function_id);
			Integer flag = yyuserService.updateYyUser(yyUser);
			if(flag>0){
				
//				//匹配课程
//				YyCourse yyCourse = new YyCourse();
//				yyCourse.setFunction_id(function_id);
//				List<YyCourse> listC = yycourseService.getYyCourseListByFunction(yyCourse);
//				if(listC!=null&&listC.size()>0){
//					for(YyCourse course:listC){
//						YyUserCourse yyUserCourse = new YyUserCourse();
//						yyUserCourse.setUser_id(userId);
//						yyUserCourse.setCourse_id(course.getId());
//						yyusercourseService.insertYyUserCourse(yyUserCourse);
//					}
//				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 已分配的课程详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toShowCourse", method = RequestMethod.GET)
	public String toShowCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Long id = RequestHandler.getLong(request, "id");
		
		JSONArray array = new JSONArray(); 
		try{
			
			YyCourseClassify yyCourseClassify = new YyCourseClassify();
			List<YyCourseClassify> list = yycourseclassifyService.getYyCourseClassifyBaseList(yyCourseClassify);
			
			
			YyUserCourse yyUserCourse = new YyUserCourse();
			yyUserCourse.setUser_id(id);
			List<YyUserCourse> listSel = yyusercourseService.getUserStudyList(yyUserCourse);
			
			if(list!=null&&list.size()>0){
				for (YyCourseClassify cc : list) { 
					JSONObject jsonObject = new JSONObject(); 
					jsonObject.put("pId", cc.getParent_id());
					jsonObject.put("id", cc.getId());
					jsonObject.put("name", cc.getName());
					jsonObject.put("open", true);// 默认打开
					boolean b = false;
					if(listSel!=null&&listSel.size()>0){
						for(YyUserCourse cf:listSel){
							if(cc.getId().intValue()==cf.getCourse_classify_id().intValue()){
								b = true;
								break;
							}
						}
					}
					if(b){
						jsonObject.put("checked", true);// 默认不选上
					}else{
						jsonObject.put("checked", false);// 默认不选上
					}
					array.add(jsonObject);
	            }  
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("user_id", id);
		model.addAttribute("nodes", array.toString());
		return "/yyUser/showCourse";
	}
	
	@RequestMapping(value = "/updateUserStudy", method = RequestMethod.POST)
	public String updateUserStudy(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			String ck = RequestHandler.getString(request, "ck");
			Long lession_id = RequestHandler.getLong(request, "lession_id");
			Long user_id = RequestHandler.getLong(request, "user_id");
			Integer level = RequestHandler.getInteger(request, "level");
			Date over_time =  RequestHandler.getDate(request, "over_time");
			if("add".equals(ck)){
				YyCourse yyCourse = new YyCourse();
				if(level==2){
					yyCourse.setModule_id(lession_id);
				}else if(level==1){
					yyCourse.setTheme_id(lession_id);
				}else if(level==3){
					yyCourse.setClassify_id(lession_id);
				}
				List<YyCourse> list = yycourseService.getYyCourseBaseList(yyCourse);
				if(list!=null&&list.size()>0){
					for(YyCourse c:list){
						YyUserCourse yyUserCourse = new YyUserCourse();
						yyUserCourse.setUser_id(user_id);
						yyUserCourse.setCourse_classify_id(c.getClassify_id());
						yyUserCourse.setCourse_id(c.getId());
						yyUserCourse.setStatus(0);
						yyUserCourse.setOver_time(over_time);
						yyusercourseService.insertYyUserCourse(yyUserCourse);
					}
				}
			}else if("move".equals(ck)){
				YyUserCourse yyUserCourse = new YyUserCourse();
				yyUserCourse.setUser_id(user_id);
				if(level==1){
					yyUserCourse.setThemeId(lession_id);
				}else if(level==2){
					yyUserCourse.setModuleId(lession_id);
				}else if(level==3){
					yyUserCourse.setCourse_classify_id(lession_id);
				}
				List<YyUserCourse> list = yyusercourseService.getUserStudyListAll(yyUserCourse);
				for(YyUserCourse uc:list){
					yyusercourseService.removeYyUserCourse(uc);
					//移除学习记录
					YyUserStudy yyUserStudy = new YyUserStudy();
					yyUserStudy.setUser_id(user_id);
					yyUserStudy.setLesson_id(lession_id);
					List<YyUserStudy> listStudy = yyuserstudyService.getYyUserStudyBaseList(yyUserStudy);
					if(listStudy!=null&&listStudy.size()>0){
						for(YyUserStudy us:listStudy){
							yyuserstudyService.removeYyUserStudy(us);
						}
					}
				}
			}
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
}
