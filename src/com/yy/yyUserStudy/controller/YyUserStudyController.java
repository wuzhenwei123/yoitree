package com.yy.yyUserStudy.controller;

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

import com.yy.yyUserStudy.model.YyUserStudy;
import com.yy.yyUserStudy.service.YyUserStudyService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-10-11 10:06:01
 */
@Controller
@RequestMapping("/yyUserStudy")
public class YyUserStudyController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyUserStudyController.class); //Logger
	
	@Autowired
	private YyUserStudyService yyuserstudyService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUserStudy/yyUserStudyIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUserStudy/yyUserStudyAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyUserStudy yyuserstudy1 = requst2Bean(request,YyUserStudy.class);
		YyUserStudy yyuserstudy = yyuserstudyService.getYyUserStudy(yyuserstudy1);
		model.addAttribute("yyuserstudy", yyuserstudy);
		return "/yyUserStudy/yyUserStudyUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyUserStudyList", method = RequestMethod.GET)
	public String getYyUserStudyList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserStudy yyuserstudy = requst2Bean(request,YyUserStudy.class);
			int yyuserstudyListCount = yyuserstudyService.getYyUserStudyListCount(yyuserstudy);
			ResponseList<YyUserStudy> yyuserstudyList = null;
			if ( yyuserstudyListCount > 0 )
			{
				yyuserstudyList = yyuserstudyService.getYyUserStudyList(yyuserstudy);
			} else
			{
				yyuserstudyList = new ResponseList<YyUserStudy>();
			}
			// 设置数据总数
			yyuserstudyList.setTotalResults(yyuserstudyListCount);
			
			writeSuccessMsg("ok", yyuserstudyList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyUserStudyBaseList", method = RequestMethod.GET)
	public String getYyUserStudyBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserStudy yyuserstudy = requst2Bean(request,YyUserStudy.class);
			List<YyUserStudy> yyuserstudyList = yyuserstudyService.getYyUserStudyBaseList(yyuserstudy);
			writeSuccessMsg("ok", yyuserstudyList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyUserStudy", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyUserStudy yyuserstudy = requst2Bean(request,YyUserStudy.class);
			yyuserstudyService.insertYyUserStudy(yyuserstudy);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyUserStudy", method = RequestMethod.POST)
	public String updateYyUserStudy(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserStudy yyuserstudy = requst2Bean(request,YyUserStudy.class);
			yyuserstudyService.updateYyUserStudy(yyuserstudy);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyUserStudy", method = RequestMethod.POST)
	public String removeYyUserStudy(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserStudy yyuserstudy = new YyUserStudy();
			Long id = RequestHandler.getLong(request, "id");
			yyuserstudy.setId(id);

			yyuserstudyService.removeYyUserStudy(yyuserstudy);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyUserStudy", method = RequestMethod.POST)
	public String removeAllYyUserStudy(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyUserStudy yyUserStudy = new YyUserStudy();
						yyUserStudy.setId(Long.valueOf(id));
						yyuserstudyService.removeYyUserStudy(yyUserStudy);
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
