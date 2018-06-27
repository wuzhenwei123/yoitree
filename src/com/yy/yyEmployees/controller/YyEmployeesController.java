package com.yy.yyEmployees.controller;

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

import com.yy.yyEmployees.model.YyEmployees;
import com.yy.yyEmployees.service.YyEmployeesService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-09 21:07:34
 */
@Controller
@RequestMapping("/yyEmployees")
public class YyEmployeesController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyEmployeesController.class); //Logger
	
	@Autowired
	private YyEmployeesService yyemployeesService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyEmployees/yyEmployeesIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyEmployees/yyEmployeesAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyEmployees yyemployees1 = requst2Bean(request,YyEmployees.class);
		YyEmployees yyemployees = yyemployeesService.getYyEmployees(yyemployees1);
		model.addAttribute("yyemployees", yyemployees);
		return "/yyEmployees/yyEmployeesUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyEmployeesList", method = RequestMethod.GET)
	public String getYyEmployeesList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyEmployees yyemployees = requst2Bean(request,YyEmployees.class);
			int yyemployeesListCount = yyemployeesService.getYyEmployeesListCount(yyemployees);
			ResponseList<YyEmployees> yyemployeesList = null;
			if ( yyemployeesListCount > 0 )
			{
				yyemployeesList = yyemployeesService.getYyEmployeesList(yyemployees);
			} else
			{
				yyemployeesList = new ResponseList<YyEmployees>();
			}
			// 设置数据总数
			yyemployeesList.setTotalResults(yyemployeesListCount);
			
			writeSuccessMsg("ok", yyemployeesList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyEmployeesBaseList", method = RequestMethod.GET)
	public String getYyEmployeesBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyEmployees yyemployees = requst2Bean(request,YyEmployees.class);
			List<YyEmployees> yyemployeesList = yyemployeesService.getYyEmployeesBaseList(yyemployees);
			writeSuccessMsg("ok", yyemployeesList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyEmployees", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyEmployees yyemployees = requst2Bean(request,YyEmployees.class);
			yyemployeesService.insertYyEmployees(yyemployees);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyEmployees", method = RequestMethod.POST)
	public String updateYyEmployees(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyEmployees yyemployees = requst2Bean(request,YyEmployees.class);
			yyemployeesService.updateYyEmployees(yyemployees);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyEmployees", method = RequestMethod.POST)
	public String removeYyEmployees(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyEmployees yyemployees = new YyEmployees();
			Integer id = RequestHandler.getInteger(request, "id");
			yyemployees.setId(id);

			yyemployeesService.removeYyEmployees(yyemployees);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyEmployees", method = RequestMethod.POST)
	public String removeAllYyEmployees(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyEmployees yyEmployees = new YyEmployees();
						yyEmployees.setId(Integer.valueOf(id));
						yyemployeesService.removeYyEmployees(yyEmployees);
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
