package com.yy.yyIndustry.controller;

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

import com.yy.yyIndustry.model.YyIndustry;
import com.yy.yyIndustry.service.YyIndustryService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-09 20:04:01
 */
@Controller
@RequestMapping("/yyIndustry")
public class YyIndustryController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyIndustryController.class); //Logger
	
	@Autowired
	private YyIndustryService yyindustryService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyIndustry/yyIndustryIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyIndustry/yyIndustryAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyIndustry yyindustry1 = requst2Bean(request,YyIndustry.class);
		YyIndustry yyindustry = yyindustryService.getYyIndustry(yyindustry1);
		model.addAttribute("yyindustry", yyindustry);
		return "/yyIndustry/yyIndustryUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyIndustryList", method = RequestMethod.GET)
	public String getYyIndustryList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyIndustry yyindustry = requst2Bean(request,YyIndustry.class);
			int yyindustryListCount = yyindustryService.getYyIndustryListCount(yyindustry);
			ResponseList<YyIndustry> yyindustryList = null;
			if ( yyindustryListCount > 0 )
			{
				yyindustryList = yyindustryService.getYyIndustryList(yyindustry);
			} else
			{
				yyindustryList = new ResponseList<YyIndustry>();
			}
			// 设置数据总数
			yyindustryList.setTotalResults(yyindustryListCount);
			
			writeSuccessMsg("ok", yyindustryList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyIndustryBaseList", method = RequestMethod.GET)
	public String getYyIndustryBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyIndustry yyindustry = requst2Bean(request,YyIndustry.class);
			List<YyIndustry> yyindustryList = yyindustryService.getYyIndustryBaseList(yyindustry);
			writeSuccessMsg("ok", yyindustryList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyIndustry", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyIndustry yyindustry = requst2Bean(request,YyIndustry.class);
			yyindustryService.insertYyIndustry(yyindustry);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyIndustry", method = RequestMethod.POST)
	public String updateYyIndustry(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyIndustry yyindustry = requst2Bean(request,YyIndustry.class);
			yyindustryService.updateYyIndustry(yyindustry);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyIndustry", method = RequestMethod.POST)
	public String removeYyIndustry(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyIndustry yyindustry = new YyIndustry();
			Integer id = RequestHandler.getInteger(request, "id");
			yyindustry.setId(id);

			yyindustryService.removeYyIndustry(yyindustry);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyIndustry", method = RequestMethod.POST)
	public String removeAllYyIndustry(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyIndustry yyIndustry = new YyIndustry();
						yyIndustry.setId(Integer.valueOf(id));
						yyindustryService.removeYyIndustry(yyIndustry);
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
