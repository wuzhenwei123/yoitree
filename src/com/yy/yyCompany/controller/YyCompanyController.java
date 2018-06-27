package com.yy.yyCompany.controller;

import java.io.File;
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
import com.yy.yyCompanyLearnStyle.model.YyCompanyLearnStyle;
import com.yy.yyCompanyLearnStyle.service.YyCompanyLearnStyleService;
import com.yy.yyEmployees.model.YyEmployees;
import com.yy.yyEmployees.service.YyEmployeesService;
import com.yy.yyIndustry.model.YyIndustry;
import com.yy.yyIndustry.service.YyIndustryService;
import com.yy.yyLearningStyle.model.YyLearningStyle;
import com.yy.yyLearningStyle.service.YyLearningStyleService;
import com.yy.yyTurnover.model.YyTurnover;
import com.yy.yyTurnover.service.YyTurnoverService;
import com.yy.yyUser.model.YyUser;
import com.yy.yyUser.service.YyUserService;
import com.base.utils.ConfigConstants;
import com.base.utils.MatrixUtil;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-09 21:54:36
 */
@Controller
@RequestMapping("/yyCompany")
public class YyCompanyController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyCompanyController.class); //Logger
	
	@Autowired
	private YyCompanyService yycompanyService = null;
	@Autowired
	private YyIndustryService yyindustryService = null;
	@Autowired
	private YyLearningStyleService yylearningstyleService = null;
	@Autowired
	private YyEmployeesService yyemployeesService = null;
	@Autowired
	private YyTurnoverService yyturnoverService = null;
	@Autowired
	private YyCompanyLearnStyleService yycompanylearnstyleService = null;
	@Autowired
	private YyUserService yyuserService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
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
		model.addAttribute("listIndustry", listIndustry);
		model.addAttribute("listLearningStyle", listLearningStyle);
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("listTurnover", listTurnover);
		
		return "/yyCompany/yyCompanyIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
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
		
		model.addAttribute("listIndustry", listIndustry);
		model.addAttribute("listLearningStyle", listLearningStyle);
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("listTurnover", listTurnover);
		
		return "/yyCompany/yyCompanyAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
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
		YyCompany yycompany1 = requst2Bean(request,YyCompany.class);
		YyCompany yycompany = yycompanyService.getYyCompany(yycompany1);
		
		YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
		yyCompanyLearnStyle.setCompany_id(yycompany.getId());
		List<YyCompanyLearnStyle> listCompanyLearnStyle = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
		
		model.addAttribute("yycompany", yycompany);
		model.addAttribute("listIndustry", listIndustry);
		model.addAttribute("listLearningStyle", listLearningStyle);
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("listTurnover", listTurnover);
		model.addAttribute("listCompanyLearnStyle", listCompanyLearnStyle);
		return "/yyCompany/yyCompanyUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyCompanyList", method = RequestMethod.GET)
	public String getYyCompanyList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompany yycompany = requst2Bean(request,YyCompany.class);
			int yycompanyListCount = yycompanyService.getYyCompanyListCount(yycompany);
			ResponseList<YyCompany> yycompanyList = null;
			if ( yycompanyListCount > 0 )
			{
				yycompanyList = yycompanyService.getYyCompanyList(yycompany);
			} else
			{
				yycompanyList = new ResponseList<YyCompany>();
			}
			// 设置数据总数
			yycompanyList.setTotalResults(yycompanyListCount);
			
			writeSuccessMsg("ok", yycompanyList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyCompanyBaseList", method = RequestMethod.GET)
	public String getYyCompanyBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompany yycompany = requst2Bean(request,YyCompany.class);
			List<YyCompany> yycompanyList = yycompanyService.getYyCompanyBaseList(yycompany);
			writeSuccessMsg("ok", yycompanyList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCompany", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			String learningStyle_ids = RequestHandler.getString(request, "learningStyle_id");
			YyCompany yycompany = requst2Bean(request,YyCompany.class);
			//所属行业
			YyIndustry yyIndustry = new YyIndustry();
			yyIndustry.setId(yycompany.getIndustry_id());
			yyIndustry = yyindustryService.getYyIndustry(yyIndustry);
			if(yyIndustry!=null){
				yycompany.setIndustry_name(yyIndustry.getName());
			}
			//员工数量
			YyEmployees yyEmployees = new YyEmployees();
			yyEmployees.setId(yycompany.getEmployees_id());
			yyEmployees = yyemployeesService.getYyEmployees(yyEmployees);
			if(yyEmployees!=null){
				yycompany.setEmployees_name(yyEmployees.getName());
			}
			//营业额
			YyTurnover yyTurnover = new YyTurnover();
			yyTurnover.setId(yycompany.getTurnover_id());
			yyTurnover = yyturnoverService.getYyTurnover(yyTurnover);
			if(yyTurnover!=null){
				yycompany.setTurnover_name(yyTurnover.getName());
			}
			
			Long id = yycompanyService.insertYyCompany(yycompany);
			if(StringUtils.isNotBlank(learningStyle_ids)&&id>0){
				YyCompany yycompany1 = new YyCompany();
				Long code = 100000 + id ;
				yycompany1.setCode(code+"");
				yycompany1.setId(id);
				yycompanyService.updateYyCompany(yycompany1);
				String[] learningStyle_id = learningStyle_ids.split(",");
				for(String str:learningStyle_id){
					YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
					yyCompanyLearnStyle.setCompany_id(id);
					yyCompanyLearnStyle.setLearning_style_id(Integer.valueOf(str));
					yycompanylearnstyleService.insertYyCompanyLearnStyle(yyCompanyLearnStyle);
				}
				
			}
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyCompany", method = RequestMethod.POST)
	public String updateYyCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			String learningStyle_ids = RequestHandler.getString(request, "learningStyle_id");
			YyCompany yycompany = requst2Bean(request,YyCompany.class);
			
			YyCompany yycompany1 = new YyCompany();
			yycompany1.setId(yycompany.getId());
			yycompany1 = yycompanyService.getYyCompany(yycompany1);
			
			//所属行业
			YyIndustry yyIndustry = new YyIndustry();
			yyIndustry.setId(yycompany.getIndustry_id());
			yyIndustry = yyindustryService.getYyIndustry(yyIndustry);
			if(yyIndustry!=null){
				yycompany.setIndustry_name(yyIndustry.getName());
			}
			//员工数量
			YyEmployees yyEmployees = new YyEmployees();
			yyEmployees.setId(yycompany.getEmployees_id());
			yyEmployees = yyemployeesService.getYyEmployees(yyEmployees);
			if(yyEmployees!=null){
				yycompany.setEmployees_name(yyEmployees.getName());
			}
			//营业额
			YyTurnover yyTurnover = new YyTurnover();
			yyTurnover.setId(yycompany.getTurnover_id());
			yyTurnover = yyturnoverService.getYyTurnover(yyTurnover);
			if(yyTurnover!=null){
				yycompany.setTurnover_name(yyTurnover.getName());
			}
			if(!yycompany1.getName().equals(yycompany.getName())){
				//修改用户表对应的公司名
				YyUser yyuser1 = new YyUser();
				yyuser1.setCompany_id(yycompany.getId());
				yyuser1.setCompany_name(yycompany.getName());
				yyuserService.updateCompany_name(yyuser1);
			}
			int flag = yycompanyService.updateYyCompany(yycompany);
			if(flag>0&&StringUtils.isNotBlank(learningStyle_ids)){
				YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
				yyCompanyLearnStyle.setCompany_id(yycompany.getId());
				List<YyCompanyLearnStyle> list = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
				if(list!=null&&list.size()>0){
					for(YyCompanyLearnStyle cl:list){
						yycompanylearnstyleService.removeYyCompanyLearnStyle(cl);
					}
				}
				String[] learningStyle_id = learningStyle_ids.split(",");
				for(String str:learningStyle_id){
					YyCompanyLearnStyle yyCompanyLearnStyle1 = new YyCompanyLearnStyle();
					yyCompanyLearnStyle1.setCompany_id(yycompany.getId());
					yyCompanyLearnStyle1.setLearning_style_id(Integer.valueOf(str));
					yycompanylearnstyleService.insertYyCompanyLearnStyle(yyCompanyLearnStyle1);
				}
			}
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyCompany", method = RequestMethod.POST)
	public String removeYyCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompany yycompany = new YyCompany();
			Long id = RequestHandler.getLong(request, "id");
			yycompany.setId(id);

			int flag = yycompanyService.removeYyCompany(yycompany);
			if(flag>0){
				YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
				yyCompanyLearnStyle.setCompany_id(id);
				List<YyCompanyLearnStyle> list = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
				if(list!=null&&list.size()>0){
					for(YyCompanyLearnStyle cl:list){
						yycompanylearnstyleService.removeYyCompanyLearnStyle(cl);
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
	@RequestMapping(value = "/removeAllYyCompany", method = RequestMethod.POST)
	public String removeAllYyCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyCompany yyCompany = new YyCompany();
						yyCompany.setId(Long.valueOf(id));
						int flag = yycompanyService.removeYyCompany(yyCompany);
						if(flag>0){
							YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
							yyCompanyLearnStyle.setCompany_id(Long.valueOf(id));
							List<YyCompanyLearnStyle> list = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yyCompanyLearnStyle);
							if(list!=null&&list.size()>0){
								for(YyCompanyLearnStyle cl:list){
									yycompanylearnstyleService.removeYyCompanyLearnStyle(cl);
								}
							}
						}
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
	
	@RequestMapping(value = "/toqRCode", method = RequestMethod.GET)
	public String toqRCode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String fileToday = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
			String uuName = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
			Long id = RequestHandler.getLong(request, "id");
			//生成二维码
			String state = "c_" + id + "_admin";
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
		return "/yyCompany/qRCode";
	}
	
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/showCompany", method = RequestMethod.GET)
	public String showCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
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
		model.addAttribute("listIndustry", listIndustry);
		model.addAttribute("listLearningStyle", listLearningStyle);
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("listTurnover", listTurnover);
		
		return "/yyUser/showCompany";
	}
}
