package com.base.upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.base.controller.BaseController;
import com.base.utils.DateFormatToString;
import com.base.utils.ConfigConstants;
import com.base.utils.PicCompression;
import com.base.vod.VodDemo;

/**
 * @author keeny
 * @time 2015年02月04日 21:27:20
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

	// private static Logger logger =
	// Logger.getLogger(AdminRoleController.class); //Logger

	/***************** 页面中转 *********************/
	@RequestMapping(value = "/exec")
	public String execNative(HttpServletRequest request, HttpServletResponse response,String proVal,String type) {

		JSONObject reObject = new JSONObject();
		reObject.put("code", -1);

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		// 获取文件扩展名
		if (StringUtils.isBlank(proVal)) {
			return reObject.toString();
		}
		try {
			String fileToday = DateFormatUtils.format(new Date(), "yyyy/MM/dd");

			// 获取保存文件的最终路径
			String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT;
//			String proPath = ConfigConstants.getPropValue(proVal);
			if (saveFilePath == null) {
				writeErrorMsg("保持图片根目录不能为空", null, response);
				return null;
			}
			// 获取文件
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> uploadlist = multipartRequest.getFiles("file");

			String oldFileName = "", newFileName = "";
			for (int i = 0; i < uploadlist.size(); i++) {
				MultipartFile item = (MultipartFile) uploadlist.get(i);
				if (!item.isEmpty()) {// 处理文件上传域// 忽略其他不是文件域的所有表单信息
					String uuName = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
					String filename = new String(item.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");// 取到客户端完整 路径+文件名
					oldFileName = FilenameUtils.getName(filename);// 取到文件名
					oldFileName = new String( oldFileName.getBytes("ISO-8859-1"), "UTF-8");
					String ext = FilenameUtils.getExtension(filename);// 扩展名
					newFileName =  File.separator + fileToday + File.separator + uuName + "." + ext;// 新路径
					File file = new File(saveFilePath + newFileName);
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					SaveFileFromInputStream(item.getInputStream(), saveFilePath, newFileName);//源文件
					if (StringUtils.isNotBlank(type) && type.equals("pic")) {
						PicCompression .resize(file,file);//等比例压缩文件
						PicCompression .resize(file, new File(getSPic( file.getAbsolutePath(), "_200")), 200);//200像素文件
						PicCompression .resize(file, new File(getSPic( file.getAbsolutePath(), "_100")), 100);//100像素文件
					}
					Map<String, Object> rtnMap = new HashMap<String, Object>();
					rtnMap.put("filePath", newFileName.replace("\\", "/"));
					rtnMap.put("fileName", oldFileName);
					rtnMap.put("fileSize", item.getSize());
					rtnMap.put("oldFileName", filename);
					reList.add(rtnMap);
				}
			}
			reObject.put("code", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		reObject.put("list", reList);
		writeJsonObject(reObject, response);
		return null;
	}
	
	@RequestMapping(value = "/exec1")
	public String execNative1(HttpServletRequest request, HttpServletResponse response,String proVal,String type) {
		
		JSONObject reObject = new JSONObject();
		reObject.put("code", -1);
		
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		// 获取文件扩展名
		if (StringUtils.isBlank(proVal)) {
			return reObject.toString();
		}
		try {
			String fileToday = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
			
			// 获取保存文件的最终路径
			String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT;
//			String proPath = ConfigConstants.getPropValue(proVal);
			if (saveFilePath == null) {
				writeErrorMsg("保持图片根目录不能为空", null, response);
				return null;
			}
			// 获取文件
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> uploadlist = multipartRequest.getFiles("file");
			
			String oldFileName = "", newFileName = "";
			for (int i = 0; i < uploadlist.size(); i++) {
				MultipartFile item = (MultipartFile) uploadlist.get(i);
				if (!item.isEmpty()) {// 处理文件上传域// 忽略其他不是文件域的所有表单信息
					String uuName = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
					String filename = item.getOriginalFilename();// 取到客户端完整 路径+文件名
					oldFileName = FilenameUtils.getName(filename);// 取到文件名
					oldFileName = new String( oldFileName.getBytes("ISO-8859-1"), "UTF-8");
					String ext = FilenameUtils.getExtension(filename);// 扩展名
					newFileName =  File.separator + fileToday + File.separator + uuName + "." + ext;// 新路径
					File file = new File(saveFilePath + newFileName);
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					SaveFileFromInputStream(item.getInputStream(), saveFilePath, newFileName);//源文件
					if (StringUtils.isNotBlank(type) && type.equals("pic")) {
						PicCompression .resize(file,file);//等比例压缩文件
						PicCompression .resize(file, new File(getSPic( file.getAbsolutePath(), "_200")), 200);//200像素文件
						PicCompression .resize(file, new File(getSPic( file.getAbsolutePath(), "_100")), 100);//100像素文件
					}
					Map<String, Object> rtnMap = new HashMap<String, Object>();
//					if (file.exists()) {
//						String fileId = VodDemo.vodUplpad(saveFilePath + newFileName,ext,oldFileName);
//						rtnMap.put("fileId", fileId);
//					}
					rtnMap.put("filePath", newFileName.replace("\\", "/"));
					rtnMap.put("fileName", oldFileName);
					rtnMap.put("fileSize", item.getSize());
					rtnMap.put("oldFileName", filename);
					reList.add(rtnMap);
				}
			}
			reObject.put("code", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		reObject.put("list", reList);
		writeJsonObject(reObject, response);
		return null;
	}
	
	/**
	 * 
	 * @param stream 文件流
	 * @param path 路径
	 * @param filename 文件名称
	 * @throws IOException
	 */
	private void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
	private String getSPic(String path, String exName) {

		if (path != null) {
			String ex = path.substring(path.lastIndexOf("."), path.length());
			String fp = path.substring(0, path.lastIndexOf(".")) + exName;
			return fp + ex;
		}
		return null;
	}
	private String getSavePath(String proVal){
		String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT;
		File aSaveFile = new File(saveFilePath);
		if (!aSaveFile.isDirectory())
			aSaveFile.mkdirs();
		return saveFilePath;
	}
	
	@RequestMapping(value = "/execNativeFile")
	public String execNativeFile(HttpServletRequest request,HttpServletResponse response, Model model, String proVal) {
		
		JSONObject reObject = new JSONObject();
		
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		
		try   
		{  
			
			if(request instanceof MultipartHttpServletRequest){

				MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
				MultipartFile imgFile = req.getFile("feedbackImg");
				
				
				String uuName = System.currentTimeMillis() + UUID.randomUUID().toString().split("-")[0];// 新文件名
				
				String fileToday = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
				
				// 获取保存文件的最终路径
				String saveFilePath = ConfigConstants.UPLOAD_FILE_ROOT;
				String proPath = ConfigConstants.getPropValue("sys_user_head");
				
				String newFileName = proPath + File.separator + fileToday + File.separator + uuName + ".jpg";// 新路径
				File file = new File(saveFilePath + newFileName);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				
				FileOutputStream outputStream = new FileOutputStream(saveFilePath + newFileName);
	        	InputStream in = imgFile.getInputStream();
	    	    int byteCount = 0;
	    	    byte[] bytes = new byte[1024];
	    	    while ((byteCount = in.read(bytes)) != -1){
	    	         outputStream.write(bytes, 0, byteCount);
	    	    }
	    	    outputStream.close();   
	    	    in.close();
				
				
				Map<String, Object> rtnMap = new HashMap<String, Object>();
				rtnMap.put("filePath", newFileName.replace("\\", "/"));
				reList.add(rtnMap);
				reObject.put("code", 1);
			}else{
				reObject.put("code", -1);
			}
			
		}   
		catch (Exception e)   
		{  
			e.printStackTrace();
		}  
		reObject.put("list", reList);
		writeJsonObject(reObject, response);
		return null;
	}
}
