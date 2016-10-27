package com.neusoft.neumooc.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.core.common.BaseController;
import com.neusoft.core.common.conf.Params;
import com.neusoft.core.util.MD5Util;
import com.neusoft.neumooc.service.UserService;



/** 
 * @ClassName: LoginController 
 * @Description: 登录 
 * @author lusiyu,huangruijin
 * @date 2016年7月26日
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	
	@Autowired UserService userService;
	/** 
	 * @Title: getUserByName 
	 * @Description: TODO 通过用户名获取用户
	 * @param request
	 * @param response
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserByName")
	public Map<String, Object> getUserByName(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> retMap = new HashMap<String, Object>();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordMD5 = MD5Util.gen(password.getBytes());
		Map<String,Object> map = userService.getUserByName(userName);
		if(map != null && map.get("password").equals(passwordMD5)){
			HttpSession session = request.getSession(true);
			session.setAttribute(Params.SESSION_ATTR_KEY_USER, userName);
			retMap.put(Params.RET_CODE, Params.RET_CODE_SUCCESS);
			retMap.put(Params.RET_OBJ, "登录成功");
		} else {// 封装返回值 用户名或密码错误
			retMap.put(Params.RET_CODE, Params.RET_CODE_ERROR);
			retMap.put(Params.RET_OBJ, "用户名或密码错误");
		}
		
		return retMap;
	}
	
	/** 
	 * @Title: login 
	 * @Description: TODO 进入登录页面
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "login";
	}
	
	/** 
	 * @Title: viewLogin 
	 * @Description: 注销登录
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute(Params.SESSION_ATTR_KEY_USER, null);
		return "login";
	}
}
