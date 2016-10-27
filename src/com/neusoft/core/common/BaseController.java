package com.neusoft.core.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.neusoft.core.common.conf.Params;
import com.neusoft.core.common.pagination.Paginating;
import com.neusoft.core.util.JsonUtil;
import com.neusoft.core.util.ServletUtil;

/**
 * 基础Controller类
 * 
 * 2014-10-24
 * 
 */
@Component
public class BaseController extends BaseObject {

	/**
	 * json操作工具
	 */
	private final static Gson g = JsonUtil.getGson();

	/**
	 * 获得登陆用户对象
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getSessionUser(HttpServletRequest request) {
		Map<String, Object> userMap = null;
		if (request.getSession().getAttribute(Params.SESSION_ATTR_KEY_USER) != null) {
			userMap = (Map<String, Object>) request.getSession().getAttribute(
					Params.SESSION_ATTR_KEY_USER);
		}
		return userMap;
	}

	protected String getParameterByEscape(HttpServletRequest request,
			String param) {
		String val = request.getParameter(param);
		if (val != null) {
			val = HtmlUtils.htmlEscape(val);
		}
		return val;
	}

	/**
	 * 获得远程IP地址
	 * 
	 * @param request
	 * @return
	 */
	protected String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		return ServletUtil.getRemoteHost(request);
	}

	/**
	 * @Title: getPaginating
	 * @Description: 获得表格翻页对象
	 * @param request类型HttpServletRequest
	 * @return Paginating对象
	 * @throws
	 */
	protected Paginating getPaginating(HttpServletRequest request) {
		Paginating page = new Paginating(request);
		return page;
	}

	/**
	 * @Title: getPaginating
	 * @Description: 获得翻页对象
	 * @param pageSize
	 *            每页显示数据数目
	 * @param pageNo
	 *            页码
	 * @return Paginating
	 */
	protected Paginating getPaginating(int pageSize, int pageNo) {
		int pageIndex = 1;
		if (pageNo > 0) {
			pageIndex = (pageNo - 1) * pageSize;
		}
		Paginating page = new Paginating(pageSize, pageIndex);
		return page;
	}

	/**
	 * 返回类型转换为string，避免精度等问题
	 * 
	 * @param map
	 * @return 转换后的json字符串
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map<String, Object> tJson(Map<String, Object> map) {

		if (map == null)
			return null;
		// if (map.get(Params.RET_CODE) != null)
		// map.put(Params.RET_CODE, g.toJson(map.get(Params.RET_CODE)));
		// if (map.get(Params.RET_MSG) != null)
		// map.put(Params.RET_MSG, g.toJson(map.get(Params.RET_MSG)));
		// if (map.get(Params.RET_OBJ) != null)
		// map.put(Params.RET_OBJ, g.toJson(map.get(Params.RET_OBJ)));
		for (String key : map.keySet()) {
			Object v = map.get(key);
			if (v != null
					&& (v instanceof Integer || v instanceof Long || v instanceof Short)) {
				map.put(key, g.toJson(String.valueOf(v)));
			} else if (v != null && (v instanceof Map)) {
				Map m = (Map) v;
				for (Object k : m.keySet()) {
					Object val = m.get(k);
					if (val != null
							&& (val instanceof Integer || val instanceof Long || val instanceof Short)) {
						m.put(k, String.valueOf(val));
					}
				}
				map.put(key, g.toJson(v));
			} else {
				map.put(key, g.toJson(v));
			}
		}
		return map;
	}

	/**
	 * @Title loadPagination
	 * @Description 将翻页信息转为String类型并加载到你的参数中,为bootstrap table表格翻页使用
	 * @param params
	 * @param pagination
	 *            void
	 */
	protected void loadPagination(Map<String, String> params,
			Paginating pagination) {
		if (pagination != null) {
			params.put("limit", String.valueOf(pagination.getPageSize()));
			params.put("offset", String.valueOf(pagination.getPageIndex()));
			if (pagination.getSort() != null)
				params.put("sort", pagination.getSort());
			if (pagination.getOrder() != null)
				params.put("order", pagination.getOrder());
		}
	}

	public static void main(String[] args) {
		System.out.println(HtmlUtils
				.htmlEscape("<input type='text'>eqeqe1313113$#@$@@$"));
		// try {
		// System.out.println(URLEncoder.encode("<input type='text'>","UTF-8"));
		//
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
