package com.yy.yyCourse.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;










import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import com.yy.yyCourse.model.YyCourse;
import com.yy.yyCourse.service.YyCourseService;
import com.yy.yyCourseAppendix.model.YyCourseAppendix;
import com.yy.yyCourseAppendix.service.YyCourseAppendixService;
import com.yy.yyCourseFunction.model.YyCourseFunction;
import com.yy.yyCourseFunction.service.YyCourseFunctionService;
import com.yy.yyFunction.model.YyFunction;
import com.yy.yyFunction.service.YyFunctionService;
import com.yy.yyUserCourse.model.YyUserCourse;
import com.yy.yyUserCourse.service.YyUserCourseService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.ConfigConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.vod.VodDemo;
import com.base.controller.BaseController;
/**
 * @author	wzw
 * @time	2017-09-11 16:02:52
 */
@Controller
@RequestMapping("/yyCourse")
public class YyCourseController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(YyCourseController.class); //Logger
	
	@Autowired
	private YyCourseService yycourseService = null;
	@Autowired
	private YyCourseAppendixService yycourseappendixService = null;
	@Autowired
	private YyCourseFunctionService yycoursefunctionService = null;
	@Autowired
	private YyFunctionService yyfunctionService = null;
	@Autowired
	private YyUserCourseService yyusercourseService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourse/yyCourseIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyCourse/yyCourseAdd";
	}
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCourse yycourse1 = requst2Bean(request,YyCourse.class);
		YyCourse yycourse = yycourseService.getYyCourse(yycourse1);
		model.addAttribute("yycourse", yycourse);
		YyCourseAppendix yyCourseAppendix1 = new YyCourseAppendix();
		yyCourseAppendix1.setCourse_id(yycourse.getId());
		List<YyCourseAppendix> list = yycourseappendixService.getYyCourseAppendixBaseList(yyCourseAppendix1);
		model.addAttribute("list", list);
		return "/yyCourse/yyCourseUpdate";
	}
	
	@RequestMapping(value = "/showVideo", method = RequestMethod.GET)
	public String showVideo(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCourse yycourse1 = requst2Bean(request,YyCourse.class);
		YyCourse yycourse = yycourseService.getYyCourse(yycourse1);
		model.addAttribute("yycourse", yycourse);
		return "/yyCourse/showVideo";
	}
	
	@RequestMapping(value = "/showAudio", method = RequestMethod.GET)
	public String showAudio(HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		YyCourse yycourse1 = requst2Bean(request,YyCourse.class);
		YyCourse yycourse = yycourseService.getYyCourse(yycourse1);
		model.addAttribute("yycourse", yycourse);
		return "/yyCourse/showAudio";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getYyCourseList", method = RequestMethod.GET)
	public String getYyCourseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourse yycourse = requst2Bean(request,YyCourse.class);
			int yycourseListCount = yycourseService.getYyCourseListCount(yycourse);
			ResponseList<YyCourse> yycourseList = null;
			if ( yycourseListCount > 0 )
			{
				yycourseList = yycourseService.getYyCourseList(yycourse);
			} else
			{
				yycourseList = new ResponseList<YyCourse>();
			}
			// 设置数据总数
			yycourseList.setTotalResults(yycourseListCount);
			
			writeSuccessMsg("ok", yycourseList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getYyCourseBaseList", method = RequestMethod.GET)
	public String getYyCourseBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourse yycourse = requst2Bean(request,YyCourse.class);
			List<YyCourse> yycourseList = yycourseService.getYyCourseBaseList(yycourse);
			writeSuccessMsg("ok", yycourseList, response);
		} catch (Exception e){
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addYyCourse", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			String fujianUrl = RequestHandler.getString(request, "fujianUrl");
			String videoFileName = RequestHandler.getString(request, "videoFileName");
			String audioFileName = RequestHandler.getString(request, "audioFileName");
			YyCourse yycourse = requst2Bean(request,YyCourse.class);
			long id = yycourseService.insertYyCourse(yycourse);
			String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT;
			if(StringUtils.isNotBlank(yycourse.getVideo_url())){
				String fileId = VodDemo.vodUplpad(saveFilePath + yycourse.getVideo_url(),"mp4",videoFileName);
				YyCourse yycourse1 = new YyCourse();
				yycourse1.setVideo_fileId(fileId);
				yycourse1.setId(id);
				yycourse1.setVideo_state(2);
				yycourseService.updateYyCourse(yycourse1);
			}
			if(StringUtils.isNotBlank(yycourse.getMp3_url())){
				String fileId = VodDemo.vodUplpad(saveFilePath + yycourse.getMp3_url(),"mp3",audioFileName);
				YyCourse yycourse1 = new YyCourse();
				yycourse1.setAudio_fileId(fileId);
				yycourse1.setId(id);
				yycourse1.setAudio_state(2);
				yycourseService.updateYyCourse(yycourse1);
			}
			
			if(id>0&&StringUtils.isNotBlank(fujianUrl)){
				String fujianUrls[] = fujianUrl.split(",");
				for(String fj:fujianUrls){
					String fjparam[] = fj.split("_");
					YyCourseAppendix yyCourseAppendix = new YyCourseAppendix();
					yyCourseAppendix.setCourse_id(id);
					yyCourseAppendix.setName(fjparam[1]);
					yyCourseAppendix.setUrl(fjparam[0]);
					yycourseappendixService.insertYyCourseAppendix(yyCourseAppendix);
				}
			}
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateYyCourse", method = RequestMethod.POST)
	public String updateYyCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			String fujianUrl = RequestHandler.getString(request, "fujianUrl");
			YyCourse yycourse = requst2Bean(request,YyCourse.class);
			
			String videoFileName = RequestHandler.getString(request, "videoFileName");
			String audioFileName = RequestHandler.getString(request, "audioFileName");
			
			YyCourse yycourse1 = new YyCourse();
			yycourse1.setId(yycourse.getId());
			yycourse1 = yycourseService.getYyCourse(yycourse1);
			int flag = yycourseService.updateYyCourse(yycourse);
			String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT;
			if(StringUtils.isNotBlank(yycourse.getVideo_url())&&!yycourse.getVideo_url().equals(yycourse1.getVideo_url())){
				String fileId = VodDemo.vodUplpad(saveFilePath + yycourse.getVideo_url(),"mp4",videoFileName);
				YyCourse yycourse2 = new YyCourse();
				yycourse2.setVideo_fileId(fileId);
				yycourse2.setId(yycourse.getId());
				yycourse2.setVideo_state(2);
				yycourseService.updateYyCourse(yycourse2);
			}
			if(StringUtils.isNotBlank(yycourse.getMp3_url())&&!yycourse.getMp3_url().equals(yycourse1.getMp3_url())){
				String fileId = VodDemo.vodUplpad(saveFilePath + yycourse.getMp3_url(),"mp3",audioFileName);
				YyCourse yycourse2 = new YyCourse();
				yycourse2.setAudio_fileId(fileId);
				yycourse2.setId(yycourse.getId());
				yycourse2.setAudio_state(2);
				yycourseService.updateYyCourse(yycourse2);
			}
			
			
			if(flag>0&&StringUtils.isNotBlank(fujianUrl)){
				//删除原来的
				YyCourseAppendix yyCourseAppendix1 = new YyCourseAppendix();
				yyCourseAppendix1.setCourse_id(yycourse.getId());
				List<YyCourseAppendix> list = yycourseappendixService.getYyCourseAppendixBaseList(yyCourseAppendix1);
				if(list!=null&&list.size()>0){
					for(YyCourseAppendix fj:list){
						yycourseappendixService.removeYyCourseAppendix(fj);
					}
				}
				String fujianUrls[] = fujianUrl.split(",");
				for(String fj:fujianUrls){
					String fjparam[] = fj.split("_");
					YyCourseAppendix yyCourseAppendix = new YyCourseAppendix();
					yyCourseAppendix.setCourse_id(yycourse.getId());
					yyCourseAppendix.setName(fjparam[1]);
					yyCourseAppendix.setUrl(fjparam[0]);
					yycourseappendixService.insertYyCourseAppendix(yyCourseAppendix);
				}
			}
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeYyCourse", method = RequestMethod.POST)
	public String removeYyCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			YyCourse yycourse = new YyCourse();
			Long id = RequestHandler.getLong(request, "id");
			yycourse.setId(id);
			//判断是否已经分配
			YyUserCourse yyUserCourse = new YyUserCourse();
			yyUserCourse.setCourse_id(id);
			int count = yyusercourseService.getYyUserCourseListCount(yyUserCourse);
			if(count==0){
				//删除原来的
				YyCourseAppendix yyCourseAppendix1 = new YyCourseAppendix();
				yyCourseAppendix1.setCourse_id(id);
				List<YyCourseAppendix> list = yycourseappendixService.getYyCourseAppendixBaseList(yyCourseAppendix1);
				if(list!=null&&list.size()>0){
					for(YyCourseAppendix fj:list){
						yycourseappendixService.removeYyCourseAppendix(fj);
					}
				}
				yycourseService.removeYyCourse(yycourse);
				writeSuccessMsg("删除成功", null, response);
			}else{
				writeErrorMsg("删除失败，该知识点已分配学员", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(exception(e), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllYyCourse", method = RequestMethod.POST)
	public String removeAllYyCourse(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						//判断是否已经分配
						YyUserCourse yyUserCourse = new YyUserCourse();
						yyUserCourse.setCourse_id(Long.valueOf(id));
						int count = yyusercourseService.getYyUserCourseListCount(yyUserCourse);
						if(count==0){
							YyCourse yyCourse = new YyCourse();
							yyCourse.setId(Long.valueOf(id));
							yycourseService.removeYyCourse(yyCourse);
							//删除原来的
							YyCourseAppendix yyCourseAppendix1 = new YyCourseAppendix();
							yyCourseAppendix1.setCourse_id(Long.valueOf(id));
							List<YyCourseAppendix> list = yycourseappendixService.getYyCourseAppendixBaseList(yyCourseAppendix1);
							if(list!=null&&list.size()>0){
								for(YyCourseAppendix fj:list){
									yycourseappendixService.removeYyCourseAppendix(fj);
								}
							}
						}
						
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
	
	@RequestMapping(value = "/courseNotify", method = RequestMethod.POST)
	public String courseNotify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			System.out.println("---------------------回放转码回调开始-------------------------------------------");
			InputStream in = request.getInputStream();
			InputStreamReader ir = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(ir);
			String result = br.readLine();
			System.out.println("-----------------------------回放转码回调开始----------------------------------------------------"+result);
			JSONObject json = JSONObject.parseObject(result);
			JSONObject data = json.getJSONObject("data");
			if(data!=null){
//				String ret = data.getString("ret");
				String fileId = data.getString("fileId");
				System.out.println("--------------------fileId-------------------------------------------"+fileId);
//				JSONObject image_video = data.getJSONObject("image_video");
				JSONArray jsonArray = JSONArray.parseArray(data.getString("playSet"));
				if(jsonArray!=null&&jsonArray.size()>0){
					String url = ((JSONObject) jsonArray.get(1)).getString("url");
					if(StringUtils.isNotBlank(fileId)){
						YyCourse yycourse = new YyCourse();
						yycourse.setVideo_fileId(fileId);
						yycourse = yycourseService.getYyCourse(yycourse);
						if(yycourse!=null&&yycourse.getId().intValue()>0){
							yycourse.setVideo_state(1);
							yycourse.setVideo_play(url);
							yycourseService.updateYyCourse(yycourse);
						}else{
							YyCourse yycourseq = new YyCourse();
							yycourseq.setAudio_fileId(fileId);
							yycourseq = yycourseService.getYyCourse(yycourseq);
							if(yycourseq!=null&&yycourseq.getId().intValue()>0){
								yycourseq.setAudio_state(1);
								yycourseq.setAudio_play(url);
								yycourseService.updateYyCourse(yycourseq);
							}
						}
					}
				}
			}
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/function_set", method = RequestMethod.GET)
	public String function_set(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Long id = RequestHandler.getLong(request, "id");
		
		JSONArray array = new JSONArray(); 
		try{
			
			YyFunction yyFunction = new YyFunction();
			List<YyFunction> list = yyfunctionService.getYyFunctionBaseList(yyFunction);
			
			
			YyCourseFunction yyCourseFunction = new YyCourseFunction();
			yyCourseFunction.setCourse_classify_id(id);
			List<YyCourseFunction> listSel = yycoursefunctionService.getYyCourseFunctionBaseList(yyCourseFunction);
			
			if(list!=null&&list.size()>0){
				for (YyFunction cc : list) { 
					JSONObject jsonObject = new JSONObject(); 
					jsonObject.put("pId", cc.getParent_id());
					jsonObject.put("id", cc.getId());
					jsonObject.put("name", cc.getName());
					jsonObject.put("open", true);// 默认打开
					boolean b = false;
					if(listSel!=null&&listSel.size()>0){
						for(YyCourseFunction cf:listSel){
							if(cc.getId().intValue()==cf.getFunction_id().intValue()){
								b = true;
								break;
							}
						}
					}
					if(b){
						jsonObject.put("checked", true);// 默认不选上
					}else{
						jsonObject.put("checked", false);// 默认不选上
					}
					array.add(jsonObject);
	            }  
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("course_id", id);
		model.addAttribute("nodes", array.toString());
		return "/yyCourse/function_set";
	}
}
