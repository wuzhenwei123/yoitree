package com.yy.yyCourseFunction.controller;

import java.util.Date;

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

import com.yy.yyCourseFunction.model.YyCourseFunction;
import com.yy.yyCourseFunction.service.YyCourseFunctionService;
import com.yy.yyFunction.model.YyFunction;
import com.yy.yyFunction.service.YyFunctionService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-22 13:22:53
 */
@Controller
@RequestMapping("/yyCourseFunction")
public class YyCourseFunctionController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyCourseFunctionController.class); //Logger
	
	@Autowired
	private YyCourseFunctionService yycoursefunctionService = null;
	@Autowired
	private YyFunctionService yyfunctionService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourseFunction/yyCourseFunctionIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourseFunction/yyCourseFunctionAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCourseFunction yycoursefunction1 = requst2Bean(request,YyCourseFunction.class);
		YyCourseFunction yycoursefunction = yycoursefunctionService.getYyCourseFunction(yycoursefunction1);
		model.addAttribute("yycoursefunction", yycoursefunction);
		return "/yyCourseFunction/yyCourseFunctionUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyCourseFunctionList", method = RequestMethod.GET)
	public String getYyCourseFunctionList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseFunction yycoursefunction = requst2Bean(request,YyCourseFunction.class);
			int yycoursefunctionListCount = yycoursefunctionService.getYyCourseFunctionListCount(yycoursefunction);
			ResponseList<YyCourseFunction> yycoursefunctionList = null;
			if ( yycoursefunctionListCount > 0 )
			{
				yycoursefunctionList = yycoursefunctionService.getYyCourseFunctionList(yycoursefunction);
			} else
			{
				yycoursefunctionList = new ResponseList<YyCourseFunction>();
			}
			// 设置数据总数
			yycoursefunctionList.setTotalResults(yycoursefunctionListCount);
			
			writeSuccessMsg("ok", yycoursefunctionList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyCourseFunctionBaseList", method = RequestMethod.GET)
	public String getYyCourseFunctionBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseFunction yycoursefunction = requst2Bean(request,YyCourseFunction.class);
			List<YyCourseFunction> yycoursefunctionList = yycoursefunctionService.getYyCourseFunctionBaseList(yycoursefunction);
			writeSuccessMsg("ok", yycoursefunctionList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCourseFunction", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyCourseFunction yycoursefunction = requst2Bean(request,YyCourseFunction.class);
			yycoursefunctionService.insertYyCourseFunction(yycoursefunction);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyCourseFunction", method = RequestMethod.POST)
	public String updateYyCourseFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseFunction yycoursefunction = requst2Bean(request,YyCourseFunction.class);
			String ck = RequestHandler.getString(request, "ck");
			//判读是否是子节点
			YyFunction yyFunction = new YyFunction();
			yyFunction.setParent_id(yycoursefunction.getFunction_id());
			int countP = yyfunctionService.getYyFunctionListCount(yyFunction);
			if("add".equals(ck)){
				if(countP==0){
					int count = yycoursefunctionService.getYyCourseFunctionListCount(yycoursefunction);
					if(count==0){
						yycoursefunctionService.insertYyCourseFunction(yycoursefunction);
					}else{
						List<YyCourseFunction> list = yycoursefunctionService.getYyCourseFunctionBaseList(yycoursefunction);
						for(YyCourseFunction cf:list){
							yycoursefunctionService.removeYyCourseFunction(cf);
						}
						yycoursefunctionService.insertYyCourseFunction(yycoursefunction);
					}
				}else{
					List<YyFunction> listF = yyfunctionService.getYyFunctionBaseList(yyFunction);
					for(YyFunction f:listF){
						YyCourseFunction yyCourseFunction = new YyCourseFunction();
						yyCourseFunction.setCourse_classify_id(yycoursefunction.getCourse_classify_id());
						yyCourseFunction.setFunction_id(f.getId());
						List<YyCourseFunction> list = yycoursefunctionService.getYyCourseFunctionBaseList(yyCourseFunction);
						for(YyCourseFunction cf:list){
							yycoursefunctionService.removeYyCourseFunction(cf);
						}
						yycoursefunctionService.insertYyCourseFunction(yyCourseFunction);
					}
				}
			}else if("move".equals(ck)){
				if(countP==0){
					List<YyCourseFunction> list = yycoursefunctionService.getYyCourseFunctionBaseList(yycoursefunction);
					for(YyCourseFunction cf:list){
						yycoursefunctionService.removeYyCourseFunction(cf);
					}
				}else{
					List<YyFunction> listF = yyfunctionService.getYyFunctionBaseList(yyFunction);
					for(YyFunction f:listF){
						YyCourseFunction yyCourseFunction = new YyCourseFunction();
						yyCourseFunction.setCourse_classify_id(yycoursefunction.getCourse_classify_id());
						yyCourseFunction.setFunction_id(f.getId());
						List<YyCourseFunction> list = yycoursefunctionService.getYyCourseFunctionBaseList(yyCourseFunction);
						for(YyCourseFunction cf:list){
							yycoursefunctionService.removeYyCourseFunction(cf);
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
	
	@RequestMapping(value = "/removeYyCourseFunction", method = RequestMethod.POST)
	public String removeYyCourseFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseFunction yycoursefunction = new YyCourseFunction();
			Long id = RequestHandler.getLong(request, "id");
			yycoursefunction.setId(id);

			yycoursefunctionService.removeYyCourseFunction(yycoursefunction);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyCourseFunction", method = RequestMethod.POST)
	public String removeAllYyCourseFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyCourseFunction yyCourseFunction = new YyCourseFunction();
						yyCourseFunction.setId(Long.valueOf(id));
						yycoursefunctionService.removeYyCourseFunction(yyCourseFunction);
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
}
