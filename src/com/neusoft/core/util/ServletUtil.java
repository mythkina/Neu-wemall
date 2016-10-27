package com.neusoft.core.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** 
* @ClassName: ServletUtil 
* @Description: httpServlet相关工具类 
* @author neusoft
* @date 2015年10月10日 上午8:17:39 
*  
*/
public class ServletUtil {
	
	/** 
	* @Title getRemoteHost 
	* @Description 获得客户端IP地址 
	* @param request
	* @return 
	* String
	*/
	public static String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/** 
	* @Title getBean 
	* @Description 从web上下文中获得spring管理的bean 
	* @param request
	* @param beanName spring配置文件中对应的bean的名称
	* @return 
	* Object bean对象
	*/
	public static Object getBean(HttpServletRequest request,String beanName){
		WebApplicationContext webApplicationContext=WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		return webApplicationContext.getBean(beanName);
	}
}
