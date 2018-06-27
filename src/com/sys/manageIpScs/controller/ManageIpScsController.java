package com.sys.manageIpScs.controller;

import java.util.Date;
import org.apache.log4j.Logger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sys.manageIpScs.model.ManageIpScs;
import com.sys.manageIpScs.service.ManageIpScsService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	keeny
 * @time	2016-05-04 11:40:08
 */
@Controller
@RequestMapping("/manageIpScs")
public class ManageIpScsController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(ManageIpScsController.class); //Logger
	
	@Autowired
	private ManageIpScsService manageipscsService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/sys/manageIpScs/manageIpScsIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/sys/manageIpScs/manageIpScsAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		Integer id = RequestHandler.getInteger(request, "id");
		ManageIpScs manageipscs1 = new ManageIpScs();
		manageipscs1.setId(id);
		ManageIpScs manageipscs = manageipscsService.getManageIpScs(manageipscs1);
		model.addAttribute("manageipscs", manageipscs);
		
		return "/sys/manageIpScs/manageIpScsUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getManageIpScsList", method = RequestMethod.GET)
	public String getManageIpScsList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageIpScs manageipscs = requst2Bean(request,ManageIpScs.class);
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageipscs.setId(id);
			
			String requestIP = RequestHandler.getString(request, "requestIP");
			manageipscs.setRequestIP(requestIP);
			
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageipscs.setRequestCount(requestCount);
			
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageipscs.setRequestDateTime(requestDateTime);
			
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageipscs.setSortColumn(sortColumn);
			
			int manageipscsListCount = manageipscsService.getManageIpScsListCount(manageipscs);
			ResponseList<ManageIpScs> manageipscsList = null;
			if ( manageipscsListCount > 0 )
			{
				manageipscsList = manageipscsService.getManageIpScsList(manageipscs);
			} else
			{
				manageipscsList = new ResponseList<ManageIpScs>();
			}
			// 设置数据总数
			manageipscsList.setTotalResults(manageipscsListCount);
			
			writeSuccessMsg("ok", manageipscsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getManageIpScsBaseList", method = RequestMethod.GET)
	public String getManageIpScsBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageIpScs manageipscs = new ManageIpScs();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageipscs.setId(id);
			
			String requestIP = RequestHandler.getString(request, "requestIP");
			manageipscs.setRequestIP(requestIP);
			
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageipscs.setRequestCount(requestCount);
			
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageipscs.setRequestDateTime(requestDateTime);
			
			List<ManageIpScs> manageipscsList = manageipscsService.getManageIpScsBaseList(manageipscs);
		
			writeSuccessMsg("ok", manageipscsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addManageIpScs", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			ManageIpScs manageipscs = new ManageIpScs();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageipscs.setId(id);
			String requestIP = RequestHandler.getString(request, "requestIP");
			manageipscs.setRequestIP(requestIP);
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageipscs.setRequestCount(requestCount);
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageipscs.setRequestDateTime(requestDateTime);
				
			manageipscsService.insertManageIpScs(manageipscs);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateManageIpScs", method = RequestMethod.POST)
	public String updateManageIpScs(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageIpScs manageipscs = new ManageIpScs();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageipscs.setId(id);
			
			String requestIP = RequestHandler.getString(request, "requestIP");
			manageipscs.setRequestIP(requestIP);
			
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageipscs.setRequestCount(requestCount);
			
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageipscs.setRequestDateTime(requestDateTime);
			

			manageipscsService.updateManageIpScs(manageipscs);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeManageIpScs", method = RequestMethod.POST)
	public String removeManageIpScs(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageIpScs manageipscs = new ManageIpScs();
			Integer id = RequestHandler.getInteger(request, "id");
			manageipscs.setId(id);

			manageipscsService.removeManageIpScs(manageipscs);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllManageIpScs", method = RequestMethod.POST)
	public String removeAllManageIpScs(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						ManageIpScs manageIpScs = new ManageIpScs();
						manageIpScs.setId(Integer.valueOf(id));
						manageipscsService.removeManageIpScs(manageIpScs);
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
