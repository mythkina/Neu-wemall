package com.neusoft.neumooc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.neusoft.core.common.BaseController;
import com.neusoft.core.common.conf.Params;
import com.neusoft.core.common.pagination.Paginating;


/**
 * @ClassName: HomeController
 * @Description: 各页调用方法
 * @author lusiyu
 * @date 2016年7月26日
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController {


	/**
	 * @Title: index
	 * @Autowired PhotoService photoservice;
	 * @Description: 进入相册管理页
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "home/index";
	}
}