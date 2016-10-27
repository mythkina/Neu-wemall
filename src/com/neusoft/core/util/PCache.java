package com.neusoft.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 加载配置文件的缓存
 * 
 * @author vincefan
 *
 */
public class PCache {
	private final static Log log = LogFactory.getLog(PCache.class);
	/**
	 * 属性实际存储内容
	 */
	private static HashMap<String, String> SYS_HM = new HashMap<String, String>();

	/**
	 * 服务中缓存内容
	 */
	private static HashMap<String, Object> SERVICE_HM = new HashMap<String, Object>();

	/**
	 * 敏感词内容List
	 */
	private static List<String> SENSITIVE_WORD_LIST = new ArrayList<String>();

	/**
	 * 敏感词内容（正则表达式形式）
	 */
	private static String SENSITIVE_WORD = null;

	/**
	 * 是否加载文件的标志位
	 */
	private static boolean isLoad = false;

	/**
	 * 普通属性入口调用方法
	 * 
	 * @return
	 */
	public static HashMap<String, String> getHM() {
		if (isLoad == false) {
			loadFile();
			isLoad = true;
		}
		return SYS_HM;
	}

	/**
	 * 服务中缓存内容入口调用方法
	 * 
	 * @return
	 */
	public static HashMap<String, Object> getServiceHM() {
		return SERVICE_HM;
	}

	/**
	 * 加载属性文件
	 */
	@SuppressWarnings("rawtypes")
	private static void loadFile() {
		log.debug("load deploy file......");
		Properties properties = new Properties();
		InputStream inputStream = null;
		String path = null;
		Set set = null;
		Iterator iter = null;
		try {
			// 加载common配置信息
			path = Thread.currentThread().getContextClassLoader()
					.getResource("deploy.properties").getPath();
			inputStream = new FileInputStream(path);
			properties.load(inputStream);

			set = properties.keySet();
			iter = set.iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				SYS_HM.put((String) key, (String) properties.get(key));
			}

			// 加载敏感词配置信息 rk_chechangying
			properties = new Properties();
			path = Thread.currentThread().getContextClassLoader()
					.getResource("sensitiveword.properties").getPath();
			inputStream = new FileInputStream(path);
			properties.load(inputStream);

			set = properties.keySet();
			iter = set.iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				SENSITIVE_WORD_LIST.add((String) properties.get(key));
			}
			SENSITIVE_WORD = SensitiveWordFilter
					.getPattern(SENSITIVE_WORD_LIST);

			inputStream.close(); // 关闭流
			log.debug("comlete load deploy file.");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} // 关闭流
		}
	}

	/**
	 * 敏感词调用方法
	 * 
	 * @return
	 */
	public static String getSensitiveWord() {
		if (isLoad == false) {
			loadFile();
			isLoad = true;
		}
		return SENSITIVE_WORD;
	}

}
