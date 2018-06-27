package com.yy.yyLearningStyle.controller;

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

import com.yy.yyLearningStyle.model.YyLearningStyle;
import com.yy.yyLearningStyle.service.YyLearningStyleService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-09 20:51:40
 */
@Controller
@RequestMapping("/yyLearningStyle")
public class YyLearningStyleController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyLearningStyleController.class); //Logger
	
	@Autowired
	private YyLearningStyleService yylearningstyleService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyLearningStyle/yyLearningStyleIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyLearningStyle/yyLearningStyleAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyLearningStyle yylearningstyle1 = requst2Bean(request,YyLearningStyle.class);
		YyLearningStyle yylearningstyle = yylearningstyleService.getYyLearningStyle(yylearningstyle1);
		model.addAttribute("yylearningstyle", yylearningstyle);
		return "/yyLearningStyle/yyLearningStyleUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyLearningStyleList", method = RequestMethod.GET)
	public String getYyLearningStyleList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyLearningStyle yylearningstyle = requst2Bean(request,YyLearningStyle.class);
			int yylearningstyleListCount = yylearningstyleService.getYyLearningStyleListCount(yylearningstyle);
			ResponseList<YyLearningStyle> yylearningstyleList = null;
			if ( yylearningstyleListCount > 0 )
			{
				yylearningstyleList = yylearningstyleService.getYyLearningStyleList(yylearningstyle);
			} else
			{
				yylearningstyleList = new ResponseList<YyLearningStyle>();
			}
			// 设置数据总数
			yylearningstyleList.setTotalResults(yylearningstyleListCount);
			
			writeSuccessMsg("ok", yylearningstyleList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyLearningStyleBaseList", method = RequestMethod.GET)
	public String getYyLearningStyleBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyLearningStyle yylearningstyle = requst2Bean(request,YyLearningStyle.class);
			List<YyLearningStyle> yylearningstyleList = yylearningstyleService.getYyLearningStyleBaseList(yylearningstyle);
			writeSuccessMsg("ok", yylearningstyleList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyLearningStyle", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyLearningStyle yylearningstyle = requst2Bean(request,YyLearningStyle.class);
			yylearningstyleService.insertYyLearningStyle(yylearningstyle);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyLearningStyle", method = RequestMethod.POST)
	public String updateYyLearningStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyLearningStyle yylearningstyle = requst2Bean(request,YyLearningStyle.class);
			yylearningstyleService.updateYyLearningStyle(yylearningstyle);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyLearningStyle", method = RequestMethod.POST)
	public String removeYyLearningStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyLearningStyle yylearningstyle = new YyLearningStyle();
			Integer id = RequestHandler.getInteger(request, "id");
			yylearningstyle.setId(id);

			yylearningstyleService.removeYyLearningStyle(yylearningstyle);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyLearningStyle", method = RequestMethod.POST)
	public String removeAllYyLearningStyle(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyLearningStyle yyLearningStyle = new YyLearningStyle();
						yyLearningStyle.setId(Integer.valueOf(id));
						yylearningstyleService.removeYyLearningStyle(yyLearningStyle);
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
