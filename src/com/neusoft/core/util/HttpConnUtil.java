package com.neusoft.core.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.neusoft.core.common.conf.Params;
import com.neusoft.core.common.exception.WebServiceException;

/**
 * 调用服务资源的实现工具方式
 * 
 * @author lisonghua
 *
 */
public class HttpConnUtil {
	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(HttpConnUtil.class);
	/**
	 * json操作工具
	 */
	private final static Gson g = JsonUtil.getGson();

	private String call(String urlStr, String queryStr)
			throws WebServiceException {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		DataOutputStream out = null;

		try {
			// 建立连接
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);

			// long endTime1 = System.currentTimeMillis();
			// log.error("#1-1调用服务消耗时间：" + (endTime1 - startTime) + "ms");
			conn.connect();
			// long endTime2 = System.currentTimeMillis();
			// log.error("#1-2调用服务消耗时间：" + (endTime2 - endTime1) + "ms");
			// 发送
			out = new DataOutputStream(conn.getOutputStream());
			out.write(queryStr.getBytes(Params.ENCODING));
			out.flush();
			out.close();
			int status = conn.getResponseCode();
			boolean error = false;
			String msg = null;
			switch (status) {
			case java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT:// 504
				msg = "状态码：" + status + "访问：" + urlStr + "?" + queryStr
						+ "连接超时!";
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_FORBIDDEN:// 403
				msg = "状态码：" + status + "禁止访问!地址：" + urlStr + "?" + queryStr;
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_INTERNAL_ERROR:// 500
				msg = "状态码：" + status + "网址错误或者不存在！访问地址：" + urlStr + "?"
						+ queryStr;
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_NOT_FOUND:// 404
				msg = "状态码：" + status + "网址不存在！访问地址：" + urlStr + "?" + queryStr;
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_OK:
				msg = "状态码：" + status + "访问地址：" + urlStr + "?" + queryStr
						+ "成功!";
				error = false;
				break;
			default:
				msg = "状态码：" + status + "访问地址：" + urlStr + "?" + queryStr
						+ "失败!";
				error = true;
				break;
			}
			if (error) {
				log.error(msg);
				throw new WebServiceException(msg);// 发生错误
			} else {
				log.info(msg);
			}
			// long endTime3 = System.currentTimeMillis();
			// log.error("#1-3调用服务消耗时间：" + (endTime3 - endTime2) + "ms");
			// 读取
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), Params.ENCODING));
			// long endTime31 = System.currentTimeMillis();
			// log.error("#1-3-1调用服务消耗时间：" + (endTime31 - endTime3) + "ms");

			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			throw new WebServiceException("访问远端服务发生异常：" + e.getMessage()
					+ ";url=" + urlStr + "?" + queryStr, e);
		} finally {
			// 释放资源
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	private String call(String urlStr, String queryStr, int type, String method)
			throws WebServiceException {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		DataOutputStream out = null;
		try {
			// 建立连接
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			if (type == 1) {
				conn.setRequestProperty("Content-Type", "application/json");
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
			conn.connect();
			// POST请求
			if ("POST".equalsIgnoreCase(method)) {

				out = new DataOutputStream(conn.getOutputStream());
				out.write(queryStr.getBytes(Params.ENCODING));
				out.flush();
				out.close();
			}
			int status = conn.getResponseCode();
			boolean error = false;
			String msg = null;
			switch (status) {
			case java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT:// 504
				msg = "状态码：" + status + "访问：" + urlStr + "?" + queryStr
						+ "连接超时!";
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_FORBIDDEN:// 403
				msg = "状态码：" + status + "禁止访问!地址：" + urlStr + "?" + queryStr;
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_INTERNAL_ERROR:// 500
				msg = "状态码：" + status + "网址错误或者不存在！访问地址：" + urlStr + "?"
						+ queryStr;
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_NOT_FOUND:// 404
				msg = "状态码：" + status + "网址不存在！访问地址：" + urlStr + "?" + queryStr;
				error = true;
				break;
			case java.net.HttpURLConnection.HTTP_OK:
				msg = "状态码：" + status + "访问地址：" + urlStr + "?" + queryStr
						+ "成功!";
				error = false;
				break;
			default:
				msg = "状态码：" + status + "访问地址：" + urlStr + "?" + queryStr
						+ "失败!";
				error = true;
				break;
			}
			if (error) {
				log.error(msg);
				throw new WebServiceException(msg);// 发生错误
			} else {
				log.info(msg);
			}
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), Params.ENCODING));

			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			throw new WebServiceException("访问远端服务发生异常：" + e.getMessage()
					+ ";url=" + urlStr + "?" + queryStr, e);
		} finally {
			// 释放资源
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @Title callService2Map
	 * @Description 调用远端服务并返回map类型
	 * @param urlStr
	 *            远端服务地址
	 * @param queryStr
	 *            查询字符串
	 * @return
	 * @throws WebServiceException
	 *             Map<String,Object>
	 */
	public Map<String, Object> callService2Map(String urlStr, String queryStr)
			throws WebServiceException {
		String json = call(urlStr, queryStr);
		return dealResult2Map(json);
	}

	/**
	 * @Title callService2Map
	 * @Description TODO
	 * @param urlStr
	 * @param queryStr
	 * @param type
	 *            0 正常 1 json
	 * @param method
	 *            GET POST
	 * @return
	 * @throws WebServiceException
	 *             Map<String,Object>
	 */
	public Map<String, Object> callService2Map(String urlStr, String queryStr,
			int type, String method) throws WebServiceException {
		String json = call(urlStr, queryStr, type, method);
		return dealResult2Map(json);
	}

	/**
	 * @Title callService2List
	 * @Description 调用远端服务并返回list类型
	 * @param urlStr
	 *            远端服务地址
	 * @param queryStr
	 *            查询字符串
	 * @return
	 * @throws WebServiceException
	 *             List<Map<String,Object>>
	 */
	public List<Map<String, Object>> callService2List(String urlStr,
			String queryStr) throws WebServiceException {
		String json = call(urlStr, queryStr);
		return dealResult2List(json);
	}

	/**
	 * @deprecated 连接到服务并获取数据
	 * 
	 * @param urlStr
	 *            服务地址
	 * @param content
	 * @return
	 */
	public Map<String, Object> callSrv(String urlStr, String queryStr,
			String type) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// long startTime = System.currentTimeMillis();
		// 初始设置返回值为失败
		retMap.put(Params.RET_CODE, Params.RET_CODE_ERROR);

		HttpURLConnection conn = null;
		BufferedReader reader = null;
		DataOutputStream out = null;
		try {
			// 建立连接
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);

			// long endTime1 = System.currentTimeMillis();
			// log.error("#1-1调用服务消耗时间：" + (endTime1 - startTime) + "ms");
			conn.connect();
			// long endTime2 = System.currentTimeMillis();
			// log.error("#1-2调用服务消耗时间：" + (endTime2 - endTime1) + "ms");
			// 发送
			out = new DataOutputStream(conn.getOutputStream());
			out.write(queryStr.getBytes(Params.ENCODING));
			out.flush();
			out.close();
			// long endTime3 = System.currentTimeMillis();
			// log.error("#1-3调用服务消耗时间：" + (endTime3 - endTime2) + "ms");
			// 读取
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), Params.ENCODING));
			// long endTime31 = System.currentTimeMillis();
			// log.error("#1-3-1调用服务消耗时间：" + (endTime31 - endTime3) + "ms");

			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			// long endTime4 = System.currentTimeMillis();
			// log.error("#1-4调用服务消耗时间：" + (endTime4 - endTime31) + "ms");
			// 返回结果
			retMap = dealResult(buffer.toString(), retMap, type);
			// long endTime5 = System.currentTimeMillis();
			// log.error("#1-5调用服务消耗时间：" + (endTime5 - endTime4) + "ms");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			retMap.put(Params.RET_MSG, e.getMessage());
		} finally {
			// 释放资源
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		// long endTime = System.currentTimeMillis();
		log.debug("#1调用服务地址:" + urlStr + ",调用Restful API:" + queryStr);
		log.debug("#2最终返回内容:" + retMap);
		// log.debug("#3调用服务消耗时间：" + (endTime - startTime) + "ms");
		return retMap;
	}

	/**
	 * @deprecated 将返回结果转换为List或Map
	 * 
	 * @param resultStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> dealResult(String resultStr,
			Map<String, Object> retMap, String type) {
		// log.info("##resultStr1 is:" + resultStr);
		String resultStr2 = appendChar(resultStr);
		// log.info("##resultStr2 is:" + resultStr2);
		Map<String, Object> map;
		try {
			map = g.fromJson(resultStr2, Map.class);
		} catch (Exception e) {
			map = g.fromJson(resultStr, Map.class);
		}
		// log.info("##map is:" + map);
		if (map.get(Params.RET_CODE).equals(Params.RET_CODE_SUCCESS)) {// 成功
			try {
				retMap.put(Params.RET_CODE, Params.RET_CODE_SUCCESS);
				retMap.put(Params.RET_MSG, map.get(Params.RET_MSG));
				List<Map<String, Object>> list = (List<Map<String, Object>>) (map
						.get(Params.RET_OBJ));
				if (type.equalsIgnoreCase("MAP")) {// map
					if (list.size() == 1) {
						Map<String, Object> rMap = (Map<String, Object>) list
								.get(0);
						retMap.put(Params.RET_OBJ, rMap);// 返回的数据结果
					} else {
						retMap.put(Params.RET_OBJ, null);
						retMap.put(Params.RET_MSG, "没有Map数据，或者返回值多于1个");
						retMap.put(Params.RET_CODE, Params.RET_CODE_ERROR);
					}
				} else {// list
					retMap.put(Params.RET_OBJ, list);// 返回的数据结果
				}
			} catch (Exception e) {
				// 转换异常，尝试直接转换为Map
				Map<String, Object> rMap = (Map<String, Object>) (map
						.get(Params.RET_OBJ));
				retMap.put(Params.RET_OBJ, rMap);// 返回的数据结果
			}
		} else {// 失败
			retMap.put(Params.RET_CODE, Params.RET_CODE_ERROR);
			retMap.put(Params.RET_MSG, map.get(Params.RET_MSG));
		}

		return retMap;
	}

	// {"RET_OBJ":[{"quesid":"ques2","questitle":"课程测试答疑2","quesdetail":"答疑2详细信息","userid":"user2","opdate":1419472163000,"replytimes":100,"viewtimes":100}],"RET_CODE":"success"}
	/**
	 * 根据传输的串，还原处理，为了解决int\long\float\double的无引号精度显示问题
	 * 
	 * @param jsonStr
	 * @return 处理后的结果
	 */
	@SuppressWarnings("unchecked")
	private String appendChar(String jsonStr) {
		Map<String, Object> map = g.fromJson(jsonStr, Map.class);
		return map.toString();
		// return jsonStr;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> dealResult2Map(String resultStr) {
		String resultStr2 = appendChar(resultStr);
		Map<String, Object> map;
		try {
			map = g.fromJson(resultStr2, Map.class);
		} catch (Exception e) {
			map = g.fromJson(resultStr, Map.class);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> dealResult2List(String resultStr) {
		String resultStr2 = appendChar(resultStr);
		List<Map<String, Object>> list;
		try {
			list = g.fromJson(resultStr2, List.class);
		} catch (Exception e) {
			list = g.fromJson(resultStr, List.class);
		}
		return list;
	}

	// public static void main(String[] args) {
	// String str =
	// "{\"RET_OBJ\":[{\"quesid\":\"ques2\",\"questitle\":\"课程测试答疑2\",\"quesdetail\":\"答疑2详细信息\",\"userid\":\"user2\",\"opdate\":1419472163000,\"replytimes\":100,\"viewtimes\":100,\"ttt\":[\"1\",\"2\",\"3\"]}],\"RET_CODE\":\"success\"}";
	// System.out.println("str is:" + str);
	// String newStr = new HttpConnUtil().appendChar(str);
	// System.out.println("newStr is:" + newStr);
	// }

}
