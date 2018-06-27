package com.sys.manageActScs.controller;

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

import com.sys.manageActScs.model.ManageActScs;
import com.sys.manageActScs.service.ManageActScsService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	keeny
 * @time	2016-05-04 10:19:14
 */
@Controller
@RequestMapping("/manageActScs")
public class ManageActScsController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(ManageActScsController.class); //Logger
	
	@Autowired
	private ManageActScsService manageactscsService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/sys/manageActScs/manageActScsIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/sys/manageActScs/manageActScsAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		Integer id = RequestHandler.getInteger(request, "id");
		ManageActScs manageactscs1 = new ManageActScs();
		manageactscs1.setId(id);
		ManageActScs manageactscs = manageactscsService.getManageActScs(manageactscs1);
		model.addAttribute("manageactscs", manageactscs);
		
		return "/sys/manageActScs/manageActScsUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getManageActScsList", method = RequestMethod.GET)
	public String getManageActScsList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageActScs manageactscs = requst2Bean(request,ManageActScs.class);
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageactscs.setId(id);
			
			String requestPath = RequestHandler.getString(request, "requestPath");
			manageactscs.setRequestPath(requestPath);
			
			String createTime2 = RequestHandler.getString(request, "createTime2");
			manageactscs.setCreateTime2(createTime2);
			String createTime1 = RequestHandler.getString(request, "createTime1");
			manageactscs.setCreateTime1(createTime1);
			
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageactscs.setRequestCount(requestCount);
			
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageactscs.setRequestDateTime(requestDateTime);
			
			String aliasName = RequestHandler.getString(request, "aliasName");
			manageactscs.setAliasName(aliasName);
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageactscs.setSortColumn(sortColumn);
			
			int manageactscsListCount = manageactscsService.getManageActScsListCount(manageactscs);
			ResponseList<ManageActScs> manageactscsList = null;
			if ( manageactscsListCount > 0 )
			{
				manageactscsList = manageactscsService.getManageActScsList(manageactscs);
			} else
			{
				manageactscsList = new ResponseList<ManageActScs>();
			}
			// 设置数据总数
			manageactscsList.setTotalResults(manageactscsListCount);
			
			writeSuccessMsg("ok", manageactscsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getManageActScsBaseList", method = RequestMethod.GET)
	public String getManageActScsBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageActScs manageactscs = new ManageActScs();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageactscs.setId(id);
			String aliasName = RequestHandler.getString(request, "aliasName");
			manageactscs.setAliasName(aliasName);
			String requestPath = RequestHandler.getString(request, "requestPath");
			manageactscs.setRequestPath(requestPath);
			
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageactscs.setRequestCount(requestCount);
			
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageactscs.setRequestDateTime(requestDateTime);
			
			List<ManageActScs> manageactscsList = manageactscsService.getManageActScsBaseList(manageactscs);
		
			writeSuccessMsg("ok", manageactscsList, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addManageActScs", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			ManageActScs manageactscs = new ManageActScs();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageactscs.setId(id);
			String requestPath = RequestHandler.getString(request, "requestPath");
			manageactscs.setRequestPath(requestPath);
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageactscs.setRequestCount(requestCount);
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageactscs.setRequestDateTime(requestDateTime);
			String aliasName = RequestHandler.getString(request, "aliasName");
			manageactscs.setAliasName(aliasName);
			manageactscsService.insertManageActScs(manageactscs);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateManageActScs", method = RequestMethod.POST)
	public String updateManageActScs(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageActScs manageactscs = new ManageActScs();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageactscs.setId(id);
			
			String requestPath = RequestHandler.getString(request, "requestPath");
			manageactscs.setRequestPath(requestPath);
			
			Integer requestCount = RequestHandler.getInteger(request, "requestCount");
			manageactscs.setRequestCount(requestCount);
			String aliasName = RequestHandler.getString(request, "aliasName");
			manageactscs.setAliasName(aliasName);
			String requestDateTime = RequestHandler.getString(request, "requestDateTime");
			manageactscs.setRequestDateTime(requestDateTime);
			

			manageactscsService.updateManageActScs(manageactscs);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeManageActScs", method = RequestMethod.POST)
	public String removeManageActScs(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageActScs manageactscs = new ManageActScs();
			Integer id = RequestHandler.getInteger(request, "id");
			manageactscs.setId(id);

			manageactscsService.removeManageActScs(manageactscs);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllManageActScs", method = RequestMethod.POST)
	public String removeAllManageActScs(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						ManageActScs manageActScs = new ManageActScs();
						manageActScs.setId(Integer.valueOf(id));
						manageactscsService.removeManageActScs(manageActScs);
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
