package com.yy.yyCourseClassify.controller;

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
import com.yy.yyCourseClassify.service.YyCourseClassifyService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-10 22:02:35
 */
@Controller
@RequestMapping("/yyCourseClassify")
public class YyCourseClassifyController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyCourseClassifyController.class); //Logger
	
	@Autowired
	private YyCourseClassifyService yycourseclassifyService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourseClassify/yyCourseClassifyIndex";
	}
	
	@RequestMapping(value = "/toAddLevelCourse", method = RequestMethod.GET)
	public String toAddLevelCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer level = RequestHandler.getInteger(request, "level");
		Long pId = RequestHandler.getLong(request, "pId");
		Long id = RequestHandler.getLong(request, "id");
		if(id!=null){
			YyCourseClassify yyCourseClassify = new YyCourseClassify();
			yyCourseClassify.setId(id);
			yyCourseClassify = yycourseclassifyService.getYyCourseClassify(yyCourseClassify);
			model.addAttribute("yyCourseClassify", yyCourseClassify);
		}
		model.addAttribute("pId", pId);
		model.addAttribute("level", level);
		return "/yyCourseClassify/addLevelCourse";
	}
	@RequestMapping(value = "/getRootNode", method = RequestMethod.GET)
	public String getRootNode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		JSONArray array = new JSONArray(); 
		try{
			YyCourseClassify yycourseclassify = requst2Bean(request,YyCourseClassify.class);
			List<YyCourseClassify> yycourseclassifyList = yycourseclassifyService.getYyCourseClassifyBaseList(yycourseclassify);
			if(yycourseclassifyList!=null&&yycourseclassifyList.size()>0){
				for (YyCourseClassify cc : yycourseclassifyList) { 
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
//	@RequestMapping(value = "/getRootNode", method = RequestMethod.GET)
//	public String getRootNode(HttpServletRequest request, HttpServletResponse response, Model model)
//	{
//		JSONArray array = new JSONArray(); 
//		try{
//			YyCourseClassify yycourseclassify = requst2Bean(request,YyCourseClassify.class);
//			yycourseclassify.setParent_id(0L);
//			List<YyCourseClassify> yycourseclassifyList = yycourseclassifyService.getYyCourseClassifyBaseList(yycourseclassify);
//			if(yycourseclassifyList!=null&&yycourseclassifyList.size()>0){
//				for (YyCourseClassify cc : yycourseclassifyList) {  
//					JSONObject jsonObject = new JSONObject();  
//					jsonObject.put("id",cc.getId());  
//					jsonObject.put("name",cc.getName());  
//					jsonObject.put("pId","0");
//					jsonObject.put("open", true);
//					//查询子节点
//					YyCourseClassify yycourseclassify1 = new YyCourseClassify();
//					yycourseclassify1.setParent_id(cc.getId());
//					List<YyCourseClassify> list = yycourseclassifyService.getYyCourseClassifyBaseList(yycourseclassify1);
//					if(list!=null&&list.size()>0){
//						JSONArray array1 = new JSONArray(); 
//						for(YyCourseClassify cc1:list){
//							JSONObject jsonObject1 = new JSONObject();  
//							jsonObject1.put("id",cc1.getId());  
//							jsonObject1.put("name",cc1.getName()); 
//							jsonObject1.put("pId",cc.getId());
//							jsonObject1.put("open", true);
//							//查找子节点
//							//查询子节点
//							YyCourseClassify yycourseclassify2 = new YyCourseClassify();
//							yycourseclassify2.setParent_id(cc1.getId());
//							List<YyCourseClassify> list2 = yycourseclassifyService.getYyCourseClassifyBaseList(yycourseclassify2);
//							if(list2!=null&&list2.size()>0){
//								JSONArray array2 = new JSONArray(); 
//								for(YyCourseClassify cc2:list2){
//									JSONObject jsonObject2 = new JSONObject();  
//									jsonObject2.put("id",cc2.getId());  
//									jsonObject2.put("name",cc2.getName()); 
//									jsonObject2.put("pId",cc1.getId());
//									array2.add(jsonObject2);
//								}
//								jsonObject1.put("children", array2);
//								array1.add(jsonObject1);
//							}
//						}
//						jsonObject.put("children", array1);
//					}
//					array.add(jsonObject);  
//				}  
//			}
//			response.setContentType("text/html;charset=utf-8");
//			response.setHeader("Cache-Control","no-cache");
//			response.getWriter().write(array.toString());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourseClassify/yyCourseClassifyAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCourseClassify yycourseclassify1 = requst2Bean(request,YyCourseClassify.class);
		YyCourseClassify yycourseclassify = yycourseclassifyService.getYyCourseClassify(yycourseclassify1);
		model.addAttribute("yycourseclassify", yycourseclassify);
		return "/yyCourseClassify/yyCourseClassifyUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyCourseClassifyList", method = RequestMethod.GET)
	public String getYyCourseClassifyList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseClassify yycourseclassify = requst2Bean(request,YyCourseClassify.class);
			int yycourseclassifyListCount = yycourseclassifyService.getYyCourseClassifyListCount(yycourseclassify);
			ResponseList<YyCourseClassify> yycourseclassifyList = null;
			if ( yycourseclassifyListCount > 0 )
			{
				yycourseclassifyList = yycourseclassifyService.getYyCourseClassifyList(yycourseclassify);
			} else
			{
				yycourseclassifyList = new ResponseList<YyCourseClassify>();
			}
			// 设置数据总数
			yycourseclassifyList.setTotalResults(yycourseclassifyListCount);
			
			writeSuccessMsg("ok", yycourseclassifyList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyCourseClassifyBaseList", method = RequestMethod.GET)
	public String getYyCourseClassifyBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseClassify yycourseclassify = requst2Bean(request,YyCourseClassify.class);
			List<YyCourseClassify> yycourseclassifyList = yycourseclassifyService.getYyCourseClassifyBaseList(yycourseclassify);
			writeSuccessMsg("ok", yycourseclassifyList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCourseClassify", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			YyCourseClassify yycourseclassify = requst2Bean(request,YyCourseClassify.class);
			yycourseclassifyService.insertYyCourseClassify(yycourseclassify);
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyCourseClassify", method = RequestMethod.POST)
	public String updateYyCourseClassify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourseClassify yycourseclassify = requst2Bean(request,YyCourseClassify.class);
			yycourseclassifyService.updateYyCourseClassify(yycourseclassify);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyCourseClassify", method = RequestMethod.POST)
	public String removeYyCourseClassify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			Long id = RequestHandler.getLong(request, "id");
			//查询是否有子节点
			YyCourseClassify yycourseclassify1 = new YyCourseClassify();
			yycourseclassify1.setParent_id(id);
			int count = yycourseclassifyService.getYyCourseClassifyListCount(yycourseclassify1);
			if(count==0){
				YyCourseClassify yycourseclassify = new YyCourseClassify();
				
				yycourseclassify.setId(id);

				yycourseclassifyService.removeYyCourseClassify(yycourseclassify);
				writeSuccessMsg("删除成功", null, response);
			}else{
				writeErrorMsg("删除失败", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyCourseClassify", method = RequestMethod.POST)
	public String removeAllYyCourseClassify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						YyCourseClassify yyCourseClassify = new YyCourseClassify();
						yyCourseClassify.setId(Long.valueOf(id));
						yycourseclassifyService.removeYyCourseClassify(yyCourseClassify);
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
			Long pId = RequestHandler.getLong(request, "pId");
			String name = RequestHandler.getString(request, "name");
			String info = RequestHandler.getString(request, "info");
			String img_url = RequestHandler.getString(request, "img_url");
			Integer level = RequestHandler.getInteger(request, "level");
			YyCourseClassify yyCourseClassify = new YyCourseClassify();
			yyCourseClassify.setName(name);
			yyCourseClassify.setLevel(level!=null?(level+1):null);
			yyCourseClassify.setParent_id(pId);
			yyCourseClassify.setInfo(info);
			yyCourseClassify.setImg_url(img_url);
			Long id = yycourseclassifyService.insertYyCourseClassify(yyCourseClassify);
			writeSuccessMsg("成功", id, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
}
