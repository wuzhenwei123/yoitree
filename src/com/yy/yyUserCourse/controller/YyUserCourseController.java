package com.yy.yyUserCourse.controller;

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

import com.yy.yyUserCourse.model.YyUserCourse;
import com.yy.yyUserCourse.service.YyUserCourseService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-10-04 11:30:06
 */
@Controller
@RequestMapping("/yyUserCourse")
public class YyUserCourseController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyUserCourseController.class); //Logger
	
	@Autowired
	private YyUserCourseService yyusercourseService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUserCourse/yyUserCourseIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyUserCourse/yyUserCourseAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyUserCourse yyusercourse1 = requst2Bean(request,YyUserCourse.class);
		YyUserCourse yyusercourse = yyusercourseService.getYyUserCourse(yyusercourse1);
		model.addAttribute("yyusercourse", yyusercourse);
		return "/yyUserCourse/yyUserCourseUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyUserCourseList", method = RequestMethod.GET)
	public String getYyUserCourseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserCourse yyusercourse = requst2Bean(request,YyUserCourse.class);
			int yyusercourseListCount = yyusercourseService.getYyUserCourseListCount(yyusercourse);
			ResponseList<YyUserCourse> yyusercourseList = null;
			if ( yyusercourseListCount > 0 )
			{
				yyusercourseList = yyusercourseService.getYyUserCourseList(yyusercourse);
			} else
			{
				yyusercourseList = new ResponseList<YyUserCourse>();
			}
			// 设置数据总数
			yyusercourseList.setTotalResults(yyusercourseListCount);
			
			writeSuccessMsg("ok", yyusercourseList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyUserCourseBaseList", method = RequestMethod.GET)
	public String getYyUserCourseBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserCourse yyusercourse = requst2Bean(request,YyUserCourse.class);
			List<YyUserCourse> yyusercourseList = yyusercourseService.getYyUserCourseBaseList(yyusercourse);
			writeSuccessMsg("ok", yyusercourseList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyUserCourse", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyUserCourse yyusercourse = requst2Bean(request,YyUserCourse.class);
			yyusercourseService.insertYyUserCourse(yyusercourse);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyUserCourse", method = RequestMethod.POST)
	public String updateYyUserCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserCourse yyusercourse = requst2Bean(request,YyUserCourse.class);
			yyusercourseService.updateYyUserCourse(yyusercourse);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyUserCourse", method = RequestMethod.POST)
	public String removeYyUserCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyUserCourse yyusercourse = new YyUserCourse();
			Long id = RequestHandler.getLong(request, "id");
			yyusercourse.setId(id);

			yyusercourseService.removeYyUserCourse(yyusercourse);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyUserCourse", method = RequestMethod.POST)
	public String removeAllYyUserCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyUserCourse yyUserCourse = new YyUserCourse();
						yyUserCourse.setId(Long.valueOf(id));
						yyusercourseService.removeYyUserCourse(yyUserCourse);
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
