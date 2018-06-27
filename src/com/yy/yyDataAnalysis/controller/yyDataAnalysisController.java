package com.yy.yyDataAnalysis.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.RequestHandler;
import com.yy.yyCompany.model.YyCompany;
import com.yy.yyCompany.service.YyCompanyService;
import com.yy.yyUser.model.YyUser;
import com.yy.yyUser.service.YyUserService;

@Controller
@RequestMapping("/yyDataAnalysisController")
public class yyDataAnalysisController extends BaseController{
	
	@Autowired
	private YyCompanyService yycompanyService = null;
	@Autowired
	private YyUserService yyuserService = null;
	
	@RequestMapping(value = "/toCompanyAnalysis", method = RequestMethod.GET)
	public String toCompanyAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyDataAnalysis/companyAnalysis";
	}
	@RequestMapping(value = "/toUserAnalysis", method = RequestMethod.GET)
	public String toUserAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyDataAnalysis/userAnalysis";
	}
	@RequestMapping(value = "/toUserCountAnalysis", method = RequestMethod.GET)
	public String toUserCountAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyDataAnalysis/userCountAnalysis";
	}
	@RequestMapping(value = "/toUserStudyAnalysis", method = RequestMethod.GET)
	public String toUserStudyAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/yyDataAnalysis/userStudyAnalysis";
	}
	
	@RequestMapping(value = "/companyAnalysis", method = RequestMethod.GET)
	public String companyAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String year_str = RequestHandler.getString(request, "year_str");
			Integer month = 12;
			if(!StringUtils.isNotBlank(year_str)){
				year_str = sf.format(new Date()).substring(0, 4);
				month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
			}else{
				String year_str_now = sf.format(new Date()).substring(0, 4);
				if(year_str.equals(year_str_now)){
					month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
				}
			}
			JSONObject json = new JSONObject();
			YyCompany yyCompany = new YyCompany();
			yyCompany.setYear_str(year_str);
			List<YyCompany> list = yycompanyService.companyAnalysis(yyCompany);
//			if(list!=null&&list.size()>0){
			String data_str = null;
			String mouthY_str = null;
			for(int i=1;i<=month;i++){
				String mouthY = null;
				if(i<10){
					mouthY = "0" + i;
				}else{
					mouthY = i + "";
				}
				
				Integer data = 0;
				if(list!=null&&list.size()>0){
					for(YyCompany c:list){
						if(mouthY!=null&&(year_str+"-"+mouthY).equals(c.getMouthA())){
							data = c.getCompanyCount();
						}
						
					}
				}
				if(data_str==null){
					data_str = data + "";
				}else{
					data_str = data_str + "," + data;
				}
				
				if(mouthY_str==null){
					mouthY_str = "'" + mouthY + "月'";
				}else{
					mouthY_str = mouthY_str + ",'" + mouthY + "月'";
				}
				
			}
			json.put("year_str", year_str);
			json.put("mouthY_str", mouthY_str);
			json.put("data_str", data_str);
			writeSuccessMsg("ok", json.toString(), response);
//			}else{
//				writeErrorMsg("nodata", null, response);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/userAnalysis", method = RequestMethod.GET)
	public String userAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String year_str = RequestHandler.getString(request, "year_str");
			Integer month = 12;
			if(!StringUtils.isNotBlank(year_str)){
				year_str = sf.format(new Date()).substring(0, 4);
				month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
			}else{
				String year_str_now = sf.format(new Date()).substring(0, 4);
				if(year_str.equals(year_str_now)){
					month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
				}
			}
			JSONObject json = new JSONObject();
			YyUser yyUser = new YyUser();
			yyUser.setYear_str(year_str);
			List<YyUser> list = yyuserService.userAnalysis(yyUser);
//			if(list!=null&&list.size()>0){
			String data_str = null;
			String mouthY_str = null;
			for(int i=1;i<=month;i++){
				String mouthY = null;
				if(i<10){
					mouthY = "0" + i;
				}else{
					mouthY = i + "";
				}
				
				Integer data = 0;
				if(list!=null&&list.size()>0){
					for(YyUser c:list){
						if(mouthY!=null&&(year_str+"-"+mouthY).equals(c.getMouthA())){
							data = c.getUserCount();
						}
						
					}
				}
				if(data_str==null){
					data_str = data + "";
				}else{
					data_str = data_str + "," + data;
				}
				
				if(mouthY_str==null){
					mouthY_str = "'" + mouthY + "月'";
				}else{
					mouthY_str = mouthY_str + ",'" + mouthY + "月'";
				}
				
			}
			json.put("year_str", year_str);
			json.put("mouthY_str", mouthY_str);
			json.put("data_str", data_str);
			writeSuccessMsg("ok", json.toString(), response);
