package com.yy.yyFunction.controller;

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

import com.yy.yyCourseClassify.model.YyCourseClassify;
import com.yy.yyFunction.model.YyFunction;
import com.yy.yyFunction.service.YyFunctionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-22 11:31:15
 */
@Controller
@RequestMapping("/yyFunction")
public class YyFunctionController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyFunctionController.class); //Logger
	
	@Autowired
	private YyFunctionService yyfunctionService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyFunction/yyFunctionIndex";
	}
	
	@RequestMapping(value = "/getRootNode", method = RequestMethod.GET)
	public String getRootNode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		JSONArray array = new JSONArray(); 
		try{
			YyFunction yyFunction = requst2Bean(request,YyFunction.class);
			List<YyFunction> list = yyfunctionService.getYyFunctionBaseList(yyFunction);
			if(list!=null&&list.size()>0){
				for (YyFunction cc : list) { 
	                JSONObject jsonObject = new JSONObject();  
	                jsonObject.put("id",cc.getId());  
	                jsonObject.put("name",cc.getName());  
	                jsonObject.put("pId",cc.getParent_id());
	                jsonObject.put("open", true);
	                array.add(jsonObject);  
	            }  
			}
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control","no-cache");
			response.getWriter().write(array.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyFunction/yyFunctionAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyFunction yyfunction1 = requst2Bean(request,YyFunction.class);
		YyFunction yyfunction = yyfunctionService.getYyFunction(yyfunction1);
		model.addAttribute("yyfunction", yyfunction);
		return "/yyFunction/yyFunctionUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyFunctionList", method = RequestMethod.GET)
	public String getYyFunctionList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyFunction yyfunction = requst2Bean(request,YyFunction.class);
			int yyfunctionListCount = yyfunctionService.getYyFunctionListCount(yyfunction);
			ResponseList<YyFunction> yyfunctionList = null;
			if ( yyfunctionListCount > 0 )
			{
				yyfunctionList = yyfunctionService.getYyFunctionList(yyfunction);
			} else
			{
				yyfunctionList = new ResponseList<YyFunction>();
			}
			// 设置数据总数
			yyfunctionList.setTotalResults(yyfunctionListCount);
			
			writeSuccessMsg("ok", yyfunctionList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyFunctionBaseList", method = RequestMethod.GET)
	public String getYyFunctionBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyFunction yyfunction = requst2Bean(request,YyFunction.class);
			List<YyFunction> yyfunctionList = yyfunctionService.getYyFunctionBaseList(yyfunction);
			writeSuccessMsg("ok", yyfunctionList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyFunction", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyFunction yyfunction = requst2Bean(request,YyFunction.class);
			yyfunctionService.insertYyFunction(yyfunction);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyFunction", method = RequestMethod.POST)
	public String updateYyFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyFunction yyfunction = requst2Bean(request,YyFunction.class);
			yyfunctionService.updateYyFunction(yyfunction);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyFunction", method = RequestMethod.POST)
	public String removeYyFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyFunction yyfunction = new YyFunction();
			Integer id = RequestHandler.getInteger(request, "id");
			yyfunction.setId(id);

			yyfunctionService.removeYyFunction(yyfunction);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyFunction", method = RequestMethod.POST)
	public String removeAllYyFunction(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyFunction yyFunction = new YyFunction();
						yyFunction.setId(Integer.valueOf(id));
						yyfunctionService.removeYyFunction(yyFunction);
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
	
	@RequestMapping(value = "/addNodes", method = RequestMethod.POST)
	public String addNodes(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			Integer pId = RequestHandler.getInteger(request, "pId");
			String name = RequestHandler.getString(request, "name");
			YyFunction yyFunction = new YyFunction();
			yyFunction.setName(name);
			yyFunction.setParent_id(pId);
			Integer id = yyfunctionService.insertYyFunction(yyFunction);
			writeSuccessMsg("成功", id, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
}
