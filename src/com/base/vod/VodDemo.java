package com.base.vod;

import java.io.File;
import java.util.List;
import java.util.TreeMap;

import com.base.utils.ConfigConstants;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Vod;
import com.qcloud.Utilities.SHA1;
import com.qcloud.Utilities.Json.JSONObject;

public class VodDemo {
	
	public static String vodUplpad(String filePath,String fileTye,String fileName){
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		
		config.put("SecretId", ConfigConstants.SECRETID);
		config.put("SecretKey", ConfigConstants.SECRETKEY);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		String fileId = null;
		try{
			System.out.println("starting...");
//			String fileName = "d:\\test.rmvb";
			long fileSize = new File(filePath).length();
			String fileSHA1 = SHA1.fileNameToSHA(filePath);
			
			int fixDataSize = 1024*1024*10;  //每次上传字节数，可自定义
			int firstDataSize = 1024*10;    //切片上传：最小片字节数（默认不变）,如果：dataSize + offset > fileSize,把这个值变小即可
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;
			
			String result = null;
			
			if(remainderSize<=0){
				System.out.println("wrong file path...");
			}
			while (remainderSize>0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				/*
				 * 亲，输入参数的类型，记得参考wiki详细说明
				 */
				params.put("fileSha", fileSHA1);
				params.put("fileType", fileTye);
				params.put("fileName", fileName);
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", filePath);
				params.put("isTranscode", 1);
				params.put("isScreenshot", 0);
				params.put("isWatermark", 1);
				params.put("notifyUrl", ConfigConstants.CALLBACK_URL);
				
				
				result = module.call("MultipartUploadVodFile", params);
				System.out.println(result);
				JSONObject json_result = new JSONObject(result);
				code = json_result.getInt("code");
				if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=10K,满足大多数视频的上传)
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					return null;
				}
				flag = json_result.getInt("flag");
				if (flag == 1) {
					fileId = json_result.getString("fileId");
					break;
				} else {
					tmpOffset = Integer.parseInt(json_result.getString("offset"));
				}
				remainderSize = fileSize - tmpOffset;
				if (fixDataSize < remainderSize) {
					tmpDataSize = fixDataSize;
				} else {
					tmpDataSize = (int) remainderSize;
				}
			}
			System.out.println("end...");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error..."+e.toString());
		}
		return fileId;
	}
	
	public static String getUrl(String str){
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(str);
		
		int object = (Integer) json.get("code");
		
		if(object == 0){
			List<net.sf.json.JSONObject> jsonArray = (List<net.sf.json.JSONObject>) json.get("playSet");
			
			String url = (String) jsonArray.get(1).get("url");
			
			return url;
		}
		
		return "";
	}
	
	public static String getVedioUrl(String fileId){
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		
		config.put("SecretId", ConfigConstants.SECRETID);
		config.put("SecretKey", ConfigConstants.SECRETKEY);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try{
			TreeMap<String, Object> params = new TreeMap<String, Object>();
			params.put("fileId", fileId);
			
			return module.call("DescribeVodPlayUrls", params);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error..."+e.toString());
		}
		return null;
	}
	
	public static void main(String[] args) {
		ConfigConstants.init();
		String url = VodDemo.getVedioUrl("9031868223241996370");
		System.out.println(url);
	}
}
