package com.base.filter;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	HttpServletRequest orgRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
        orgRequest = request;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name)
    {
        String value = super.getParameter(name);
        if(value != null && !"".equals(value)){
        try {
			value = URLDecoder.decode(value,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        if (value != null && name.indexOf("_FCK") == -1)
        {
            value = xssEncode(value);
        }
      
        return value;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name)
    {

        String value = super.getHeader(name);
        if(value != null && !"".equals(value)){
        try {
			value = URLDecoder.decode(value,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        if (value != null && name.indexOf("_FCK") == -1)
        {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     * 
     * @param s
     * @return
     */
    private static String xssEncode(String s)
    {
        if (s == null || s.isEmpty())
        {
            return s;
        }
        
        return HTMLFilter.replaceHtmlCode(s);
        
    }

    /**
	 * 过滤url中非法字符串
	 * @param url
	 * @return
	 */
	private static String filterUrl(String url) {
		url = url.replaceAll("<script(.*)</script>", "");//过滤js脚本
		url = url.replaceAll("<","");
		url = url.replaceAll(">","");
		url = url.replaceAll(";","");
		//url = url.replaceAll("[(]","");//
		//url = url.replaceAll("[)]","");//
		//url = url.replaceAll("[+]","");//特殊字符转义
	    url = url.replaceAll("(?i)"+"alert","");
	    url = url.replaceAll("(?i)"+"javascript","");
	    url = url.replaceAll("(?i)"+"/script","");
	    url = url.replaceAll("(?i)"+"script","");
	    //url = url.replaceAll("(?i)"+"style","");
	    url = url.replaceAll("(?i)"+"src=","");
	    //url = url.replaceAll("(?i)"+"href=","");
		return url;
	}
    /**
     * 获取最原始的request
     * 
     * @return
     */
    public HttpServletRequest getOrgRequest()
    {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     * 
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req)
    {
        if (req instanceof XssHttpServletRequestWrapper)
        {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }
}