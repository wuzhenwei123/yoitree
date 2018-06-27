package com.sys.manageException.controller;

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
import com.sys.manageException.model.ManageException;
import com.sys.manageException.service.ManageExceptionService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	keeny
 * @time	2016-04-27 11:36:30
 */
@Controller
@RequestMapping("/manageException")
public class ManageExceptionController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(ManageExceptionController.class); //Logger
	
	@Autowired
	private ManageExceptionService manageexceptionService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/sys/manageException/manageExceptionIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/sys/manageException/manageExceptionAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate( HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		Integer id = RequestHandler.getInteger(request, "id");
		ManageException manageexception1 = new ManageException();
		manageexception1.setId(id);
		ManageException manageexception = manageexceptionService.getManageException(manageexception1);
		model.addAttribute("manageexception", manageexception);
		
		return "/sys/manageException/manageExceptionUpdate";
	}
	@RequestMapping(value = "/toShow", method = RequestMethod.GET)
	public String toShow(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		Integer id = RequestHandler.getInteger(request, "id");
		ManageException manageexception1 = new ManageException();
		manageexception1.setId(id);
		ManageException manageexception = manageexceptionService.getManageException(manageexception1);
		model.addAttribute("manageexception", manageexception);
		
		return "/sys/manageException/manageExceptionShow";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getManageExceptionList", method = RequestMethod.GET)
	public String getManageExceptionList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageException manageexception = requst2Bean(request,ManageException.class);
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageexception.setId(id);
			
			String exception = RequestHandler.getString(request, "exception");
			manageexception.setException(exception);
			
			String content = RequestHandler.getString(request, "content");
			manageexception.setContent(content);
			
			Date createTime1 = RequestHandler.getDate(request, "createTime1");
			manageexception.setCreateTime1(createTime1);
			Date createTime2 = RequestHandler.getDate(request, "createTime2");
			manageexception.setCreateTime2(createTime2);
			
			String remark = RequestHandler.getString(request, "remark");
			manageexception.setRemark(remark);
			
			Integer state = RequestHandler.getInteger(request, "state");
			manageexception.setState(state);
			
			String brief = RequestHandler.getString(request, "brief");
			manageexception.setBrief(brief);
			
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageexception.setSortColumn(sortColumn);
			
			int manageexceptionListCount = manageexceptionService.getManageExceptionListCount(manageexception);
			ResponseList<ManageException> manageexceptionList = null;
			if ( manageexceptionListCount > 0 )
			{
				manageexceptionList = manageexceptionService.getManageExceptionList(manageexception);
			} else
			{
				manageexceptionList = new ResponseList<ManageException>();
			}
			// 设置数据总数
			manageexceptionList.setTotalResults(manageexceptionListCount);
			
			writeSuccessMsg("ok", manageexceptionList, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getManageExceptionBaseList", method = RequestMethod.GET)
	public String getManageExceptionBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageException manageexception = new ManageException();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageexception.setId(id);
			
			String exception = RequestHandler.getString(request, "exception");
			manageexception.setException(exception);
			
			String content = RequestHandler.getString(request, "content");
			manageexception.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			manageexception.setCreateTime(createTime);
			
			String remark = RequestHandler.getString(request, "remark");
			manageexception.setRemark(remark);
			
			Integer state = RequestHandler.getInteger(request, "state");
			manageexception.setState(state);
			
			String brief = RequestHandler.getString(request, "brief");
			manageexception.setBrief(brief);
			
			List<ManageException> manageexceptionList = manageexceptionService.getManageExceptionBaseList(manageexception);
		
			writeSuccessMsg("ok", manageexceptionList, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addManageException", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			ManageException manageexception = new ManageException();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageexception.setId(id);
			String exception = RequestHandler.getString(request, "exception");
			manageexception.setException(exception);
			String content = RequestHandler.getString(request, "content");
			manageexception.setContent(content);
			Date createTime = RequestHandler.getDate(request, "createTime");
			manageexception.setCreateTime(new Date());
			String remark = RequestHandler.getString(request, "remark");
			manageexception.setRemark(remark);
			Integer state = RequestHandler.getInteger(request, "state");
			manageexception.setState(state);
			String brief = RequestHandler.getString(request, "brief");
			manageexception.setBrief(brief);
				
			manageexceptionService.insertManageException(manageexception);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateManageException", method = RequestMethod.POST)
	public String updateManageException(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageException manageexception = new ManageException();
			
			Integer id = RequestHandler.getInteger(request, "id");
			manageexception.setId(id);
			
			String exception = RequestHandler.getString(request, "exception");
			manageexception.setException(exception);
			
			String content = RequestHandler.getString(request, "content");
			manageexception.setContent(content);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			manageexception.setCreateTime(createTime);
			
			String remark = RequestHandler.getString(request, "remark");
			manageexception.setRemark(remark);
			
			Integer state = RequestHandler.getInteger(request, "state");
			manageexception.setState(state);
			
			String brief = RequestHandler.getString(request, "brief");
			manageexception.setBrief(brief);
			

			manageexceptionService.updateManageException(manageexception);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeManageException", method = RequestMethod.POST)
	public String removeManageException(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			ManageException manageexception = new ManageException();
			Integer id = RequestHandler.getInteger(request, "id");
			manageexception.setId(id);

			manageexceptionService.removeManageException(manageexception);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllManageException", method = RequestMethod.POST)
	public String removeAllManageException(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						ManageException manageException = new ManageException();
						manageException.setId(Integer.valueOf(id));
						manageexceptionService.removeManageException(manageException);
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