//			}else{
//				writeErrorMsg("nodata", null, response);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/userCountAnalysis", method = RequestMethod.GET)
	public String userCountAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String year_str = RequestHandler.getString(request, "year_str");
			Integer month = 12;
			if(!StringUtils.isNotBlank(year_str)){
				year_str = sf.format(new Date()).substring(0, 4);
				month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
			}else{
				String year_str_now = sf.format(new Date()).substring(0, 4);
				if(year_str.equals(year_str_now)){
					month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
				}
			}
			JSONObject json = new JSONObject();
			YyUser yyUser = new YyUser();
			yyUser.setYear_str(year_str);
			List<YyUser> list = yyuserService.userAnalysis(yyUser);
			
			
//			if(list!=null&&list.size()>0){
			String data_str = null;
			String data_str1 = null;
			String mouthY_str = null;
			for(int i=1;i<=month;i++){
				String mouthY = null;
				if(i<10){
					mouthY = "0" + i;
				}else{
					mouthY = i + "";
				}
				
				YyUser yyUser1 = new YyUser();
				yyUser1.setYear_str(year_str+"-"+mouthY);
				List<YyUser> list1 = yyuserService.userCountAnalysis(yyUser1);
				
				Integer data = 0;
				if(list!=null&&list.size()>0){
					for(YyUser c:list){
						if(mouthY!=null&&(year_str+"-"+mouthY).equals(c.getMouthA())){
							data = c.getUserCount();
						}
						
					}
				}
				if(data_str==null){
					data_str = data + "";
				}else{
					data_str = data_str + "," + data;
				}
				if(data_str1==null){
					data_str1 = list1.get(0).getUserCount() + "";
				}else{
					data_str1 = data_str1 + "," + list1.get(0).getUserCount();
				}
				
				if(mouthY_str==null){
					mouthY_str = "'" + mouthY + "月'";
				}else{
					mouthY_str = mouthY_str + ",'" + mouthY + "月'";
				}
				
			}
			json.put("year_str", year_str);
			json.put("mouthY_str", mouthY_str);
			json.put("data_str", data_str);
			json.put("data_str1", data_str1);
			writeSuccessMsg("ok", json.toString(), response);
//			}else{
//				writeErrorMsg("nodata", null, response);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/userStudyAnalysis", method = RequestMethod.GET)
	public String userStudyAnalysis(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String year_str = RequestHandler.getString(request, "year_str");
			Integer month = 12;
			if(!StringUtils.isNotBlank(year_str)){
				year_str = sf.format(new Date()).substring(0, 4);
				month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
			}else{
				String year_str_now = sf.format(new Date()).substring(0, 4);
				if(year_str.equals(year_str_now)){
					month =  Integer.valueOf(sf.format(new Date()).substring(5, 7));
				}
			}
			JSONObject json = new JSONObject();
			YyUser yyUser = new YyUser();
			yyUser.setYear_str(year_str);
			List<YyUser> list = yyuserService.userAnalysis(yyUser);
			
			
//			if(list!=null&&list.size()>0){
			String data_str = null;
			String data_str1 = null;
			String mouthY_str = null;
			for(int i=1;i<=month;i++){
				String mouthY = null;
				if(i<10){
					mouthY = "0" + i;
				}else{
					mouthY = i + "";
				}
				
				YyUser yyUser1 = new YyUser();
				yyUser1.setYear_str(year_str+"-"+mouthY);
				List<YyUser> list1 = yyuserService.userCountAnalysis(yyUser1);
				
				Integer data = 0;
				if(list!=null&&list.size()>0){
					for(YyUser c:list){
						if(mouthY!=null&&(year_str+"-"+mouthY).equals(c.getMouthA())){
							data = c.getUserCount();
						}
						
					}
				}
				if(data_str==null){
					data_str = data + "";
				}else{
					data_str = data_str + "," + data;
				}
				if(data_str1==null){
					data_str1 = list1.get(0).getUserCount() + "";
				}else{
					data_str1 = data_str1 + "," + list1.get(0).getUserCount();
				}
				
				if(mouthY_str==null){
					mouthY_str = "'" + mouthY + "月'";
				}else{
					mouthY_str = mouthY_str + ",'" + mouthY + "月'";
				}
				
			}
			json.put("year_str", year_str);
			json.put("mouthY_str", mouthY_str);
			json.put("data_str", data_str);
			json.put("data_str1", data_str1);
			writeSuccessMsg("ok", json.toString(), response);
//			}else{
//				writeErrorMsg("nodata", null, response);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
