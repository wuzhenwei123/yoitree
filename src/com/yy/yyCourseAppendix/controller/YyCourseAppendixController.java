package com.yy.yyCourseAppendix.controller;

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

import com.yy.yyCourseAppendix.model.YyCourseAppendix;
import com.yy.yyCourseAppendix.service.YyCourseAppendixService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-14 18:26:43
 */
@Controller
@RequestMapping("/yyCourseAppendix")
public class YyCourseAppendixController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyCourseAppendixController.class); //Logger
	
	@Autowired
	private YyCourseAppendixService yycourseappendixService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourseAppendix/yyCourseAppendixIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourseAppendix/yyCourseAppendixAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCourseAppendix yycourseappendix1 = requst2Bean(request,YyCourseAppendix.class);
		YyCourseAppendix yycourseappendix = yycourseappendixService.getYyCourseAppendix(yycourseappendix1);
		model.addAttribute("yycourseappendix", yycourseappendix);
		return "/yyCourseAppendix/yyCourseAppendixUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyCourseAppendixList", method = RequestMethod.GET)
	public String getYyCourseAppendixList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseAppendix yycourseappendix = requst2Bean(request,YyCourseAppendix.class);
			int yycourseappendixListCount = yycourseappendixService.getYyCourseAppendixListCount(yycourseappendix);
			ResponseList<YyCourseAppendix> yycourseappendixList = null;
			if ( yycourseappendixListCount > 0 )
			{
				yycourseappendixList = yycourseappendixService.getYyCourseAppendixList(yycourseappendix);
			} else
			{
				yycourseappendixList = new ResponseList<YyCourseAppendix>();
			}
			// 设置数据总数
			yycourseappendixList.setTotalResults(yycourseappendixListCount);
			
			writeSuccessMsg("ok", yycourseappendixList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyCourseAppendixBaseList", method = RequestMethod.GET)
	public String getYyCourseAppendixBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseAppendix yycourseappendix = requst2Bean(request,YyCourseAppendix.class);
			List<YyCourseAppendix> yycourseappendixList = yycourseappendixService.getYyCourseAppendixBaseList(yycourseappendix);
			writeSuccessMsg("ok", yycourseappendixList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCourseAppendix", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyCourseAppendix yycourseappendix = requst2Bean(request,YyCourseAppendix.class);
			yycourseappendixService.insertYyCourseAppendix(yycourseappendix);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCourseAppendix1", method = RequestMethod.POST)
	public String addYyCourseAppendix1(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyCourseAppendix yycourseappendix = requst2Bean(request,YyCourseAppendix.class);
			yycourseappendixService.insertYyCourseAppendix1(yycourseappendix);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyCourseAppendix", method = RequestMethod.POST)
	public String updateYyCourseAppendix(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseAppendix yycourseappendix = requst2Bean(request,YyCourseAppendix.class);
			yycourseappendixService.updateYyCourseAppendix(yycourseappendix);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyCourseAppendix", method = RequestMethod.POST)
	public String removeYyCourseAppendix(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseAppendix yycourseappendix = new YyCourseAppendix();
			Long id = RequestHandler.getLong(request, "id");
			yycourseappendix.setId(id);

			yycourseappendixService.removeYyCourseAppendix(yycourseappendix);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyCourseAppendix", method = RequestMethod.POST)
	public String removeAllYyCourseAppendix(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyCourseAppendix yyCourseAppendix = new YyCourseAppendix();
						yyCourseAppendix.setId(Long.valueOf(id));
						yycourseappendixService.removeYyCourseAppendix(yyCourseAppendix);
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
