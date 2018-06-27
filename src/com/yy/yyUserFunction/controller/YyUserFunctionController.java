package com.yy.yyUserFunction.controller;

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

import com.yy.yyUserFunction.model.YyUserFunction;
import com.yy.yyUserFunction.service.YyUserFunctionService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-11-13 10:23:22
 */
@Controller
@RequestMapping("/yyUserFunction")
public class YyUserFunctionController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyUserFunctionController.class); //Logger
	
	@Autowired
	private YyUserFunctionService yyuserfunctionService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUserFunction/yyUserFunctionIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUserFunction/yyUserFunctionAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyUserFunction yyuserfunction1 = requst2Bean(request,YyUserFunction.class);
		YyUserFunction yyuserfunction = yyuserfunctionService.getYyUserFunction(yyuserfunction1);
		model.addAttribute("yyuserfunction", yyuserfunction);
		return "/yyUserFunction/yyUserFunctionUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyUserFunctionList", method = RequestMethod.GET)
	public String getYyUserFunctionList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserFunction yyuserfunction = requst2Bean(request,YyUserFunction.class);
			int yyuserfunctionListCount = yyuserfunctionService.getYyUserFunctionListCount(yyuserfunction);
			ResponseList<YyUserFunction> yyuserfunctionList = null;
			if ( yyuserfunctionListCount > 0 )
			{
				yyuserfunctionList = yyuserfunctionService.getYyUserFunctionList(yyuserfunction);
			} else
			{
				yyuserfunctionList = new ResponseList<YyUserFunction>();
			}
			// 设置数据总数
			yyuserfunctionList.setTotalResults(yyuserfunctionListCount);
			
			writeSuccessMsg("ok", yyuserfunctionList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyUserFunctionBaseList", method = RequestMethod.GET)
	public String getYyUserFunctionBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserFunction yyuserfunction = requst2Bean(request,YyUserFunction.class);
			List<YyUserFunction> yyuserfunctionList = yyuserfunctionService.getYyUserFunctionBaseList(yyuserfunction);
			writeSuccessMsg("ok", yyuserfunctionList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyUserFunction", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyUserFunction yyuserfunction = requst2Bean(request,YyUserFunction.class);
			yyuserfunctionService.insertYyUserFunction(yyuserfunction);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyUserFunction", method = RequestMethod.POST)
	public String updateYyUserFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserFunction yyuserfunction = requst2Bean(request,YyUserFunction.class);
			yyuserfunctionService.updateYyUserFunction(yyuserfunction);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyUserFunction", method = RequestMethod.POST)
	public String removeYyUserFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserFunction yyuserfunction = new YyUserFunction();
			Integer id = RequestHandler.getInteger(request, "id");
			yyuserfunction.setId(id);

			yyuserfunctionService.removeYyUserFunction(yyuserfunction);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyUserFunction", method = RequestMethod.POST)
	public String removeAllYyUserFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyUserFunction yyUserFunction = new YyUserFunction();
						yyUserFunction.setId(Integer.valueOf(id));
						yyuserfunctionService.removeYyUserFunction(yyUserFunction);
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
