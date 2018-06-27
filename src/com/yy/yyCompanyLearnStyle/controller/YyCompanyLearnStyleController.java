package com.yy.yyCompanyLearnStyle.controller;

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

import com.yy.yyCompanyLearnStyle.model.YyCompanyLearnStyle;
import com.yy.yyCompanyLearnStyle.service.YyCompanyLearnStyleService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-09 21:55:38
 */
@Controller
@RequestMapping("/yyCompanyLearnStyle")
public class YyCompanyLearnStyleController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyCompanyLearnStyleController.class); //Logger
	
	@Autowired
	private YyCompanyLearnStyleService yycompanylearnstyleService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCompanyLearnStyle/yyCompanyLearnStyleIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCompanyLearnStyle/yyCompanyLearnStyleAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCompanyLearnStyle yycompanylearnstyle1 = requst2Bean(request,YyCompanyLearnStyle.class);
		YyCompanyLearnStyle yycompanylearnstyle = yycompanylearnstyleService.getYyCompanyLearnStyle(yycompanylearnstyle1);
		model.addAttribute("yycompanylearnstyle", yycompanylearnstyle);
		return "/yyCompanyLearnStyle/yyCompanyLearnStyleUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyCompanyLearnStyleList", method = RequestMethod.GET)
	public String getYyCompanyLearnStyleList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompanyLearnStyle yycompanylearnstyle = requst2Bean(request,YyCompanyLearnStyle.class);
			int yycompanylearnstyleListCount = yycompanylearnstyleService.getYyCompanyLearnStyleListCount(yycompanylearnstyle);
			ResponseList<YyCompanyLearnStyle> yycompanylearnstyleList = null;
			if ( yycompanylearnstyleListCount > 0 )
			{
				yycompanylearnstyleList = yycompanylearnstyleService.getYyCompanyLearnStyleList(yycompanylearnstyle);
			} else
			{
				yycompanylearnstyleList = new ResponseList<YyCompanyLearnStyle>();
			}
			// 设置数据总数
			yycompanylearnstyleList.setTotalResults(yycompanylearnstyleListCount);
			
			writeSuccessMsg("ok", yycompanylearnstyleList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyCompanyLearnStyleBaseList", method = RequestMethod.GET)
	public String getYyCompanyLearnStyleBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompanyLearnStyle yycompanylearnstyle = requst2Bean(request,YyCompanyLearnStyle.class);
			List<YyCompanyLearnStyle> yycompanylearnstyleList = yycompanylearnstyleService.getYyCompanyLearnStyleBaseList(yycompanylearnstyle);
			writeSuccessMsg("ok", yycompanylearnstyleList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCompanyLearnStyle", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyCompanyLearnStyle yycompanylearnstyle = requst2Bean(request,YyCompanyLearnStyle.class);
			yycompanylearnstyleService.insertYyCompanyLearnStyle(yycompanylearnstyle);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyCompanyLearnStyle", method = RequestMethod.POST)
	public String updateYyCompanyLearnStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompanyLearnStyle yycompanylearnstyle = requst2Bean(request,YyCompanyLearnStyle.class);
			yycompanylearnstyleService.updateYyCompanyLearnStyle(yycompanylearnstyle);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyCompanyLearnStyle", method = RequestMethod.POST)
	public String removeYyCompanyLearnStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCompanyLearnStyle yycompanylearnstyle = new YyCompanyLearnStyle();
			Long id = RequestHandler.getLong(request, "id");
			yycompanylearnstyle.setId(id);

			yycompanylearnstyleService.removeYyCompanyLearnStyle(yycompanylearnstyle);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyCompanyLearnStyle", method = RequestMethod.POST)
	public String removeAllYyCompanyLearnStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyCompanyLearnStyle yyCompanyLearnStyle = new YyCompanyLearnStyle();
						yyCompanyLearnStyle.setId(Long.valueOf(id));
						yycompanylearnstyleService.removeYyCompanyLearnStyle(yyCompanyLearnStyle);
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
