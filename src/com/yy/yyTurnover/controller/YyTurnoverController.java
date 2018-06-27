package com.yy.yyTurnover.controller;

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

import com.yy.yyTurnover.model.YyTurnover;
import com.yy.yyTurnover.service.YyTurnoverService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-09 21:16:32
 */
@Controller
@RequestMapping("/yyTurnover")
public class YyTurnoverController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyTurnoverController.class); //Logger
	
	@Autowired
	private YyTurnoverService yyturnoverService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyTurnover/yyTurnoverIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyTurnover/yyTurnoverAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyTurnover yyturnover1 = requst2Bean(request,YyTurnover.class);
		YyTurnover yyturnover = yyturnoverService.getYyTurnover(yyturnover1);
		model.addAttribute("yyturnover", yyturnover);
		return "/yyTurnover/yyTurnoverUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyTurnoverList", method = RequestMethod.GET)
	public String getYyTurnoverList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyTurnover yyturnover = requst2Bean(request,YyTurnover.class);
			int yyturnoverListCount = yyturnoverService.getYyTurnoverListCount(yyturnover);
			ResponseList<YyTurnover> yyturnoverList = null;
			if ( yyturnoverListCount > 0 )
			{
				yyturnoverList = yyturnoverService.getYyTurnoverList(yyturnover);
			} else
			{
				yyturnoverList = new ResponseList<YyTurnover>();
			}
			// 设置数据总数
			yyturnoverList.setTotalResults(yyturnoverListCount);
			
			writeSuccessMsg("ok", yyturnoverList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyTurnoverBaseList", method = RequestMethod.GET)
	public String getYyTurnoverBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyTurnover yyturnover = requst2Bean(request,YyTurnover.class);
			List<YyTurnover> yyturnoverList = yyturnoverService.getYyTurnoverBaseList(yyturnover);
			writeSuccessMsg("ok", yyturnoverList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyTurnover", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyTurnover yyturnover = requst2Bean(request,YyTurnover.class);
			yyturnoverService.insertYyTurnover(yyturnover);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyTurnover", method = RequestMethod.POST)
	public String updateYyTurnover(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyTurnover yyturnover = requst2Bean(request,YyTurnover.class);
			yyturnoverService.updateYyTurnover(yyturnover);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyTurnover", method = RequestMethod.POST)
	public String removeYyTurnover(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyTurnover yyturnover = new YyTurnover();
			Integer id = RequestHandler.getInteger(request, "id");
			yyturnover.setId(id);

			yyturnoverService.removeYyTurnover(yyturnover);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyTurnover", method = RequestMethod.POST)
	public String removeAllYyTurnover(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyTurnover yyTurnover = new YyTurnover();
						yyTurnover.setId(Integer.valueOf(id));
						yyturnoverService.removeYyTurnover(yyTurnover);
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
