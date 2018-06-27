package com.base.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class StandardOutput {
	public static int PAGE_NO = 1;
	public static int PAGE_SIZE = 10;

	public static void printObject(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			if (obj instanceof ResultRsp) {
				Map<String, Object> reMap = new HashMap<String, Object>();
				ResultRsp responseList = (ResultRsp) obj;
				reMap.put("code", responseList.getCode());
				reMap.put("message", responseList.getMessage());

				if (responseList.getData() instanceof ResponseList) {
					ResponseList responseList2 = (ResponseList) responseList.getData();
					reMap.put("totalResults", responseList2.getTotalResults());
					reMap.put("items", responseList2);

				} else {
					reMap.put("items", responseList.getData());
				}

				JSONObject result = JSONObject.fromObject(reMap);
				pw.print(result);
			} else {
				JSONObject result = JSONObject.fromObject(obj);
				pw.print(result);
			}

			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void print(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			JSONObject result = JSONObject.fromObject(obj);
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
