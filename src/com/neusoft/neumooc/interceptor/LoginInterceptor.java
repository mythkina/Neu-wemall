package com.neusoft.neumooc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neusoft.core.common.conf.Params;


/** 
 * @ClassName: LoginInterceptor 
 * @Description: TODO 拦截除登录以外的其他功能，需登录才能进入其他页面
 * @author huangruijin
 * @date 2016年7月28日
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		Object session = request.getSession().getAttribute(Params.SESSION_ATTR_KEY_USER);
		if(session != null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/login/login");
		}
		return false;
	}

}
